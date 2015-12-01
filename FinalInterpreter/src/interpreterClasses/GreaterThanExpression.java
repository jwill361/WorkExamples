package interpreterClasses;

public class GreaterThanExpression extends BooleanExpression {

	public GreaterThanExpression(Expression op1, Expression op2) {
		super(op1, op2);
	}

	@Override
	public boolean getValue() {
		// TODO Auto-generated method stub
		return getLeft().getValue() > getRight().getValue();
	}

}
