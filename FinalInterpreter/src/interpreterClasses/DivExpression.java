package interpreterClasses;

public class DivExpression extends BinaryExpression {

	public DivExpression(Expression op1, Expression op2) {
		super(op1, op2);
	}

	@Override
	public int getValue() {
		return getLeft().getValue() / getRight().getValue();
	}

}
