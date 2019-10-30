package uoft.csc207.games.model.dodger;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class RectObj implements  GameObject {


    private int color;
    private int x;
    private int y;
    private Rect rect1;
    private Rect rect2;

    public RectObj (int color, int PlayerHeight){
        this.x = Constants.SCREEN_WIDTH - 100;
        int rGap = (int)(Math.random()* Constants.SCREEN_HEIGHT)/2;
        this.rect1 = new Rect(x, 0, x + 100, rGap);
        this.rect2 = new Rect(x, (int)(rGap + 2 * PlayerHeight), x + 100, Constants.SCREEN_HEIGHT);
        this.color = color;
        }
    public void draw(Canvas canvas){

        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rect1, paint);
        canvas.drawRect(rect2, paint);
    }

    public void update(){
        if (this.x > 0) {
            this.x -= 1;
            rect1.left -= 1;
            rect1.right -= 1;
            rect2.left -= 1;
            rect2.right -= 1;
        }
    }

    public boolean Collide(scrollerCharacter s){
        Rect pRect = s.getRect();
        if (rect1.intersect(pRect) || rect2.intersect(pRect)){
            return true;
        }
        return false;
    }


    public int getX() {
        return x + 100;
    }
}
