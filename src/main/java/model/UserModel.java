package model;

public class UserModel {
    private final String login;
    private final String password;
    private  String email;
    private final String homeDirectory;

    public UserModel(String login, String password) {
        this.login = login;
        this.password = password;
        homeDirectory = "C:/javaUsers/" + login;
    }
    public UserModel(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
        homeDirectory = "C:/javaUsers/" + login;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getHomeDirectory() {
        return homeDirectory;
    }
}

