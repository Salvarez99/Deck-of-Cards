package deckOfCards;

public class Player {
    private Deck offHandDeck;
    private Deck inUseDeck;

    public Player(){
        this.offHandDeck = new Deck();
        this.inUseDeck = new Deck();
    }

    public Deck getOffHandDeck() {
        return offHandDeck;
    }

    public Deck getInUseDeck() {
        return inUseDeck;
    }

    public void setInUseDeck(Deck deck){
        this.inUseDeck = deck;
        this.offHandDeck = new Deck();
    }

    public void swapInUseAndOffHand(){
        this.inUseDeck = this.offHandDeck;
        this.offHandDeck = new Deck();
    }

    


}
