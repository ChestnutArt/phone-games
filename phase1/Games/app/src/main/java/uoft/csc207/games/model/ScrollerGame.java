package uoft.csc207.games.model;


public class ScrollerGame extends Game {


    public int HighScore;

    public ScrollerGame(PlayerProfile p){
        super(p);
        initializeAchievements();
        this.id = p.getId() + ": Scroller";
        this.owner = p;
        gameCurrency = p.getCurrency();
        HighScore = 0;
    }


    String getId(){return this.id;}

    /**
     * Updates score of the Game and the total score of the account
     * @param i The amount to add to the score
     */
    public void updateScore(int i){this.gameScore = i;}

    /**
     * Updates currency of the Game and the total currency of the account
     * @param i The amount to add to the currency
     */
    public void updateCurrency(int i){
        this.gameCurrency = i;
    }

    public void setHighScore(int h){
        this.HighScore = h;
    }

    public int getScore(){
        return this.gameScore;
    }
    public int getCurrency(){
        return this.gameCurrency;
    }

    /**
     * Clears the game stats
     */
    void restart(){gameScore = 0;}

    void chooseCharacter(String character){this.character = character;}

    void chooseFont(String font){this.textFont = font;}

    void chooseColor(String color){this.color = color;}

    /**
     * Initialize all the achievements that can be attained in your game
     */
    void initializeAchievements(){
        for (int i = 50; i < 15000; i = i * 2){
            i = i - (i % 100); //round down
            String name = "Scored: " + i;
            String description = "Player reached this score.";
            gameAchievements.add(new Achievement(name, description, i, 0, true, false));
        }
    }
}
