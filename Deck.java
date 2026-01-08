/*****************************************
 * Deck class
 * Zoey Zhu
 ****************************************/ 

import java.util.Random;

public class Deck {
	
	private Card[] cards;
	private int top; // the index of the top of the deck
	
	public Deck(){
		cards = new Card[52];
        int counter = 0;
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 13; j++) {
                cards[counter] = new Card(i, j);
                counter++;
            }
        }
        top = 0;
	}
	
	public void shuffle(){ 
        Random r = new Random();
        //looping through all cards in the deck
        for (int i = 0; i < cards.length; i++) {
            //swapping the card at index i with the card at some random index
            swapCard(r.nextInt(52), i);
        }
	}
	
	public Card deal(){
        //skips any cards that have been dealt
        while (cards[top].hasBeenDealt() == true) {
            top++;
        }

        //deal the top card in the deck as long as we haven't made it through the deck
        //joker card is dealt if we have made it through the deck
        if(top>51) {
            return new Card(0,0);
        }
        else {
            top++;
            cards[top-1].dealt();
            return cards[top-1];
        } 
	}
	

    public void swapCard(int target, int source) {
        Card currentCard = cards[source];
        cards[source] = cards[target];
        cards[target] = currentCard;
    }

    public Card getCard(int i) {
        return cards[i];
    }

}
