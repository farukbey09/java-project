import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;


public class BooksPage extends Abstractt {

    public void getPage() {


        Stage window = new Stage();


        BorderPane layout = new BorderPane();
        HBox topPane = new HBox(530);
        topPane.prefHeight(55);
        //for Mylist and Search
        HBox boxMYlist=new HBox();
        boxMYlist.setAlignment(Pos.TOP_LEFT);
        HBox boxSearch=new HBox(4);
        boxSearch.setAlignment(Pos.TOP_RIGHT);
        topPane.getChildren().addAll(boxMYlist,boxSearch);

        VBox CenterPane = new VBox(20);
        HBox botPane = new HBox(25);
        HBox botPane1 = new HBox(15);
        HBox botPane2 = new HBox(12 - 0);


        botPane.getChildren().addAll(botPane1, botPane2);


        //Paddings
        botPane.setPadding(new Insets(0, 0, 20, 22));
        topPane.setPadding(new Insets(10, 15, 10, 20));


        //Table Columns
        //BOOK ID Column
        TableColumn<Books, String> bookidColumn = new TableColumn("BOOK ID");
        bookidColumn.setPrefWidth(100);
        bookidColumn.setCellValueFactory(new PropertyValueFactory<>("bookid"));

        //NAME COLUMNS
        TableColumn<Books, String> nameColumn = new TableColumn("NAME");
        nameColumn.setPrefWidth(320);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        //AUTHOR COLUMNS
        TableColumn<Books, String> authorColumn = new TableColumn("AUTHOR");
        authorColumn.setPrefWidth(320);
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));

        //PAGES COLUMNS
        TableColumn<Books, Integer> pagesColumn = new TableColumn("PAGES");
        pagesColumn.setPrefWidth(100);
        pagesColumn.setCellValueFactory(new PropertyValueFactory<>("pages"));
        //Table View
        TableView<Books> table = new TableView<>();
        try {
            table.setItems(getBooks());
        } catch (IOException e) {
            e.printStackTrace();
        }
        table.getColumns().addAll(bookidColumn, nameColumn, authorColumn, pagesColumn);
        // Add to myList
        Image img =new Image("favourite.png");
        ImageView imgview =new ImageView(img);
        imgview.setFitHeight(18);
        imgview.setFitWidth(18);
        Button btmylist = new Button("MyList", imgview);

        btmylist.setOnAction(e -> {

            try {
                if (table.getSelectionModel().isEmpty() == false) {
                    File file = new File(new LoginPage().username.toLowerCase() + "'slist.txt");
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    ObservableList<Books> userSelected;
                    userSelected = table.getSelectionModel().getSelectedItems();
                    Books book = userSelected.get(0);

                    Scanner reader = new Scanner(file);
                    int flag = 0;
                    while (reader.hasNextLine()) {
                        String str = reader.nextLine();

                        if ((book.getName().toLowerCase().equals(str.toLowerCase()))) {
                            flag++;
                        }
                    }

                    BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
                    if (flag == 0) {
                        if (file.length() != 0)
                            writer.newLine();
                        writer.write(book.getName());


                    }


                    reader.close();
                    writer.close();
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }


        });

        boxMYlist.getChildren().add(btmylist);


        //Search Field
        TextField search = new TextField();
        search.setPromptText("Search...");
        Image image1 = new Image("Search1.jpg");
        ImageView image2 = new ImageView(image1);
        image2.setFitWidth(25);
        image2.setFitHeight(21);
        Button btsearch = new Button("", image2);
        boxSearch.getChildren().addAll(search, btsearch);
        btsearch.setOnAction(e -> {

            try {
                ObservableList<Books> list = getSearch(search, table);
                table.setItems(list);


            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });


        //ID Field
        TextField id = new TextField();
        id.setPromptText("Book ID");

        //Name Field
        TextField name = new TextField();
        name.setPromptText("Name");

        //Author Field
        TextField author = new TextField();
        author.setPromptText("Author");

        //Pages Field
        TextField pages = new TextField();
        //ı made this part because ı just want  only integers
        pages.textProperty().addListener(new ChangeListener<String>() {

            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                    pages.setText(oldValue);
                }
            }
        });
        pages.setPromptText("Pages");

        //add button
        Button btadd = new Button("Add");
        btadd.setOnAction(e -> {
            File booksFile = new File("Books.txt");
            File takenbooksFile = new File("TakenBooks.txt");
            try {
                Alerts alert = new Alerts();
                Scanner readerbooks = new Scanner(booksFile);
                Scanner readertaken = new Scanner(takenbooksFile);

                boolean flag = true;
                boolean flag2 = true;
                while (readerbooks.hasNextLine()) {
                    String str = readerbooks.nextLine();
                    String str2 = readerbooks.nextLine();
                    String str3 = readerbooks.nextLine();
                    String str4 = readerbooks.nextLine();

                    if (id.getText().trim().toUpperCase().equals(str.trim().toUpperCase())) {
                        flag = false;
                    }


                }
                while (readertaken.hasNextLine()) {
                    String str = readertaken.nextLine();
                    String str2 = readertaken.nextLine();
                    String str3 = readertaken.nextLine();
                    String str4 = readertaken.nextLine();
                    String str5 = readertaken.nextLine();
                    String str6 = readertaken.nextLine();

                    if (id.getText().trim().toUpperCase().equals(str.trim().toUpperCase())) {
                        flag2 = false;
                    }


                }
                if (flag == false) {
                    alert.getAlert("This ID is taken so choose another ID");
                } else if (!(id.getText().length() >= 2)) {
                    alert.getAlert("ID size should be min 2 characters");
                } else if (!(id.getText().length() <= 40)) {
                    alert.getAlert("ID size should be max 40 characters");
                } else if (id.getText().contains(" ")) {
                    alert.getAlert("ID cannot contain space");
                } else if (!(name.getText().length() >= 2)) {
                    alert.getAlert("Name size should be min 2 characters");
                } else if (!(name.getText().length() <= 40)) {
                    alert.getAlert("Name size should be max 40 characters");
                } else if (!(name.getText().length() >= 2)) {
                    alert.getAlert("Author size should be min 2 characters");
                } else if (!(name.getText().length() <= 40)) {
                    alert.getAlert("Author size should be max 40 characters");
                } else if (pages.getText().isEmpty() == true) {
                    alert.getAlert("You have to fill all the gaps");
                } else if (flag2 == false) {
                    alert.getAlert("This ID is taken so choose another ID");
                } else {

                    BufferedWriter writer = new BufferedWriter(new FileWriter(booksFile, true));
                    if (!(booksFile.length() == 0))
                        writer.newLine();
                    writer.write(id.getText().trim());
                    writer.newLine();
                    writer.write(name.getText().trim());
                    writer.newLine();
                    writer.write(author.getText().trim());
                    writer.newLine();
                    writer.write(pages.getText().trim());

                    writer.close();
                    table.getItems().removeAll();
                    table.setItems(getBooks());

                }

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            id.clear();
            author.clear();
            name.clear();
            pages.clear();


        });
        //Barrow Button
        Button btbarrow = new Button("Barrow");
        btbarrow.setOnAction(e -> {
            getBarrowButton(table);
            table.getItems().removeAll();
            try {
                table.setItems(getBooks());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        });
        botPane1.getChildren().addAll(id, name, author, pages);
        botPane2.getChildren().addAll(btadd, btbarrow);


        layout.setTop(topPane);
        layout.setCenter(CenterPane);
        CenterPane.getChildren().addAll(table, botPane);


    //CSS
        search.setStyle("-fx-background-radius:100");
        btmylist.setStyle("-fx-background-radius:22");

        window.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(layout, 820, 430);
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE) {
                window.close();
            }
        });
        window.getIcons().add(new Image("unnamed.png"));
        window.setScene(scene);
        window.setResizable(false);
        window.show();

    }


    public ObservableList<Books> getBooks() throws IOException {
        ObservableList<Books> list = FXCollections.observableArrayList();
        File file = new File("Books.txt");
        Scanner reader = new Scanner(file);
        while (reader.hasNextLine()) {
            String strid = reader.nextLine();
            String strname = reader.nextLine();
            String strauthor = reader.nextLine();
            String strpage = reader.nextLine();

            list.add(new Books(strid, strname, strauthor, Integer.parseInt(strpage)));
        }

        reader.close();


        return list;


    }

    public ObservableList<Books> getSearch(TextField ID, TableView<Books> table) throws IOException {
        ObservableList<Books> list = FXCollections.observableArrayList();

        File fileInfo = new File("Books.txt");
        Scanner reader = new Scanner(fileInfo);
        while (reader.hasNextLine()) {
            String BookId = reader.nextLine();
            String Namebook = reader.nextLine();
            String Nameauthor = reader.nextLine();
            String pages = reader.nextLine();


            if (BookId.toUpperCase().trim().contains(ID.getText().trim().toUpperCase()) || Nameauthor.toUpperCase().trim().contains(ID.getText().trim().toUpperCase()) || Namebook.toUpperCase().trim().contains(ID.getText().trim().toUpperCase())) {
                list.add(new Books(BookId, Namebook, Nameauthor, Integer.parseInt(pages)));


            }


        }


        if (ID.getText().trim().length() == 0) {
            table.setItems(getBooks());
        }


        reader.close();

        return list;
    }

    public void getBarrowButton(TableView<Books> table) {


        try {

            ObservableList<Books> userSelected, allUsers;
            allUsers = table.getItems();
            userSelected = table.getSelectionModel().getSelectedItems();
            Books book = userSelected.get(0);
            if (table.getSelectionModel().isEmpty() == false) {
                File Lastuser = new File("LastUser.txt");
                File books = new File("Books.txt");
                ArrayList<String> list = new ArrayList<>();
                File takenbooks = new File("TakenBooks.txt");


                Scanner readBooks = new Scanner(books);
                Scanner readLastUser = new Scanner(Lastuser);
                String lastuser = readLastUser.next();


                BufferedWriter writetaken = new BufferedWriter(new FileWriter(takenbooks, true));


                while (readBooks.hasNextLine()) {
                    String BookID = readBooks.nextLine();
                    String Name = readBooks.nextLine();
                    String Author = readBooks.nextLine();
                    String Pages = readBooks.nextLine();


                    int flag = 0;


                    if (book.getBookid().trim().toUpperCase().equals(BookID.trim().toUpperCase())) {
                        if (takenbooks.length() != 0)
                            writetaken.newLine();
                        writetaken.write(BookID);
                        writetaken.newLine();
                        writetaken.write(Name);
                        writetaken.newLine();
                        writetaken.write(Author);
                        writetaken.newLine();
                        writetaken.write(Pages);
                        writetaken.newLine();
                        writetaken.write(lastuser);

                        //Current Time Expression
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyyy");
                        LocalDateTime now = LocalDateTime.now();
                        writetaken.newLine();
                        writetaken.write(dtf.format(now));


                        flag = 1;

                    }

                    if (flag == 0) {
                        list.add(BookID);
                        list.add(Name);
                        list.add(Author);
                        list.add(Pages);


                    }

                }


                readBooks.close();
                writeTofile(list);


                readLastUser.close();
                writetaken.flush();
                writetaken.close();


            } else {
                Alerts alert = new Alerts();
                alert.getAlert("You must click one item");
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public  void writeTofile(ArrayList<String> list) throws IOException {
        File file = new File("Books.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        for (int i = 0; i < list.size(); i++) {

            writer.write(list.get(i).toString());
            if (!(i == (list.size() - 1)))
                writer.newLine();

        }
        writer.close();
    }

}
