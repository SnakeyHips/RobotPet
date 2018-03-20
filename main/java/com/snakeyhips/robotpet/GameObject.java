package com.snakeyhips.robotpet;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public abstract class GameObject {

 protected Bitmap image;

 protected final int rowCount;
 protected final int colCount;

 protected final int WIDTH;
 protected final int HEIGHT;

 protected final int width;


 protected final int height;
 protected int x;
 protected int y;

 public GameObject(Bitmap image, int rowCount, int colCount, int x, int y) {

  this.image = image;
  this.rowCount = rowCount;
  this.colCount = colCount;

  this.x = x;
  this.y = y;

  this.WIDTH = image.getWidth();
  this.HEIGHT = image.getHeight();

  this.width = this.WIDTH / colCount;
  this.height = this.HEIGHT / rowCount;
 }


 protected Bitmap createSubImageAt(int row, int col) {
  Bitmap subImage = Bitmap.createBitmap(image, col * width, row * height, width, height);
  return subImage;
 }

 public int getX() {
  return this.x;
 }

 public int getY() {
  return this.y;
 }


 public int getHeight() {
  return height;
 }

 public int getWidth() {
  return width;
 }

}
