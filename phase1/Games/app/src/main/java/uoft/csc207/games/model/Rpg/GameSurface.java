package uoft.csc207.games.model.Rpg;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameSurface extends SurfaceView{ //implements SurfaceHolder.Callback{
    private GameThread gameThread;
    private PlayerCharacter pCharacter;

    public GameSurface(Context context){
        super(context);
        this.setFocusable(true);
        //this.getHolder().addCallback(this);
    }
}


