package uoft.csc207.games.model.Rpg;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import uoft.csc207.games.R;

public class RpgActivity extends Activity {

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

        Button pauseResumeBtn = new Button(this);
        pauseResumeBtn.setText(R.string.rpg_setting);
        pauseResumeBtn.setId(123456);

        RelativeLayout.LayoutParams params4Btn = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams params4Layout = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        buttonHolder.setLayoutParams(params4Layout);
        buttonHolder.addView(pauseResumeBtn);
        params4Btn.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        params4Btn.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        pauseResumeBtn.setLayoutParams(params4Btn);


        // Set fullscreen
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Set No Title
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        gameFrame.addView(gameSurface);
        gameFrame.addView(buttonHolder);

        this.setContentView(gameFrame);
        //this.setContentView(new GameSurface(this));

        pauseResumeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });
    }
}
