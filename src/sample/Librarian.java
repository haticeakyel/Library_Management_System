package sample;

import java.io.FileReader;
import java.util.ArrayList;

public class Librarian implements ILibrarian {

    @Override
    public void showRules() {
        String rules = "1.Users can borrow 1 book at the same time.\n" +
                "2.Borrowed books should be gaven back at 15 days.\n" +
                "3.After 15 days for every single day you should pay 1 Tl to library.\n";


    }

    @Override
    public void showBooks() {

    }

}
