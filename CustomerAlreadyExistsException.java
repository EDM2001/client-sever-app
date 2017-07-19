/**
 * Assignment:	Homework 5
 * Class:	Insy 4305 Sec. 001
 * Name:	Michael Rodriguez
 * ID:		1000408768
 * Comments:	
 */
public class CustomerAlreadyExistsException extends RuntimeException
{
	public CustomerAlreadyExistsException()
	{	
		super();
	}
	
	public CustomerAlreadyExistsException(String message)
	{
		super(message);
	}
	
	public CustomerAlreadyExistsException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
	public CustomerAlreadyExistsException(Throwable cause)
	{
		super(cause);
	}
	
}