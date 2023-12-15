package deckOfCards;

public class InvalidException extends Exception{
    public InvalidException(String statement){
        super(statement);
    }
}
