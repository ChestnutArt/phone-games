package uoft.csc207.games.model.CardGame;

import uoft.csc207.games.model.Achievement;
import uoft.csc207.games.model.Game;
import uoft.csc207.games.model.IGameID;
import uoft.csc207.games.model.PlayerProfile;

public class CardGame extends Game {

    private int currentScore = 0;
    private String character;
    private String mode;
    private String cardDeck;

    public CardGame() {
        super();
        this.id = IGameID.CARD;
        this.character = "Obama";
        this.mode = "Day";
        this.cardDeck = "Ash";
        initializeAchievements();
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    /**
     * The method updateScore takes a score and compares it with the existing gameScore, if the
     * input is higher than the gameScore, then the difference is taken and added to the cumulative
     * score, and the gameScore is set to be the input as it is the highest
     */
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
        this.character = character;
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
        achieve = new Achievement("Game's On", "\tDamage the opponent",
                1, 0, true, false);
        this.availableAchievements.add(achieve);
        achieve = new Achievement("Body Count", "\tDeal at least 3000 damage or win a duel",
                7000, 0, true, false);
        this.availableAchievements.add(achieve);
    }

    public void setCardDeck(String cardDeck) {
        this.cardDeck = cardDeck;
    }

    public String getCardDeck() {
        return cardDeck;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    @Override
    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }
}
