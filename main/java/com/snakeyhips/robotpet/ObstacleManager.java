package com.snakeyhips.robotpet;

public class ObstacleManager {
    //Higher index = lower on screen = higher y value
    private ArrayList<Obstacle> obstacles;
    private int robotGap;
    private int obstacleGap;
    private int obstacleHeight;
    private int color;
    
    private long startTime;
    
    public ObstacleManager(int robotGap, int obstacleGap, int obstacleHeight, int color){
      this.robotGap = robotGap;
      this.obstacleGap = obstacleGap;
      this.obstacleHeight = obstacleHeight;
      this.color = color;
        
      startTime = System.currentTimeMillis();
      
      obstacles = new ArrayList<>();
      populateObstacles();
    }
    
    private void populateObstacles(){
      int currY = -5*Constants.SCREEN_HEIGHT/4;
      while(currY < 0){
        int startX = (int)(Math.random()*(Constants.SCREEN_WIDTH - robotGap));
        obstacles.add(new Obstacle(color, startX, currY, obstacleHeight, robotGap));
        currY += obstacleHeight + obstacleGap;
      }
    }
    
    public void update(){
        int elaspedTime = (int)(System.currentTimeMillis() - startTime);
        startTime = System.currentTimeMillis();
        float speed = Constants.SCREEN_HEIGHT/10000.0f;
        for(Obstacle ob : obstacles){
            ob.incrementY(speed * elaspedTime);
        }
        if(obstacles.get(obstacles.size - 1).getRect().top >= Constants.SCREEN_HEIGHT){
            int startX = (int)(Math.random()*(Constants.SCREEN_WIDTH - robotGap));
            obstacles.add(0, new Obstacle(color, startX,
            obstacles.get(0).getRect().top + obstacleHeight - obstacleGap, obstacleHeight, robotGap));
            obstacles.remove(obstacles.size() - 1);
        }
    }
    
    public void draw(Canvas canvas){
        for(Obstacle ob : obstacles){
            ob.draw(canvas);
        }
    }
}
