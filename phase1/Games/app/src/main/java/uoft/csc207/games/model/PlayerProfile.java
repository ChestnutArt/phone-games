package uoft.csc207.games.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;

public class PlayerProfile implements Serializable {
    private String id;
    private String password;
    private TreeMap games;

    private int playerCurrency;
    private int playerScore;
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

    public int getCurrency(){
        return playerCurrency;
    }

    public int getScore(){
        return playerScore;
    }

    public String getAchievements(){
        String result = "";
        for (Achievement a: playerAchievements){
            result += a.getAchiementName() + ":\n";
            result += a.getDescription() + "\n";
        }
        return result;
    }
}
