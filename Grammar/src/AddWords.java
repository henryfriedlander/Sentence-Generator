import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import parts_of_speech.*;

/*
 * I need to go from the strings of the sentence structure to the different POS classes
 * I think that I should only have to loop through the structures loop only once and be able to parse all of the relavent information
 * In the structures I could have the last two characters signify some atributes about its postitioning in the sentence
 * Subject: "Sb"
 * Main Verb: MV
 * Any modifier parts of speech should have the last character be the index its antecedent or for adjectives or adverbs what they modify be in that place
 * for example: "adverb 6"
 * 
 */
public class AddWords {
	public static void main(String[]args) throws IOException{
		print(converter(GetSentence.getRandStructure()));
	}
	
	public static void print(POS[] pos){
		int i=0;
		try{
		System.out.print("{");
		for(i=0;i<pos.length;i++){
			if(pos[i]==null){
				System.out.print("null");
			}else{
				System.out.print(pos[i].word);
			}
			if(i!=pos.length-1){
				System.out.print(", ");
			}
		}
		System.out.println("}");
		}
		catch(Exception e){
			System.out.println(e+" i : "+i);
			pos[i].word=pos[i].pos();
			System.exit(1);
		}
	}
	
	public static POS[]converter(String[]POS) throws IOException{
		POS[] speech = new POS[POS.length];
		boolean isVerbPlural = randBool();
		int[] indexToSkip = new int[POS.length];
		int curITS = 0;
		int tense=(int)(Math.random()*3)-1;
		for(int i=0;i<POS.length;i++){
			Verb v=null;
			String cur=POS[i].substring(0, POS[i].length()-2);
			
			if(cur.equals("name")){
				String f=POS[i].substring(POS[i].length()-2,POS[i].length());
				if(f.equals("DO")){
					Name n=new Name(randName(),"Direct Object",false);
					speech[i]=n;
				}
				if(f.equals("IO")){
					Name n=new Name(randName(),"Indirect Object",false);
					speech[i]=n;
				}
				
			}
			if(cur.equals("adverb")){
				int rand=(int)(Math.random()*3);
				if (rand==0){
					Adverb a=new Adverb("fast",(Verb)speech[i-1]);
					speech[i]=a;
				}
				if(rand==1){
					Adverb a=new Adverb("furiously",(Verb)speech[i-1]);
					speech[i]=a;
				}
				else{
					Adverb a=new Adverb("easily",(Verb)speech[i-1]);
					speech[i]=a;
				}
			}
			if(cur.equals("noun") || cur.equals("subject")){
				String f=POS[i].substring(POS[i].length()-2,POS[i].length());
				System.out.println("f "+f);
				
				String s=randNoun();
				Noun n;
				//System.out.println("s "+s);
				if(f.equals("DO")){
					n=new Noun(s,"Direct Object", !isVerbPlural);
				}
				else if(f.equals("Sb")){
					n=new Noun(s,"subject", !isVerbPlural);
				}
				else if(f.equals("NS")){
					n=new Noun(s,"subject", !isVerbPlural);
				}
				else{
					n=new Noun(s,"Indirect Object", !isVerbPlural);
				}
				speech[i]=n;
			}
			//for verbs I need to implement the sequence of tenses I cant just keep the same tense for all of the different verbs
			//need check if its a main verb
			if(cur.equals("action verb")){
				System.out.println("HERE");
				v=new Verb(RandActionVerb(),tense,"action verb",isVerbPlural,3);
				speech[i]=v;
			}else{
				System.out.println(cur+" is not action verb");
			}
			if(cur.equals("linking verb")){
				v=new Verb(randLkingVerb(),tense,"linking verb",true,3);
				speech[i]=v;
			}
			if(cur.equals("io verb")){
				System.out.println("IO VERB");
				v=new Verb(randIOVerb(),tense,"action verb",true,3);
				speech[i]=v;
			}
			if(cur.equals("conjunction")){
				int rand=(int)(Math.random()*2);
				if(rand==0){
					conjunction conj=new conjunction("or","correlitive");
					speech[i]=conj;
				}else{
					conjunction conj=new conjunction("and","correlitive");
					speech[i]=conj;
				}
			}
			if(cur.equals("adjective")){
				int index = Integer.parseInt(POS[i].substring(POS[i].length()-1));
				System.out.println("index "+index+" i : "+i);
					String f=POS[i].substring(POS[i].length()-1,POS[i].length());
					System.out.println("f "+f);
					Noun n=null;
					if(f.equals("DO")){
						n=new Noun(randNoun(),"Direct Object", !isVerbPlural);
					}
					if(f.equals("Sb")){
						n=new Noun(randNoun(),"subject", !isVerbPlural);
					}
					if(f.equals("IO")){
						n=new Noun(randNoun(),"Direct Object", !isVerbPlural);
					}
					speech[index]=n;
					Adjective a = new Adjective(randAdj(),(Noun) speech[index]);
					speech[i]=a;
					indexToSkip[curITS]=index;
					curITS++;
			}
			
			if(POS[i].substring(0, POS[i].length()-2).equals("subordinate conjunction")){
				if(POS[i].charAt(POS[i].length()-1)=='N'){
					SubConj s=new SubConj(randNSubConj(),"noun clause",null);
					speech[i]=s;
				}
				if(POS[i].charAt(POS[i].length()-1)=='V'){
					SubConj s=new SubConj(randVSubConj(),"adverb clause",null);
					speech[i]=s;
				}
				if(POS[i].charAt(POS[i].length()-1)=='J'){
					SubConj s=new SubConj(randJSubConj(),"adjective clause",(Noun)speech[i-1]);
					speech[i]=s;
				}
			}
			
		}
		return speech;
	}
	
	private static String randName() throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("Names"));
    	
    	int line = (int)(Math.random()*100);
    	System.out.println(line);
    	for (int i=0;i<=line;i++){
    		String next = f.readLine();
    		if (i!=line){
    			continue;
    		}
    		else{
    			System.out.println("Name "+next);
    			return next;
    		}
    	}
		return "Name";
	}
	
	private static String randJSubConj() throws IOException{
		BufferedReader f = new BufferedReader(new FileReader("getJSubConj"));
    	
    	int line = (int)(Math.random()*3);
    	System.out.println(line);
    	for (int i=0;i<=line;i++){
    		String next = f.readLine();
    		if (i!=line){
    			continue;
    		}
    		else{
    			System.out.println("Adjective Subordinating Conjunction "+next);
    			return next;
    		}
    	}
		return "Adjective Subordinating Conjunction";
	}	
	
	private static String randVSubConj() throws IOException{
		BufferedReader f = new BufferedReader(new FileReader("getVSubConj"));
    	
    	int line = (int)(Math.random()*8);
    	System.out.println(line);
    	for (int i=0;i<=line;i++){
    		String next = f.readLine();
    		if (i!=line){
    			continue;
    		}
    		else{
    			System.out.println("Adverb Subordinating Conjunction "+next);
    			return next;
    		}
    	}
		return "Adverb Subordinating Conjunction";
	}
	
	private static String randNSubConj() throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("getNSubConj"));
    	
    	int line = (int)(Math.random()*2);
    	System.out.println(line);
    	for (int i=0;i<=line;i++){
    		String next = f.readLine();
    		if (i!=line){
    			continue;
    		}
    		else{
    			System.out.println("Noun Subordinating Conjunction "+next);
    			return next;
    		}
    	}
		return "Noun Subordinating Conjunction";
	}
	
	private static String randAdj() throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("Adjective List"));
    	
    	int line = (int)(Math.random()*200);
    	System.out.println(line);
    	for (int i=0;i<=line;i++){
    		String next = f.readLine();
    		if (i!=line){
    			continue;
    		}
    		else{
    			System.out.println("Adjective "+next);
    			return next;
    		}
    	}
		return "Adjective";
		
	}
	
	private static String randLkingVerb() throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("LinkingVerbs"));
    	
    	int line = (int)(Math.random()*44);
    	System.out.println("Linking Verb line "+line);
    	int i;
    	for (i=0;i<=line;i++){
    		String next = f.readLine();
    		if (i!=line){
    			continue;
    		}
    		else{
    			System.out.println("HERE "+next);
    			return next;
    		}
    	}
    	System.out.println("Linking Verb i : "+i);
		return "Linking Verb";
	}
	
	private static String RandActionVerb() throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("All verbs"));
    	
    	int line = (int)(Math.random()*96);
    	System.out.println("Action Verb line "+line);
    	int i;
    	for (i=0;i<=line;i++){
    		String next = f.readLine();
    		if (i!=line){
    			continue;
    		}
    		else{
    			return next.substring(0, next.indexOf(" "));
    		}
    	}
    	
    	System.out.println("Action Verb i : "+i);
		return "Action Verb";
		
	}
	
	private static String randIOVerb() throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("IO Verb List"));
    	
    	int line = (int)(Math.random()*4);
    	System.out.println("IO Verb "+line);
    	int i;
    	for (i=0;i<=line;i++){
    		String next = f.readLine();
    		if (i!=line){
    			continue;
    		}
    		else{
    			System.out.println("HERE "+next);
    			f.close();
    			return next;
    		}
    	}
    	System.out.println("IO verb i : "+i);
    	f.close();
		return "IO Verb";
		
	}
	
	public static boolean randBool(){
		return ((int)(Math.random()*2)==0);
	}
	
    private static String randNoun() throws IOException {
    	BufferedReader f = new BufferedReader(new FileReader("New Nouns"));
    	
    	int line = (int)(Math.random()*100);
    	System.out.println("line Noun "+line);
    	int i;
    	for (i=0;i<=line;i++){
    		String next = f.readLine();
    		if (i!=line){
    			continue;
    		}
    		else{
    			System.out.println(next);
    			return next;
    		}
    	}
    	System.out.println("noun i : "+i);
		return "noun";
		
	}
    
	public String getPresentVerb(String baseVerb, int sign, int person, boolean singular, int curIndex, String subject){
    	//sign is an int that will only ever take three values:
    	//1 - this means the verb is positive e.g. He does talk.
    	//0 - this mean that the verb is question e.g. Does he talk?
    	//-1 - this mean that the verb is negative e.g. He does not talk.
    	
    	//singular is a boolean that if:
    	//true then the verb is singular
    	//false then the verb is plural
    	
    	//curIndex is the index of baseVerb in the curSent ArrayList
    	
    	if(sign==1){
    		if(singular){
    			if(person==3){
        			return baseVerb+"s";
    			}else{return baseVerb;}
    		}else{return baseVerb;}
    	}
    	else if(sign==-1){
    		if(singular){
    			if(person==3){
    				return "does not " + baseVerb;
    			}else{return "do not "+baseVerb;}
    		}else{return "do not "+baseVerb;}
    	}
    	
    	return null;
    }
	
    public String getPresentPerfect(String baseWord,int sign,boolean singular,
    		int person, String noun){
    	if(singular){
    		if(person==1 || person==2){return "have "+addED(baseWord);}
    		if(person==3){return "has "+addED(baseWord);}
    	}
    	else{return "have "+addED(baseWord);}
    	
    	return null;
    }
    
    public String getPresentContinuous(String baseWord,int sign,boolean singular,
    		int person, String noun){
    	if(sign==1){
    		if(singular){
    			if(person==1){return "am "+addING(baseWord);}
    			if(person==2){return "are "+addING(baseWord);}
    			if(person==3){return "is "+addING(baseWord);}
    		}
    		else{return "are "+addING(baseWord);}
    	}
    	if(sign==2){
    		if(singular){
    			if(person==1){return "am not "+addING(baseWord);}
    			if(person==2){return "are not "+addING(baseWord);}
    			if(person==3){return "is not "+addING(baseWord);}
    		}
    		else{return "are not "+addING(baseWord);}
    	}
    	return null;
    }
    
    public String getPastSimple(String baseWord,int sign,boolean singular,
    		int person, String noun){
    	if(sign==1){
        	return addED(baseWord);
    	}
    	if(sign==2){
    		return "did not "+baseWord;
    	}
    	return null;
    }
    
    public String getPastPerfect(String baseWord,int sign,boolean singular,
    		int person, String noun){
    	if(sign==1){return "had "+addED(baseWord);}
    	if(sign==2){return "had not "+addED(baseWord);}
    	
    	return null;
    }
    
    public String getPastContinuous(String baseWord,int sign,boolean singular,
    		int person, String noun){
    	if(sign==1){
    		return "was "+addING(baseWord);
    	}
    	if(sign==2){
    		return "was not "+addING(baseWord);
    	}
    	return null;
    }
    
    public String getFutureSimple(String baseWord,int sign,boolean singular,
    		int person, String noun){
    	if(sign==1){return "will "+baseWord;}
    	if(sign==2){return "won't "+baseWord;}
    	
    	return null;
    }
    
    public String getFuturePerfect(String baseWord,int sign,boolean singular,
    		int person, String noun){
    	if(sign==1){return "will have "+baseWord;}
    	if(sign==2){return "won't have "+baseWord;}
    	return null;
    }
    
    public String getFutureContinuous(String baseWord,int sign,boolean singular,
    		int person, String noun){
    	if(sign==1){return "will be "+baseWord;}
    	if(sign==2){return "won't be "+baseWord;}
    	return null;
    }

   
    //ALL OF THESE METHODS ONLY WORK FOR REGULAR VERBS
    public String addED(String baseWord){
    	if(baseWord.charAt(baseWord.length()-1)=='e'){
    		return baseWord+"d";
    	}
    	else{
    		return baseWord+"ed";
    	}
    }
    public String addING(String baseWord){
    	if(baseWord.charAt(baseWord.length()-1)=='e'){
    		return baseWord.substring(0,baseWord.length()-1)+"ing";
    	}else{
    		return baseWord+"ing";
    	}
    }
}
