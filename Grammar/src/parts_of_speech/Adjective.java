package parts_of_speech;

public class Adjective extends POS{
	Noun subject;
	public Adjective(String word, Noun subject){
		super(word, "adjective");
		this.subject=subject;
	}
	
	private static String pos = "adjective";
	
	@Override
	public boolean isPOS(String guess) {
		return guess.equals(pos) || super.isPOS(guess);
	}

	@Override
	public String pos() {
		return pos;
	}

}
