import java.util.Random;

public class RandomDNA {

	public static void main(String[] args) {
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
			
			System.out.println(output);
		
	}

}
