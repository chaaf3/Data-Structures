package topic1;

import java.sql.Array;

public class SLL<E> {

   

	public static class Pair<E,F> {
		// data fields
		private E x;
		private F y;

		// Constructor
		public Pair(E x, F y) {
			super();
			this.x=x;
			this.y=y;
		}

		public E getX() {
			return x;
		}

		public void setX(E x) {
			this.x = x;
		}

		public F getY() {
			return y;
		}

		public void setY(F y) {
			this.y = y;
		}

		public String toString() {
			return "("+x.toString()+","+y.toString()+")";
		}

	}

    
	public static class Node<F> {
		// data fields
		private F data;
		private Node<F> next;

		Node(F data) {
			this.data=data;
			this.next=null;
		}

		Node(F data, Node<F> next) {
			this.data=data;
			this.next=next;
		}

	}

	// Data fields
	private Node<E> head;
	private int size;

	// Constructor
	SLL() {
		head=null;
		size=0;
	}

	// Methods
	public E addFirst(E item) {
		head = new Node<E>(item,head);
		size++;
		return item;
	}
	// add item at the end of the list
	public E add(E item) {

		if (head==null) { // the list is empty
			head = new Node<E>(item);
			size++;
			return item;
		} 
		// the list is not empty
		Node<E> current = head;

		while (current.next!=null) {
			current = current.next;
		}
		current.next = new Node<E>(item);
		size++;
		return item;

	}	

	// add item at given index
	public E add(E item, int index) {
		if (index<0 || index > size ) {
			throw new IllegalArgumentException("Index out of bounds");
		}
		if (head==null) {
			addFirst(item);
			return item;
		}
		// the list is not empty
		Node<E> current = head;
		for (int i =0; i<index-1; i++) {
			current = current.next;
		}
		current.next = new Node<E>(item,current.next);
		size++;
		return item;
		
	}
	


	
	public boolean mem(E data) {
		boolean found = false;
		Node<E> current=head;
		
		while (!found && current!=null) {
			found = found || current.data.equals(data);
			current = current.next;
		}
		
		return found;
		
	}
	





	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		Node<E> current=head;

		result.append("[");
		while (current!=null) {
			result.append(current.data.toString()+",");
			current = current.next;
		}
		result.append("]");

		return result.toString();


	}
	public SLL<E> noRepeats() {
		//return the "this" list with no repeated elements
		Node<E> holder = head;
		SLL<E> l = null;
		l.addFirst(head.data);
		while (holder.next != null) {
			if (!l.mem(holder.data)) {
				l.add(holder.data);
			}
			holder=holder.next;
		}
		return l;
		
	}
	public int countOccurence(E item) {
		int counter = 0;
		Node<E> iterator = head;
		while (iterator.next != null) {
			if (iterator.data == item) {
				counter++;
			}
			
		}
		return counter;
	}


	
	public SLL<Pair<E,Integer>> histogram() {
		SLL<Pair<E, Integer>> l = new SLL<>();
	    if (this.head == null) {
	    	return null;
	    }
	    else {
	    	SLL<E> members = noRepeats();
	    	Node<E> iterator = members.head;
	    	while(iterator.next != null) {
	    		Pair<E, Integer> inserter = new Pair(iterator.data,countOccurence(iterator.data));
	    		l.add(inserter);
	    	}
	    			
	    }
	   return l;
	}
		
	

	


	public static void main(String[] args) {
		   SLL<String> l = new SLL<>();

	          l.add("a");
	          l.add("b");
	          l.add("b");
	          l.add("b");
	          l.add("c");
	          l.add("k");
	          l.add("g");
	          l.add("g");
	          l.add("k");
	          l.add("g");
	          l.add("b");
	          l.add("b");
	          l.add("b");
	          System.out.println(l);
	          SLL<Pair<String,Integer>> l2 = l.histogram();
	          System.out.println(l2);

	        
	          }



}

