package model;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Class that deals with parsing and calculations of Exponential functions. Implements methods from the Equations abstract class.
 * @author Taha
 *
 */
public class Exponential extends Equations{
    private static final Pattern EQN = Pattern.compile("[Yy]=([+-]?\\d*\\.?\\d*)[*]?e\\^[Xx]");
    private double a;
    protected Double[] xints, yints;
    
    /**
     * Parses a string input to extract variable values and uses that to instantiate an exponential equation.
     * The quadratic equation has to be of the format: "y=ax+b". If there are any zero-valued varaibles, they have to be included.
     * @param The userinput that is expected to be a mathematical equation.
     */
    public Exponential(String eq) 
    {
        final Matcher matcher = EQN.matcher(eq);
        if (!matcher.matches())
        {
            throw new IllegalArgumentException("Not a valid pattern " + eq);
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
    }
    
    /**
     * Provides a toString representation of the equation.
     * @return 
     */
    @Override
    public String toString() 
    {
    		return "An exponential function: y=a*e^x where a = " + this.a + ".";
    }

    /**
     * Method to approximate the root of the exponential equation.
     * We find out the (x,y) point within our interval where y is 
     * closest to zero.
     */
	
	@Override
	public void getRoots() {
		this.computeIntervals();
		Double[] sortedYArray = new Double[200];
		System.arraycopy( this.yints, 0, sortedYArray, 0, 200 );
		Arrays.sort(sortedYArray);
		double lowest = Math.abs(sortedYArray[0]);
		int index= Arrays.asList(yints).indexOf(lowest);
		for (int i = 0; i < 200; i++) 
		{
			if (Math.abs(this.yints[i]) < lowest)
			{
				lowest = Math.abs(this.yints[i]);
				index = i;
			}
		}
		System.out.println("Approximate solution is x= " + this.xints[index] + ", y= " + this.yints[index] + ".");
		
	}
	
	/**
	 * Prints 200 pairs of values between the intervals specified for the function during its declaration in the format "(x, y)".
	 */
	@Override
	public void printIntervals() 
	{
		this.computeIntervals();
		for (int i =0; i < this.getIntervals().length; i++)
		{
			System.out.println("(x=" + xints[i] + ", " + "y=" + yints[i] + ")");
		}
		
	}
	
	/**
	 * Calculates the y-values for each of the 200 x-values generated in setintervals.
	 */
	@Override
	public void computeIntervals() 
	{
		this.setIntervals(this.getlowerBound(), this.getupperBound());

		this.xints = this.getIntervals();
		this.yints = new Double[200];
		for (int i =0; i < xints.length; i++)
		{
			this.yints[i] = this.a * Math.exp(xints[i]);
		}
		
	}


}
