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

import org.w3c.dom.Text;

import java.util.List;

import uoft.csc207.games.R;
import uoft.csc207.games.activity.GameSelectActivity;
import uoft.csc207.games.controller.ProfileManager;
import uoft.csc207.games.model.Game;
import uoft.csc207.games.model.PlayerProfile;


public class CardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_main);
        Intent intent = getIntent();
        final CardGame gameState = (CardGame) CardGame.getPlayerProfile().containsGame("257846");


        //Objects positions set up
        final CardGameState new_game = new CardGameState();
        final List<Card> player_deck = new_game.getPlayer_deck();
        final ImageView bottom_left = findViewById(R.id.bleft);
        final ImageView bottom_mid = findViewById(R.id.bmid);
        final ImageView bottom_right = findViewById(R.id.bright);
        final ImageView battle_p_left = findViewById(R.id.battle_p_left);
        final ImageView battle_p_mid = findViewById(R.id.battle_p_mid);
        final ImageView battle_p_right = findViewById(R.id.battle_p_right);
        final ImageView uleft = findViewById(R.id.uleft);
        final ImageView umid = findViewById(R.id.umid);
        final ImageView uright = findViewById(R.id.uright);
        final TextView score = findViewById(R.id.score);
        final View curr_layout = findViewById(R.id.linearLayout);

        new_game.empty_slot_set(new_game.getPlayer_hand());
        new_game.empty_slot_set(new_game.getPlayer_board());
        new_game.empty_slot_set(new_game.getAi_hand());

        //Sets the deck of the player
        String deck_name = intent.getStringExtra("Deck Type");
        if (deck_name.equals("Ash")) {
            new_game.add_three(new Card(1800, 0, "Ash", R.drawable.ashblossom));
            new_game.add_three(new Card(100, 2000, "Ghost Ogre", R.drawable.ghost_ogre));
        } else {
            new_game.add_three(new Card(100, 2000, "Ghost Ogre", R.drawable.ghost_ogre));
            new_game.add_three(new Card(1800, 0, "Ash", R.drawable.ashblossom));
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
        bottom_left.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!new_game.isSummoned()) {
                            if (!new_game.getP_h()[0] == new_game.getP_brd()[0]) {
                                Card next_card = new_game.getPlayer_hand().get(0);
                                new_game.getPlayer_board().set(0, next_card);
                                ImageView player_left = findViewById(R.id.battle_p_left);
                                player_left.setImageResource(next_card.getCard_art());
                                bottom_left.setImageResource(R.drawable.square);
                                new_game.getP_h()[0] = true;
                                new_game.getP_brd()[0] = false;
                                new_game.setSummoned(true);
                            } else if (!(new_game.getPlayer_hand().get(0).getCard_art() == R.drawable.square)) {
                                Snackbar cannot_summon =
                                        Snackbar.make(
                                                findViewById(R.id.toolbar), R.string.slot_occupied, Snackbar.LENGTH_SHORT);
                                cannot_summon.show();
                            }
                        } else {
                            Snackbar already_summoned = Snackbar.make(findViewById(R.id.toolbar), R.string.summoned,
                                    Snackbar.LENGTH_SHORT);
                            already_summoned.show();
                        }
                    }
                });

        bottom_mid.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!new_game.isSummoned()) {
                            if (!new_game.getP_h()[1] == new_game.getP_brd()[1]) {
                                Card next_card = new_game.getPlayer_hand().get(1);
                                new_game.getPlayer_board().set(1, next_card);
                                ImageView player_mid = findViewById(R.id.battle_p_mid);
                                player_mid.setImageResource(next_card.getCard_art());
                                bottom_mid.setImageResource(R.drawable.square);
                                new_game.getP_h()[1] = true;
                                new_game.getP_brd()[1] = false;
                                new_game.setSummoned(true);
                            } else if (!(new_game.getPlayer_hand().get(1).getCard_art() == R.drawable.square)) {
                                Snackbar cannot_summon =
                                        Snackbar.make(
                                                findViewById(R.id.toolbar), R.string.slot_occupied, Snackbar.LENGTH_SHORT);
                                cannot_summon.show();
                            }
                        } else {
                            Snackbar already_summoned = Snackbar.make(findViewById(R.id.toolbar), R.string.summoned,
                                    Snackbar.LENGTH_SHORT);
                            already_summoned.show();
                        }
                    }
                });

        bottom_right.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!new_game.isSummoned()) {
                            if (!new_game.getP_h()[2] == new_game.getP_brd()[2]) {
                                Card next_card = new_game.getPlayer_hand().get(2);
                                new_game.getPlayer_board().set(2, next_card);
                                ImageView player_right = findViewById(R.id.battle_p_right);
                                player_right.setImageResource(next_card.getCard_art());
                                bottom_right.setImageResource(R.drawable.square);
                                new_game.getP_h()[2] = true;
                                new_game.getP_brd()[2] = false;
                                new_game.setSummoned(true);
                            } else if (!(new_game.getPlayer_hand().get(2).getCard_art() == R.drawable.square)) {
                                Snackbar cannot_summon =
                                        Snackbar.make(
                                                findViewById(R.id.toolbar), R.string.slot_occupied, Snackbar.LENGTH_SHORT);
                                cannot_summon.show();
                            }
                        } else {
                            Snackbar already_summoned = Snackbar.make(findViewById(R.id.toolbar), R.string.summoned,
                                    Snackbar.LENGTH_SHORT);
                            already_summoned.show();
                        }
                    }
                });


        // Card attack to another card or direct attack, also checks whether win or not
        // TODO Bug concerning attack orders, sometimes the attacks will not register
        battle_p_left.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!new_game.getAttacked()[0]) {
                            new_game.direct_attack(new_game.getPlayer_board().get(0), "ai");
                            TextView ai_lp = findViewById(R.id.ai_lp);
                            ai_lp.setText("LP: " + new_game.getAi_health());
                            new_game.getAttacked()[0] = true;
                            int currentScore = gameState.getCurrentScore();
                            gameState.setCurrentScore(currentScore + new_game.getPlayer_board().get(0).getAttack());
                            if (new_game.getAi_health() == 0) {
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
                                    Snackbar.make(findViewById(R.id.toolbar), R.string.attacked, Snackbar.LENGTH_SHORT);
                            attacked.show();
                        }
                    }
                });

        battle_p_mid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!new_game.getAttacked()[1]) {
                    new_game.direct_attack(new_game.getPlayer_board().get(1), "ai");
                    TextView ai_lp = findViewById(R.id.ai_lp);
                    ai_lp.setText("LP: " + new_game.getAi_health());
                    new_game.getAttacked()[1] = true;
                    int currentScore = gameState.getCurrentScore();
                    gameState.setCurrentScore(currentScore + new_game.getPlayer_board().get(0).getAttack());
                    if (new_game.getAi_health() == 0) {
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
                            Snackbar.make(findViewById(R.id.toolbar), R.string.attacked, Snackbar.LENGTH_SHORT);
                    attacked.show();
                }
            }
        });

        battle_p_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!new_game.getAttacked()[2]) {
                    new_game.direct_attack(new_game.getPlayer_board().get(2), "ai");
                    TextView ai_lp = findViewById(R.id.ai_lp);
                    ai_lp.setText("LP: " + new_game.getAi_health());
                    new_game.getAttacked()[2] = true;
                    int currentScore = gameState.getCurrentScore();
                    gameState.setCurrentScore(currentScore + new_game.getPlayer_board().get(0).getAttack());
                    if (new_game.getAi_health() == 0) {
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
                            Snackbar.make(findViewById(R.id.toolbar), R.string.attacked, Snackbar.LENGTH_SHORT);
                    attacked.show();
                }
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
                        int curr_deck_size = new_game.getPlayer_deck().size();
                        int hand_occupancy = 0;
                        for (boolean empty: new_game.getP_h()) {
                            if (empty) {
                                hand_occupancy++;
                            }
                        }

                        if (curr_deck_size < hand_occupancy) {
                            Snackbar lose_message =
                                    Snackbar.make(
                                            findViewById(R.id.toolbar), R.string.lose_string, Snackbar.LENGTH_SHORT);
                            lose_message.show();
                        } else {


                            // The ai makes its moves
                            uleft.setImageResource(R.drawable.card_back);
                            umid.setImageResource(R.drawable.card_back);
                            uright.setImageResource(R.drawable.card_back);

                            // Replenish cards in hand
                            int i = 0;
                            for (boolean empty : new_game.getP_h()) {
                                if (empty) {
                                    Card next_card = player_deck.get(0);
                                    if (i == 0) {
                                        new_game.getPlayer_hand().set(0, next_card);
                                        bottom_left.setImageResource(next_card.getCard_art());
                                        player_deck.remove(0);
                                        new_game.getP_h()[0] = false;
                                    } else if (i == 1) {
                                        new_game.getPlayer_hand().set(1, next_card);
                                        bottom_mid.setImageResource(next_card.getCard_art());
                                        player_deck.remove(0);
                                        new_game.getP_h()[1] = false;
                                    } else if (i == 2) {
                                        new_game.getPlayer_hand().set(2, next_card);
                                        bottom_right.setImageResource(next_card.getCard_art());
                                        player_deck.remove(0);
                                        new_game.getP_h()[2] = false;
                                    }
                                }
                                i++;
                            }
                        }
                        new_game.setSummoned(false);
                        new_game.restoreAttack();
                    }
                });
    }
}