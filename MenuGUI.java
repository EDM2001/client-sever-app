/**
 * Assignment:		Homework 5
 * Class:		Insy 4305 Sec. 001
 * Name:		Michael Rodriguez
 * ID:			1000408768
 * Comments:	
 *		
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class MenuGUI extends JFrame
{	
	private JDesktopPane desktop;

	private JMenuBar bar;

	private JMenu addMenu;
	private JMenu invoiceMenu;
	private JMenu exitMenu;

	private JMenuItem addCustomerItem;
	private JMenuItem addCourseItem;
	private JMenuItem generateInvoiceItem;
	private JMenuItem writeDatabaseItem;
	private JMenuItem exitProgramItem;
	
	private DatabaseMethods databaseMethods = new DatabaseMethods();;
	private CourseGUI courseGUI;

	public MenuGUI()
	{
		super("Enrollment Menu");

		desktop = new JDesktopPane();

		bar = new JMenuBar();

		addMenu = new JMenu("Add");
		invoiceMenu = new JMenu("Invoice");
		exitMenu = new JMenu("Exit");

		addCustomerItem = new JMenuItem("Add Customer");
		addCourseItem = new JMenuItem("Add Course");
		generateInvoiceItem = new JMenuItem("Generate Invoice");
		writeDatabaseItem = new JMenuItem("Write Database");
		exitProgramItem = new JMenuItem("Exit Program");
		
		addCustomerItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				JInternalFrame frame = new JInternalFrame("Add Customer", true, true, true, true);
				
				CustomerGUI customerGUI = new CustomerGUI();
				
				frame.add(customerGUI);
				frame.pack();
				desktop.add(frame);
				frame.setVisible(true);
				
			}
		}
		);
		
		addCourseItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				JInternalFrame frame = new JInternalFrame("Add Course", true, true, true, true);
				
				courseGUI = new CourseGUI();
				
				frame.add(courseGUI);
				frame.pack();
				desktop.add(frame);
				frame.setVisible(true);
			}
		}
		);
		
		generateInvoiceItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				generateInvoice();			
			}
		}
		);
		
		writeDatabaseItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{				
				databaseMethods.writeCustomerTable();
				databaseMethods.writeCustomerCoursesTable();
				databaseMethods.writeCourseTable();
			}
		});
		
		exitProgramItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{			
				System.exit(0);
			}
		}
		);
		

		addMenu.add(addCustomerItem);
		addMenu.add(addCourseItem);
		
		invoiceMenu.add(generateInvoiceItem);
		
		exitMenu.add(writeDatabaseItem);
		exitMenu.add(exitProgramItem);

		bar.add(addMenu);
		bar.add(invoiceMenu);
		bar.add(exitMenu);

		add(desktop);
	
		setJMenuBar(bar);		
	}
	
	public void generateInvoice()
	{
		String invoice = "";
		
		for(Customer c:databaseMethods.getCustomerArrayList())
		{
			try
			{
				invoice += c.createInvoice();
			}
			
			catch(CustomerNotFoundException customerNotFoundException)
			{
				System.out.println(c.getName() + " is not enrolled in any courses.\n");
			}

		}
		
		JOptionPane.showMessageDialog(null, String.format("%s%20s%20s%n%s","Name","Account","Charge", invoice));
	}	
}