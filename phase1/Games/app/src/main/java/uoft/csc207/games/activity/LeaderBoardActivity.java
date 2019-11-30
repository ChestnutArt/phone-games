package uoft.csc207.games.activity;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import uoft.csc207.games.R;
import uoft.csc207.games.controller.Score;
import uoft.csc207.games.controller.ScoreBoard;
import uoft.csc207.games.model.dodger.Constants;

public class LeaderBoardActivity extends AppCompatActivity {
    private ListView score_view;
    private ScoreBoard scoreBoard = new ScoreBoard();
    private ArrayAdapter adapter;
    private Button change;
    private ArrayList<Score> scores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_scores);
        score_view = (ListView)findViewById(R.id.score_board);
        scores = scoreBoard.sortScores(true);
        adapter = new ArrayAdapter(LeaderBoardActivity.this, android.R.layout.simple_list_item_1, viewScore(scores));
        score_view.setAdapter(adapter);
    }

    private ArrayList<String> viewScore(ArrayList<Score> scores){
        ArrayList<String> return_list = new ArrayList<>();
        return_list.add("Scores: ");
        for (Score s: scores){
            return_list.add(s.getName() + ": Money - "+s.getPoints()+" Coins Earned - "+s.getPoints());
        }
        return return_list;
    }

}
