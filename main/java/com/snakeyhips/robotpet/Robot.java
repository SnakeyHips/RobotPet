package com.snakeyhips.robotpet;

import java.util.UUID;

public class Robot {
    private UUID id;
    private String name;
    private int age;
    private int happy;
    private int hunger;
    private int fatigue;
    private int naughty;
    private int waste;
    private boolean illness;
    
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
    
    public void setVariableChangeListener(VariableChangeListener variableChangeListener) {
        this.variableChangeListener = variableChangeListener;
    }
}
