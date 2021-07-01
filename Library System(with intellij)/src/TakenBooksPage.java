import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class TakenBooksPage extends Abstractt {

    public  void getTakenBooksPage() throws ParseException {

        Stage window =new Stage();
        BorderPane layout=new BorderPane();
        HBox topPane=new HBox(4);
        topPane.prefHeight(55);
        topPane.setAlignment(Pos.TOP_RIGHT);
        VBox CenterPane=new VBox(20);
        HBox botPane=new HBox(25);
        HBox botPane1=new HBox(15);
        HBox botPane2=new HBox(12-0);



        botPane.getChildren().addAll(botPane1,botPane2);





        //Paddings
        botPane.setPadding(new Insets(0,0,20,22));
        topPane.setPadding(new Insets(10,15,10,0));


        //Table Columns
        //BOOK ID Column
        TableColumn<TakenBooks,String> bookidColumn =new TableColumn("Book ID");
        bookidColumn.setPrefWidth(205);
        bookidColumn.setCellValueFactory(new PropertyValueFactory<>("bookid"));

        //Book NAME COLUMNS
        TableColumn<TakenBooks,String> booknameColumn =new TableColumn("Book Name");
        booknameColumn.setPrefWidth(205);
        booknameColumn.setCellValueFactory(new PropertyValueFactory<>("bookname"));

        //Users Name COLUMNS
        TableColumn<TakenBooks,String> usersnameColumn =new TableColumn("User's Name");
        usersnameColumn.setPrefWidth(205);
        usersnameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        //Time Column

        TableColumn<TakenBooks,String> timeColumn =new TableColumn("Days");
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        timeColumn.setPrefWidth(205);

        //Table View
        TableView<TakenBooks> table=new TableView<>();
        try{
            table.setItems(getTakenBooks());
        }
       catch (IOException io){
            io.printStackTrace();
       }

        table.getColumns().addAll(bookidColumn, booknameColumn, usersnameColumn,timeColumn);
//Search Field
        TextField search = new TextField();
        search.setPromptText("Search...");
        Image image1 = new Image("Search1.jpg");
        ImageView image = new ImageView(image1);
        image.setFitWidth(25);
        image.setFitHeight(21);
        Button btsearch = new Button("", image);
        topPane.getChildren().addAll(search, btsearch);
        btsearch.setOnAction(e -> {

            try {
                ObservableList<TakenBooks> list = getSearch(search, table);
                table.getItems().removeAll();
                table.setItems(list);


            } catch (IOException | ParseException ioException) {
                ioException.printStackTrace();
            }
        });
        Button btGivingback=new Button("Giving Back");
        btGivingback.setOnAction(
                e->{
                    try {
                        getGivingBackButton(table);
                        table.getItems().removeAll();
                        table.setItems(getTakenBooks());

                    } catch (IOException | ParseException ioException) {
                        ioException.printStackTrace();
                    }
                }
        );
        botPane1.getChildren().add(btGivingback);


        layout.setTop(topPane);
        layout.setCenter(CenterPane);
        CenterPane.getChildren().addAll(table,botPane);



//CSS
        search.setStyle("-fx-background-radius:100");



        window.initModality(Modality.APPLICATION_MODAL);
        Scene scene =new Scene(layout,820,430);

       scene.setOnKeyPressed(e->{
           if(e.getCode()== KeyCode.ESCAPE){
               window.close();
           }
       });
        window.getIcons().add(new Image("unnamed.png"));
        window.setScene(scene);
        window.setResizable(false);
        window.show();

    }

    public   ObservableList<TakenBooks> getSearch(TextField ID, TableView<TakenBooks> table) throws IOException, ParseException {
        ObservableList<TakenBooks> list = FXCollections.observableArrayList();

        File fileInfo = new File("TakenBooks.txt");
        Scanner reader = new Scanner(fileInfo);
        while (reader.hasNextLine()) {
            String BookId = reader.nextLine();
            String Namebook = reader.nextLine();
            String Author = reader.nextLine();
            String Pages = reader.nextLine();
            String User = reader.nextLine();
            String Time = reader.nextLine();

            if (BookId.toUpperCase().trim().contains(ID.getText().trim().toUpperCase()) || User.toUpperCase().trim().contains(ID.getText().trim().toUpperCase()) || Namebook.toUpperCase().trim().contains(ID.getText().trim().toUpperCase())) {

                if(timeEquation(Time)>=15) {
                    String time= String.valueOf(timeEquation(Time));
                    list.add(new TakenBooks(BookId, Namebook, User, time+" DAYS!!!"));


                }
                else{
                    String time= String.valueOf(timeEquation(Time));
                    list.add(new TakenBooks(BookId, Namebook, User,time+" Days"));
                }


            }


        }


        if (ID.getText().trim().length() == 0) {
            table.setItems(getTakenBooks());
        }


        reader.close();

        return list;
    }



    public  ObservableList<TakenBooks> getTakenBooks() throws IOException, ParseException {
      File file=new File("TakenBooks.txt");
      Scanner reader = new Scanner(file);
      ObservableList<TakenBooks> list= FXCollections.observableArrayList();
      while(reader.hasNextLine()){

          String BookID =reader.nextLine();
          String BookName=reader.nextLine();
          String Author=reader.nextLine();
          String Pages =reader.nextLine();
          String User  =reader.nextLine();
          String Time  =reader.nextLine();


        if(timeEquation(Time)>=15) {
            String time= String.valueOf(timeEquation(Time));
            list.add(new TakenBooks(BookID, BookName, User, time+" DAYS!!!"));


        }
      else{
           String time= String.valueOf(timeEquation(Time));
            list.add(new TakenBooks(BookID, BookName, User,time+" Days"));
        }

        }

       reader.close();


        return list;
    }

    public  void getGivingBackButton(TableView<TakenBooks> table) throws IOException{
        ObservableList<TakenBooks> userSelected;
        userSelected = table.getSelectionModel().getSelectedItems();
        TakenBooks takenbook = userSelected.get(0);


        if(table.getSelectionModel().isEmpty()==false) {
            File fileTaken = new File("TakenBooks.txt");
            File fileBooks=new File("Books.txt");
            Scanner readtaken=new Scanner(fileTaken);
            BufferedWriter writerbooks=new BufferedWriter(new FileWriter(fileBooks,true));
            ArrayList<String> list=new ArrayList<>();
            while(readtaken.hasNextLine()){
                String BookID =readtaken.nextLine();
                String BookName=readtaken.nextLine();
                String Author=readtaken.nextLine();
                String Pages =readtaken.nextLine();
                String User  =readtaken.nextLine();
                String Time  =readtaken.nextLine();
                boolean flag =true;

                if(BookID.trim().toUpperCase().equals(takenbook.getBookid().toUpperCase().trim())){
                   if(fileBooks.length()!=0)
                    writerbooks.newLine();
                    writerbooks.write(BookID.trim());
                    writerbooks.newLine();
                    writerbooks.write(BookName.trim());
                    writerbooks.newLine();
                    writerbooks.write(Author.trim());
                    writerbooks.newLine();
                    writerbooks.write(Pages.trim());
                    flag=false;


                }

                if(flag==true){

                 list.add(BookID);
                 list.add(BookName);
                 list.add(Author);
                 list.add(Pages);
                 list.add(User);
                 list.add(Time);



                }





            }
            writerbooks.close();
            readtaken.close();
          writeTofile(list);




        }
        else{
            Alerts alert =new Alerts();
            alert.getAlert("You must click one item");
        }



    }


    public  void writeTofile(ArrayList<String>list)throws IOException{
        File file=new File("TakenBooks.txt");
        BufferedWriter writer=new BufferedWriter(new FileWriter(file));
        for(int i=0;i<list.size();i++){

            writer.write(list.get(i).toString());
            if(!(i==(list.size()-1)))
                writer.newLine();

        }
        writer.close();
    }

  public  int timeEquation(String time) throws ParseException {
      DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyyy");
      LocalDateTime now = LocalDateTime.now();
      Calendar cal1 = new GregorianCalendar();
      Calendar cal2 = new GregorianCalendar();

      SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");

      Date date = sdf.parse(time);
      cal1.setTime(date);
      date = sdf.parse(dtf.format(now));
      cal2.setTime(date);


    return daysBetween(cal1.getTime(),cal2.getTime());

  }

    public  int daysBetween(Date d1, Date d2){
        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }
}




