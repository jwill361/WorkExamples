package interpreterClasses;

public class UnaryExpression implements Expression {

	private Expression op;

	public UnaryExpression(Expression op) {
		this.op = op;
	}

	@Override
	public int getValue() {
		// TODO Auto-generated method stub
		return op.getValue();
	}

}
