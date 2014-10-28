package taxes;

import junit.framework.Assert;

import org.junit.Test;

import taxes.items.Book;
import taxes.items.OtherItemType;
import taxes.visitor.TaxVisitor;
import taxes.visitor.Visitor;

@SuppressWarnings("deprecation")
public class TestTaxVisitor {
	@Test
	public void testRoundedTax(){
		TaxVisitor visitor = new TaxVisitor();
		double tax = visitor.roundedTax(2.375);
		Assert.assertEquals(2.40, tax);
	}
	
	@Test
	public void testBookObject(){
		Book book = new Book(12.49, 1, false, "book");
		Visitor taxVisitor = new TaxVisitor();
		double tax = book.accept(taxVisitor);
		Assert.assertEquals(0.0, tax);
	}
	
	@Test
	public void testBookObjectIfImported(){
		Book book = new Book(12.49, 1, true, "book");
		Visitor taxVisitor = new TaxVisitor();
		double tax = book.accept(taxVisitor);
		Assert.assertEquals(0.6, tax);
	}
	
	@Test
	public void testOtherItemObjectIfImported(){
		OtherItemType item = new OtherItemType(47.5, 1, true, "imported bottle of perfume");
		Visitor taxVisitor = new TaxVisitor();
		double tax = item.accept(taxVisitor);
		Assert.assertEquals(7.15, tax);
	}
}
