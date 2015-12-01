package interpreterClasses;

import java.io.FileNotFoundException;

public class Interpreter {

	public static void main(String[] args) {
		try {
			Parser p = new Parser("Test4.txt");
			Program prog = p.parse();
			prog.execute();
		} catch (FileNotFoundException e) {
			System.out.println("source file not found");
		} catch (ParserException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println("unknown error - terminating");
		}
	}
}
