//Name: Peehoo Dewan
//USC loginid: pdewan@usc.edu
//CSCI 455 PA2
//Fall 2013

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Arrays;

/**
 * A polynomial. Polynomials can be added together, evaluated, and converted to
 * a string form for printing.
 * 
 * @param terms
 *            represents a list of terms that make up the polynomial
 */
public class Poly {

	// PRIVATE INSTANCE VARIABLE(S)
	private ArrayList<Term> terms;

	/**
	 * Creates the 0 polynomial
	 */
	public Poly() {
		terms = new ArrayList<Term>();
		assert isValidPoly();
	}

	/**
	 * Creates polynomial with single term given
	 */
	public Poly(Term term) {
		terms = new ArrayList<Term>();
		terms.add(term);
		assert isValidPoly();
	}

	/**
	 * Creates polynomial from a (possibly-empty) ArrayList of terms. The terms
	 * in the ArrayList have no restrictions: can be in any order, have 0 terms,
	 * and/or duplicate exponents. The given ArrayList is not modified by the
	 * constructor.
	 */
	public Poly(ArrayList<Term> termList) {
		Term[] tempArr = termList.toArray(new Term[termList.size()]); 
		Arrays.sort(tempArr, new TermComparator()); // sorts in decreasing order by exponent 
		terms = new ArrayList<Term>();

		for (int i = 0; i < tempArr.length; i++) {
			insertTerm(tempArr[i]);
		}
		assert isValidPoly();
	}

	/**
	 * Inserts a term into the Arraylist. It also checks if the exponent is
	 * present in the list, if it is then it adds the coefficients of the term
	 * with same exponents.
	 */
	private void insertTerm(Term term) {
		if (terms.size() == 0) {
			terms.add(term);
		} else if (term.getCoeff() == 0.0) {
			return;
		} else {
			Term lastTerm = terms.get(terms.size() - 1);

			if (lastTerm.getExpon() == term.getExpon()) {
				Term newTerm = new Term(lastTerm.getCoeff() + term.getCoeff(),
						lastTerm.getExpon());
				if (newTerm.getCoeff() != 0) {
					terms.set(terms.size() - 1, newTerm);
				} else {
					terms.remove(lastTerm);
				}
			} else
				terms.add(term);
		}
		assert isValidPoly();
	}

	/**
	 * Returns the value of the poly at a given value of x.
	 */
	public double eval(double x) {
		double result = 0.0; // result stores the value of polynomial at given
		// value of x
		for (int i = 0; i < terms.size(); i++) {
			result += terms.get(i).getCoeff()
					* Math.pow(x, terms.get(i).getExpon());
		}
		assert isValidPoly();
		return result;
	}

	/**
	 * Return a String version of the polynomial with the following format,
	 * shown by exmaple: zero poly: "0.0" 1-term poly: "3.0x^2" 4-term poly:
	 * "3.0x^5 + x^2 + 2.0x + 7.0"
	 * 
	 * Poly is in a simplified form (only one term for any exponent), with no
	 * zero-coefficient terms, and terms are shown in decreasing order by
	 * exponent.
	 */
	public String toFormattedString() {
		if (terms.size() == 0) {
			return "0.0";
		}

		String formatedString = "";
		for (int i = 0; i < terms.size() - 1; i++) {

			formatedString += terms.get(i).getCoeff() + "x^"
					+ terms.get(i).getExpon() + " + ";

		}
		if (terms.get(terms.size() - 1).getExpon() != 0) {
			formatedString += terms.get(terms.size() - 1).getCoeff() + "x^"
					+ terms.get(terms.size() - 1).getExpon();
		} else
			formatedString += terms.get(terms.size() - 1).getCoeff();
		assert isValidPoly();
		return formatedString;
	}

	// **************************************************************
	// PRIVATE METHOD(S)

	/**
	 * This method returns true iff the poly data is in a valid state.
	 * Representation Invariants :
	 * The polynomial represented should be sorted in decreasing order and the exponential values should be unique
	 * Exponent of each term should be non negative 
	 * Zero polynomial should be represented as "0.0" 
	 * There will be no zero co-efficient terms in the poly
	 * 
	 */
	private boolean isValidPoly() {
		for (int i = 0; i < terms.size() - 1; i++) {
			if (terms.get(i).getExpon() <= terms.get(i + 1).getExpon()) { //The polynomial represented should be sorted in decreasing order
				return false;
			}
			if (terms.get(i).getExpon() < 0) { 	//Exponent of each term should be non negative
				return false;
			}
			if(terms.get(i).getCoeff() == 0){	//There will be no zero co-efficient terms in the poly
				return false;
			}
		}
		if (terms.size() == 0) { 	//Zero polynomial should be represented as "0.0" 
			if (!this.toFormattedString().equals("0.0")) {
				return false;
			}
		}
		return true;
	}

}

// *****************************************************************
// Helper class needed for call to Arrays.sort above -- DO NOT CHANGE

// comparator to be used by sort in ArrayList to Poly constructor, above
class TermComparator implements Comparator<Term> {

	// returns value < 0 if t1's exponent is > t2's exponent (i.e. t1 should
	// come before t2),
	// value > 0 if t1's exponent is < t2's exponent (i.e., t1 should come after
	// t2),
	// and 0 if their exponents are the same
	public int compare(Term t1, Term t2) {
		return t2.getExpon() - t1.getExpon();
	}
}
