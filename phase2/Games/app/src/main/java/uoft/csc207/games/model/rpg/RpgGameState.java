package uoft.csc207.games.model.Rpg;

import uoft.csc207.games.model.Achievement;
import uoft.csc207.games.model.Game;
import uoft.csc207.games.model.IGameID;

/**
 * Contains the relevant stats of the Rpg game
 */
public class RpgGameState extends Game {
    public final static String FONT_TYPE_MONOSPACE = "monospace";
    public final static String FONT_TYPE_SANS_SERIF = "sans-serif";
    public final static String FONT_COLOR_BLACK = "black";
    public final static String FONT_COLOR_WHITE = "white";
    public final static String FONT_COLOR_RED = "red";

    public RpgGameState(){
        super();
        id = IGameID.RPG;
        //updateCurrency(1);
        initializeAchievements();
    }

    public String getId(){
        return id;
    }
    public void updateScore(Integer i){
        gameScore += i;
        cumulativeScore += i;
    }
    public void updateCurrency(Integer i){
        gameCurrency += i;
        cumulativeCurrency += i;
    }
    public void restart(){
        gameCurrency = 0;
        gameScore = 0; 
    }
    public void chooseCharacter(String character){
        this.character = character;
    }
    public void chooseFont(String font){
        this.textFont = font;
    }
    public void chooseColor(String color){
        this.color = color;
    }

    /**
     * Initializes all the attainable achievements in the rpg game
     */
    public void initializeAchievements(){
        Achievement temp;
        temp = new Achievement("Adventurer", "\tAchieve score of at least 100 in the Rpg.",
                100, 0, true, false);
        availableAchievements.add(temp);
        temp = new Achievement("Talkative", "\tAchieve score of at least 200 in the Rpg",
                200, 0, true, false);
        availableAchievements.add(temp);
        temp = new Achievement("Moving up in the world", "\tGet 1 gold.",
                0, 1, false, true);
        availableAchievements.add(temp);
        temp = new Achievement("Enough for a meal", "\tGet 10 gold.", 0,
                10, false, true);
    }
}
