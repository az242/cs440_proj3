

public class Coord {
	private int x;
	private int y;
	public Coord(String coord){
		this.x = Integer.parseInt(coord.split(",")[0]);
		this.y = Integer.parseInt(coord.split(",")[1]);
	}
	public Coord(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return x + "," + y;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Coord))
			return false;
		Coord temp = (Coord) obj;
		if (x == temp.getX() && y == temp.getY())
			return true;
		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = 1;
		hashCode = ((31 * hashCode) + x);
		hashCode = ((31 * hashCode) + y);
		return hashCode;
	}
}
