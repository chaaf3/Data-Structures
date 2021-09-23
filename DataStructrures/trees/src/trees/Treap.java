package trees;

import java.util.Random;
import java.util.Stack;

import trees.BTree.Node;

public class Treap<E extends Comparable<E>> {
	// Data fields for Treap
	private Random priorityGenerator;
	private Node<E> root;
	private static class Node<E> {
		public E data; // key for the search
		public int priority; // random heap priority
		public Node <E> left;
		public Node <E> right;
		public Node(E data, int priorityGenerator) {
			if (data == null) {
				throw new NullPointerException("insert data");
			}
			this.data = data;
			this.priority = priorityGenerator;
			left = null;
			right = null;
		}
		public Node<E> rotateRight() {
			if (this.left == null) {
				throw new NullPointerException("can't rotate right");
			}
			Node<E> holder = this.left;
			this.left = holder.right;
			holder.right = this;
			return holder;
		}
		public Node<E> rotateLeft() {
			if (this.right == null) {
				throw new NullPointerException("can't rotate Left");
			}
			Node<E> holder = this.right;
			this.right =  holder.left;
			holder.left = this;
			return holder;
		}
		public String toString() {
			//(key = 1, priority = 84)
			return "(key = "+ data + ", priority = " + priority + ")";
		}
	}
	public Treap() {
		this.priorityGenerator = new Random();
		// TODO Auto-generated constructor stub
	}
	public Treap(long seed) {
		this.priorityGenerator = new Random(seed);
	}
	public boolean add(E key, int priority) {
		Node<E> keyHolder = new Node<E>(key, priority);
		Stack<Node<E>> stack = new Stack<Node<E>>();
		//pointer to the parent of the node in question so that I can rotate
		if (root == null) {
			root = keyHolder;
		}
		Node<E> parent = root;
		if (key.compareTo(root.data) == 0) {
			return false;
		}
		boolean bool = addHelper (keyHolder,parent, stack);
//		while (stack.size() != 0) {
//			System.out.print(stack.pop().data + " ");
//		}
//		System.out.println("");
		bubbleUp(keyHolder, parent, stack);
		return bool;
	}
	public boolean add(E key) {
		
		Node<E> keyHolder = new Node<E>(key, priorityGenerator.nextInt());
		Stack<Node<E>> stack = new Stack<Node<E>>();
		//pointer to the parent of the node in question so that I can rotate
		if (root == null) {
			root = keyHolder;
		}
		Node<E> parent = root;
		if (key.compareTo(root.data) == 0) {
			return false;
		}
		boolean bool = addHelper (keyHolder,parent, stack);
//		while (stack.size() != 0) {
//			System.out.print(stack.pop().data + " ");
//		}
//		System.out.println("");
		bubbleUp(keyHolder, parent, stack);
		return bool;
	}
		
	public void bubbleUp(Node<E> keyHolder, Node<E> parent, Stack<Node<E>> stack) {
		Node<E> grandparent;
		while (! stack.isEmpty()) {
			parent = stack.pop();
			if (! stack.isEmpty()) {
				grandparent = stack.peek();
				if (keyHolder.priority < parent.priority) {
					break;
				}
				else {
					if (parent.left != null) {
					if (parent.left.data.compareTo(keyHolder.data) == 0) {
						if (grandparent.right == parent) {
							grandparent.right = parent.rotateRight();
						}
						else {
							grandparent.left = parent.rotateRight();
						}
						
					}
					}
					if (parent.right != null) {
						if (parent.right.data.compareTo(keyHolder.data) == 0) {
							if (grandparent.left == parent) {
								grandparent.left = parent.rotateLeft();
							}
							else {
								grandparent.right = parent.rotateLeft();
							}
						}
					}
				}
			}
			else {
				if (keyHolder.priority < parent.priority) {
					break;
				}
				else {
					if (parent.left != null) {
					if (parent.left.data.compareTo(keyHolder.data) == 0) {
						root = parent.rotateRight();
						
					}
					}
					if (parent.right != null) {
						if (parent.right.data.compareTo(keyHolder.data) == 0) {
							root = parent.rotateLeft();
							}
						}
					}
				}
				
			}

		}
	public boolean addHelper(Node<E> keyHolder, Node<E> parent, Stack<Node<E>> stack) {
		
		
		if (keyHolder.data.compareTo(parent.data) > 0) {
			if (parent.right == null) {
				stack.push(parent);
				parent.right = keyHolder;
				stack.push(keyHolder);
				return true;
			}
			stack.push(parent);
			return addHelper(keyHolder, parent.right, stack);
		}
		if (keyHolder.data.compareTo(parent.data) < 0) {
			if (parent.left == null) {
				stack.push(parent);
				parent.left = keyHolder;
				stack.push(keyHolder);
				return true;
			}	
			stack.push(parent);
			return addHelper(keyHolder, parent.left, stack);
		}
		return false;
		
	}
	public boolean find(E key) {
		if (root == null) {
			return false;
		}
		if (findHelper(root, key)) {
			return true;
		}
		return findHelper(root, key);
	}
	public boolean findHelper(Node<E> current, E key) {
		if (current == null) {
			return false;
		}
		if (current.data.compareTo(key) == 0) {
			return true;
		}
		if (findHelper(current.left, key) || findHelper (current.right, key)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean delete (E key) {
		if (!find(key)) {
			return false;
		}
		Node<E> parent = null;
		Node<E> keyHolder = root;
			
		while (true) {
			if (keyHolder.data.compareTo(key) == 0) {
					break;
			}
			else if (keyHolder.data.compareTo(key) > 0) {
				parent = keyHolder;
				keyHolder = keyHolder.left;
			}
			else {
				parent = keyHolder;
				keyHolder = keyHolder.right;
			}
		}
		if (keyHolder == root) {
				
				if (keyHolder.right == null && keyHolder.left == null) {
					root = null;
					return true;
				}
				else if (keyHolder.right == null && keyHolder.left != null) {
					root = keyHolder.rotateRight();
				}
				else if (keyHolder.right != null && keyHolder.left == null) {
					root = keyHolder.rotateLeft();
				}
				else if (keyHolder.right != null && keyHolder.left != null) {
					if (keyHolder.right.priority >= keyHolder.left.priority) {
						root = keyHolder.rotateLeft();
					}
					else {
						root = keyHolder.rotateRight();
					}
				}
				parent = root;
					
			}
		
		while (true) {
			if (parent.right == keyHolder) {
				
			if (keyHolder.right == null && keyHolder.left == null) {
				parent.right = null;
				return true;
			}
			else if (keyHolder.right == null && keyHolder.left != null) {
				parent.right = keyHolder.rotateRight();
			}
			else if (keyHolder.right != null && keyHolder.left == null) {
				parent.right = keyHolder.rotateLeft();
			}
			else if (keyHolder.right != null && keyHolder.left != null) {
				if (keyHolder.right.priority >= keyHolder.left.priority) {
					parent.right = keyHolder.rotateLeft();
				}
				else {
					parent.right = keyHolder.rotateRight();
				}
			}
			parent = parent.right;
				
		}
			else if (parent.left == keyHolder) {
				
				if (keyHolder.right == null && keyHolder.left == null) {
					parent.left = null;
					return true;
				}
				else if (keyHolder.right == null && keyHolder.left != null) {
					parent.left = keyHolder.rotateRight();
				}
				else if (keyHolder.right != null && keyHolder.left == null) {
					parent.left = keyHolder.rotateLeft();
				}
				else if (keyHolder.right != null && keyHolder.left != null) {
					if (keyHolder.right.priority >= keyHolder.left.priority) {
						parent.left = keyHolder.rotateLeft();
					}
					else {
						parent.left = keyHolder.rotateRight();
					}
				}
				parent = parent.left;
					
			}
		}
		
		
		}
		
		// copy and paste with a pointer to the root for that case
	
	private StringBuilder toString(Node<E> current, int level) {
		 StringBuilder r = new StringBuilder();
		 
		 for (int i=0; i<level; i++) {
			 r.append("  ");
		 }
		 if (current==null) {
			 r.append("null\n");
			 return r;
		 }
		 r.append(current.toString() + "\n");
		 r.append(toString(current.left,level+1));
		 r.append(toString(current.right,level+1));
		 return r;
		
	}
	public String toString() {
		return toString(root,0).toString();
	}
	
	public static void main(String[] args) {
	}
}
