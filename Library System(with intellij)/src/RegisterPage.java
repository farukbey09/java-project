import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.Scanner;

public class RegisterPage {
    static boolean answer;

    public static void getRegisterPage(Stage close) {
        Stage registerStage = new Stage();
        HBox boxBot = new HBox(25);
        HBox boxTop = new HBox(95);

        //BorderPane Operations
        GridPane layout = new GridPane();
        BorderPane pane = new BorderPane();
        pane.setCenter(layout);
        pane.setBottom(boxBot);
        pane.setTop(boxTop);


        //Boxes For Top
        HBox boxImage = new HBox();
        HBox boxLabel = new HBox(25);
        HBox boxReturn = new HBox();


        //boxLabel
        Label lb3 = new Label("LIBRARY");
        Label lb4 = new Label("SYSTEM");
        boxLabel.getChildren().addAll(lb3, lb4);



        //Image adding
        Image image1 = new Image("unnamed.png");
        ImageView image = new ImageView(image1);
        image.setFitWidth(80);
        image.setFitHeight(70);
        boxImage.getChildren().add(image);


        //BoxReturn
        Button returnButton = new Button("Return Back");
        boxReturn.getChildren().add(returnButton);
        boxBot.getChildren().add(boxReturn);
        returnButton.setOnAction(e -> {
            LoginPage lgpage = new LoginPage();
            lgpage.getPage(registerStage);
        });


        //Username block
        TextField usernameinp = new TextField();
        usernameinp.setPromptText("Username");

        //Name Block
        TextField nameinp = new TextField();
        nameinp.setPromptText("Name");

        //Passwords block
        PasswordField passinp = new PasswordField();
        passinp.setPromptText("Password");
        PasswordField passinp1= new PasswordField();
        passinp1.setPromptText("Re-Password");
        //question block
        ChoiceBox<String> choicequestion = new ChoiceBox<>();
        choicequestion.getItems().addAll("What's your favourite book ?", "Define You with one word", "What's your animal's name?","What's your favourite movie?");
        choicequestion.getSelectionModel().select(0);
        TextField questioninp = new TextField();
        questioninp.setPromptText("Answer of Questions");

        //Genders  Choice
        ChoiceBox<String> choicegender = new ChoiceBox<>();
        choicegender.getItems().addAll("Male", "Female");
        choicegender.getSelectionModel().select(0);


        //Register Button
        Button btregister = new Button(" Register ");
        boxBot.getChildren().addAll(btregister);
        btregister.setOnAction(e -> {


            try {


                if (!(usernameinp.getText().length() >= 6) && (passinp.getText().length() >= 6)) {

                    Alerts alert = new Alerts();
                    alert.getAlert("Your Username and Password Should be min 6 characters ");
                } else if (!(passinp.getText().equals(passinp1.getText()))) {
                    Alerts alert = new Alerts();
                    alert.getAlert("Your Password and RE-Password Should be same ");

                } else if (!(nameinp.getText().length() > 0 && questioninp.getText().length() > 0)) {

                    Alerts alert = new Alerts();
                    alert.getAlert("Your Name and Your Answer Should be at least 1 characters ");
                } else if (readFile("Admins.txt", usernameinp.getText().trim().toUpperCase())) {

                    Alerts alert = new Alerts();
                    alert.getAlert("This ID is already taken by another user");
                } else {
                    writeFile("Users.txt", usernameinp, passinp, registerStage, nameinp, choicegender.getValue(), choicequestion, questioninp);
                }


            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        });


        //Exit Operations
        registerStage.setOnCloseRequest(e -> {
            e.consume();
            Confirm conf = new Confirm();
            boolean result = conf.Display("Exit");
            if (result == true) {
                registerStage.close();
            }

        });


        //Cancel Button
        Button btcan = new Button("  Cancel  ");
        boxBot.getChildren().addAll(btcan);
        btcan.setOnAction(e -> {
            Confirm conf = new Confirm();
            boolean result = conf.Display("Exit");
            if (result == true) {
                registerStage.close();
            }

        });

        //Paddings
        layout.setPadding(new Insets(80, 70, 25, 0));
        boxTop.setPadding(new Insets(20, 0, 0, 30));
        boxBot.setPadding(new Insets(0, 0, 95, 40));
        boxReturn.setPadding(new Insets(15, 0, 0, 30));
        boxTop.setAlignment(Pos.CENTER);
        boxImage.setAlignment(Pos.CENTER_LEFT);
        boxLabel.setAlignment(Pos.CENTER);
        layout.setAlignment(Pos.CENTER);
        boxBot.setAlignment(Pos.CENTER);

        //Labels
        Label lb1 = new Label("Username:");
        Label lb2 = new Label("Password:");
        Label lb5 = new Label("Re-Password:");
        Label lb6 = new Label("Gender:");
        Label lb7 = new Label("Name:");
        Label lb8 = new Label("Question:");
        Label lb9 = new Label("Answer:");


        layout.add(lb1, 0, 0);
        layout.add(lb2, 0, 2);
        layout.add(lb7, 0, 1);
        layout.add(nameinp, 1, 1);
        layout.add(usernameinp, 1, 0);
        layout.add(passinp, 1, 2);
        layout.add(lb5, 0, 3);
        layout.add(passinp1, 1, 3);
        layout.add(lb8, 0, 4);
        layout.add(choicequestion, 1, 4);
        layout.add(lb9, 0, 5);
        layout.add(questioninp, 1, 5);
        layout.add(lb6, 0, 6);
        layout.add(choicegender, 1, 6);



        layout.setHgap(15);
        layout.setVgap(15);
        boxTop.getChildren().addAll(boxImage, boxLabel, boxReturn);


        //Css
        lb1.setStyle("-fx-text-fill:#E0FFFF;-fx-font-size:16");
        lb2.setStyle("-fx-text-fill:#E0FFFF;-fx-font-size:16");
        lb3.setStyle("-fx-text-fill:#F5F5DC;-fx-font-size:20");
        lb4.setStyle("-fx-text-fill:F5F5DC;-fx-font-size:20");
        lb5.setStyle("-fx-text-fill:#E0FFFF;-fx-font-size:16");
        lb6.setStyle("-fx-text-fill:#E0FFFF;-fx-font-size:16");
        lb7.setStyle("-fx-text-fill:#E0FFFF;-fx-font-size:16");
        lb8.setStyle("-fx-text-fill:#E0FFFF;-fx-font-size:16");
        lb9.setStyle("-fx-text-fill:#E0FFFF;-fx-font-size:16");
        pane.setStyle("-fx-background-color:#2D3447");
        btregister.setStyle("-fx-background-color:#2196f3; -fx-text-fill:#E0FFFF;-fx-font-veight:bold;-fx-background-radius:22;");
        btcan.setStyle("-fx-background-color:#2196f3; -fx-text-fill:#E0FFFF;-fx-font-veight:bold;-fx-background-radius:22;");
        returnButton.setStyle("-fx-background-color:#2D3447; -fx-border-color:#a0a2ab;-fx-text-fill:#E0FFFF;");


        Scene scene = new Scene(pane, 600, 520);
        close.close();
        registerStage.getIcons().add(image1);
        registerStage.setScene(scene);
        registerStage.setResizable(false);
        registerStage.show();
    }
    public static void writeFile(String str, TextField id, PasswordField pass, Stage register, TextField name, String gender, ChoiceBox<String> Question, TextField answer) {
        File file = new File(str);
        try {


            if (readFile("Users.txt", id.getText().toUpperCase().trim())) {
                Alerts alert = new Alerts();
                alert.getAlert("This ID is already taken by another user");
            } else {

                File fileInfo = new File("UsersInfo.txt");
                File fileQuestion = new File("Question.txt");

                BufferedWriter Users = new BufferedWriter(new FileWriter(file, true));
                BufferedWriter UsersInfo = new BufferedWriter(new FileWriter(fileInfo, true));
                BufferedWriter UsersQuestion = new BufferedWriter(new FileWriter(fileQuestion, true));

                if (fileQuestion.length() != 0)
                    UsersQuestion.newLine();
                UsersQuestion.write(Question.getValue());
                UsersQuestion.newLine();
                UsersQuestion.write(id.getText());
                UsersQuestion.newLine();
                UsersQuestion.write(answer.getText());
                UsersQuestion.newLine();
                UsersQuestion.write(pass.getText());

                if(fileInfo.length()!=0)
                UsersInfo.newLine();
                UsersInfo.write(id.getText().trim());
                UsersInfo.newLine();
                UsersInfo.write(pass.getText().trim());
                UsersInfo.newLine();
                UsersInfo.write(name.getText().trim());
                UsersInfo.newLine();
                UsersInfo.write(gender.trim());

                if(file.length()!=0)
                Users.newLine();
                Users.write(id.getText().trim());
                Users.newLine();
                Users.write(pass.getText().trim());



                Alerts alert = new Alerts();
                alert.getAlert("Register is Successfully . Please Go to the Login Screen", register, new LoginPage());
                Users.close();
                UsersInfo.close();
                UsersQuestion.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static boolean readFile(String url, String id) throws FileNotFoundException {

        File file = new File(url);


        Scanner reader = new Scanner(file);
        answer = false;
        while (reader.hasNextLine()) {
           String Username=reader.nextLine();
           String pass=reader.nextLine();

            if (Username.toUpperCase().equals(id))
                answer = true;
        }

        return answer;
    }

}