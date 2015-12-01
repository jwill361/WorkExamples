package interpreterClasses;

public class LessThanExpression extends BooleanExpression {

	public LessThanExpression(Expression op1, Expression op2) {
		super(op1, op2);
	}

	@Override
	public boolean getValue() {
		// TODO Auto-generated method stub
		return getLeft().getValue() < getRight().getValue();
	}

}
