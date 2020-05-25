package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public abstract class Librarian extends Application implements ILibrarian {

    Scene showBooksScene;

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

        VBox vBoxUL = new VBox(25);
        vBoxUL.setPadding(new Insets(80,80,80,80));
        vBoxUL.getChildren().addAll(tableView);
        showBooksScene = new Scene(vBoxUL,1000,2000);
        showBooksStage.setScene(showBooksScene);
        showBooksStage.show();
    }

    @Override
    public void start(Stage stage) throws Exception {


        }

    }

