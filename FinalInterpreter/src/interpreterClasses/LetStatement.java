package interpreterClasses;

public class LetStatement implements Statement {
	private Id var;

	private Expression expr;

	public LetStatement(Id var, Expression expr) {
		this.var = var;
		this.expr = expr;
	}

	public void execute() {

		Memory.store(var.getChar(), expr.getValue());
	}
}
