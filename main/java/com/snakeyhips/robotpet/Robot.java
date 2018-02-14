public class Robot {
    private UUID id;
    private String name;
    private int age;
    private int happy;
    private int hunger;
    private int fatigue;
    private int naughty;
    private int waste;
    private Boolean illness;
    
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
    
    public void SetAge(int age){
        this.age = age;
    }
    public int getAge(){
        return age;
    }
    
    public void SetHappy(int happy){
        this.happy = happy;
    }
    public int getHappy(){
        return happy;
    }
    
    public void SetHunger(int hunger){
        this.hunger = hunger;
    }
    public int getHunger(){
        return hunger;
    }

    public void SetFatigue(int fatigue){
        this.fatigue = fatigue;
    }
    public int getFatigue(){
        return fatigue;
    }
    
    public void SetNaughty(int naughty){
        this.naughty = naughty;
    }
    public int getNaughty(){
        return naughty;
    }
    
    public void SetWaste(int waste){
        this.waste = waste;
    }
    public int getWaste(){
        return waste;
    }
    
    public void SetIllness(Boolean illness){
        this.illness = illness;
    }
    public Boolean getIllness(){
        return illness;
    }
}
