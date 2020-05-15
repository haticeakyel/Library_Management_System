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

public class Main extends Application {
    Scene welcomeScene, userScene, adminScene, currentUScene, newUScene, adminMethodsScene,showBooksScene,showUsersScene,addBookScene;


    @Override
    public void start(Stage stage) throws Exception {

        //
        File userFile = new File("users.txt");
        if(userFile.createNewFile()){
            System.out.println("users.txt created");
        }
        else {
            System.out.println("users.txt is already exist");
        }
        //new user oluşturuldu
        BufferedReader bufferedReader = new BufferedReader(new FileReader("users.txt"));
        String row;
        List<User> userList = new ArrayList<>();
        while ((row = bufferedReader.readLine()) != null){
            String[] data = row.split(",");
            User user = new User(data[0],data[1],data[2]);
            userList.add(user);
        }
        stage = new Stage();
        Stage userStage = new Stage();
        Stage adminStage = new Stage();

        Label welcomeLabel = new Label("---------Welcome to the Library---------");

        Button user = new Button("User Login");
        Button admin = new Button("Admin Login");
        Button exit = new Button("Exit");

        Stage finalStage = stage;
        VBox vBox = new VBox(25);
        vBox.setPadding(new Insets(0,20,20,20));
        vBox.getChildren().addAll(welcomeLabel, admin, user, exit);
        welcomeScene = new Scene(vBox, 300, 250);
        stage.setScene(welcomeScene);
        stage.setTitle("Library Management System");
        stage.show();

        //if admin
        admin.setOnAction(e -> {
            GridPane gridPane = new GridPane();
            gridPane.setPadding(new Insets(10, 10, 10, 10));
            gridPane.setVgap(8);
            gridPane.setHgap(10);
            // name label
            Label nameLabel = new Label("Username: ");
            GridPane.setConstraints(nameLabel, 0, 0);

            //name input
            TextField nameInput = new TextField();
            nameInput.setPromptText("username");
            GridPane.setConstraints(nameInput, 1, 0);

            //password label
            Label passwordLabel = new Label("Password: ");
            GridPane.setConstraints(passwordLabel, 0, 1);

            //password input
            PasswordField passwordInput = new PasswordField();
            passwordInput.setPromptText("password");
            GridPane.setConstraints(passwordInput, 1, 1);

            Button login = new Button("Log In");
            GridPane.setConstraints(login, 1, 2);
            Admin adminH = new Admin("haticeakyel", "library");
            login.setOnAction(event -> {
                if (nameInput.getText().equals(adminH.getUsername()) && (passwordInput.getText().equals(adminH.getPassword()))){
                    Stage adminMethods = new Stage();
                    VBox vBoxAdM = new VBox(30);
                    vBoxAdM.setPadding(new Insets(30,30,30,30));
                    Button showUsers = new Button("Show Users");
                    Button showBooks = new Button("Show Books");
                    Button showRules = new Button("Show Rules");

                    vBoxAdM.getChildren().addAll(showUsers, showBooks, showRules);
                    adminMethodsScene = new Scene(vBoxAdM, 180,200);
                    adminMethods.setScene(adminMethodsScene);
                    adminMethods.show();

                    showUsers.setOnAction(event1 -> {
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
                        vBoxUL.setPadding(new Insets(80,80,80,80));
                        vBoxUL.getChildren().addAll(tableView);
                        showUsersScene = new Scene(vBoxUL,300,500);
                        stageUsers.setScene(showUsersScene);
                        stageUsers.show();

                    });

                    /* showBooks.setOnAction(event1 -> {
                        Stage stage
                    }); */
                }
                else {
                    System.out.println("You entered something wrong");
                }

            });


            gridPane.getChildren().addAll(nameLabel, nameInput, passwordLabel, passwordInput, login);
            adminScene = new Scene(gridPane, 300, 200);
            adminStage.setScene(adminScene);
            adminStage.setTitle("Admin Login");
            adminStage.show();
        });

        user.setOnAction(event -> {
            Button currentUser = new Button("Current User");
            Button newUser = new Button("New User");
            Button exitUser = new Button("Exit");

            VBox vBoxU = new VBox(25);
            vBoxU.setPadding(new Insets(20,20,20,20));
            vBoxU.getChildren().addAll(currentUser, newUser, exitUser);
            userScene = new Scene(vBoxU, 200, 250);
            userStage.setScene(userScene);
            userStage.setTitle("User Sign In");
            userStage.show();

            //current user
            currentUser.setOnAction(e -> {
                Stage curUStage = new Stage();
                VBox curVBox = new VBox(25);
                GridPane curGridPane = new GridPane();
                curGridPane.setPadding(new Insets(10, 10, 10, 10));
                curGridPane.setVgap(8);
                curGridPane.setHgap(10);
                // name label
                Label curNameLabel = new Label("Username: ");
                GridPane.setConstraints(curNameLabel, 0, 0);

                //name input
                TextField curNameInput = new TextField();
                curNameInput.setPromptText("username");
                GridPane.setConstraints(curNameInput, 1, 0);

                //password label
                Label curPasswordLabel = new Label("Password: ");
                GridPane.setConstraints(curPasswordLabel, 0, 1);

                //password input
                PasswordField curPasswordInput = new PasswordField();
                curPasswordInput.setPromptText("password");
                GridPane.setConstraints(curPasswordInput, 1, 1);

                Button curLogin = new Button("Sign In");
                GridPane.setConstraints(curLogin, 1, 2);
                curGridPane.getChildren().addAll(curNameLabel, curNameInput, curPasswordLabel, curPasswordInput, curLogin);
                currentUScene = new Scene(curGridPane, 300, 250);
                curUStage.setScene(currentUScene);
                curUStage.setTitle("Current User Sign In");
                curUStage.show();

                curLogin.setOnAction(event1 -> {
                    if (userList.stream().anyMatch(o -> o.getNickname().equals(curNameInput.getText()) && o.getPassword().equals(curPasswordInput.getText()))){
                        Stage stageUser = new Stage();
                        VBox vBoxC = new VBox(25);
                        vBoxC.setPadding(new Insets(20,20,20,20));
                        Button showBooks = new Button("Show Books");
                        Button showFine = new Button("Show Fine");
                        Button changeP = new Button("Change Password");
                        Button showR = new Button("Show Rules");
                        //Scene userScene = new Scene();
                    }
                    else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Dialog");
                        alert.setContentText("Ooops, there was an error!");
                        alert.showAndWait();
                    }


                });

            });

            //new user
            newUser.setOnAction(eNew -> {
                Stage newUStage = new Stage();
                VBox newUVBox = new VBox(25);
                GridPane newGridPane = new GridPane();
                newGridPane.setPadding(new Insets(10, 10, 10, 10));
                newGridPane.setVgap(8);
                newGridPane.setHgap(10);

                //name surname label
                Label newNSLabel = new Label("Name Surname: ");
                GridPane.setConstraints(newNSLabel, 0, 0);
                //name surname input
                TextField newNSInput = new TextField();
                newNSInput.setPromptText("name surname");
                GridPane.setConstraints(newNSInput, 1, 0);

                //nickname label
                Label nickLabel = new Label("Nickname: ");
                GridPane.setConstraints(nickLabel, 0, 1);
                //nickname input
                TextField nickInput = new TextField();
                nickInput.setPromptText("nickname");
                GridPane.setConstraints(nickInput, 1, 1);

                //password label
                Label passwordLabel = new Label("Password");
                GridPane.setConstraints(passwordLabel, 0, 2);
                //password input
                PasswordField passInput = new PasswordField();
                passInput.setPromptText("password");
                GridPane.setConstraints(passInput, 1, 2);
                Label passCheckL = new Label("Password again: ");
                GridPane.setConstraints(passCheckL, 0, 3);
                PasswordField passCheckI = new PasswordField();
                passCheckI.setPromptText("password");
                GridPane.setConstraints(passCheckI, 1, 3);
                Button signUp = new Button("Sign Up");
                GridPane.setConstraints(signUp, 1,4);

                signUp.setOnAction(event1 -> {
                    try {
                        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("users.txt",true));
                        bufferedWriter.write(String.format("%s,%s,%s\n",newNSInput.getText(),nickInput.getText(),passInput.getText()));
                        bufferedWriter.close();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }

                });

                newGridPane.getChildren().addAll(newNSLabel, newNSInput, nickLabel, nickInput, passwordLabel, passInput, passCheckL, passCheckI, signUp);
                newUScene = new Scene(newGridPane, 400, 340);
                newUStage.setScene(newUScene);
                newUStage.setTitle("Sign Up Page");
                newUStage.show();

            });
            //exit in user page
            exitUser.setOnAction(event1 -> {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                userStage.close();
                finalStage.close();
            });

        });

        //if exit in welcome page
        exit.setOnAction(e -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            finalStage.close();
        });
                File bookFile = new File("books.txt");
                if (bookFile.createNewFile()){
                    System.out.println("File created.");
                }
                else {
                    System.out.println("File is already exist");
                }

                BufferedReader bufferedReaderbook = new BufferedReader(new FileReader("books.txt"));
                String rowB;
                List<Book> bookList = new ArrayList<>();
                while ((rowB = bufferedReaderbook.readLine()) != null){
                    String[] info = rowB.split(",");
                    Book book = new Book(info[0],info[1],info[2]);
                    bookList.add(book);

                }

            }


    public static void main(String[] args) {
        launch(args);
    }

}
