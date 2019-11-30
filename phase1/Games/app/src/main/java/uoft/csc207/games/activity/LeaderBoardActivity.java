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

public class LeaderBoardActivity extends AppCompatActivity {
    private ListView score_view = (ListView)findViewById(R.id.score_board);
    private ScoreBoard scoreBoard = new ScoreBoard(this);
    private ArrayAdapter adapter;
    private Button change;
    private ArrayList<Score> scores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scores = scoreBoard.sortScores(true);
        adapter = new ArrayAdapter(LeaderBoardActivity.this, android.R.layout.simple_list_item_1, viewScore(scores));
        score_view.setAdapter(adapter);
    }

    private ArrayList viewScore(ArrayList<Score> scores){
        ArrayList<String> return_list = new ArrayList<>();
        for (Score s: scores){
            return_list.add(s.getName() + ": Money - "+s.getPoints()+" Coins Earned - "+s.getPoints());
        }
        return return_list;
    }

}
