package controllers;

import java.io.Serializable;
import java.util.Objects;

public class SportsClub implements Serializable { //ai
    private String club_name;
    private String location_club;
    public SportsClub(){

    }


    public SportsClub(String club_name, String location_club) {
        this.club_name = club_name;
        this.location_club = location_club;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SportsClub club = (SportsClub) o;
        return Objects.equals(club_name, club.club_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(club_name, location_club);
    }
    public String getclub_name() {
        return club_name;
    }

    public     void setclub_name(String club_name) {

        this.club_name = club_name;
    }