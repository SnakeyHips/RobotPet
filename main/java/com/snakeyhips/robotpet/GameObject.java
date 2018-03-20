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
 protected int rowCount;
 protected int colCount;
 protected int imageWidth;
 protected int imageHeight;
 protected int width;
 protected int height;
 protected int x;
 protected int y;

 public GameObject(Bitmap image, int rowCount, int colCount, int x, int y) {

  this.image = image;
  this.rowCount = rowCount;
  this.colCount = colCount;

  this.x = x;
  this.y = y;

  this.imageWidth = image.getWidth();
  this.imageHeight = image.getHeight();

  this.width = this.imageWidth / colCount;
  this.height = this.imageHeight / rowCount;
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
