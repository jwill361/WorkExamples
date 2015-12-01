package interpreterClasses;

import java.util.ArrayList;

public class StatementList {
	private ArrayList<Statement> l;

	public StatementList() {
		l = new ArrayList<Statement>(999);
		l = getNullArrayList();
	}

	private ArrayList<Statement> getNullArrayList() {
		for (int i = 0; i < 999; i++)
			l.add(null);
		return l;
	}

	public int size() {
		return l.size();
	}

	public Statement get(int i) {
		return l.get(i);
	}

	public void add(int index, Statement s) {
		l.add(index, s);
	}

	public void execute(int s) {
		l.get(s).execute();
	}
}