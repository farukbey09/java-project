import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class MyListPage extends Abstractt implements Interface {



    public void getPage()throws IOException {
BorderPane layout=new BorderPane();
        HBox topBox=new HBox();
        layout.setTop(topBox);
        ListView<String> list=new ListView<>();
        layout.setCenter(list);
        getItem(list);


        Image img =new Image("favourite.png");
        ImageView imgview =new ImageView(img);
        imgview.setFitHeight(61);
        imgview.setFitWidth(72);
        Label label1=new Label("MyList: ");
        Label label2=new Label("                if you wanna delete Books U Can use 'Delete'");

        topBox.getChildren().addAll(imgview,label1);
        layout.setBottom(label2);

        label1.setAlignment(Pos.CENTER);
        //css
        label1.setStyle("-fx-font-size:30 ; -fx-text-fill:E0FFFF");
        label2.setStyle(" -fx-text-fill:E0FFFF");
        layout.setStyle("-fx-background-color:black");
        list.setStyle("-fx-background-color: #C0C0C0");


        Stage mylistStage=new Stage();
        Scene scene=new Scene(layout,343,500);
        scene.setOnKeyPressed(e->{
                if(e.getCode()== KeyCode.DELETE){
                    try {
                            getDelete(list);
                          ObservableList<String> userSelected,allUser;
                          allUser=list.getItems();
                          userSelected=list.getSelectionModel().getSelectedItems();
                          userSelected.forEach(allUser::remove);


                    }
                    catch (IOException ioException){
                            ioException.printStackTrace();
                    }
                    }
        });
        mylistStage.getIcons().add(new Image("unnamed.png"));
        mylistStage.initModality(Modality.APPLICATION_MODAL);
        mylistStage.setScene(scene);
        mylistStage.setResizable(false);
        mylistStage.show();



    }
public void getDelete(ListView<String> list)throws  IOException{
        ObservableList<String> oblist=list.getSelectionModel().getSelectedItems();
        if(list.getSelectionModel().isEmpty()==false ) {
            File file=new File(new LoginPage().username.toLowerCase()+"'slist.txt");
        Scanner reader=new Scanner(file);
        ArrayList<String> item=new ArrayList<>();


               while (reader.hasNextLine()) {
                       String str = reader.nextLine();


                       if (oblist.get(0).equals(str)) {


                       } else {
                               item.add(str);

                       }

               }

               reader.close();
               writeTofile(item);


       }}
       public  void writeTofile(ArrayList<String> list) throws IOException {
                File file=new File(new LoginPage().username.toLowerCase()+"'slist.txt");
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                for (int i = 0; i < list.size(); i++) {

                        writer.write(list.get(i).toString());
                        if (!(i == (list.size() - 1)))
                                writer.newLine();

                }
                writer.close();
        }
public void getItem(ListView<String> list)throws IOException{

        File file=new File(new LoginPage().username.toLowerCase()+"'slist.txt");
        if(!file.exists()){
            file.createNewFile();
        }
        Scanner reader=new Scanner(file);
        while (reader.hasNextLine()){


                String str=reader.nextLine();
                list.getItems().add(str);
        }

        reader.close();



}
}
