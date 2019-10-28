package uoft.csc207.games.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import uoft.csc207.games.model.PlayerProfile;
import uoft.csc207.games.controller.ProfileManager;
import uoft.csc207.games.R;
import uoft.csc207.games.model.Rpg.RpgActivity;

public class GameSelectActivity extends AppCompatActivity {
    private TextView welcome;
    private Button logout;
    private PlayerProfile currentProfile;

    private Button scrollerSelect;
    private Button cardSelect;
    private Button rpgSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_select);
        welcome = findViewById(R.id.tvWelcome);
        logout = findViewById(R.id.btnLogout);

        scrollerSelect = findViewById(R.id.btnScroller);
        cardSelect = findViewById(R.id.btnCard);
        rpgSelect = findViewById(R.id.btnRPG);

        Intent intent = getIntent();
        currentProfile = (PlayerProfile)intent.getSerializableExtra(ProfileManager.CURRENT_PLAYER);
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
                // jump to the scroller game activity here
                // check to see if the PlayerProfile already has an instance of the scroller game
                // and use that instance's game , otherwise create a new one and add it to the PlayerProfile
            }
        });

        cardSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // jump to the card game activity here
                // check to see if the account already has an instance of the card game
                // and use that instance's game , otherwise create a new one and add it to the PlayerProfile
            }
        });

        rpgSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(GameSelectActivity.this, RpgActivity.class);
                startActivity(myIntent);
            }
        });
    }

    private void moveToLoginActivity(){
        Intent intent = new Intent(GameSelectActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
