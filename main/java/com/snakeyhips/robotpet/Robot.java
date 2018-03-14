package com.snakeyhips.robotpet;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

public class Robot extends BaseObservable {
    private String name;
    private int age;
    private int happy;
    private int hunger;
    private int fatigue;
    private int naughty;
    private int waste;
    private boolean illness;
    
    public void setName(String name){
        this.name = name;
        notifyPropertyChanged(name);  
    }
    public String getName(){
        return name;
    }
    
    public void setAge(int age){
        this.age = age;
        notifyPropertyChanged(age);
    }
    public String getAge(){
        return String.valueOf(age);
    }
    
    public void setHappy(int happy){
        this.happy = happy;
        notifyPropertyChanged(happy);
    }
    public String getHappy(){
        return String.valueOf(happy);
    }
    
    public void setHunger(int hunger){
        this.hunger = hunger;
        notifyPropertyChanged(hunger);
    }
    public String getHunger(){
        return String.valueOf(hunger);
    }

    public void setFatigue(int fatigue){
        this.fatigue = fatigue;
        notifyPropertyChanged(fatigue);
    }
    public String getFatigue(){
        return String.valueOf(fatigue);
    }
    
    public void setNaughty(int naughty){
        this.naughty = naughty;
        notifyPropertyChanged(naughty);
    }
    public String getNaughty(){
        return String.valueOf(naughty);
    }
    
    public void setWaste(int waste){
        this.waste = waste;
        notifyPropertyChanged(waste);
    }
    public String getWaste(){
        return String.valueOf(waste);
    }
    
    public void setIllness(boolean illness){
        this.illness = illness;
        notifyPropertyChanged(illness);
    }
    public String getIllness(){
        return illness;
    }
}
