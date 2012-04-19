package global;

public class ID {
	
	private Line line;
	private char section;
	private int unit;
	
	public ID (Line l, char s, int u) {
		this.line = l;
		this.section = s;
		this.unit = u;
	}
	
	public Line getLine() {
		return line;
	}
	
	public char getSection() {
		return section;
	}
	
	public int getUnit() {
		return unit;
	}
	
	public String toString() {
		return line + "_" + section + "_" + unit;
	}
}
