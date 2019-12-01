package uoft.csc207.games.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Game implements Serializable {
    /**
     * Abstract Super Class for all games
     *
     * Fields
     * gameScore: Integer - Current score of game
     * gameCurrency: Integer - Current Money Earned in game
     * cumulativeScore: Integer - Cumulative Points earned in game
     * cumulativeCurrency: Integer - Cumulative Money Earned in game
     * availableAchievements: ArrayList<availableAchievements> - Collection of all
     *                     achievements that are still available to be achieved in this game.
     * completedAchievements: ArrayList<availableAchievements> - Collection of all
     *                          achievements that were achieved in this game.
     * id: String - ID of Game
     * color: String - Color customisation of Game
     * character: String - Character customisation of Game
     * textFont: String - Font customisation of Game
     */

    protected Integer gameScore;
    protected Integer gameCurrency;
    protected Integer cumulativeScore;
    protected Integer cumulativeCurrency;
    protected List<Achievement> availableAchievements;
    private List<Achievement> completedAchievements;
    protected String id;
    protected String color;
    protected String character;
    protected String textFont;

    public Game(){
        gameScore = 0;
        gameCurrency = 0;
        cumulativeScore = 0;
        cumulativeCurrency = 0; 
        //owner = p;
        availableAchievements = new ArrayList<>();
        completedAchievements = new ArrayList<>();
    }

    /**
     * @return completedAchievements
     */
    ArrayList<Achievement> getCompletedAchievements() {
        return (ArrayList<Achievement>)completedAchievements;
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
     * @param i: Cumulative Currency to be set in game
     */
    public void setCumulativeCurrency(Integer i){ cumulativeCurrency = i; }

    /**
     * @return cumulativeCurrency
     */
    public int getCumulativeCurrency(){ return cumulativeCurrency; }

    /**
     * @param i: Cumulative Score to be set in game
     */
    public void setCumulativeScore(Integer i){ cumulativeScore = i; }

    /**
     * @return cumulativeScore
     */
    public int getCumulativeScore(){ return cumulativeScore; }

    /**
     * Clears the game stats
     */
    public abstract void restart();

    /**
     * @param character (String) character to be chosen
     */
    public abstract void chooseCharacter(String character);

    /**
     * @param font font to be chosen
     */
    public abstract void chooseFont(String font);

    /**
     * @param color to be chosen
     */
    public abstract void chooseColor(String color);

    public int getScore(){
        return gameScore;
    }

    /**
     * @return gameCurrency
     */
    public int getGameCurrency(){
        return gameCurrency;
    }

    /**
     * @return character
     */
    public String getCharacter(){
        return character;
    }

    /**
     * @return textFont
     */
    public String getFont(){
        return textFont;
    }

    /**
     * @return color
     */
    public String getColor(){
        return color;
    }

    /**
     * Initialize all the achievements that can be attained in your game
     */
    public abstract void initializeAchievements();

    /**
     * Checks if any of the availableAchievements are satisfied, and moves any that are to the
     * completedAchievements array list so it's no longer checked.
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
