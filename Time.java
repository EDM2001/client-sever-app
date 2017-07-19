/**
 * Assignment:	Homework 5
 * Class:	Insy 4305 Sec. 001
 * Name:	Michael Rodriguez
 * ID:		1000408768
 * Comments:	
 */
import java.io.*;

public class Time implements Serializable
{
	private int hours;
	private int minutes;

	public Time()
	{
		setHours(0);
		setMinutes(0);
	}

	public Time(int hours, int minutes)
	{
		setHours(hours);
		setMinutes(minutes);
	}

	public void setHours(int hours)
	{  
        this.hours = hours;
	}

	public void setMinutes(int minutes)
	{
		this.minutes = minutes;
	}

	public int getHours()
	{
		return hours;
	}

	public int getMinutes()
	{
		return minutes;
	}
	
	public String toString()
	{
		return (" Hours " + hours + " :" + " Minutes " + minutes);
	}
	
}