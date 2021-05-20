package controllers;

import com.google.gson.Gson;
import play.mvc.*;
import java.util.ArrayList;


public class backendController extends Controller {
    //we need a premier league object for call inside premier league manager methods
    PremierLeagueManager premierManager = new PremierLeagueManager();


    public Result sendPremierLeagueMethods() {
        //re-load premier league methods for backend
        premierManager.loadLeagueDetail();
        //get club data for created new array list
        ArrayList<FootballClub> leagueDetailList = (ArrayList<FootballClub>) premierManager.clubs;
        //create gson file
        Gson gson1 = new Gson();
        try {
            //get converted list data assign to string
            String convertedData = gson1.toJson(leagueDetailList);
            System.out.println("club list sent to front-end successfully");
            return ok(convertedData);
        } catch (Exception e) {
            System.out.println("could not load data!");
        }
        //return it
        return ok("");
    }

    public Result sendMatchData(){
        //re-load premier league methods for backend
        premierManager.loadLeagueDetail();
        //get club data for created new array list
        ArrayList<Match> matchDetailList = (ArrayList<Match>) premierManager.premierClubs;
        //create gson file
        Gson gson2 = new Gson();
        try {
            //get converted list data assign to string
            String convertedData = gson2.toJson(matchDetailList);
            System.out.println("match list sent to front end successfully");
            return ok(convertedData);
        } catch (Exception e) {
            System.out.println("could not load data!");
        }
        //return it
        return ok("");
    }

    public Result randomMatch(){

        premierManager.loadLeagueDetail();
        Gson gson3 = new Gson();
        String jsonConvert= gson3.toJson(premierManager.randomAddMatch());
        System.out.println("correctly run");
        premierManager.saveLeagueDetail();
        return ok(jsonConvert);

    }

    public Result sortByGoals() {
        //re-load premier league methods for backend
        premierManager.loadLeagueDetail();
        premierManager.sortArrayListAccordingToGoals();
        //get club data for created new array list
        ArrayList<FootballClub> leagueDetailList1 = (ArrayList<FootballClub>) premierManager.clubs;
        //create gson file
        Gson gson1 = new Gson();
        try {
            //get converted list data assign to string
            String convertedData = gson1.toJson(leagueDetailList1);
            return ok(convertedData);
        } catch (Exception e) {
            System.out.println("could not load data!");
        }
        //return it
        return ok("");
    }

    public Result sortByWins() {
        //re-load premier league methods for backend
        premierManager.loadLeagueDetail();
        premierManager.sortArrayListAccordingToWins();
        //get club data for created new array list
        ArrayList<FootballClub> leagueDetailList = (ArrayList<FootballClub>) premierManager.clubs;
        //create gson file
        Gson gson1 = new Gson();
        try {
            //get converted list data assign to string
            String convertedData = gson1.toJson(leagueDetailList);
            return ok(convertedData);
        } catch (Exception e) {
            System.out.println("could not load data!");
        }
        //return it
        return ok("");
    }



}



