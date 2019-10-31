package uoft.csc207.games.model.dodger;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;

public class Coin implements GameObject{

    private ArrayList<Rect> coins;
    private int numCoins;
    public Coin (int numCoins){
        this.numCoins = numCoins;
        coins = new ArrayList<Rect>();
        for (int i = 0; i < numCoins; i++){
            int x = (int)(Math.random() * Constants.SCREEN_WIDTH - 50);
            int y = 50 + (int)(Math.random() * Constants.SCREEN_HEIGHT - 50);
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

    public void update(){
        for (Rect r: coins){
            if (r.left > 0) {
                r.left -= 1;
                r.right -= 1;
            } else{
                coins.remove(r);
            }
        }
        if (coins.size() == 0){
            for (int i = 0; i < numCoins; i++){
                int x = (int)(Math.random() * Constants.SCREEN_WIDTH - 50);
                int y = 50 + (int)(Math.random() * Constants.SCREEN_HEIGHT - 50);
                Rect r = new Rect(x, y, x + 50, y + 50);
                coins.add(r);
            }
        }
    }


    public void CollisionCheck(scrollerCharacter s){
        Rect pRect = s.getRect();
        for (Rect r: coins) {
            if (r.intersect(pRect)) {
                coins.remove(r);
                s.Currency += 2;
            }
        }
    }

}

