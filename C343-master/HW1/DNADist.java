import java.util.Random;

public class DNADist{

	public static void main(String[] args){
		String s1 = getRandomString();
		String s2 = getRandomString();


		System.out.println("The Hamming Distance between " + s1 + " and " + s2 + " is " +  hammingDistance(s1, s2));

	}

	public static int hammingDistance(String s1, String s2){
	
		int count = 0;
		for(int k = 0; k < 20; k++){
			if( s1.charAt(k) ==  s2.charAt(k) ) {
				count +=0;
			} 
			else{ count++;}}

		return count;}

	public static String getRandomString(){
			Random rnd = new Random();
                        String output="";
                        for(int k=0; k<20; k++){
                                int t = rnd.nextInt(4); //generates 0-3
                                if(t==0){
                                        output = output + "A";
                                }
                                if(t==1){
                                        output = output + "T";
                                }
                                if(t==2){
                                        output = output + "C";
                                }
                                if(t==3){
                                        output = output + "G";
                                }
                        }

                        return output;

	}
}

