package controllers;

import java.io.Serializable;
import java.util.Objects;

public class FootballClub extends SportsClub implements Serializable {

    //local variables for football club
    private final int winPoint=3;
    private final int DrawPoint=1;

    private int playedMatches;
    private int wins;
    private int draws;
    private int loss;
    private int gF;
    private int gA;
    private int points;
    //constructor for football club
    public FootballClub (String name, String location, int wins, int draws, int loss, int gF, int gA) {
        super(name,location);
        this.wins=wins;
        this.draws=draws;
        this.loss=loss;
        this.gF=gF;
        this.gA=gA;
        this.points=wins*winPoint+draws*DrawPoint;
        this.playedMatches= wins+draws+loss;
    }

    //toString method for display each club statics
    @Override
    public String toString() {
        return "Statics of "+ getName()+ " football club [" +
                " playedMatches:" + playedMatches +
                " wins :" + wins +
                " draws :" +draws +
                " loss :" + loss +
                " goals forward: " + gF +
                " goals against: " + gA +
                " points: " + points +"]";
    }
    //equals method
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), playedMatches, wins, draws, loss, gF, gA, points);
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public int getPlayedMatches() {
        return playedMatches;
    }

    public void setPlayedMatches(int playedMatches) {
        this.playedMatches=playedMatches;

    }

    public int getLoss() {
        return loss;
    }

    public void setLoss(int loss) {
        this.loss = loss;
    }

    public int getgF() {
        return gF;
    }

    public void setgF(int gF) {
        this.gF = gF;
    }

    public int getgA() {
        return gA;
    }

    public void setgA(int gA) {
        this.gA = gA;
    }

}
