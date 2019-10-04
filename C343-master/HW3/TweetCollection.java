import java.util.*;
import java.net.*;
import java.io.*;

public class TweetCollection{

  ArrayList<Tweet> tweets = new ArrayList<Tweet>();
  public static void main(String[] args) throws IOException{
	TweetCollection tc = new TweetCollection();
	tc.getTweets();
	tc.printAll();
  }

  public void getTweets() throws IOException{
	URL url;
       	url = new URL("http://homes.soic.indiana.edu/classes/spring2016/csci/c343-yye/tweet-data-Jan16.txt");
	Scanner in = new Scanner(url.openStream() );
	while (in.hasNext() ){
		String[] tweet = new String[2];
		tweet = in.nextLine().split(" ", 2);//separates author from content. [0] = author [1]=content
		tweets.add(new Tweet(tweet[1], tweet[0]));//Tweet(Content, Author)
	}
	in.close();
  }

  public void printAll(){
	for(Tweet t: tweets){System.out.println(t.getTweet() );//uses getTweet method from Tweet
	}

  }
  



}

