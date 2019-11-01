package uoft.csc207.games.model.Rpg;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.TreeMap;

import uoft.csc207.games.R;
import uoft.csc207.games.controller.ProfileManager;

public class GameSurface extends SurfaceView implements SurfaceHolder.Callback{
    private GameThread gameThread;
    private PlayerCharacter pCharacter;     //the character the player controls
    private TreeMap<String, Integer> characterMap;     //map of character strings to their sprite sheet
    private int usingCharacter = R.drawable.c1_sprite_sheet;    //current character being used
    private String usingColor;
    public String usingFont;
    private RpgGameState gameState;     //class that stores all statistics and customizations
    private ArrayList<NpcCharacter> nonPlayerCharacters;
    private RpgActivity rpgActivity;
    private TextView resultTextView;    //dialogue box
    private TextView statsTextView;     //score box
    private NpcCharacter interceptedNpc;
    private boolean gameEnd;

    public void setInterceptedNpc(NpcCharacter interceptedNpc) {
        this.interceptedNpc = interceptedNpc;
    }

    public GameSurface(Context context){
        super(context);
        this.setFocusable(true);
        this.getHolder().addCallback(this);

        gameState = new RpgGameState(ProfileManager.getProfileManager(context).getCurrentPlayer());
        rpgActivity = (RpgActivity)context;

        nonPlayerCharacters = new ArrayList<NpcCharacter>();
        gameEnd = false;
    }
    public ArrayList<NpcCharacter> getNpcs(){
        return nonPlayerCharacters;
    }

    public TextView getResultTextView(){
        return resultTextView;
    }

    public RpgGameState getGameState(){
        return this.gameState;
    }

    public void update(){

        this.pCharacter.update();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        if (gameEnd){
            new CountDownTimer(10000, 1000) {
                public void onFinish() {
                    rpgActivity.finishGame();
                }
                public void onTick(long millisUntilFinished) {
                }
            }.start();
        }
        if (interceptedNpc != null){
            for (String s: interceptedNpc.getDialogue()){
                resultTextView.setText(s);
            }
            gameEnd = true;     //for phase 1, will just end when you talk to the one npc
        }
        if (event.getAction() == MotionEvent.ACTION_DOWN){
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
        for(NpcCharacter npc: this.nonPlayerCharacters){
            npc.draw(canvas);
        }
        handleCustomization();
        updateStatistics();
    }

    private void initialize(){
        initializeCharacterMap();
        gameState.initializeAchievements();
    }

    private void initializeCharacterMap(){
        characterMap = new TreeMap<>();
        characterMap.put("male", R.drawable.c1_sprite_sheet);
        characterMap.put("female", R.drawable.c2_sprite_sheet);
    }

    private void handleCustomization(){
        String gameStateCharacter = this.gameState.getCharacter();
        if(gameStateCharacter.equalsIgnoreCase("male") || gameStateCharacter == null){
            usingCharacter = characterMap.get("male");
        } else if (gameStateCharacter.equalsIgnoreCase("female") ){
            usingCharacter = characterMap.get("female");
        }
        Bitmap currentBitmap = BitmapFactory.decodeResource(this.getResources(), usingCharacter);
        pCharacter.setWalkCycleImages(currentBitmap) ;

        usingColor = gameState.getColor();
        if(usingColor.equals("black") || usingColor == null){
            resultTextView.setBackgroundColor(Color.BLACK);
        } else if (usingColor.equals("white")){
            resultTextView.setBackgroundColor(Color.WHITE);
        }

        usingFont = gameState.getFont();
        if(usingFont.equals("monospace") || usingColor == null){
            resultTextView.setTypeface(Typeface.MONOSPACE);
        } else if (usingColor.equals("sans-serif")){
            resultTextView.setTypeface(Typeface.SANS_SERIF);
        }
    }

    public void updateStatistics(){
        if (Math.random() > 0){
            gameState.updateCurrency(1);
        }
        if ((System.currentTimeMillis() / 1000) % 1 == 0){
            gameState.updateScore(1);
        }
        statsTextView.setText("Score: " + gameState.getScore() + "\nGold: " + gameState.getGameCurrency());
    }

    public void surfaceCreated(SurfaceHolder holder){
        initialize();
        //handleCustomization();
        Bitmap pcBitmap = BitmapFactory.decodeResource(this.getResources(), usingCharacter);
        this.pCharacter = new PlayerCharacter(this, pcBitmap, 200, 800);
        initializeNPOs();

        this.gameThread = new GameThread(this, holder);
        this.gameThread.setRunning(true);
        this.gameThread.start();
        resultTextView = rpgActivity.getTextView();
        resultTextView.setText("");
        statsTextView = rpgActivity.getStatsView();
    }

    private void initializeNPOs(){
        ArrayList<String> tempList = new ArrayList<>();
        tempList.add("Thank you brave hero for coming to save our village. Fortunately the devs " +
                "haven't created anything" + " we need saving from yet but we'll need your help in phase 2. " +
                "We do appreciate the effort though. Please take this gold and score for your trouble.");
        NpcCharacter temp = new NpcCharacter(this,  BitmapFactory.decodeResource(this.getResources(),
                R.drawable.hooded_npc_sprites), 500, 800, tempList);
        this.nonPlayerCharacters.add(temp);
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){
    }

    public void surfaceDestroyed(SurfaceHolder holder){
        boolean retry = true;
        while(retry){
            try{
                this.gameThread.setRunning(false);
                this.gameThread.join();
            } catch(InterruptedException e){
                e.printStackTrace();
            }
            retry = true;
        }
    }
}


