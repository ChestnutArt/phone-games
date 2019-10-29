package uoft.csc207.games.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;

public class PlayerProfile implements Serializable {
    private String id;
    private String password;
    private ArrayList<Game> games;

    private int playerCurrency;
    private int playerScore;
    /**
     * Achievements players has gotten across all games. Likewise for currency and score.
     */
    private ArrayList<Achievement> playerAchievements;

    public PlayerProfile(String id, String password) {
        this.id = id;
        this.password = password;
        playerCurrency = 0;
        playerScore = 0;
        playerAchievements = new ArrayList<>();
    }

    public String getId() { return id; }

    public String getPassword() {
        return password;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCurrency(){ return playerCurrency; }

    public void setCurrency(int newCurrency) { playerCurrency = newCurrency; }

    public int getScore(){ return playerScore; }

    public void setScore(int newScore) { playerScore = newScore; }

    /**
     * Gets all the player's current achievements. Originally created this for display purposes when
     * the user clicks the profile button in GameSelectActivity, might change it to return a collection
     * of Achievements instead.
     * @return Returns a String representation of all the achievements
     */
    private String getAchievements(){
        String result = "";
        for (Achievement a: playerAchievements){
            result += a.getAchievementName() + ":\n";
            result += a.getDescription() + "\n";
        }
        return result;
    }

    /**
     * Checks if the PlayerProfile already owns the game
     * @param gameId The id of the game that is being searched for
     * @return The game with the given id and null if the PlayerProfile doesn't contain it
     */
    private Game containsGame(String gameId){
        for (Game g: games){
            if (g.getId().equals(gameId)){
                return g;
            }
        }
        return null;
    }

    /**
     *  Adds a game to the PlayerProfile's collection. Each profile should only have one instance of
     *  each type of game at most.
     * @param newGame The new instance of the Game to be added
     */
    private void addGame(Game newGame){
        if (containsGame(newGame.getId()) == null){
            games.add(newGame);
        }
    }

    /**
     *
     * @param newAchievement The achievement to be added to the PlayerProfile's collection
     */
    public void addAchievement(Achievement newAchievement){
        playerAchievements.add(newAchievement);
    }
}
