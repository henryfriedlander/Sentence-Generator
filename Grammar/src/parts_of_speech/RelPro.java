package parts_of_speech;


public class RelPro extends POS{
	int indexOfAntecedent;
	public RelPro(String word, int indexOfAntecedent){
		super(word,"pronoun");
		this.indexOfAntecedent=indexOfAntecedent;
	}
	
	private static String pos = "relative pronoun";
	
	@Override
	public boolean isPOS(String guess) {
		return guess.equals(pos) || super.isPOS(guess);
	}

	@Override
	public String pos() {
		return pos;
	}
}
