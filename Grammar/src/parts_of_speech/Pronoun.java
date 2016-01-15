package parts_of_speech;


public class Pronoun extends POS{
	int person;
	boolean number;
	public Pronoun(String word, boolean number,int person) {
		super(word,"pronoun");
		this.person=person;
		this.number=number;
		// TODO Auto-generated constructor stub
	}
	
	private static String pos = "pronoun";
	
	@Override
	public boolean isPOS(String guess) {
		return guess.equals(pos) || super.isPOS(guess);
	}

	@Override
	public String pos() {
		return pos;
	}
}
