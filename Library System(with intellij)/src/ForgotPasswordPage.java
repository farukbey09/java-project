
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ForgotPasswordPage implements Interface {



    public void getPage()  {
        Stage primaryStage=new Stage();
        GridPane layout = new GridPane();
        BorderPane pane = new BorderPane();
        pane.setCenter(layout);
        Button btuserok = new Button("OK");
        Button btansweok = new Button("OK");

        layout.setVgap(10);
        layout.setHgap(10);

        //Username block
        TextField nameinp = new TextField();
        nameinp.setPromptText("Username");
        //Password block
        TextField answer = new TextField();
        answer.setPromptText("Answer");



        //Labels
        Label lb1 = new Label("Username:");
        Label lb2 = new Label("Question:");
        Label lb3 = new Label();
        Label lb4 = new Label("Answer");
        Label lb5 = new Label();
        Label lb6 = new Label("WELCOME:) YOUR PASSWORD");

        //
        layout.add(lb1, 0, 0);
        layout.add(nameinp, 1, 0);
        layout.add(btuserok, 2, 0);
        layout.add(lb2, 0, 1);
        layout.add(lb3, 1, 1);
        layout.add(lb4, 0, 2);
        layout.add(answer, 1, 2);
        layout.add(lb5, 2, 4);
        layout.add(btansweok,2,2);
        layout.add(lb6,1,3);
        layout.setAlignment(Pos.CENTER);

        lb2.setVisible(false);
        lb3.setVisible(false);
        lb4.setVisible(false);
        lb5.setVisible(false);
        lb6.setVisible(false);
        answer.setVisible(false);
        btansweok.setVisible(false);

        btuserok.setOnAction(e ->{
            File file=new File("Question.txt");
            Scanner reader= null;
            boolean flag=false;
           String Soru=null;
            try {
                reader = new Scanner(file);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
            while(reader.hasNextLine()){
                String question=reader.nextLine();
                String id =reader.nextLine();
                String answer1=reader.nextLine();
                String pass=reader.nextLine();
                if(nameinp.getText().toUpperCase().equals(id.toUpperCase())){
                    flag=true;
                    Soru=question;
                }


            }


reader.close();

            if(flag) {
                lb3.setText(Soru);
                lb1.setVisible(false);
                nameinp.setVisible(false);
                btuserok.setVisible(false);
                lb2.setVisible(true);
                lb3.setVisible(true);
                lb4.setVisible(true);
                lb5.setVisible(false);
                lb6.setVisible(false);
                answer.setVisible(true);
                btansweok.setVisible(true);
            }
            else{
                Alerts wrong=new Alerts();
                wrong.getAlert("There is no Username that you entered");
            }
        } );


        btansweok.setOnAction(e ->{
            File file=new File("Question.txt");
            Scanner reader= null;
            boolean flag=false;
            String Password=null;
            try {
                reader = new Scanner(file);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
            while(reader.hasNextLine()){
                String question=reader.nextLine();
                String id=reader.nextLine();
                String answer1=reader.nextLine();
                String pass=reader.nextLine();
                if(answer.getText().toUpperCase().equals(answer1.toUpperCase())){
                    flag=true;
                    Password=pass;
                }


            }


            reader.close();
        if(flag) {
            lb5.setText(Password);
            lb1.setVisible(false);
            nameinp.setVisible(false);
            btuserok.setVisible(false);
            lb2.setVisible(false);
            lb3.setVisible(false);
            lb4.setVisible(false);
            lb5.setVisible(true);
            lb6.setVisible(true);
            answer.setVisible(false);
            btansweok.setVisible(false);
        }
        else{
            Alerts alert=new Alerts();
            alert.getAlert("Wrong answer");
        }
        } );

        lb1.setStyle("-fx-text-fill:#F5F5DC;-fx-font-size:16");
        lb2.setStyle("-fx-text-fill:#F5F5DC;-fx-font-size:16");
        lb3.setStyle("-fx-text-fill:#F5F5DC;-fx-font-size:16");
        lb4.setStyle("-fx-text-fill:#F5F5DC;-fx-font-size:20");
        lb5.setStyle("-fx-text-fill:#F5F5DC;-fx-font-size:20");
        lb6.setStyle("-fx-text-fill:#F5F5DC;-fx-font-size:16");
        pane.setStyle("-fx-background-color:#2D3447");



        Scene scene = new Scene(pane, 500, 247);
        primaryStage.getIcons().add(new Image("unnamed.png"));
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.show();


    }


}