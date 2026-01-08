/*****************************************
 * Card class
 * Zoey Zhu
 ****************************************/ 

public class Card implements Comparable<Card>{
	
	private int suit; // use integers 1-4 to encode the suit
	private int rank; // use integers 1-13 to encode the rank
	private boolean dealt;
	
	public Card(int s, int r){
		suit = s;
		rank = r;
		dealt = false;
	}
	
	//compares rank
	public int compareTo(Card c){
		return this.rank - c.getRank();
	}
	
	public int getSuit(){
		return suit;
	}

	public int getRank(){
		return rank;
	}


	public String toString(){
		String s = "";
		if (rank == 13) {
			s += "King";
		}
		if (rank == 12) {
			s += "Queen";
		}
		if (rank == 11) {
			s += "Jack";
		}
		if (rank == 10) {
			s += "Ten";
		}
		if (rank == 9) {
			s += "Nine";
		}
		if (rank == 8) {
			s += "Eight";
		}
		if (rank == 7) {
			s += "Seven";
		}
		if (rank == 6) {
			s += "Six";
		}
		if (rank == 5) {
			s += "Five";
		}
		if (rank == 4) {
			s += "Four";
		}
		if (rank == 3) {
			s += "Three";
		}
		if (rank == 2) {
			s += "Two";
		}
		if (rank == 1) {
			s += "Ace";
		}
        if (rank == 0) {
            s += "Joker";
        }
		s += " of";
		if (suit == 1) {
			s += " Spades";
		}
		else if (suit == 2) {
			s += " Diamonds";
		}
		else if (suit == 3) {
			s += " Clubs";
		}
		else if (suit == 4) {
			s += " Hearts";
		}
		return s;
	}

	//compares suits
	public int compareSuit(Card c) {
		return this.suit-c.getSuit();
	}

	//marks card as dealt
	public void dealt() {
		dealt = true;
	}

	//reveal whether card has been dealt yet
	public boolean hasBeenDealt() {
		if (dealt == true) {
			return true;
		}
		return false;
	}

}
