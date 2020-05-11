package sample;

public class Admin implements IAdmin,ILibrarian{

    private String username;
    private String password ;

    public Admin (String username, String password){
        this.setUsername(username);
        this.setPassword(password);
    }

    @Override
    public void showUsers() {

    }

    @Override
    public void deleteUser() {

    }

    @Override
    public void addBook() {

    }



    @Override
    public void showRules() {
    }

    @Override
    public void showBooks() {

    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
