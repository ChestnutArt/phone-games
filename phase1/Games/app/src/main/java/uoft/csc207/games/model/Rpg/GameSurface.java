package uoft.csc207.games.model.Rpg;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import uoft.csc207.games.R;

public class GameSurface extends SurfaceView implements SurfaceHolder.Callback{
    private GameThread gameThread;
    private PlayerCharacter pCharacter;

    public GameSurface(Context context){
        super(context);
        this.setFocusable(true);
        this.getHolder().addCallback(this);
    }

    public void update(){
        this.pCharacter.update();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            gameThread.setMoving(true);

            int x = (int) event.getX();
            int y = (int) event.getY();

            int movementVectorX = x - this.pCharacter.getX();
            int movementVectorY = y - this.pCharacter.getY();

            pCharacter.setMovementVector(movementVectorX, movementVectorY);
            pCharacter.setDestinationCoordinates(x, y);
            return true;
        }
        return false;
    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        pCharacter.draw(canvas);
    }

    public void surfaceCreated(SurfaceHolder holder){
        Bitmap pcBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.c1_sprite_sheet);
        this.pCharacter = new PlayerCharacter(this, pcBitmap, 200, 200);
        this.gameThread = new GameThread(this, holder);
        this.gameThread.setMoving(true);
        this.gameThread.start();
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){

    }

    public void surfaceDestroyed(SurfaceHolder holder){
        boolean retry = true;
        while(retry){
            try{
                this.gameThread.setMoving(false);
                this.gameThread.join();
            } catch(InterruptedException e){
                e.printStackTrace();
            }
            retry = true;
        }
    }
}


