package uoft.csc207.games.model.Rpg;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class PlayerCharacter extends GameObject{
    private static final int ROW_BOTTOM_TO_TOP = 9;
    private static final int ROW_RIGHT_TO_LEFT = 10;
    private static final int ROW_TOP_TO_BOTTOM = 11;
    private static final int ROW_LEFT_TO_RIGHT = 12;

    private GameSurface gameSurface;
    private Bitmap[] bottomToTopImages;   //bottom to top cycle
    private Bitmap[] rightToLeftImages;   //right to left cycle
    private Bitmap[] topToBottomImages;   //top to bottom cycle
    private Bitmap[] leftToRightImages;   //left to right cycle


    public PlayerCharacter(GameSurface gs, Bitmap image, int x, int y){
        super(image,4, 9, x, y);

        this.gameSurface = gs;

        leftToRightImages = new Bitmap[colCount];
        rightToLeftImages = new Bitmap[colCount];
        topToBottomImages = new Bitmap[colCount];
        bottomToTopImages = new Bitmap[colCount];

        for(int col = 0; col < this.colCount; col++){
            this.bottomToTopImages[col] = this.createSubImageAt(ROW_BOTTOM_TO_TOP, col);
            this.rightToLeftImages[col] = this.createSubImageAt(ROW_RIGHT_TO_LEFT, col);
            this.topToBottomImages[col] = this.createSubImageAt(ROW_TOP_TO_BOTTOM, col);
            this.leftToRightImages[col] = this.createSubImageAt(ROW_LEFT_TO_RIGHT, col);
        }
    }
}
