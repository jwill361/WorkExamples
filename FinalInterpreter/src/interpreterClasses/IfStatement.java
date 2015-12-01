package interpreterClasses;

public class IfStatement implements Statement {

	private BooleanExpression expr;

	private LineNumber line;

	public IfStatement(BooleanExpression expr, LineNumber line) {
		this.expr = expr;
		this.line = line;
	}

	public void execute() {
		if (expr.getValue()) {
			Program.isBranchStatement(line.getValue());
		}
	}

}
