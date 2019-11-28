package uoft.csc207.games.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import uoft.csc207.games.R;
import uoft.csc207.games.controller.ProfileManager;
import uoft.csc207.games.controller.Score;
import uoft.csc207.games.controller.ScoreBoard;
import uoft.csc207.games.model.PlayerProfile;

public class AddScoreActivity extends AppCompatActivity {

    private Score score;
    private Button submit;
    private Button cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public AddScoreActivity(Score score){
        this.score = score;
    }

    private void changeName(String nameTag){
        score.setName(nameTag);
    }

    public void sumbitScore(){
        ScoreBoard scoreBoard = new ScoreBoard(this);
    }

}

