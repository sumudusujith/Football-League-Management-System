package controllers;

import java.io.IOException;

interface LeagueManager {
    void addclub(FootballClub footballClub);
    void deleteclub(String club_name);
    void displaystatistics();
    void displaytable();
    void addPlayedfcMatch(int day,int month,int year, String homefcname, int homefcGoals, String visitorfcname, int visitorfcGoals, String stadium);
    void saveLeagueDetail();
    void saveToFile() throws IOException;

    void loadFile();
    void addRandomMatch();



}

