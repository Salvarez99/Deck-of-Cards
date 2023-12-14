package deckOfCards;

import java.util.ArrayList;

public class WarGame {
    private Deck deck;
    private ArrayList<Player> players;
    private int PLAYERCOUNT = 2;
    private static Deck pool;

    enum gameState{
        NONE,
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

    public void run() {
        
        Deck player1Deck = players.get(0).getInUseDeck();
        Deck player2Deck = players.get(1).getInUseDeck();

        Deck player1OH = players.get(0).getOffHandDeck();
        Deck player2OH = players.get(1).getOffHandDeck();

        while(true){
            
            gameState status = checkHands(player1Deck, player2Deck, player1OH, player2OH);
            if(status == gameState.PLAYER1WIN){
                break;
            }else if( status == gameState.PLAYER2WIN){
                break;
            }


            Card p1Card = player1Deck.drawFromTop();
            Card p2Card = player2Deck.drawFromTop();

            System.out.println("Player 1 Deck size: " + player1Deck.deckSize());
            System.out.println("Player 1 OH Deck size: " + player1OH.deckSize());
            
            System.out.println("Player 2 Deck size: " + player2Deck.deckSize());
            System.out.println("Player 2 OH Deck size: " + player2OH.deckSize());

            if(player1Deck.deckSize() == 1){
                System.out.println("stuff");
            }

            compareCards(p1Card, p2Card, player1OH, player2OH);
        }
        
    }

    public void war(Deck p1OffHand, Deck p2OffHand) {
        /*
         * Interesting bug
         * If I get into double or more war I lose the previous wars (Fixed)
         * Not holding played cards in pool
         * 
         * 
         */
        System.out.println("Declaring War!!!");

        for (int i = 0; i < 3; i++) {
            for (Player player : players) {

                if (!player.getInUseDeck().isEmpty()) {
                    pool.addCardToDeck(player.getInUseDeck().drawFromTop());
                } else {
                    player.setInUseDeck(player.getOffHandDeck());
                    pool.addCardToDeck(player.getInUseDeck().drawFromTop());
                }
            }
        }

        Card p1Card = players.get(0).getInUseDeck().drawFromTop();
        Card p2Card = players.get(1).getInUseDeck().drawFromTop();
        pool.addCardToDeck(p1Card);
        pool.addCardToDeck(p2Card);

        System.out.println("P1: [] [] []");

        System.out.println("P2: [] [] []");
        if (compareCards(p1Card, p2Card, p1OffHand, p2OffHand)) {

            p1OffHand.addCardsToDeck(pool);
        } else {
            p2OffHand.addCardsToDeck(pool);
        }
    }

    private boolean compareCards(Card p1Card, Card p2Card, Deck p1OffHand, Deck p2OffHand) {

        if (p1Card.getValue() > p2Card.getValue()) {
            System.out.println("\nP1: " + p1Card);
            System.out.println("P2: " + p2Card);

            // Player one takes pool
            p1OffHand.addCardToDeck(p1Card);
            p1OffHand.addCardToDeck(p2Card);

            System.out.println("\nP1: Takes pool!!!");
            return true;

        } else if (p1Card.getValue() < p2Card.getValue()) {
            System.out.println("\nP1: " + p1Card);
            System.out.println("P2: " + p2Card);

            // Player two takes pool
            p2OffHand.addCardToDeck(p1Card);
            p2OffHand.addCardToDeck(p2Card);

            System.out.println("\nP2: Takes pool!!!");
            return false;
        } else if (p1Card.getValue() == p2Card.getValue()) {
            war(p1OffHand, p2OffHand);
        } else {
            System.out.println("Card is null?");
        }

        // shouldnt reach here
        return true;
    }

    private gameState checkHands(Deck player1Deck, Deck player2Deck, Deck player1OH, Deck player2OH){
        if(player1Deck.isEmpty()){
                if(!player1OH.isEmpty()){
                    players.get(0).swapInUseAndOffHand();
                    player1Deck = players.get(0).getInUseDeck();
                    player1OH = players.get(0).getOffHandDeck();
        
                }else{
                    System.out.println("Player 2 Wins!!!");
                    return gameState.PLAYER2WIN;
                }
            }

            if(player2Deck.isEmpty()){
                if(!player2OH.isEmpty()){
                    players.get(1).swapInUseAndOffHand();
                    player2Deck = players.get(1).getInUseDeck();
                    player2OH = players.get(1).getOffHandDeck();

                }else{
                    System.out.println("Player 1 Wins!!!");
                    return gameState.PLAYER1WIN;
                }
            }
        return gameState.NONE;
    }

    private void displayPlayerHands() {
        for (int i = 0; i < players.size(); i++) {

            System.out.println("\nPlayer " + (i + 1) + ": ");
            System.out.println("Deck Size:" + this.players.get(i).getInUseDeck().deckSize());
            this.players.get(i).getInUseDeck().printDeck();
        }
    }
}
