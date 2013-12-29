//Name: Peehoo Dewan
//USC loginid: pdewan@usc.edu
//CSCI 455 PA2
//Fall 2013

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
/**
 * The PolyProg class lets a user build and do computations on 
 * several Poly objects in the array polynomials
 * @author Peehoo
 *
 */
public class PolyProg {
	private final int CAPACITY = 10;
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
			} else if (command.equalsIgnoreCase("quit")) {
				break; // Quits the console
			} else if (command.equalsIgnoreCase("help")) {
				System.out.println("The List of Valid commands are: \n"
						+ "1. create n - This command creates a new polynomial at index 'n' (n is between 0-9) \n"
						+ "2. print n - This command prints the polynomial at index 'n' (n is between 0-9)."
						+ "If there is no polynomial created at that index, it prints the zero polynomial which is 0.0 \n"
						+ "3. eval n - This command evaluates the polynomial at index 'n' (n is between 0-9) for a given value of x \n"
						+ "4. addin n m - This command adds polynomial at index 'm' to polynomial at index 'n' (n and m are between 0-9) \n"
						+ "5. add n m p - This command adds polynomials at inices 'p' and 'm' and stores the result in polynomial at index 'n'"
						+ "(n, m and p are between 0-9) \n"
						+ "6. mult n m p - This command multiplies polynomials at inices 'p' and 'm' and stores the result in polynomial at index 'n'"
						+ "(n, m and p are between 0-9) \n"
						+ "7. copy n m - This command copies the polynomial at index 'm' into polynomial at index 'n' \n"
						+ "8. quit - quits this program");
			} else {
				System.out
				.println("ERROR: Illegal command.  Type 'help' for command options.");
			}
		}
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
		int arrayIndex = 0;
		try {
			arrayIndex = in.nextInt();
			while (arrayIndex >= 10 || arrayIndex < 0) {
				System.out.print("Illegal index: " + arrayIndex + ". Please enter an index between 0-9: ");
				arrayIndex = in.nextInt();
			}

		} catch (InputMismatchException e) {
			System.out.println("Invalid index " + in.next() + ". Please enter command again.");
			return;
		}

		System.out.print("Enter a space-separated sequence of coeff-power pairs terminated by <nl>");
		in = new Scanner(System.in);
		tempString = in.nextLine();
		String[] nums = tempString.split("\\s+"); // Splits the nums string with any whitespace characters as delimiters
		if (nums.length % 2 != 0) {
			System.out.println("Warning : You have entered odd number of terms. The polynomial would "
					+ "not contain the last term as the exponent is missing.");
		}
		for (int i = 0; i < nums.length - 1; i = i + 2) {
			try {
				if (Integer.parseInt(nums[i + 1]) < 0) {
					System.out.println("Warning : You have entered a negative value of exponent. "+ "Converting it to positive value and using in the polynomial");
				}
				Term tempTerm = new Term(Integer.parseInt(nums[i]), Math.abs(Integer.parseInt(nums[i + 1])));
				termList.add(tempTerm);
			} catch (NumberFormatException e) {
				System.out.println("Invalid input: The input contains characters other than numbers. " + "The polynomial cannot be created with these inputs. Please enter the command again");
				return;
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
			int arrayIndex = in.nextInt();
			while (arrayIndex >= 10 || arrayIndex < 0) {
				System.out.print("Illegal index: " + arrayIndex
						+ ". Please enter an index between 0-9: ");
				arrayIndex = in.nextInt();
			}
			System.out.println(polys[arrayIndex].toFormattedString());
		} catch (InputMismatchException e) {
			System.out.println("Invalid index " + in.next()
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
			int arrayIndex = in.nextInt();
			while (arrayIndex >= 10 || arrayIndex < 0) {
				System.out.print("Illegal index: " + arrayIndex
						+ ". Please enter an index between 0-9: ");
				arrayIndex = in.nextInt();
			}
			double x;
			System.out.print("Enter the value of x: ");
			x = in.nextDouble();
			
			System.out.println(polys[arrayIndex].eval(x));
		} catch (InputMismatchException e) {
			System.out.println("Invalid number " + in.next()
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
