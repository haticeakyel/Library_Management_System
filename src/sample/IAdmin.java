package sample;

import java.io.IOException;

public interface IAdmin extends ILibrarian{
    void showUsers() throws IOException;
    void addBook();
}
