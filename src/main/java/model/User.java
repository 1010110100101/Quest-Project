package model;

public class User {
    private int id;
    private String username;
    private String password;

    public User(int userid, String username, String userPassword) {
        this.id = userid;
        this.username = username;
        this.password = userPassword;
    }

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
