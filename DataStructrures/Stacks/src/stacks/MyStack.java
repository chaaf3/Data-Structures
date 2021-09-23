package stacks;


public class MyStack {
	public static class pairInt {
		private int x;
		private int y;
		
		public pairInt(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public int getX() {
			return this.x;
		}
		public int getY() {
			return this.y;
		}
		public void setX(int x) {
			this.x = x;
		}
		public void setY(int y) {
			this.y = y;
		}
		public boolean equals(pairInt p) {
			if (this.x == p.getX() && this.y == p.getY()) {
				return true;
			}
			else {
				return false;
			}
		}
		@Override
		public String toString() {
			StringBuilder result = new StringBuilder();

			result.append("[");
			result.append(this.getX());
			result.append(" ");
			result.append(this.getY());
			result.append("]");
			return result.toString();
		}
	}

	public static class Node {
		// Data fields
		private pairInt data;
		private Node next;
		
		public Node(pairInt data, Node next) {
			super();
			this.data = data;
			this.next = next;
		}
		
		public Node(pairInt data) {
			super();
			this.data = data;
			this.next = null;
		}
		
	}

    // Data fields
	private Node top;
	private int size;
	
	// Constructor
	MyStack() {
		top=null;
		size=0;				
	}
	
	// Methods
	
	public boolean push(pairInt i) {
		top = new Node(i,top);
		size++;
		return true;
	}
	public boolean push(int x, int y) {
		top = new Node(new pairInt(x, y), top);
		size++;
		return true;
	}
	
	public pairInt pop() {
		if (top==null) {
			throw new IllegalStateException("pop: empty stack");
		}
		pairInt temp = top.data;
		top = top.next;
		size--;
		return temp;		
	}
	
	public pairInt top() {
		if (top==null) {
			throw new IllegalStateException("top: empty stack");
		}
		return top.data;
	}
	
	public boolean isEmpty() {
		return top==null;
	}
	public int getSize() {
		return this.size;
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();

		result.append("[");
		while (!this.isEmpty()) {
			result.append(this.top().getX() + " ");
			result.append(this.top().getY() + ", ");
			this.pop();
			if (this.top == null) {
				break;
			}
		}
		result.append("]");

		return result.toString();


	}
	
	

public static void main(String[] args) {
	MyStack helper = new MyStack();
	helper.push(1, 2);
	helper.push(8, 4);
	helper.push(4, 4);
	System.out.print(helper.toString());
}
}

