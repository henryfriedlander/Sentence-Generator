import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/*
 * make it make some degree of sense
 * ask part of speech
 * graphics
 * text to speech
 * different language
 * change number of verbs
 * I have got to make the PrintToScreen class be able to take in a POS instead of the arrrays.
 * Also I have to make it so that you can have to put in more specific answers to the different questions being asked
 * for example you should be able to type in verb in a linking verb context
 * Then I NEED TO MAKE DEPENDENT CLAUSES PART OF THE GENERATOR
 * relative clauses that have a subordinating conjunction that if you 
 * I need to make it so that the program first assigns all of the base words to pos things so that all of the other indices wont get fucked up
 * I think that I have to loop through all of the places and then add one to their placement
 * Then I need to formulate questions for all of the different POS attributes
 */

public class RandomScentenceGenerator  {
    public static void main(String[] args) throws IOException{
	    PrintToScreen pts= new PrintToScreen(AddWords.converter(GetSentence.getRandStructure()));
	    pts.run(pts); 
    }
}