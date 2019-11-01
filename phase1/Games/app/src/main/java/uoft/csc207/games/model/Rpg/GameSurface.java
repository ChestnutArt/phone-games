package uoft.csc207.games.model.Rpg;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.TreeMap;

import uoft.csc207.games.R;
import uoft.csc207.games.controller.ProfileManager;

public class GameSurface extends SurfaceView implements SurfaceHolder.Callback{
    private GameThread gameThread;
    private PlayerCharacter pCharacter;
    private TreeMap<String, Integer> characterMap;

    private NpcCharacter hoodNpc;
    private RpgGameState gameState;
    private ArrayList<NpcCharacter> nonPlayerCharacters;
    private RpgActivity rpgActivity;
    private TextView resultTextView;

    public void setIntercepted(boolean intercepted) {
        isIntercepted = intercepted;
    }

    private boolean isIntercepted = false;


    public GameSurface(Context context){
        super(context);
        this.setFocusable(true);
        this.getHolder().addCallback(this);

        gameState = new RpgGameState(ProfileManager.getProfileManager(context).getCurrentPlayer());
        rpgActivity = (RpgActivity)context;

        nonPlayerCharacters = new ArrayList<NpcCharacter>();
    }
    public ArrayList<NpcCharacter> getNpcs(){
        return nonPlayerCharacters;
    }

    public TextView getResultTextView(){
        return resultTextView;
    }

    public void update(){
        this.pCharacter.update();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        if (isIntercepted){
            resultTextView.setText("hello");
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
        handleCustomization();
        super.draw(canvas);
        pCharacter.draw(canvas);
        for(NpcCharacter npc: this.nonPlayerCharacters){
            npc.draw(canvas);
        }
    }

    private void initializeCustomizationOptions(){
        //characterMap.put()
    }

    private void handleCustomization(){
       // if (){

        //}
    }

    public void surfaceCreated(SurfaceHolder holder){
        Bitmap pcBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.c1_sprite_sheet);
        this.pCharacter = new PlayerCharacter(this, pcBitmap, 200, 800);
        initializeNPOs();

        this.gameThread = new GameThread(this, holder);
        this.gameThread.setRunning(true);
        this.gameThread.start();
        resultTextView = rpgActivity.getTextView();
        resultTextView.setText(R.string.app_name);
    }

    private void initializeNPOs(){
        NpcCharacter temp = new NpcCharacter(this,  BitmapFactory.decodeResource(this.getResources(),
                R.drawable.hooded_npc_sprites), 500, 800);
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


