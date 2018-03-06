package com.snakeyhips.robotpet;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Obstacle implements GameObject{
      private Rect rect;
      private Rect rect2;
      private int color;
      private int startX;
      private int startY;
      private int robotGap;
    
  public Obstacle(int color, int startX, int startY, int rectHeight, int robotGap) {
      this.color = color;
      rect = new Rect(0, startY, startX, startY + rectHeight);
      rect2 = new Rect(startX + robotGap, startY, Constants.SCREEN_WIDTH, startY + rectHeight);
  }
      
  public Rect getRect(){
      return rect;
  }
      
  public void incrementY(float y){
      rect.top += y;
      rect.bottom += y;
      rect2.top += y;
      rect2.bottom += y;
  }
  
  public boolean playerCollide(RobotSprite robot) {
    if(rect.contains(robot.getRect().left, robot.getRect().top)
      || rect.contains(robot.getRect().right, robot.getRect().top)
      || rect.contains(robot.getRect().left, robot.getRect().bottom)
      || rect.contains(robot.getRect().right, robot.getRect().bottom)){
        return true;
      } else {
        return false;
      }
    }
  
  public void draw(Canvas canvas){
      //canvas.drawBitmap(sprite, 100, 100, null);
      Paint paint = new Paint();
      paint.setColor(color);
      canvas.drawRect(rect, paint);
      canvas.drawRect(rect2, paint);
  }
    
  public void update(){
      //put stuff in here
  }
    
  /*public void update(Point point){
      //l, t, r, b
      rect.set(point.x - rect.width()/2, point.y - rect.height()/2,
               point.x + rect.width()/2, point.y + rect.height()/2);
  }*/
}
