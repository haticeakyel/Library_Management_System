package sample;

import java.io.*;

public class User  implements Serializable, IUser,ILibrarian {
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
    public void showRules() {

    }

    @Override
    public void showBooks() {

    }


    @Override
    public void borrowBook() {

    }

    @Override
    public void showFine() {

    }

    @Override
    public void changePassword() {

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
