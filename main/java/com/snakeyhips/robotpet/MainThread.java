package com.snakeyhips.robotpet;

public class MainThread extends Thread {
    private SurfaceHolder surfaceHolder;
    private GameView gameView;

    public MainThread(SurfaceHolder surfaceHolder, GameView gameView) {

        super();
        this.surfaceHolder = surfaceHolder;
        this.gameView = gameView;

    }
}
