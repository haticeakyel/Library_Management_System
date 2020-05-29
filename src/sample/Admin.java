package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Admin extends Librarian implements IAdmin{


    Scene addBookScene, showUsersScene;
    private String username;
    private String password ;

    public Admin (String username, String password) throws FileNotFoundException {
        super();
        this.setUsername(username);
        this.setPassword(password);
    }


    @Override
    public void showUsers() throws IOException {
        File userFile = new File("users.txt");
        if(userFile.createNewFile()){
            System.out.println("users.txt created");
        }
        else {
            System.out.println("users.txt is already exist");
        }

        // buffered reader for users.txt
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader("users.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String row = null;
        List<User> userList = new ArrayList<>();
        while (true){
            try {
                if (!((row = bufferedReader.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            String[] data = row.split(",");
            User user = new User(data[0],data[1],data[2]);
            userList.add(user);
        }

        Stage stageUsers = new Stage();
        TableView tableView = new TableView ();
        tableView.setEditable(true);
        TableColumn nameColumn = new TableColumn("Name Surname");
        TableColumn usernameColumn = new TableColumn("Username");
        tableView.getColumns().addAll(nameColumn,usernameColumn);
        ObservableList<User> userObservableList = FXCollections.observableArrayList(userList);
        tableView.getItems().addAll(userObservableList);
        nameColumn.setCellValueFactory(new PropertyValueFactory<User,String>("name"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<User,String>("nickname"));

        VBox vBoxUL = new VBox(25);
        vBoxUL.setPadding(new Insets(10,10,10,10));
        vBoxUL.getChildren().addAll(tableView);
        showUsersScene = new Scene(vBoxUL,500,500);
        stageUsers.setScene(showUsersScene);
        stageUsers.show();
    }


    @Override
    public void addBook() {
        Stage addBookStage = new Stage();
        VBox vBoxAddBook = new VBox(25);
        GridPane gridPaneAddBook = new GridPane();
        gridPaneAddBook.setPadding(new Insets(10,10,10,10));
        gridPaneAddBook.setVgap(8);
        gridPaneAddBook.setHgap(10);

        Label bookNameLabel = new Label("Book Name: ");
        gridPaneAddBook.setConstraints(bookNameLabel, 0, 0);
        TextField bookNameInput = new TextField();
        bookNameInput.setPromptText("book name");
        gridPaneAddBook.setConstraints(bookNameInput, 1, 0);
        Label authorLabel = new Label("Author: ");
        gridPaneAddBook.setConstraints(authorLabel, 0, 1);
        TextField authorInput = new TextField();
        authorInput.setPromptText("author");
        gridPaneAddBook.setConstraints(authorInput, 1, 1);
        Label publisherLabel = new Label("Publisher: ");
        gridPaneAddBook.setConstraints(publisherLabel, 0, 2);
        TextField publisherInput = new TextField();
        publisherInput.setPromptText("publisher");
        gridPaneAddBook.setConstraints(publisherInput, 1, 2);

        Button add = new Button("Add");
        gridPaneAddBook.setConstraints(add, 1,3);

        gridPaneAddBook.getChildren().addAll(bookNameLabel, bookNameInput, authorLabel, authorInput, publisherLabel, publisherInput,add);
        addBookScene = new Scene(gridPaneAddBook, 380, 300);
        addBookStage.setScene(addBookScene);
        addBookStage.setTitle("Adding New Book");
        addBookStage.show();

        add.setOnAction(event2 -> {
            File bookFile = new File("books.txt");
            try {
                if (bookFile.createNewFile()){
                    System.out.println("File created.");
                }
                else {
                    System.out.println("books.txt is already exist");
                }
            } catch (IOException ex) {
                System.out.println("Io Exception");
            }
            try {
                BufferedWriter bufferedWriterBook = new BufferedWriter(new FileWriter("books.txt",true));
                bufferedWriterBook.write(String.format("%s,%s,%s\n",bookNameInput.getText(),authorInput.getText(),publisherInput.getText()));
                bufferedWriterBook.close();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Added book");
                alert.setHeaderText("The book that you written is added to list!");
                alert.setContentText("Mission completed. Book is in the list.");
                alert.showAndWait();
            } catch (IOException ex) {
                System.out.println("Io Exception while writing books.txt");
            }


        });

    }

    @Override
    public void showBooks() {
        super.showBooks();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
