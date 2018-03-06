package com.snakeyhips.robotpet;

import android.graphics.Canvas;

public class Obstacle implements GameObject{
      private Rect rect;
      private int color;
      private int startX;
      private int robotGap;
    
  public Obstacle(Rect rect, int color int startX, int robotGap) {
      this.rect = rect;
      this.color = color;
      this.startX = startX;
      this.robotGap = robotGap;
  }
  
  public boolean playerCollide(RobotSprite robot) {
    if(rect.contains(robot.getRect().left, robot.getRect().top)
      || rect.contains(robot.getRect().right, robot.getRect().top)
      || rect.contains(robot.getRect().left, robot.getRect().bottom)
      || rect.contains(robot.getRect().right, robot.getRect().bottom){
        return true;
      } else {
        return false;
      }
    }
  }
  
  public void draw(Canvas canvas){
      //canvas.drawBitmap(sprite, 100, 100, null);
      Paint paint = new Paint();
      paint.setColor(color);
      canvas.drawRect(rect, paint);
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
