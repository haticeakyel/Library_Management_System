package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.io.FileReader;
import java.util.ArrayList;

public class Librarian extends Application implements ILibrarian {
    Scene showRulesScene;

    @Override
    public void showRules() {

    }

    @Override
    public void showBooks() {

    }

    @Override
    public void start(Stage stage) throws Exception {
       /* public void showRules() {
            Label rule1 = new Label("1.Users can borrow 1 book at the same time.");
            Label rule2 = new Label("2.Borrowed books should be gaven back at 15 days.");
            Label rule3 = new Label("3.After 15 days for every single day you should pay 1 Tl to library.");
            stage = new Stage();
            VBox vBox = new VBox(25);
            vBox.setPadding(new Insets(25,25,25,25));
            vBox.getChildren().addAll(vBox,rule1,rule2, rule3);
            showRulesScene = new Scene(vBox,200,200);
            stage.setScene(showRulesScene);
            stage.show();

        } */
    }
}
