import java.util.Map;
import java.util.TreeMap;

//This simple Program illustrates two ways to count letters, special characters, and white spaces in a String.

public class Random {

	public static void main(String[] args) {
		String s = "aaaaa____----";
		System.out.println("Using an Array");
		countLetters(s);
		System.out.println("Using Maps");
		mapLetters(s);

	}
	//This method uses a Treemap in order to sort the characters in the string after being put in the map.
	private static void mapLetters(String s) {
		assert(s != null);
		Map<Character, Integer> count = new TreeMap<>();
		for(char i : s.toCharArray()){
			if(!count.containsKey(i))
				count.put(i, 1);
			else
				count.put(i, count.get(i) + 1);
		}
		for(char j : count.keySet())
			System.out.println("Count for letter " + j + ": " + count.get(j));	
	}
	//This method stores the characters in an array
	private static void countLetters(String s) {
		assert(s != null);
		int[] counts = new int[126];
		for(int i = 0; i < s.length(); i++)
			counts[s.charAt(i)]++;
		for(int j = 0; j< counts.length; j++)
			if(counts[j] != 0)
				System.out.println("Count for letter " + (char)(j) + ": " + counts[j]);
		System.out.println();
	}

}
