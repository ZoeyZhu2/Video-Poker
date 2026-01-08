/*****************************************
 * Player class
 * Zoey Zhu
 ****************************************/ 

import java.util.ArrayList;

public class Player {
	
		
	private ArrayList<Card> hand; // the player's cards
	private double bankroll;
        private double bet;

		
	public Player(){		
	    // create a player here
        hand = new ArrayList<>();
        bankroll = 50.0;
	}

	public void addCard(Card c){
	    // add the card c to the player's hand
        hand.add(c);
	}

	public void removeCard(Card c){
	    // remove the card c from the player's hand
        hand.remove(c);
    }
		
    public void bets(double amt){
        // player makes a bet
        bet = amt;
        bankroll = bankroll - bet;
    }

    public void winnings(double odds){
        //	adjust bankroll if player wins
        //assuming odds are the winnings?
        bankroll = bankroll + bet * odds;
    }

    public double getBankroll(){
        // return current balance of bankroll
        return bankroll;
    }

    public ArrayList<Card> getHand() {
        return hand;
    } 

    public String showHand() {
        String cards = "";
        for (int i = 0; i < hand.size(); i++) {
            cards += hand.get(i).toString() + " ";
        }
        return cards;
    }

    public void exchangeCard(int loc, Card c) {
        hand.set(loc, c);
    }

    public void clearHand() {
        hand = new ArrayList<>();
    }
}