package sample;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.*;

public class Book  {
    private String name;
    private String author;
    private String publisher;

    public Book (String name, String author, String publisher){
        this.setName(name);
        this.setAuthor(author);
        this.setPublisher(publisher);

    }

    File fileB = new File("books.txt");
    FileReader frB;
    {
        try {
            frB = new FileReader(fileB);
            char b = (char) frB.read();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("IO Exception");
        }
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

}
