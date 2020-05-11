package sample;

import java.io.File;
import java.io.Serializable;

public class Book implements Serializable {
    private String name;
    private String author;
    private String publisher;
    private transient int libID;

    public Book (String name, String author, String publisher, int libID){
        this.setName(name);
        this.setAuthor(author);
        this.setPublisher(publisher);
        this.setLibID(libID);
    }

    public File fileB = new File("books.txt");



    @Override
    public String toString() {
        return super.toString();
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

    public int getLibID() {
        return libID;
    }

    public void setLibID(int libID) {
        this.libID = libID;
    }
}
