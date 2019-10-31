package uoft.csc207.games.model.Rpg;

import android.annotation.SuppressLint;
import android.app.Activity;
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

import uoft.csc207.games.R;

public class RpgActivity extends Activity implements PopupMenu.OnMenuItemClickListener {

    GameSurface gameSurface;
    FrameLayout gameFrame;
    RelativeLayout buttonHolder;
    boolean isPaused = false;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameSurface = new GameSurface(this);
        gameFrame = new FrameLayout(this);
        buttonHolder = new RelativeLayout(this);

        Button settingsBtn = new Button(this);
        settingsBtn.setText(R.string.rpg_setting);
        settingsBtn.setId(123456);

        RelativeLayout.LayoutParams params4Btn = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams params4Layout = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        buttonHolder.setLayoutParams(params4Layout);
        buttonHolder.addView(settingsBtn);
        params4Btn.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
        params4Btn.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        settingsBtn.setLayoutParams(params4Btn);


        // Set fullscreen
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Set No Title
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        gameFrame.addView(gameSurface);
        gameFrame.addView(buttonHolder);

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

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.male_item:
                break;
            case R.id.female_item:
                break;
            case R.id.black_item:
                break;
            case R.id.white_item:
                break;
            case R.id.droid_sans_item:
                break;
            case R.id.droid_serif_item:
                break;
            case R.id.exit_rpg_item:
                break;

        }
        return false;
    }
}
