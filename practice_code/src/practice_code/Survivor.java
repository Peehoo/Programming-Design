package practice_code;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

/**
 * This class calculates the chair number of the survivor when the survivor would 
 * be sitting on any one of the chairs in the circular arrangement of chairs.
 * @author Peehoo
 *
 */
public class Survivor {

	// PRE : The entered number should be an integer. Error handling not done
	// for the cases when input is other than integer.
	public static void main(String args[]) {
		Survivor S = new Survivor();
		Scanner input = new Scanner(System.in);
		System.out.print("Please enter the total number of chairs : ");
		String numberOfChairs = input.next();
		LinkedList<Integer> list = new LinkedList<Integer>();
		while(Integer.parseInt(numberOfChairs)<=0){
			System.out.println("Error : Number of chairs should be greater than zero");
			System.out.print("Please enter the total number of chairs : ");
			numberOfChairs = input.next();
		}
		for (int i = 1; i <= Integer.parseInt(numberOfChairs); i++) {
			list.add(i);
		}
		System.out.println("The Survivor was sitting on chair number : "
				+ S.getSurvivorChairNumber(list));
	}

	public Survivor(){			//default constructor
		
	}
	
	
	public int getSurvivorChairNumber(LinkedList<Integer> list) {
		Integer chairNumber = 0;
		if (list.size() == 0) { // In case of empty list
			return chairNumber;
		}
		while (list.size() != 0) {
			ListIterator<Integer> iter = list.listIterator();
			while (iter.hasNext()) {
				chairNumber = iter.next();
				if (list.size() != 1) {
					iter.remove();
					if (iter.hasNext()) {
						chairNumber = iter.next();
					}
				} else if (list.size() == 1) {
					return chairNumber;
				}
			}
		}

		return chairNumber;
	}
}
