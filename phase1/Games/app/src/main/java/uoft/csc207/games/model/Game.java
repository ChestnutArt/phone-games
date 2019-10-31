package uoft.csc207.games.model;

import android.content.Intent;

import java.util.ArrayList;
import java.util.Iterator;

import uoft.csc207.games.controller.ProfileManager;

abstract class Game {
    protected int gameScore;
    protected int gameCurrency;
    /**
     * Collection of all attainable achievements in this game.
     */
    protected ArrayList<Achievement> gameAchievements;

    protected String id;
    /**
     * Binomial association should be fine here, and Game needs easy access to the current player anyways.
     * Because the owner will always be the current player when a given Game instance is used to play
     * the game, and because there's no way to set the owner of a game to something else, there should
     * be no problem from a design perspective.
     */
    protected PlayerProfile owner;

    protected String color;
    protected String character;
    protected String textFont;

    public Game(PlayerProfile p){
        gameScore = 0;
        gameCurrency = 0;
        owner = p;
        gameAchievements = new ArrayList<>();
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

    /**
     * Initialize all the achievements that can be attained in your game
     */
    abstract void initializeAchievements();

    /**
     * Checks if any of the achievement conditions are satisfied. Adds them to the PlayerProfile's achievement
     * ArrayList and removes it from the gameAchievements ArrayList so it doesn't need to be checked
     * again. Ideally would call this method whenever you update the game state (specifically when score
     * and/or gold update)
     */
    public void checkAchievements(){
        Iterator<Achievement> itr = gameAchievements.iterator();
        while(itr.hasNext()){
            Achievement curAchievement = itr.next();
            if (curAchievement.isAchieved(gameScore, gameCurrency)){
                owner.addAchievement(curAchievement);
                itr.remove();
            }
        }
    }
}
