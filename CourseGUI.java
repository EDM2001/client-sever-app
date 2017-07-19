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

public class CourseGUI extends JPanel
{	
	private static JComboBox<String> customerSelectComboBox; 
	private static JComboBox<String> courseSelectComboBox;
		
	private JLabel nameLabel;
	private JLabel courseSelectLabel;
	private JLabel submitLabel;
	private JLabel resetLabel;
	
	private JButton resetButton;
	private JButton submitButton;
	
	private CustomerTest customerTest = new CustomerTest();
	private DatabaseMethods databaseMethods = new DatabaseMethods();
	
	public CourseGUI()
	{		
		setLayout(new GridLayout(4,2));
		
		nameLabel = new JLabel(" Enter or Select Customer Name ");
		courseSelectLabel = new JLabel(" Select a Course ");
		submitLabel = new JLabel(" Click Submit When Done ");
		resetLabel = new JLabel(" Click to Reset form ");
		
		setComboBoxes();
		customerSelectComboBox.setEditable(true);
		customerSelectComboBox.setMaximumRowCount(3);
		courseSelectComboBox.setMaximumRowCount(3);

		submitButton = new JButton("Submit");
		resetButton = new JButton("Reset");

		submitButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent)
			{
				if(formIsValid())
				{
					if(userConfirmsCourse())	
						try
						{
							addCourse();
						}
					
						catch(CustomerNotFoundException cnfe)
						{
							JOptionPane.showMessageDialog(null, " Customer Not Found ","Customer Not Found", 2);
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
			public void actionPerformed(ActionEvent actionEvent)
			{
				
				clearFields();
			}

		}
		);
		
		add(nameLabel);
		add(customerSelectComboBox);
		
		add(courseSelectLabel);
		add(courseSelectComboBox);
		
		add(submitLabel);
		add(submitButton);
		
		add(resetLabel);
		add(resetButton);
	
	}
	public void setComboBoxes()
	{
		courseSelectComboBox = new JComboBox<String>(databaseMethods.getCourseTitles());
		customerSelectComboBox = new JComboBox<String>(databaseMethods.getCustomerNames());
	}
	
	public void clearFields()
	{
		customerSelectComboBox.setSelectedIndex(0);
		courseSelectComboBox.setSelectedIndex(0);
		
		customerSelectComboBox.requestFocus();
	}
	
	public void addCourse() throws CustomerNotFoundException
	{
		if(!databaseMethods.customerExists(customerSelectComboBox.getSelectedItem().toString()))
			throw new CustomerNotFoundException();
		
		databaseMethods.addCourseToCustomer(customerSelectComboBox.getSelectedItem().toString(), courseSelectComboBox.getSelectedItem().toString());
	}
	
	public boolean userConfirmsCourse()
	{
		JFrame frame = new JFrame();
		
		int result = 0;
		
		String courseConfirmMessage = "error";
		
		if(getCourseSelected() instanceof OnlineCourse)
			courseConfirmMessage = String.format("OnlineCourse%n%s%n$%s%nDates of Class%n%s-%s", getCourseSelected().getTitle(), getCourseSelected().getPrice(), formatDate(getCourseSelected().getStartDate()), formatDate(getCourseSelected().getEndDate()));
		
		if(getCourseSelected() instanceof InClassCourse)
			courseConfirmMessage = String.format("InClassCourse%n%s%n$%s%nDates of Class%n%s-%s", getCourseSelected().getTitle(), getCourseSelected().getPrice(), formatDate(getCourseSelected().getStartDate()), formatDate(getCourseSelected().getEndDate()));
		
		result = JOptionPane.showConfirmDialog(frame,  courseConfirmMessage);
		
		if (result == JOptionPane.YES_OPTION)
			return true;
		
		return false;
	}
	
	public String formatDate(Date date)
	{
		return (date.getMonth()+"/"+date.getDay());
	}
	
	public Course getCourseSelected()
	{
		return databaseMethods.getCourseArrayList().get(courseSelectComboBox.getSelectedIndex()-1);
	}
	
	public boolean formIsValid()
	{
		if(courseSelectComboBox.getSelectedIndex()==0)
		{
			JOptionPane.showMessageDialog(null, "Course Not Selected","Course Not Selected",2);
			courseSelectComboBox.setSelectedIndex(0);
			courseSelectComboBox.requestFocus();
			return false;
		}
		
		return true;
	}
}

