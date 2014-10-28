package taxes.items;

import java.text.DecimalFormat;
import taxes.visitor.TaxVisitor;
import taxes.visitor.Visitor;

/**
 * This class is a concrete implementation of the item interface.
 * All books belong to this category.
 */
public class Book implements Item{

	private double bookPrice;
	private int quantity;
	private boolean isImported;
	private String bookName;
	
	public Book(double bookPrice, int quantity, boolean isImported, String bookName){
		this.bookPrice = bookPrice;
		this.quantity = quantity;
		this.isImported = isImported;
		this.bookName = bookName;
	}

	public int getQuantity() {
		return quantity;
	}

	public boolean isImported() {
		return isImported;
	}
	
	public String getBookName() {
		return bookName;
	}
	
	public double addTaxToPrice(double tax) {
		 return (getPrice() + tax);
	}
	
	@Override
	public double getPrice() {
		return bookPrice;
	}
	
	@Override
	public double accept(Visitor taxVisitor) {
		return taxVisitor.visit(this);
	}

	@Override
	public String toString(){
		DecimalFormat df = new DecimalFormat("#.##");
		return quantity + " " + bookName + " : " + df.format(this.calculateTotalPrice());
	}

	@Override
	public double calculateTotalPrice() {
		Visitor taxVisitor = new TaxVisitor();
		return (bookPrice + taxVisitor.visit(this));
	}

}
