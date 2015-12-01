package interpreterClasses;

public class AddExpression extends BinaryExpression {

	public AddExpression(Expression op1, Expression op2) {
		super(op1, op2);
	}

	@Override
	public int getValue() {
		return getLeft().getValue() + getRight().getValue();
	}

}
