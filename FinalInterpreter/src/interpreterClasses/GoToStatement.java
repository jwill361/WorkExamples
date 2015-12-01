package interpreterClasses;

public class GoToStatement implements Statement {

	private LineNumber line;

	public GoToStatement(LineNumber line) {
		this.line = line;
	}

	public void execute() {

		Program.isBranchStatement(line.getValue());
	}

}
