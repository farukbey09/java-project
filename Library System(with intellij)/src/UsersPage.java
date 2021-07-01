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

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class UsersPage  {

    public  void getUsersPage()  {

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
        //Username Column
        TableColumn<Users,String> usernameColumn =new TableColumn("Username");
        usernameColumn.setPrefWidth(204);
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        //Password COLUMNS
        TableColumn<Users,String> passwordColumn =new TableColumn("Password");
        passwordColumn.setPrefWidth(204);
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));

        //name COLUMNS
        TableColumn<Users,String> nameColumn =new TableColumn("Name");
        nameColumn.setPrefWidth(204);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        //gender COLUMNS
        TableColumn<Users,String> genderColumn =new TableColumn("Gender");
        genderColumn.setPrefWidth(204);
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        //Table View
           TableView<Users> table=new TableView<>();
      table.setItems(getUsers());
        table.getColumns().addAll(usernameColumn, passwordColumn, nameColumn, genderColumn);

        //ID Field
        TextField id =new TextField();
        id.setPromptText("Username");

        //Name Field
        TextField password =new TextField();
        password.setPromptText("Password");

        //Author Field
        TextField name =new TextField();
        name.setPromptText("Name");

        //Pages Field
        TextField Gender =new TextField();
        Gender.setPromptText("Gender");

        //add button
        Button btadd =new Button("Add");
        btadd.setOnAction(e->{
            Alerts alert=new Alerts();
            File fileNames = new File("Users.txt");
            try {
                Scanner reader = new Scanner(fileNames);
               int flag=0;
                while(reader.hasNextLine()){
                  String username=reader.nextLine();
                  String    pass=reader.nextLine();
                   if(username.trim().toUpperCase().equals(id.getText().trim().toUpperCase()))
                       flag++;
               }

                reader.close();



                   if(!(Gender.getText().toUpperCase().trim().equals("FEMALE")||Gender.getText().toUpperCase().trim().equals("MALE"))){
                    alert.getAlert("Gender should be Male or Female");
                }

                   else if((id.getText().contains(" "))){
                       alert.getAlert("ID cannot contain space");
                   }
                   else if((password.getText().contains(" "))){
                       alert.getAlert("Password cannot contain space");
                   }

                   else if(!(id.getText().trim().length()>=6)&&!(id.getText().trim().length()<15)){
                    alert.getAlert("ID should be at least 6 Characters");
                }

                   else if(!(id.getText().trim().length()<=15)){
                       alert.getAlert("ID should be at most 15 Characters");
                   }

                   else if(!(password.getText().trim().length()<=15)){
                       alert.getAlert("Password should be at most 15 Characters");
                   }
                   else if(!(name.getText().trim().length()<=15)){
                       alert.getAlert("Name should be at most 15 Characters");
                   }
                   else if((name.getText().contains(" "))){
                       alert.getAlert("Name cannot contain space");
                   }
                   else if(!(password.getText().trim().length()<=15)){
                       alert.getAlert("ID should be at most 15 Characters");
                   }

                   else if(!(password.getText().trim().length()>=6)){
                    alert.getAlert("Password should be at least 6 Characters");
                }
                else if(!(name.getText().trim().length()>=1)){
                    alert.getAlert("Name should be at least 1 Characters");
                }

                else if(flag==1){
                    alert.getAlert("This ID s already taken by another user");
                   }

            else{



                        File fileInfo = new File("UsersInfo.txt");
                       File fileUser = new File("Users.txt");
                       BufferedWriter UsersInfo= new BufferedWriter(new FileWriter(fileInfo,true));
                       BufferedWriter Users = new BufferedWriter(new FileWriter(fileUser, true));


                        if(fileInfo.length()!=0)
                       UsersInfo.newLine();
                       UsersInfo.write(id.getText().trim());
                       UsersInfo.newLine();
                       UsersInfo.write(password.getText().trim());
                       UsersInfo.newLine();
                       UsersInfo.write(name.getText().trim());
                       UsersInfo.newLine();
                       UsersInfo.write(Gender.getText().trim());


                        if(fileUser.length()!=0)
                       Users.newLine();
                       Users.write(id.getText().trim());
                       Users.newLine();
                       Users.write(password.getText().trim());




                        UsersInfo.close();
                        Users.close();
                    table.getItems().removeAll();
                    table.setItems(getUsers());

                }

        id.clear();
          Gender.clear();
          name.clear();
          password.clear();


            }
           catch (IOException ed){
                ed.printStackTrace();
           }




        });
        //Delete Button
        Button btdelete =new Button("Delete");
        btdelete.setOnAction(e->{
        getDeleteButton(table);




        });
        //Search Field
        TextField search=new TextField();
        search.setPromptText("Search...");
        Image image1=new Image("Search1.jpg");
        ImageView image2 =new ImageView(image1);
        image2.setFitWidth(25);
        image2.setFitHeight(21);
        Button btsearch=new Button("", image2);
        topPane.getChildren().addAll(search,btsearch);
        btsearch.setOnAction(e->{

            try {
                ObservableList<Users> list=getSearch(search,table);
               table.setItems(list);


            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        botPane1.getChildren().addAll(id, password, name, Gender);
        botPane2.getChildren().addAll(btadd, btdelete);



        layout.setTop(topPane);
        layout.setCenter(CenterPane);
        CenterPane.getChildren().addAll(table,botPane);



//CSS
        search.setStyle("-fx-background-radius:100");
        layout.setStyle("-fx-backgrund-color:gray");
        btsearch.setStyle("-fx-backgrund-color:gray");


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




    public  ObservableList<Users> getUsers(){
        File fileInfo = new File("UsersInfo.txt");
        ObservableList<Users> list= FXCollections.observableArrayList();
        try {
            Scanner reader=new Scanner(fileInfo);

           while (reader.hasNextLine()) {
            String username=reader.nextLine();
            String password=reader.nextLine();
            String name=reader.nextLine();
            String gender=reader.nextLine();
               list.add(new Users(username,password,name,gender));
           }

       reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }





        return list;
    }


    public  void getDeleteButton(TableView<Users> table)   {


        try {
            ObservableList<Users> userSelected;

            userSelected=table.getSelectionModel().getSelectedItems();
            Users selected=userSelected.get(0);
            if(table.getSelectionModel().isEmpty()==false) {


                File fileInfo = new File("UsersInfo.txt");
                File fileUsers = new File("Users.txt");
                ArrayList Info=new ArrayList();
                ArrayList Users=new ArrayList();


                Scanner readInfo = new Scanner(fileInfo);
                Scanner readUsers = new Scanner(fileUsers);




                while (readUsers.hasNextLine()) {
                    String username = readUsers.nextLine();
                    String password = readUsers.nextLine();

                    int flag = 0;


                    if (selected.getUsername().trim().toUpperCase().equals(username.trim().toUpperCase()) && selected.getPassword().trim().toUpperCase().equals(password.trim().toUpperCase())) {
                        flag = 1;

                    }

                    if (flag == 0) {
                        Users.add(username);
                        Users.add(password);

                    }

                }


                while (readInfo.hasNext()) {
                    String Username = readInfo.next();
                    String Password = readInfo.next();
                    String Name = readInfo.next();
                    String Gender = readInfo.next();

                    int flag = 0;
                    if (selected.getUsername().trim().toUpperCase().equals(Username.trim().toUpperCase()) && selected.getPassword().trim().toUpperCase().equals(Password.trim().toUpperCase()) && selected.getName().trim().toUpperCase().equals(Name.trim().toUpperCase()) && selected.getGender().trim().toUpperCase().equals(Gender.trim().toUpperCase())) {
                        flag = 1;

                    }
                    if (flag == 0) {

                        Info.add(Username);
                        Info.add(Password);
                        Info.add(Name);
                        Info.add(Gender);
                    }

                }





                readInfo.close();
                readUsers.close();
                writeTofile(Users,"Users.txt");
                writeTofile(Info,"UsersInfo.txt");


                table.getItems().removeAll();
                table.setItems(getUsers());
            }
            else{
                Alerts alert=new Alerts();
                alert.getAlert("You must click one item");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }











    }



    public  ObservableList<Users> getSearch(TextField ID,TableView<Users> table)throws  IOException{
        ObservableList<Users> list =FXCollections.observableArrayList();

        File fileInfo=new File("UsersInfo.txt");
        Scanner reader=new Scanner(fileInfo);
        while(reader.hasNextLine()){
            String Username=reader.nextLine();
            String Password= reader.nextLine();
            String Name=reader.nextLine();
            String Gender=reader.nextLine();




        if(Username.toUpperCase().trim().contains(ID.getText().trim().toUpperCase())||Name.toUpperCase().trim().contains(ID.getText().trim().toUpperCase())){
         list.add(new Users(Username,Password,Name,Gender));


       }



        }


        if(ID.getText().trim().length()==0){
           table.setItems(getUsers());
        }


        reader.close();

        return list;
    }

    public  void writeTofile(ArrayList<String> list,String url) throws IOException {
        File file = new File(url);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        for (int i = 0; i < list.size(); i++) {

            writer.write(list.get(i).toString());
            if (!(i == (list.size() - 1)))
                writer.newLine();

        }
        writer.close();
    }


}
