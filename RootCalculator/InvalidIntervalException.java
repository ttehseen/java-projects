package exceptions;
/**Exception for when the parser identifies a wrong interval input.
 * Thus, if someone inputs an interval that is not according to the
 * format provided, this exception is thrown.
 * @author Taha
 *
 */
public class InvalidIntervalException extends Exception{
	
	public InvalidIntervalException(String string)
	{
		super(string);
	}

}
