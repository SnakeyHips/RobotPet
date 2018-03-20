package com.snakeyhips.robotpet;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public class RobotCharacter extends GameObject {

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

 // Velocity of game character (pixels per update)
 private float velocity = 10.0f;

 private int movingVectorX;
 private int movingVectorY;

 private int viewWidth;
 private int viewHeight;

 public RobotCharacter(Bitmap image, int viewWidth, int viewHeight, int x, int y) {
  super(image, 4, 3, x, y);
  
  this.viewWidth = viewWidth;
  this.viewHeight = viewHeight;
  movingVectorX = viewWidth/2;
  movingVectorY = viewHeight/2;

  topToBottoms = new Bitmap[colCount];
  rightToLefts = new Bitmap[colCount];
  leftToRights = new Bitmap[colCount];
  bottomToTops = new Bitmap[colCount];

  for (int col = 0; col < colCount; col++) {
   topToBottoms[col] = createSubImageAt(ROW_TOP_TO_BOTTOM, col);
   rightToLefts[col] = createSubImageAt(ROW_RIGHT_TO_LEFT, col);
   leftToRights[col] = createSubImageAt(ROW_LEFT_TO_RIGHT, col);
   bottomToTops[col] = createSubImageAt(ROW_BOTTOM_TO_TOP, col);
  }
 }

 public Bitmap[] getMoveBitmaps() {
  switch (rowUsing) {
   case ROW_BOTTOM_TO_TOP:
    return bottomToTops;
   case ROW_LEFT_TO_RIGHT:
    return leftToRights;
   case ROW_RIGHT_TO_LEFT:
    return rightToLefts;
   case ROW_TOP_TO_BOTTOM:
    return topToBottoms;
   default:
    return null;
  }
 }

 public Bitmap getCurrentMoveBitmap() {
  Bitmap[] bitmaps = getMoveBitmaps();
  return bitmaps[colUsing];
 }
 
 public void update() {
  colUsing++;
  if (colUsing >= colCount) {
   colUsing = 0;
  }

  double movingVectorLength = Math.sqrt(movingVectorX * movingVectorX + movingVectorY * movingVectorY);

  // Calculate the new position of the game character.
  x += (int)(velocity * movingVectorX / movingVectorLength);
  y += (int)(velocity * movingVectorY / movingVectorLength);

  // When the game's character touches the edge of the screen, then change direction
  if (x < 0) {
   x = 0;
   movingVectorX = -movingVectorX;
  } else if (x > viewWidth - width) {
   x = viewWidth - width;
   movingVectorX = -movingVectorX;
  }

  if (y < 0) {
   y = 0;
   movingVectorY = -movingVectorY;
  } else if (y > viewHeight - height) {
   y = viewHeight - height;
   movingVectorY = -movingVectorY;
  }

  // rowUsing - put movingVectoxX == 0 if here for idle animations
  if (movingVectorX > 0) {
   if (movingVectorY > 0 && Math.abs(movingVectorX) < Math.abs(movingVectorY)) {
    rowUsing = ROW_TOP_TO_BOTTOM;
   } else if (movingVectorY < 0 && Math.abs(movingVectorX) < Math.abs(movingVectorY)) {
    rowUsing = ROW_BOTTOM_TO_TOP;
   } else {
    rowUsing = ROW_LEFT_TO_RIGHT;
   }
  } else {
   if (movingVectorY > 0 && Math.abs(movingVectorX) < Math.abs(movingVectorY)) {
    rowUsing = ROW_TOP_TO_BOTTOM;
   } else if (movingVectorY < 0 && Math.abs(movingVectorX) < Math.abs(movingVectorY)) {
    rowUsing = ROW_BOTTOM_TO_TOP;
   } else {
    rowUsing = ROW_RIGHT_TO_LEFT;
   }
  }
 }

 public void draw(Canvas canvas) {
  Bitmap bitmap = getCurrentMoveBitmap();
  canvas.drawBitmap(bitmap, x, y, null);
 }

 public void setMovingVector(int movingVectorX, int movingVectorY) {
  this.movingVectorX = movingVectorX;
  this.movingVectorY = movingVectorY;
 }

 /*old update method
 public void update() {
  this.colUsing++;
  if (colUsing >= this.colCount) {
   this.colUsing = 0;
  }
  // Current time in nanoseconds
  long now = System.nanoTime();

  // Never once did draw.
  if (lastDrawNanoTime == -1) {
   lastDrawNanoTime = now;
  }
  // Change nanoseconds to milliseconds (1 nanosecond = 1000000 milliseconds).
  int deltaTime = (int)((now - lastDrawNanoTime) / 1000000);

  // Distance moves
  float distance = VELOCITY * deltaTime;

  double movingVectorLength = Math.sqrt(movingVectorX * movingVectorX + movingVectorY * movingVectorY);

  // Calculate the new position of the game character.
  this.x += (int)(distance * movingVectorX / movingVectorLength);
  this.y += (int)(distance * movingVectorY / movingVectorLength);

  // When the game's character touches the edge of the screen, then change direction

  if (this.x < 0) {
   this.x = 0;
   this.movingVectorX = -this.movingVectorX;
  } else if (this.x > this.gameView.getWidth() - width) {
   this.x = this.gameView.getWidth() - width;
   this.movingVectorX = -this.movingVectorX;
  }

  if (this.y < 0) {
   this.y = 0;
   this.movingVectorY = -this.movingVectorY;
  } else if (this.y > this.gameView.getHeight() - height) {
   this.y = this.gameView.getHeight() - height;
   this.movingVectorY = -this.movingVectorY;
  }

  // rowUsing
  if (movingVectorX > 0) {
   if (movingVectorY > 0 && Math.abs(movingVectorX) < Math.abs(movingVectorY)) {
    this.rowUsing = ROW_TOP_TO_BOTTOM;
   } else if (movingVectorY < 0 && Math.abs(movingVectorX) < Math.abs(movingVectorY)) {
    this.rowUsing = ROW_BOTTOM_TO_TOP;
   } else {
    this.rowUsing = ROW_LEFT_TO_RIGHT;
   }
  } else {
   if (movingVectorY > 0 && Math.abs(movingVectorX) < Math.abs(movingVectorY)) {
    this.rowUsing = ROW_TOP_TO_BOTTOM;
   } else if (movingVectorY < 0 && Math.abs(movingVectorX) < Math.abs(movingVectorY)) {
    this.rowUsing = ROW_BOTTOM_TO_TOP;
   } else {
    this.rowUsing = ROW_RIGHT_TO_LEFT;
   }
  }
 }

 public void draw(Canvas canvas) {
  Bitmap bitmap = this.getCurrentMoveBitmap();
  canvas.drawBitmap(bitmap, x, y, null);
  // Last draw time.
  this.lastDrawNanoTime = System.nanoTime();
 }

 public void setMovingVector(int movingVectorX, int movingVectorY) {
  this.movingVectorX = movingVectorX;
  this.movingVectorY = movingVectorY;
 }*/
}
