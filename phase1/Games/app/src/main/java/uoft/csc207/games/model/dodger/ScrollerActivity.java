package uoft.csc207.games.model.dodger;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

import uoft.csc207.games.activity.GameSelectActivity;
import uoft.csc207.games.controller.ProfileManager;
import uoft.csc207.games.model.Rpg.RpgActivity;

public class ScrollerActivity extends Activity {
    private GamePanel scrollerPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Constants.CURRENT_CONTEXT = this;
        DisplayMetrics d = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(d);
        Constants.SCREEN_HEIGHT = d.heightPixels;
        Constants.SCREEN_WIDTH = d.widthPixels;
        scrollerPanel = new GamePanel(this);
        setContentView(scrollerPanel);
        Constants.activity = this;
    }

    public void finishGame() {
        ProfileManager.getProfileManager(getApplicationContext()).saveProfiles();
        MainThread t = (MainThread)scrollerPanel.getMainThread();
        t.setRunning(false);
        Intent myIntent = new Intent(ScrollerActivity.this, GameSelectActivity.class);
        startActivity(myIntent);
    }
}
