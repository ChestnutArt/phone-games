package uoft.csc207.games.model.CardGame;

import java.util.ArrayList;

// this is a really simplified card game, based on half-remembered details of how to play Yu-Gi-Oh
// we should replace that comment w/ a better one explaining the class
public class CardGameState {
    // P1 is the player, P2 is AI
    // maybe there should be a player class? but it's not like there'll ever be more players
    // and it would only hold like 3 variables and do nothing else? idk

    private Card[] p1Hand;
    private Card[] p2Hand;
    private int handSize = 5;

    private ArrayList<Card> p1Field = new ArrayList<>();
    private ArrayList<Card> p2Field = new ArrayList<>();

    private int p1HP;
    private int p2HP;

    private CardGameState() {
        int defaultHP = 4000;

        p1Hand = new Card[handSize];
        p2Hand = new Card[handSize];

        // generate each player's hand
        for (int i = 0; i < handSize; i++) {
            p1Hand[i] = Card.generateCard();
            p2Hand[i] = Card.generateCard();
        }

        p1HP = defaultHP;
        p2HP = defaultHP;
    }

    // each player takes their turn, then the board gets updated
    private void Turn() {
        // TODO: get player 1 input, make their moves

        DrawBoard(); // update to reflect player 1's move

        // TODO: add delay before p2 makes a move

        // play a card and then generate a new one
        int p2Play = (int) (Math.random() * handSize);
        p2Field.add(p2Hand[p2Play]);
        p2Hand[p2Play] = Card.generateCard();

        // TODO: player 2 randomly has a card from their field attack one of player 1's if possible
        // if p2 attacks, p1 should have their hp reduced by (p2's card).atk - (p1's card).def
        // and then destroy p1's card
        // this should probably be its own function actually

        DrawBoard(); // update to reflect player 2's move

        if (p1HP <= 0) {
            // TODO: lose condition
        } else if (p2HP <= 0) {
            // TODO: win condition
        }
    }

    // draw each card individually, showing player 1's hand but not player 2's
    private void DrawBoard() {
        // TODO
    }
}
