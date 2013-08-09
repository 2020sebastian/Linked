import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;


/**
 * A class that allows insertions, deletions, contains, size, getMax, reverse and iteration of the elements.
 * 
 * Implemented:
 *    void add   		  
 *    boolean isEmpty() 	  
 *    int size() 		  
 *    Iterator<E> iterator() 
 *
 * TO BE IMPLEMENTED:
 *    E removeAt(int pos)
 *    void removeAll(E x)
 *    E getMax()	      
 *    int contains(E x)  
 *    void reverse()     
 *
 * Notes:
 * Elements must be Comparable.
 * Class is Iterable
 *
 * Checking Class Invariants:
 * To enable assertions in Eclipse:
 * Windows > Preferences > Java > Installed JREs
 * Then select the listed JRE and then the Edit button.
 * Then type -ea in textbox labeled 'Default VM arguments:'
 * To disable assertions, delete -ea from the textbox.
 *
 * @param <E>
 */
public class MyList<E extends Comparable<E>> implements Iterable<E> {
	private Node first;
	private int sz;

	//constructor
	public MyList()
	{
		first = null;
		sz = 0;
		assert check();
	}

	//size
	public int size()
	{
		return sz;
	}

	
	public void add(E x)
	{
		Node p = new Node(x);
		if (first == null) {
		  first = p;
		} else {
		  Node r = first;
		  while(r.next != null) {
		    r = r.next;
		  }
		  r.next = p;
		}
		sz++;

		assert check();
	}

	public boolean isEmpty()
	{
		return first == null;
	}

	public Iterator<E> iterator()
	{
		return new MyIterator();
	}

	/** 
	 * checks if element x is in this MyList
	 * @param x the element to check
	 * @return true if x is in this MyList; else false
	 */
	public boolean contains(E x)
	{   
		//TO DO 
		Node a = first;
		while (a.next != null){
			if (x == a.data){
				return true;
			}
			a = a.next;
		}
		return false;
	}
	/**
	 * Removes the item at position k.
	 * The position of the first item is 0, the position of the second 
	 * item is 1 and so on. So removeAt(0) should remove the first item.
	 * @param k position where item is to be removed
	 * @throws IndexOutOfBoundsException if k < 0 or k >= size()
	 */
	public void removeAt(int k)
	{  
		// TO DO
		if (k==0 && !isEmpty()) first = first.next;
		int counter = 0;
		for(Node current = first.next;current.next != null;current = current.next){
			Node previous = first;
			if (counter == k){
				previous.next = previous.next.next;
				sz--;
			}
			previous = previous.next;
		}
	   assert check();
	}

	/** 
	 * Removes ALL occurrences of key from this list.
	 * @param key the item to be removed
	 */
	public void removeAll(E x) {
	   // TO DO
		Node a = first;
		int counter = 0;
		while(a.next !=null){
			if ((a.data).equals(x)){
				removeAt(counter);
			}
			a = a.next;
			counter++;
		}
		
		
		
		assert check();
	}
	
	/** TO DO
	 * finds largest item in this MyList
	 * @return reference to a largest item or null if MyList is empty 
	 */
	public E getMax()
	{  
		// TO DO
		Node max = first;
	  for(Node a = first.next;a.next != null;a = a.next){
		  if ((a.data).compareTo(max.data) > 1){
			  max = a;
		  }
	  }
	  return (E)(max.data);
	}

	/** TO DO 
	 * Reverses the order of this list without creating any new Nodes!
	 */
	public void reverse()
	{
	  //TO DO
		Node previous = first;
		previous.next = null;
		for (Node a = first.next; a.next != null; a = a.next){
			a.next = previous;
			previous = previous.next;
		}
		assert check();
	}

	/**
	 * Check Class Invariants
	 * 1. If first == null then sz == 0
	 *    and if sz == 0, then first == null
	 * 2. If sz == 1, then first != null and first.next == null
	 * 3. If sz > 1, then first != null and first.next != null
	 * 4. sz == number of Nodes 
	 * @return false if any class invariant fails; else return true
	 */
	private boolean check()
	{
		if (first == null && sz != 0) return false;
		if (sz == 0 && first != null) return false;
		if (sz == 1 && (first == null || first.next != null)) return false;
		if (sz > 1 && (first == null || first.next == null)) return false;

		int count = 0;
		Node p = first;
		while(p != null) {
			count++;
			p = p.next;
		}

		if (count != sz) {
			System.out.printf("count = %d, sz = %d\n", count, sz);
			return false;
		}

		return true;
	}
	
	//Node class
	private class Node
	{
		private E data;
		private Node next;
		
		//constructor
		public Node(E x)
		{
			data = x;
			next = null;
		}
	}

	private class MyIterator implements Iterator<E>
	{
		Node current;
		public MyIterator()
		{
			current = first;
		}

		public boolean hasNext()
		{
			return current != null;
		}

		public E next() 
		{
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			E x = current.data;
			current = current.next;
			return x;
		}
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	/**
	 * Simple test application. See JUnit test class MyListTest.java
	 * for a bit more extensive unit test.
	 */
	public static void main(String[] args)
	{
		Scanner stdin = new Scanner(System.in);
		int n;
		System.out.printf("Generate how many random numbers: ");
		n = stdin.nextInt();
		
		MyList<Double> lst = new MyList<Double>();
		
		for(int i = 0; i < n; i++) {
			double x = Math.random();
			lst.add(x);
		}
		int count = 0;
		Iterator<Double> p = lst.iterator();
		while(p.hasNext()) {
			double x = p.next();
			if (x <= 0.5) {
				count++;
			}
		}
		System.out.printf("The fraction of %d randomly generated betwee 0.0 and 1.0 that were <= 0.5 is %.3f", 
				lst.size(), (double) count / lst.size());


	}

}
