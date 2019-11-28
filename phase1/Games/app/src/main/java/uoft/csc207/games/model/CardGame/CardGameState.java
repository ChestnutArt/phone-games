package uoft.csc207.games.model.CardGame;

import java.util.ArrayList;
import java.util.List;
import uoft.csc207.games.R;
import uoft.csc207.games.model.Achievement;
import uoft.csc207.games.model.Game;
import uoft.csc207.games.model.PlayerProfile;


public class CardGameState {

    private CardDeck player_deck, ai_deck; // the cards in the deck
    private int player_health, ai_health;
    private List<Card> ai_hand, player_hand, ai_board, player_board;// the cards in the slots
    private boolean[] p_h, p_brd, ai_brd, ai_h, attacked; //The occupancy of the slots
    private boolean summoned;

    public CardGameState() {
//        super(player);
        player_deck = new CardDeck();
        ai_deck = new CardDeck();
        player_health = 4000;
        ai_health = 4000;
        ai_hand = new ArrayList<>(3);
        ai_board = new ArrayList<>(3);
        player_hand = new ArrayList<>(3);
        player_board = new ArrayList<>();
        p_h = new boolean[3];
        p_brd = new boolean[3];
        ai_h = new boolean[3];
        ai_brd = new boolean[3];
        attacked = new boolean[3];
        summoned = false;
        for (int i = 2; i >= 0; i--) {
            p_h[i] = true;
            p_brd[i] = true;
            ai_h[i] = true;
            ai_brd[i] = true;
            attacked[i] = false;
        }
    }
//
//    @Override
//    public String getId() {
//        return this.owner.getId();
//    }
//
//    @Override
//    public void updateScore(Integer i) {
//        this.owner.setScore(this.owner.getScore() + i);
//    }
//
//    @Override
//    public void updateCurrency(Integer i) {
//        this.owner.setCurrency(this.owner.getScore());
//    }

    public List<Card> getAi_board() {
        return this.ai_board;
    }

    public List<Card> getPlayer_hand() {
        return this.player_hand;
    }

    public List<Card> getAi_hand() {
        return ai_hand;
    }

    public void direct_attack(MonsterCard card, String target) {
        if (target.equals("ai")) {
            if (this.ai_health - card.getAttack() >= 0) {
                this.ai_health = this.ai_health - card.getAttack();
            } else {
                this.ai_health = 0;
            }
        } else {
            if (this.player_health - card.getAttack() >= 0) {
                this.player_health = this.player_health - card.getAttack();
            } else {
                this.player_health = 0;
            }
        }
    }

    public void setSummoned(boolean summoned) {
        this.summoned = summoned;
    }

    public boolean isSummoned() {
        return summoned;
    }

    public boolean[] getAttacked() {
        return attacked;
    }

    public int getAi_health() {
        return this.ai_health;
    }

    public int getPlayer_health() {
        return player_health;
    }

    public boolean[] getP_h() {
        return p_h;
    }

    public boolean[] getAi_h() {
        return ai_h;
    }

    public boolean[] getP_brd() {
        return p_brd;
    }

    public CardDeck getPlayer_deck() {
        return player_deck;
    }

    public List<Card> getPlayer_board() {
        return player_board;
    }

    public void restoreAttack() {
        this.attacked[0] = false;
        this.attacked[1] = false;
        this.attacked[2] = false;
    }

    public void empty_slot_set(List<Card> slots) {
        Card empty_card = new MonsterCard(0, 0, "Empty Slot", R.drawable.square);
        slots.add(empty_card);
        slots.add(empty_card);
        slots.add(empty_card);
    }
}
