package com.snakeyhips.robotpet;

public class ObstacleManager {
    //Higher index = lower on screen = higher y value
    private ArrayList<Obstacle> obstacles;
    private int robotGap;
    private int obstacleGap;
    private int obstacleHeight;
    private int color;
    
    public ObstacleManager(int robotGap, int obstacleGap, int obstacleHeight, int color){
      this.robotGap = robotGap;
      this.obstacleGap = obstacleGap;
      this.obstacleHeight = obstacleHeight;
      int.color = color;
      
      obstacles = new ArrayList<>();
      populateObstacles();
    }
    
    private void populateObstacles(){
      int currY = -5*Constants.SCREEN_HEIGHT/4;
      while(obstacles.get(obstacles.size() - 1).getRect().bottom < 0){
        int startX = (int)(Math.random()*(Constants.SCREEN_WIDTH - robotGap));
        obstacles.add(new Obstacle(color, startX, currY, obstacleHeight, robotGap));
        currY += obstacleHeight + obstacleGap;
      }
    }
}
