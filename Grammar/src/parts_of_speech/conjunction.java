package parts_of_speech;


public class conjunction extends POS{
	public conjunction(String word, String function){
		super(word,function);
	}
	
	@Override
	public boolean isPOS(String guess) {
		return guess.equals("conjunction") || super.isPOS(guess);
	}

	@Override
	public String pos() {
		return "conjunction";
	}
}
