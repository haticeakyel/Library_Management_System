package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class User extends Librarian{
    private String name;
    private String nickname;
    private String password;

    public User(String name, String nickname, String password){
        this.setName(name);
        this.setNickname(nickname);
        this.setPassword(password);
    }

    File fileU = new File("users.txt");
    FileReader frU;
    {
        try {
            frU = new FileReader(fileU);
            char c = (char)frU.read();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("Io exception");
        }
    }


    @Override
    public void showBooks() {
        //buffered reader for books.txt
        BufferedReader bufferedReaderbook = null;
        try {
            bufferedReaderbook = new BufferedReader(new FileReader("books.txt"));
        } catch (FileNotFoundException ex) {
            System.out.println("File not found.");
        }
        String rowB = "";
        List<Book> bookList = new ArrayList<>();
        while (true){
            try {
                if (!((rowB = bufferedReaderbook.readLine()) != null))
                    break;
            } catch (IOException ex) {
                System.out.println("Io exception");
            }
            String[] info = rowB.split(",");
            Book book = new Book(info[0],info[1],info[2]);
            bookList.add(book);
        }




        Stage showBooksStage = new Stage();
        TableView tableView = new TableView ();
        tableView.setEditable(true);
        TableColumn nameColumn = new TableColumn("Name");
        TableColumn authorColumn = new TableColumn("Author");
        TableColumn publisherColumn = new TableColumn("Publisher");
        tableView.getColumns().addAll(nameColumn,authorColumn, publisherColumn);
        ObservableList<Book> bookObservableList = FXCollections.observableArrayList(bookList);
        tableView.getItems().addAll(bookObservableList);
        nameColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("name"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("author"));
        publisherColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("publisher"));

        Button order = new Button("Order");
        order.setOnAction(event3 -> {
            Book book = (Book) tableView.getSelectionModel().getSelectedItem();
            if (book != null){
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Ordering Page");
                dialog.setHeaderText(book.getName());
                dialog.setContentText("Please enter your adress: ");
                Optional<String> result = dialog.showAndWait();
                result.ifPresent (adress -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Adress page");
                    alert.setHeaderText("Ordered Succesfully");
                    alert.setContentText(book.getName() + " is shipping to : " + adress);
                    alert.show();
                });

            }
        });
        VBox vBoxUL = new VBox(25);
        vBoxUL.setPadding(new Insets(10,10,10,10));
        vBoxUL.getChildren().addAll(tableView,order);
        showBooksScene = new Scene(vBoxUL,1000,2000);
        showBooksStage.setScene(showBooksScene);
        showBooksStage.show();

    }



    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
