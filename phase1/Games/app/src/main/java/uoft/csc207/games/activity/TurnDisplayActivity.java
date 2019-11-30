package uoft.csc207.games.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import uoft.csc207.games.R;
import uoft.csc207.games.controller.ProfileManager;
import uoft.csc207.games.controller.rpg.RPGGameManager;
import uoft.csc207.games.model.CardGame.CardActivity;
import uoft.csc207.games.model.Rpg.RpgActivity;
import uoft.csc207.games.model.dodger.ScrollerActivity;

public class TurnDisplayActivity extends AppCompatActivity{
    private TextView currentTurnDisplay;
    private Class destination;
    private List<Class> gameActivities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turn_display);
        currentTurnDisplay = findViewById(R.id.tvCurrentTurn);
        currentTurnDisplay.setText("User " + ProfileManager.getProfileManager(this).getCurrentPlayer().getId() + "'s Turn");

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

    private void initClassTypeLists(){
        gameActivities = new ArrayList<>();
        gameActivities.add(RpgActivity.class);
        gameActivities.add(ScrollerActivity.class);
        gameActivities.add(CardActivity.class);
    }

    private void goToRandomGame(){
        Class destination;
    }
}
