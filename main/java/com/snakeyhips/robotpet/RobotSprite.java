package com.snakeyhips.robotpet;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;

public class RobotSprite  {

    private Bitmap sprite;
    private int x;
    private int y;

    private int maxX;
    private int minX;

    private Animation idle;
    private Animation walkRight;
    private Animation walkLeft;

    public RobotSprite(Context context, Bitmap sprite, int x, int y, int maxX, int minX){
        this.sprite = sprite;
        this.x = x;
        this.y = y;
        this.maxX = maxX;
        this.minX = minX;

        Bitmap idleImg = BitmapFactory.decodeResource(context.getResources(), R.drawable.alien);
        Bitmap walk1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.alien_walk1);
        Bitmap walk2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.alien_walk2);

        idle = new Animation(new Bitmap[]{idleImg}, 2);
        walkRight = new Animation(new Bitmap[]{walk1, walk2}, 0.5f);

        Matrix m = new Matrix();
        m.preScale(-1, 1);
        walk1 = Bitmap.createBitmap(walk1, 0, 0, walk1.getWidth(), walk1.getHeight(), m, false);
        walk2 = Bitmap.createBitmap(walk2, 0, 0, walk2.getWidth(), walk2.getHeight(), m, false);

        walkLeft = new new Animation(new Bitmap[]{walk1, walk2}, 0.5f);
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(sprite, 100, 100, null);
    }

    public int getX(){
        return x;
    }

    public void setX(int x){
        this.x = x;
    }

    public int getY(){
        return y;
    }

    public void update(){
        if( x < minX){
            x = minX;
        }
        if(x > maxX) {
            x = maxX;
        }

    }

    /* vector method
    private static final int ROW_TOP_TO_BOTTOM = 0;
    private static final int ROW_RIGHT_TO_LEFT = 1;
    private static final int ROW_LEFT_TO_RIGHT = 2;
    private static final int ROW_BOTTOM_TO_TOP = 3;
 
    // Row index of Image are being used.
    private int rowUsing = ROW_LEFT_TO_RIGHT;
 
    private int colUsing;
 
    private Bitmap[] leftToRights;
    private Bitmap[] rightToLefts;
    private Bitmap[] topToBottoms;
    private Bitmap[] bottomToTops;
 
    // Velocity of game character (pixel/millisecond)
<<<<<<< HEAD
    public static final float VELOCITY = 0.05f;
=======
    public static final float VELOCITY = 0.5f;
>>>>>>> fc5730ebeaa99ad9fd7d3dca5753835c1922e459
 
    private int movingVectorX = 10;
    private int movingVectorY = 5;
 
    private long lastDrawNanoTime =-1;
 
    private GameView gameView;
 
    public RobotSprite(GameView gameView, Bitmap image, int x, int y) {
        super(image, 4, 3, x, y);
 
        this.gameView= gameView;
 
        this.topToBottoms = new Bitmap[colCount]; // 3
        this.rightToLefts = new Bitmap[colCount]; // 3
        this.leftToRights = new Bitmap[colCount]; // 3
        this.bottomToTops = new Bitmap[colCount]; // 3
 
        for(int col = 0; col< this.colCount; col++ ) {
            this.topToBottoms[col] = this.createSubImageAt(ROW_TOP_TO_BOTTOM, col);
            this.rightToLefts[col]  = this.createSubImageAt(ROW_RIGHT_TO_LEFT, col);
            this.leftToRights[col] = this.createSubImageAt(ROW_LEFT_TO_RIGHT, col);
            this.bottomToTops[col]  = this.createSubImageAt(ROW_BOTTOM_TO_TOP, col);
        }
    }
 
    public Bitmap[] getMoveBitmaps()  {
        switch (rowUsing)  {
            case ROW_BOTTOM_TO_TOP:
                return  this.bottomToTops;
            case ROW_LEFT_TO_RIGHT:
                return this.leftToRights;
            case ROW_RIGHT_TO_LEFT:
                return this.rightToLefts;
            case ROW_TOP_TO_BOTTOM:
                return this.topToBottoms;
            default:
                return null;
        }
    }
 
    public Bitmap getCurrentMoveBitmap()  {
        Bitmap[] bitmaps = this.getMoveBitmaps();
        return bitmaps[this.colUsing];
    }
 
 
    public void update()  {
        this.colUsing++;
        if(colUsing >= this.colCount)  {
            this.colUsing =0;
        }
        // Current time in nanoseconds
        long now = System.nanoTime();
 
        // Never once did draw.
        if(lastDrawNanoTime == -1) {
            lastDrawNanoTime = now;
        }
        // Change nanoseconds to milliseconds (1 nanosecond = 1000000 milliseconds).
        int deltaTime = (int)((now - lastDrawNanoTime) / 1000000);
 
        // Distance moves
        float distance = (VELOCITY * deltaTime);
 
        double movingVectorLength = Math.sqrt(movingVectorX * movingVectorX + movingVectorY * movingVectorY);
 
        // Calculate the new position of the game character.
        this.x += (int)(distance * movingVectorX / movingVectorLength);
        this.y += (int)(distance * movingVectorY / movingVectorLength);
 
        // When the game's character touches the edge of the screen, then change direction
 
        if(this.x < 0 )  {
            this.x = 0;
            this.movingVectorX = -this.movingVectorX;
        } else if(this.x > this.gameView.getWidth() - width)  {
            this.x = this.gameView.getWidth() - width;
            this.movingVectorX = -this.movingVectorX;
        }
 
        if(this.y < 0 )  {
            this.y = 0;
            this.movingVectorY = -this.movingVectorY;
        } else if(this.y > this.gameView.getHeight() - height)  {
            this.y = this.gameView.getHeight() - height;
            this.movingVectorY = -this.movingVectorY;
        }
 
        // rowUsing
        if( movingVectorX > 0 )  {
            if(movingVectorY > 0 && Math.abs(movingVectorX) < Math.abs(movingVectorY)) {
                this.rowUsing = ROW_TOP_TO_BOTTOM;
            }else if(movingVectorY < 0 && Math.abs(movingVectorX) < Math.abs(movingVectorY)) {
                this.rowUsing = ROW_BOTTOM_TO_TOP;
            }else  {
                this.rowUsing = ROW_LEFT_TO_RIGHT;
            }
        } else {
            if(movingVectorY > 0 && Math.abs(movingVectorX) < Math.abs(movingVectorY)) {
                this.rowUsing = ROW_TOP_TO_BOTTOM;
            }else if(movingVectorY < 0 && Math.abs(movingVectorX) < Math.abs(movingVectorY)) {
                this.rowUsing = ROW_BOTTOM_TO_TOP;
            }else  {
                this.rowUsing = ROW_RIGHT_TO_LEFT;
            }
        }
    }
 
    public void draw(Canvas canvas)  {
        Bitmap bitmap = this.getCurrentMoveBitmap();
        canvas.drawBitmap(bitmap, x, y, null);
        // Last draw time.
        this.lastDrawNanoTime = System.nanoTime();
    }
 
    public void setMovingVector(int movingVectorX, int movingVectorY)  {
        this.movingVectorX = movingVectorX;
        this.movingVectorY = movingVectorY;
    }*/
}
