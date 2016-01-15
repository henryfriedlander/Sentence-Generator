import java.util.ArrayList;


public class GetSentence {
	/*
	public static void main(String[]args){
		System.out.println("HERE");
		getRandStructure();
	}
	*/
	
	public static String[] getRandStructure(){
		ArrayList<String>sentence=new ArrayList<String>();
		String[]base=getRoot();
		for(int i=0;i<base.length;i++){
			int rand=(int)(Math.random()*4);
			if(base[i].substring(0,base[i].length()-2).equals("noun") && rand==0){
				sentence.add("adjective "+(i+1));
			}
			else if(base[i].substring(0,base[i].length()-2).equals("subject") && rand==0){
				sentence.add("adjective "+(i+1));
			}
			if(base[i].substring(0,base[i].length()-2).equals("noun") && rand==2){
				String[]newS=getNounClause();
				for(int j=0;j<newS.length;j++){
					sentence.add(newS[j]);
				}
			}
			else if(base[i].substring(0,base[i].length()-2).equals("adverb") && rand==2){
				String[]newS=getAdverbClause(Character.getNumericValue(base[i].charAt(base[i].length()-1)));
				for(int j=0;j<newS.length;j++){
					sentence.add(newS[j]);
				}
			}
			else if(base[i].substring(0,base[i].length()-2).equals("adjective") && rand==2){
				String[]newS=getAdjectiveClause(Character.getNumericValue(base[i].charAt(base[i].length()-1)));
				for(int j=0;j<newS.length;j++){
					sentence.add(newS[j]);
				}
			}
			else if(base[i].substring(0,base[i].length()-2).equals("subject") && rand==2){
				String[]newS=getNounClause();
				for(int j=0;j<newS.length;j++){
					sentence.add(newS[j]);
				}
			}
			else{
				sentence.add(base[i]);
			}
			if(base[i].substring(0, base[i].length()-2).equals("subject") && rand==3){
				sentence=addAND(sentence,i);
			}
			else if(base[i].substring(0, base[i].length()-2).equals("subject") && rand==3){
				sentence=addAND(sentence,i);
			}
			
		}
		String[]finalS=sentence.toArray(new String[sentence.size()]);
		print(finalS);
		return finalS;
    }
    private static String[] getNounClause() {
		String sb="subordinate conjunction N";
		
		return one(sb,getBase());
	}
	private static String[] getAdjectiveClause(int ioa) {
		String sb="subordinate conjunction J"+ioa;
		String[]s=getBase();
		String[]b=new String[s.length-1];
		
		for(int i=1;i<s.length;i++){
			s[i]=b[i-1];
		}
		return one(sb,b);
	}
	private static String[] getAdverbClause(int ioa) {
		String sb="subordinate conjunction V"+ioa;
		
		return one(sb,getBase());
	}
	public static String[] one(String s, String[]a){
		String[]array=new String[a.length+1];
		array[0]=s;
		for(int i=1;i<a.length+1;i++){
			array[i]=a[i-1];
		}
		return array;
	}
	private static void print(String[] finalS) {
    	System.out.print("{");
		for(int i=0;i<finalS.length;i++){
			System.out.print(finalS[i]);
			if(i!=finalS.length-1){
				System.out.print(", ");
			}
		}
		System.out.println("}");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
	}
    
    public static String[]getPrepPhrase(){
    	String[]phrase=new String[2];
    	return phrase;
    }
    private static String[] combiner(String sb, String[] base, int ioa) {
		String[]array=new String[base.length+1];
		array[0]=sb;
		for(int i=1;i<array.length;i++){
			if(base[i-1].charAt(base[i-1].length()-1)=='0'){
				array[i]=base[i-1].substring(0,base[i-1].length()-1)+(ioa+i-1);
			}else{
				array[i]=base[i-1];
			}
		}
		return array;
	}
	public static String[]getBase(){
    	String[][]structures={
    			//{"subjectNS","linking verbLV","adjectiveP0"},
    			{"subjectNS","io verbIO","nameIO","nounDO"},
    			{"subjectNS","action verbAV","nameDO"},
    			{"subjectNS","action verbAV","adverb-1"}
    	};
    	return structures[(int)(Math.random()*structures.length)];
    }
	public static String[]getRoot(){
		String[]root=getBase();
		root[0]=root[0].substring(0,root[0].length()-2)+"Sb";
		return root;
	}
    
    public static ArrayList<String> addAND(ArrayList<String> sentence, int index){
    	//this method outputs a string array 
    	//that adds a correlative conjunction and a word of 
    	//the same part of speech as the word before the conjunction
    	ArrayList<String>a=new ArrayList<String>();
    	for(int i=0;i<sentence.size();i++){
    		a.add(sentence.get(i));
    		if(i==index){
    			a.add("conjunction C");
    			a.add(sentence.get(i));
    		}
    	}
    	
    	return a;
    }
    public String[]adder(String[]array,int num){
    	//the method adder takes the string array "array" and num
    	//then the function outputs a new array of length array.length+num 
    	//where the last num spots of the array are empty
    	String[]newArray=new String[array.length+num];
    	for(int i=0;i<array.length;i++){
    		newArray[i]=array[i];
    	}
    	
    	return newArray;
    }
    public String creater(){
    	return null;
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
    public String getFutureContinuous(String baseWord,int sign,boolean singular,
    		int person, String noun){
    	if(sign==1){return "will be "+baseWord;}
    	if(sign==2){return "won't be "+baseWord;}
    	return null;
    }
    public String getFuturePerfect(String baseWord,int sign,boolean singular,
    		int person, String noun){
    	if(sign==1){return "will have "+baseWord;}
    	if(sign==2){return "won't have "+baseWord;}
    	return null;
    }
   
    //ALL OF THESE METHODS ONLY WORK FOR REGULAR VERBS
    public String addED(String baseWord){
    	if(baseWord.charAt(baseWord.length()-1)=='e'){
    		return baseWord+"d";
    	}
    	else if(baseWord.charAt(baseWord.length()-1)=='e'){
    		return baseWord.substring(0,baseWord.length()-1)+"ied";
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