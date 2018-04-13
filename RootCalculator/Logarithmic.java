package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Class that deals with parsing and calculations of Log functions. Implements methods from the Equations abstract class.
 * @author Taha
 *
 */
public class Logarithmic extends Equations{
    private static final Pattern EQN = Pattern.compile("[Yy]=([+-]?\\d*\\.?\\d*)[*]?log_2\\([Xx]\\)([+-]?\\d*\\.?\\d*)");
    private double a;
    private double b;
    protected Double[] xints, yints;

    /**
     * Parses a string input to extract variable values and uses that to instantiate a linear equation.
     * The quadratic equation has to be of the format: "y=a*log_2(x)+b". If there are any zero-valued varaibles, they have to be included.
     * @param The userinput that is expected to be a mathematical equation.
     */
    public Logarithmic(String equation) 
    {
        final Matcher matcher = EQN.matcher(equation);
        if (!matcher.matches())
        {
            throw new IllegalArgumentException("Not a valid pattern " + equation);
        }
        if (matcher.group(1).equals(""))
        {
        		this.a = 1;
        }
        else if (matcher.group(1).equals("-"))
        {
        		this.a = -1;
        }
        else
        {
        		this.a = Double.parseDouble(matcher.group(1));
        }
        if (matcher.group(2).equals(""))
        {
        		this.b = 0;
        }
        else
        {
        		this.b = Double.parseDouble(matcher.group(2));
        }
    }
    

    /**
     * Provides a toString representation of the equation.
     * @return 
     */
    @Override
    public String toString() 
    {
    		return "Logarithmic function: y=a*log_2(x)+b with a = " + this.a
    				+ " and b = " + this.b + ".";
    }

    /**
     * Method to calculates the roots of the log equation.
     */
	@Override
	public void getRoots() {
		System.out.println("The root is: x= " + Math.pow(2, (-this.b/this.a)) + ".");
	}

	/**
	 * Prints 200 solutions to the equation in the format "(x=***, y=***)".
	 */
	@Override
	public void printIntervals() {
		this.computeIntervals();
		for (int i =0; i < this.getIntervals().length; i++)
		{
			System.out.println("(x=" + xints[i] + ", " + "y=" + yints[i] + ")");
		}
	}


	/**
	 * Calculates the y-values for each of the 200 x-values in setInterval. 
	 */
	@Override
	public void computeIntervals() 
	{
		this.setIntervals(this.getlowerBound(), this.getupperBound());
		this.xints = this.getIntervals();
		this.yints = new Double[200];
		for (int i =0; i < 200; i++)
		{
			this.yints[i]= this.a * (Math.log(this.xints[i]) / Math.log(2)) + this.b;
		}
		
	}

}
