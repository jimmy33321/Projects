import java.util.*;
import java.net.*;
import java.io.*;


//This program will return the frequency of the word in the given document. I misread the instructions, instead of storing the number of lines the word appears, I am storing the number of times a word appears.
public class Search{
  	
	Hashtable map = new Hashtable();

  	public static void main(String[] args) throws IOException{
		Search s = new Search();
		s.preprocess(); //Stores all words from the online URL version of the given document
		System.out.println("The word algorithm appears: " +  s.map.get("algorithm") ); //0
		System.out.println("The word data appears: " + s.map.get("data") ); //7
		System.out.println("The word From appears: " + s.map.get("From") ); //1


	}

	public void preprocess() throws IOException{ //includes words and frequency

	URL url;
	url = new URL("http://homes.soic.indiana.edu/classes/spring2016/csci/c343-yye/docu.txt");
	Scanner in = new Scanner(url.openStream());
	while(in.hasNext()){
		String w = in.next();
		if(map.containsKey(w)){
			int count = (Integer) map.get(w);
			map.put(w,count + 1);
		} else { map.put(w,1);}
	}
	}

}
