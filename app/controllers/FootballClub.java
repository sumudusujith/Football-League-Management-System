package controllers;

import java.io.Serializable;
import java.util.Objects;

public class FootballClub extends SportsClub implements Serializable {
    private int nomatches;
    private int wins;
    private int draws;
    private int loss;
    private int goals_scored;
    private int goals_recieved;
    private int points;
    /*public sample11.FootballClub(){

    }*/


    public FootballClub(String club_name , String location_club , int nomatches, int wins, int draws, int loss, int goals_scored, int goals_recieved, int points)     {
        super( club_name, location_club);
        this.nomatches = nomatches;
        this.wins = wins;
        this.draws = draws;
        this.loss = loss;
        this.goals_scored = goals_scored;
        this.goals_recieved = goals_recieved;
        this.points = points;

}
    @Override
    public String toString() {
        return "sample11.FootballClub{" +
                "playedMatches=" + nomatches +
                ", wins=" + wins +
                ", draws=" + draws +
                ", loss=" + loss +
                ", gF=" + getgoals_scored() +
                ", gA=" + getgoals_recieved() +
                ", points=" + points +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nomatches, wins, draws, loss, goals_scored, goals_recieved, points);
    }

    public int getnomatches() {
        return nomatches;
    }

    public void setnomatches(int nomatches) {
       this.nomatches = nomatches;
    }

    public int getwins() {
        return wins;
    }

    public  void setwins(int wins) {
        this.wins = wins;
    }

    public int getdraws() {
        return draws;
    }

    public  void setdraws(int draws) {
        this.draws = draws;
    }

    public int getloss() {
        return loss;
    }

    public  void setloss(int loss) {
        this.loss = loss;
    }

    public int getgoals_scored() {
        return goals_scored;
    }

    public  void setgoals_scored(int goals_scored) {
        this.goals_scored = goals_scored;
    }

    public int getgoals_recieved() {
        return goals_recieved;
    }

    public  void setgoals_recieved(int goals_recieved) {
        this.goals_recieved = goals_recieved;
    }

    public int getpoints() {
        return points;
    }

    public void setpoints(int points) {
        this.points = points;
    }

}

