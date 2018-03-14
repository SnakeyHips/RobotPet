package com.snakeyhips.robotpet;

public class Robot {
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
    }
    public String getAge(){
        return String.valueOf(age);
    }
    
    public void setHappy(int happy){
        this.happy = happy;
    }
    public String getHappy(){
        return String.valueOf(happy);
    }
    
    public void setHunger(int hunger){
        this.hunger = hunger;
    }
    public String getHunger(){
        return String.valueOf(hunger);
    }

    public void setFatigue(int fatigue){
        this.fatigue = fatigue;
    }
    public String getFatigue(){
        return String.valueOf(fatigue);
    }
    
    public void setNaughty(int naughty){
        this.naughty = naughty;
    }
    public String getNaughty(){
        return String.valueOf(naughty);
    }
    
    public void setWaste(int waste){
        this.waste = waste;
    }
    public String getWaste(){
        return String.valueOf(waste);
    }
    
    public void setIllness(boolean illness){
        this.illness = illness;
    }
    public String getIllness(){
        return illness;
    }
}
