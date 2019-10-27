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
            }
        });

        cardSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // jump to the card game activity here
            }
        });

        rpgSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // jump to the rpg game activity here
            }
        });
    }

    private void moveToLoginActivity(){
        Intent intent = new Intent(GameSelectActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
