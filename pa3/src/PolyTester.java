//USC loginid: pdewan@usc.edu
//CSCI 455 PA3
//Fall 2013

import java.util.ArrayList;

public class PolyTester {
	public static void main(String[] args) {
		testUnsortedTerms();
		testSingleTermPoly();
		testEmptyPoly();
		testSameExponentAddition();
		testNoZeroCoefficient();
		testCopyConstructor();
		testMult();
		testMultWithZeroPoly();
		testMult2();

		
		// addition of 2 non zero lists
		Poly polyTester1 = testAddInMethod(new Term(10,9),new Term(8,7),new Term(4,2),
			                    	new Term(5,3), new Term(6,1), new Term(3,0),false);	
		System.out.println(polyTester1.toFormattedString());
		assert (polyTester1.toFormattedString().equals
				("10.0x^9 + 8.0x^7 + 5.0x^3 + 4.0x^2 + 6.0x + 3.0")==true);
		
		// addition of 2 non zero list with common exponents
		Poly polyTester2 = testAddInMethod(new Term(3,3), new Term(3,3), new Term(3,3),
							new Term(5,3), new Term(6,10), new Term(3,0),false);
		assert (polyTester2.toFormattedString().equals
				("6.0x^10 + 14.0x^3 + 3.0")==true);
		
		// testing addition of empty list to empty list
		Poly polyTester3 = testAddInMethod(new Term(0,0),new Term(0,0),new Term(0,0),
            	new Term(0,0), new Term(0,0), new Term(0,0),false);	
		assert (polyTester3.toFormattedString().equals
				("0.0")==true);
		
		// addition of a polynomial to an empty list
		Poly polyTester4 = testAddInMethod(new Term(0,0),new Term(0,0),new Term(0,0),
            	new Term(6,2), new Term(6,1), new Term(-6,0),false);	
        assert (polyTester4.toFormattedString().equals
        ("6.0x^2 + 6.0x + -6.0")==true);
        
        //addition of empty polynomial to a non empty list
        Poly polyTester5 = testAddInMethod(new Term(10,5),new Term(-8,2),new Term(3,20),
            	new Term(0,0), new Term(0,0), new Term(0,0),false);
        assert (polyTester5.toFormattedString().equals
        ("3.0x^20 + 10.0x^5 + -8.0x^2")==true);
        
        //addition of non empty polynomial to a non empty list
        Poly polyTester6 = testAddInMethod(new Term(1,3),new Term(1,1),new Term(1,0),
            	new Term(-1,3), new Term(1,0), new Term(0,0), false);
        System.out.println(polyTester6.toFormattedString());
        assert (polyTester6.toFormattedString().equals("x + 2.0")==true);
        
        // testing Add method
        polyTester6 = testAddInMethod(new Term(1,3),new Term(1,1),new Term(1,0),
            	new Term(1,2), new Term(1,0), new Term(0,0), true);
        assert (polyTester6.toFormattedString().equals
        ("x^3 + x^2 + x + 2.0")==true);
        
      //addition of empty polynomial to a non empty list
        Poly polyTester7 = testAddInMethod(new Term(7,1),new Term(0,0),new Term(0,0),
            	new Term(9,3), new Term(-1,2), new Term(0,0),false);
        System.out.println("poly7 is : " + polyTester7.toFormattedString());
        assert (polyTester7.toFormattedString().equals
        ("9.0x^3 + -1.0x^2 + 7.0x")==true);
        
	}

	private static void testMult() {
		Term term1 = new Term(1,3);
		Term term2 = new Term(1,1);
		Term term3 = new Term(1,0);
		ArrayList<Term> termList1 = new ArrayList<Term>();
		termList1.add(term1);
		termList1.add(term2);
		termList1.add(term3);
		
		Term term1a = new Term(1,2);
		Term term2a = new Term(1,1);
		ArrayList<Term> termList2 = new ArrayList<Term>();
		termList2.add(term1a);
		termList2.add(term2a);
		
		Poly poly1 = new Poly(termList1);
		Poly poly2 = new Poly(termList2);
		Poly result = poly1.mult(poly2);
		assert (result.toFormattedString().equals
		        ("x^5 + x^4 + x^3 + 2.0x^2 + x")==true);	
	}
	
	private static void testMultWithZeroPoly() {
		Term term1 = new Term(1,3);
		Term term2 = new Term(1,1);
		Term term3 = new Term(1,0);
		ArrayList<Term> termList1 = new ArrayList<Term>();
		termList1.add(term1);
		termList1.add(term2);
		termList1.add(term3);
		
		// checked also when poly1 is a zero poly and poly2 is non zero
		Poly poly1 = new Poly(termList1);
		Poly poly2 = new Poly();
		Poly result = poly1.mult(poly2);
		assert (result.toFormattedString().equals("0.0")==true);	
	}
	
	private static void testMult2() {
		Term term1 = new Term(10,9);
		Term term2 = new Term(6,5);
		Term term3 = new Term(7,0);
		ArrayList<Term> termList1 = new ArrayList<Term>();
		termList1.add(term1);
		termList1.add(term2);
		termList1.add(term3);
		
		Term term1a = new Term(5,3);
		Term term2a = new Term(4,7);
		Term term3a = new Term(20,0);
		ArrayList<Term> termList2 = new ArrayList<Term>();
		termList2.add(term1a);
		termList2.add(term2a);
		termList2.add(term3a);
		
		Poly poly1 = new Poly(termList1);
		Poly poly2 = new Poly(termList2);
		Poly result = poly1.mult(poly2);
		System.out.println(result.toFormattedString());
		assert (result.toFormattedString().equals
		   ("40.0x^16 + 74.0x^12 + 200.0x^9 + 30.0x^8 + 28.0x^7 + 120.0x^5 + 35.0x^3 + 140.0")==true);	
	    }

	private static Poly testAddInMethod(Term term1, Term term2, Term term3, 
										Term term1a, Term term2a, Term term3a, boolean isAdd) {
		ArrayList<Term> termList1 = new ArrayList<Term>();
		termList1.add(term1);
		termList1.add(term2);
		termList1.add(term3);
		
		ArrayList<Term> termList2 = new ArrayList<Term>();
		termList2.add(term1a);
		termList2.add(term2a);
		termList2.add(term3a);
		
		Poly poly1 = new Poly(termList1);
		Poly poly2 = new Poly(termList2);
		if(!isAdd){
			poly1.addIn(poly2);
			return poly1;
		}
		return poly1.add(poly2);
	}

	private static void testEmptyPoly() {
		Poly poly = new Poly();
		assert (poly.toFormattedString().equals("0.0"));
		assert (poly.eval(2)==0.0);
	}

	private static void testSingleTermPoly() {
		Term term = new Term(3,5);
		Poly poly = new Poly(term);
		assert (poly.toFormattedString().equals("3.0x^5"));
		assert (poly.eval(2)==96.0);
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
		Term term1 = new Term(1,3);
		Term term2 = new Term(6,2);
		Term term3 = new Term(5,3);
		Term term4 = new Term(-1,2);
		ArrayList<Term> termList = new ArrayList<Term>();
		termList.add(term1);
		termList.add(term2);
		termList.add(term3);
		termList.add(term4);
		
		Poly poly = new Poly(termList);
		assert (poly.toFormattedString().equals("6.0x^3 + 5.0x^2")==true);
		assert (poly.eval(-1)== -1.0);
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
	
	private static void testCopyConstructor(){
		Term term1 = new Term(9,3);
		Term term2 = new Term(3,10);
		Term term3 = new Term(-18,0);
		Term term4 = new Term(7,1);
		ArrayList<Term> termList = new ArrayList<Term>();
		termList.add(term1);
		termList.add(term2);
		termList.add(term3);
		termList.add(term4);
		
		Poly poly = new Poly(termList);
		Poly copyOfpoly = new Poly(poly);
		assert (poly.toFormattedString().equals("3.0x^10 + 9.0x^3 + 7.0x + -18.0")==true);
		assert (copyOfpoly.toFormattedString().equals("3.0x^10 + 9.0x^3 + 7.0x + -18.0")==true);
		assert (poly.eval(1)== 1.0);
		assert (copyOfpoly.eval(1)== 1.0);
	}
}


