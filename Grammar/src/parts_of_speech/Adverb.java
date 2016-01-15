package parts_of_speech;

public class Adverb extends POS {
	POS subject;
	public Adverb(String word, Verb subject){
		super(word,"adverb");
		this.subject=subject;
	}
	public Adverb(String word, Adjective subject){
		super(word,"adverb");
		this.subject=subject;
	}
	public Adverb(String word, Adverb subject){
		super(word,"adverb");
		this.subject=subject;
	}
	
	private static String pos = "adverb";
	
	@Override
	public boolean isPOS(String guess) {
		return guess.equals(pos) || super.isPOS(guess);
	}

	@Override
	public String pos() {
		return pos;
	}
}
