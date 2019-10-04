public interface Card {
	//create a full deck of cards (with 52 cards; no Jokers)
	public void initialize();
	//draw a card, and return the card in the format of a string, e.g, 2S (2 of Spades)
	//the single letter representation for shapes: 
	//S (spades), C (clubs), H (hearts) and D (diamonds)
	public String drawCard();
}