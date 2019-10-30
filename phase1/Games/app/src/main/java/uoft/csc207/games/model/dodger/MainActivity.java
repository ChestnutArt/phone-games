package uoft.csc207.games.model.dodger;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

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
        setContentView(new GamePanel(Constants.CURRENT_CONTEXT));
    }
}
