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
  private Point robotPoint;
  private ObstacleManager obstacleManager;
  private boolean movingPlayer = false;
  private boolean gameOver = false;
  private long gameOverTime;
  private Rect r = new Rect();
 
  //private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
  //private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

  public GameView(Context context, AttributeSet attrs) {
      super(context, attrs);

      getHolder().addCallback(this);
      thread = new MainThread(getHolder(), this);
      robotSprite = new RobotSprite(new Rect(100, 100, 200, 200), Color.rgb(255, 0, 0));
      robotPoint = new Point(Constans.SCREEN_WIDTH/2, 3*Constants.SCREEN_HEIGHT/4);
      robotSprite.update(robotPoint);
      obstacleManager = new ObstacleManager(200, 350, 75, Color.BLACK);
    
      setFocusable(true);
  }
  
  public void reset(){
      robotPoint = new Point(Constants.SCREEN_WIDTH/2, 3*Constants.SCREEN_HEIGHT/4);
      robotSprite.update(robotPoint);
      obstacleManager = new ObstacleManager(200, 350, 75, Color.BLACK);
      movingPlayer = false;
  }
  
  @Override
  public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

  }

  @Override
  public void surfaceCreated(SurfaceHolder holder) {
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
          if(!gameOver && robot.getRect().contains((int)event.getX(), (int)event.getY()){
            movingPlayer = true;
          }
          if(gameOver && System.currentTimeMillis() - gameOverTime >= 2000){
            reset();
            gameOver = false;
          }
          break;
        case MotionEvent.ACTION_MOVE:
          if(!gameOver && movingPlayer){
            robotPoint.set((int)event.getX(), (int)event.getY()); 
          }
          break;
        case MotionEvent.ACTION_UP:
             movingPlayer = false;
             break;
      }
      return true;
  }
  
  public void update() {
    if(!gameOver){
      robotSprite.update(robotPoint);
      obstacleManager.update();
      if(obstacleManager.playerCollide(robot)){
        gameOver = true;
        gameOvertime = System.currentTimeMillis();
      }
    }
  }
  
  @Override
  public void draw(Canvas canvas) {
    super.draw(canvas);
    canvas.drawColor(Color.WHITE);
    robotSprite.draw(canvas);
    obstacleManager.draw(canvas);
    
    if(gameOver){
      Paint paint = new Paint();
      paint.setTextSize(100);
      paint.setColor(Color.MAGENTA);
      drawCenterText(canvas, paint, "Game Over");
    }
  }
             
  private void drawCenterText(Canvas canvas, Paint paint, String text) {
    paint.setTextAlign(Paint.Align.LEFT);
    canvas.getClipBounds(r);
    int cHeight = r.height();
    int cWidth = r.width();
    paint.getTextBounds(text, 0, text.length(), r);
    float x = cWidth / 2f - r.width() / 2f - r.left;
    float y = cHeight / 2f + r.height() / 2f - r.bottom;
    canvas.drawText(text, x, y, paint);
  }           
}
