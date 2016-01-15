import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import parts_of_speech.*;


public class BrownSentenceGenerator {

	public static void main(String[] args) throws IOException {
	    PrintToScreen pts= new PrintToScreen(readFile("brown/cn22"));
	    pts.run(pts); 
	}
	
	public static POS[] readFile(String filename) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader(filename));
		
		while (true) {
			String line = f.readLine();
			if (line == null) {
				System.out.println("Ran out of lines in the file...");
				break;
			}
			
			line = line.trim(); // pull off optional leading tab, and trailing '\n' character
			String[] taggedWords = line.split(" ");
			if (taggedWords.length > 5 && Math.random() < .1) {
				POS[] result = new POS[taggedWords.length];
				for (int i = 0; i < taggedWords.length; i++) {
					String taggedWord = taggedWords[i];
					
					// taggedWord might equal something like "they/pass"
					String[] split = taggedWord.split("/");
					String word = split[0];
					String posStr = split[1];
					
					result[i] = new Name(word, "animal", true);
				}
				return result;
			}
		}
		
		System.out.println("what happeened?");
		return null;
	}

}
