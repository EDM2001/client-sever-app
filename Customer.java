/**
 * Assignment:	Homework 5
 * Class:	Insy 4305 Sec. 001
 * Name:	Michael Rodriguez
 * ID:		1000408768
 * Comments:	
 */
import java.util.ArrayList;
import java.io.*;

public class Customer implements Invoice, Serializable
{
	private String name;
	private Address address;
	private int accountNumber;
	public enum CustomerType {STUDENT, FACULTY, GOVERNMENT};
	private CustomerType cType = CustomerType.STUDENT;
	private ArrayList<Course> courseList = new ArrayList<Course>();
	
	public Customer()
	{
		setName("");
		setAddress(new Address());
		setAccountNumber(0);
	}

	public Customer(String name, Address address, int accountNumber)
	{
		setName(name);
		setAddress(address);
		setAccountNumber(accountNumber);
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setAddress(Address address)
	{
		this.address = address;
	}
	
	public void setAccountNumber(int accountNumber)
	{
		this.accountNumber = accountNumber;
	}

	public void setCType(CustomerType cType)
	{
		this.cType = cType;
	}

	public void addCourse(Course course)
	{
		courseList.add(course);
	}

	public String getName()
	{
		return name;
	}

	public Address getAddress()
	{
		return address;
	}

	public int getAccountNumber()
	{
		return accountNumber;
	}
	
	public CustomerType getCType()
	{
		return cType;
	}
	
	public String getCourseList()
	{
		String message = "";

		for(Course c:courseList)
		{
			if(c != null)
				message += c.toString() + "\n";
		}
		
		return(message);
	}

	public String toString()
	{
		return("Name " + name + " Address " + address + " \nAccount Number " + accountNumber + " Customer Type " + cType + "\n" + getCourseList());
	}
	
	
	public String createInvoice() throws CustomerNotFoundException
	{		
		double totalCost = 0.0;
		double baseCost = 0.0;

		if(courseList.size()==0)
			throw new CustomerNotFoundException(name);
			
		if(cType == CustomerType.STUDENT)
			baseCost = 25.00;
			
		if(cType == CustomerType.FACULTY)
			baseCost = 50.00;
			
		if(cType == CustomerType.GOVERNMENT)
			baseCost = 35.00;
			
		for(Course c:courseList)
			totalCost += c.calculateCharge(cType);
		
		totalCost += baseCost;

		return(String.format("%-20s%-20d$ %-20.2f%n", name, accountNumber, totalCost));
	}

}