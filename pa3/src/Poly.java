//Name: Peehoo Dewan
//USC loginid: pdewan@usc.edu
//CSCI 455 PA3
//Fall 2013

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * A polynomial. Polynomials can be copied, added together, multiplied,
 * evaluated, and converted to a string form for printing.
 * 
 * @param terms
 *            represents a list of terms that make up the polynomial
 */
public class Poly {

	// PRIVATE INSTANCE VARIABLE(S)
	private LinkedList<Term> terms;

	/**
	 * Returns the LinkedList
	 * 
	 * @return
	 */
	public LinkedList<Term> getTerms() {
		return terms;
	}

	/**
	 * Creates the 0 polynomial
	 */
	public Poly() {
		terms = new LinkedList<Term>();
		assert isValidPoly();
	}

	/**
	 * Creates polynomial with single term given
	 */
	public Poly(Term term) {
		terms = new LinkedList<Term>();
		terms.add(term);
		assert isValidPoly();
	}

	/**
	 * Creates poly that is a copy of original (Copy constructor)
	 */
	public Poly(Poly original) {
		this.terms = original.getTerms();
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
		Arrays.sort(tempArr, new TermComparator()); // sorts in decreasing order
													// by exponent
		terms = new LinkedList<Term>();

		for (int i = 0; i < tempArr.length; i++) {
			insertTerm(tempArr[i]);
		}
		assert isValidPoly();
	}

	/**
	 * Inserts a term into the Linkedlist. It also checks if the exponent is
	 * present in the list, if it is then it adds the coefficients of the term
	 * with same exponents.
	 */
	private void insertTerm(Term term) {
		if (term.getCoeff() == 0.0) {
			return;
		}
		if (terms.size() == 0) {
			terms.add(term);
		} else {
			Term lastTerm = terms.getLast();

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
	 * Returns the Poly that is the sum of this polynomial and b (neither poly
	 * is modified)
	 */
	public Poly add(Poly b) {
		Poly result = new Poly(this);
		result.addIn(b);
		assert isValidPoly();
		assert b.isValidPoly();
		assert result.isValidPoly();
		return result;
	}

	/**
	 * Returns the Poly that is the product of this polynomial and b (neither
	 * poly is modified)
	 */
	public Poly mult(Poly b) {
		Poly result = new Poly();
		ListIterator<Term> iterator2 = b.terms.listIterator();
		while (iterator2.hasNext()) {
			Term tempMultiplier = iterator2.next();
			Poly tempResult = this.multiplyTerm(tempMultiplier);
			result.addIn(tempResult);
		}
		assert isValidPoly();
		assert b.isValidPoly();
		assert result.isValidPoly();
		return result;
	}

	/**
	 * Returns a temporary polynomial which is the product of one term of b with
	 * all terms of Poly
	 */
	private Poly multiplyTerm(Term term) {
		Poly tempResult = new Poly();
		Term termFromPoly1 = null;
		ListIterator<Term> iterator1 = this.terms.listIterator();
		while (iterator1.hasNext()) {
			termFromPoly1 = iterator1.next();
			Term newTerm = new Term(termFromPoly1.getCoeff() * term.getCoeff(),
					termFromPoly1.getExpon() + term.getExpon());
			tempResult.terms.add(newTerm);
		}
		return tempResult;
	}

	/**
	 * Adds Poly b to this poly. (mutator) (b is unchanged)
	 */
	public void addIn(Poly b) {
		ListIterator<Term> iterator1 = this.terms.listIterator();
		ListIterator<Term> iterator2 = b.terms.listIterator();

		Term termFromPoly1 = getNextTerm(iterator1);
		Term termFromPoly2 = getNextTerm(iterator2);

		while (termFromPoly1 != null && termFromPoly2 != null) {
			if (termFromPoly1.getExpon() > termFromPoly2.getExpon()) {
				termFromPoly1 = getNextTerm(iterator1);
			} else if (termFromPoly1.getExpon() < termFromPoly2.getExpon()) {
				if (iterator1.hasPrevious()) {
					iterator1.previous();
				}
				iterator1.add(termFromPoly2);
				termFromPoly1 = getNextTerm(iterator1);
				termFromPoly2 = getNextTerm(iterator2);
			} else {
				Term newTerm = new Term(
						(termFromPoly1.getCoeff() + termFromPoly2.getCoeff()),
						termFromPoly1.getExpon());
				if (newTerm.getCoeff() != 0) {
					iterator1.set(newTerm);
				} else {
					iterator1.remove();
				}
				termFromPoly2 = getNextTerm(iterator2);
			}
		}
		while (termFromPoly2 != null) {
			if (termFromPoly2.getCoeff() != 0) {
				iterator1.add(termFromPoly2);
			}
			termFromPoly2 = getNextTerm(iterator2);
		}

		assert isValidPoly();
		assert b.isValidPoly();
		return;
	}

	/**
	 * Returns the value of the poly at a given value of x.
	 */
	public double eval(double x) {
		double result = 0.0; // result stores the value of polynomial at given
		// value of x
		ListIterator<Term> iterator = terms.listIterator();
		while (iterator.hasNext() && iterator.nextIndex() < terms.size()) {
			Term term = iterator.next();
			result += term.getCoeff() * Math.pow(x, term.getExpon());
		}
		assert isValidPoly();
		return result;
	}

	/**
	 * Return a String version of the polynomial with the following format,
	 * shown by example: zero poly: "0.0" 1-term poly: "3.0x^2" 4-term poly:
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
		ListIterator<Term> iterator = terms.listIterator();
		String formatedString = "";
		while (iterator.hasNext() && iterator.nextIndex() < (terms.size() - 1)) {
			Term term = iterator.next();
			if (term.getExpon() != 1 && term.getCoeff() != 1.0) {
				formatedString += term.getCoeff() + "x^" + term.getExpon() + " + ";
			} else if (term.getExpon() != 1 && term.getCoeff() == 1.0)
				formatedString += "x^" + term.getExpon() + " + ";
			else if (term.getExpon() == 1 && term.getCoeff() != 1.0)
				formatedString += term.getCoeff() + "x" + " + ";
			else
				formatedString += "x" + " + ";
		}
		if (terms.getLast().getExpon() != 0) {
			if (terms.getLast().getExpon() != 1 && terms.getLast().getCoeff() != 1.0) {
				formatedString += terms.getLast().getCoeff() + "x^"
						+ terms.getLast().getExpon();
			} else if (terms.getLast().getExpon() != 1 && terms.getLast().getCoeff() == 1.0){
				formatedString += "x^" + terms.getLast().getExpon();
			} else if (terms.getLast().getExpon() == 1 && terms.getLast().getCoeff() != 1.0){
				formatedString += terms.getLast().getCoeff() + "x";
			}
			else
				formatedString += "x";
		} else {
			formatedString += terms.getLast().getCoeff();
		}
		assert isValidPoly();
		return formatedString;
	}

	// **************************************************************
	// PRIVATE METHOD(S)

	/**
	 * Gets the next term from the iterator if iterator has next term otherwise
	 * returns null.
	 * 
	 * @param iterator
	 * @return
	 */
	private Term getNextTerm(ListIterator<Term> iterator) {
		Term termFromPoly;
		if (iterator.hasNext())
			termFromPoly = iterator.next();
		else
			termFromPoly = null;
		assert isValidPoly();
		return termFromPoly;
	}

	/**
	 * This method returns true iff the poly data is in a valid state.
	 * Representation Invariants : The polynomial represented should be sorted
	 * in decreasing order and the exponential values should be unique Exponent
	 * of each term should be non negative Zero polynomial should be represented
	 * as "0.0" There will be no zero co-efficient terms in the poly
	 * 
	 */
	private boolean isValidPoly() {
		ListIterator<Term> iterator = terms.listIterator();
		while (iterator.hasNext() && iterator.nextIndex() < (terms.size() - 1)) {
			Term term = iterator.next();
			Term termNext = iterator.next();
			// The polynomial represented should be sorted in decreasing order
			if (term.getExpon() <= termNext.getExpon()) { 
				return false;
			}
			// Exponent of each term should be non-negative
			if (term.getExpon() < 0) { 
				return false;
			}
			// There will be no zero coefficient terms in the poly
			if (term.getCoeff() == 0) { 
				return false;
			}
		}
		// Zero polynomial should be represented as "0.0"
		if (terms.size() == 0) { 
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
