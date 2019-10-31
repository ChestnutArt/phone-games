package uoft.csc207.games.model.Rpg;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class NpcCharacter extends GameObject {
    private static final int UP_ROW = 0;
    private static final int LEFT_ROW = 1;
    private static final int DOWN_ROW = 2;
    private static final int RIGHT_ROW = 3;

    private static int COL_USING = 0;
    private int rowUsing;

    private Bitmap up;
    private Bitmap down;
    private Bitmap left;
    private Bitmap right;


    private GameSurface gameSurface;

    public NpcCharacter(GameSurface gS, Bitmap image, int x, int y){
        super(image, 21, 13, x, y);
        this.gameSurface = gS;
        rowUsing = LEFT_ROW;    //for now, will be decided by a parameter later

        Bitmap up = this.createSubImageAt(UP_ROW, COL_USING);
        Bitmap left = this.createSubImageAt(LEFT_ROW, COL_USING);
        Bitmap down = this.createSubImageAt(DOWN_ROW, COL_USING);
        Bitmap right = this.createSubImageAt(RIGHT_ROW, COL_USING);
    }

    public Bitmap getCurrentBitmap(){
        Bitmap result = null;
        switch (rowUsing){
            case UP_ROW:
                result = up;
                break;
            case LEFT_ROW:
                result = left;
                break;
            case DOWN_ROW:
                result = down;
                break;
            case RIGHT_ROW:
                result = right;
                break;
        }
        return result;
    }

    public void update(){
        /*
            For future implementation where NPC turns to face the character
         */
    }

    public void draw(Canvas canvas){
        Bitmap bitmap = left;
        canvas.drawBitmap(bitmap, x, y, null);
    }
}
