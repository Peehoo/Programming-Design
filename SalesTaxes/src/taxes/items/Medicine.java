package taxes.items;

import java.text.DecimalFormat;
import taxes.visitor.TaxVisitor;
import taxes.visitor.Visitor;

/**
 * This class is a concrete implementation of the item interface.
 * All medicines belong to this category.
 */
public class Medicine implements Item{

	private double medPrice;
	private int quantity;
	private boolean isImported;
	private String medName;
	
	public Medicine(double medPrice, int quantity, boolean isImported, String medName){
		this.medPrice = medPrice;
		this.isImported = isImported;
		this.quantity = quantity;
		this.medName = medName;
	}

	public int getQuantity() {
		return quantity;
	}

	public boolean isImported() {
		return isImported;
	}
	
	public String getMedName(){
		return medName;
	}
	
	@Override
	public double getPrice() {
		return medPrice;
	}
	
	@Override
	public double accept(Visitor taxVisitor) {
		return taxVisitor.visit(this);
	}

	@Override
	public String toString(){
		DecimalFormat df = new DecimalFormat("#.##");
		return quantity + " " + medName + " : " + df.format(this.calculateTotalPrice());
	}

	@Override
	public double calculateTotalPrice() {
		Visitor taxVisitor = new TaxVisitor();
		return (medPrice + taxVisitor.visit(this));
	}
}
