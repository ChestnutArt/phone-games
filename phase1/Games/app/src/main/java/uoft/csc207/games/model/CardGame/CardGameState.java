package uoft.csc207.games.model.CardGame;


public class CardGameState {

    private CardDeck player_deck, ai_deck; // the cards in the deck
    private int player_health, ai_health;
    private CardCollection ai_hand, ai_board, player_hand, player_board; // the cards in the slots
    private boolean[] attacked; // track whether each card has attacked this turn
    private boolean summoned; // track whether the player has summoned this turn

    CardGameState() {
        int handCap = 3;
        int boardCap = 3;

        player_deck = new CardDeck();
        ai_deck = new CardDeck();

        player_health = 4000;
        ai_health = 4000;

        ai_hand = new CardCollection(handCap);
        ai_board = new CardCollection(boardCap);
        player_hand = new CardCollection(handCap);
        player_board = new CardCollection(boardCap);

        attacked = new boolean[boardCap];
        summoned = false;

        for (int i = 2; i >= 0; i--) {
            attacked[i] = false;
        }
    }

    Card getAi_board(int index) {
        return ai_board.getCard(index);
    }

    Card getPlayer_hand(int index) {
        return player_hand.getCard(index);
    }

    Card getAi_hand(int index) {
        return ai_hand.getCard(index);
    }

    Card setPlayer_hand(int index, Card c) {
        return player_hand.setCard(index, c);
    }

    Card setAi_hand(int index, Card c) {
        return ai_hand.setCard(index, c);
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
            this.ai_health = Math.max(this.ai_health - card.getAttack(), 0);
        } else {
            this.player_health = Math.max(this.player_health - card.getAttack(), 0);
        }
    }

    void setSummoned(boolean summoned) {
        this.summoned = summoned;
    }

    boolean isSummoned() {
        return summoned;
    }

    boolean[] getAttacked() {
        return attacked;
    }

    int getAi_health() {
        return this.ai_health;
    }

    public int getPlayer_health() {
        return player_health;
    }

    boolean getPlayer_hand_occupied(int index) {
        return player_hand.isOccupied(index);
    }

    public boolean getAi_hand_occupied(int index) {
        return ai_hand.isOccupied(index);
    }

    boolean getPlayer_board_occupied(int index) {
        return player_board.isOccupied(index);
    }

    CardDeck getPlayer_deck() {
        return player_deck;
    }

    Card getPlayer_board(int index) {
        return player_board.getCard(index);
    }

    Card setPlayer_board(int index, Card c) {
        return player_board.setCard(index, c);
    }

    int getPlayer_hand_size() {
        return player_hand.getSize();
    }

    void restoreAttack() {
        this.attacked[0] = false;
        this.attacked[1] = false;
        this.attacked[2] = false;
    }
}
