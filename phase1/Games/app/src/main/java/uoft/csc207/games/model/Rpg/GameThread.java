package uoft.csc207.games.model.Rpg;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameThread extends Thread{
    private boolean moving;
    private GameSurface gameSurface;
    private SurfaceHolder surfaceHolder;

    public GameThread(GameSurface gS, SurfaceHolder sH){
        this.gameSurface = gS;
        this.surfaceHolder = sH;
    }

    public void run(){
        long startTime = System.nanoTime();

        while(moving){
            Canvas canvas = null;
            try{
                //Gets Canvas from Holder and locks it
                canvas = this.surfaceHolder.lockCanvas();

                synchronized(canvas){
                    this.gameSurface.update();
                    this.gameSurface.draw(canvas);
                }
            } catch(Exception e){

            } finally {
                if(canvas != null){
                    this.surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
            long currentTime = System.nanoTime();

            long waitTime = (currentTime - startTime) / 1000000;
            if (waitTime < 10){
                waitTime = 10;
            }

            try{
                this.sleep(waitTime);
            } catch(InterruptedException e){

            }
            startTime = System.nanoTime();
        }
    }

    public void setMoving(boolean isMoving){
        moving = isMoving;
    }
}
