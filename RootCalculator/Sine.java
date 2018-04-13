package model;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Class that deals with parsing and calculations of Sine functions. Implements methods from the Equations abstract class.
 * @author Taha
 *
 */
public class Sine extends Equations{
    private static final Pattern EQN = Pattern.compile("[Yy]=([+-]?\\d*\\.?\\d*)[*]?sin\\([Xx]\\)");
    private double a;
    protected Double[] xints, yints;
    
    /**
     * Parses a string input to extract variable values and uses that to instantiate a linear equation.
     * The quadratic equation has to be of the format: "y=a*sin(x)". If there are any zero-valued varaibles, they have to be included.
     * @param The userinput that is expected to be a mathematical equation.
     */
    public Sine(String eq) 
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
    		return "A sinusoidal function: y=ax+b with a = " + this.a;
    }

    /**
     * Method to calculates the roots of the sine equation.
     * All equations of type y=a*sin(x) will have the same roots
     * (multiples of Math.PI) for the same interval. 
     * Thus, this method takes in the the two bounds and finds 
     * all multiples of pi within those bounds. If there are none
     * it finds the closest approximation using the stored 200
     * values.
     */
  
	@Override
	public void getRoots() 
	{
		
			int lowerdigit = (int) (this.getlowerBound()/Math.PI)+1;
			int upperdigit = (int) (this.getupperBound()/Math.PI);
			if (lowerdigit <= upperdigit) {
				System.out.println("The roots are: \n");
			for (int i = lowerdigit; i < upperdigit + 1; i++)
			{
				System.out.print("(x=" + i*Math.PI +",y=0)\n");
			}
			}
			else {
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
				System.out.println("Here is an approximation: x= " + 
						this.xints[index] + ",y= " + 
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
			this.yints[i]= this.a * Math.sin(xints[i]);
		}
		
	}
	
	

}
