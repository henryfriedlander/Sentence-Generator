package parts_of_speech;


public class SubConj extends POS{
	Noun subject;
	public SubConj(String word,String function,Noun n){
		super(word,function);
		if(!function.equals("noun clause")){
			this.subject=n;
		}
			
	}
	
	private static String pos = "subordinating conjunction";
	
	@Override
	public boolean isPOS(String guess) {
		return guess.equals(pos) || super.isPOS(guess);
	}

	@Override
	public String pos() {
		return pos;
	}
}