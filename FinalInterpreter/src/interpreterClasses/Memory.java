package interpreterClasses;

public class Memory {
	private static int[] mem = new int[26];

	public static void store(char ch, int value) {
		mem[ch - 'A'] = value;
	}

	public static int fetch(char ch) {
		return mem[ch - 'A'];
	}
}
