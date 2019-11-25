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
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import uoft.csc207.games.R;
import uoft.csc207.games.controller.ProfileManager;
import uoft.csc207.games.model.IGameID;
import uoft.csc207.games.model.PlayerProfile;
import uoft.csc207.games.model.Rpg.GameObject;
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
    private PlayerCharacter playerCharacter;
    //private ArrayList<ArrayList<GameObject>> screens;
    private ArrayList<GameObject> gameObjects;

    public RpgGameState getCurrentGameState() {
        return currentGameState;
    }

    private RpgGameState currentGameState;
    private Context currentContext;
    private PlayerProfile currentPlayer;
    private Paint scorePaint = new Paint();
    private Paint resultPaint = new Paint();
    private Paint outerPaint = new Paint();
    private Paint innerPaint = new Paint();
    private Rect outerRect = new Rect();
    private Rect innerRect = new Rect();
    private int usingCharacter = R.drawable.c1_sprite_sheet;
    private int hoodedNPCSprites = R.drawable.hooded_npc_sprites;
    private int background = R.drawable.forest_background;
    private int forestPath = R.drawable.forest_path;
    private Bitmap backgroundBitmap;
    private Bitmap forestPathBitMap;
    private TreeMap<String, Integer> characterMap;     //map of character strings to their sprite sheet

    private boolean isProcessingText = false;
    private NpcCharacter lastTalkedToNpc;
    /**
     * Distance between outer and inner rectangles making up the text box
     */
    private final static int OUTER_TO_INNER_OFFSET = 15;
    /**
     * Distance between outer rectangle borders to the text
     */
    private final static int OUTER_TO_TEXT_OFFSET = 30;
    private final static int TEXT_BOX_HEIGHT = 400;
    private final static int GAME_SPACE = 500;
    private static int backgroundSpace;
    private int canvasWidth;
    private int canvasHeight;
    private String currentText = "";

    public boolean isProcessingText(){
        return isProcessingText;
    }

    public boolean isGameEnded() {
        return isGameEnded;
    }

    private boolean isGameEnded = false;

    public PlayerProfile getCurrentPlayer() {
        return currentPlayer;
    }

    public Paint getResultPaint() {
        return resultPaint;
    }
    public Paint getScorePaint() {
        return scorePaint;
    }
    /**
     * A constructor of RPGGameManager class
     * @param currentContext a <class>Context</class> object for RPGGameManager retrieve system resource
     */
    public RPGGameManager(Context currentContext, int width, int height) {
        this.currentContext = currentContext;
        playerCharacter = new PlayerCharacter(BitmapFactory.decodeResource(currentContext.getResources(), usingCharacter), 200, 1000);
        initializeGameObjects();
        currentPlayer = ProfileManager.getProfileManager(currentContext).getCurrentPlayer();
        currentGameState = new RpgGameState();
        canvasWidth = width;
        canvasHeight = height;
        backgroundSpace = canvasHeight - TEXT_BOX_HEIGHT - GAME_SPACE;
    }

    public PlayerCharacter getPlayerCharacter() {
        return playerCharacter;
    }

    public ArrayList<GameObject> getGameObjects(){
        return gameObjects;
    }

    /**
     * Initialize all required resource for RPGGame to start up
     */
    public void initialize(){
        initializeGameState();
        initializePaints();
        initializeCharacterMap();
        initializeBackground();
    }

    /**
     * Initializes all the game objects which is just the npcs as of now.
     */
    public void initializeGameObjects(){
        gameObjects = new ArrayList<>();
        List<String> dialogue1 = new ArrayList<>();
        dialogue1.add("Hi 1.");
        dialogue1.add("Hi 2.");
        String talkedToText = "you have talked to me";
        NpcCharacter npc1 = new NpcCharacter(BitmapFactory.decodeResource(currentContext.getResources(), hoodedNPCSprites),
                500, 1000, dialogue1, talkedToText);
        gameObjects.add(npc1);
    }

    /**
     * Creates a RpgGameState if the current player doesn't already have one
     */
    private void initializeGameState(){
        currentGameState = (RpgGameState)currentPlayer.containsGame(IGameID.RPG);
        if(currentGameState == null){
            currentGameState = new RpgGameState();
            currentPlayer.addGame(currentGameState);
        }
    }

    /**
     * Initializes scorePaint which is used for drawing the score and gold, and initializes the paints
     * for the 2 rectangles making up the dialogue box at the bottom.
     */
    private void initializePaints(){
        //configure scorePaint
        if(currentGameState.getColor() != null){
            if(currentGameState.getColor().equals(RpgGameState.FONT_COLOR_RED)){
                scorePaint.setColor(Color.RED);
            }else if (currentGameState.getColor().equals(RpgGameState.FONT_COLOR_BLACK)){
                scorePaint.setColor(Color.BLACK);
            }else {
                scorePaint.setColor(Color.WHITE);
            }
        }else {
            scorePaint.setColor(Color.WHITE);
        }
        scorePaint.setTextSize(50);
        if(currentGameState.getFont() != null){
            if(currentGameState.getFont().equals(RpgGameState.FONT_TYPE_MONOSPACE)){
                scorePaint.setTypeface(Typeface.MONOSPACE);
            }else if (currentGameState.getFont().equals(RpgGameState.FONT_TYPE_SANS_SERIF)){
                scorePaint.setTypeface(Typeface.SANS_SERIF);
            }else {
                scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
            }

        }else{
            scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        }
        scorePaint.setAntiAlias(true);

        //configure resultPaint
        resultPaint.setColor(Color.WHITE);
        resultPaint.setTextSize(60);
        resultPaint.setTypeface(Typeface.DEFAULT_BOLD);
        resultPaint.setAntiAlias(true);
        resultPaint.setTextAlign(Paint.Align.LEFT);

        //Creating the text box border
        outerRect.set(0,canvasHeight - TEXT_BOX_HEIGHT, canvasWidth, canvasHeight);
        outerPaint.setColor(Color.LTGRAY);
        outerPaint.setStyle(Paint.Style.FILL);

        //Creating the actual text box
        innerRect.set(OUTER_TO_INNER_OFFSET, canvasHeight - TEXT_BOX_HEIGHT + OUTER_TO_INNER_OFFSET,
                canvasWidth - OUTER_TO_INNER_OFFSET, canvasHeight - OUTER_TO_INNER_OFFSET);
        innerPaint.setColor(Color.BLACK);
        innerPaint.setStyle(Paint.Style.FILL);
    }

    private void initializeCharacterMap(){
        characterMap = new TreeMap<>();
        characterMap.put("male", R.drawable.c1_sprite_sheet);
        characterMap.put("female", R.drawable.c2_sprite_sheet);
    }

    private void initializeBackground(){
        //initializes forest background
        backgroundBitmap = BitmapFactory.decodeResource(currentContext.getResources(), background);
        backgroundBitmap = Bitmap.createBitmap(backgroundBitmap, 0, 872, canvasWidth,
                canvasHeight - TEXT_BOX_HEIGHT - GAME_SPACE);
        //initializes the ground background
        forestPathBitMap = BitmapFactory.decodeResource(currentContext.getResources(), forestPath);
        forestPathBitMap = Bitmap.createBitmap(forestPathBitMap, 500, 500, canvasWidth,
                GAME_SPACE);
    }

    public void setPlayerCharacterDestination(int x, int y){
        int movementVectorX = x - playerCharacter.getX();
        int movementVectorY = y - playerCharacter.getY();
        playerCharacter.setMovementVector(movementVectorX, movementVectorY);
        playerCharacter.setDestinationCoordinates(x, y);
    }

    public void update(){
        playerCharacter.update();
        GameObject interceptor = this.isIntercepted();
        if (interceptor != null){
            playerCharacter.stopMoving();
            //update score
            if (interceptor instanceof NpcCharacter){
                NpcCharacter npcInterceptor = (NpcCharacter) interceptor;
                lastTalkedToNpc = npcInterceptor; //added
                isProcessingText = true;
                if (npcInterceptor.hasTalkedToAlready()){
                    currentText = (npcInterceptor).getAfterTalkedToText();
                    isProcessingText = false;
                } else {
                    currentGameState.updateScore(50);
                    if(npcInterceptor.hasNextDialogue()){
                        currentText = npcInterceptor.getNextDialogue();
                    } else {
                        //(npcInterceptor).setTalkedToAlready(true);
                        isProcessingText = false;
                    }
                }
            }
        } else {
            if(lastTalkedToNpc != null){
                lastTalkedToNpc.setTalkedToAlready(true);
            }
            currentText = "";
        }
        if(Math.random() < 0.05){
            currentGameState.updateCurrency(1);
        }
        currentGameState.checkAchievements();
    }

    /**
     * Draws all relevant game assets including backgrounds, the dialogue box, the score/gold, the PlayerCharacter,
     * and all the GameObject objects.
     * @param canvas The Canvas for RPGGameManager to draw on
     */
    public void draw(Canvas canvas){
        canvas.drawBitmap(backgroundBitmap, 0, 0, null);
        canvas.drawBitmap(forestPathBitMap, 0, canvasHeight - GAME_SPACE - TEXT_BOX_HEIGHT, null);
        playerCharacter.draw(canvas);
        for(GameObject gameObject: this.gameObjects){
            (gameObject).draw(canvas);
        }
        canvas.drawRect(outerRect, outerPaint);
        canvas.drawRect(innerRect, innerPaint);

        /*GameObject interceptedObject = isIntercepted();
        if (interceptedObject != null){
            //Creating scoreboard in top left
            String score = "Score: " + currentGameState.getScore();
            String gold = "Gold:  " + currentGameState.getGameCurrency();
            canvas.drawText(score, 40, 40, scorePaint);
            canvas.drawText(gold, 40, 120, scorePaint);

            String win = "WinWinWinWinWinWin";
            int x = OUTER_TO_TEXT_OFFSET; //canvas.getWidth() - bounds.width();
            int y = canvasHeight - TEXT_BOX_HEIGHT + (OUTER_TO_TEXT_OFFSET * 3); //canvas.getHeight() - bounds.height();
            canvas.drawText(currentText, x, y, resultPaint);

            //isGameEnded = true;     //for phase 1, will just end when you talk to the one npc
            //playerCharacter.resetCoordinates();
            //((RpgActivity) currentContext).finishGame(0);
        }else {
            String score = "Score: " + currentGameState.getScore();
            String gold = "Gold:  " + currentGameState.getGameCurrency();
            canvas.drawText(score, 40, 40, scorePaint);
            canvas.drawText(gold, 40, 120, scorePaint);
        }*/
        int x = OUTER_TO_TEXT_OFFSET; //canvas.getWidth() - bounds.width();
        int y = canvasHeight - TEXT_BOX_HEIGHT + (OUTER_TO_TEXT_OFFSET * 3); //canvas.getHeight() - bounds.height();
        canvas.drawText(currentText, x, y, resultPaint);
        String score = "Score: " + currentGameState.getScore();
        String gold = "Gold:  " + currentGameState.getGameCurrency();
        canvas.drawText(score, 40, 40, scorePaint);
        canvas.drawText(gold, 40, 120, scorePaint);
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

    public GameObject isIntercepted(){
        //boolean isMet = false;
        GameObject interceptor = null;
        for (GameObject gameObject: gameObjects){
            if ((Math.abs(gameObject.getX() - playerCharacter.getX()) < (gameObject.getWidth() / 2)) &&
                    (Math.abs(gameObject.getY() - playerCharacter.getY() ) < (gameObject.getHeight() / 2)) ){
                interceptor = gameObject;
                break;
            }
        }
        return interceptor;
    }
    public boolean isInGameSpace(int yCoordinate){
        return yCoordinate >= (backgroundSpace - playerCharacter.getHeight()) && yCoordinate <=
                (canvasHeight - TEXT_BOX_HEIGHT - playerCharacter.getHeight());
    }
}
