package uoft.csc207.games.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.TextView;

import uoft.csc207.games.R;
import uoft.csc207.games.controller.ProfileManager;
import uoft.csc207.games.controller.rpg.RPGGameManager;

public class TurnDisplayActivity extends AppCompatActivity{
    private TextView currentTurnDisplay;
    private Class destination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turn_display);
        currentTurnDisplay = findViewById(R.id.tvCurrentTurn);
        currentTurnDisplay.setText("User " + ProfileManager.getProfileManager(this).getCurrentPlayer().getId() + "'s Turn");

        View turnView = findViewById(R.id.currentTurnLayout);

    }
    public boolean onTouchEvent(MotionEvent event){
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            if (ProfileManager.getProfileManager(this).isFirstTurn()){

            } else {

            }
            Intent myIntent = new Intent(TurnDisplayActivity.this, GameSelectActivity.class);
            startActivity(myIntent);
            return true;
        } else {
            return false;
        }
    }
}
