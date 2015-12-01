package interpreterClasses;

public class Id implements Expression {
	private char ch;

	public Id(char ch) {
		this.ch = ch;
	}

	public int getValue() {
		return Memory.fetch(ch);
	}

	public char getChar() {
		return ch;
	}
}
