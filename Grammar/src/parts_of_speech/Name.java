package parts_of_speech;

public class Name extends Noun {
	
	public Name(String word, String function,boolean singular){
		super(word,function, singular);
	}

	@Override
	public boolean isPOS(String guess) {
		return guess.equals("name") || super.isPOS(guess);
	}

	@Override
	public String pos() {
		return "name";
	}
}
