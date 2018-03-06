package com.snakeyhips.robotpet;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback{
  
  private MainThread thread;
  private RobotSprite robotSprite;
  private Point robotPoint;
  private ObstacleManager obstacleManager;
 
  //private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
  //private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

  public GameView(Context context, AttributeSet attrs) {
      super(context, attrs);

      getHolder().addCallback(this);
      thread = new MainThread(getHolder(), this);
      robotSprite = new RobotSprite(new Rect(100, 100, 200, 200), Color.rgb(255, 0, 0));
      robotPoint = new Point(150, 150);
      obstacleManager = new ObstacleManager(200, 350, 75, Color.BLACK);
    
      setFocusable(true);
  }
  
  @Override
  public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

  }

  @Override
  public void surfaceCreated(SurfaceHolder holder) {
    thread.setRunning(true);
    thread.start();
    robotSprite = new RobotSprite(BitmapFactory.decodeResource(getResources(),R.drawable.robot));
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
      switch(Event.getAction()){
        case MotionEvent.ACTION_DOWN:
        case MotionEvent.ACTION_MOVE:
          robotPoint.set((int)event.getX(), (int)event.getY());          
      }
      return true;
      //return super.onTouchEvent(event);
  }
  
  public void update() {
      robotSprite.update(robotPoint);
      obstacleManager.update();
  }
  
  @Override
  public void draw(Canvas canvas) {
    super.draw(canvas);
    if(canvas != null) {
      canvas.drawColor(Color.WHITE);
      robotSprite.draw(canvas);
      obstacleManager.draw(canvas);
    }
  }
}
