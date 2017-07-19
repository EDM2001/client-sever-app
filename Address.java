/**
 * Assignment:	Homework 5
 * Class:	Insy 4305 Sec. 001
 * Name:	Michael Rodriguez
 * ID:		1000408768
 * Comments:	
 */
import java.io.*;

public class Address implements Serializable
{
	private String street;
	private String city;
	private String state;
	private int zip;

	public Address()
	{
		setStreet("");
		setCity("");
		setState("");
		setZip(0);
	}

	public Address(String street, String city, String state, int zip)
	{
		setStreet(street);
		setCity(city);
		setState(state);
		setZip(zip);
	}

	public void setStreet(String street)
	{
		this.street = street;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public void setZip(int zip)
	{
		this.zip = zip;
	}
	
	public String getStreet()
	{
		return street;
	}

	public String getCity()
	{
		return city;
	}
	
	public String getState()
	{
		return state;
	}
	
	public int getZip()
	{
		return zip;
	}

	public String toString()
	{
		return (" Street " + street + " City " + city + " State " + state + " Zip " + zip );
	}
}