package parts_of_speech;


public class Verb extends POS {
	boolean singular;
	int person;
	int tense; //-1 is past, 0 is present, 1 is future
	public Verb(String word, int tense, String function, boolean singular, int person) {
		super(word,function);
		this.tense=tense;
		this.singular=singular;
		this.person=person;
	}
	
	private static String pos = "verb";
	@Override
	public boolean isPOS(String guess) {
		return guess.equals(pos) || super.isPOS(guess);
	}

	@Override
	public String pos() {
		return pos;
	}
}
