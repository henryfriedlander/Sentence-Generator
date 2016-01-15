package parts_of_speech;

public class Noun extends POS {
	public boolean singular;
	// boolean hasThe=false;
	
	public Noun(String word, String function,boolean singular){
		super(word, function);
		this.singular=singular;
	}
	
	private static String pos = "noun";

	@Override
	public boolean isPOS(String guess) {
		return guess.equals(pos) || super.isPOS(guess);
	}

	@Override
	public String pos() {
		return pos;
	}
}
