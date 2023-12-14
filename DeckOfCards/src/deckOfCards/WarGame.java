package deckOfCards;

import java.util.ArrayList;

public class WarGame {
    private Deck deck;
    private ArrayList<Player> players;
    private int PLAYERCOUNT = 2;
    private Deck pool;

    enum gameState{
        WAR,
        PLAYER1WIN,
        PLAYER2WIN;
    }

    public WarGame() {
        this.deck = new Deck();
        this.players = new ArrayList<>();
        pool = new Deck();

        this.deck.fillDeck();
        this.deck.shuffleDeck();

        for (int i = 0; i < this.PLAYERCOUNT; i++) {
            this.players.add(new Player());
        }

        this.deck.deal(players);
    }

    public void run() throws InterruptedException {
        /*
         *Loop while main and offhands are not empty
         *    Each player draws a card
         *    Cards are compared to each other
         *        P1 won: add pool to P1 offhand
         *        P2 won: add pool to P2 offhand
         *        Tie: Call war
         */ 

        //Continue while both in use hands are not empty
        while(!this.players.get(0).getInUseDeck().isEmpty() && !this.players.get(1).getInUseDeck().isEmpty()){
            
            Card p1Card = this.players.get(0).getInUseDeck().drawFromTop();
            Card p2Card = this.players.get(1).getInUseDeck().drawFromTop();
            gameState state = compareCards(p1Card, p2Card);
            
            this.pool.addCardToDeck(p1Card);
            this.pool.addCardToDeck(p2Card);
            
            if(state == gameState.WAR){
                war();
            }
            
            //Check eligibity for hand swap
            giveWinnings(state);
            checkHands();
            
            Thread.sleep(650);
        }
    }

    /*
     * 
     */
    public void war() {
        /*
         * For filling pool with 3 cards from each player:
        *       As I draw a card I should check if the card is null
        *       if the card is null 
        *            I should check hands
        *            Draw a card again
        *       else
        *            add card to pool
         * ----
         * Draw 1 card from each player
         * Compare cards
         * Give winnings
         * 
         * 
         */
        System.out.println("War should be called\n");
    }

    /*
     * Compare cards in play. Returns who won current battle or if there was a tie.
     */
    private gameState compareCards(Card p1Card, Card p2Card) {
        System.out.println("P1: " + p1Card);
        System.out.println("P2: " + p2Card);

        if(p1Card.getValue() > p2Card.getValue()){
            System.out.println("Player 1 wins the battle!\n");
            return gameState.PLAYER1WIN;

        }else if(p1Card.getValue() < p2Card.getValue()){
            System.out.println("Player 2 wins the battle!\n");
            return gameState.PLAYER2WIN;
        }

        return gameState.WAR;
    }

    private void checkHands(){
        if(this.players.get(0).getInUseDeck().isEmpty()){
            if(!this.players.get(0).getOffHandDeck().isEmpty()){
                this.players.get(0).swapInUseAndOffHand();

                int handsize = this.players.get(0).getInUseDeck().deckSize();
                int offhandSize = this.players.get(0).getOffHandDeck().deckSize();
                System.out.println("P1: Swapped hands\n " + "In Use size: " + handsize + "\nOffhand size: " + offhandSize);
            }else{
                System.out.println("Player 2 wins the game!");
            }
        }

        if(this.players.get(1).getInUseDeck().isEmpty()){
            if(!this.players.get(1).getOffHandDeck().isEmpty()){
                this.players.get(1).swapInUseAndOffHand();
                // System.out.println("P2: Swapped hands");

                int handsize = this.players.get(1).getInUseDeck().deckSize();
                int offhandSize = this.players.get(1).getOffHandDeck().deckSize();
                System.out.println("P2: Swapped hands\n " + "In Use size: " + handsize + "\nOffhand size: " + offhandSize);
            }else{
                System.out.println("Player 2 wins the game!");
            }
        }
        System.out.println("\n");
    }

    private void giveWinnings(gameState state){
        switch (state) {
            case PLAYER1WIN:
                this.players.get(0).getOffHandDeck().addCardsToDeck(this.pool);
                break;
            case PLAYER2WIN:
                this.players.get(1).getOffHandDeck().addCardsToDeck(this.pool);
                break;            
            default:
                break;
        }
    }

    private void displayPlayerHands() {
        for (int i = 0; i < players.size(); i++) {

            System.out.println("\nPlayer " + (i + 1) + ": ");
            System.out.println("Deck Size:" + this.players.get(i).getInUseDeck().deckSize());
            this.players.get(i).getInUseDeck().printDeck();
        }
    }
}
