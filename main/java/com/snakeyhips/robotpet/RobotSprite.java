package com.snakeyhips.robotpet;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public class RobotSprite{
    //private Bitmap sprite;
    private Rect rect;
    private int color;
    
    public RobotSprite(Rect rect, int color) {
        this.rect = rect;
        this.color = color;
    }
    
    public Rect getRect(){
        return rect;
    }
    
    public void draw(Canvas canvas){
        //canvas.drawBitmap(sprite, 100, 100, null);
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rect, paint);
    }
    
    public void update(Point point){
        //l, t, r, b
        rect.set(point.x - rect.width()/2, point.y - rect.height()/2,
                 point.x + rect.width()/2, point.y + rect.height()/2);
    }
}
