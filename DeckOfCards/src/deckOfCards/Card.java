package deckOfCards;

public class Card {
	public static String[] faceValues = {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
	public static String[] suits = {"Clubs", "Hearts", "Diamonds", "Spades"};
	private int value;
	private String suit;
	private String faceValue;
	
	
	public Card() {
		super();
	}
	
	public Card(String faceValue, int value, String suit) {
		super();
		this.faceValue = faceValue;
		this.value = value;
		this.suit = suit;
	}
	public String getFaceValue() {
		return this.faceValue;
	}
	public int getValue() {
		return value;
	}

	public String getSuit() {
		return suit;
	}
	
	@Override
	public String toString() {
		return this.getFaceValue() + " of " +  this.getSuit();

	}
}
