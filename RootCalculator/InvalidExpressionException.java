package exceptions;

/**Exception for when the parser identifies a wrong Equation expression input.
 * Thus, if someone inputs an expression that is not readable
 * this exception is thrown.
 * @author Taha
 *
 */
public class InvalidExpressionException extends Exception{
	
	public InvalidExpressionException(String string)
	{
		super(string);
	}

}
