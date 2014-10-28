package taxes.items;

import java.text.DecimalFormat;
import taxes.visitor.TaxVisitor;
import taxes.visitor.Visitor;

/**
 * This class is a concrete implementation of the item interface.
 * All food items belong to this category.
 */
public class Food implements Item{

	private double foodPrice;
	private int quantity;
	private boolean isImported;
	private String foodName;
	
	
	public Food(double foodPrice, int quantity, boolean isImported, String foodName){
		this.foodPrice = foodPrice;
		this.isImported = isImported;
		this.quantity = quantity;
		this.foodName = foodName;
	}

	public int getQuantity() {
		return quantity;
	}

	public boolean isImported() {
		return isImported;
	}

	public String getFoodName() {
		return foodName;
	}
	
	@Override
	public double getPrice() {
		return foodPrice;
	}
	
	@Override
	public double accept(Visitor taxVisitor) {
		return taxVisitor.visit(this);
	}
	
	@Override
	public String toString(){
		DecimalFormat df = new DecimalFormat("#.##");
		return quantity + " " + foodName + " : " + df.format(this.calculateTotalPrice());
	}

	@Override
	public double calculateTotalPrice() {
		Visitor taxVisitor = new TaxVisitor();
		return (foodPrice + taxVisitor.visit(this));
	}

}
