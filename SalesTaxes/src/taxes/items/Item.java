package taxes.items;

import taxes.visitor.Visitor;

/**
 * This common item iterface takes care of 
 * @author peehoo
 *
 */
public interface Item {
	public double accept(Visitor taxVisitor);
	public double getPrice();
	/*Get price including taxes*/
	public double calculateTotalPrice();
	public String toString();
}
