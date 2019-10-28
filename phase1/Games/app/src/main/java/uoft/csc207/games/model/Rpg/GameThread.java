package uoft.csc207.games.model.Rpg;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameThread extends Thread{
    private boolean moving;
    private GameSurface gameSurface;
    private SurfaceHolder surfaceHolder;

    public GameThread(GameSurface gs, SurfaceHolder sh){
        this.gameSurface = gs;
        this.surfaceHolder = sh;
    }

    /*public void move(){
        long startTime = System.nanoTime();

        while(moving){
            Canvas canvas = null;
            try{
                canvas = this.surfaceHolder.lockCanvas();

                synchronized (canvas){
                    this.gameSurface.update();
                    this.gameSurface.draw(canvas);
                }
            } catch (Exception e){

            }
        }
    }   */
}
