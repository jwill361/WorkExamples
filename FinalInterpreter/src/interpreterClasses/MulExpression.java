package interpreterClasses;

public class MulExpression extends BinaryExpression {

	public MulExpression(Expression op1, Expression op2) {
		super(op1, op2);
	}

	@Override
	public int getValue() {
		return getLeft().getValue() * getRight().getValue();
	}

}
