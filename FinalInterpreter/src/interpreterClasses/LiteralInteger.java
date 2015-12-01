package interpreterClasses;

public class LiteralInteger implements Expression {
	private int value;

	public LiteralInteger(int value) {
		this.value = value;
	}

	@Override
	public int getValue() {
		return value;
	}
}
