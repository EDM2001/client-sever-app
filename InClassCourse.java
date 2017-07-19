/**
 * Assignment:	Homework 5
 * Class:	Insy 4305 Sec. 001
 * Name:	Michael Rodriguez
 * ID:		1000408768
 * Comments:	
 */
import java.io.*;

public class InClassCourse extends Course implements Serializable
{
	private String room;
	private Time startTime;
	private Time endTime;
	
	public InClassCourse()
	{
		super();
		setRoom("");
		setStartTime(new Time());
		setEndTime(new Time());
	}
	
	public InClassCourse(String title, String instructor, double price, Date startDate, Date endDate, String room, Time startTime, Time endTime)
	{
		super(title, instructor, price, startDate, endDate);
		setRoom(room);
		setStartTime(startTime);
		setEndTime(endTime);
	}
	
	public void setRoom(String room)
	{
		this.room = room;
	}
	
	public void setStartTime(Time startTime)
	{
		this.startTime = startTime;
	}
	
	public void setEndTime(Time endTime)
	{
		this.endTime = endTime;
	}
	
	public String getRoom()
	{
		return room;
	}
	
	public Time getStartTime()
	{
		return startTime;
	}
	
	public Time getEndTime()
	{
		return endTime;
	}
	
	public double calculateCharge(Customer.CustomerType c)
	{	
		
		if(c == Customer.CustomerType.STUDENT)
		{
			setPrice(super.calculateCharge(c) + 0.00);			
		}
		
		if(c == Customer.CustomerType.FACULTY)
		{
			setPrice(super.calculateCharge(c) + 5.00);
		}
		
		if(c == Customer.CustomerType.GOVERNMENT)
		{
			setPrice(super.calculateCharge(c) + 0.00);
		}
		
		return getPrice();
	}
	
	public String toString()
	{
		return (super.toString() + " \nRoom " + room + " Start Time " + startTime + " End Time " + endTime);
	}
	
}