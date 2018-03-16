package com.snakeyhips.robotpet;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback{
  
  private MainThread thread;
  private RobotSprite robotSprite;

  public GameView(Context context, AttributeSet attrs) {
      super(context, attrs);
      getHolder().addCallback(this);
      setFocusable(true);
  }
  
  @Override
  public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
  }

  @Override
  public void surfaceCreated(SurfaceHolder holder) {
    thread = new MainThread(getHolder(), this);
    robotSprite = new RobotSprite(BitmapFactory.decodeResource(this.getResources(), R.drawable.robot), 100, 50);
    thread.setRunning(true);
    thread.start();
  }

  @Override
  public void surfaceDestroyed(SurfaceHolder holder) {
    boolean retry = true;
    while(retry) {
      try {
        thread.setRunning(false);
        thread.join();  
      } catch(InterruptedException e) {
          e.printStackTrace();
      }
      retry = false;
    }
  }
  
  //Handle touch events in here
  @Override
  public boolean onTouchEvent(MotionEvent event){
      switch(event.getAction()){
        case MotionEvent.ACTION_DOWN:
        case MotionEvent.ACTION_MOVE: 
            robotSprite.setMovingVector((int)event.getX() - robotSprite.getX(),(int)event.getY() - robotSprite.getY());
            return true;
            break;
      }
      return true;
  }
  
  public void update() {
      robotSprite.update();
      //if(MainActivity.robot.getHunger() > 0){ MainActivity.robot.setHunger(MainActivity.robot.getHunger() - 1); }
  }
  
  @Override
  public void draw(Canvas canvas) {
    if(canvas != null){
        super.draw(canvas);
        canvas.drawColor(Color.WHITE);
        robotSprite.draw(canvas);
    }
  }       
}
