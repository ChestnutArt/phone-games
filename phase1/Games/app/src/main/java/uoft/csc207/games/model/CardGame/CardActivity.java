package uoft.csc207.games.model.CardGame;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.Random;

import uoft.csc207.games.R;
import uoft.csc207.games.controller.ProfileManager;


public class CardActivity extends AppCompatActivity implements CardClicker, SpellEffect {

    private CardGameState newGame;
    private CardGame gameState;
    private ImageView bottomLeft, bottomMid, bottomRight;
    private ImageView battlePosLeft, battlePosMid, battlePosRight;
    private ImageView upperLeft, upperMid, upperRight;
    private TextView score;
    private View curr_layout;
    private CardPool cardPool;
    private CardDeck playerDeck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_main);
        Intent intent = getIntent();

        //Objects positions set up
        gameState = (CardGame) CardGame.getPlayerProfile().containsGame("257846");
        newGame = new CardGameState();
        playerDeck = newGame.getPlayerDeck();
        bottomLeft = findViewById(R.id.bleft);
        bottomMid = findViewById(R.id.bmid);
        bottomRight = findViewById(R.id.bright);
        battlePosLeft = findViewById(R.id.battle_p_left);
        battlePosMid = findViewById(R.id.battle_p_mid);
        battlePosRight = findViewById(R.id.battle_p_right);
        upperLeft = findViewById(R.id.uleft);
        upperMid = findViewById(R.id.umid);
        upperRight = findViewById(R.id.uright);
        score = findViewById(R.id.score);
        curr_layout = findViewById(R.id.linearLayout);
        cardPool = new CardPool();

        //Sets the CardPool

        cardPool.addNewCard(new MonsterCard(100, 2000, "Ghost Ogre",
                R.drawable.ghost_ogre));
        cardPool.addNewCard(new MonsterCard(1800, 0, "Ash",
                R.drawable.ashblossom));


        //Sets the deck of the player
        String deck_name = intent.getStringExtra("Deck Type");
        if (deck_name.equals("Ash")) {
            playerDeck.addThree("Ghost Ogre", cardPool);
            playerDeck.addThree("Ash", cardPool);
        } else {
            playerDeck.addThree("Ash", cardPool);
            playerDeck.addThree("Ghost Ogre", cardPool);
        }

        //Day mode and night mode changes
        final Button background = findViewById(R.id.background);
        background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //If the button is "Night"
                if (background.getText().length() == 5) {
                    background.setText("Day");
                    curr_layout.setBackgroundColor(Color.DKGRAY);
                } else {
                    background.setText("Night");
                    curr_layout.setBackgroundColor(Color.WHITE);
                }
            }
        });

        //Character changes
        final Button character = findViewById(R.id.character_change);
        final ImageView character_icon = findViewById(R.id.character_icon);
        character.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (character.getText().length() == 11) {
                    character.setText("Obama Mode");
                    character_icon.setImageResource(R.drawable.raegan);
                } else {
                    character.setText("Raegan Mode");
                    character_icon.setImageResource(R.drawable.obama);
                }
            }
        });

        // Card summoning
        bottomLeft.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickSummon(newGame, 0);
                    }
                });

        bottomMid.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickSummon(newGame, 1);
                    }
                });

        bottomRight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickSummon(newGame, 2);
                    }
                });


        // Card attack to another card or direct attack, also checks whether win or not
        battlePosLeft.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickAttack(newGame, 0);
                    }
                });

        battlePosMid.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickAttack(newGame, 1);
                    }
                });

        battlePosRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickAttack(newGame, 2);
            }
        });

        // Card replenishment and Phase process
        final Button action = findViewById(R.id.action);
        action.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        action.setText("Next Turn");
                        TextView score = findViewById(R.id.score);
                        score.setText("HIGH SCORE: " + gameState.getScore());
                        // Determines whether loses
                        int curr_deck_size = newGame.getPlayerDeck().getDeckSize();
                        int hand_occupancy = 0;
                        for (int i = 0; i < newGame.getPlayerHandSize(); i++) {
                            if (newGame.getPlayerHandOccupied(i)) {
                                hand_occupancy++;
                            }
                        }

                        if (curr_deck_size < hand_occupancy) {
                            Snackbar lose_message =
                                    Snackbar.make(
                                            findViewById(R.id.toolbar), R.string.lose_string,
                                            Snackbar.LENGTH_SHORT);
                            lose_message.show();
                        } else {
                            // The ai makes its moves
                            upperLeft.setImageResource(R.drawable.card_back);
                            upperMid.setImageResource(R.drawable.card_back);
                            upperRight.setImageResource(R.drawable.card_back);

                            // Replenish cards in hand
                            for (int i = 0; i < newGame.getPlayerHandSize(); i++) {
                                if (newGame.getPlayerHandOccupied(i)) {
                                    Card next_card = playerDeck.getNextCard();
                                    if (i == 0) {
                                        newGame.setPlayerHand(0, next_card);
                                        bottomLeft.setImageResource(next_card.getCardArt());
                                        playerDeck.removeNextCard();
                                    } else if (i == 1) {
                                        newGame.setPlayerHand(1, next_card);
                                        bottomMid.setImageResource(next_card.getCardArt());
                                        playerDeck.removeNextCard();
                                    } else if (i == 2) {
                                        newGame.setPlayerHand(2, next_card);
                                        bottomRight.setImageResource(next_card.getCardArt());
                                        playerDeck.removeNextCard();
                                    }
                                }
                            }
                        }
                        newGame.setSummoned(false);
                        newGame.restoreAttack();
                    }
                });
    }

    public void clickSummon(CardGameState cardGameState, int posIndex) {
        if (!cardGameState.isSummoned()) {
            if (!cardGameState.getPlayerHandOccupied(posIndex) ==
                    cardGameState.getPlayerBoardOccupied(posIndex)) {
                MonsterCard next_card = (MonsterCard) cardGameState.getPlayerHand(posIndex);
                cardGameState.setPlayerBoard(posIndex, next_card);
                if (posIndex == 0) {
                    battlePosLeft.setImageResource(next_card.getCardArt());
                    bottomLeft.setImageResource(R.drawable.square);
                } else if (posIndex == 1) {
                    battlePosMid.setImageResource(next_card.getCardArt());
                    bottomMid.setImageResource(R.drawable.square);
                } else {
                    battlePosRight.setImageResource(next_card.getCardArt());
                    bottomRight.setImageResource(R.drawable.square);
                }
//                cardGameState.getP_h()[posIndex] = true;
//                cardGameState.getP_brd()[posIndex] = false;
                cardGameState.setSummoned(true);
            } else if (!(cardGameState.getPlayerHand(posIndex).getCardArt() ==
                    R.drawable.square)) {
                Snackbar cannot_summon =
                        Snackbar.make(findViewById(R.id.toolbar), R.string.slot_occupied,
                                Snackbar.LENGTH_SHORT);
                cannot_summon.show();
            }
        } else {
            Snackbar already_summoned = Snackbar.make(findViewById(R.id.toolbar),
                    R.string.summoned, Snackbar.LENGTH_SHORT);
            already_summoned.show();
        }
    }

    public void clickAttack(CardGameState cardGameState, int posIndex) {
        if (!cardGameState.getAttacked(posIndex)) {
            cardGameState.direct_attack(((MonsterCard)
                    cardGameState.getPlayerBoard(posIndex)), "ai");
            TextView ai_lp = findViewById(R.id.ai_lp);
            ai_lp.setText("LP: " + cardGameState.getAiHealth());
            cardGameState.setAttacked(posIndex, true);
            int currentScore = gameState.getCurrentScore();
            gameState.setCurrentScore(currentScore +
                    ((MonsterCard)cardGameState.getPlayerBoard(posIndex)).getAttack());
            if (cardGameState.getAiHealth() == 0) {
                int currScore = gameState.getCurrentScore();
                gameState.setCurrentScore(currScore + 3000);
                gameState.updateScore(gameState.getCurrentScore());
                gameState.setCurrentScore(0);
                score.setText("HIGH SCORE: " + gameState.getScore());
                ProfileManager.getProfileManager(getApplicationContext()).saveProfiles();
                Snackbar winner_msg =
                        Snackbar.make(
                                findViewById(R.id.toolbar), R.string.winner_msg, Snackbar.LENGTH_LONG);
                winner_msg.show();
            }
        } else {
            Snackbar attacked =
                    Snackbar.make(findViewById(R.id.toolbar), R.string.attacked,
                            Snackbar.LENGTH_SHORT);
            attacked.show();
        }
    }

    @Override
    public void destroyOneRandom() {
        Random randCard = new Random();

    }

    @Override
    public void destroyAll() {

    }

    @Override
    public void increaseHP(int healthPoint) {

    }

    @Override
    public void decreaseHP(int healthPoint) {

    }

    @Override
    public void attackAgain() {

    }
}