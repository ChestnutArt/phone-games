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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_select);
        welcome = findViewById(R.id.tvWelcome);
        logout = findViewById(R.id.btnLogout);
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

    }

    private void moveToLoginActivity(){
        Intent intent = new Intent(GameSelectActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
