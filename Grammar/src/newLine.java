import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;


public class newLine {

	public static void main(String[] args) throws IOException{
		BufferedReader f = new BufferedReader(new FileReader("All verbs"));

		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("present active irregular verbs")));

		PrintWriter out2 = new PrintWriter(new BufferedWriter(new FileWriter("perfect active irregular verbs")));
		
		PrintWriter out3 = new PrintWriter(new BufferedWriter(new FileWriter("pluperfect active irregular verbs")));
		
		for(int i=0;i<97;i++){
			String line=f.readLine();
			boolean b=true;
			for(int j=0;j<line.length();j++){
				if(line.charAt(j)>=97 && line.charAt(j)<=122){
					
				}else{
					b=false;
				}
				
			}
			if(b){
				out.println(line);
			}
		}
		out.close();
	}
	public static void makeArrayList(BufferedReader f, PrintWriter out) throws IOException{
		ArrayList<String> stuff = new ArrayList<String>();
		String nextLine = null;
		for(int i=0;i<76;i++){
			nextLine = f.readLine();
			if (nextLine!=null){
				StringTokenizer st = new StringTokenizer(nextLine);
				
				while(true){
					String next;
					try {
						next = st.nextToken();
					}
					catch (NoSuchElementException e) {
						break;
					}
					stuff.add(next);
				}
			}
			else
				break;
		 }
		for (int i=0;i<stuff.size();i++){
			System.out.println(stuff.get(i));
		}
	}

}