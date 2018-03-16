package com.snakeyhips.robotpet;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public class RobotSprite{
    private Bitmap sprite;
    private int x;
    private int y;
    
    //Controlling X coordinate so that robot won't go outside the screen
    private int maxX;
    private int minX;
    
    public RobotSprite(Bitmap sprite, int x, int y, maxX, minX) {
        this.sprite = sprite;
        this.x = x;
        this.y = y;
    }
    
    public Bitmap getBitmap() {
        return bitmap;
    }
    
    public void setBitmap(Bitmap sprite) {
        this.sprite = sprite;
    }
 
    public int getX() {
        return x;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public int getY() {
        return y;
    }
      
    public void draw(Canvas canvas){
        //canvas.drawBitmap(sprite, 100, 100, null);
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rect, paint);
    }
    
    public void update(Point point){
        if (x < minX) {
            x = minX;
        }
        if (x > maxX) {
            x = maxX;
        }
    }
}
