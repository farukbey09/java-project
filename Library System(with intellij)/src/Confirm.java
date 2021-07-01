import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class Confirm  {

    static boolean answer;
    public static boolean Display(String Title){
        Stage window=new Stage();
        window.setTitle(Title);
        window.initModality(Modality.APPLICATION_MODAL);
        Image image1 =new Image("20dm.gif");
        ImageView image =new ImageView(image1);
        image.setFitWidth(85);
        image.setFitHeight(85);

        Button bt1=new Button("Yes");
        Button bt2=new Button("No");
        bt1.setOnAction(e->  {
            answer=true;
            window.close();


        });
        bt2.setOnAction(e->  {
            answer=false;
            window.close();


        });
        GridPane layout=new GridPane();
        BorderPane pane=new BorderPane();
        VBox vbox =new VBox(15);
       Label label= new Label("Do you leave me alone?");
       label.setStyle("-fx-text-fill:#E0FFFF;-fx-font-size:16");
vbox.setPadding(new Insets(20,0,0,0));
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(image,label);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(0,0,0,0));

        pane.setTop(vbox);

        pane.setCenter(layout);
        pane.setStyle("-fx-background-color:#2D3447");

        layout.add(bt1,0,1);
        layout.add(bt2,1,1);
        layout.setVgap(20);
        layout.setHgap(10);
        layout.setAlignment(Pos.CENTER);

        Scene scene=new Scene(pane,250,170);
        window.setResizable(false);
        window.setScene(scene);
        window.showAndWait();

        return answer;
    }




}
