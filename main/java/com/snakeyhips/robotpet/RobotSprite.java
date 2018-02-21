package com.snakeyhips.robotpet;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class RobotSprite{
    private Bitmap sprite;
    
    public RobotSprite(Bitmap bmp) {
        sprite = bmp;
    }
    
    public void draw(Canvas canvas){
        canvas.drawBitmap(sprite, 100, 100, null);
    }
    
    public void update(){
        //put stuff in here
    }
}
