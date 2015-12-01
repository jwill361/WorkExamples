package interpreterClasses;

public class SubExpression extends BinaryExpression {

	public SubExpression(Expression op1, Expression op2) {
		super(op1, op2);
	}

	@Override
	public int getValue() {
		return getLeft().getValue() - getRight().getValue();
	}

}
