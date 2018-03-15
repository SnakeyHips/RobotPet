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
    }
    public String getName(){
        return name;
    }

    public void setAge(int age){
        this.age = age;
        notifyPropertyChanged(BR.age);
    }
    @Bindable
    public int getAge(){
        return age;
    }

    public void setHappy(int happy){
        this.happy = happy;
        notifyPropertyChanged(BR.happy);
    }
    @Bindable
    public int getHappy(){
        return happy;
    }

    public void setHunger(int hunger){
        this.hunger = hunger;
        notifyPropertyChanged(BR.hunger);
    }
    @Bindable
    public int getHunger(){
        return hunger;
    }

    public void setFatigue(int fatigue){
        this.fatigue = fatigue;
        notifyPropertyChanged(BR.fatigue);
    }
    @Bindable
    public int getFatigue(){
        return fatigue;
    }

    public void setNaughty(int naughty){
        this.naughty = naughty;
        notifyPropertyChanged(BR.naughty);
    }
    @Bindable
    public int getNaughty(){
        return naughty;
    }

    public void setWaste(int waste){
        this.waste = waste;
        notifyPropertyChanged(BR.waste);
    }
    @Bindable
    public int getWaste(){
        return waste;
    }

    public void setIllness(boolean illness){
        this.illness = illness;
    }
    public boolean getIllness(){
        return illness;
    }
}
