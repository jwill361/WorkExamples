package interpreterClasses;

public abstract class BinaryExpression implements Expression {
	enum ArithmeticOperator {
		add, sub, mul, div
	}

	private Expression left;

	private Expression right;

	public BinaryExpression(Expression left, Expression right) {
		this.left = left;
		this.right = right;
	}

	protected Expression getLeft() {
		return left;
	}

	protected Expression getRight() {
		return right;
	}

}
