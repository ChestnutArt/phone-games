package uoft.csc207.games.controller.rpg;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import java.util.ArrayList;
import java.util.TreeMap;

import uoft.csc207.games.R;
import uoft.csc207.games.controller.ProfileManager;
import uoft.csc207.games.model.PlayerProfile;
import uoft.csc207.games.model.Rpg.NpcCharacter;
import uoft.csc207.games.model.Rpg.PlayerCharacter;
import uoft.csc207.games.model.Rpg.RpgActivity;
import uoft.csc207.games.model.Rpg.RpgGameState;

/**
 * This Class is a main class to manage RPG Game's characters (objects of PlayerCharacter,
 * RpgCharacter), plyaer's profile (object of PlayerProfile) and refresh a GameSurface with
 * updated information
 */
public class RPGGameManager {
    public PlayerCharacter getPlayerCharacter() {
        return playerCharacter;
    }

    private PlayerCharacter playerCharacter;
    private ArrayList<NpcCharacter> nonPlayerCharacters;

    public RpgGameState getCurrentGameState() {
        return currentGameState;
    }

    private RpgGameState currentGameState;
    private Context currentContext;
    private PlayerProfile currentPlayer;
    private Paint scorePaint = new Paint();
    private Paint resultPaint = new Paint();
    private int usingCharacter = R.drawable.c1_sprite_sheet;
    private int hoodedNPCSprites = R.drawable.hooded_npc_sprites;
    private TreeMap<String, Integer> characterMap;     //map of character strings to their sprite sheet

    public boolean isGameEnded() {
        return isGameEnded;
    }

    private boolean isGameEnded = false;

    public PlayerProfile getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * A constructor of RPGGameManager class
     * @param currentContext a <class>Context</class> object for RPGGameManager retrieve system resource
     */
    public RPGGameManager(Context currentContext) {
        this.currentContext = currentContext;
        playerCharacter = new PlayerCharacter(BitmapFactory.decodeResource(currentContext.getResources(), usingCharacter), 200, 800);
        nonPlayerCharacters = new ArrayList<>();
        nonPlayerCharacters.add( new NpcCharacter(BitmapFactory.decodeResource(currentContext.getResources(), hoodedNPCSprites), 500, 800));
        currentPlayer = ProfileManager.getProfileManager(currentContext).getCurrentPlayer();
        currentGameState = (RpgGameState) currentPlayer.containsGame("16812");//new RpgGameState(currentPlayer);
    }

    /**
     * Initialize all required resource for RPGGame to start up
     */
    public void initialize(){
        intializePaints();
        initializeCharacterMap();
        currentGameState.initializeAchievements();
    }

    private void intializePaints(){
        //configure scorePaint
        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(50);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);

        //configure resultPaint
        resultPaint.setColor(Color.WHITE);
        resultPaint.setTextSize(60);
        resultPaint.setTypeface(Typeface.DEFAULT_BOLD);
        resultPaint.setAntiAlias(true);
        resultPaint.setTextAlign(Paint.Align.CENTER);
    }

    private void initializeCharacterMap(){
        characterMap = new TreeMap<>();
        characterMap.put("male", R.drawable.c1_sprite_sheet);
        characterMap.put("female", R.drawable.c2_sprite_sheet);
    }


    public void update(){
        playerCharacter.update();
    }

    public void setPlayerCharacterDestination(int x, int y){
        int movementVectorX = x - playerCharacter.getX();
        int movementVectorY = y - playerCharacter.getY();

        playerCharacter.setMovementVector(movementVectorX, movementVectorY);
        playerCharacter.setDestinationCoordinates(x, y);
    }

    public void draw(Canvas canvas){
        playerCharacter.draw(canvas);
        for(NpcCharacter npc: this.nonPlayerCharacters){
            npc.draw(canvas);
        }

        if (isIntercepted()){
            //update score
            currentGameState.updateScore(1);
            currentGameState.checkAchievements();
            String score = "Score: " + currentGameState.getScore();
            String gold = "Gold: " + currentGameState.getGameCurrency();
            canvas.drawText(score, 40, 40, scorePaint);
            canvas.drawText(gold, 40, 80, scorePaint);

            String win = "You Won!";
            Rect bounds = new Rect();
            resultPaint.getTextBounds(win, 0, win.length(), bounds);
            int x = canvas.getWidth() - bounds.width();
            int y = canvas.getHeight() - bounds.height();
            canvas.drawText(win, x, y, resultPaint);
            isGameEnded = true;     //for phase 1, will just end when you talk to the one npc
            playerCharacter.resetCoordinates();
            ((RpgActivity) currentContext).finishGame(0);
        }else {
            String score = "Score: " + currentGameState.getScore();
            String gold = "Gold: " + currentGameState.getGameCurrency();
            canvas.drawText(score, 40, 40, scorePaint);
            canvas.drawText(gold, 40, 80, scorePaint);
        }
        handleCustomization();
    }

    private void handleCustomization(){
        String gameStateCharacter = currentGameState.getCharacter();
        if(gameStateCharacter == null || gameStateCharacter.equalsIgnoreCase("male") ){
            usingCharacter = characterMap.get("male");
        } else if (gameStateCharacter.equalsIgnoreCase("female") ){
            usingCharacter = characterMap.get("female");
        }
        Bitmap currentBitmap = BitmapFactory.decodeResource(currentContext.getResources(), usingCharacter);
        playerCharacter.setWalkCycleImages(currentBitmap) ;
    }

    private boolean isIntercepted(){
        boolean isMet = false;
        for (NpcCharacter npc: nonPlayerCharacters){
            if ((Math.abs(npc.getX() - playerCharacter.getX()) < (npc.getWidth() / 2)) &&
                    (Math.abs(npc.getY() - playerCharacter.getY() ) < (npc.getHeight() / 2)) ){
                isMet = true;
                break;
            }
        }
        return isMet ;
    }
}
