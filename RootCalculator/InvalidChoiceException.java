package exceptions;

/**Exception for when the parser identifies a wrong Choice input.
 * Thus, if someone inputs a letter that is not a,b,c or d or any
 * other character, this exception is thrown.
 * @author Taha
 *
 */
public class InvalidChoiceException extends Exception{
	
	public InvalidChoiceException(String string)
	{
		super(string);
	}
}
