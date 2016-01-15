package parts_of_speech;

import javax.swing.JLabel;


public abstract class POS {	
	public String word;
	public String function;
	
	public JLabel label;
	
	public abstract String pos();
	
	public POS(String word, String function){
		// Subclasses set their part of speech
		// this.pos=pos;
		this.word=word;
		this.function=function;
	}

	public boolean isPOS(String guess) {
		return false;
	}
}
