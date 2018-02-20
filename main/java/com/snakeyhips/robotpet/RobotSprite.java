package com.snakeyhips.robotpet;

public class RobotSprite{
    private Bitmap sprite;
    
    public RobotSprite(Bitmap bmp) {
        sprite = bmp;
    }
    
    public void draw(Canvas canvas){
        canvas.drawBitmap(sprite, 100, 100, null);
    }
}
