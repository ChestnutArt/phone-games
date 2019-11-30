package uoft.csc207.games.model.CardGame;

import uoft.csc207.games.model.Achievement;
import uoft.csc207.games.model.Game;
import uoft.csc207.games.model.IGameID;
import uoft.csc207.games.model.PlayerProfile;

public class CardGame extends Game {

    private int currentScore = 0;

    public CardGame() {
        super();
        this.id = IGameID.CARD;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void updateScore(Integer i) {
        if (i > gameScore) {
            cumulativeScore += i - gameScore;
            super.gameScore = i;
        }
    }

    @Override
    public void updateCurrency(Integer i) {
        cumulativeCurrency += i - gameCurrency;
        gameCurrency = i;
    }

    @Override
    public void restart() {
    }

    @Override
    public void chooseCharacter(String character) {

    }

    @Override
    public void chooseFont(String font) {

    }

    @Override
    public void chooseColor(String color) {

    }

    public int getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }

    @Override
    public void initializeAchievements() {
        Achievement achieve;
        achieve = new Achievement("Game's On", "Damage the opponent",
                1, 0, true, false);
        this.availableAchievements.add(achieve);
        achieve = new Achievement("Body Count", "Deal at least 3000 damage or win a duel",
                7000, 0, true, false);
        this.availableAchievements.add(achieve);
    }
}
