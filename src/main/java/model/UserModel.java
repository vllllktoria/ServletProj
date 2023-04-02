package model;
import javax.persistence.*;

@Entity
@Table(name = "usersTable")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "homeDirectory")
    private String homeDirectory;

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

    public UserModel() {
    }
    public long getId() {
        return id;
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

    public void setId(long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setHomeDirectory(String homeDirectory) {
        this.homeDirectory = homeDirectory;
    }
}

