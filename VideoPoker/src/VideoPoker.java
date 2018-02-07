//Video Poker

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class VideoPoker {
	
	int number;
	String suit,card;
	Scanner sc = new Scanner(System.in);
	Random rand = new Random();
	
	public static void main(String args[]) {
		
		int flag = 0, holdamt;
		String currentCard;
		
		VideoPoker poker = new VideoPoker();
		Scanner s = new Scanner(System.in);
		
		ArrayList<Integer> holdNumber = new ArrayList<Integer>();		//holding number
		ArrayList<Integer> holdSuit = new ArrayList<Integer>();			//holding suit
		ArrayList<String> suits = new ArrayList<String>();				//suit
		ArrayList<String> cards = new ArrayList<String>();				//full cards
		ArrayList<String> holds = new ArrayList<String>();				//hold
		
		//declaring suits
		suits.add("♥");
		suits.add("♦");
		suits.add("♣");
		suits.add("♠");
/*	
		//checking process							//remove multiline comment of this block for manual checking
		cards.add("2♠");							//fill your own cards
		cards.add("3♣");							//remember to multiline comment the 'while' block while manual checking
		cards.add("4♠");
		cards.add("5♥");
		cards.add("7♠");
*/			
		//create card space
		cards.add("");
		cards.add("");
		cards.add("");
		cards.add("");
		cards.add("");
		
		//unique card generation
		while(flag < 5) {							//multiline comment this block while manual checking
		
			currentCard = poker.generateCard(suits);
			if(currentCard.equalsIgnoreCase(cards.get(0)) || currentCard.equalsIgnoreCase(cards.get(1)) || currentCard.equalsIgnoreCase(cards.get(2)) || currentCard.equalsIgnoreCase(cards.get(3)) || currentCard.equalsIgnoreCase(cards.get(4))) {
			} else {
				cards.set(flag, currentCard);
				flag++;
			}	
		}
		
		//printing unique cards
		System.out.print("Dispenced cards: ");
		for(int i = 0; i < 5;i++) {
			System.out.print(cards.get(i) + " ");
		}
		
		//holding cards
		System.out.print("\n\nEnter the number of cards to hold: ");
		holdamt = s.nextInt();
		System.out.print("\n\nEnter the position of cards to hold: ");
		for(int i = 0; i < holdamt; i++) {
			holds.add(cards.get(s.nextInt() - 1));
			holdNumber.add(poker.getHoldNumber(holds, i));
			holdSuit.add(poker.getHoldSuit(holds, i));
		}
		
		//generating remaining cards
		for(int i = holdamt; i < 5; i++) {
			holds.add("");
		}
		
		while(holdamt < 5) {
			currentCard = poker.generateCard(suits);
			if(currentCard.equalsIgnoreCase(holds.get(0)) || currentCard.equalsIgnoreCase(holds.get(1)) || currentCard.equalsIgnoreCase(holds.get(2)) || currentCard.equalsIgnoreCase(holds.get(3)) || currentCard.equalsIgnoreCase(holds.get(4))) {
			} else {
				holds.set(holdamt, currentCard);
				holdNumber.add(poker.getHoldNumber(holds, holdamt));
				holdSuit.add(poker.getHoldSuit(holds, holdamt));
				holdamt++;
			}	
		}

		//print final card
		System.out.print("\n\nFinal Cards: ");
		for(int i = 0; i < holdamt; i++) {
			System.out.print(holds.get(i) + " ");
		}
	
		
		//result
		if(poker.isSuitSimilar(holdSuit)) {
		
			if(poker.isRoyalFlush(holdNumber)) {								//Royal Flush
				System.out.print("\n\n\nResult: Royal Flush!...");
			} else if(poker.isStraightFlush(holdNumber)) {						//Straight Flush
				System.out.print("\n\n\nResult: Straight Flush!...");
			} else {															//Flush
				System.out.print("\n\n\nResult: Flush!...");
			}
			
		} else {
			
			if(poker.isFourOfAKind(holdNumber, holdSuit)) {						//Four of a Kind
				System.out.print("\n\n\nResult: Four of a Kind!...");
			} else if(poker.isFullHouse(holdNumber)) {							//Full House
				System.out.print("\n\n\nResult: Full House!...");
			} else if(poker.isThreeOfAKind(holdNumber)) {						//Three of a Kind
				System.out.print("\n\n\nResult: Three of a Kind!...");
			} else if(poker.isTwoPair(holdNumber)) {							//Two Pair
				System.out.print("\n\n\nResult: Two Pair!...");
			} else if(poker.isOnePair(holdNumber)) {							//One Pair
				System.out.print("\n\n\nResult: One Pair!...");
			} else if(poker.isStraight(holdNumber)) {							//Straight
				System.out.print("\n\n\nResult: Straight!...");
			} else {															//High Card
				System.out.println("\n\n\nResult: High Card!... \nYour High Card is " + poker.getHighCard(holdNumber));
			}
			
		}
			
	}

	//generates random card
	public String generateCard(ArrayList suits) {
		
		number = rand.nextInt(13) + 1;
		suit = (String) suits.get(rand.nextInt(4));
		
		if(number == 11) {
			card = 'J' + suit;
		} else if(number == 12) {
			card = 'Q' + suit;
		} else if(number == 13) {
			card = 'K' + suit;
		} else if(number == 1) {
			card = 'A' + suit;
		} else {
			card = number + suit;
		}
		
		return card;
		
	}
	
	//returns hold number
	public int getHoldNumber(ArrayList holds, int i) {
		
		int number = 0,in;
		in = i;
		if(((String) holds.get(in)).charAt(0) == 'K') {
			number = 13;
		} else if(((String) holds.get(in)).charAt(0) == 'Q') {
			number = 12;
		} else if(((String) holds.get(in)).charAt(0) == 'J') {
			number = 11;
		} else if(((String) holds.get(in)).charAt(0) == 'A') {
			number = 1;
		} else if(((String) holds.get(in)).charAt(0) == '1') {
			number = 10;
		} else {
			number = ((String) holds.get(in)).charAt(0) - 48;
		}
		
		return number;
		
	}
	
	//returns hold suit
	public int getHoldSuit(ArrayList holds, int i) {
		
		int suit = 0;
		if(((String) holds.get(i)).charAt(1) == '♥') {
			suit = 1;
		} else if(((String) holds.get(i)).charAt(1) == '♦') {
			suit = 2;
		} else if(((String) holds.get(i)).charAt(1) == '♣') {
			suit = 3;
		} else if(((String) holds.get(i)).charAt(1) == '♠') {
			suit = 4;
		} else if(((String) holds.get(i)).charAt(1) == '0') {
			if(((String) holds.get(i)).charAt(2) == '♥') {
				suit = 1;
			} else if(((String) holds.get(i)).charAt(2) == '♦') {
				suit = 2;
			} else if(((String) holds.get(i)).charAt(2) == '♣') {
				suit = 3;
			} else if(((String) holds.get(i)).charAt(2) == '♠') {
				suit = 4;
			}
		}
		
		return suit;
		
	}
	
	//check for similar suits
	public boolean isSuitSimilar(ArrayList holdSuit) {
		
		boolean isSimilar = false;
		if(holdSuit.get(0) == holdSuit.get(1) && holdSuit.get(1) == holdSuit.get(2) && holdSuit.get(2) == holdSuit.get(3) && holdSuit.get(3) == holdSuit.get(4)) {
			isSimilar = true;
		}
		
		return isSimilar;
	}
	
	//check for royal flush
	public boolean isRoyalFlush(ArrayList holdNumber) {
		
		boolean isRoyal = false;
		
		//reverse sorting
		Collections.sort(holdNumber, Collections.reverseOrder());
		
		if((int) holdNumber.get(0) == 13 && (int) holdNumber.get(1) == 12 && (int) holdNumber.get(2) == 11 && (int) holdNumber.get(3) == 10 && (int) holdNumber.get(4) == 1 ) {
			isRoyal = true;
		}
		
		return isRoyal;
	}
	
	//check for straight flush
	public boolean isStraightFlush(ArrayList holdNumber) {
		
		boolean isTrue = false;
		
		//reverse sorting
		Collections.sort(holdNumber, Collections.reverseOrder());
		
		if((int) holdNumber.get(0) == (int) holdNumber.get(1) + 1 && (int) holdNumber.get(1) == (int) holdNumber.get(2) + 1 && (int) holdNumber.get(2) == (int) holdNumber.get(3) + 1 && (int) holdNumber.get(3) == (int) holdNumber.get(4) + 1) {
			isTrue = true;
		}
		
		return isTrue;
	}
	
	//check for four of a kind
	public boolean isFourOfAKind(ArrayList holdNumber, ArrayList holdSuit) {
		
		boolean isTrue = false;
		
		if(holdNumber.get(0) == holdNumber.get(1) && holdNumber.get(1) == holdNumber.get(2) && holdNumber.get(2) == holdNumber.get(3) && holdSuit.get(0) != holdSuit.get(1) && holdSuit.get(1) != holdSuit.get(2) && holdSuit.get(2) != holdSuit.get(3)) {
			isTrue = true;
		} else if(holdNumber.get(0) == holdNumber.get(1) && holdNumber.get(1) == holdNumber.get(2) && holdNumber.get(2) == holdNumber.get(4) && holdSuit.get(0) != holdSuit.get(1) && holdSuit.get(1) != holdSuit.get(2) && holdSuit.get(2) != holdSuit.get(4)) {
			isTrue = true;
		} else if(holdNumber.get(0) == holdNumber.get(1) && holdNumber.get(1) == holdNumber.get(3) && holdNumber.get(3) == holdNumber.get(4) && holdSuit.get(0) != holdSuit.get(1) && holdSuit.get(1) != holdSuit.get(3) && holdSuit.get(3) != holdSuit.get(4)) {
			isTrue = true;
		} else if(holdNumber.get(0) == holdNumber.get(2) && holdNumber.get(2) == holdNumber.get(3) && holdNumber.get(3) == holdNumber.get(4) && holdSuit.get(0) != holdSuit.get(2) && holdSuit.get(2) != holdSuit.get(3) && holdSuit.get(3) != holdSuit.get(4)) {
			isTrue = true;
		} else if(holdNumber.get(1) == holdNumber.get(2) && holdNumber.get(2) == holdNumber.get(3) && holdNumber.get(3) == holdNumber.get(4) && holdSuit.get(1) != holdSuit.get(2) && holdSuit.get(2) != holdSuit.get(3) && holdSuit.get(3) != holdSuit.get(4)) {
			isTrue = true;
		}
		
		return isTrue;
		
	}
	
	//check for full house
	public boolean isFullHouse(ArrayList holdNumber) {
		
		boolean isTrue = false;
		
		//reverse sorting
		Collections.sort(holdNumber, Collections.reverseOrder());
		
		if(holdNumber.get(0) == holdNumber.get(1) && holdNumber.get(1) == holdNumber.get(2) && holdNumber.get(3) == holdNumber.get(4)) {
			isTrue = true;
		} else if(holdNumber.get(0) == holdNumber.get(1) && holdNumber.get(2) == holdNumber.get(3) && holdNumber.get(3) == holdNumber.get(4)) {
			isTrue = true;
		}
		
		return isTrue;
		
	}
	
	//check for three of a kind
	public boolean isThreeOfAKind(ArrayList holdNumber) {
		
		boolean isTrue = false;
		
		//reverse sorting
		Collections.sort(holdNumber, Collections.reverseOrder());
		
		if(holdNumber.get(0) == holdNumber.get(1) && holdNumber.get(1) == holdNumber.get(2)) {
			isTrue = true;
		} else if(holdNumber.get(1) == holdNumber.get(2) && holdNumber.get(2) == holdNumber.get(3)) {
			isTrue = true;
		} else if(holdNumber.get(2) == holdNumber.get(3) && holdNumber.get(3) == holdNumber.get(4)) {
			isTrue = true;
		}
		
		return isTrue;
	}
	
	//check for two pair
	public boolean isTwoPair(ArrayList holdNumber) {
		
		boolean isTrue = false;
		
		//reverse sorting
		Collections.sort(holdNumber, Collections.reverseOrder());
		
		if(holdNumber.get(0) == holdNumber.get(1) && holdNumber.get(2) == holdNumber.get(3)) {
			isTrue = true;
		} else if(holdNumber.get(1) == holdNumber.get(2) && holdNumber.get(3) == holdNumber.get(4)) {
			isTrue = true;
		} else if(holdNumber.get(0) == holdNumber.get(1) && holdNumber.get(3) == holdNumber.get(4)) {
			isTrue = true;
		}
		
		return isTrue;
		
	}
	
	//check for one pair
	public boolean isOnePair(ArrayList holdNumber) {
		
		boolean isTrue = false;
		
		//reverse sorting
		Collections.sort(holdNumber, Collections.reverseOrder());
		
		if(holdNumber.get(0) == holdNumber.get(1)) {
			isTrue = true;
		} else if(holdNumber.get(1) == holdNumber.get(2)) {
			isTrue = true;
		} else if(holdNumber.get(2) == holdNumber.get(3)) {
			isTrue = true;
		} else if(holdNumber.get(3) == holdNumber.get(4)) {
			isTrue = true;
		}
		
		return isTrue;
		
	}
	
	//check for straight
	public boolean isStraight(ArrayList holdNumber) {
		
		boolean isTrue = false;
		
		//reverse sorting
		Collections.sort(holdNumber, Collections.reverseOrder());
		
		if((int) holdNumber.get(0) == (int) holdNumber.get(1) + 1 && (int) holdNumber.get(1) == (int) holdNumber.get(2) + 1 && (int) holdNumber.get(2) == (int) holdNumber.get(3) + 1 && (int) holdNumber.get(3) == (int) holdNumber.get(4) + 1) {
			isTrue = true;
		}
		
		return isTrue;
		
	}
	
	//return high card
	public String getHighCard(ArrayList holdNumber) {
		
		int highCard, checkA;
		String returnCard;
		//reverse sorting
		Collections.sort(holdNumber, Collections.reverseOrder());
		
		highCard = (int) holdNumber.get(0);
		checkA = (int) holdNumber.get(4);
		
		if(checkA == 1) {
			returnCard = "A";
		} else if(highCard == 13) {
			returnCard = "K";
		} else if(highCard == 12) {
			returnCard = "Q";
		} else if(highCard == 11) {
			returnCard = "J";
		} else {
			returnCard = Integer.toString(highCard);
		}
		
		return returnCard;
	}

}
