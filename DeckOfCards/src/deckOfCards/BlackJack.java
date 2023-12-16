package deckOfCards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BlackJack{

    //Can make use of a hashmap to keep track of player scores
    private ArrayList<Player> players;
    private HashMap<Player, Integer> playerScores;
    private Deck deck;
    private int PLAYERCOUNT = 2;
    private int VALUE_LIMIT = 21;

    public BlackJack() throws InvalidException{
        this.deck = new Deck("std");
        this.players = new ArrayList<>();
        this.playerScores = new HashMap<>();

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
        this.playerScores = new HashMap<>();

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
                this.dealerLogic(this.players.get(this.PLAYERCOUNT - 1));
            }else{
                System.out.println("P" + (index + 1) + ": ");
                players.get(index).getInUseDeck().printDeck();
            }
        }

        //While there are still players playing

        //end loop

        //Check who won

    }

    private void dealerLogic(Player player){
        if(this.canHit(player)){
            Card drawnCard = this.deck.drawFromTop();
            player.getInUseDeck().addCardToDeck(drawnCard);
            player.getInUseDeck().printDeck();
            System.out.println("[]");

        }else{
            System.out.println("Could not hit, needs to wait to show cards now");
        }
    }

    private boolean canHit(Player player){

        ArrayList<Card> playerHand = new ArrayList<>(player.getInUseDeck().getDeck());
        int sum = 0;

        for (Card card : playerHand) {
            sum += card.getValue();

            //this will add mutiple scores, more than player amount
            this.playerScores.put(player, sum);
        }

        if(sum < this.VALUE_LIMIT){
            return true;
        }
        return false;
    }

    public void checkWinner(){

        //need to find all max values that are less than value limit and put into a list
        //then identify the keys associated to those values
        //if there is one key in the list, then there is one winner
        //if there is more than one key in the list, then it is a tie (no one wins or loses)
        for (Map.Entry<Player, Integer> playerScores : this.playerScores.entrySet()) {
            this.playerScores.    
        }
    }
}
