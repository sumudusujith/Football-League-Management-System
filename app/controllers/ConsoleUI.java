package controllers;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleUI {
    //scanner for get every inputs
    public static Scanner getUserInputs = new Scanner(System.in);
    //object for parse inputs to premier league manager
    public static  PremierLeagueManager manager=new PremierLeagueManager();
    private static boolean javaFxLaunched=false;

    //main method
    public static void main(String[] args) {
        //to load saved detailed file every run time programme
        manager.loadLeagueDetail();
        while (true) {
            //execute menu
            menu();
            System.out.print("\nchoose your option      :");
            while (!getUserInputs.hasNextInt()) {//check the input (input or String)
                System.out.println("Integer Requested. Check provided menu list again!!");
                System.out.print("\nchoose your option      : ");
                getUserInputs.next();
            }
            int choice1=getUserInputs.nextInt();
             getUserInputs.nextLine();
                switch (choice1) {
                    case 1:
                        addFootballClubToPremierLeague();
                        break;

                    case 2:
                        deleteClubForPremierLeague();
                        break;

                    case 3:
                        displayClubStatics();
                        break;

                    case 4:
                        manager.displayLeagueTable();
                        break;

                    case 5:
                        GUI.manager1=manager;
                        runGUIApplication();
                        break;

                    case 6:
                        playedClubMatches();
                        break;

                    case 7:
                        manager.saveLeagueDetail();
                        break;
                    case 8:
                        try {
                            String command = "sbt run";
                            Runtime.getRuntime().exec("cmd /c start cmd.exe /K " + command);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;

                    case 9:
                        //Exit the programme
                        System.out.println("Thanks for using our program. Good bye..");
                        System.exit(0);
                    default:
                        System.out.println("invalid Input!");
                }
            }
        }
    //console menu
    private static void menu() {

        System.out.println("\n===========================================");
        System.out.println("----Welcome To football manager----\n");
        System.out.println("   press 1 to add a club team");
        System.out.println("   press 2 to delete added club");
        System.out.println("   press 3 to display added club static");
        System.out.println("   press 4 to display league table");
        System.out.println("   press 5 to open GUI");
        System.out.println("   press 6 to add played matches");
        System.out.println("   press 7 to save Played match");
        System.out.println("   press 8 to open app browser");
        System.out.println("   press 9 to Exit the program\n");

    }
    //to get inputs for add club
    public static void addFootballClubToPremierLeague(){

        System.out.print("\n===========================================\n");
        String name= strValidInputs("\nenter club name         :","");
        String location= strValidInputs("enter club location     :","location");
        int wins=0;
        int draws=0;
        int loss =0;
        int Gf=0;
        int Ga=0;

        endLessLoop:
        while (true){
            int clubType = intValidInputs("if your club has any specification \n" +
                    "enter 1 to school club/            \n" +
                    "enter 2 to university club/        \n" +
                    "enter 3 to non statics  :");
            switch (clubType) {
                case 1:
                    String sclName = strValidInputs("enter school name       :", "");
                    SchoolFootballClub sclClub = new SchoolFootballClub(name, location, sclName, wins, draws, loss, Gf, Ga);
                    manager.addLeagueClub(sclClub);
                    break endLessLoop;
                case 2:
                    String uniName = strValidInputs("enter university name   :", "");
                    UniversityFootballClub uniClub = new UniversityFootballClub(name, location, uniName, wins, draws, loss, Gf, Ga);
                    manager.addLeagueClub(uniClub);
                    break endLessLoop;
                case 3:
                    FootballClub club = new FootballClub(name, location, wins, draws, loss, Gf, Ga);
                    manager.addLeagueClub(club);
                    break endLessLoop;
                default:
                    System.out.println("\nplease check given input is in range(1-3)!\n");

            }
        }
    }
    //get a input for delete club static and club from premier league manager
    public static void deleteClubForPremierLeague(){
        System.out.print("\n===========================================\n\n");
        System.out.println("select a club in here to delete"+"\n-------------------------------------------");
        for (FootballClub obj: manager.clubs){
            System.out.println(obj.getName());
        }
        System.out.println("-------------------------------------------");
        String name= strValidInputs("enter club name here what you want to \n" +
                "delete in premier league manager    :","");
        manager.deleteLeagueClub(name);

    }
    //get a input for display club static
    public static void displayClubStatics(){
        System.out.print("\n===========================================\n");
        System.out.println("select a club in here to display statics"+"\n-------------------------------------------");
        for (FootballClub obj: manager.clubs){
            System.out.println(obj.getName());
        }
        System.out.println("-------------------------------------------");
        String name= strValidInputs("enter club name here what you \n" +
                "want to display statics     :","");
        manager.displayStatic(name);

    }
    //get inputs for add a match
    public static void playedClubMatches(){

        System.out.print("\n===========================================\n");
        System.out.println("here added club names"+"\n-------------------------------------------");
        for (FootballClub obj: manager.clubs){
            System.out.println(obj.getName());
        }
        System.out.println("-------------------------------------------");
        Date date1 =dateValidation();
        String clbName = strValidInputs("enter home club name      : ","");
        String awyName = strValidInputs("enter away club name      : ","");
        int homeGoals = getValidGoalsScored("Enter home club goals     : ",10);
        int awyGoals = getValidGoalsScored("enter away club goals     : ",10);
        manager.addPlayedMatch(date1,clbName,homeGoals,awyName,awyGoals,"manual");



    }
    public static Integer intValidInputs(String lable1) {
        while (true) {
            System.out.print(lable1);
            String unchecked1 = getUserInputs.nextLine();
            try {
                //try to convert user input to integer
                int checkInt = Integer.parseInt(unchecked1);
                //is user input checking positive number or not
                if (checkInt < 0) {
                    System.out.println("\nValid only positive numbers!\n");
                } else
                    return checkInt;
            }catch (Exception e) {
                System.out.println("\ninvalid Input integer required!\n");
            }
        }
    }
    //regular Exception in java
    public static String strValidInputs(String lable2,String datatype){
        //this is string pattern wants to get from user
        String regX = "^[A-a-zZ\\s]+$";
        if (datatype.equals("location")){
            //patter for get address type
            regX = "^[a-zA-Z0-9\\s,]+$";
        }
        //given string pattern compile to default format
        Pattern pattern =Pattern.compile(regX,Pattern.CASE_INSENSITIVE);
        while (true) {
            System.out.print(lable2);
            String unchecked2 = getUserInputs.nextLine();
            //given user input matching to customize pattern
            Matcher matcher =pattern.matcher(unchecked2);
            //if given input matched condition is true
            if (matcher.find()){
                return unchecked2;
            }else
                System.out.println("\nstring not proper type!\n");
        }
    }
    public static Integer getValidGoalsScored(String lable1,int maxGoals) {
        while (true) {
            System.out.print(lable1);
            String unchecked1 = getUserInputs.nextLine();
            try {
                //try to convert user input to integer
                int checkInt = Integer.parseInt(unchecked1);
                //is user input checking positive number or not
                if (checkInt < 0) {
                    System.out.println("\nValid only positive numbers!\n");
                } else if (checkInt>maxGoals){
                    System.out.println("can not scored this kind goals! input valid number of goals!");
                }
                else
                    return checkInt;
            }catch (Exception e) {
                System.out.println("\ninvalid Input integer required!\n");
            }
        }
    }

    private static void runGUIApplication(){

        //This method makes gui can be run multiple times

        if (!javaFxLaunched) { // First time
            Platform.setImplicitExit(false);
            new Thread(()-> Application.launch(GUI.class)).start();
            javaFxLaunched = true;
        } else { // Next times
            Platform.runLater(()->{
                    try {
                        Application application = GUI.class.newInstance();
                        Stage primaryStage = new Stage();
                        application.start(primaryStage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        }
    private static Date dateValidation() {
        while (true){
            boolean leap = false;
            int year = getValidNumberForDate("year: ",2100,2000,"year");
            int day = getValidNumberForDate("date: ",31,1,"date");
            int month = getValidNumberForDate("month: ",12,1,"month");
            //A leap year is exactly divisible by 4 except for century years (years ending with 00).
            // The century year is a leap year only if it is perfectly divisible by 400.
            if (year % 4 == 0) {
                // if the year is century
                if (year % 100 == 0) {
                    leap = year % 400 == 0;
                } else {
                    leap = true;
                }
            }
            if (leap && month == 2 && day < 30) {
                return new Date(day, month, year);
            } else if (!leap && month == 2 && day < 29) {
                return new Date(day, month, year);
            } else if ((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) && (day < 32)) {
                return new Date(day, month, year);
            } else if ((month == 4 || month == 6 || month == 9 || month == 11) && (day < 31)) {
                return new Date(day, month, year);
            } else
                System.out.println("not valid date!");
        }
    }

    public static Integer getValidNumberForDate(String lable3,int maxNumber,int minNumber,String dataType) {
        while (true) {
            System.out.print(lable3);
            String unchecked1 = getUserInputs.nextLine();
            try {
                //try to convert user input to integer
                int checkInt = Integer.parseInt(unchecked1);
                //is user input checking positive number or not
                if (checkInt>maxNumber || checkInt<minNumber){
                    System.out.println("Not a valid "+dataType+". please enter valid "+dataType+" number.");
                }
                else
                    return checkInt;
            }catch (Exception e) {
                System.out.println("\ninvalid Input integer required!\n");
            }
        }
    }
}


