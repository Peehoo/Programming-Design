
import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Test;


public class JUnitPolyTester{
	
	@Test
	public void testMultiply1(){
		Term term1 = new Term(1,3);
		Term term2 = new Term(1,1);
		Term term3 = new Term(1,0);
		ArrayList<Term> termList1 = new ArrayList<Term>();
		termList1.add(term1);
		termList1.add(term2);
		termList1.add(term3);
		
		Term term1a = new Term(1,2);//x2 +x * x3 +x +1 = x5 + x4 + x3 + x2
		Term term2a = new Term(1,1);
		ArrayList<Term> termList2 = new ArrayList<Term>();
		termList2.add(term1a);
		termList2.add(term2a);
		
		Poly poly1 = new Poly(termList1);
		Poly poly2 = new Poly(termList2);
		Poly result = poly1.mult(poly2);
		System.out.println(result.toFormattedString());
		Assert.assertTrue(result.toFormattedString().equals
		        ("x^5 + x^4 + x^3 + 2.0x^2 + x"));
	}

	@Test
	public void testMultiply2(){
		Term term1 = new Term(4,3);
		Term term2 = new Term(-3,0);
		Term term3 = new Term(700,2);
		ArrayList<Term> termList1 = new ArrayList<Term>();
		termList1.add(term1);
		termList1.add(term2);
		termList1.add(term3);
		
		Term term1a = new Term(1,1);
		Term term2a = new Term(1,1);
		ArrayList<Term> termList2 = new ArrayList<Term>();
		termList2.add(term1a);
		termList2.add(term2a);
		
		Poly poly1 = new Poly(termList1);
		Poly poly2 = new Poly(termList2);
		Poly result = poly1.mult(poly2);
		Assert.assertTrue(result.toFormattedString().equals
		        ("8.0x^4 + 1400.0x^3 + -6.0x"));
	}

	@Test //tests multiplication with constant polynomial
	public void testMultiply3(){
		Term term1 = new Term(29,2);
		Term term2 = new Term(-19,0);
		Term term3 = new Term(100,1);
		ArrayList<Term> termList1 = new ArrayList<Term>();
		termList1.add(term1);
		termList1.add(term2);
		termList1.add(term3);
		
		Term term1a = new Term(1,0);
		Term term2a = new Term(2,0);
		ArrayList<Term> termList2 = new ArrayList<Term>();
		termList2.add(term1a);
		termList2.add(term2a);
		
		Poly poly1 = new Poly(termList1);
		Poly poly2 = new Poly(termList2);
		Poly result = poly1.mult(poly2);
		Assert.assertTrue(result.toFormattedString().equals
		        ("87.0x^2 + 300.0x + -57.0"));
	}

}
