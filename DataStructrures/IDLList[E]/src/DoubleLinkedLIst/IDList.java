package DoubleLinkedLIst;
import java.util.*;

import DLL.SLL;
import DLL.SLL.Node;

public class IDList<E> {

	public static class Node<E> {
		// data fields
		private E data;
		private Node<E> next;
		private Node<E> previous;

		Node(E elem) {
			this.data=elem;
			this.next=null;
			this.previous = null;
		}

		Node(E elem, Node<E> next, Node<E> previous) {
			this.data=elem;
			this.next=next;
			this.previous=previous;
		}

	}

	// Data fields
	private Node<E> head;
	private Node<E> tail;
	private ArrayList<Node<E>> indices;
	private int size;

	// Constructor
	public IDList() {
		head = null;
		tail = null;
		indices = new ArrayList<Node<E>>();
		size = 0;
	}
	public <indices> void makeNewDLL (E data) {
		Node<E> holder = new Node<E>(data);
		tail = holder;
		head = holder;
		holder.next=tail;
		holder.previous=head;
		size = 1;
		indices.add(0,holder);
	}

	// Methods
	public boolean add (int index, E elem) {
		if (this.head == null) {
			makeNewDLL(elem);
		}
		else if (index > size || index < 0) {
			throw new IndexOutOfBoundsException("Pice an index Out of bounds");
		}
		else if (index == size) {
		Node<E> reference = tail.previous;
		Node<E> holder = new Node<E>(tail.data);
		tail.data = elem;
		holder.next = tail;
		reference.next = holder;
		tail.previous = holder;
		holder.previous = reference;
		tail.data = elem;
		size++;
		indices.add(tail);
		indices.set(indices.size() - 2, holder);
		}
		else if (index == 0) {
			Node<E> holder = new Node<E>(head.data);
			Node<E> reference = head.next;
			head.data = elem;
			head.next = holder;
			holder.next = reference;
			reference.previous = holder;
			holder.previous = head;
			size++;
			indices.add(0,head);
			indices.set(1, holder);
		}
		else {
			Node<E> before = indices.get(index-1);
			Node<E> after = indices.get(index);
			Node<E> holder = new Node<E>(elem);
			before.next = holder;
			holder.next = after;
			after.previous = holder;
			holder.previous = before;
			indices.add(index, holder);
			size++;
		}
		return true;
	}

	public boolean append (E elem) {
		this.add(size, elem);
		return true;
	}
	public E get (int index) {
		if (index < 0 || index <= size) {
			throw new IllegalArgumentException("choose an index in bounds");
		}
		else {
		return indices.get(index).data;
		}
	}
	
	public E getHead () {
		if (head == null) {
			return null;
		}
		else {
			return head.data;
		}
	}
	public E getLast () {
		if (head == null) {
			return null;
		}
		else {
			return tail.data;
		}
	}
	public int size() {
		return size;
	}
	public E remove() {
		if (head==null) {
			throw new IllegalStateException("removeFirst: list is empty");
		}
		if (head.next == null) {
			size = 0;
			E temp = head.data;
			head = null;
			tail = null;
			indices.clear();
			return temp;
		}
		E temp = head.data;
		head = head.next;
		indices.remove(0);
		size--;
		return temp;
	}
	
	public E removeLast() {
		if (head==null) { // list is empty
			 throw new IllegalStateException("removeLast: list is empty");
		 }
		if (head.next == null) {
			size = 0;
			E temp = tail.data;
			tail = null;
			head = null;
			indices.clear();
			return temp;
		}
		E temp = tail.data;
		tail = head.previous;
		indices.remove(size - 1);
		size--;
		return temp;
	}
	
	public E removeAt(int index) {
		if (index<0 || index>size) {
			throw new IllegalArgumentException("remove: index out of bounds");
		}
		if (head==null) {
			throw new IllegalStateException("remove: list is empty");
		}
		// list is non-empty and index is in range
		if (index==0) {
			return remove();
		}
		if (index == (size -1)) {
			return removeLast();
		}
		else {
			Node<E> before = indices.get(index-1);
			Node<E> after = indices.get(index+1);
			Node<E> holder = new Node<E>(indices.get(index).data);
			before.next = after;
			after.previous = before;
			size --;
			indices.remove(index);
			return holder.data;
		}
	}
	
	public boolean remove (E elem) {
		int holder = -5;
		int placement = -1;
		for (int i = 0; i < size; i ++) {
			if (indices.get(i).data == elem) {
				holder = -10;
				placement = i;
				break;
			}
		}
		if (holder == -10) {
			Node<E> before = indices.get(placement-1);
			Node<E> after = indices.get(placement+1);
			before.next = after;
			after.previous = before;
			size--;
			indices.remove(placement);
		}
		return (holder == -10);
	}
	@Override
	public String toString() {
		String result ="[" ;
		Node<E> current = head;
		int i = 0;
		while (i < size) {
			result += " ";
			result += current.data.toString();
			current = current.next;
			i++;
		}
		result += " ]";

		return result;


	}



	public static void main(String[] args) {
		IDList<Integer> l = new IDList<Integer>();
		l.append(1);
		l.append(2);
		l.append(3);
		l.append(9);
		l.add(4, 7);
		l.append(5);
		System.out.print(l);
		
		
		}



}