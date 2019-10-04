import java.util.Random;

public class MyCard implements Card {
	
	public MyCard(){
		initialize();
	}
	
	String[] deck = new String[52];//Array containing 52 String, representing the 52 cards.

	public static void main(String[] args) {
		MyCard m = new MyCard();
		System.out.println(m.drawCard());
	}
	
	public void initialize(){//Fills in the array with String representing Cards.
		String suite = "";
		String value = "";
		for(int k=0; k < 52; k++){//runs index 0-51, for all 52 cards
			if (k<13){
				suite = "H";
			}else if(k<26){
				suite = "D"; 
			}else if(k<39){
				suite = "S"; 
			}
			else suite ="C"; 
			
			if(k%13==0){ 
				value="Ace";
			}else if(k%13 == 1){ value="King";
			}else if(k%13==11){ value="Queen";
			}else if(k%13==12){ value="Jack";
			}else value=""+(k%13);

			deck[k] = value + suite;
		}}
			
	
	public String drawCard(){//Draws 1 Random String that is a Card
		Random rnd = new Random();
		int t = rnd.nextInt(52);//Selects 0-51
		return deck[t];
	}

}
