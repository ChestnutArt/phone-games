package uoft.csc207.games.controller;

import java.io.Serializable;

public class Score implements Serializable {
    private String name;
    private int points;
    private int money;

    public Score(String name, int points, int money){
    this.money = money;
    this.name = name;
    this.points = points;}

    public int getMoney() {
        return money;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }
    public void setName(String name){
        this.name = name;
    }
}
