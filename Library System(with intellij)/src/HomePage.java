import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.text.ParseException;


public class HomePage  {
    public  void getPage(Stage lastStage) {

        lastStage.close();
        Stage homepageStage = new Stage();
        homepageStage.setTitle("Home Page");
        BorderPane pane1 = new BorderPane();
        BorderPane pane2 = new BorderPane();
        BorderPane pane3 = new BorderPane();
        VBox leftBox = new VBox(65);
        HBox topBox = new HBox(150);
        Pane sample = new Pane();
        pane1.setLeft(leftBox);
        pane1.setRight(pane2);
        pane2.setTop(topBox);


        //Account Settings
        Image accimg1 = new Image("SampleUsers.png");
        ImageView accimg = new ImageView(accimg1);
        accimg.setFitWidth(27);
        accimg.setFitHeight(22);
        Button btaccount = new Button(new LoginPage().username, accimg);
        VBox boxforbt = new VBox(3);
        //Log out Button
        Image img1 =new Image("logout.png");
        ImageView imgview1 =new ImageView(img1);
        imgview1.setFitWidth(18);
        imgview1.setFitHeight(18);
        Button btLogout = new Button("-Log Out ", imgview1);
        btLogout.setOnAction(e -> {
            LoginPage lgback = new LoginPage();
            lgback.getPage(homepageStage);


        });
        //My List Button
        Image img2 =new Image("favourite.png");
        ImageView imgview2 =new ImageView(img2);
        imgview2.setFitWidth(20);
        imgview2.setFitHeight(21);
        Button btMylist = new Button("-MyList    ", imgview2);
        btMylist.setOnAction(e -> {
            MyListPage mylist = new MyListPage();
            try {

                mylist.getPage();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        // For Account settings
        btLogout.setVisible(false);
        btMylist.setVisible(false);
        boxforbt.getChildren().addAll(btaccount, btMylist, btLogout);
        btaccount.setOnAction(e -> {
            if (btLogout.isVisible() == false) {
                btLogout.setVisible(true);
                btMylist.setVisible(true);
            } else {
                btLogout.setVisible(false);
                btMylist.setVisible(false);
            }
        });

        //Library System Labels
        Label lb3 = new Label("           LIBRARY");
        Label lb4 = new Label("SYSTEM");
        HBox labelbox = new HBox(15);
        labelbox.getChildren().addAll(lb3, lb4);
        topBox.getChildren().addAll(labelbox, boxforbt);
        topBox.setAlignment(Pos.CENTER);


        Image image2 = new Image("progressbook.gif");
        ImageView image = new ImageView(image2);

        image.setFitWidth(700);
        image.setFitHeight(430);
        pane2.setBottom(sample);
        sample.getChildren().addAll(image);


        //Button Panels
        BooksPage bkpanel = new BooksPage();
        Button btbooks = new Button("", getImage("Booksicon.png"));//books
        btbooks.setOnAction(e -> {
            bkpanel.getPage();


        });
        //USERS
        UsersPage userpage = new UsersPage();
        Button btusers = new Button("", getImage("lastentries.png"));
        btusers.setOnAction(e -> {


                    if (new LoginPage().Admin == true) {
                            userpage.getUsersPage();
                    }
                    else{
                        Alerts alert=new Alerts();
                        alert.getAlert("You aren't Admin therefore you can't access this part");
                    }





                }
        );

        //TAKEN BOOKS
        TakenBooksPage takenbooks = new TakenBooksPage();
        Button bttakenbooks = new Button("", getImage("alinan.png"));
        bttakenbooks.setOnAction(e -> {
            if (new LoginPage().Admin == true) {
                try {
                    takenbooks.getTakenBooksPage();
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }
            }
            else{
                Alerts alert=new Alerts();
                alert.getAlert("You aren't Admin therefore you can't access this part");
            }

        });


        //LOGO
        Pane imgPane = new Pane();

        Image image1 = new Image("unnamed.png");
        ImageView image3 = new ImageView(image1);
        image3.setFitWidth(155);
        image3.setFitHeight(130);


        imgPane.getChildren().add(image3);
        leftBox.getChildren().add(imgPane);
        VBox btbox = new VBox(7);


        leftBox.getChildren().add(btbox);


        //labels
        Label label1 = new Label("        Books");
        label1.setAlignment(Pos.CENTER);
        Label label2 = new Label("    Taken Books");
        label2.setAlignment(Pos.CENTER);
        Label label3 = new Label("         Users");
        label3.setAlignment(Pos.CENTER);
        btbox.getChildren().addAll(btbooks, label1, bttakenbooks, label2, btusers, label3);

        //Padding
        topBox.setPadding(new Insets(0, 0, 0, 40));
        boxforbt.setPadding(new Insets(25, 30, 0, 35));
        imgPane.setPadding(new Insets(15, 0, 0, 0));
        leftBox.setPadding(new Insets(10, 15, 0, 40));
        labelbox.setPadding(new Insets(50, 0, 0, 100));
        sample.setPadding(new Insets(0, 130, 60, 0));
        btbox.setPadding(new Insets(0, 0, 50, 10));

        //Css

        leftBox.setStyle("-fx-background-color: #2D3447");
        pane1.setStyle("-fx-background-color: #2D3447");
        topBox.setStyle("-fx-background-color: #2D3447");
        lb3.setStyle("-fx-text-fill:#F5F5DC;-fx-font-size:27");
        lb4.setStyle("-fx-text-fill:#F5F5DC;-fx-font-size:27");
        label1.setStyle("-fx-text-fill:#F5F5DC;-fx-font-size:15");
        label2.setStyle("-fx-text-fill:#F5F5DC;-fx-font-size:15");
        label3.setStyle("-fx-text-fill:#F5F5DC;-fx-font-size:15");
        btaccount.setStyle("-fx-background-radius:50;-fx-background-color:#2196f3");
        btbooks.setStyle("-fx-background-radius:100");
        btLogout.setStyle("-fx-border-size:2px;-fx-border-color:black");
        btMylist.setStyle("-fx-border-size:2px;-fx-border-color:black");
        btusers.setStyle("-fx-background-radius:100");
        bttakenbooks.setStyle("-fx-background-radius:100");
        homepageStage.getIcons().add(image1);

        Scene scene = new Scene(pane1, 1000, 670);
        homepageStage.initStyle(StageStyle.UNDECORATED);
        homepageStage.setResizable(false);
        homepageStage.setScene(scene);
        homepageStage.show();


    }
    public  ImageView getImage(String url) {
        Image image = new Image(url);
        ImageView image1 = new ImageView(image);
        image1.setFitHeight(100);
        image1.setFitWidth(100);

        return image1;
    }

}
