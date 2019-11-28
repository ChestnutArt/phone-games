package uoft.csc207.games.model.CardGame;

/***
 * A class for representing individual cards
 */
public class Card {
    private String name;
    private int card_art;

    public Card(String name, int card_art) {
        this.name = name;
        this.card_art = card_art;
    }

    int getCard_art() {
        return card_art;
    }

    String getName() {
        return name;
    }
}

