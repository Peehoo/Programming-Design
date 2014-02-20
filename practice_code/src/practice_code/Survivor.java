package practice_code;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

import practice_code.TrinaryTree.Node;



/**
 * This class calculates the chair number of the survivor when the survivor would 
 * be sitting on any one of the chairs in the circular arrangement of chairs.
 * @author Peehoo
 *
 */
public class Survivor {

	// PRE : The entered number should be an integer. Error handling not done
	// for the cases when input is other than integer.
	
		private Node head;
	
	public Node getHead() {
			return head;
		}

		public void setHead(Node head) {
			this.head = head;
		}

	public static void main(String args[]) {
		Survivor S = new Survivor();
		Scanner input = new Scanner(System.in);
		System.out.print("Please enter the total number of chairs : ");
		int numberOfChairs = Integer.parseInt(input.next());
		LinkedList<Node> list = new LinkedList<Node>();
		while(numberOfChairs<=0){
			System.out.println("Error : Number of chairs should be greater than zero");
			System.out.print("Please enter the total number of chairs : ");
			numberOfChairs = Integer.parseInt(input.next());
		}
		
		for (int i = 1; i <= numberOfChairs; i++) {
			
			if(i==1){
				Node newNode = S.new Node(i);
				list.add(newNode);
				S.setHead(newNode);;
			}
			else if(i==numberOfChairs){
				Node newNode = S.new Node(i, S.getHead());
				Node tempNode = S.getHead();
				while(tempNode.next!=null){
					tempNode = tempNode.next;
				}
				tempNode.next = newNode;
				list.add(newNode);
			}
			else{
				Node newNode = S.new Node(i, null);
				Node tempNode = S.getHead();
				while(tempNode.next!=null){
					tempNode = tempNode.next;
				}
				tempNode.next = newNode;
			}
		}
		System.out.println("The Survivor was sitting on chair number : "
				+ S.getSurvivorChairNumber(list));
	}

	public Survivor(){			//default constructor
		head = null;
	}
	
	
	public int getSurvivorChairNumber(LinkedList<Node> list) {
		Node chairNumber = new Node(0,null);
		if (list.size() == 0) { // In case of empty list
			return chairNumber.data;
		}
		while (list.size() != 0) {
			ListIterator<Node> iter = list.listIterator();
			while (iter.hasNext()) {
				chairNumber = iter.next();
				if (list.size() != 1) {
					iter.remove();
					if (iter.hasNext()) {
						chairNumber = iter.next();
					}
				} else if (list.size() == 1) {
					return chairNumber.data;
				}
			}
		}

		return chairNumber.data;
	}
	
	private class Node {
		private int data;
		private Node next;

		public Node(int data, Node next) {
			this.next = next;
			this.data = data;
		}

		public Node(int data) {
			this(data, null);
		}
	} 
}
