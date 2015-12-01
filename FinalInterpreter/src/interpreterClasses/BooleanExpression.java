package interpreterClasses;

public abstract class BooleanExpression {

	enum RelativeOperator {
		LE, LT, GT, GE, EQ, NE
	};

	private Expression left;

	private Expression right;

	public BooleanExpression(Expression left, Expression right) {
		this.left = left;
		this.right = right;
	}

	protected Expression getLeft() {
		return left;
	}

	protected Expression getRight() {
		return right;
	}

	public abstract boolean getValue();
}
