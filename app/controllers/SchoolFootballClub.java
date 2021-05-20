package controllers;

public class SchoolFootballClub extends FootballClub {
    private String schoolName;

    public SchoolFootballClub(String name, String location, String schoolName, int wins, int draws, int loss, int gF, int gA) {
        super(name, location, wins, draws, loss, gF, gA);
        this.schoolName=schoolName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        while (true) {
            try {
                this.schoolName = schoolName;
                break;
            }catch (Exception e){
                System.out.println("invalid input!");
            }
        }
    }
}
