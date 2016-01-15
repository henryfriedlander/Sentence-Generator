import java.io.IOException;
import java.util.ArrayList;

import parts_of_speech.*;


public class POSparser {
	public static void print(String[][]a){
		for(int i=0;i<a.length;i++){
			System.out.print("{");
			for(int j=0;j<a[0].length;j++){
				System.out.print(a[i][j]);
				if(j!=a[0].length-1){
					System.out.print(", ");
				}
			}
			if(i!=a.length-1){
				System.out.print("}");
			}
			System.out.println(",");
		}
	}
	public static String[][] parse(POS[]pos){
		ArrayList<String>s=new ArrayList<String>();
		ArrayList<String>p=new ArrayList<String>();
		for(int i=0;i<pos.length;i++){
			String st=pos[i].pos();
			String word=pos[i].word;
			if(st.equals("noun")){
				if(i>1 && p.get(i-1).equals("adjective") && ((Noun)(pos[i])).singular){
					p.remove(i-1);
					p.add("article");
					p.add("adjective");
					String curVal=s.get(i-1);
					s.remove(i-1);
					int rand = (int)(Math.random()*2);
					if(rand==0){
						if(curVal.charAt(0)=='a'){
							s.add("an");
						}else{
							s.add("a");
						}
					}else{
						s.add("the");
					}
					s.add(curVal);
				}
				s.add("the");
				p.add("article");
				s.add(word);
				p.add("noun");
				
			}
			else{
				s.add(word);
				p.add(st);
			}
		}
		String[][]words=new String[2][s.size()];
		for(int i=0;i<s.size();i++){
			words[0][i]=s.get(i);
		}
		for(int i=0;i<p.size();i++){
			words[1][i]=p.get(i);
		}
		return words;
	}
}