package com.snakeyhips.robotpet;

public class GameView extends SurfaceView implements SurfaceHolder.Callback{
  
  private MainThread thread;

  public GameView(Context context) {
      super(context);

      getHolder().addCallback(this);
  }
  
  @Override
  public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

  }

  @Override
  public void surfaceCreated(SurfaceHolder holder) {

  }

  @Override
  public void surfaceDestroyed(SurfaceHolder holder) {

  }
}
