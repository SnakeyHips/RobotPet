package com.snakeyhips.robotpet;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import java.util.UUID;

public class Robot {
    
    //private Bitmap sprite;
    private Rect rect;
    private int color;
    private UUID id;
    private String name;
    private int age;
    private int happy;
    private int hunger;
    private int fatigue;
    private int naughty;
    private int waste;
    private boolean illness;
    
    public Robot(Rect rect, int color) {
        this.rect = rect;
        this.color = color;
    }
    
    public void setId(UUID id){
        this.id = id;
    }
    public UUID getId(){
        return id;
    }
    
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    
    public void setAge(int age){
        this.age = age;
    }
    public int getAge(){
        return age;
    }
    
    public void setHappy(int happy){
        this.happy = happy;
    }
    public int getHappy(){
        return happy;
    }
    
    public void setHunger(int hunger){
        this.hunger = hunger;
    }
    public int getHunger(){
        return hunger;
    }

    public void setFatigue(int fatigue){
        this.fatigue = fatigue;
    }
    public int getFatigue(){
        return fatigue;
    }
    
    public void setNaughty(int naughty){
        this.naughty = naughty;
    }
    public int getNaughty(){
        return naughty;
    }
    
    public void setWaste(int waste){
        this.waste = waste;
    }
    public int getWaste(){
        return waste;
    }
    
    public void setIllness(boolean illness){
        this.illness = illness;
    }
    public boolean getIllness(){
        return illness;
    }
    
    public Rect getRect(){
        return rect;
    }
    
    public void draw(Canvas canvas){
        //canvas.drawBitmap(sprite, 100, 100, null);
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rect, paint);
    }
    
    public void update(Point point){
        //l, t, r, b
        rect.set(point.x - rect.width()/2, point.y - rect.height()/2,
                 point.x + rect.width()/2, point.y + rect.height()/2);
    }
}
