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
  private RobotCharacter robotCharacter;
  private int perSecond;

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
    robotCharacter = new RobotCharacter(this, BitmapFactory.decodeResource(this.getResources(),R.drawable.chibi1), 
                                        this.getWidth()/2, this.getHeight()/2);
    thread = new MainThread(getHolder(), this);
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
          robotCharacter.setMovingVector((int)event.getX() - this.chibi1.getX(), (int)event.getY() - this.chibi1.getY());
          break;
      }
      return true;
  }
  
  public void update() {
    if(perSecond < 99){
      perSecond = 0;
      robotCharacter.update();
      if(MainActivity.robot.getHunger() > 0){ MainActivity.robot.setHunger(MainActivity.robot.getHunger() - 1); }
    } else {
      perSecond++;
    }
  }
  
  @Override
  public void draw(Canvas canvas) {
    if(canvas != null){
        super.draw(canvas);
        canvas.drawColor(Color.WHITE);
        robotCharacter.draw(canvas);
    }
  }       
}
