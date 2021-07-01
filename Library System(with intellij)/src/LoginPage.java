import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.*;
import java.util.Scanner;

public class LoginPage {
    //Remember me Button
    static CheckBox rememberme =new CheckBox("Remember me");

    static boolean Admin =true;
    static  String username;
    public static void getPage() {
        Stage loginStage = new Stage();
        loginStage.setTitle("Log In");
        VBox boxBot=new VBox(40);
        //box1 boxBot
        HBox boxBot1 = new HBox(15);
        boxBot.getChildren().add(boxBot1);
        HBox boxForgot =new HBox();
        GridPane layout = new GridPane();

        BorderPane pane = new BorderPane();
        pane.setCenter(layout);
        pane.setBottom(boxBot);

        //setting top
        HBox boxTop = new HBox(95);
        pane.setTop(boxTop);

        //Boxes For Top
        HBox boxImage = new HBox();
        HBox boxLabels = new HBox(25);
        HBox boxRegister = new HBox();
        boxImage.setAlignment(Pos.CENTER_LEFT);

        //boxLabels for top
        Label lb3 = new Label("LIBRARY");
        Label lb4 = new Label("SYSTEM");
        boxLabels.getChildren().addAll(lb3, lb4);
        boxLabels.setAlignment(Pos.CENTER);


        //boxRegister Register button
        Button register = new Button("REGISTER");
        register.setOnAction(e -> {
            RegisterPage rgpage = new RegisterPage();
            rgpage.getRegisterPage(loginStage);
        });


        boxRegister.getChildren().add(register);
        boxRegister.setAlignment(Pos.TOP_RIGHT);



        //icon adding
        Image image = new Image("unnamed.png");
        ImageView image1 = new ImageView(image);
        image1.setFitWidth(95);
        image1.setFitHeight(70);
        boxImage.getChildren().add(image1);

        //Username block
        TextField nameinp = new TextField();
        nameinp.setPromptText("Username");

        //Password block
        PasswordField passinp = new PasswordField();
        passinp.setPromptText("Password");

        try {
            checkbox(nameinp,passinp);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Log in Button
        Button btlog = new Button(" Log In ");
        boxBot1.getChildren().addAll(rememberme, btlog);
        btlog.setOnAction(e -> {
            try {
                compareForLogin(nameinp, passinp, loginStage);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        //Forgot password Button
Button btforgot=new Button("Forgot Password");

btforgot.setOnAction(e->{
    ForgotPasswordPage forgotPasswordPage=new ForgotPasswordPage();
    forgotPasswordPage.getPage();
});
    boxForgot.getChildren().add(btforgot);
    boxBot.getChildren().add(boxForgot);




        //Paddings
        layout.setPadding(new Insets(60, 70, 25, 0));
        boxTop.setPadding(new Insets(20, 0, 0, 30));
        boxBot1.setPadding(new Insets(0, 0, 0, 25));
        boxRegister.setPadding(new Insets(15, 0, 0, 30));
        boxForgot.setPadding(new Insets(0,20,20,0));
        boxBot.setPadding(new Insets(0,0,120,0));
        boxBot1.setAlignment(Pos.CENTER);
        boxBot.setAlignment(Pos.CENTER);
        boxForgot.setAlignment(Pos.TOP_RIGHT);
        boxTop.setAlignment(Pos.CENTER);
        layout.setAlignment(Pos.CENTER);


        //Labels
        Label lb1 = new Label("Username:");
        Label lb2 = new Label("Password:");


        //GridPane adding Elemenets
        layout.add(lb1, 0, 0);
        layout.add(lb2, 0, 1);
        layout.add(nameinp, 1, 0);
        layout.add(passinp, 1, 1);
        layout.setHgap(15);
        layout.setVgap(15);
        boxTop.getChildren().addAll(boxImage, boxLabels, boxRegister);


        //Css
        lb1.setStyle("-fx-text-fill:#E0FFFF;-fx-font-size:16");
        lb2.setStyle("-fx-text-fill:#E0FFFF;-fx-font-size:16");
        lb3.setStyle("-fx-text-fill:#F5F5DC;-fx-font-size:20");
        lb4.setStyle("-fx-text-fill:#F5F5DC;-fx-font-size:20");
        pane.setStyle("-fx-background-color:#2D3447");
        btlog.setStyle("-fx-background-color:#2196f3; -fx-text-fill:#E0FFFF;-fx-font-veight:bold;-fx-background-radius:22;");
        register.setStyle("-fx-background-color:#2D3447; -fx-border-color:#a0a2ab;-fx-text-fill:#E0FFFF;");
        rememberme.setStyle("-fx-text-fill:#E0FFFF");
        btforgot.setStyle("-fx-background-color:#2196f3; -fx-text-fill:#E0FFFF;-fx-font-veight:bold;-fx-background-radius:22;");

        Scene scene1 = new Scene(pane, 600, 347);
        //Enter key
        scene1.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                try {
                    compareForLogin(nameinp, passinp, loginStage);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        loginStage.getIcons().add(image);
        loginStage.setScene(scene1);
        loginStage.setResizable(false);
        loginStage.show();

    }
    public static void getPage(Stage window) {
        Stage loginStage = new Stage();
        loginStage.setTitle("Log In");
        VBox boxBot=new VBox(40);
        //box1 boxBot
        HBox boxBot1 = new HBox(15);
        boxBot.getChildren().add(boxBot1);
        HBox boxForgot =new HBox();
        GridPane layout = new GridPane();

        BorderPane pane = new BorderPane();
        pane.setCenter(layout);
        pane.setBottom(boxBot);

        //setting top
        HBox boxTop = new HBox(95);
        pane.setTop(boxTop);

        //Boxes For Top
        HBox boxImage = new HBox();
        HBox boxLabels = new HBox(25);
        HBox boxRegister = new HBox();
        boxImage.setAlignment(Pos.CENTER_LEFT);

        //boxLabels for top
        Label lb3 = new Label("LIBRARY");
        Label lb4 = new Label("SYSTEM");
        boxLabels.getChildren().addAll(lb3, lb4);
        boxLabels.setAlignment(Pos.CENTER);


        //boxRegister Register button
        Button register = new Button("REGISTER");
        register.setOnAction(e -> {
            RegisterPage rgpage = new RegisterPage();
            rgpage.getRegisterPage(loginStage);
        });


        boxRegister.getChildren().add(register);
        boxRegister.setAlignment(Pos.TOP_RIGHT);



        //icon adding
        Image image = new Image("unnamed.png");
        ImageView image1= new ImageView(image);
        image1.setFitWidth(95);
        image1.setFitHeight(70);
        boxImage.getChildren().add(image1);

        //Username block
        TextField nameinp = new TextField();
        nameinp.setPromptText("Username");

        //Password block
        PasswordField passinp = new PasswordField();
        passinp.setPromptText("Password");

        try {
            checkbox(nameinp,passinp);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Log in Button
        Button btlog = new Button(" Log In ");
        boxBot1.getChildren().addAll(rememberme, btlog);
        btlog.setOnAction(e -> {
            try {
                compareForLogin(nameinp, passinp, loginStage);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        //Forgot password Button
        Button btforgot=new Button("Forgot Password");

        btforgot.setOnAction(e->{
            ForgotPasswordPage forgotPasswordPage=new ForgotPasswordPage();
            forgotPasswordPage.getPage();
        });
        boxForgot.getChildren().add(btforgot);
        boxBot.getChildren().add(boxForgot);




        //Paddings
        layout.setPadding(new Insets(60, 70, 25, 0));
        boxTop.setPadding(new Insets(20, 0, 0, 30));
        boxBot1.setPadding(new Insets(0, 0, 0, 25));
        boxRegister.setPadding(new Insets(15, 0, 0, 30));
        boxForgot.setPadding(new Insets(0,20,20,0));
        boxBot.setPadding(new Insets(0,0,120,0));
        boxBot1.setAlignment(Pos.CENTER);
        boxBot.setAlignment(Pos.CENTER);
        boxForgot.setAlignment(Pos.TOP_RIGHT);
        boxTop.setAlignment(Pos.CENTER);
        layout.setAlignment(Pos.CENTER);


        //Labels
        Label lb1 = new Label("Username:");
        Label lb2 = new Label("Password:");


        //GridPane adding Elemenets
        layout.add(lb1, 0, 0);
        layout.add(lb2, 0, 1);
        layout.add(nameinp, 1, 0);
        layout.add(passinp, 1, 1);
        layout.setHgap(15);
        layout.setVgap(15);
        boxTop.getChildren().addAll(boxImage, boxLabels, boxRegister);


        //Css
        lb1.setStyle("-fx-text-fill:#E0FFFF;-fx-font-size:16");
        lb2.setStyle("-fx-text-fill:#E0FFFF;-fx-font-size:16");
        lb3.setStyle("-fx-text-fill:#F5F5DC;-fx-font-size:20");
        lb4.setStyle("-fx-text-fill:F5F5DC;-fx-font-size:20");
        pane.setStyle("-fx-background-color:#2D3447");
        btlog.setStyle("-fx-background-color:#2196f3; -fx-text-fill:#E0FFFF;-fx-font-veight:bold;-fx-background-radius:22;");
        register.setStyle("-fx-background-color:#2D3447; -fx-border-color:#a0a2ab;-fx-text-fill:#E0FFFF;");
        rememberme.setStyle("-fx-text-fill:#E0FFFF");
        btforgot.setStyle("-fx-background-color:#2196f3; -fx-text-fill:#E0FFFF;-fx-font-veight:bold;-fx-background-radius:22;");

        Scene scene1 = new Scene(pane, 600, 347);
        //Enter key
        scene1.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                try {
                    compareForLogin(nameinp, passinp, loginStage);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        window.close();
        loginStage.getIcons().add(image);
        loginStage.setScene(scene1);
        loginStage.setResizable(false);
        loginStage.show();

    }
    public static void compareForLogin(TextField id, PasswordField pass, Stage loginStage) throws IOException {
        File file= new File("Users.txt");
        File fileadmins = new File("Admins.txt");
        Scanner reader = new Scanner(file);
        Scanner readeradmins = new Scanner(fileadmins);

        boolean flag = false;
        HomePage hpage = new HomePage();
        while (reader.hasNextLine()) {
        String name=reader.nextLine();
        String password=reader.nextLine();
            if ((id.getText().trim().toUpperCase().equals(name.trim().toUpperCase())) && pass.getText().equals(password)) {
                flag=true;
                Admin=false;
            }
        }
        if (flag ==false || id.getText().length() < 6 || pass.getText().length() < 6) {
           while(readeradmins.hasNextLine()) {
              String adname=readeradmins.nextLine();
              String  adpass=readeradmins.nextLine();
               if(id.getText().toUpperCase().equals(adname.toUpperCase())&&pass.getText().toUpperCase().equals(adpass.toUpperCase()))
                   flag=true;
               Admin=true;

           }

        }
        if (flag == true) {
            File checkfile =new File("ControlCheckBox.txt");
            BufferedWriter writer =new BufferedWriter(new FileWriter(checkfile));
            File lastuserfile =new File("LastUser.txt");
            BufferedWriter writerlastuser =new BufferedWriter(new FileWriter(lastuserfile));


                username= id.getText().trim();
            Alerts alert = new Alerts();
            alert.getAlert("Login is Successfully", loginStage, hpage);

            if(lastuserfile.length()!=0)
            writerlastuser.newLine();
            writerlastuser.write(id.getText().trim());
            writerlastuser.newLine();
            writerlastuser.write(pass.getText().trim());

            if(rememberme.isSelected()){
                writer.write("true");


            }
            else{

                writer.write("false");

            }




            writerlastuser.close();
            writer.close();




        }

        else{
            Alerts alert=new Alerts();
            alert.getAlert("Incorrect Entry");
        }
reader.close();
    }
    public static  void checkbox (TextField nameinp ,PasswordField passinp) throws  IOException{

        File checkfile =new File("ControlCheckBox.txt");
        File lastuserfile =new File("LastUser.txt");
      if(lastuserfile.length()!=0) {
          Scanner reader = new Scanner(checkfile);
          Scanner readeruser = new Scanner(lastuserfile);
          String username = readeruser.nextLine();
          String pass = readeruser.nextLine();
          if (reader.next().equals("true")) {
              nameinp.setText(username);
              passinp.setText(pass);
              rememberme.setSelected(true);
          }

          reader.close();
          readeruser.close();
      }
    }



}
