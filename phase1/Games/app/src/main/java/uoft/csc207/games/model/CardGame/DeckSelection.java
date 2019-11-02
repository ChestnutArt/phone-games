package uoft.csc207.games.model.CardGame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import uoft.csc207.games.R;
import uoft.csc207.games.controller.ProfileManager;
import uoft.csc207.games.model.PlayerProfile;

public class DeckSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deck_selection);
        ImageView ash = findViewById(R.id.imageView);
        ImageView g_ogre = findViewById(R.id.imageView2);

        ash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeckSelection.this, CardActivity.class);
                intent.putExtra("Deck Type", "Ash");
                startActivity(intent);
            }
        });

        g_ogre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeckSelection.this, CardActivity.class);
                intent.putExtra("Deck Type", "G_ogre");
                startActivity(intent);
            }
        });

    }
}
