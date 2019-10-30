package uoft.csc207.games.model.dodger;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;

public class Coin {

    private ArrayList<Rect> coins;
    public Coin (int numCoins){
        for (int i = 0; i < numCoins; i++){
            int x = (int)(Math.random() * Constants.SCREEN_WIDTH - 50);
            int y = (int)(Math.random() * Constants.SCREEN_HEIGHT - 50);
            Rect r = new Rect(x, y, x + 50, y + 50);
            coins.add(r);
        }
    }
    public void draw(Canvas canvas){

        Paint paint = new Paint();
        paint.setColor(Color.YELLOW);
        for (Rect r: coins){
            canvas.drawRect(r, paint);
        }
    }


    public void CollisionCheck(scrollerCharacter s){
        Rect pRect = s.getRect();
        for (Rect r: coins) {
            if (r.intersect(pRect)) {
                coins.remove(r);
                s.Currency += 2;
                int x = (int)(Math.random() * Constants.SCREEN_WIDTH - 50);
                int y = (int)(Math.random() * Constants.SCREEN_HEIGHT - 50);
                Rect cR = new Rect(x, y, x + 50, y + 50);
                coins.add(cR);
            }
        }
    }

}


