package controllers;

import java.io.IOException;
import java.util.Scanner;

public class Main{
    private final static PremierLeagueManager PLM9 = new PremierLeagueManager();
    public static Scanner inputManager9 = new Scanner(System.in);

    public static void main(String[] args ) throws IOException {
        PLM9.loadFile();


        do{



                System.out.println("\nWelcome to Premier League  console application !");
                System.out.println("\t 1. Add a new club");
                System.out.println("\t 2. Delete a club");
                System.out.println("\t 3. display statistics");
                System.out.println("\t 4. Print Table");
                System.out.println("\t 5. Add played matches");
                System.out.println("\t 6. Save Details");
                System.out.println("\t 7. Open GUI");
                System.out.println("\t 8. Exit");

                System.out.println("\nEnter option : ");
                int input = inputManager9.nextInt();

                switch (input) {

                    case 1:
                        addclub();
                        break;
                    case 2:
                        deleteclub();
                        break;
                    case 3:
                        PLM9.displaystatistics();

                        break;
                    case 4:
                        PLM9.displaytable();
                        break;
                    case 5:
                        addPlayedfcMatch();
                        break;
                    case 6:
                        PLM9.saveToFile();
                        break;
                    case 7:
                       // GUI();
                        break;
                    case 8:
                        System.exit(0);
                    default:
                        System.out.println("Please enter VALID input!!!");
                        break;


                }
            }while (true);

        }
    public static void addclub(){


        System.out.print("enter club name:");
        String club_name= inputManager9.next();
        System.out.print("enter club location:");
        String location_club= inputManager9.next();
        System.out.print("enter club played match:");
        int nomatches= inputManager9.nextInt();
        System.out.print("enter club wins:");
        int wins= inputManager9.nextInt();
        System.out.print("enter club drawss:");
        int draws = inputManager9.nextInt();
        System.out.print("enter club losses:");
        int loss= inputManager9.nextInt();
        System.out.print("enter goals scored:");
        int goals_scored= inputManager9.nextInt();
        System.out.print("enter goals recieved:");
        int goals_recieved= inputManager9.nextInt();
        System.out.print("enter club points:");
        int points= inputManager9.nextInt();
        System.out.println(" Select the club type \n " +
                "enter 1 to school club/\n " +
                "enter 2 to university club/\n" +
                " enter 3 to Professional fc club");
        int clubType = inputManager9.nextInt();
        switch (clubType){
            case 1:
                System.out.print("enter school name:");
                String schoolname=inputManager9.next();
                SchoolFootballClub sclClub=new SchoolFootballClub( club_name ,location_club ,schoolname, nomatches,  wins,  draws,  loss, goals_scored,  goals_recieved,  points);
                PLM9.addclub(sclClub);
                break;

            case 2:
                System.out.print("enter university name:");
                String universityname=inputManager9.next();
                UniversityFootballClub uniClub=new UniversityFootballClub(club_name ,location_club , universityname,nomatches,  wins,  draws,  loss, goals_scored,  goals_recieved,  points);
                PLM9.addclub(uniClub);
                break;
            case 3:
                FootballClub club =new FootballClub(club_name ,location_club , nomatches,  wins,  draws,  loss, goals_scored,  goals_recieved,  points);
                //add a professional club to premier league manager
                PLM9.addclub(club);
                break;
        }

    }
    public static void deleteclub(){
        System.out.print("enter club name  \n" +
                "to  delete it in fc league :");
        String clname= inputManager9.next();
        PLM9.deleteclub(clname);

    }


    public static void addPlayedfcMatch() {

        System.out.print("enter date below");
        System.out.print("enter day: ");
        int day = inputManager9.nextInt();
        System.out.print("enter month: ");
        int month = inputManager9.nextInt();
        System.out.print("enter year: ");
        int year = inputManager9.nextInt();




        System.out.print("enter home_club name: ");
        String homefcname = inputManager9.next();// change
        System.out.print("Enter home_club goals: ");
        int homefcGoals = inputManager9.nextInt();
        System.out.print("enter visitor club name: ");
        String visitorfcname = inputManager9.next();
        System.out.print("enter visitor club goals: ");
        int visitorfcGoals = inputManager9.nextInt();
        System.out.print("enter played fc stadium: ");
        String stadium = inputManager9.next();

        PLM9.addPlayedfcMatch(day,month,year,homefcname, homefcGoals, visitorfcname, visitorfcGoals,stadium);
       /* public static Integer intValidInputs(String lable1) {
            while (true) {
                System.out.print(lable1);
                String unchecked1 = getUserInputs.nextLine();
                try {
                    //try to convert user input to integer
                    int checkInt = Integer.parseInt(unchecked1);
                    //is user input checking positive number or not
                    if (checkInt < 0) {
                        System.out.println("\nValid only positive numbers!\n");
                    }
                    if (checkInt < 0) {
                        System.out.println("\nValid only positive numbers!\n");
                    } else
                        return checkInt;
                }catch (Exception e) {
                    System.out.println("\ninvalid Input integer required!\n");

                }
            }*/
        }



}



