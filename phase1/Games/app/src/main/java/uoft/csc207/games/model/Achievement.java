package uoft.csc207.games.model;

public class Achievement {
    private String achievementName;
    private String description;

    public Achievement(String name, String description){
        achievementName = name;
        this.description = description;
    }

    public String getAchiementName(){
        return achievementName;
    }
    public String getDescription(){
        return description;
    }
}
