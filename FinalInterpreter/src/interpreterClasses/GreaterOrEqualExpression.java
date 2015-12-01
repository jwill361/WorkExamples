package interpreterClasses;

public class GreaterOrEqualExpression extends BooleanExpression {

	public GreaterOrEqualExpression(Expression op1, Expression op2) {
		super(op1, op2);
	}

	@Override
	public boolean getValue() {
		return getLeft().getValue() >= getRight().getValue();
	}

}
