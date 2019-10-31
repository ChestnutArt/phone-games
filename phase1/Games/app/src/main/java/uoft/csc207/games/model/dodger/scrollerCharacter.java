package uoft.csc207.games.model.dodger;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;


public class scrollerCharacter {
    private Bitmap img;
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean isMale;
    Integer Score;
    Integer Currency;

    public scrollerCharacter(Bitmap img, boolean isMale){
            x = Constants.SCREEN_WIDTH/2;
            y = Constants.SCREEN_HEIGHT/2;
            width = Constants.SCREEN_WIDTH/6;
            int scale = img.getWidth()/width;
            height = img.getHeight()/scale;
            this.img = Bitmap.createScaledBitmap(img, width,
                    height, true);
    }

    public void draw(Canvas canvas) {
        Paint p = new Paint();
        p.setColor(Color.BLACK);
        canvas.drawBitmap(img, x, y, p);
    }

    public int getHeight(){return this.height;}




    public void update(int y){
        if (y < Constants.SCREEN_HEIGHT - height) {
            this.y = y;
        }
    }

    public Rect getRect(){
        return new Rect(x, y, x + width, y + height);
    }

    public void setScore(Integer s){
        this.Score = s;
    }

    public void setCurrency(Integer c){
        this.Currency = c;
    }
}
