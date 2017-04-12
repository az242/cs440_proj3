

public class Cell {
	private Coord coords;
	private char type;
	private double probability;
	private Coord contributer;
	public Coord getContributer() {
		return contributer;
	}

	public void setContributer(Coord contributer) {
		this.contributer = contributer;
	}

	public double getProbability() {
		return probability;
	}

	public void setProbability(double probability) {
		this.probability = probability;
	}

	public Cell(int x, int y, char type) {
		coords = new Coord(x, y);
		this.type = type;
		probability=0;
	}

	public char getType() {
		return type;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Cell))
			return false;
		Cell other = (Cell) obj;
		if (coords == null) {
			if (other.coords != null)
				return false;
		} else if (!coords.equals(other.getCoords()))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coords == null) ? 0 : coords.hashCode());
		return result;
	}

	public Coord getCoords() {
		return coords;
	}
}
