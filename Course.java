/**
 * Assignment:	Homework 5
 * Class:	Insy 4305 Sec. 001
 * Name:	Michael Rodriguez
 * ID:		1000408768
 * Comments:	
 */
import java.io.*;

public class Course implements Serializable
{
	private String title;
	private String instructor;
	private double price;
	public enum CourseType {PROGRAMMING, MATHEMATICS, PHOTOGRAPHY, MUSIC, PAINTING, MISC};
	private CourseType cType = CourseType.MISC;
	private Date startDate;
	private Date endDate;
	
	public Course()
	{
		setTitle("");
		setInstructor("");
		setPrice(0.0);
		setStartDate(new Date());
		setEndDate(new Date());
	}
	
	public Course(String title, String instructor, double price, Date startDate, Date endDate)
	{
		setTitle(title);
		setInstructor(instructor);
		setPrice(price);
		setStartDate(startDate);
		setEndDate(endDate);
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public void setInstructor(String instructor)
	{
		this.instructor = instructor;
	}

	public void setPrice(double price)
	{
		this.price = price;
	}

	public void setCType(CourseType cType)
	{
		this.cType = cType;
	}
	
	public void setStartDate(Date startDate)
	{
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}

	public String getTitle()
	{
		return title;
	}

	public String getInstructor()
	{
		return instructor;
	}

	public double getPrice()
	{
		return price;
	}

	public CourseType getCType()
	{
		return cType;
	}

	public Date getStartDate()
	{
		return startDate;
	}
	
	public Date getEndDate()
	{
		return endDate;
	}

	public double calculateCharge(Customer.CustomerType c)
	{	
		if((cType == Course.CourseType.PROGRAMMING)||(cType == Course.CourseType.MATHEMATICS))
		{
			price += 99.00;
		}
		
		if((cType == Course.CourseType.PHOTOGRAPHY)||(cType == Course.CourseType.MUSIC)||(cType == Course.CourseType.PAINTING))
		{
			price += 59.00;
		}
		
		if(cType == Course.CourseType.MISC)
		{
			price += 39.00;
		}
		
		return price;
	}
	
	public String toString()
	{
		return("Title " + title + " Price " + price + " \nStart Date " + startDate + " Instructor " + instructor + " \nEnd Date " + endDate + " Course Type " + cType);
	}

}	
	