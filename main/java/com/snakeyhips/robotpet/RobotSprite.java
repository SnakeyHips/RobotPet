package com.snakeyhips.robotpet;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public class RobotSprite extends GameObject {

    public RobotSprite(Context context){
        super(context, new Rect(100, 100, 200, 200),
                                BitmapFactory.decodeResource(context.getResources(), R.drawable.alien),
                                BitmapFactory.decodeResource(context.getResources(), R.drawable.alien_walk1),
                                BitmapFactory.decodeResource(context.getResources(), R.drawable.alien_walk2));
    }
}
