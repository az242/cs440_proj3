

public class SG {
	Coord start, end;

	public SG(Coord start, Coord end) {
		this.start = start;
		this.end = end;
	}

	public String toString() {
		return "Start:" + start.toString() + " & End:" + end.toString();
	}

	public Coord getStart() {
		return start;
	}

	public Coord getEnd() {
		return end;
	}
}
