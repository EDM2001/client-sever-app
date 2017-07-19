/**
 * Assignment:	Homework 5
 * Class:	Insy 4305 Sec. 001
 * Name:	Michael Rodriguez
 * ID:		1000408768
 * Comments:	
 */
import java.io.*;

public class OnlineCourse extends Course implements Serializable
{
	private String examProtor;
	private boolean video;
	private int numberOfChapters;
	
	public OnlineCourse()
	{
		super();
		setExamProctor("");
		setVideo(true);
		setNumberOfChapters(0);
	}
	
	public OnlineCourse(String title, String instructor, double price, Date startDate, Date endDate, String examProtor, boolean video, int numberOfChapters)
	{
		super(title, instructor, price, startDate, endDate);
		setExamProctor(examProtor);
		setVideo (video);
		setNumberOfChapters (numberOfChapters);
	}
	
	public void setExamProctor(String examProtor)
	{
		this.examProtor = examProtor;
	}
	
	public void setVideo(boolean video)
	{
		this.video = video;
	}
	
	public void setNumberOfChapters(int numberOfChapters)
	{
		this.numberOfChapters = numberOfChapters;
	}
	
	public String getExamProctor()
	{
		return examProtor;
	}
	
	public boolean getVideo()
	{
		return video;
	}
	
	public int getNumberOfChapters()
	{
		return numberOfChapters;
	}
	
	public double calculateCharge(Customer.CustomerType c)
	{	
		if(c == Customer.CustomerType.STUDENT)
		{
			setPrice(super.calculateCharge(c) + 20.00);
		}
		
		if(c == Customer.CustomerType.FACULTY)
		{
			setPrice(super.calculateCharge(c) + 25.00);
		}
		
		if(c == Customer.CustomerType.GOVERNMENT)
		{
			setPrice(super.calculateCharge(c) + 0.00);
		}
		
		return getPrice();
	}
	
	public String toString()
	{
		return (super.toString() + " \nExam Proctor " + examProtor + " Video " + video + " Number of Chapters " + numberOfChapters);
	}
	
}