package uoft.csc207.games.model.CardGame;

import java.util.ArrayList;
import java.util.List;
import uoft.csc207.games.R;
import uoft.csc207.games.model.Achievement;
import uoft.csc207.games.model.Game;
import uoft.csc207.games.model.PlayerProfile;

// this is a really simplified card game, based on half-remembered details of how to play Yu-Gi-Oh
// we should replace that comment w/ a better one explaining the class
//public class CardGameState {
//    // P1 is the player, P2 is AI
//    // maybe there should be a player class? but it's not like there'll ever be more players
//    // and it would only hold like 3 variables and do nothing else? idk
//
//    private Card[] p1Hand;
//    private Card[] p2Hand;
//    private int handSize = 5;
//
//    private ArrayList<Card> p1Field = new ArrayList<>();
//    private ArrayList<Card> p2Field = new ArrayList<>();
//
//    private int p1HP;
//    private int p2HP;
//
//    private CardGameState() {
//        int defaultHP = 4000;
//
//        p1Hand = new Card[handSize];
//        p2Hand = new Card[handSize];
//
//        // generate each player's hand
//        for (int i = 0; i < handSize; i++) {
//            p1Hand[i] = Card.generateCard();
//            p2Hand[i] = Card.generateCard();
//        }
//
//        p1HP = defaultHP;
//        p2HP = defaultHP;
//    }
//
//    // each player takes their turn, then the board gets updated
//    private void Turn() {
//        // TODO: get player 1 input, make their moves
//
//        DrawBoard(); // update to reflect player 1's move
//
//        // TODO: add delay before p2 makes a move
//
//        // play a card and then generate a new one
//        int p2Play = (int) (Math.random() * handSize);
//        p2Field.add(p2Hand[p2Play]);
//        p2Hand[p2Play] = Card.generateCard();
//
//        // TODO: player 2 randomly has a card from their field attack one of player 1's if possible
//        // if p2 attacks, p1 should have their hp reduced by (p2's card).atk - (p1's card).def
//        // and then destroy p1's card
//        // this should probably be its own function actually
//
//        DrawBoard(); // update to reflect player 2's move
//
//        if (p1HP <= 0) {
//            // TODO: lose condition
//        } else if (p2HP <= 0) {
//            // TODO: win condition
//        }
//    }
//
//    // draw each card individually, showing player 1's hand but not player 2's
//    private void DrawBoard() {
//        // TODO
//    }
//}

public class CardGameState {

    private List<Card> player_deck, ai_deck; // the cards in the deck
    private int player_health, ai_health;
    private List<Card> ai_hand, ai_board, player_board, player_hand; // the cards in the slots
    private boolean[] p_h, p_brd, ai_brd, ai_h, attacked; //The occupancy of the slots
    private boolean summoned;

    public CardGameState() {
//        super(player);
        player_deck = new ArrayList<>(20);
        ai_deck = new ArrayList<>(20);
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

    //Adds 3 copies of the same card to the deck
    public void add_three(Card card) {
        for (int i = 3; i > 0; i--) {
            this.player_deck.add(card);
        }
    }

    public List<Card> getAi_board() {
        return this.ai_board;
    }

    public List<Card> getPlayer_hand() {
        return this.player_hand;
    }

    public List<Card> getAi_hand() {
        return ai_hand;
    }

    public void direct_attack(Card card, String target) {
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

    public List<Card> getPlayer_deck() {
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
        Card empty_card = new Card(0, 0, "Empty Slot", R.drawable.square);
        slots.add(empty_card);
        slots.add(empty_card);
        slots.add(empty_card);
    }
}
