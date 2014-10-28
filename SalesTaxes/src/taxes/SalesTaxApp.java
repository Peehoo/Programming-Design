package taxes;

import java.util.LinkedHashMap;
import java.util.Map.Entry;
import taxes.items.Medicine;
import taxes.items.Book;
import taxes.items.Food;
import taxes.items.Item;
import taxes.items.OtherItemType;
import taxes.visitor.TaxVisitor;
import taxes.visitor.Visitor;

/**
 * The SalesTaxApp class containing the main method.
 *
 */
public class SalesTaxApp{
	public static void main(String args[]){
		
		Item[] input1 = new Item[]{new Book(12.49, 1, false, "book"), 
				new OtherItemType(14.99, 1, false, "music CD"),
				new Food(0.85, 1, false, "chocolate bar")};
		
		LinkedHashMap<Item, Double> salesTaxMap = calcSalesTax(input1);
		System.out.println("Output 1: ");
		print(salesTaxMap);
		
		Item[] input2 = new Item[]{new Food(10.00, 1, true, "imported box of chocolates"), 
				new OtherItemType(47.50, 1, true, "imported bottle of perfume")};
		
		salesTaxMap = calcSalesTax(input2);
		System.out.println("\n\nOutput 2: ");
		print(salesTaxMap);
		
		Item[] input3 = new Item[]{new OtherItemType(27.99, 1, true, "imported bottle of perfume"),
		new OtherItemType(18.99, 1, false, "bottle of perfume"),
		new Medicine(9.75, 1, false, "packet of headache pills"), 
		new Food(11.25, 1, true, "imported box of chocolates")};
		
		salesTaxMap = calcSalesTax(input3);
		System.out.println("\n\nOutput 3: ");
		print(salesTaxMap);
	}

	private static void print(LinkedHashMap<Item, Double> salesTaxMap) {
		for (Entry<Item, Double> entry : salesTaxMap.entrySet()) {
			System.out.println(entry.getKey().toString());
		}
		double salesTax = 0.0;
		double price = 0.0;
        for (Item item : salesTaxMap.keySet()) {
                salesTax = salesTax + salesTaxMap.get(item);
                price = price + item.getPrice();
        }
        System.out.printf("Sales Tax : %.2f", salesTax);
        System.out.printf("\nTotal : %.2f" , (price + salesTax));
	}

	private static LinkedHashMap<Item, Double> calcSalesTax(Item[] input1) {
		Visitor taxVisitor = new TaxVisitor();
		LinkedHashMap<Item, Double> taxMap = new LinkedHashMap<Item, Double>();
		for (Item item : input1) {
			taxMap.put(item, item.accept(taxVisitor));
		}
		return taxMap;
	}
}