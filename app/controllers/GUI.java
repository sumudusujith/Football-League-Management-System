package controllers;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;


public class GUI extends Application {

    public static PremierLeagueManager manager1=new PremierLeagueManager();
    Stage mainStage;
    static TableView <FootballClub> tableLeague;
    static TableView <Match>tableMatches;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        mainStage = primaryStage;
        primaryStage.setTitle("Premier League Manager");


        //-----------------------------------------------------------------------------------------------------------//
        AnchorPane aPane1 = new AnchorPane();
        aPane1.setPrefSize(800, 500);

        HBox hBox1 = new HBox();

        aPane1.getChildren().addAll(hBox1);

        AnchorPane aPane2 = new AnchorPane();
        aPane2.setPrefSize(200, 500);
        aPane2.setStyle("-fx-background-color: white");

        AnchorPane aPane3 = new AnchorPane();
        aPane3.setPrefSize(600, 500);

        hBox1.getChildren().addAll(aPane2,aPane3);

        //-----------------------------------------------------------------------------------------------------------//
        VBox vBox1 = new VBox();
        vBox1.setSpacing(30);
        vBox1.setLayoutX(25);
        vBox1.setLayoutY(41);

        ImageView image1 = addImage("pictures/pic2.jpg", 150, 200, 0, 0, "imgMainBack");

        Button btnDisplayLeagueClub = createButton("Display League Club",150,40,0,0,"btn1");
        Button btnAddRandomMatch = createButton("Add Random Match",150,40,10,10,"btn1");
        Button btnDisplayMatchTable = createButton("Display Match Table",150,40,10,36,"btn1");

        vBox1.getChildren().addAll(image1,btnDisplayLeagueClub,btnAddRandomMatch,btnDisplayMatchTable);
        aPane2.getChildren().add(vBox1);

        //-----------------------------------------------------------------------------------------------------------//
        Pane pane1 = new Pane();
        pane1.setId("p1");
        pane1.setPrefSize(600,500);

        Pane pane2 = new Pane();
        pane2.setId("p2");
        pane2.setPrefSize(600,500);

        Pane pane3 = new Pane();
        pane3.setId("p3");
        pane3.setPrefSize(600,500);

        aPane3.getChildren().addAll(
                pane2,//display match played
                pane3,//add random match
                pane1);//display league table

        //-----------------------------------------------------------------------------------------------------------//
        //
        Label leagueTable= createLabel("League table",250,15,"lable1");

        tableLeague=new TableView();
        tableLeague.setLayoutX(15);
        tableLeague.setLayoutY(72);
        tableLeague.setPrefSize(568,361);
        tableLeague.setId("tab1");

        TableColumn<FootballClub,String> colClubName= new  TableColumn("Club name");
        colClubName.setPrefWidth(120);
        colClubName.setResizable(false);
        colClubName.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<FootballClub,String> colLocation= new TableColumn("location");
        colLocation.setPrefWidth(95);
        colLocation.setResizable(false);
        colLocation.setCellValueFactory(new PropertyValueFactory<>("location"));

        TableColumn<FootballClub,Integer> colPM= new TableColumn("MP");
        colPM.setPrefWidth(72);
        colPM.setResizable(false);
        colPM.setCellValueFactory(new PropertyValueFactory<>("playedMatches"));

        TableColumn<FootballClub,Integer> colWin= new TableColumn("Win");
        colWin.setPrefWidth(72);
        colWin.setResizable(false);
        colWin.setCellValueFactory(new PropertyValueFactory<>("wins"));

        TableColumn<FootballClub,Integer> colDraws= new TableColumn("Draw");
        colDraws.setPrefWidth(68);
        colDraws.setResizable(false);
        colDraws.setCellValueFactory(new PropertyValueFactory<>("draws"));

        TableColumn<FootballClub,Integer> colLoss= new  TableColumn("Loss");
        colLoss.setPrefWidth(67);
        colLoss.setResizable(false);
        colLoss.setCellValueFactory(new PropertyValueFactory<>("loss"));

        TableColumn<FootballClub,Integer> colPts= new  TableColumn("Pts");
        colPts.setPrefWidth(72);
        colPts.setResizable(false);
        colPts.setCellValueFactory(new PropertyValueFactory<>("points"));

        tableLeague.getColumns().addAll(
                colClubName,
                colLocation,
                colPM,
                colWin,
                colDraws,
                colLoss,
                colPts
        );

        ArrayList<FootballClub> footballClubArrayList=(ArrayList<FootballClub>) manager1.clubs;
//      System.out.println(footballClubArrayList);
        for (FootballClub obj : footballClubArrayList){
            tableLeague.getItems().add(obj);
        }

        //-----------------------------------------------------------------------------------------------------------//
        HBox hBox2 = new HBox();
        hBox2.setLayoutX(298);
        hBox2.setLayoutY(446);
        hBox2.setPrefSize(283,40);
        hBox2.setSpacing(5);

        Label sort = createLabel("Sort By",0,0,"lable2");
        sort.setPrefSize(50,40);
        Button statics = createButton("statics",70,34,0,0,"btn2");
        Button wins = createButton("wins",70,34,10,10,"btn2");
        Button goals = createButton("goals",70,34,10,36,"btn2");

        hBox2.getChildren().addAll(sort,statics,wins,goals);
        pane1.getChildren().addAll(leagueTable,hBox2,tableLeague);

        //-----------------------------------------------------------------------------------------------------------//
        Label tableOfPlayedMatches = createLabel("Table of played matches",201,14,"lable1");

        HBox hBox3= new HBox();
        hBox3.setLayoutX(150);
        hBox3.setLayoutY(440);
        hBox3.setSpacing(5);

        tableMatches =new TableView();
        tableMatches.setLayoutX(50);
        tableMatches.setLayoutY(100);
        tableMatches.setPrefSize(500,300);
        tableMatches.setId("tab2");

        TableColumn<Match,String> clubNameCol= new  TableColumn("Home club name");
        clubNameCol.setPrefWidth(115);
        clubNameCol.setResizable(false);
        clubNameCol.setCellValueFactory(new PropertyValueFactory<>("homeTeam"));

        TableColumn<Match,String> awyClubNameCol= new TableColumn("Away club name");
        awyClubNameCol.setPrefWidth(100);
        awyClubNameCol.setResizable(false);
        awyClubNameCol.setCellValueFactory(new PropertyValueFactory<>("awayTeam"));

        TableColumn<Match,Integer> hGoalsCol= new TableColumn("Home goals");
        hGoalsCol.setPrefWidth(85);
        hGoalsCol.setResizable(false);
        hGoalsCol.setCellValueFactory(new PropertyValueFactory<>("homeTeamGoals"));

        TableColumn<Match,Integer> aGoalsCol= new TableColumn("Away goals");
        aGoalsCol.setPrefWidth(87);
        aGoalsCol.setResizable(false);
        aGoalsCol.setCellValueFactory(new PropertyValueFactory<>("awayTeamGoals"));

        TableColumn<Match,String> dateCol= new TableColumn("Date");
        dateCol.setPrefWidth(107);
        dateCol.setResizable(false);
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));

        tableMatches.getColumns().addAll(
                clubNameCol,
                awyClubNameCol,
                hGoalsCol,
                aGoalsCol,
                dateCol
        );

        ObservableList<Match> matches= FXCollections.observableArrayList(manager1.premierClubs);
        tableMatches.setItems(matches);

        TextField date = createTextField("dd",0,0,70,30,"txt1");
        TextField month= createTextField("mm",10,10,70,30,"txt1");
        TextField year = createTextField("yyyy",98,10,70,30,"txt1");

        Button search= createButton("Search",100,30,0,0,"btn3");
        Button clear= createButton("Clear",100,30,187,10,"btn3");

        hBox3.getChildren().addAll(date,month,year,search,clear);

        pane2.getChildren().addAll(tableOfPlayedMatches,hBox3,tableMatches);

        //-----------------------------------------------------------------------------------------------------------//
        btnDisplayLeagueClub.setOnAction(e->{
            pane1.toFront();
            btnDisplayLeagueClub.setId("btn1_selected");
            btnAddRandomMatch.setId("btn1");
            btnDisplayMatchTable.setId("btn1");
        });

        btnDisplayMatchTable.setOnAction(e->{
            pane2.toFront();
            btnDisplayMatchTable.setId("btn1_selected");
            btnAddRandomMatch.setId("btn1");
            btnDisplayLeagueClub.setId("btn1");
        });

        btnAddRandomMatch.setOnAction(e->{
            pane3.toFront();
            btnAddRandomMatch.setId("btn1_selected");
            btnDisplayMatchTable.setId("btn1");
            btnDisplayLeagueClub.setId("btn1");
            manager1.randomAddMatch();
        });

        search.setOnAction(e->{
            search.setId("btn3_selected");
            clear.setId("btn3");


        });

        clear.setOnAction(e->{
            clear.setId("btn3_selected");
            search.setId("btn3");
        });

        statics.setOnAction(e->{
            //clear the table when im not do that it become double
            tableLeague.getItems().clear();
            //sort array list again according to statics
            manager1.sortArrayList();
            //add that new structured list to table
            for (FootballClub obj : footballClubArrayList){
                tableLeague.getItems().add(obj);
            }
            statics.setId("btn2_selected");
            goals.setId("btn2");
            wins.setId("btn2");
        });
        wins.setOnAction(e->{
            tableLeague.getItems().clear();
            manager1.sortArrayListAccordingToWins();
            for (FootballClub obj : footballClubArrayList){
                tableLeague.getItems().add(obj);
            }
            wins.setId("btn2_selected");
            goals.setId("btn2");
            statics.setId("btn2");
        });
        goals.setOnAction(e->{
            tableLeague.getItems().clear();
            manager1.sortArrayListAccordingToGoals();
            for (FootballClub obj : footballClubArrayList){
                tableLeague.getItems().add(obj);
            }
            goals.setId("btn2_selected");
            wins.setId("btn2");
            statics.setId("btn2");
        });

        //-----------------------------------------------------------------------------------------------------------//
        Scene scene1 = new Scene(aPane1, 787, 487);
        scene1.getStylesheets().add("styles.css");
        mainStage.setResizable(false);
        mainStage.setScene(scene1);
        mainStage.show();
    }

    //-----------------------------------------------------------------------------------------------------------//
    public static ImageView addImage(String PATH, double width, double height, double x, double y,String id){
        Image image = new Image(PATH);
        ImageView imageView = new ImageView(image);
        imageView.setLayoutX(x);
        imageView.setLayoutY(y);
        imageView.setFitHeight(height);
        imageView.setFitWidth(width);
        imageView.setId(id);
        return imageView;
    }

    public static Button createButton(String promptText, double x, double y, double lx, double ly, String id){
        Button btn=new Button(promptText);
        btn.setPrefSize(x,y);
        btn.setLayoutX(lx);
        btn.setLayoutY(ly);
        btn.setId(id);
        return btn;
    }

    public Label createLabel(String promptText, double x, double y,String id){
        Label lbls = new Label(promptText);
        lbls.setLayoutX(x);
        lbls.setLayoutY(y);
        lbls.setId(id);
        return lbls;
    }

    public TextField createTextField(String prompt, double x, double y, double scaleX, double scaleY, String id){
        TextField txtF=new TextField();
        txtF.setPromptText(prompt);
        txtF.setLayoutX(x);
        txtF.setLayoutY(y);
        txtF.setPrefWidth(scaleX);
        txtF.setPrefHeight(scaleY);
        txtF.setId(id);
        return txtF;
    }
    //-----------------------------------------------------------------------------------------------------------//
}



