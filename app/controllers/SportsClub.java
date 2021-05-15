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