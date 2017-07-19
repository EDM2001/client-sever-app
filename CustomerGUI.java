/**
 * Assignment:	Homework 5
 * Class:	Insy 4305 Sec. 001
 * Name:	Michael Rodriguez
 * ID:		1000408768
 *
 * Comments:	
 *		
 */

import javax.swing.*;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class CustomerGUI extends JPanel
{		
	private JLabel 				nameLabel;
	private JLabel				streetLabel;
	private JLabel 				cityLabel;
	private JLabel 				stateLabel;
	private JLabel 				zipLabel;
	private JLabel 				accountLabel;	
	private JLabel 				customerSelectLabel;
	private JLabel 				submitLabel;
	private JLabel 				resetLabel;

	private JTextField 			nameField;
	private JTextField 			streetField;
	private JTextField 			cityField;
	private JTextField 			stateField;
	private JTextField			zipField;
	private JTextField 			accountField;
	
	private JRadioButton 		studentRadioButton;
	private JRadioButton 		facultyRadioButton;
	private JRadioButton 		governmentRadioButton;
	
	private ButtonGroup 		customerSelectButtonGroup;
	
	private JPanel 				customerSelectPanel;
	
	private JButton 			resetButton;
	private JButton 			submitButton;
	
	private DatabaseMethods		databaseMethods = new DatabaseMethods();
	
	public CustomerGUI()
	{				
		setLayout(new GridLayout(9,2));
		
		nameLabel = new JLabel(" Enter Name ");
		streetLabel = new JLabel(" Enter Street ");
		cityLabel = new JLabel(" Enter City ");
		stateLabel = new JLabel(" Enter State ");
		zipLabel = new JLabel(" Enter Zip ");
		accountLabel = new JLabel(" Enter Account Number ");
		customerSelectLabel = new JLabel(" Select Type of Customer ");
		submitLabel = new JLabel(" Click Sumit When Done ");
		resetLabel = new JLabel(" Click to Reset Form ");
		
		nameField = new JTextField(20);
		streetField = new JTextField(20);
		cityField = new JTextField(20);
		stateField = new JTextField(20);
		zipField = new JTextField(20);
		accountField = new JTextField(20);
		
		nameField.setToolTipText(" Enter Customer Name Here ");
		streetField.setToolTipText(" Enter Customer's Street Name Here ");
		cityField.setToolTipText(" Enter Customer's City Name Here ");
		stateField.setToolTipText(" Enter Customer's State Name Here ");
		zipField.setToolTipText(" Enter Customer's Zip Here ");
		accountField.setToolTipText(" Enter Customer's Account Number Here ");
		
		studentRadioButton = new JRadioButton("STUDENT");
		facultyRadioButton = new JRadioButton("FACULTY");
		governmentRadioButton = new JRadioButton("GOVERNMENT");
		
		customerSelectButtonGroup = new ButtonGroup();
		
		customerSelectButtonGroup.add(studentRadioButton);
		customerSelectButtonGroup.add(facultyRadioButton);
		customerSelectButtonGroup.add(governmentRadioButton);
		
		customerSelectPanel = new JPanel(new GridLayout(1,3));
		
		customerSelectPanel.add(studentRadioButton);
		customerSelectPanel.add(facultyRadioButton);
		customerSelectPanel.add(governmentRadioButton);
					
		submitButton = new JButton("Submit");
		resetButton = new JButton("Reset");
		
		submitButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if(formIsValid())
				{
					try
					{
						addCustomer();
					}
					catch(CustomerAlreadyExistsException caee)
					{
						JOptionPane.showMessageDialog(null, "Customer " + nameField.getText() + " already exists.","Customer Already Exists", 2);
					}
					finally
					{
						clearFields();
					}
						
				}
			
			}

		}
		);
		
		resetButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				clearFields();
			}

		}
		);
		add(nameLabel);
		add(nameField);
		
		add(streetLabel);
		add(streetField);
		
		add(cityLabel);
		add(cityField);
		
		add(stateLabel);
		add(stateField);
		
		add(zipLabel);
		add(zipField);
		
		add(accountLabel);
		add(accountField);
		
		add(customerSelectLabel);
		add(customerSelectPanel);
		
		add(submitLabel);
		add(submitButton);
		
		add(resetLabel);
		add(resetButton);
	}	
	
	public void clearFields()
	{
		nameField.setText("");
		streetField.setText("");
		cityField.setText("");
		stateField.setText("");
		zipField.setText("");
		accountField.setText("");
		customerSelectButtonGroup.clearSelection();
		
		nameField.requestFocus();
	}
	
	public String formatedAddress()
	{
		String street = streetField.getText().replaceAll("\\s+", "");
		String city = cityField.getText().replaceAll("\\s+", "");
		String state = stateField.getText().replaceAll("\\s+", "");
		String zip = zipField.getText().replaceAll("\\s+", "");
		
		return street+" "+city+" "+state+" "+zip;
	}
	
	public void addCustomer() throws CustomerAlreadyExistsException
	{
		if(databaseMethods.customerExists(nameField.getText()))
			throw new CustomerAlreadyExistsException();
		
		databaseMethods.addCustomer(nameField.getText(),formatedAddress(),Integer.parseInt(accountField.getText()), getCustomerType());
		
	}
	
	public String getCustomerType()
	{
		if(studentRadioButton.isSelected())
			return studentRadioButton.getText();

		else if(facultyRadioButton.isSelected())
			return facultyRadioButton.getText();

		else
			return governmentRadioButton.getText();
	}
	
	public boolean formIsValid()
	{
		if(nameField.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(null, "Enter a Name for Customer","Field was empty",2);
			nameField.requestFocus();
			return false;			
		}
		
		else if(streetField.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(null, "Enter a Street for Customer","Field was empty",2);
			streetField.requestFocus();
			return false;			
		}
		
		else if(cityField.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(null, "Enter a City for Customer","Field was empty",2);
			cityField.requestFocus();
			return false;
		}
		
		else if(stateField.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(null, "Enter a State for Customer","Field was empty",2);
			stateField.requestFocus();
			return false;
		}
		
		else if(zipField.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(null, "Enter an integer for Zip code","Field was empty",2);
			zipField.requestFocus();
			return false;
		}
		
		else if(accountField.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(null, "Enter an integer for Account Number","Field was empty",2);
			accountField.requestFocus();
			return false;
		}
		
		else if(!studentRadioButton.isSelected()&&!facultyRadioButton.isSelected()&&!governmentRadioButton.isSelected())
		{
			JOptionPane.showMessageDialog(null, "Please Select a Customer Type","Customer Type Not Selected",2);
			customerSelectPanel.requestFocus();
			return false;
		}
		
		else if(!zipField.getText().isEmpty()||!accountField.getText().isEmpty())
		{
			String message="Enter an integer for the Zip code";
			JTextField field = zipField;
			
			try
			{
				Integer.parseInt(zipField.getText());
				
				message="Enter an integer for Account Number";
				field=accountField;
				
				Integer.parseInt(accountField.getText());
				return true;
			}
			
			catch(NumberFormatException nfe)
			{
				JOptionPane.showMessageDialog(null, message,"Enter an Integer",2);
				field.setText("");
				field.requestFocus();
				return false;
			}
			
		}
		return true;
	}
	
}

