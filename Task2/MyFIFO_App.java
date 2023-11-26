package Task2;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class MyFIFO_App {
	// method stutter that accepts a queue of integers as 
	//	a parameter and replaces
	// every element of the queue with two copies of that element
	// front [1, 2, 3] back
	// becomes
	// front [1, 1, 2, 2, 3, 3] back
	public static <E> void stutter(Queue<E> input) {
		int size = input.size();
		for (int i = 0; i < size; i++) {
			E e = input.poll();
			input.add(e);
			input.add(e);
		}
	}
	// Method mirror that accepts a queue of strings as a 
	//	parameter and appends the
	// queue's contents to itself in reverse order
	// front [a, b, c] back 
	// front [a, b, c, c, b, a] back
	public static <E> void mirror(Queue<E> input) {
		Stack<E> temp = new Stack<E>();
		int size = input.size();
		for (int i = 0; i < size; i++) {
			E e = input.poll();
			temp.add(e);
			input.add(e);
		}
		for (int i = 0; i < size; i++) {
			input.add(temp.pop());
		}
	}
	
	public static void test() {
		Queue<Integer> q = new ArrayDeque<Integer>();
		Queue<Integer> q1 = new ArrayDeque<Integer>();
		for (int i = 1; i <= 3; i++) {
			q.add(i);
			q1.add(i);
		}
		stutter(q);
		System.out.print("Phương thức Stutter: ");
		System.out.println(q);
		mirror(q1);
		System.out.print("Phương thức Mirror: ");
		System.out.println(q1);
		
	}
	
	public static void main(String[] args) {
		test();
	}

}
