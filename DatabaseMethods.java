/*
 * Assignment:		Homework 5
 * Class: 		Insy 4305 Sec. 001
 * Name:		Michael Rodriguez
 * ID:			1000408768
 * Comments:
 */

import java.sql.*;
import java.util.ArrayList;

public class DatabaseMethods
{
	
	private static final String DATABASE_URL=  "jdbc:mysql://localhost/courses";
	private static final String USER_NAME = "cdavis";
	private static final String PASSWORD = "fall2013";
	private ArrayList<Customer> customerList;
	private ArrayList<Course> courseList;
	private Customer customer;
	private OnlineCourse onlineCourse;
	private InClassCourse inClassCourse;
	private Connection connection = null;
	private Statement statement = null;
	private PreparedStatement insertCustomer = null;
	private PreparedStatement insertCourse = null;
	private ResultSet resultSet = null;
	private int res;
	
	public ArrayList<Course> getCourseArrayList()
	{
		courseList = new ArrayList<Course>();
		
		try
		{
			connection = DriverManager.getConnection(DATABASE_URL, USER_NAME, PASSWORD);
			
			statement = connection.createStatement();
			
			resultSet = statement.executeQuery("SELECT * FROM Course");
			
			while(resultSet.next())
			{
				if(resultSet.getString("CourseDelivery").equals("Online"))
				{
					courseList.add(onlineCourse = new OnlineCourse(resultSet.getString("CourseTitle"),resultSet.getString("Instructor"),resultSet.getFloat("Price"),parseDate(resultSet.getString("StartDate")),parseDate(resultSet.getString("EndDate")),resultSet.getString("examProtor"),resultSet.getBoolean("Video"), resultSet.getInt("Chapters")));
					onlineCourse.setCType(Course.CourseType.valueOf(resultSet.getString("CourseType")));
				}
				else if(resultSet.getString("CourseDelivery").equals("Inclass"))
				{
					courseList.add(inClassCourse = new InClassCourse(resultSet.getString("CourseTitle"),resultSet.getString("Instructor"),resultSet.getFloat("Price"),parseDate(resultSet.getString("StartDate")),parseDate(resultSet.getString("EndDate")),resultSet.getString("Room"),parseTime(resultSet.getString("StartTime")), parseTime(resultSet.getString("EndTime"))));		
					inClassCourse.setCType(Course.CourseType.valueOf(resultSet.getString("CourseType")));
				}
			}
			
		}
		catch(SQLException sqle)
		{
			sqle.printStackTrace();
		}
		finally
		{
			try
			{
				resultSet.close();
				statement.close();
				connection.close();
			}
			
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return courseList;
			
	}
	
	public ArrayList<Customer> getCustomerArrayList()
	{
		customerList = new ArrayList<Customer>();
		Course courseDelivery = new Course();
		boolean found = false;
		
		try
		{
			connection = DriverManager.getConnection(DATABASE_URL, USER_NAME, PASSWORD);
			
			statement = connection.createStatement();
			
			resultSet = statement.executeQuery("SELECT CC.CustomerName, Address, AccountNumber, CustomerType, CourseDelivery, CC.CourseTitle, Instructor, Price, CourseType, StartDate, EndDate, ExamProtor, Video, Chapters, Room, StartTime, EndTime "
					+ "FROM Customer_Courses CC "
					+ "INNER JOIN Course CR ON CC.CourseTitle = CR.CourseTitle "
					+ "INNER JOIN Customer C ON CC.CustomerName = C.CustomerName "
					+ "ORDER BY CC.CustomerName");
			
			while(resultSet.next())
			{
					customer = new Customer(resultSet.getString("CC.CustomerName"), parseAddress(resultSet.getString("Address")), resultSet.getInt("AccountNumber"));
					customer.setCType(Customer.CustomerType.valueOf(resultSet.getString("CustomerType")));
					if(resultSet.getString("CourseDelivery").equals("Online"))
					{
						onlineCourse = new OnlineCourse(resultSet.getString("CC.CourseTitle"),resultSet.getString("Instructor"),resultSet.getFloat("Price"),parseDate(resultSet.getString("StartDate")),parseDate(resultSet.getString("EndDate")),resultSet.getString("examProtor"),resultSet.getBoolean("Video"), resultSet.getInt("Chapters"));
						onlineCourse.setCType(Course.CourseType.valueOf(resultSet.getString("CourseType")));
						courseDelivery = (Course) onlineCourse;
					}
					else if(resultSet.getString("CourseDelivery").equals("Inclass"))
					{
						inClassCourse = new InClassCourse(resultSet.getString("CC.CourseTitle"),resultSet.getString("Instructor"),resultSet.getFloat("Price"),parseDate(resultSet.getString("StartDate")),parseDate(resultSet.getString("EndDate")),resultSet.getString("Room"),parseTime(resultSet.getString("StartTime")), parseTime(resultSet.getString("EndTime")));		
						inClassCourse.setCType(Course.CourseType.valueOf(resultSet.getString("CourseType")));
						courseDelivery = (Course) inClassCourse;
					}
					if(customerList.isEmpty())
					{
						customerList.add(customer);
						customer.addCourse(courseDelivery);
					}
					else
					{
						for(Customer c:customerList)
							if(c.getName().equals(customer.getName()))
							{
								c.addCourse(courseDelivery);
								found = true;
							}
								
						
						if(!found)
						{
							customerList.add(customer);
							customer.addCourse(courseDelivery);
						}
					}
					found=false;			
			}
			
		}
		catch(SQLException sqle)
		{
			sqle.printStackTrace();
		}
		finally
		{
			try
			{
				resultSet.close();
				statement.close();
				connection.close();
			}
			
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return customerList;
	}
	
	public String[] getCustomerNames()
	{
		String customerNames[];
		String sentence = " ;";
		
		try
		{
			connection = DriverManager.getConnection(DATABASE_URL, USER_NAME, PASSWORD);
			
			statement = connection.createStatement();
			
			resultSet = statement.executeQuery("SELECT CustomerName FROM Customer");
			
			
			while(resultSet.next())
			{
					sentence += resultSet.getString(1)+";";
			}
		}
		
		catch(SQLException sqle)
		{
			sqle.printStackTrace();
		}
		
		finally
		{
			try
			{
				resultSet.close();
				statement.close();
				connection.close();
			}
			
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		customerNames = sentence.split(";");
		return customerNames;
	}
	
	public String[] getCourseTitles()
	{
		String courseTitles[];
		String sentence = " ;";
		
		try
		{
			connection = DriverManager.getConnection(DATABASE_URL, USER_NAME, PASSWORD);
			
			statement = connection.createStatement();
			
			resultSet = statement.executeQuery("SELECT CourseTitle FROM Course");
			
			
			while(resultSet.next())
			{
				sentence += resultSet.getString(1)+";";
			}
			
		}
		
		catch(SQLException sqle)
		{
			sqle.printStackTrace();
		}
		
		finally
		{
			try
			{
				resultSet.close();
				statement.close();
				connection.close();
			}
			
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		courseTitles = sentence.split(";");
		return courseTitles;
	}
	
	public Time parseTime(String time)
	{
		String values[] = time.split(":");
		
		return (new Time(Integer.parseInt(values[0]), Integer.parseInt(values[1])));
	}
	
	public Date parseDate(String date)
	{
		String values[] = date.split("-");
		
		return (new Date(Integer.parseInt(values[1]), Integer.parseInt(values[2]), Integer.parseInt(values[0])));
	}
	
	public Address parseAddress(String address)
	{
		String values[] = address.split(" ");
		
		return (new Address(values[0],values[1],values[2],Integer.parseInt(values[3])));
	}
	
	public boolean customerExists(String name)
	{	
		boolean result = false;
		try
		{
			connection = DriverManager.getConnection(DATABASE_URL, USER_NAME, PASSWORD);
			
			statement = connection.createStatement();
			
			resultSet = statement.executeQuery("SELECT CustomerName FROM Customer");
			
			while(resultSet.next())
			{
				if(name.equals(resultSet.getString(1)))
					result = true;
			}
			
		}
		
		catch(SQLException sqle)
		{
			sqle.printStackTrace();
		}
		
		finally
		{
			try
			{
				resultSet.close();
				statement.close();
				connection.close();
			}
			
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public void addCustomer(String name, String address, int account, String cType)
	{
		try
		{
			connection = DriverManager.getConnection(DATABASE_URL, USER_NAME, PASSWORD);
			statement = connection.createStatement();
		
			insertCustomer = connection.prepareStatement("INSERT INTO Customer(CustomerName, Address, AccountNumber, CustomerType) VALUES (?,?,?,?)");
			
			insertCustomer.setString(1, name);
			insertCustomer.setString(2, address);
			insertCustomer.setInt(3, account);
			insertCustomer.setString(4, cType);
			res = insertCustomer.executeUpdate();
			
		}
		catch(SQLException sqle)
		{
			sqle.printStackTrace();
		}
		
		finally
		{
			try
			{
				resultSet.close();
				statement.close();
				connection.close();	
			}
			
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public void addCourseToCustomer(String name, String title)
	{
		try
		{
			connection = DriverManager.getConnection(DATABASE_URL, USER_NAME, PASSWORD);
			
			statement = connection.createStatement();
		
			insertCourse = connection.prepareStatement("INSERT INTO Customer_Courses(CustomerName, CourseTitle) VALUES (?,?)");
			
			insertCourse.setString(1, name);
			insertCourse.setString(2, title);
			
			res = insertCourse.executeUpdate();
			
		}
		catch(SQLException sqle)
		{
			sqle.printStackTrace();
		}
		
		finally
		{
			try
			{
				resultSet.close();
				statement.close();
				connection.close();	
			}
			
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public void writeCustomerTable()
	{
		try
		{
			connection = DriverManager.getConnection(DATABASE_URL, USER_NAME, PASSWORD);
			
			statement = connection.createStatement();
			
			resultSet = statement.executeQuery("SELECT * FROM Customer");
			
			ResultSetMetaData metaData = resultSet.getMetaData();
			
			int numberOfColumns = metaData.getColumnCount();
			
			System.out.println("Customer Table\n");
			
			for(int i = 1; i <= numberOfColumns; i++)
				System.out.printf("%-35s\t", metaData.getColumnName(i));
			System.out.println();

			
			while(resultSet.next())
			{
				for(int i = 1; i <= numberOfColumns; i++)
				{
					System.out.printf("%-35s\t", resultSet.getObject(i));
				}
				System.out.println();
			}
			System.out.println();
		}
		
		catch(SQLException sqle)
		{
			sqle.printStackTrace();
		}
		
		finally
		{
			try
			{
				resultSet.close();
				statement.close();
				connection.close();
			}
			
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public void writeCustomerCoursesTable()
	{
		try
		{
			connection = DriverManager.getConnection(DATABASE_URL, USER_NAME, PASSWORD);
			
			statement = connection.createStatement();
			
			resultSet = statement.executeQuery("SELECT CC.CustomerName, "
					+ "AccountNumber, "
					+ "CustomerType, "
					+ "CC.CourseTitle, "
					+ "CourseType "
					+ "FROM Customer_Courses CC "
					+ "INNER JOIN Course CR "
					+ "ON CC.CourseTitle=CR.CourseTitle "
					+ "INNER JOIN Customer C "
					+ "ON CC.CustomerName=C.CustomerName "
					+ "ORDER BY CC.CustomerName");
			
			ResultSetMetaData metaData = resultSet.getMetaData();
			
			int numberOfColumns = metaData.getColumnCount();
			
			System.out.println("Customer Courses Table\n");
			
			for(int i = 1; i <= numberOfColumns; i++)
				System.out.printf("%-8s\t", metaData.getColumnName(i));
			
			System.out.println();
			
			while(resultSet.next())
			{
				for(int i = 1; i <= numberOfColumns; i++)
				{
					System.out.printf("%-8s\t", resultSet.getObject(i));
				}
				System.out.println();
			}
			System.out.println();
		}
		
		catch(SQLException sqle)
		{
			sqle.printStackTrace();
		}
		
		finally
		{
			try
			{
				resultSet.close();
				statement.close();
				connection.close();
			}
			
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public void writeCourseTable()
	{		
		try
		{
			connection = DriverManager.getConnection(DATABASE_URL, USER_NAME, PASSWORD);
			
			statement = connection.createStatement();
			
			resultSet = statement.executeQuery("SELECT * FROM Course");
			
			ResultSetMetaData metaData = resultSet.getMetaData();
			
			int numberOfColumns = metaData.getColumnCount();
			
			System.out.println("Course Table\n");
			
			for(int i = 1; i <= numberOfColumns; i++)
				System.out.printf("%-8s\t", metaData.getColumnName(i));
			System.out.println();

			
			while(resultSet.next())
			{
				for(int i = 1; i <= numberOfColumns; i++)
				{
					System.out.printf("%-8s\t", resultSet.getObject(i));
				}
				System.out.println();
			}
			System.out.println();
		}
		
		catch(SQLException sqle)
		{
			sqle.printStackTrace();
		}
		
		finally
		{
			try
			{
				resultSet.close();
				statement.close();
				connection.close();
			}
			
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
}