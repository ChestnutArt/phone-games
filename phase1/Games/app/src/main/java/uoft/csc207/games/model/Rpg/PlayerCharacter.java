package uoft.csc207.games.model.Rpg;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import uoft.csc207.games.controller.rpg.RPGGameManager;

public class PlayerCharacter extends GameObject{
    private static final int ROW_BOTTOM_TO_TOP = 8;
    private static final int ROW_RIGHT_TO_LEFT = 9;
    private static final int ROW_TOP_TO_BOTTOM = 10;
    private static final int ROW_LEFT_TO_RIGHT = 11;

    private Bitmap[] bottomToTopImages;   //bottom to top cycle
    private Bitmap[] rightToLeftImages;   //right to left cycle
    private Bitmap[] topToBottomImages;   //top to bottom cycle
    private Bitmap[] leftToRightImages;   //left to right cycle

    // Velocity of game character (pixels/millisecond)
    public static final float VELOCITY = 0.2f;

    private static int NUM_MOVEMENTS = 9;

    private int movingVectorX;
    private int movingVectorY;

    private int destinationX;
    private int destinationY;

    private boolean isLeftBlocked = false;
    private boolean isRightBlocked = false;
    private boolean isTopBlocked = false;
    private boolean isBotBlocked = false;
    private boolean movingRight = false;
    private boolean movingLeft = false;
    private boolean movingTop = false;
    private boolean movingBot = false;

    private int rowUsing;   //sprite sheet row
    private int colUsing;      //sprite sheet column

    private long lastDrawNanoTime;

    public PlayerCharacter(Bitmap image, int x, int y){
        super(image,21, 13, x, y);

        movingVectorX = 0;
        movingVectorY = 0;
        destinationX = x;
        destinationY = y;
        rowUsing = ROW_TOP_TO_BOTTOM;
        colUsing = 0;
        lastDrawNanoTime = -1;

        leftToRightImages = new Bitmap[NUM_MOVEMENTS];
        rightToLeftImages = new Bitmap[NUM_MOVEMENTS];
        topToBottomImages = new Bitmap[NUM_MOVEMENTS];
        bottomToTopImages = new Bitmap[NUM_MOVEMENTS];

        setWalkCycleImages(image);
    }
    public boolean isLeftBlocked() {
        return isLeftBlocked;
    }
    public void setLeftBlocked(boolean leftBlocked) {
        isLeftBlocked = leftBlocked;
    }
    public boolean isRightBlocked() {
        return isRightBlocked;
    }
    public void setRightBlocked(boolean rightBlocked) {
        isRightBlocked = rightBlocked;
    }
    public boolean isTopBlocked() {
        return isTopBlocked;
    }
    public void setTopBlocked(boolean topBlocked) {
        isTopBlocked = topBlocked;
    }
    public boolean isBotBlocked() {
        return isBotBlocked;
    }
    public void setBotBlocked(boolean botBlocked) {
        isBotBlocked = botBlocked;
    }
    public int getMovingVectorX() { return movingVectorX; }
    public int getMovingVectorY() { return movingVectorY; }

    public Bitmap[] getMoveBitmapImages(){
        Bitmap[] result = null;
        switch (rowUsing){
            case ROW_BOTTOM_TO_TOP:
                result = this.bottomToTopImages;
                break;
            case ROW_RIGHT_TO_LEFT:
                result = this.rightToLeftImages;
                break;
            case ROW_TOP_TO_BOTTOM:
                result = this.topToBottomImages;
                break;
            case ROW_LEFT_TO_RIGHT:
                result = this.leftToRightImages;
                break;
        }
        return result;
    }

    public Bitmap getCurrentMoveBitmap(){
        Bitmap[] bitmaps = this.getMoveBitmapImages();
        return bitmaps[colUsing];
    }

    public void setWalkCycleImages(Bitmap newImage){
        this.image = newImage;
        for(int col = 0; col < NUM_MOVEMENTS; col++){
            this.bottomToTopImages[col] = this.createSubImageAt(ROW_BOTTOM_TO_TOP, col);
            this.rightToLeftImages[col] = this.createSubImageAt(ROW_RIGHT_TO_LEFT, col);
            this.topToBottomImages[col] = this.createSubImageAt(ROW_TOP_TO_BOTTOM, col);
            this.leftToRightImages[col] = this.createSubImageAt(ROW_LEFT_TO_RIGHT, col);
        }
    }

    public void update(){
        if (!(movingVectorX == 0 && movingVectorY == 0)){
            this.colUsing++;    //updates walking image being used if the character is moving
        }
        if(colUsing >= NUM_MOVEMENTS){
            this.colUsing = 0;      //resets walk cycle
        }
        //current time in nanoseconds
        long currentTime = System.nanoTime();

        if (lastDrawNanoTime == -1){
            lastDrawNanoTime = currentTime;
        }

        //converting nanoseconds to milliseconds
        int deltaTime = (int)((currentTime - lastDrawNanoTime) / 1000000);
        float distance = VELOCITY * deltaTime;

        //if it's reached its x destination, sets x vector to 0
        if((this.x >= destinationX && movingVectorX > 0) || (this.x <= destinationX && movingVectorX < 0)){
            movingVectorX = 0;
        } else {
            this.x = x + (int)(distance * movingVectorX / Math.abs(movingVectorX));
        }
        //only moves in y direction if movement in x direction already finished
        if (movingVectorX == 0){
            //if it's reached it's y destination, sets y vector to 0
            if((this.y >= destinationY && movingVectorY > 0) || (this.y <= destinationY && movingVectorY < 0)){
                movingVectorY = 0;
            } else {
                this.y = y + (int)(distance * movingVectorY / Math.abs(movingVectorY));
            }
        }
        /*
            Decides which bitmaps to use in the same order movement is decided (x then y)
         */
        if(movingVectorX > 0){
            this.rowUsing = ROW_LEFT_TO_RIGHT;
        } else if (movingVectorX < 0){
            this.rowUsing = ROW_RIGHT_TO_LEFT;
        } else {
            if (movingVectorY > 0){
                this.rowUsing = ROW_TOP_TO_BOTTOM;
            } else if (movingVectorY < 0){
                this.rowUsing = ROW_BOTTOM_TO_TOP;
            }
        }
    }

    public void stopMoving(){
        movingVectorX = 0;
        movingVectorY = 0;
        destinationX = x;
        destinationY = y;
    }

    public void draw(Canvas canvas){
        Bitmap bitmap = this.getCurrentMoveBitmap();
        canvas.drawBitmap(bitmap, x - (singleWidth / 2), y - (singleWidth / 2), null);

        // Updates the last draw time.
        this.lastDrawNanoTime = System.nanoTime();
    }

    public void setMovementVector(int movingVectorX, int movingVectorY){
        this.movingVectorX = movingVectorX;
        this.movingVectorY = movingVectorY;
    }

    /**
     * Sets the destination coordinates of the PlayerCharacter
     * @param newX The destination x value
     * @param newY The destination y value
     */
    public void setDestinationCoordinates(int newX, int newY){
        destinationX = newX;
        destinationY = newY;
    }

    /**
     * Manually sets the coordinate values of the PlayerCharacter object
     * @param x The new x value
     * @param y The new y value
     */
    public void setCoordinates(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void unblockAllDirections(){
        isLeftBlocked = false;
        isRightBlocked = false;
        isTopBlocked = false;
        isBotBlocked = false;
    }
    public void setNotMoving(){
        movingLeft = false;
        movingRight = false;
        movingTop = false;
        movingBot = false;
    }
    public String getMovingDirection(){
        if(movingVectorX != 0){
            if (movingVectorX > 0){
                return "right";
            } else {
                return "left";
            }
        } else {
            if (movingVectorY > 0){
                return "down";
            } else {
                return "up";
            }
        }
    }

    public List<String> getBlockedDirections(){
        List<String> blockedDirections = new ArrayList<>();
        if (isRightBlocked){
            blockedDirections.add("right");
        } else if (isLeftBlocked){
            blockedDirections.add("left");
        } else if (isTopBlocked){
            blockedDirections.add("up");
        } else if (isBotBlocked){
            blockedDirections.add("down");
        }
        return blockedDirections;
    }
}
