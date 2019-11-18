package uoft.csc207.games.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class Game implements Serializable {
    protected Integer gameScore;
    protected Integer gameCurrency;
    /**
     * Collection of all achievements that are still available to be achieved in this game.
     */
    protected ArrayList<Achievement> availableAchievements;

    protected ArrayList<Achievement> completedAchievements;

    protected String id;
    /**
     * Will be removing the PlayerProfile variable in a future push
     */
    protected PlayerProfile owner;

    protected String color;
    protected String character;
    protected String textFont;

    //PlayerProfile parameter is obsolete, will remove soon -William
    public Game(PlayerProfile p){
        gameScore = 0;
        gameCurrency = 0;
        owner = p;
        availableAchievements = new ArrayList<>();
        completedAchievements = new ArrayList<>();
    }

    public Game() {
    }

    public ArrayList<Achievement> getCompletedAchievements() {
        return completedAchievements;
    }

    public abstract String getId();

    /**
     * Updates score of the Game and the total score of the account
     * @param i The amount to add to the score
     */
    public abstract void updateScore(Integer i);

    /**
     * Updates currency of the Game and the total currency of the account
     * @param i The amount to add to the currency
     */
    public abstract void updateCurrency(Integer i);

    /**
     * Clears the game stats
     */
    public abstract void restart();

    public abstract void chooseCharacter(String character);

    public abstract void chooseFont(String font);

    public abstract void chooseColor(String color);

    public int getScore(){
        return gameScore;
    }

    public int getGameCurrency(){
        return gameCurrency;
    }

    public ArrayList<Achievement> getAvailableAchievements(){
        return availableAchievements;
    }

    public String getCharacter(){
        return character;
    }

    public String getFont(){
        return textFont;
    }

    public String getColor(){
        return color;
    }
    /**
     * Initialize all the achievements that can be attained in your game
     */
    public abstract void initializeAchievements();

    /**
     * Checks if any of the achievement conditions are satisfied. Adds them to the PlayerProfile's achievement
     * ArrayList and removes it from the gameAchievements ArrayList so it doesn't need to be checked
     * again. Ideally would call this method whenever you update the game state (specifically when score
     * and/or gold update)
     */
    public void checkAchievements(){
        Iterator<Achievement> itr = availableAchievements.iterator();
        while(itr.hasNext()){
            Achievement curAchievement = itr.next();
            if (curAchievement.isAchieved(gameScore, gameCurrency)){
                completedAchievements.add(curAchievement);
                itr.remove();
            }
        }
    }
}
