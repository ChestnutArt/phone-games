package uoft.csc207.games.model;

import android.content.Intent;

import java.util.ArrayList;

abstract class Game {
    protected int gameScore;
    protected int gameCurrency;

    protected String id;
    protected PlayerProfile owner;

    protected String color;
    protected String character;
    protected String textFont;

    public Game(PlayerProfile p){
        gameScore = 0;
        gameCurrency = 0;
        owner = p;
    }

    abstract String getId();

    /**
     * Updates score of the Game and the total score of the account
     * @param i The amount to add to the score
     */
    abstract void updateScore(int i);

    /**
     * Updates currency of the Game and the total currency of the account
     * @param i The amount to add to the currency
     */
    abstract void updateCurrency(int i);

    /**
     * Clears the game stats
     */
    abstract void restart();

    abstract void chooseCharacter(String character);

    abstract void chooseFont(String font);

    abstract void chooseColor(String color);
}
