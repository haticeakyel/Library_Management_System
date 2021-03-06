package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.*;
import java.util.*;


public class Main extends Application {
    Scene welcomeScene, userScene, adminScene, currentUScene, newUScene, adminMethodsScene,curUSignScene, pScene;

    @Override
    public void start(Stage stage) throws Exception {
        File userFile = new File("users.txt");
        if(userFile.createNewFile()){
            System.out.println("users.txt created");
        }
        else {
            System.out.println("users.txt is already exist");
        }

        // buffered reader for users.txt
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
            gridPane.setAlignment(Pos.CENTER);

            Label nameLabel = new Label("Username: ");
            GridPane.setConstraints(nameLabel, 0, 0);
            TextField nameInput = new TextField();
            nameInput.setPromptText("username");
            GridPane.setConstraints(nameInput, 1, 0);
            Label passwordLabel = new Label("Password: ");
            GridPane.setConstraints(passwordLabel, 0, 1);
            PasswordField passwordInput = new PasswordField();
            passwordInput.setPromptText("password");
            GridPane.setConstraints(passwordInput, 1, 1);

            Button login = new Button("Log In");
            GridPane.setConstraints(login, 1, 2);
            Admin adminH = null;
            try {
                adminH = new Admin("haticeakyel", "library");
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            Admin finalAdminH = adminH;
            login.setOnAction(event -> {
                if (nameInput.getText().equals(finalAdminH.getUsername()) && (passwordInput.getText().equals(finalAdminH.getPassword()))){
                    Stage adminMethods = new Stage();
                    VBox vBoxAdM = new VBox(30);
                    vBoxAdM.setPadding(new Insets(30,30,30,30));
                    Button showUsers = new Button("Show Users");
                    Button showBooks = new Button("Show Books");
                    Button addBook = new Button("Add Book");

                    vBoxAdM.getChildren().addAll(showUsers, showBooks, addBook);
                    vBoxAdM.setAlignment(Pos.CENTER);
                    adminMethodsScene = new Scene(vBoxAdM, 180,200);
                    adminMethods.setScene(adminMethodsScene);
                    adminMethods.show();

                    showUsers.setOnAction(event1 -> {
                        try {
                            finalAdminH.showUsers();
                        } catch (IOException ex) {
                            System.out.println("Io exception");
                        }

                    });
                        addBook.setOnAction(event1 -> {
                            finalAdminH.addBook();

                    });
                        showBooks.setOnAction(event1 -> {
                            finalAdminH.showBooks();
                        });
                }
                else {
                    AlertMaker alert = new AlertMaker("Error Dialog","Please try again!","You entered something wrong!");
                    alert.makeAlert();
                }
            });

            gridPane.getChildren().addAll(nameLabel, nameInput, passwordLabel, passwordInput, login);
            adminScene = new Scene(gridPane, 300, 200);
            adminStage.setScene(adminScene);
            adminStage.setTitle("Admin Login");
            adminStage.show();
        });

        String finalRow = row;
        user.setOnAction(event -> {
            Button currentUser = new Button("Current User");
            Button newUser = new Button("New User");
            Button exitUser = new Button("Exit");

            VBox vBoxU = new VBox(25);
            vBoxU.setPadding(new Insets(20,20,20,20));
            vBoxU.getChildren().addAll(currentUser, newUser, exitUser);
            userScene = new Scene(vBoxU, 200, 250);
            vBoxU.setAlignment(Pos.CENTER);
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

                Label curNameLabel = new Label("Username: ");
                GridPane.setConstraints(curNameLabel, 0, 0);

                TextField curNameInput = new TextField();
                curNameInput.setPromptText("username");
                GridPane.setConstraints(curNameInput, 1, 0);

                Label curPasswordLabel = new Label("Password: ");
                GridPane.setConstraints(curPasswordLabel, 0, 1);

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
                    String currentName;
                    String currentPassword;
                    currentName= curNameInput.getText();
                    currentPassword = curPasswordInput.getText();
                    User currentUserEnter = userList.stream().filter(o -> o.getNickname().equals(currentName) && o.getPassword().equals(currentPassword)).findFirst().orElse(null);
                    if (currentUserEnter != null){
                        Stage stageUser = new Stage();
                        VBox vBox1 = new VBox(25);
                        vBox1.setPadding(new Insets(20,20,20,20));
                        Button showBooksC = new Button("Show Books");
                        Button changeP = new Button("Change Password");
                        vBox1.getChildren().addAll(showBooksC, changeP);
                        curUSignScene = new Scene(vBox1, 200,200);
                        vBox1.setAlignment(Pos.CENTER);
                        stageUser.setScene(curUSignScene);
                        stageUser.show();

                        changeP.setOnAction(event2 -> {
                            Stage stageP = new Stage();
                            GridPane gridPane = new GridPane();
                            gridPane.setPadding(new Insets(10, 10, 10, 10));
                            gridPane.setVgap(8);
                            gridPane.setHgap(10);

                            Label passwordLabel = new Label(" New Password: ");
                            gridPane.setConstraints(passwordLabel, 0, 0);
                            PasswordField passInput = new PasswordField();
                            passInput.setPromptText(" new password");
                            gridPane.setConstraints(passInput, 1, 0);
                            Label passCheckL = new Label("New Password again: ");
                            gridPane.setConstraints(passCheckL, 0, 1);
                            PasswordField passCheckI = new PasswordField();
                            passCheckI.setPromptText(" new password");
                            gridPane.setConstraints(passCheckI, 1, 1);
                            Button edit = new Button("Change Password");
                            gridPane.setConstraints(edit,1,2);
                            gridPane.getChildren().addAll(passwordLabel,passInput,passCheckL,passCheckI,edit);
                            pScene = new Scene(gridPane,400,400);
                            stageP.setScene(pScene);
                            stageP.show();
                            edit.setOnAction(event3 -> {
                                if (passInput.getText().equals(passCheckI.getText())){
                                    try {
                                        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(userFile));
                                        Iterator iterator = userList.iterator();
                                        while (iterator.hasNext()){
                                            User user1 = (User) iterator.next();
                                            if (user1.getNickname().equals(currentUserEnter.getNickname())){
                                                bufferedWriter.write(String.format("%s,%s,%s\n",currentUserEnter.getName(),currentUserEnter.getNickname(),passCheckI.getText()));
                                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                                alert.setTitle("Password changed");
                                                alert.setHeaderText("Mission completed");
                                                alert.setContentText("Password has changed!");
                                                alert.show();

                                            }
                                            else {
                                                bufferedWriter.write(String.format("%s,%s,%s\n",user1.getName(),user1.getNickname(),user1.getPassword()));
                                            }
                                        }
                                        bufferedWriter.close();
                                    } catch (IOException ex) {
                                        ex.printStackTrace();
                                    }
                                }
                                else{
                                    AlertMaker alert = new AlertMaker("Error Dialogue", "Passwords don't match!","Please write same passwords!");
                                    alert.makeAlert();
                                }
                            });
                        });

                        showBooksC.setOnAction(event2 -> {
                            currentUserEnter.showBooks();
                        });
                    }

                    else {
                        AlertMaker alert = new AlertMaker("Error Dialog","Please try again!","You entered something wrong!");
                        alert.makeAlert();
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

                Label newNSLabel = new Label("Name Surname: ");
                GridPane.setConstraints(newNSLabel, 0, 0);
                TextField newNSInput = new TextField();
                newNSInput.setPromptText("name surname");
                GridPane.setConstraints(newNSInput, 1, 0);
                Label nickLabel = new Label("Nickname: ");
                GridPane.setConstraints(nickLabel, 0, 1);
                TextField nickInput = new TextField();
                nickInput.setPromptText("nickname");
                GridPane.setConstraints(nickInput, 1, 1);
                Label passwordLabel = new Label("Password");
                GridPane.setConstraints(passwordLabel, 0, 2);
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
                    if (passInput.getText().equals(passCheckI.getText())){

                        if (userList.stream().anyMatch(o -> o.getNickname().equals(nickInput.getText()))){
                            AlertMaker alert = new AlertMaker("Error Dialog","This nickname is already exist!","Please write another nickname.");
                            alert.makeAlert();
                        }
                        else {

                    try {
                        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("users.txt",true));
                        bufferedWriter.write(String.format("%s,%s,%s\n",newNSInput.getText(),nickInput.getText(),passInput.getText()));
                        bufferedWriter.close();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }}
                    }
                    else {
                        AlertMaker alert = new AlertMaker("Error Dialog","Passwords don't match!","Please write same passwords!");
                        alert.makeAlert();
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
                userStage.close();
                finalStage.close();
            });

        });

        //if exit in welcome page
        exit.setOnAction(e -> {
            finalStage.close();
        });
            }

    public static void main(String[] args) {
        launch(args);
    }

}

