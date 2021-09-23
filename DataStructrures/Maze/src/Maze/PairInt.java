package Maze;


public class PairInt {
	private int x;
	private int y;
	
	public PairInt(int x, int y) {
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
	public boolean equals(PairInt p) {
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

		result.append("(");
		result.append(this.getX());
		result.append(" ");
		result.append(this.getY());
		result.append(")");
		return result.toString();
	}
}