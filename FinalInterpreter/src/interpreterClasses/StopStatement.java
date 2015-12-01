package interpreterClasses;

public class StopStatement implements Statement {

	public StopStatement() {
	}

	public void execute() {
		System.exit(0);
	}

}
