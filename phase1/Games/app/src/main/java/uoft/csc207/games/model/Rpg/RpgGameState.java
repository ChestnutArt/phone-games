package uoft.csc207.games.model.Rpg;

import java.util.ArrayList;

import uoft.csc207.games.model.Achievement;
import uoft.csc207.games.model.Game;
import uoft.csc207.games.model.PlayerProfile;

/**
 * Contains the relevant stats of the Rpg game
 */
public class RpgGameState extends Game {

    public RpgGameState(PlayerProfile p){
        super(p);
        id = "16812";
        updateCurrency(5);
        updateScore(5);
        gameAchievements.add(new Achievement("test", "test description",
                0, 0, false, false));
        p.addGame(this);
    }
    public String getId(){
        return id;
    }
    public void updateScore(Integer i){
        gameScore++;
        owner.setScore(owner.getScore() + 1);
    }
    public void updateCurrency(Integer i){
        gameCurrency++;
        owner.setCurrency(owner.getCurrency() + 1);
    }
    public void restart(){

    }
    public void chooseCharacter(String character){
        this.character = character;
    }
    public void chooseFont(String font){

    }
    public void chooseColor(String color){

    }

    public void initializeAchievements(){
        Achievement temp;
        temp = new Achievement("Adventurer", "Achieved score of 100 in the Rpg",
                100, 0, true, false);
        gameAchievements.add(temp);
        temp = new Achievement("Opportunist", "Increased your money above 0",
                0, 1, false, true);
        gameAchievements.add(temp);
    }
}
