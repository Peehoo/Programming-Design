package taxes.visitor;

import taxes.items.Book;
import taxes.items.Food;
import taxes.items.Medicine;
import taxes.items.OtherItemType;
/**
 * This class is a concrete implementation of the Visitor interface
 * It implements the visit method for specific items
 * @author peehoo
 *
 */
public class TaxVisitor implements Visitor {

	@Override
	public double visit(Book book) {
		double totalCost = book.getQuantity() * book.getPrice();
		double tax = 0;
		//add the import tax if the item is imported
		if (book.isImported()) {
			tax = totalCost * 0.05;
			tax = roundedTax(tax);
		}
		return tax;
	}

	@Override
	public double visit(Food food) {
		double totalCost = food.getQuantity() * food.getPrice();
		double tax = 0;
		//add the import tax if the item is imported
		if (food.isImported()) {
			tax = totalCost * 0.05;
			tax = roundedTax(tax);
		}
		return tax;
	}

	@Override
	public double visit(Medicine medicine) {
		double totalCost = medicine.getQuantity() * medicine.getPrice();
		double tax = 0;
		//add the import tax if the item is imported
		if (medicine.isImported()) {
			tax = totalCost * 0.05;
			tax = roundedTax(tax);
		}
		return tax;
	}

	@Override
	public double visit(OtherItemType other) {
		double totalCost = other.getQuantity() * other.getPrice();
		double tax = totalCost * 0.1;
		double importTax = 0;
		tax = roundedTax(tax);
		//add the import tax if the item is imported
		if (other.isImported()) {
			importTax = totalCost * 0.05;
			importTax = roundedTax(importTax);
		}
		return (tax + importTax);
	}
	
	//method to take care of rounding rules for tax
	public double roundedTax(double tax) {
		double totalTax = ((double)Math.round(tax*10*2))/20;
		return totalTax;
	}
}
