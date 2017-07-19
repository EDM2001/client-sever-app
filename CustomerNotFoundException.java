/**
 * Assignment:	Homework 5
 * Class:	Insy 4305 Sec. 001
 * Name:	Michael Rodriguez
 * ID:		1000408768
 * Comments:	
 */
public class CustomerNotFoundException extends RuntimeException
{
	public CustomerNotFoundException()
	{	
		super();
	}
	
	public CustomerNotFoundException(String message)
	{
		super(message);
	}
	
	public CustomerNotFoundException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
	public CustomerNotFoundException(Throwable cause)
	{
		super(cause);
	}
	
}