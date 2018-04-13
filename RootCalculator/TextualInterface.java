package userinterface;
import exceptions.*;
import model.*;
import java.util.Scanner;
import java.util.regex.Pattern;


/**
 * @author Taha
 *The fields are to store values. In specific, var1 and var2 store the two interval bounds. theequation will be used to instantiate
 *Equations so that we can call relevant methods from within this class.
 */
public class TextualInterface {
	private double var1, var2;
	private Equations theequation;

	/**
	 * The interactive Textual Interface that takes in user input, parses it and then stores it. The outermost while loop 
	 * keeps the program running until it it shut down by the user itself. The inner while loops also keep prompting the user for input
	 * if incorrect input is entered for example, a wrong equation or invalid choice.
	 */
	/**
	 * 
	 */
	public TextualInterface() {
		while (true) 
		{
			while (true) 
			{
				try {
					Scanner inputa = new Scanner(System.in);
					System.out.println(
							"Please input your equation.");
					String equationinput = inputa.nextLine();
					equationinput = equationinput.replaceAll(" ", ""); 
																
					this.expressionCheck(equationinput);
					System.out.println("You entered the equation: " + equationinput
							+ ". Please specify an interval as follows: [x1;x2]:");
					
					break;
				} catch (InvalidExpressionException ex) {
					System.err.print(ex);
				}
				
			}

			while (true) 
			{
				try {
					Scanner inputb = new Scanner(System.in);
					String intervalinput = inputb.nextLine();
					intervalinput = intervalinput.replace("[", "");
					intervalinput = intervalinput.replace("]", "");
					String breaker = "[;]+"; 
					String[] interval = intervalinput.split(breaker);
					this.var1 = Double.parseDouble(interval[0]);
					this.var2 = Double.parseDouble(interval[1]);
					if (this.var1 >= this.var2) {
						throw new InvalidIntervalException("x2 must be greater than x1. Try again:\n");
					} 

					
					this.theequation.setBounds(var1, var2);
					System.out.println(
							"Calculating the values of 200 points in the interval " + "[" + var1
									+ ";" + var2 + "]. Choose the next option:");
					
					break;
				} catch (InvalidIntervalException ex){
					System.err.print(ex);
				}
				catch (NumberFormatException ex ) {
					System.err.print("Please reinput the interval in the correct format i.e [x1;x2]\n");
				}
				catch (ArrayIndexOutOfBoundsException ex ) {
					System.err.print("Please reinput the interval in the correct format i.e [x1;x2]\n");
				}
				
				}
			

			while (true) 
			{
				try {
					Scanner inputc = new Scanner(System.in);
					System.out.println(
							" a. Solve\n" + " b. Print values\n" + " c. Input new equation\n" + " d. Exit");
					String choiceinput = inputc.nextLine().toLowerCase();
					if (choiceinput.equals("a") ) {
						this.theequation.getRoots();
					} else if (choiceinput.equals("b"))  {
						this.theequation.printIntervals();
					} else if (choiceinput.equals("d"))  {
						System.out.println("Program is shutting down.");
						System.exit(0);
					} else if (choiceinput.equals("c") ) {
						
						break;
					} else { throw new InvalidChoiceException("Invalid choice. Please choose an option from a to d. \n");
					} 
				} catch (InvalidChoiceException ex) {
					System.err.print(ex);
				}

			}
		}

	}

	
	
	/**
	 * Parses input strong to match it to an equation, and then proceeds to create an instance of that equation. If the input is incorrect,
	 * it throws an expression and asks for the user to re-input
	 * @param expression
	 * @throws InvalidExpressionException
	 */
	public void expressionCheck(String expression) throws InvalidExpressionException {

		if (Pattern.compile("[Yy]=([+-]?\\d*\\.?\\d*)[Xx]([+-]?\\d*\\.?\\d*)").matcher(expression).matches()){
			this.theequation = new Linear(expression);
		} else if (Pattern.compile("[Yy]=([+-]?\\d*\\.?\\d*)[Xx]\\^2([+-]?\\d*\\.?\\d*)[Xx]([+-]?\\d*\\.?\\d*)").matcher(expression).matches()) {
			this.theequation = new Quadratic(expression);
		} else if (Pattern.compile("[Yy]=([+-]?\\d*\\.?\\d*)[*]?log_2\\([Xx]\\)([+-]?\\d*\\.?\\d*)").matcher(expression).matches()) {
			this.theequation = new Logarithmic(expression);
		} else if (Pattern.compile("[Yy]=([+-]?\\d*\\.?\\d*)[*]?e\\^[Xx]").matcher(expression).matches()) {
			this.theequation = new Exponential(expression);
		} else if (Pattern.compile("[Yy]=([+-]?\\d*\\.?\\d*)[*]?sin\\([Xx]\\)").matcher(expression).matches()) {
			this.theequation = new Sine(expression);
		} else  {
			throw new InvalidExpressionException("\n Wrong input equation. Make sure your equation is in the following format: \n y=ax+b \n y=ax^2+bx+c \n y=a*log_2(x)+b \n y=a*e^x \n y=a*sin(x)"+" \n Also please make sure that you include any variables that have a zero coefficient. For ex please input y=x^2-0x-2 instead of y=x^2-2. \nPlease try again:\n");
		}
	}


	
}
