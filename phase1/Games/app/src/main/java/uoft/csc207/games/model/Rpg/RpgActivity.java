package uoft.csc207.games.model.Rpg;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;

import uoft.csc207.games.R;
import uoft.csc207.games.activity.GameSelectActivity;

public class RpgActivity extends Activity implements PopupMenu.OnMenuItemClickListener {

   private GameSurface gameSurface;
   private FrameLayout gameFrame;
   private RelativeLayout widgetHolder;

    public TextView getTextView() {
        return textView;
    }
    public TextView getStatsView() { return statsView; }

    private TextView textView;
    private TextView statsView;
    private Button settingsBtn;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameSurface = new GameSurface(this);
        gameFrame = new FrameLayout(this);
        widgetHolder = new RelativeLayout(this);
        settingsBtn = createButton(widgetHolder);
        textView = createTextView(widgetHolder);
        statsView = createScoreView(widgetHolder);
        statsView.setText("\nScore: 0   \nGold: 0");

        // Set fullscreen
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Set No Title
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        gameFrame.addView(gameSurface);
        gameFrame.addView(widgetHolder);
        //gameFrame.addView(textViewHolder);

        this.setContentView(gameFrame);
        //this.setContentView(new GameSurface(this));

        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(v);
            }
        });
    }
    public void showPopup(View v){
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);

        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.game_menu, popup.getMenu());
        popup.show();
    }
    public void finishGame(){
        Intent myIntent = new Intent(RpgActivity.this, GameSelectActivity.class);
        startActivity(myIntent);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.male_item:
                gameSurface.getGameState().chooseCharacter("male");
                break;
            case R.id.female_item:
                gameSurface.getGameState().chooseCharacter("female");
                break;
            case R.id.black_item:
                gameSurface.getGameState().chooseColor("black");
                textView.setBackgroundColor(Color.BLACK);
                break;
            case R.id.white_item:
                gameSurface.getGameState().chooseColor("white");
                textView.setBackgroundColor(Color.WHITE);
                break;
            case R.id.monospace_item:
                gameSurface.getGameState().chooseFont("monospace");
                textView.setTypeface(Typeface.MONOSPACE);
                break;
            case R.id.sans_serif_item:
                gameSurface.getGameState().chooseFont("sans-serif");
                textView.setTypeface(Typeface.SANS_SERIF);
                break;
            case R.id.exit_rpg_item:
                finishGame();
                break;
        }
        return false;
    }

    private Button createButton(RelativeLayout widgetHolder) {

        Button pauseResumeBtn = new Button(this);
        pauseResumeBtn.setText(R.string.rpg_setting);

        RelativeLayout.LayoutParams params4Btn = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams params4Layout = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        widgetHolder.setLayoutParams(params4Layout);
        widgetHolder.addView(pauseResumeBtn);
        params4Btn.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
        params4Btn.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        pauseResumeBtn.setLayoutParams(params4Btn);
        return pauseResumeBtn;
    }

    private TextView createTextView(RelativeLayout widgetHolder) {
        TextView textView = new TextView(this);

        textView.setTextSize(24);
        textView.setTextColor(Color.RED);

        RelativeLayout.LayoutParams params4Btn = new RelativeLayout.LayoutParams(RelativeLayout.
                LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        //RelativeLayout.LayoutParams params4Layout = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        //textViewHolder.setLayoutParams(params4Layout);
        widgetHolder.addView(textView);
        params4Btn.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        params4Btn.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        params4Btn.addRule(RelativeLayout.TEXT_ALIGNMENT_CENTER, RelativeLayout.TRUE);

        textView.setLayoutParams(params4Btn);

        return textView;
    }

    private TextView createScoreView(RelativeLayout widgetHolder){
        TextView textView = new TextView(this);
        textView.setTextSize(20);
        textView.setTextColor(Color.WHITE);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.
                LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        widgetHolder.addView(textView);
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
        params.addRule(RelativeLayout.TEXT_ALIGNMENT_CENTER, RelativeLayout.TRUE);
        return textView;
    }
}
