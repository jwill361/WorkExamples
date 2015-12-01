package interpreterClasses;

public class Program {

	private StatementList l;

	private static int counter;

	private static boolean branch;

	private boolean flag;

	public Program(StatementList l) {
		this.l = l;
	}

	private void getNext() {
		if (branch == false) {
			counter = counter + 1;
			while (l.get(counter) == null) {
				l.get(counter);
				counter++;
			}
		} else
			branch = false;
	}

	public static void isBranchStatement(int line) {
		counter = line;
		branch = true;
	}

	public void execute() {
		flag = false;
		do {
			getNext();
			if (l.get(counter) != null)
			{
				l.execute(counter);
			}
		} while (!flag);
	}
}