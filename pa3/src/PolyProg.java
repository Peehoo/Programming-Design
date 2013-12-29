//Name: Peehoo Dewan
//USC loginid: pdewan@usc.edu
//CSCI 455 PA3
//Fall 2013

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * The PolyProg class lets a user build and do computations on several Poly
 * objects in the array polynomials
 * 
 * @author Peehoo
 * 
 */
public class PolyProg {
	private final static int CAPACITY = 10;
	private final static int MININDEX = 0;
	private final static int ADDIN_NUMBER_OF_ARGUMENTS = 2;
	private final static int ADD_NUMBER_OF_ARGUMENTS = 3;
	private final static int MULT_NUMBER_OF_ARGUMENTS = 3;
	private final static int COPY_NUMBER_OF_ARGUMENTS = 2;
	private final static int HELP_NUMBER_OF_ARGUMENTS = 0;
	private final static int QUIT_NUMBER_OF_ARGUMENTS = 0;
	private final static int CREATE_NUMBER_OF_ARGUMENTS = 1;
	private final static int PRINT_NUMBER_OF_ARGUMENTS = 1;
	private final static int EVAL_NUMBER_OF_ARGUMENTS = 1;
	private final static int ADDIN_POLY1 = 0;
	private final static int ADDIN_POLY2 = 1;
	private final static int ADD_POLY1 = 1;
	private final static int ADD_POLY2 = 2;
	private final static int ADD_RESULT = 0;
	private final static int MULT_POLY1 = 1;
	private final static int MULT_POLY2 = 2;
	private final static int MULT_RESULT = 0;
	private final static int COPY_POLY1 = 0;
	private final static int COPY_POLY2 = 1;
	private Poly[] polynomials;

	/**
	 * Creates a new PolyProg object
	 */
	public PolyProg() {
		polynomials = new Poly[CAPACITY];
		for (int i = 0; i < getPolynomials().length; i++) {
			getPolynomials()[i] = new Poly();
		}
	}

	/**
	 * main method that lets a user build and do computations on several Poly
	 * objects
	 * 
	 * @param args
	 *            not used
	 */
	public static void main(String[] args) {

		PolyProg polyProgConsole = new PolyProg();
		Scanner in = new Scanner(System.in);
		Poly[] polys = polyProgConsole.getPolynomials();

		while (true) {
			System.out.print("cmd>");
			String command = in.next();// Stores the command requested by the
										// user
			if (command.equalsIgnoreCase("create")) {
				createPoly(in, polys); // Creating a polynomial
			} else if (command.equalsIgnoreCase("print")) {
				printPoly(in, polys); // Prints a polynomial
			} else if (command.equalsIgnoreCase("eval")) {
				evaluatePoly(in, polys); // Evaluates a polynomial
			} else if (command.equalsIgnoreCase("addin")) {
				addInPoly(in, polys); // Adds another polynomial to the same
										// polynomial
			} else if (command.equalsIgnoreCase("add")) {
				addPoly(in, polys); // Adds one polynomial to other and stores
									// result in another polynomial
			} else if (command.equalsIgnoreCase("mult")) {
				multPoly(in, polys); // Multiplies two polynomials and stores
										// result in another polynomial
			} else if (command.equalsIgnoreCase("copy")) {
				copyPoly(in, polys); // Copies one polynomial into another
			} else if (command.equalsIgnoreCase("quit")) {
				try {
					getCommandLineArgs(in, PolyProg.QUIT_NUMBER_OF_ARGUMENTS);
				} catch (IllegalPolyIndexException e) {
					System.out.println("Ignoring Arguments for Quit Command");
				} catch (IllegalNumberOfArgumentsException e) {
					System.out.println("Ignoring Arguments for Quit Command");
				}
				break; // Quits the console
			} else if (command.equalsIgnoreCase("help")) {
				printHelp(in);
			} else {
				System.out
						.println("ERROR: Illegal command.  Type 'help' for command options.");
			}
		}
	}

	private static void printHelp(Scanner in) {
		try {
			getCommandLineArgs(in, PolyProg.HELP_NUMBER_OF_ARGUMENTS);
		} catch (IllegalPolyIndexException e) {
			System.out.println("Ignoring Arguments for Help Command");
		} catch (IllegalNumberOfArgumentsException e) {
			System.out.println("Ignoring Arguments for Help Command");
		}
		System.out
				.println("The List of Valid commands are: \n"
						+ "1) create n - This command creates a new polynomial at index 'n' (n is between 0-9) \n"
						+ "2) print n - This command prints the polynomial at index 'n' (n is between 0-9).\n"
						+ "   If there is no polynomial created at that index, it prints the zero polynomial which is 0.0 \n"
						+ "3) eval n - This command evaluates the polynomial at index 'n' (n is between 0-9) for a given value of x \n"
						+ "4) addin n m - This command adds polynomial at index 'm' to polynomial at index 'n' (n and m are between 0-9) \n"
						+ "5) add n m p - This command adds polynomials at inices 'p' and 'm' and stores the result in polynomial at index 'n' "
						+ "\n   (n, m and p are between 0-9) \n"
						+ "6) mult n m p - This command multiplies polynomials at inices 'p' and 'm' and stores the result in polynomial at index 'n'\n "
						+ "  (n, m and p are between 0-9) \n"
						+ "7) copy n m - This command copies the polynomial at index 'm' into polynomial at index 'n' \n"
						+ "8) quit - quits this program");
	}

	/**
	 * This method processes and returns command-line arguments
	 * 
	 * @param in
	 *            Scanner object for getting the inputs from the user
	 * @param numArgs
	 *            Number of Arguments required for a command
	 * @return Array of integers
	 * @throws IllegalPolyIndexException
	 * @throws IllegalNumberOfArgumentsException
	 */
	private static int[] getCommandLineArgs(Scanner in, int numArgs)
			throws IllegalPolyIndexException, IllegalNumberOfArgumentsException {
		String args = in.nextLine().trim();
		// Splits the nums string with any whitespace characters as delimiters
		String[] argsStringArray = args.split("\\s+"); 

		if (argsStringArray.length > numArgs) {
			System.out.println("ERROR : Too many Arguments");
			throw new IllegalNumberOfArgumentsException(numArgs
					+ " argument(s) expected");
		}
		if (argsStringArray.length < numArgs
				|| (numArgs == 1 && argsStringArray[0].trim().equals(""))) {
			System.out.println("ERROR : Too few Arguments");
			throw new IllegalNumberOfArgumentsException(numArgs
					+ " argument(s) expected");
		}

		int[] argsArray = new int[numArgs];
		for (int i = 0; i < numArgs; i++) {
			try {
				argsArray[i] = Integer.parseInt(argsStringArray[i]);
			} catch (NumberFormatException e) {
				throw new IllegalPolyIndexException("ERROR : Illegal index: "
						+ argsStringArray[i], argsStringArray[i]);
			}
			if (argsArray[i] >= PolyProg.CAPACITY
					|| argsArray[i] < PolyProg.MININDEX)
				throw new IllegalPolyIndexException(
						"ERROR : Illegal polynomial index: "
								+ argsStringArray[i], argsStringArray[i]);
		}
		return argsArray;
	}

	/**
	 * This method adds two polynomials, resulting in a new one that is the sum
	 * of the first two. The two original polynomials are unchanged.
	 * 
	 * @param in
	 *            Scanner object for getting the inputs from the user
	 * @param polys
	 *            An Array of polynomials in which the created polynomial gets
	 *            inserted
	 */
	private static void addPoly(Scanner in, Poly[] polys) {
		int[] polyArrayIndex = new int[PolyProg.ADD_NUMBER_OF_ARGUMENTS];
		try {
			polyArrayIndex = getCommandLineArgs(in,
					PolyProg.ADD_NUMBER_OF_ARGUMENTS);
		} catch (IllegalPolyIndexException e1) {
			System.out.println("ERROR : Illegal polynomial index: "
					+ e1.getIndex() + " \nExpecting a number between 0 and 9.");
			return;
		} catch (IllegalNumberOfArgumentsException e) {
			System.out.println(e.getMessage());
			return;
		}
		polys[polyArrayIndex[ADD_RESULT]] = polys[polyArrayIndex[PolyProg.ADD_POLY1]]
				.add(polys[polyArrayIndex[PolyProg.ADD_POLY2]]);
	}

	/**
	 * This method adds a polynomial(poly2) to an existing polynomial(poly1).
	 * poly2 is unchanged
	 * 
	 * @param in
	 *            Scanner object for getting the inputs from the user
	 * @param polys
	 *            An Array of polynomials in which the created polynomial gets
	 *            inserted
	 */
	private static void addInPoly(Scanner in, Poly[] polys) {
		int[] polyArrayIndex = new int[PolyProg.ADDIN_NUMBER_OF_ARGUMENTS];
		try {
			polyArrayIndex = getCommandLineArgs(in,
					PolyProg.ADDIN_NUMBER_OF_ARGUMENTS);
		} catch (IllegalPolyIndexException e1) {
			System.out.println("ERROR : Illegal polynomial index: "
					+ e1.getIndex() + " \nExpecting a number between 0 and 9.");
			return;
		} catch (IllegalNumberOfArgumentsException e) {
			System.out.println(e.getMessage());
			return;
		}
		polys[polyArrayIndex[PolyProg.ADDIN_POLY1]]
				.addIn(polys[polyArrayIndex[PolyProg.ADDIN_POLY2]]);
	}

	/**
	 * This method multiplies two polynomials, resulting in a new one that is
	 * the product of the first two. The two original polynomials are unchanged.
	 * 
	 * @param in
	 *            Scanner object for getting the inputs from the user
	 * @param polys
	 *            An Array of polynomials in which the created polynomial gets
	 *            inserted
	 */
	private static void multPoly(Scanner in, Poly[] polys) {
		int[] polyArrayIndex = new int[PolyProg.MULT_NUMBER_OF_ARGUMENTS];
		try {
			polyArrayIndex = getCommandLineArgs(in,
					PolyProg.MULT_NUMBER_OF_ARGUMENTS);
		} catch (IllegalPolyIndexException e1) {
			System.out.println("ERROR : Illegal polynomial index: "
					+ e1.getIndex() + " \nExpecting a number between 0 and 9.");
			return;
		} catch (IllegalNumberOfArgumentsException e) {
			System.out.println(e.getMessage());
			return;
		}
		polys[polyArrayIndex[MULT_RESULT]] = polys[polyArrayIndex[PolyProg.MULT_POLY1]]
				.mult(polys[polyArrayIndex[PolyProg.MULT_POLY2]]);
	}

	/**
	 * This method copies one polynomial into another.
	 * 
	 * @param in
	 *            Scanner object for getting the inputs from the user
	 * @param polys
	 *            An Array of polynomials in which the created polynomial gets
	 *            inserted
	 */
	private static void copyPoly(Scanner in, Poly[] polys) {
		int[] polyArrayIndex = new int[PolyProg.COPY_NUMBER_OF_ARGUMENTS];
		try {
			polyArrayIndex = getCommandLineArgs(in,
					PolyProg.COPY_NUMBER_OF_ARGUMENTS);
		} catch (IllegalPolyIndexException e1) {
			System.out.println("ERROR : Illegal polynomial index: "
					+ e1.getIndex() + " \nExpecting a number between 0 and 9.");
			return;
		} catch (IllegalNumberOfArgumentsException e) {
			System.out.println(e.getMessage());
			return;
		}
		polys[polyArrayIndex[COPY_POLY1]] = new Poly(
				polys[polyArrayIndex[COPY_POLY2]]);
	}

	/**
	 * This method takes input values from the user and creates a new polynomial
	 * which gets inserted at the given index.
	 * 
	 * @param in
	 *            Scanner object for getting the inputs from the user
	 * @param polys
	 *            An Array of polynomials in which the created polynomial gets
	 *            inserted
	 */

	private static void createPoly(Scanner in, Poly[] polys) {
		ArrayList<Term> termList = new ArrayList<Term>();
		String tempString;
		int arrayIndex = -1;
		try {
			arrayIndex = getCommandLineArgs(in,
					PolyProg.CREATE_NUMBER_OF_ARGUMENTS)[0];
		} catch (IllegalPolyIndexException e1) {
			System.out.println("ERROR : Illegal polynomial index: "
					+ e1.getIndex() + "\nExpecting a number between 0 and 9.");
			return;
		} catch (IllegalNumberOfArgumentsException e) {
			System.out.println(e.getMessage());
			return;
		}
		boolean correctlyEntered = false;
		while (!correctlyEntered) {
			System.out
					.print("Enter a space-separated sequence of coeff-power pairs terminated by <nl>");
			in = new Scanner(System.in);
			tempString = in.nextLine();
			String[] nums = tempString.split("\\s+"); // Splits the nums string
														// with any whitespace
														// characters as
														// delimiters
			if (nums.length % 2 != 0) {
				System.out
						.println("Warning : You have entered odd number of terms. The polynomial would "
								+ "not contain the last term as the exponent is missing.");
			}

			try {
				for (int i = 0; i < nums.length - 1; i = i + 2) {
					if (Integer.parseInt(nums[i + 1]) < 0) {
						System.out
								.println("Warning : You have entered a negative value of exponent. "
										+ "Converting it to positive value and using in the polynomial");
					}
					Term tempTerm = new Term(Double.parseDouble(nums[i]),
							Math.abs(Integer.parseInt(nums[i + 1])));
					termList.add(tempTerm);
					correctlyEntered = true;
				}
			} catch (NumberFormatException e) {
				System.out
						.println("ERROR : Invalid input- The input contains characters other than numbers. "
								+ "The polynomial cannot be created with these inputs. ");
				termList = new ArrayList<Term>();
				correctlyEntered = false;
			}
		}
		polys[arrayIndex] = new Poly(termList);
	}

	/**
	 * This method takes input values from the user and prints the polynomial at
	 * the given index.
	 * 
	 * @param in
	 *            Scanner object for getting the inputs from the user
	 * @param polys
	 *            An Array of polynomials from which the polynomial gets printed
	 */

	private static void printPoly(Scanner in, Poly[] polys) {
		try {
			int arrayIndex = -1;
			try {
				arrayIndex = getCommandLineArgs(in,
						PolyProg.PRINT_NUMBER_OF_ARGUMENTS)[0];
			} catch (IllegalPolyIndexException e1) {
				System.out.println("ERROR : Illegal polynomial index: "
						+ e1.getIndex()
						+ " \nExpecting a number between 0 and 9.");
				return;
			} catch (IllegalNumberOfArgumentsException e) {
				System.out.println(e.getMessage());
				return;
			}
			System.out.println(polys[arrayIndex].toFormattedString());
		} catch (InputMismatchException e) {
			System.out.println("ERROR : Invalid index " + in.next()
					+ ". Please enter command again.");
			return;
		}
	}

	/**
	 * This method takes input values from the user and evaluates the polynomial
	 * at the given index for a specific value of x.
	 * 
	 * @param in
	 *            Scanner object for getting the inputs from the user
	 * @param polys
	 *            An Array of polynomials from which the polynomial gets
	 *            evaluated for a specific value of x
	 */
	private static void evaluatePoly(Scanner in, Poly[] polys) {
		try {
			int arrayIndex = -1;
			try {
				arrayIndex = getCommandLineArgs(in,
						PolyProg.EVAL_NUMBER_OF_ARGUMENTS)[0];
			} catch (IllegalPolyIndexException e1) {
				System.out.println("ERROR : Illegal polynomial index: "
						+ e1.getIndex()
						+ " \nExpecting a number between 0 and 9.");
				return;
			} catch (IllegalNumberOfArgumentsException e) {
				System.out.println(e.getMessage());
				return;
			}
			double x;
			System.out.print("Enter the value of x: ");
			x = in.nextDouble();
			System.out.println(polys[arrayIndex].eval(x));
		} catch (InputMismatchException e) {
			System.out.println("ERROR : Invalid number " + in.next()
					+ ". Please enter command again.");
			return;
		}
	}

	/**
	 * This method returns the array of polynomials
	 * 
	 * @return array of polynomials
	 */
	private Poly[] getPolynomials() {
		return polynomials;
	}
}
