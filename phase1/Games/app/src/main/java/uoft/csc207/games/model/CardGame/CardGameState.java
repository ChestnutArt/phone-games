package uoft.csc207.games.model.CardGame;


public class CardGameState {

    private CardDeck playerDeck, aiDeck; // the cards in the deck
    private int playerHealth, aiHealth;
    private CardCollection aiHand, aiBoard, playerHand, playerBoard; // the cards in the slots
    private boolean[] attacked; // track whether each card has attacked this turn
    private boolean summoned; // track whether the player has summoned this turn

    CardGameState() {
        int handCap = 3;
        int boardCap = 3;

        playerDeck = new CardDeck();
        aiDeck = new CardDeck();

        playerHealth = 4000;
        aiHealth = 4000;

        aiHand = new CardCollection(handCap);
        aiBoard = new CardCollection(boardCap);
        playerHand = new CardCollection(handCap);
        playerBoard = new CardCollection(boardCap);

        attacked = new boolean[boardCap];
        summoned = false;

        for (int i = 2; i >= 0; i--) {
            attacked[i] = false;
        }
    }

    /**
     * Directly attack target using card, reducing their health by the card's attack
     *
     * @param card   the card that should do the attacking
     * @param target who should be attacked - assumed to be the player unless "ai" is given
     */
    void direct_attack(MonsterCard card, String target) {
        if (target.equals("ai")) {
            // don't let anyone's health go below 0
            this.aiHealth = Math.max(this.aiHealth - card.getAttack(), 0);
        } else {
            this.playerHealth = Math.max(this.playerHealth - card.getAttack(), 0);
        }
    }

    void restoreAttack() {
        this.attacked[0] = false;
        this.attacked[1] = false;
        this.attacked[2] = false;
    }


    // everything after this is getters and setters

    // decks

    CardDeck getPlayerDeck() {
        return playerDeck;
    }

    CardDeck getAiDeck() {
        return aiDeck;
    }

    // health

    public int getPlayerHealth() {
        return playerHealth;
    }

    public void setPlayerHealth(int playerHealth) {
        this.playerHealth = playerHealth;
    }

    int getAiHealth() {
        return this.aiHealth;
    }

    // AI hand

    Card getAiHand(int index) {
        return aiHand.getCard(index);
    }

    Card setAiHand(int index, Card c) {
        return aiHand.setCard(index, c);
    }

    /**
     * Return whether there is a card in the AI's hand at index
     *
     * @param index the index to check
     * @return whether or not the slot in the AI's hand at index is occupied
     */
    public boolean getAiHandOccupied(int index) {
        return aiHand.isOccupied(index);
    }

    // player hand

    Card getPlayerHand(int index) {
        return playerHand.getCard(index);
    }

    Card setPlayerHand(int index, Card c) {
        return playerHand.setCard(index, c);
    }

    /**
     * Return whether there is a card in the player's hand at index
     *
     * @param index the index to check
     * @return whether or not the slot in the player's hand at index is occupied
     */
    boolean getPlayerHandOccupied(int index) {
        return playerHand.isOccupied(index);
    }

    int getPlayerHandSize() {
        return playerHand.getSize();
    }

    Card popPlayerHand(int index) {
        return playerHand.pop(index);
    }

    // boards

    Card getAiBoard(int index) {
        return aiBoard.getCard(index);
    }

    Card getPlayerBoard(int index) {
        return playerBoard.getCard(index);
    }

    Card setPlayerBoard(int index, Card c) {
        return playerBoard.setCard(index, c);
    }

    boolean getPlayerBoardOccupied(int index) {
        return playerBoard.isOccupied(index);
    }

    // attacked

    boolean getAttacked(int index) {
        return attacked[index];
    }

    void setAttacked(int index, boolean value) {
        attacked[index] = value;
    }

    // summoned

    void setSummoned(boolean summoned) {
        this.summoned = summoned;
    }

    boolean isSummoned() {
        return summoned;
    }
}
