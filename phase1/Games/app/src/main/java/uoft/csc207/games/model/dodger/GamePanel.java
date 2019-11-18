package uoft.csc207.games.model.dodger;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import uoft.csc207.games.R;
import uoft.csc207.games.controller.ProfileManager;
import uoft.csc207.games.model.PlayerProfile;
import uoft.csc207.games.model.ScrollerGame;


public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread thread;
    private ObsManager Obs;
    private int P_y;
    private scrollerCharacter player1;
    private Coin coins;
    boolean isOver;
    private boolean start = false;
    private Point po;
    private Boolean male;
    private Integer backgroundColor;
    private Integer tSize;
    private PlayerProfile playerProfile;
    private ScrollerGame CurrentGame;

    public GamePanel(Context context){
        super(context);
        isOver = false;
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);
        player1 = new scrollerCharacter(BitmapFactory.decodeResource(getResources(),R.drawable.goku), true);
        this.coins = new Coin(6);
        P_y = Constants.SCREEN_HEIGHT/2;
        Obs = new ObsManager(3, Color.MAGENTA, player1.getHeight());
        System.out.println(player1.getHeight());
        this.playerProfile = Constants.player;
        InitCurrentGame();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,int height){

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){
        thread = new MainThread(getHolder(), this);
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
        boolean retry = true;
        while (retry){
            try{
                thread.setRunning(false);
                thread.join();
            } catch (Exception e){
                e.printStackTrace();
            }
            retry = false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if (!isOver) {
            switch (e.getAction()) {
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_MOVE: {
                    if (start) {
                        P_y -= 50*Constants.SPEED;
                        player1.Score++;
                        CurrentGame.updateScore(CurrentGame.getScore() + 1);
                    } else {
                        po = new Point();
                        po.x = (int) e.getX();
                        po.y = (int) e.getY();
                    }

                }
            }
        }
        return true;
    }

    public void startScreen(Canvas canvas){
        super.draw(canvas);
        Paint p1 = new Paint();
        p1.setColor(Color.BLACK);
        p1.setTextSize(100);
        canvas.drawColor(Color.GREEN);
        canvas.drawText("Big Text", 0, Constants.SCREEN_HEIGHT/3, p1);
        canvas.drawText("Regular Text", 0, 2*Constants.SCREEN_HEIGHT/3, p1);
        if ( po.y >= 0 && po.y <= Constants.SCREEN_HEIGHT/2){
            tSize = 100;
            po.y = -1;
        }
        else if (po.y >= 0){
            tSize = 50;
            po.y = -1;
        }
    }
    public void startScreen2(Canvas canvas){
        super.draw(canvas);
        Paint p1 = new Paint();
        p1.setColor(Color.BLACK);
        p1.setTextSize(100);
        canvas.drawColor(Color.GREEN);
        canvas.drawText("Male", 0, Constants.SCREEN_HEIGHT/3, p1);
        canvas.drawText("Female", 0, 2*Constants.SCREEN_HEIGHT/3, p1);
        if (po.y >= 0) {
            male = po.y <= Constants.SCREEN_HEIGHT / 2;
            po.y = -1;
        }
        if (!male){
            player1 = new scrollerCharacter(BitmapFactory.decodeResource(getResources(),R.drawable.fem), true);
        }
    }
    private void startScreen3(Canvas canvas){
        super.draw(canvas);
        Paint p1 = new Paint();
        p1.setColor(Color.BLACK);
        p1.setTextSize(100);
        canvas.drawColor(Color.GREEN);
        canvas.drawText("Blue", 0, Constants.SCREEN_HEIGHT/3, p1);
        canvas.drawText("Red", 0, 2*Constants.SCREEN_HEIGHT/3, p1);
        if ( po.y >= Constants.SCREEN_HEIGHT/2){
            this.backgroundColor = Color.RED;
            po.y = -1;
        }
        else if (po.y >= 0){
            this.backgroundColor = Color.BLUE;
            po.y = -1;
        }
    }

    private void startScreen4(Canvas canvas){
        super.draw(canvas);
        Paint p1 = new Paint();
        p1.setColor(Color.BLACK);
        p1.setTextSize(100);
        canvas.drawColor(Color.GREEN);
        canvas.drawText("Advanced", 0, Constants.SCREEN_HEIGHT/3, p1);
        canvas.drawText("Regular", 0, 2*Constants.SCREEN_HEIGHT/3, p1);
        if ( po.y >= Constants.SCREEN_HEIGHT/2){
            Constants.SPEED = 1;
            po.y = -1;
        }
        else if (po.y >= 0){
            Constants.SPEED = 2;
            po.y = -1;
        }
    }

    @Override
    public void draw(Canvas canvas){
        if (start) {
            super.draw(canvas);
            Paint p = new Paint();
            if (isOver) {
                GameOverScreen(canvas, p);
            } else{
                p.setColor(Color.BLACK);
                p.setTextSize(tSize);
                canvas.drawColor(backgroundColor);
                Obs.draw(canvas);
                player1.draw(canvas);
                coins.draw(canvas);
                canvas.drawText("Score: " + player1.Score, 100, 50 + p.descent() - p.ascent(), p);
                canvas.drawText("Money: " + player1.Currency, 100, 200 + p.descent() - p.ascent(), p);
            }
        } else {
            if (tSize == null){
                startScreen(canvas);
            } else if (male == null){
                startScreen2(canvas);
            } else if (backgroundColor == null){
                startScreen3(canvas);
            } else if (Constants.SPEED == 0) {
                startScreen4(canvas);
            }
                else{
                    start = true;
                }
            }
        }


        private void GameOverScreen(Canvas canvas, Paint p){
            canvas.drawColor(Color.BLACK);
            p.setColor(Color.YELLOW);
            p.setStrokeWidth(10);
            p.setTextSize(100);
            canvas.drawText("GAME OVER!", Constants.SCREEN_WIDTH/4, Constants.SCREEN_HEIGHT /2, p);
            canvas.drawText("Total Points: " + playerProfile.getScore(), 50, 50 + p.descent() - p.ascent(), p);
            canvas.drawText("Current Score: " + player1.Score, 50, 200 + p.descent() - p.ascent(), p);
            canvas.drawText("Total Money: " + playerProfile.getCurrency(), 50, 350 + p.descent() - p.ascent(), p);
            if (CurrentGame.HighScore == CurrentGame.getScore()){
                canvas.drawText("New HS!", 50, 500 + p.descent() - p.ascent(), p);
            }
        }



    public void update() {
        if (!isOver && start) {
            if (!Obs.detectCollision(player1)) {
                if (P_y + player1.getHeight() < Constants.SCREEN_HEIGHT) {
                    P_y += Constants.SPEED * 5;
                }
                player1.update(P_y);
                Obs.update();
                coins.update();
                coins.CollisionCheck(player1);
                if(CurrentGame.getScore() % 100 == 0) {
                    CurrentGame.checkAchievements();
                }
            } else {
                isOver = true;
                Constants.SPEED = 0;
                //William: Changing implementation of PlayerProfile, rather than storing a separate playerScore and playerCurrency
                //it calculates these values by iterating through all its owned games. No longer a need
                //to manually set them.
                //playerProfile.setScore(playerProfile.getScore() + player1.getScore());
                //playerProfile.setCurrency(playerProfile.getCurrency() + player1.Currency);
                CurrentGame.updateScore(player1.getScore());
                CurrentGame.updateCurrency(playerProfile.getCurrency());
                if (CurrentGame.getScore() > CurrentGame.HighScore){
                    CurrentGame.HighScore = CurrentGame.getScore();
                }
            }

        }
    }

    private void InitCurrentGame(){
        ScrollerGame s = (ScrollerGame)playerProfile.containsGame(playerProfile.getId() + ": Scroller");
        if (s == null){
            s = new ScrollerGame(playerProfile);
            playerProfile.addGame(s);
        }
        CurrentGame = s;
    }


}