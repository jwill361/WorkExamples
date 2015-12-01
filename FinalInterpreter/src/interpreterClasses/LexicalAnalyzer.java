package interpreterClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LexicalAnalyzer {
	private List<String> lexemes;

	public LexicalAnalyzer(String fileName) throws FileNotFoundException {
		lexemes = new ArrayList<String>();
		Scanner scan = new Scanner(new File(fileName));
		while (scan.hasNext())
			lexemes.add(scan.next());
		scan.close();
	}

	public String getLookaheadToken() {
		String token;
		if (lexemes.isEmpty())
			token = "$";
		else
			token = lexemes.get(0);
		return token;
	}

	public String getToken() {
		String token;
		if (lexemes.isEmpty())
			token = "$";
		else
			token = lexemes.remove(0);
		return token;
	}

	public boolean hasMoreTokens() {
		return !lexemes.isEmpty();
	}

}
