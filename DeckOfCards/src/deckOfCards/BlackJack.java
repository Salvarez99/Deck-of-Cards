package deckOfCards;

import java.util.ArrayList;

public class BlackJack{
    private ArrayList<Player> players;
    private ArrayList<Integer> playerScores;
    private Deck deck;
    private int PLAYERCOUNT = 2;
    private int VALUE_LIMIT = 21;

    public BlackJack() throws InvalidException{
        this.deck = new Deck("std");
        this.players = new ArrayList<>();
        this.playerScores = new ArrayList<>();

        for (int i = 0; i < this.PLAYERCOUNT; i++) {
            this.players.add(new Player());
        }

        for (Player player : this.players) {
            player.getInUseDeck().addCardToDeck(this.deck.drawFromTop());
            player.getOffHandDeck().addCardToDeck(this.deck.drawFromTop());
        }
    }

    /*
     * Allows for additional players. Dealer will be added to passed amount
     */
    public BlackJack(int numOfPlayers) throws InvalidException{
        this.deck = new Deck("std");
        this.players = new ArrayList<>();
        this.playerScores = new ArrayList<>();
        this.PLAYERCOUNT = numOfPlayers + 1;

        for (int i = 0; i < this.PLAYERCOUNT; i++) {
            this.players.add(new Player());
        }

        
        for (Player player : this.players) {
            player.getInUseDeck().addCardToDeck(this.deck.drawFromTop());
            player.getOffHandDeck().addCardToDeck(this.deck.drawFromTop());
        }
    }

    public void run(){

        for (int index = 0; index < this.players.size(); index++) {
            if(index == this.players.size() - 1){

            }else{
                System.out.println("P" + (index + 1) + ": ");
                players.get(index).getInUseDeck().printDeck();
            }
        }
    }

    private void dealerLogic(){

    }

    private boolean canHit(Player player){

        ArrayList<Card> playerHand = new ArrayList<>(player.getInUseDeck().getDeck());
        int sum = 0;

        for (Card card : playerHand) {
            sum += card.getValue();

            //this will add mutiple scores, more than player amount
            this.playerScores.add(sum);
        }

        if(sum < this.VALUE_LIMIT){
            return true;
        }
        return false;
    }


}
