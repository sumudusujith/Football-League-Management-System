package controllers;

public class UniversityFootballClub extends FootballClub {
    //local variable for university football club
    private String uniName;

    //constructor for university football club
    public UniversityFootballClub(String name, String location, String uniName, int wins, int draws, int loss, int gF, int gA) {
        super(name, location, wins, draws, loss, gF, gA);
        this.uniName = uniName;
    }


    //getters setters for UniversityFootballClub
    public String getUniName() {
        return uniName;
    }

    public void setUniName(String uniName) {
        this.uniName = uniName;
    }
}
