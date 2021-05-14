package controllers;

import java.io.Serializable;


public class Match implements Serializable {

    private FootballClub homefcTeam;
    private FootballClub awayfcTeam;
    private String fcstadium;
    private int homefcTeamGoals;
    private int awayfcTeamGoals;
    private Date date;

    public Match(FootballClub homefcTeam,FootballClub awayfcTeam, String fcstadium, int homefcTeamGoals, int awayfcTeamGoals, Date date) {
        this.homefcTeam = homefcTeam;
        this.awayfcTeam = awayfcTeam;
        this.fcstadium = fcstadium;
        this.homefcTeamGoals = homefcTeamGoals;
        this.awayfcTeamGoals = awayfcTeamGoals;
        this.date = date;
    }

    public FootballClub getHomefcTeam() {
        return homefcTeam;
    }

    public void setHomefcTeam(FootballClub homefcTeam) {
        this.homefcTeam = homefcTeam;
    }

    public FootballClub getAwayfcTeam() {
        return awayfcTeam;
    }

    public void setAwayfcTeam(FootballClub awayfcTeam) {
        this.awayfcTeam = awayfcTeam;
    }

    public String getfcStadium() {
        return fcstadium;
    }

    public void setfcStadium(String fcstadium) {
        this.fcstadium = fcstadium;
    }

    public int getHomefcTeamGoals() {
        return homefcTeamGoals;
    }

    public void setHomefcTeamGoals(int homefcTeamGoals) {
        this.homefcTeamGoals = homefcTeamGoals;
    }

    public int getAwayfcTeamGoals() {
        return awayfcTeamGoals;
    }

    public void setAwayTeamGoals(int awayfcTeamGoals) {
        this.awayfcTeamGoals = awayfcTeamGoals;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
