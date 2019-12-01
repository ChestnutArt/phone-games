package uoft.csc207.games.model.card;

/***
 * A class for representing individual cards
 */
public class Card {
    private String name;
    private int cardArt;

    public Card(String name, int cardArt) {
        this.name = name;
        this.cardArt = cardArt;
    }

    public int getCardArt() {
        return cardArt;
    }

    String getName() {
        return name;
    }
}

