import java.util.ArrayList;



public class PolyTesterPA2 {
	public static void main(String[] args) {
		testUnsortedTerms();
		testSingleTermPoly();
		testEmptyPoly();
		testSameExponentAddition();
		testNoZeroCoefficient();
		testTwoermPoly();
		testSingleTermPoly2();
		testSingleTermPoly3();
		testDoubleTermPoly3();
	}

	private static void testEmptyPoly() {
		Poly poly = new Poly();
		assert (poly.toFormattedString().equals("0.0"));
		assert (poly.eval(2)==0.0);
	}

	private static void testSingleTermPoly() {
		Term term = new Term(3,5);
		Poly poly = new Poly(term);
		System.out.println(poly.toFormattedString());
		assert (poly.toFormattedString().equals("3.0x^5"));
		assert (poly.eval(2)==96.0);
	}
	
	private static void testSingleTermPoly2() {
		Term term = new Term(1,1);
		Poly poly = new Poly(term);
		System.out.println(poly.toFormattedString());
		assert (poly.toFormattedString().equals("x"));
		assert (poly.eval(2)==2.0);
	}
	
	private static void testSingleTermPoly3() {
		Term term = new Term(1,5);
		Poly poly = new Poly(term);
		System.out.println(poly.toFormattedString());
		assert (poly.toFormattedString().equals("x^5"));
		assert (poly.eval(2)==32.0);
	}
	private static void testDoubleTermPoly3() {
		Term term1 = new Term(5,1);
		Term term2 = new Term(1,0);
		ArrayList<Term> terms = new ArrayList<Term>();
		terms.add(term1);
		terms.add(term2);
		Poly poly = new Poly(terms);
		
		System.out.println(poly.toFormattedString());
		assert (poly.toFormattedString().equals("5.0x + 1.0"));
	}
	
	private static void testTwoermPoly() {
		Term term1 = new Term(1,1);
		Term term2 = new Term(1,0);
		ArrayList<Term> terms = new ArrayList<Term>();
		terms.add(term1);
		terms.add(term2);
		Poly poly = new Poly(terms);
		
		System.out.println(poly.toFormattedString());
		assert (poly.toFormattedString().equals("x + 1.0"));
		//assert (poly.eval(2)==96.0);
	}

	/**
	 * The test method  makes sure that the polynomial contains no zero coefficient terms
	 */
	private static void testNoZeroCoefficient() {
		Term term1 = new Term(0,3);
		Term term2 = new Term(2,2);
		Term term3 = new Term(-2,2);
		Term term4 = new Term(7,5);
		ArrayList<Term> termList = new ArrayList<Term>();
		termList.add(term1);
		termList.add(term2);
		termList.add(term3);
		termList.add(term4);
		Poly poly = new Poly(termList);
		assert (poly.toFormattedString().equals("7.0x^5"));
		assert (poly.eval(-1)== -7.0);
	}
	/**
	 * The test method  makes sure that the terms with same exponents are added properly
	 * and no 2 terms have the same exponents
	 */
	private static void testSameExponentAddition() {
		Term term1 = new Term(1,2);
		Term term2 = new Term(6,1);
		Term term3 = new Term(0,3);
		Term term4 = new Term(-1,2);
		ArrayList<Term> termList = new ArrayList<Term>();
		termList.add(term1);
		termList.add(term2);
		termList.add(term3);
		termList.add(term4);
		
		Poly poly = new Poly(termList);
		System.out.print(poly.toFormattedString());
		assert (poly.toFormattedString().equals("6.0x")==true);
		//assert (poly.eval(-1)== -1.0);
	}

	private static void testUnsortedTerms() {
		Term term1 = new Term(1,3);
		Term term2 = new Term(3,100);
		Term term3 = new Term(-11,0);
		Term term4 = new Term(5,2);
		ArrayList<Term> termList = new ArrayList<Term>();
		termList.add(term1);
		termList.add(term2);
		termList.add(term3);
		termList.add(term4);
		
		Poly poly = new Poly(termList);
		assert (poly.toFormattedString().equals("3.0x^100 + x^3 + 5.0x^2 + -11.0")==true);
		assert (poly.eval(1)== -2.0);
	}
}
