package controllers;

import java.io.Serializable;
import java.util.Objects;

public class SportsClub implements Serializable {
    //local variables
    private String name;
    private String location;

    //constructor sport club
    public SportsClub(String name, String location) {
        this.name = name;
        this.location = location;
    }

    //getters and setters for sports club
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    //equal method for comparing to objects in array list with getting string type user inputs
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        SportsClub club = (SportsClub) o;
        return Objects.equals(name, club.name);
    }

    //give getting inputs for java readable code
    @Override
    public int hashCode() {
        return Objects.hash(name, location);
    }
}
