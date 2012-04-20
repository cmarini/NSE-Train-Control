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
	
	public boolean equals(ID thatID)
	{
		if (this.line == thatID.getLine() &&
			this.section == thatID.getSection() &&
			this.unit == thatID.getUnit())
		{
			return true;
		}
		return false;
	}
	
	public String toString() {
		return line + "_" + section + "_" + unit;
	}
}
