package interpreterClasses;

public class PrintStatement implements Statement {
	private Id var;

	public PrintStatement(Id var) {
		this.var = var;
	}

	public void execute() {
		System.out.println(var.getValue());
	}
}
