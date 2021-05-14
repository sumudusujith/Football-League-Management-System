package controllers;

import java.io.*;
import java.util.*;

public class PremierLeagueManager implements LeagueManager,Serializable{ //ai?

    public static List<FootballClub> clubdata = new ArrayList<>();
    public List<Match> fcClubs = new ArrayList<>();
    private ObjectInputStream objectInputStream;

    public final static int maximumClubs = 20;//premierleague club total club capacity is 20.
    private int freeSlots = maximumClubs; //no of free slots availabale


    @Override
    public void addclub(FootballClub footballClub) {
        //checking  if element already exists in array
        for (FootballClub f1 : clubdata) {
            if (f1.equals(footballClub)) {
                System.out.println("football club is already exists in the fc league");
                return;
            }
        }

        if (freeSlots <= 0) {//checking  availble slot count
            System.out.println("No free slots available");
            return;
        }
        clubdata.add(footballClub);
        freeSlots--;
        System.out.println("Inputted " + footballClub.getclub_name() + " club is added to fc league!");
        System.out.println(clubdata);

    }
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------//

    @Override
    public void deleteclub(String club_name) {
        boolean found = false;// Java expression that returns a Boolean value: true or false
        for (FootballClub f1 : clubdata) {
            if (f1.getclub_name().equals(club_name)) {
                found = true;
                clubdata.remove(f1);
                freeSlots += 1;
                System.out.println("Inputted " + club_name + " club is deleted in fc league !");
                break;
            }
        }
        if (!found) {
            System.out.println("invalid club name!");

        }
        System.out.println(clubdata);


    }

//-----------------------------------------------------------------------------------------------------------------------------------------------------------------//

    @Override
    public void displaystatistics() {
        clubdata.sort(Comparator.comparing(FootballClub::getpoints));//order the objects of a user-defined class
        Collections.reverse(clubdata);
        System.out.println(clubdata);

        for (FootballClub footballClub : clubdata) {

            System.out.println("Name        :  " + footballClub.getclub_name());
            System.out.println("Location    :  " + footballClub.getlocation_club());
            System.out.println("No.matches  :  " + footballClub.getnomatches());
            System.out.println("Wins        :  " + footballClub.getwins());
            System.out.println("Draws       :  " + footballClub.getdraws());

            System.out.println("Loss        :  " + footballClub.getloss());
            System.out.println("Goals_Scored:  " + footballClub.getgoals_scored());
            System.out.println("Goals_Received:  " + footballClub.getgoals_recieved());
            System.out.println("Points      :  " + footballClub.getpoints());
            System.out.println();

        }
    }

//-----------------------------------------------------------------------------------------------------------------------------------------------------------------//


    @Override
    public void displaytable() {
        sortArrayList();

        String tableRow = "| %-20s                 | %3d    | %3d |    %3d     | %3d  | %3d  | %3d   |%n";
        System.out.format("-------------------------------------------------------------------------%n");
        System.out.format("| Club                                  | Points |  GD | No.Matches | Wins | Loss | Draws |%n");
        System.out.format("--------------------------------------------------------------------------%n");
        for (FootballClub cl1 : clubdata) {
            System.out.format(tableRow, cl1.getclub_name(), cl1.getpoints(), cl1.getgoals_scored() - cl1.getgoals_recieved(), cl1.getnomatches(), cl1.getwins(), cl1.getloss(), cl1.getdraws());

        }
        System.out.format("----------------------------------------------------------------------------");

    }

    @Override
    public void saveLeagueDetail() {

    }


    public void sortArrayList() { //sorting to decending order
        Comparator comparePoints = new Comparator<FootballClub>() {
            @Override
            public int compare(FootballClub cl1, FootballClub cl2) {
                int pts1 = cl1.getpoints();
                int pts2 = cl2.getpoints();
                if (pts1 == pts2) {
                    int club1GD = cl1.getgoals_scored() - cl1.getgoals_recieved();
                    int club2GD = cl2.getgoals_scored() - cl2.getgoals_recieved();
                    return club2GD - club1GD;
                }
                return pts2 - pts1;
            }
        };
        Collections.sort(clubdata, comparePoints);
    }
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------//


    @Override
    public void addPlayedfcMatch(int day,int month,int year, String homefcname, int homefcGoals, String visitorfcname, int visitorfcGoals, String stadium) {
        final int winPts = 3;//points for a win
        final int drawPts = 1;//points for a draw

        ////checking  if home and visitor  already exists in array
        if (homefcname.equals(visitorfcname)) {
            System.out.println("invalid club names!");
            return;
        }

        String fcclub1_result;
        String fcclub2_result;

        FootballClub home9 = null;
        FootballClub visitor9 = null;
        Date date1;
        //date1 = new sample11.Date(date);
        Date date = new Date(day,month,year);


        for (FootballClub fc2 : clubdata) {
            if (fc2.getclub_name().equals(homefcname)) {
                home9 = fc2;
                break;
            }
        }

        for (FootballClub fc2 : clubdata) {
            if (fc2.getclub_name().equals(visitorfcname)) {
                visitor9 = fc2;
                break;
            }
        }

        if ((home9 == null) && (visitor9 == null)) {
            System.out.println("Wrong inputted club names");
        } else if ((home9 == null) || (visitor9 == null)) {
            System.out.printf(" Club not founded_ club name is '%s'.%n", home9 == null ? homefcname : visitorfcname);
        } else {
            if (homefcGoals > visitorfcGoals) {
                home9.setpoints(home9.getpoints() + winPts);
                home9.setwins(home9.getwins() + 1);
                visitor9.setloss(visitor9.getloss() + 1);

                fcclub1_result = "WIN";
                fcclub2_result = "LOSE";

            } else if (homefcGoals == visitorfcGoals) {
                home9.setdraws(home9.getdraws() + drawPts);
                visitor9.setdraws(visitor9.getdraws() + drawPts);

                fcclub1_result = "DRAW";
                fcclub2_result = "DRAW";

            } else {
                visitor9.setpoints(visitor9.getpoints() + winPts);
                visitor9.setwins(visitor9.getwins() + 1);
                home9.setloss(home9.getloss() + 1);

                fcclub1_result = "LOSE";
                fcclub2_result = "WIN";
            }

            home9.setnomatches(home9.getnomatches() + 1);
            visitor9.setnomatches(visitor9.getnomatches() + 1);
            home9.setgoals_scored(home9.getgoals_scored());
            visitor9.setgoals_scored(visitor9.getgoals_scored() + visitorfcGoals);
            home9.setgoals_recieved(home9.getgoals_recieved() + visitorfcGoals);
            visitor9.setgoals_recieved(visitor9.getgoals_recieved() + homefcGoals);

            Match match = new Match(home9, visitor9, stadium, homefcGoals, visitorfcGoals, date);
            //(String homefcTeam, String awayfcTeam, String fcstadium, int homefcTeamGoals, int awayfcTeamGoals, sample11.Date date)
            fcClubs.add(match);
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------//
            //display updated match results

            System.out.printf("%n+----------------------+------------------------------------------+");
            System.out.printf("%n|  sample11.Date in %10s  | %18s vs %-18s |" + date + homefcname + " Club" + visitorfcname + " Club");
            System.out.printf("%n+----------------------+--------------------^---------------------+");
            System.out.printf("%n|          Score       | %18d : %-18d  |", homefcGoals, visitorfcGoals);
            System.out.printf("%n|          Result      | %18s : %-18s  |", fcclub1_result, fcclub2_result);
            System.out.printf("%n+----------------------+------------------------------------------+");
        }

    }

    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------//
    public void saveToFile() {
        try {
            FileOutputStream fileOutputStream9 = new FileOutputStream(new File("fcleaguedeatils.txt"));
            ObjectOutputStream objectOutputStream9 = new ObjectOutputStream(fileOutputStream9);
            objectOutputStream9.writeObject(clubdata);
            objectOutputStream9.close();
            fileOutputStream9.close();
        } catch (FileNotFoundException e) {
            System.out.println("no_file_found!");
        } catch (IOException e) {
            System.out.println("Error !");
        }
    }

    public void loadFile() {
        try {
            FileInputStream fileInputStream9 = new FileInputStream("fcleaguedeatils.txt");
            objectInputStream = new ObjectInputStream(fileInputStream9);
            clubdata = (List<FootballClub>) objectInputStream.readObject();
            objectInputStream.close();
            objectInputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not Found!");
        } catch (IOException e) {
            System.out.println("Error Initializing Stream!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
/*    public void sample11.AddRandomMatch(){
        List<sample11.SportsClub> matchTeams = getRandomElement(sample2019582.sample11.PremierLeagueManager.clubdata);

        String homeTeam = null;
        String awayTeam = null;

        for (int i = 0; i < sample2019582.sample11.PremierLeagueManager.clubdata.size() ; i++){
            for (int j = 1; j < i; j++){
                if (matchTeams.get(0) != matchTeams.get(1)){
                    homeTeam = matchTeams.get(0).getclub_name();
                    awayTeam = matchTeams.get(1).getclub_name();
                }
                else {
                    homeTeam = sample2019582.sample11.PremierLeagueManager.clubdata.get(i).getclub_name();
                    awayTeam = sample2019582.sample11.PremierLeagueManager.clubdata.get(j).getclub_name();
                    break;
                }
            }
        }
        System.out.println(homeTeam + " VS " + awayTeam);

        Random random = new Random();
        int homeGaols = random.nextInt(10);
        int awayGoals = random.nextInt(10);

        System.out.println(homeTeam + " : " + homeGaols );
        System.out.println(awayTeam + " : " + awayGoals );

        int day = random.nextInt(29) + 1;
        int month = random.nextInt(11) + 1;
        int year = 2020;

        sample11.Main.sample11.PremierLeagueManager.addNewMatch(matchTeams.get(0).getclub_name(), matchTeams.get(1).getlocation_club(), homeGaols, awayGoals, day, month, year);

    });*/

public static List<FootballClub> match1() {
return clubdata;
}

    @Override
    public void addRandomMatch() {
        Random random = new Random();

        String nameA = null;
        String nameB = null;

        int randNameA = random.nextInt(clubdata.size()); //array eken club ekk rndm arn ekt no ekk denw
        int randNameB = random.nextInt(clubdata.size());

        if(randNameA == randNameB){ //ekama num ek arn e deka wens wenkn while loop ek run wenw
            while (randNameA == randNameB){
                randNameA = random.nextInt(clubdata.size());
                randNameB = random.nextInt(clubdata.size());
            }
        }else if (randNameA != randNameB){ //wens dekk awa nm eke name ek gnnw
            nameA = clubdata.get(randNameA).getclub_name();
            nameB = clubdata.get(randNameB).getclub_name();
        }
//add match ek ptn gnnw  ,name ekt amtharawa anth details gnnw ,for ekn
        FootballClub clubOne = null; //ai?
        FootballClub clubTwo = null;

        for (FootballClub footballClub : clubdata) {
            if (nameA.equals(footballClub.getclub_name())) {
                clubOne = footballClub;
            }
            if (nameB.equals(footballClub.getclub_name())){
                clubTwo = footballClub;
            }
        }

        int homeGoals = random.nextInt(10); //array eke 1 idn 10 wenkn tyna club wlin randm club no ekk thorgnnw
        int visitorsGoals = random.nextInt(10);


        if (homeGoals > visitorsGoals) {
            clubOne.setwins(clubOne.getwins() + 1);
            clubTwo.setloss(clubOne.getloss() + 1);
            clubOne.setnomatches(clubOne.getnomatches() + 1);
            clubTwo.setnomatches(clubTwo.getnomatches() + 1);
        } else if (visitorsGoals > homeGoals) {
            clubTwo.setwins(clubTwo.getwins() + 1);
            clubOne.setloss(clubOne.getloss() + 1);
            clubOne.setnomatches(clubOne.getnomatches() + 1);
            clubTwo.setnomatches(clubTwo.getnomatches() + 1);
        } else {
            clubOne.setdraws(clubOne.getdraws() + 1);
            clubTwo.setdraws(clubTwo.getdraws() + 1);
            clubOne.setnomatches(clubOne.getnomatches() + 1);
            clubTwo.setnomatches(clubTwo.getnomatches() + 1);
        }
    }
}











