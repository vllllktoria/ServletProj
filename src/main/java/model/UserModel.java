package model;

public class UserModel {
    private final String login;
    private final String password;
    private final String homeDirectory;

    public UserModel(String login, String password) {
        this.login = login;
        this.password = password;
        this.homeDirectory = "C:\\\\javaUsers\\\\" + login;
    }

    public String getLogin() {

        return login;
    }

    public String getPassword() {

        return password;
    }

    public String getHomeDirectory() {
        return homeDirectory;
    }
}
