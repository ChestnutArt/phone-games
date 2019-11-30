package uoft.csc207.games.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import uoft.csc207.games.model.CardGame.CardGame;
import uoft.csc207.games.model.CardGame.DeckSelection;
import uoft.csc207.games.model.Game;
import uoft.csc207.games.model.IGameID;
import uoft.csc207.games.model.PlayerProfile;
import uoft.csc207.games.controller.ProfileManager;
import uoft.csc207.games.R;
import uoft.csc207.games.model.Rpg.RpgActivity;
import uoft.csc207.games.model.Rpg.RpgGameState;
import uoft.csc207.games.model.dodger.Constants;
import uoft.csc207.games.model.dodger.ScrollerActivity;

public class GameSelectActivity extends AppCompatActivity {
    private TextView welcome;
    private Button logout;
    private PlayerProfile currentProfile;
    private PlayerProfile secondProfile;
    private boolean isTwoPlayer;
    private boolean isFirstPlayerTurn = true;

    private Button scrollerSelect;
    private Button cardSelect;
    private Button rpgSelect;
    private Button profileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_select);
        welcome = findViewById(R.id.tvWelcome);
        logout = findViewById(R.id.btnLogout);

        scrollerSelect = findViewById(R.id.btnScroller);
        cardSelect = findViewById(R.id.btnCard);
        rpgSelect = findViewById(R.id.btnRPG);
        profileButton = findViewById(R.id.profileBtn);

        /*Intent intent = getIntent();
        currentProfile = (PlayerProfile)intent.getSerializableExtra(ProfileManager.CURRENT_PLAYER);*/
        currentProfile = ProfileManager.getProfileManager(this).getCurrentPlayer();
        welcome.setText("Welcome back " + currentProfile.getId() + "!");

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProfileManager.getProfileManager(getApplicationContext()).saveProfiles();
                moveToLoginActivity();
            }
        });

        scrollerSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sIntent = new Intent(GameSelectActivity.this, ScrollerActivity.class);
                Constants.player = currentProfile;
                startActivity(sIntent);
            }
        });

        cardSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(currentProfile.containsGame(IGameID.CARD) instanceof CardGame)) {
                    currentProfile.addGame(new CardGame());
                }
                CardGame.setPlayerProfile(currentProfile);
                Intent cardIntent = new Intent(GameSelectActivity.this, DeckSelection.class);
                startActivity(cardIntent);
            }
        });
        /**
         * Button that transfers to RPG game
         */
        rpgSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(GameSelectActivity.this, RpgActivity.class);
                //Game game = currentProfile.containsGame(IGameID.RPG);
                /*if (game == null){
                    //dependency injection design
                    Game newRpgGame = new RpgGameState();
                    currentProfile.addGame(newRpgGame);
                }*/
                startActivity(myIntent);
            }
        });

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(GameSelectActivity.this, ProfileActivity.class);
                myIntent.putExtra(ProfileManager.CURRENT_PLAYER, currentProfile);
                startActivity(myIntent);
            }
        });


    }

    private void moveToLoginActivity(){
        Intent intent = new Intent(GameSelectActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
