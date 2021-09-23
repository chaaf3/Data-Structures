package DLL;

public class SLL<E> {

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
	
	/** 
	 * Removes and returns the first item from the list
	 *  
	 * @return The element that was removed
	 * @throws IllegalStateException is the list is empty
	 */
	public E removeFirst() {
		if (head==null) {
			throw new IllegalStateException("removeFirst: list is empty");
		}
		E temp = head.data;
		head = head.next;
		size--;
		return temp;
	}

	/** 
	 * Removes and returns the last item from the list
	 *  
	 * @return The element that was removed
	 * @throws IllegalStateException is the list is empty
	 */
	public E removeLast() {
		 if (head==null) { // list is empty
			 throw new IllegalStateException("removeLast: list is empty");
		 }
		 if (head.next==null) { // list is a singleton list
			 E temp = head.data;
			 head = null;
			 size--;
			 return temp;
		 }
		 // list has two or more elements
		 Node<E> current = head;
		 
		 while (current.next.next!=null) {
			 current = current.next;
		 }
		 E temp = current.next.data;
		 current.next = null;
		 size--;
		 return temp;
		
	}
	
	/** 
	 * Removes and returns the item located at index index from the list
	 *  
	 * @param index Index of the item to be removed
	 * @return The element that was removed
	 * @throws IllegalArgumentException if the index does not exist
	 */
	public E remove(int index) {
		if (index<0 || index>size) {
			throw new IllegalArgumentException("remove: index out of bounds");
		}
		if (head==null) {
			throw new IllegalStateException("remove: list is empty");
		}
		// list is non-empty and index is in range
		if (index==0) {
			return removeFirst();
		}
		// list is non-empty, the index is in range and larger than 0
		Node<E> temp =  head;
		for(int i = 1; i < index; i++) {
			temp = temp.next;
		}
		temp.next = temp.next.next;
		// TODO
		size--;
		return temp.data;
		
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

	public static void main(String[] args) {
		SLL<String> l = new SLL<String>();

		l.add("0");
		l.add("1");
		l.add("2");
		l.add("3");
		l.remove(2);
//		
//		l.addFirst("hey");
//		l.addFirst("howdy");
//		l.add("bye");
//		l.add("seeya",4);	
//		System.out.println(l);
//
//		l.removeLast();
		System.out.println(l);
		
		}



}
