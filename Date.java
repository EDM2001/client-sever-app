/**
 * Assignment:	Homework 5
 * Class:	Insy 4305 Sec. 001
 * Name:	Michael Rodriguez
 * ID:		1000408768
 * Comments:	
 */
import java.io.*;

public class Date implements Serializable
{
	private int month;
	private int day;
	private int year;

	public Date()
	{
		setMonth(0);
		setDay(0);
		setYear(0);
	}

	public Date(int month, int day, int year)
	{
		setMonth(month);
		setDay(day);
		setYear(year);
	}

	public void setMonth(int month)
	{
		this.month = month;
	}

	public void setDay(int day)
	{
		this.day = day;
	}

	public void setYear(int year)
	{
		this.year = year;
	}
 
	public int getMonth()
	{
		return month;
	}

	public int getDay()
	{
		return day;
	}

	public int getYear()
	{
		return year;
	}

	public String toString()
	{
		return (" Month " + month + " Day " + day + " Year " + year);
	}
}