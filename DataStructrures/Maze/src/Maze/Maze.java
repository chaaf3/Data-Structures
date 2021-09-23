package Maze;

import java.util.*;


/**
 * Class that solves maze problems with backtracking.
 * @author Koffman and Wolfgang
 **/
public class Maze implements GridColors {
    /** The maze */
	
    private TwoDimGrid maze;
	
    public class MyStack {

    	public class Node {
    		// Data fields
    		private PairInt data;
    		private Node next;
    		
    		public Node(PairInt data, Node next) {
    			super();
    			this.data = data;
    			this.next = next;
    		}
    		
    		public Node(PairInt data) {
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
    	
    	public boolean push(PairInt i) {
    		top = new Node(i,top);
    		size++;
    		return true;
    	}
    	public boolean push(int x, int y) {
    		top = new Node(new PairInt(x, y), top);
    		size++;
    		return true;
    	}
    	
    	public PairInt pop() {
    		if (top==null) {
    			throw new IllegalStateException("pop: empty stack");
    		}
    		PairInt temp = top.data;
    		top = top.next;
    		size--;
    		return temp;		
    	}
    	
    	public PairInt top() {
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
    }
    	
    	

    public Maze(TwoDimGrid m) {
        maze = m;
    }

    /** Wrapper method. */
    public boolean findMazePath() {
    	return findMazePath(0, 0);
    }
    public boolean findMazePath(int x, int y) {
    	// base cases = check if current place is invalid in any way and return false
    	//recursive call in if 
    	if (x < 0 ||x > maze.getNCols()-1 || y < 0 || y > maze.getNRows()-1|| maze.getColor(x, y) != NON_BACKGROUND) {
    		return false;
    	}
    	else if (x == maze.getNCols()-1 && y == maze.getNRows()-1) {
    		maze.recolor(x, y, PATH);
    		return true;
    	}
    	else {
    		maze.recolor(x, y, TEMPORARY);
	    	if (findMazePath(x, y+1) || findMazePath(x, y-1) || findMazePath(x+1, y) || findMazePath(x-1, y)) {
	    		maze.recolor(x, y, PATH);
	    		return true;
	    	}
    	}
    	return false;
    }
//    	MyStack paths = new MyStack();
//    	MyStack fixLater = new MyStack();
//    	if (x == maze.getNCols() && y == maze.getNRows()) {
//    		paths.push(x, y);
//    		while (!paths.isEmpty()) {
//    			pairInt holder = new pairInt(paths.top().getX(), paths.top().getY());
//    			maze.recolor(holder.getX(), holder.getY(), PATH);
//    			paths.pop();
//    		}
//    		while (!fixLater.isEmpty()) {
//    			pairInt holder = new pairInt(fixLater.top().getX(), fixLater.top().getY());
//    			maze.recolor(holder.getX(), holder.getY(), NON_BACKGROUND);
//    			fixLater.pop();
//    		}
//    		return true;
//    	}
//	
//    	else if (y+1 < maze.getNRows()-1 && y+1 > 0 && (maze.getColor(x, y+1).equals(NON_BACKGROUND))) {
//    	   paths.push(x, y);
//    	   maze.recolor(x, y, TEMPORARY);
//    	   findMazePath(x, y+1);
//       }
//       else if (x+1 < maze.getNCols()-1 && x+1 > 0 && (maze.getColor(x+1, y).equals(NON_BACKGROUND))) {
//    	   paths.push(x, y);
//    	   maze.recolor(x, y, TEMPORARY);
//    	   findMazePath(x+1, y);
//    	   
//       }
//       else if (y-1 < maze.getNRows()-1 && y-1 > 0 && (maze.getColor(x, y-1).equals(NON_BACKGROUND))) {
//    	   paths.push(x, y);
//    	   maze.recolor(x, y, TEMPORARY);
//    	   findMazePath(x, y-1);
//       }
//       else if (x-1 < maze.getNCols()-1 && x-1 > 0 && (maze.getColor(x-1, y).equals(NON_BACKGROUND))) {
//    	   paths.push(x, y);
//    	   maze.recolor(x,  y, TEMPORARY);
//    	   findMazePath(x-1, y);
//       }
//       else {
//    	   maze.recolor(x,  y,  BACKGROUND);
//    	   fixLater.push(x, y);
//    	   pairInt hold = paths.pop();
//    	   findMazePath(hold.getX(), hold.getY());
//    	   
//       }
//    	return false;
//    }
     
    

    /**
     * Attempts to find a path through point (x, y).
     * @pre Possible path cells are in BACKGROUND color;
     *      barrier cells are in ABNORMAL color.
     * @post If a path is found, all cells on it are set to the
     *       PATH color; all cells that were visited but are
     *       not on the path are in the TEMPORARY color.
     * @param x The x-coordinate of current point
     * @param y The y-coordinate of current point
     * @return If a path through (x, y) is found, true;
     *         otherwise, false
     */

    // ADD METHOD FOR PROBLEM 2 HERE
    public void findMazePathStackBased(int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace) {
    	if (x < 0 ||x > maze.getNCols()-1 || y < 0 || y > maze.getNRows()-1|| maze.getColor(x, y) != NON_BACKGROUND) {
    		return;
    	}
    	else if (x == maze.getNCols()-1 && y == maze.getNRows()-1) {
    		trace.push(new PairInt(x, y));
    		ArrayList<PairInt> holder = new ArrayList <> ();
    		holder.addAll(trace);
    		result.add(holder);
    		trace.pop();
    	}
    	else {
    		maze.recolor(x,  y, PATH);
    		trace.push(new PairInt(x, y));
    		findMazePathStackBased(x+1, y, result, trace);
    		findMazePathStackBased(x-1, y, result, trace);
    		findMazePathStackBased(x, y+1, result, trace);
    		findMazePathStackBased(x, y-1, result, trace);
    		trace.pop();
    		maze.recolor(x,y, NON_BACKGROUND);
    	}

    	}
    
    public ArrayList < ArrayList < PairInt >> findAllMazePaths ( int x , int y) {
    	ArrayList < ArrayList < PairInt >> result = new ArrayList < >();
    	Stack < PairInt > trace = new Stack < >();
    	findMazePathStackBased (0 ,0 , result , trace );
    	return result ;
    	}
    
    
    
    
    
    // ADD METHOD FOR PROBLEM 3 HERE
    public ArrayList<PairInt> findMazePathMin(int x, int y) {
    	ArrayList<ArrayList<PairInt>> answer = findAllMazePaths(0,0);
    	int shortest = 1;
    	for (int i = 1; i < answer.size(); i++) {
    		if (answer.get(i).size() < answer.get(shortest).size()) {
    			shortest = i;
    		}
    	}
    	System.out.print(answer);
    	return answer.get(shortest);
    }
    	

    /*<exercise chapter="5" section="6" type="programming" number="2">*/
    public void resetTemp() {
        maze.recolor(TEMPORARY, BACKGROUND);
    }
    /*</exercise>*/

    /*<exercise chapter="5" section="6" type="programming" number="3">*/
    public void restore() {
        resetTemp();
        maze.recolor(PATH, BACKGROUND);
        maze.recolor(NON_BACKGROUND, BACKGROUND);
    }
    /*</exercise>*/
}
/*</listing>*/

