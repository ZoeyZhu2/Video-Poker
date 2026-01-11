/*****************************************
 * Game class
 * Zoey Zhu
 ****************************************/ 

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class Game {
	
	private Player p;
	private Deck cards;
	
	
	public Game(String[] testHand){
		// This constructor is to help test your code.
		// use the contents of testHand to
		// make a hand for the player
		// use the following encoding for cards
		// c = clubs
		// d = diamonds
		// h = hearts
		// s = spades
		// 1-13 correspond to ace-king
		// example: s1 = ace of spades
		// example: testhand = {s1, s13, s12, s11, s10} = royal flush
		p = new Player();
		cards = new Deck();

		//add specified cards to the hand
		for (int i = 0; i < testHand.length; i++) {
			String suit = testHand[i].substring(0,1);
			int rank = Integer.parseInt(testHand[i].substring(1));
			int s = 0;
			if (suit.equals("s")) {
				s = 1;
			}
			if (suit.equals("d")) {
				s = 2;
			} 
			if (suit.equals("c")) {
				s = 3;
			}
			if (suit.equals("h")) {
				s = 4;
			}
			//creating specified card

			//in case the user inputs too many cards
			if (i == 5) {
				break;
			}

			//adding specified card to player's hand
			p.addCard(new Card(s, rank));

			//marking specified cards as dealt
			for (int j = 0; j < 52; j++) {
				if (cards.getCard(j).getSuit() == s && cards.getCard(j).getRank() == rank) {
					cards.getCard(j).dealt();
				}
			}
		}

		//shuffle deck
		cards.shuffle();

		//in case the user has not input enough cards
		for (int i = testHand.length; i < 5; i++) {
			p.addCard(cards.deal());
		}
	}
	
	public Game(){
		// This no-argument constructor is to actually play a normal game
		p = new Player();
		cards = new Deck();

		//shuffle deck
		cards.shuffle();

		//deal a random hand
		for (int i = 0; i < 5; i++) {
			p.addCard(cards.deal());
		}
	}
	
	// public void play(){
	// 	// this method should play the game	
	// 	Scanner input = new Scanner(System.in);
	// 	System.out.println("Welcome to Video Poker!");
	// 	System.out.println("YOUR TOKENS: " + p.getBankroll());
	// 	System.out.print("Would you like to play a round? (y/n): ");
	// 	String answer = input.next();
	// 	boolean play = false;
	// 	if (answer.equals("y")) {
	// 		play = true;
	// 	}

	// 	//enter game:
	// 	while (play) {
	// 		//betting
	// 		System.out.print("How many tokens to bet this hand? (1 to 5): ");
	// 		double bet = input.nextDouble();

	// 		//checking for valid inputs
			
	// 		while (true) {
	// 			if (bet <= p.getBankroll() && bet <= 5.0 && bet >= 1.0) {
	// 				break;
	// 			}
	// 			System.out.println("You cannot bet this many tokens.");
	// 			System.out.print("How many tokens to bet this hand? (1 to 5): ");
	// 			bet = input.nextDouble();
	// 		}
	// 		p.bets(bet);
			

	// 		//showing cards
	// 		System.out.println("The hand is: " + p.showHand());

	// 		//exchanging cards
	// 		System.out.print("How many cards (0-5) would you like to exchange? ");
	// 		int num = input.nextInt();

	// 		//checking for valid inputs
	// 		while (true) {
	// 			if (num < 5 && num >= 0) {
	// 				break;
	// 			}
	// 			System.out.println("Invalid answer.");
	// 			System.out.print("How many cards (0-5) would you like to exchange? ");
	// 			num = input.nextInt();
	// 		}
	// 		int[] exchange = new int[num];

	// 		//asking for cards to exchange
	// 		ArrayList<Integer> chosenCards = new ArrayList<>();
	// 		for (int i = 0; i < num; i++) {
	// 			System.out.print("Which card (1-5) would you like to exchange? ");
	// 			int choice = input.nextInt();
	// 			//checking to make sure the user does not choose to exchange the same card multiple times
	// 			while (chosenCards.indexOf(choice) != -1) {
	// 				System.out.print("Choose a card you have not chosen before. Which card (1-5) would you like to exchange? ");
	// 				choice = input.nextInt();
	// 			}
	// 			exchange[i] = choice - 1;
	// 			chosenCards.add(choice);
	// 		}

	// 		//exchanging cards
	// 		for (int i = 0; i < num; i++) {
	// 			p.exchangeCard(exchange[i], cards.deal());
	// 		}
	// 		System.out.println("The hand is: " + p.showHand());

	// 		//seeing what the result of the hand is
	// 		String result = checkHand(p.getHand());
	// 		System.out.println("You got " + result);


	// 		//calculating payout and adding it to player's bankroll
	// 		double multiplier = getMultiplier(result);
	// 		p.winnings(multiplier);
	// 		double payout = bet * multiplier;
	// 		System.out.println("Payout: " + (int)payout + " tokens");

	// 		if ((int)p.getBankroll() == 0) {
	// 			System.out.println("Sorry, you are out of tokens :(");
	// 			break;
	// 		}

	// 		//sharing player's bankroll
	// 		System.out.println("YOUR TOKENS: " + p.getBankroll());

	// 		//play again?
	// 		System.out.print("Would you like to play a round? (y/n): ");
	// 		answer = input.next();
	// 		play = false;
	// 		if (answer.equals("y")) {
	// 			play = true;
	// 		}
	// 		//remove any cards from the player's hand:
	// 		p.clearHand();

	// 		//making a new deck 
	// 		cards = new Deck();

	// 		//shuffle cards before the start of each game
	// 		cards.shuffle(); 	

	// 		//dealing new cards before the start of each game
	// 		for (int i = 0; i < 5; i++) {
	// 			p.addCard(cards.deal());
	// 		}

	// 	}

	// 	//the end:
	// 	System.out.println("Thank you for playing Video Poker!");

	// }
	
	public String checkHand(ArrayList<Card> hand){
		// this method should take an ArrayList of cards
		// as input and then determine what evaluates to and
		// return that as a String

		//sorting the hand from least to greatest rank:
		Collections.sort(hand);
		//As a check:
		// for (Card c: hand) {
		// 	System.out.println(c);
		// }

		//seeing how many cards are of the same rank:
		int[] rankDiff = new int[4];
		int numSameRank = 0;
		for (int i = 0; i < 4; i++) {
			rankDiff[i] = hand.get(i).compareTo(hand.get(i+1));
			if (rankDiff[i] == 0) {
				numSameRank++;
			}
		}

		// {#,0,0,0} or {0,0,0,#} would be quads
		// {0,#,0,0} or {0,0,#,0} would be full house
		if (numSameRank == 3) {
			if (rankDiff[0] != 0 || rankDiff[3] != 0) {
				return "Four of a Kind";
			}
			else {
				return "Full House";
			}
		}

		//{0,0,#,#} or {#,0,0,#} or {#,#,0,0} would be trips
		//{#,0,#,0} or {0,#,0,#} would be two pair

		if (numSameRank ==2) {
			if (rankDiff[0] != rankDiff[1] && rankDiff[1] != rankDiff[2]) {
				return "Two Pairs";
			}
			else {
				return "Three of a Kind";
			}
		}

		//{0,#,#,#} or {#,0,#,#} or {#,#,0,#} or {#,#,#,0} would be one pair
		if (numSameRank == 1) {
			return "One Pair";
		}

		//check for straight
		boolean straight = false;
		if (rankDiff[0] == -1 && rankDiff[1] == -1 &&
		rankDiff[2] == -1 && rankDiff[3] == -1) {
			straight = true;
		}

		//checking for flushes
		boolean flush = true;
		for (int i = 0; i < hand.size() - 1; i++) {
			if (hand.get(i).compareSuit(hand.get(i + 1)) != 0) {
				flush = false;
			}
		}

		//checking for royal straight. hand will be sorted 1, 10, 11, 12, 13
		boolean rStraight = false;
		if (hand.get(0).getRank() == 1 && rankDiff[0] == -9 && rankDiff[1] == -1 &&
		rankDiff[2] == -1 && rankDiff[3] == -1) {
			rStraight = true;
		}


		if (flush == true && straight == true) {
			return "Straight Flush";
		}
		else if (flush == true && rStraight == true) {
			return "Royal Flush";
		}
		else if (flush == true) {
			return "Flush";
		}
		else if (straight == true) {
			return "Straight";
		}

		//if nothing has been returned up to this point,
		return "No pair";
	}
	
	public double getMultiplier(String result) {
		double multiplier = 0.0;
		if (result.equals("One Pair")) {
			multiplier = 1.0;
		}
		if (result.equals("Two Pairs")) {
			multiplier = 2.0;
		} 
		if (result.equals("Three of a Kind")) {
			multiplier = 3.0;
		}
		if (result.equals("Straight")) {
			multiplier = 4.0;
		}
		if (result.equals("Flush")) {
			multiplier = 5.0;
		}
		if (result.equals("Full House")) {
			multiplier = 6.0;
		}
		if (result.equals("Four of a Kind")) {
			multiplier = 25.0;
		}
		if (result.equals("Straight Flush")) {
			multiplier = 50.0;
		}
		if (result.equals("Royal Flush")) {
			multiplier = 250.0;
		}
		return multiplier;
	}
	public Deck getDeck() {
	return cards;
	}

	public Player getPlayer() {
		return p;
	}
}

