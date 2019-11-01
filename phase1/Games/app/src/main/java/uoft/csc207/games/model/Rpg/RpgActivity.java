package uoft.csc207.games.model.Rpg;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
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

public class RpgActivity extends Activity implements PopupMenu.OnMenuItemClickListener {

   //private GameSurface gameSurface;
   //private FrameLayout gameFrame;
   //private RelativeLayout widgetHolder;

    public TextView getTextView() {
        return textView;
    }

    private TextView textView;
    private Button settingsBtn;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GameSurface gameSurface = new GameSurface(this);
        FrameLayout gameFrame = new FrameLayout(this);
        RelativeLayout widgetHolder = new RelativeLayout(this);
        settingsBtn = createButton(widgetHolder);
        textView = createTextView(widgetHolder);

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

        textView.setTextSize(20);
        textView.setTextColor(Color.RED);

        RelativeLayout.LayoutParams params4Btn = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        //RelativeLayout.LayoutParams params4Layout = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        //textViewHolder.setLayoutParams(params4Layout);
        widgetHolder.addView(textView);
        params4Btn.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        params4Btn.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        params4Btn.addRule(RelativeLayout.TEXT_ALIGNMENT_CENTER, RelativeLayout.TRUE);

        textView.setLayoutParams(params4Btn);

        return textView;
    }
}
