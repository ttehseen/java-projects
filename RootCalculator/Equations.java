package model;


/**
 * @author Taha
 *Abstract class Equations that holds 
 *all the methods that will be inherited by 
 *the subclasses.
 */
public abstract class Equations {
	private double lowerBound, upperBound;
	private Double[] intervals;
	
	
	/**Setter:
	 * This sets the upper and lower bounds of the equation.
	 * @param _upperBound
	 * @param _lowerBound
	 */
	public void setBounds(double _lowerBound, double _upperBound)
	{
		this.upperBound = _upperBound;
		this.lowerBound = _lowerBound;
	}

	
	
	/**
	 * Generates an Array of 200 random values between the interval which can be used for future use.
	 * @param _lowerBound
	 * @param _intervalSize
	 */
	public void setIntervals(double _lowerBound, double _intervalSize)
	{
		
		this.intervals = new Double[200];
		     		
		for(int i = 0; i < 200; i++) {

				this.intervals[i] = (double)(Math.random()*(upperBound-lowerBound)  + lowerBound);}


	}
	
	/**
	 * Gets the lower Bound of the  interval.
	 * @return 
	 */
	public double getlowerBound()
	{
		return this.lowerBound;
	}
	
	/**
	 * Gets the lower Bound of the  interval.
	 * @return
	 */
	public double getupperBound()
	{
		return this.upperBound;
	}
	
	
	/**
	 * Returns the 200 x-values generated in an Array
	 * @return an Array containing the 200 x points.
	 */
	public Double[] getIntervals()
	{
		return this.intervals;
	}
	
	
	/**
	 * Abstract method to get the roots of the equations. Specific implementations differ.
	 */
	public abstract void getRoots();
	
	/**
	 * Abstract method that calculates 200 solutions of the equation within the limit. Specific implementations differ. 
	 */
	public abstract void computeIntervals();
	
	/**
	 * Abstract method that prints the 200 solutions.
	 */
	public abstract void printIntervals();
}
