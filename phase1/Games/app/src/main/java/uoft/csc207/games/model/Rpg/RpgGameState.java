package uoft.csc207.games.model.Rpg;

import java.util.ArrayList;

import uoft.csc207.games.model.Achievement;
import uoft.csc207.games.model.Game;
import uoft.csc207.games.model.PlayerProfile;

public class RpgGameState extends Game {

    public RpgGameState(PlayerProfile p){
        super(p);
        id = "16812";
    }
    public String getId(){
        return id;
    }
    public void updateScore(Integer i){

    }
    public void updateCurrency(Integer i){

    }
    public void restart(){

    }
    public void chooseCharacter(String character){

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
