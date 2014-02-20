package practice_code;

import java.util.Scanner;

/**
 * Problem Statement:
 * You are in a room with a circle of 100 chairs. The chairs are numbered sequentially from 1 to 100. At some point
 * in time, the person in chair #1 will be asked to leave. The person in chair #2 will be skipped, and the person 
 * in chair #3 will be asked to leave. This pattern of skipping one person and asking the next to leave will keep
 * going around the circle until there is one person left… the survivor. Find the chair number of survivor
 */

public class CircularList<T extends Comparable<T>> {
	private Node<T> head;
	private Node<T> tail;

	public CircularList() {
	}

	public Node<T> getHead() {
		return head;
	}

	public static void main(String[] args) {
		CircularList<Integer> listOfChairs = new CircularList<Integer>();
		Scanner input = new Scanner(System.in);
		System.out.print("Please enter the total number of chairs : ");
		int numberOfChairs = Integer.parseInt(input.next());
		while (numberOfChairs <= 0) {
			System.out
					.println("Error : Number of chairs should be greater than zero");
			System.out.print("Please enter the total number of chairs : ");
			numberOfChairs = Integer.parseInt(input.next());
		}
		for (int i = 1; i <= numberOfChairs; i++) {
			listOfChairs.insert((Integer) i);
		}
		// Starting to make people leave till survivor remains
		listOfChairs.deleteTillLastNode();
		System.out.println("The chair number of survivor is : "
				+ listOfChairs.getHead().getData());
	}

	public void insert(T data) {
		if (this.head == null) {
			Node<T> newNode = new Node<T>(data);
			head = newNode;
			tail = newNode;
		} else {
			Node<T> newNode = new Node<T>(data);
			tail.next = newNode;
			tail = newNode;
			tail.next = head;
		}
	}

	public void deleteTillLastNode() {
		if (this.head == null) {
			return;
		} else {
			Node<T> temp = new Node<T>();
			while (head != tail) {
				temp = head.next;
				tail.next = temp;
				head = temp.next;
				tail = temp;
			}
		}
	}

	public class Node<T> {
		private T data;
		private Node<T> next;

		public Node(T data, Node<T> next) {
			this.next = next;
			this.data = data;
		}

		public Node(T data) {
			this(data, null);
		}

		public Node() {

		}

		public T getData() {
			return this.data;
		}
	}

}