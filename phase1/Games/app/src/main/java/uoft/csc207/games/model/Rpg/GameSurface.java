package uoft.csc207.games.model.Rpg;

import android.content.Context;
import android.graphics.Canvas;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


import uoft.csc207.games.controller.rpg.RPGGameManager;

public class GameSurface extends SurfaceView implements SurfaceHolder.Callback{
    private RpgActivity rpgActivity;
    private GameThread gameThread;

    public RPGGameManager getRpgGameManager() {
        return rpgGameManager;
    }

    private RPGGameManager rpgGameManager;

    public GameSurface(Context context){
        super(context);
        getHolder().addCallback(this);
        gameThread = new GameThread(this, getHolder());
        rpgActivity = (RpgActivity)context;
        rpgGameManager = new RPGGameManager(context);
        setFocusable(true);
    }

    public GameThread getGameThread(){
        return gameThread;
    }

    public void update(){
        rpgGameManager.update();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        /*
            If game has ended, exit after a delay of 10 seconds
         */
        if (rpgGameManager.isGameEnded()){
            rpgActivity.finishGame(10000);
        }

        if (event.getAction() == MotionEvent.ACTION_DOWN){
            int x = (int) event.getX();
            int y = (int) event.getY();
            rpgGameManager.setPlayerCharacterDestination(x, y);
            return true;
        }
        return false;
    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        rpgGameManager.draw(canvas);
    }

    public void surfaceCreated(SurfaceHolder holder){
        rpgGameManager.initialize();
        this.gameThread.setRunning(true);
        this.gameThread.start();
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){
    }

    public void surfaceDestroyed(SurfaceHolder holder){
        boolean retry = true;
        while(retry){
            try{
                this.gameThread.setRunning(false);
                this.gameThread.join();
                retry = false;
            } catch(InterruptedException e){
                e.printStackTrace();
            }
            //retry = true;
        }
    }
}


