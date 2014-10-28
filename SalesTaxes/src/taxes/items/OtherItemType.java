package taxes.items;

import java.text.DecimalFormat;
import taxes.visitor.TaxVisitor;
import taxes.visitor.Visitor;

/**
 * This class is a concrete implementation of the item interface.
 * All items other than books, food and medicines belong to this category.
 *
 */
public class OtherItemType implements Item{

	private double otherItemPrice;
	private int quantity;
	private boolean isImported;
	private String name;
	
	public OtherItemType(double otherItemPrice, int quantity, boolean isImported, String name){
		this.otherItemPrice = otherItemPrice;
		this.isImported = isImported;
		this.quantity = quantity;
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public boolean isImported() {
		return isImported;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public double getPrice() {
		return otherItemPrice;
	}
	
	@Override
	public double accept(Visitor taxVisitor) {
		return taxVisitor.visit(this);
	}
	
	@Override
	public String toString(){
		DecimalFormat df = new DecimalFormat("#.##");
		return quantity + " " + name + " : " + df.format(this.calculateTotalPrice());
	}

	@Override
	public double calculateTotalPrice() {
		Visitor taxVisitor = new TaxVisitor();
		return (otherItemPrice + taxVisitor.visit(this));
	}

}
