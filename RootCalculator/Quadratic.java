package model;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class that deals with parsing and calculations of Quadratic functions. Implements methods from the Equations abstract class.
 * @author Taha
 */
public class Quadratic extends Equations{
    private static final Pattern EQN = Pattern.compile("[Yy]=([+-]?\\d*\\.?\\d*)[Xx]\\^2([+-]?\\d*\\.?\\d*)[Xx]([+-]?\\d*\\.?\\d*)");
    private double a, b, c;
    protected Double[] xints, yints;
    
    /**
     * Parses a string input to extract variable values and uses that to instantiate a quadratic equation.
     * The quadratic equation has to be of the format: "y=ax^2+bx+c". If there are any zero-valued varaibles, they have to be included.
     * @param The userinput that is expected to be a mathematical equation.
     */
    public Quadratic (String eq) 
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
        if (matcher.group(2).equals(""))
        {
        		this.b = 1;
        }
        else if (matcher.group(2).equals("-"))
        {
        		this.b = -1;
        }
        else
        {
        		this.b = Double.parseDouble(matcher.group(2));
        }
        if (matcher.group(3).equals(""))
        {
        		this.c = 0;
        }
        else
        {
        		this.c = Double.parseDouble(matcher.group(3));
        }
    }
    
    

    /**
     * Provides a toString representation of the equation.
     * @return 
     */
    @Override
    public String toString() 
    {
    		return "A quadratic function: y=ax+b where a = " + this.a
    				+ " b = " + this.b + " c = " + this.c + ".";
    }

    /**
     * Method to calculate the roots of the equation. Uses determinants to determine the nature of the roots and then proceeds with
     * separate ways to get them.
     */
	@Override
	public void getRoots() 
	{
		double discriminant = (this.b * this.b) - (4 * this.a * this.c);
		if (discriminant > 0)
		{
			System.out.println("I found two solutions: x= " + (-this.b - Math.sqrt(discriminant))/(2 * this.a) 
								+ "and x= " + (-this.b + Math.sqrt(discriminant))/(2 * this.a) + ".");
		}
		else if (discriminant == 0)
		{
			System.out.println("I found a solution: x= " + (-this.b/(2 * this.a)) + ".");
		}
		else if (discriminant < 0) 
		{
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
			System.out.println("I have found an approximation: x= " + 
					this.xints[index] + " where y= " + 
					this.yints[index] + ".");
		}	
	}

	/**
	 * Prints 200 solutions to the equation in the format "(x=***, y=***)".
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
	 * Calculates the y-values for each of the 200 x-values in setInterval. 
	 */
	@Override
	public void computeIntervals() 
	{
		this.setIntervals(this.getlowerBound(), this.getupperBound());
		this.xints = this.getIntervals();
		this.yints = new Double[200];
		for (int i =0; i < xints.length; i++)
		{
			this.yints[i]= this.a * xints[i]*xints[i] + this.b * xints[i]+ this.c;
		}
		
	}
	
	

}
