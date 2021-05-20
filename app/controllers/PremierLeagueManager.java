package controllers;

import java.io.*;
import java.util.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class PremierLeagueManager implements LeagueManager {

    public List<FootballClub> clubs= new ArrayList<>();
    public List<Match> premierClubs = new ArrayList<>();

    //constant variable
    public final static int maximumClubs=20;
    private int freeSlots=maximumClubs;

    @Override
    public void addLeagueClub(FootballClub footballClub) {
        //checking input club already in array
        for (FootballClub obj: clubs){
            if (obj.equals(footballClub)){
                System.out.println("\nfootball club is already added in to system! please try out another name");
                return;
            }
        }
        //checking free slots are available
        if (freeSlots<=0){
            System.out.println("\ncan not add your club no spaces available in premier league manager!");
            return;
        }
        clubs.add(footballClub);
        freeSlots-=1;
        System.out.println("\nyour club "+footballClub.getName() + " is added to premier league manager\n");
        System.out.println(clubs);

    }

    //delete club method
    @Override
    public void deleteLeagueClub(String clubName) {
        boolean found=false;
        for (FootballClub obj: clubs){
            if (obj.getName().equals(clubName)){
                found=true;
                clubs.remove(obj);
                freeSlots+=1;
                System.out.println("\nyour club "+ clubName +" is deleted in premier league manager\n");
                break;
            }
        }if (!found){
            System.out.println("\ninvalid name to delete club!");

        }
        System.out.println(clubs);
    }

    //display club static method
    @Override
    public void displayStatic(String clubName) {
        boolean found=false;
        for (FootballClub obj: clubs){
            if (obj.getName().equals(clubName)){
                found=true;
                System.out.println(obj.toString());
                break;
            }
        }if (!found){
            if (clubs.isEmpty())
                System.out.println("\nNo clubs are found in premier league manager     !\nfirst add clubs to display specified club statics!");
            else
                System.out.println("\ninvalid name to display club statics!");
        }

    }

    //display league table method
    @Override
    public void displayLeagueTable() {
        sortArrayList();

        String tableRow= "| %-20s | %3d | %3d | %3d | %4d | %5d | %4d | %2d | %2d |%n";
        System.out.format("\n|======================|=====|=====|=====|======|=======|======|====|====|%n");
        System.out.format("| Club                 | PTS |  GD |  MP | Wins | Draws | Loss | GF | GA |%n");
        System.out.format("|======================|=====|=====|=====|======|=======|======|====|====|%n");
        for (FootballClub obj: clubs){
            System.out.format(tableRow,
                    obj.getName(),
                    obj.getPoints(),
                    obj.getgF()- obj.getgA(),
                    obj.getPlayedMatches(),
                    obj.getWins(),
                    obj.getDraws(),
                    obj.getLoss(),
                    obj.getgF(),
                    obj.getgA());
            System.out.format("|----------------------|-----|-----|-----|------|-------|------|----|----|%n");
        }


    }

    //add played matches method
    @Override
    public void addPlayedMatch(Date date,String clbName,int homeGoals,String awyName,int awyGoals,String randomOrManual) {
        final int winPoints = 3;
        final int drawPoints = 1;


        //Check Club names are gone to equal
        if(clbName.equals(awyName)){
            System.out.println("\ncannot create a match try to different club names!");
            return;
        }


        String club1_status;
        String club2_status;

        FootballClub home=null;
        FootballClub away=null;

        for(FootballClub obj2: clubs){
            if(obj2.getName().equals(clbName)){
                home=obj2;
                break;
            }
        }

        for(FootballClub obj2 : clubs){
            if(obj2.getName().equals(awyName)){
                away=obj2;
                break;
            }
        }

        if((home==null) && (away==null)){
            System.out.println("\nBoth club names are Invalid!");
        }else if((home==null) || (away==null)){
            System.out.printf("\nInvalid name!. No club founded name hold '%s'.%n", home==null?clbName:awyName);
        }else {
            if(homeGoals > awyGoals){
                home.setPoints(home.getPoints()+winPoints);
                home.setWins(home.getWins()+1);
                away.setLoss(away.getLoss()+1);

                club1_status = "WIN";
                club2_status = "LOSE";

            }else if(homeGoals==awyGoals){
                home.setDraws(home.getDraws()+drawPoints);
                away.setDraws(away.getDraws()+drawPoints);

                club1_status = "DRAW";
                club2_status = "DRAW";

            }else {
                away.setPoints(away.getPoints()+winPoints);
                away.setWins(away.getWins()+1);
                home.setLoss(home.getLoss()+1);

                club1_status = "LOSE";
                club2_status = "WIN";
            }

            home.setPlayedMatches(home.getPlayedMatches()+1);
            away.setPlayedMatches(away.getPlayedMatches()+1);
            home.setgF(home.getgF()+homeGoals);
            away.setgF(away.getgF()+awyGoals);
            home.setgA(home.getgA()+awyGoals);
            away.setgA(away.getgA()+homeGoals);

            if (randomOrManual.equals("manual")){
                //Print Match info
                System.out.printf("\n%n|======================|==========================================|");
                System.out.printf("%n|  Date in %10s  | %18s vs %-18s |", date , clbName +" Club" ,awyName +" Club");
                System.out.printf("%n|----------------------|------------------------------------------|");
                System.out.printf("%n|          Score       | %18d : %-18d  |",homeGoals,awyGoals);
                System.out.printf("%n|          Result      | %18s : %-18s  |",club1_status,club2_status);
                System.out.printf("%n|----------------------|------------------------------------------|\n");
            }
            Match match = new Match(clbName,awyName,homeGoals,awyGoals,date);
            premierClubs.add(match);

        }
    }

    //save club and match details to file
    @Override
    public void saveLeagueDetail() {
            try {
                //create a new text file for save club detail
                File file1=new File("Premier league club data.txt");
                //create file out put data set
                FileOutputStream fileOutputStream = new FileOutputStream(file1);
                //objects convert to file readable data type(object serialization)
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                //write serializable club details to saved file
                objectOutputStream.writeObject(clubs);
                objectOutputStream.close();
                fileOutputStream.close();

                File file2 =new File("Premier league match data.txt");
                FileOutputStream fileOutputStream1 = new FileOutputStream(file2);
                ObjectOutputStream objectOutputStream1= new ObjectOutputStream(fileOutputStream1);
                objectOutputStream1.writeObject(premierClubs);
                objectOutputStream1.close();
                fileOutputStream1.close();

                System.out.println("Club details successfully saved!");
            } catch (FileNotFoundException e) {
                System.out.println("File not Found!");
            } catch (IOException e) {
                System.out.println("Error Initializing Stream!");
            }
        }

    //
    @Override
    public void loadLeagueDetail() {
        try {
            //get file data from specific file what are we saved before
            FileInputStream fileInputStream = new FileInputStream("Premier league club data.txt");
            //convert file data to readable data type for java (object deserializable)
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            //System.out.println("before loading:"+clubs);
            //read deserialized object and type casting (type convert) in our array list type. finally equal read arraylist object to our array list.
            clubs=(List<FootballClub>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
//            System.out.println(clubs);


            //get file data from specific file what are we saved before
            FileInputStream fileInputStream1 = new FileInputStream("Premier league match data.txt");
            //convert file data to readable data type for java (object deserializable)
            ObjectInputStream objectInputStream1 = new ObjectInputStream(fileInputStream1);
            //read deserialized object and type casting (type convert) in our array list type. finally equal read arraylist object to our array list.
            premierClubs=(List<Match>) objectInputStream1.readObject();
            objectInputStream1.close();
            fileInputStream1.close();
//            System.out.println(premierClubs);

        }
        catch(FileNotFoundException e){
            System.out.println("File not Found!");
        }
        catch (IOException e) {
            System.out.println("Error Initializing Stream");
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //sorting descending order method
    public void sortArrayList(){
        Comparator comparePoints = new Comparator <FootballClub>() {
            @Override
            public int compare(FootballClub club1, FootballClub club2) {
                int point1= club1.getPoints();
                int point2= club2.getPoints();
                if (point1==point2){
                    int club1GD = club1.getgF()-club1.getgA();
                    int club2GD = club2.getgF()-club2.getgA();
                    return (club2GD - club1GD);

                }
                return point2-point1;
            }
        };
        clubs.sort(comparePoints);
    }
    //sorting gui match table according to goals
    public void sortArrayListAccordingToGoals(){
        Comparator comparePoints1 = new Comparator <FootballClub>() {
            @Override
            public int compare(FootballClub club1, FootballClub club2) {
                return club2.getgF()-club1.getgF();
            }
        };
        clubs.sort(comparePoints1);
    }
    //sorting gui match table according to wins
    public void sortArrayListAccordingToWins(){
        Comparator comparePoints2 = new Comparator <FootballClub>() {
            @Override
            public int compare(FootballClub club1, FootballClub club2) {
                return club2.getWins()-club1.getWins();
            }
        };
        clubs.sort(comparePoints2);
    }
    //random match add method
    @Override
    public Match randomAddMatch(){
            Random RM = new Random();
            int HomeGoal, AwayGoal;

            HomeGoal = RM.nextInt(8);
            AwayGoal = RM.nextInt(8);

            int homeT, awayT;

//            do {
//                homeT = RM.nextInt(clubs.size());
//                awayT = RM.nextInt(clubs.size());
//
//            } while (homeT == awayT);

        while (true){
            homeT = RM.nextInt(clubs.size());
            awayT = RM.nextInt(clubs.size());

            if (!(homeT==awayT)){
                break;
            }
        }

            FootballClub Home__Club = clubs.get(homeT);
            FootballClub Away__Club = clubs.get(awayT);

            int Day=1, Month, Year;
            Month =(RM.nextInt(12)+1);
            if (Month==2) {
                Day = (RM.nextInt(29)+1);
            }
            else if(Month==1 || Month==3||Month==5||Month==7||Month==8||Month==10||Month==12) {
                Day = (RM.nextInt(31) + 1);
            }
            else if(Month==4 || Month==6||Month==9||Month==11){
                Day = (RM.nextInt(30) + 1);
            }
            Year = 2020;

            Date Date1 = new Date(Day, Month, Year);
            Match match1 = new Match(Home__Club.getName(),Away__Club.getName(),HomeGoal,AwayGoal,Date1);

            //when press addRandomMatch button add to played match array list

            addPlayedMatch(Date1,Home__Club.getName(),HomeGoal,Away__Club.getName(),AwayGoal,"");

            return match1;

        }


}




