package taxes.visitor;

import taxes.items.Book;
import taxes.items.Food;
import taxes.items.Medicine;
import taxes.items.OtherItemType;

/**
 * This interface defines visit operation on concrete items
 * @author peehoo
 *
 */
public interface Visitor {
	double visit(Book book);
	double visit(Food food);
	double visit(Medicine medicine);
	double visit(OtherItemType other);
}
