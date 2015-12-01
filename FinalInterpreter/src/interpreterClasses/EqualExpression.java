package interpreterClasses;

public class EqualExpression extends BooleanExpression {

	public EqualExpression(Expression op1, Expression op2) {
		super(op1, op2);
	}

	@Override
	public boolean getValue() {
		return getLeft().getValue() == getRight().getValue();
	}

}
