package deckOfCards;
import java.util.ArrayList;
import java.util.Random;


//Test
public class Deck {
	private ArrayList<Card> deck;
	private int STDDECKSIZE = 52;

	/*
	 * Creates an empty deck of cards
	 */
	public Deck() {
		deck = new ArrayList<Card>();
	}

	/*
	 * creates either a standard deck of cards or deck with attributes for the game war.
	 */
	public Deck(String mode) throws InvalidException {
		deck = new ArrayList<Card>();
		if(mode.toLowerCase().equals("war")){
			this.fillDeck();
			this.shuffleDeck();
		}else if(mode.toLowerCase().equals("std")){
			this.stdDeck();
			this.shuffleDeck();
		}else{
			throw new InvalidException("Not a valid mode. (std / war)");
		}

	}

	private void stdDeck(){
		int suit = 0;
		int cardValue = 0;

		for (int i = 0; i < this.STDDECKSIZE; i++) {
			if (cardValue == 13) {
				cardValue = 0;
				suit++;
			}
			
			Card card = new Card(Card.faceValues[cardValue], cardValue + 1, Card.suits[suit]);
			this.addCardToDeck(card);
			
			cardValue++;
		}
	}

	/*
	 * Fills deck with cards. Point values are from A - K: 1 - 13
	 */
	private void fillDeck() {
		int suit = 0;
		int cardValue = 0;

		for (int i = 0; i < this.STDDECKSIZE; i++) {
			if (cardValue == 13) {
				cardValue = 0;
				suit++;
			}
			
			if(cardValue == 0){
				
				Card card = new Card(Card.faceValues[cardValue], 9000, Card.suits[suit]);			
				this.addCardToDeck(card);

			}else{
				Card card = new Card(Card.faceValues[cardValue], cardValue + 1, Card.suits[suit]);
				this.addCardToDeck(card);
			}
			cardValue++;
		}
	}
	
	public Card drawFromTop() {
		
		if (!this.isEmpty()) 
			return deck.remove(0);
			
		return null;
	}

	public void printDeck(){
		for (Card card : deck) {
			System.out.println(card);
		}
	}

	public void shuffleDeck(){
		ArrayList<Card> shuffledDeck = new ArrayList<>();
		Random rand = new Random();
		
		while(!this.isEmpty()){
			int index = rand.nextInt(this.deck.size());
			Card card = this.deck.remove(index);
			shuffledDeck.add(card);
		}
		this.deck = shuffledDeck;
	}

	public void addCardToDeck(Card card){
		this.deck.add(card);
	}

	public void addCardsToDeck(Deck deck){
		for (Card card : deck.deck) {
			this.addCardToDeck(card);
		}
	}

	public void deal(ArrayList<Player> players){
		while(!this.isEmpty()){
			for (Player player : players) {
				player.getInUseDeck().addCardToDeck(this.drawFromTop());
			}
		}
	}

	public ArrayList<Card> getDeck(){
		return this.deck;
	}

	public int deckSize(){
		return this.deck.size();
	}
	
	public boolean isEmpty() {
		return deck.isEmpty();
	}
}
