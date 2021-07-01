import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Alerts {
// There is  Overloading methods
    //Successfully registered
    public void getAlert(String title,Stage close,LoginPage page){
        Stage window =new Stage();
        VBox pane=new VBox(15);

        Label label=new Label(title);
        pane.setAlignment(Pos.CENTER);
        Image img=new Image("Succesfully.png") ;
        ImageView imgview=new ImageView(img);
        imgview.setFitWidth(20);
        imgview.setFitHeight(20);
        label.setStyle("-fx-font-size:14");
        Button bt=new Button("  Okay",imgview);
        pane.getChildren().addAll(label,bt);

        window.setOnCloseRequest(e->{
            page.getPage();
            close.close();
            window.close();
        });

        bt.setOnAction(e-> {

                    page.getPage();
                    close.close();
                    window.close();
                }
                );
       window.initModality(Modality.APPLICATION_MODAL);
        pane.setStyle("-fx-background-color:#C0C0C0");
        Scene scene=new Scene(pane,350,170);
      scene.setOnKeyPressed(e->{
          if (e.getCode() == KeyCode.ENTER){
              page.getPage();
              close.close();
              window.close();
          }
      });
        window.initStyle(StageStyle.UNDECORATED);
        window.setResizable(false);
        window.setScene(scene);
        window.showAndWait();


    }
    // inCorrect Entry
    public void getAlert(String title){
        Stage window =new Stage();
        VBox pane=new VBox(15);

        Label label=new Label(title);
        pane.setAlignment(Pos.CENTER);
        Image img =new Image("Error.png") ;
        ImageView imgview =new ImageView(img);
        imgview.setFitWidth(20);
        imgview.setFitHeight(20);
        label.setStyle("-fx-font-size:14");
        Button bt=new Button("  Okay", imgview);
        pane.getChildren().addAll(label,bt);

        window.setOnCloseRequest(e->{


            window.close();
        });

        bt.setOnAction(e-> {


                    window.close();
                }
        );
        window.initModality(Modality.APPLICATION_MODAL);
        pane.setStyle("-fx-background-color:#C0C0C0");
        Scene scene=new Scene(pane,370,180);
        scene.setOnKeyPressed(e->{
            if (e.getCode() == KeyCode.ENTER){
                window.close();
            }
        });
        window.initStyle(StageStyle.UNDECORATED);
        window.setResizable(false);
        window.setScene(scene);
        window.showAndWait();


    }
    //Successfully entry
    public void getAlert(String title,Stage close,HomePage page){
        Stage window =new Stage();
        VBox pane=new VBox(15);

        Label label=new Label(title);
        pane.setAlignment(Pos.CENTER);
        Image img =new Image("Succesfully.png") ;
        ImageView imgview =new ImageView(img);
        imgview.setFitWidth(20);
        imgview.setFitHeight(20);
        label.setStyle("-fx-font-size:14");
        Button bt=new Button("  Okay", imgview);
        pane.getChildren().addAll(label,bt);

        window.setOnCloseRequest(e->{
            page.getPage(window);
            close.close();
        });


        bt.setOnAction(e-> {
                    page.getPage(window);
                    close.close();

                }
        );
        window.initModality(Modality.APPLICATION_MODAL);
        pane.setStyle("-fx-background-color:#C0C0C0");
        Scene scene=new Scene(pane,350,170);
        scene.setOnKeyPressed(e->{
            if (e.getCode() == KeyCode.ENTER){
                page.getPage(window);
                close.close();
            }
        });
        window.initStyle(StageStyle.UNDECORATED);
        window.setResizable(false);
        window.setScene(scene);
        window.showAndWait();


    }
}
