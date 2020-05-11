package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main extends Application {
    Scene welcomeScene, userScene, adminScene, currentUScene, newUScene;


    @Override
    public void start(Stage stage) throws Exception {
        File userFile = new File("users.txt");
        if(userFile.createNewFile()){
            System.out.println("users.txt olustu.");
        }
        else {
            System.out.println("already exist");
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

        Label welcomeLabel = new Label("-----------Welcome to the Library------------");

        Button user = new Button("User Login");
        Button admin = new Button("Admin Login");
        Button exit = new Button("Exit");

        Stage finalStage = stage;
        VBox vBox = new VBox(25);
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
                    System.out.println("Welcome Hatice!");
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
            vBoxU.getChildren().addAll(currentUser, newUser, exitUser);
            userScene = new Scene(vBoxU, 300, 250);
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
                System.out.println("Program is closing...");
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
            System.out.println("Program is closing...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            finalStage.close();
        });


    }

    public static void main(String[] args) {


        launch(args);
    }

}
