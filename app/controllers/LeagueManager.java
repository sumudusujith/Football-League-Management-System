package controllers;

import java.io.IOException;

interface LeagueManager {

     void addLeagueClub(FootballClub footballClub);
     void deleteLeagueClub(String clubName);
     void displayStatic(String clubName);
     void displayLeagueTable();
     void addPlayedMatch(Date date,String clbName,int homeGoals,String awyName,int awyGoals,String random);
     void saveLeagueDetail() throws IOException;
     void loadLeagueDetail();
     Match randomAddMatch();
}