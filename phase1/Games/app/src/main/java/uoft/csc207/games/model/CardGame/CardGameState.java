package uoft.csc207.games.model.CardGame;

import java.util.ArrayList;
import java.util.List;

import uoft.csc207.games.R;


public class CardGameState {

    private CardDeck player_deck, ai_deck; // the cards in the deck
    private int player_health, ai_health;
    private List<Card> ai_hand, ai_board, player_hand, player_board; // the cards in the slots
    private boolean[] p_h, p_brd, ai_brd, ai_h; // the occupancy of the slots
    private boolean[] attacked; // track whether each card has attacked this turn
    private boolean summoned; // track whether the player has summoned this turn

    CardGameState() {
        int handCap = 3;
        int boardCap = 3;
        player_deck = new CardDeck();
        ai_deck = new CardDeck();
        player_health = 4000;
        ai_health = 4000;
        ai_hand = new ArrayList<>(handCap);
        ai_board = new ArrayList<>(boardCap);
        player_hand = new ArrayList<>(handCap);
        player_board = new ArrayList<>(boardCap);
        p_h = new boolean[handCap];
        p_brd = new boolean[boardCap];
        ai_h = new boolean[handCap];
        ai_brd = new boolean[boardCap];
        attacked = new boolean[boardCap];
        summoned = false;
        for (int i = 2; i >= 0; i--) {
            p_h[i] = true;
            p_brd[i] = true;
            ai_h[i] = true;
            ai_brd[i] = true;
            attacked[i] = false;
        }
    }

    public List<Card> getAi_board() {
        return this.ai_board;
    }

    List<Card> getPlayer_hand() {
        return this.player_hand;
    }

    List<Card> getAi_hand() {
        return ai_hand;
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

    boolean[] getP_h() {
        return p_h;
    }

    public boolean[] getAi_h() {
        return ai_h;
    }

    boolean[] getP_brd() {
        return p_brd;
    }

    CardDeck getPlayer_deck() {
        return player_deck;
    }

    List<Card> getPlayer_board() {
        return player_board;
    }

    void restoreAttack() {
        this.attacked[0] = false;
        this.attacked[1] = false;
        this.attacked[2] = false;
    }

    void empty_slot_set(List<Card> slots) {
        Card empty_card = new MonsterCard(0, 0, "Empty Slot", R.drawable.square);
        slots.add(empty_card);
        slots.add(empty_card);
        slots.add(empty_card);
    }
}
