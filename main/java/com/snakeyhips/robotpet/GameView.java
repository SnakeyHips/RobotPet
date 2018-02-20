package com.snakeyhips.robotpet;

public class GameView extends SurfaceView implements SurfaceHolder.Callback{
  
  private MainThread thread;
  private RobotSprite robotSprite;
  private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
  private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

  public GameView(Context context) {
      super(context);

      getHolder().addCallback(this);
      thread = new MainThread(getHolder(), this);
      setFocusable(true);
  }
  
  @Override
  public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

  }

  @Override
  public void surfaceCreated(SurfaceHolder holder) {
    thread.setRunning(true);
    tread.start();
    robotSprite = new RobotSprite(BitmapFactory.decodeResource(getResources(),R.drawable.babyRobot));
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
  
  public void update() {

  }
  
  @Override
  public void draw(Canvas canvas) {
    super.draw(canvas);
    if(canvas != null) {
      canvas.drawColor(Color.WHITE);
      Paint paint = new Paint();
      paint.setColor(Color.rgb(250, 0, 0));
      canvas.drawRect(100, 100, 200, 200, paint);
      robotSprite.draw(canvas);
    }
  }
}
