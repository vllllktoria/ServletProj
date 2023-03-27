package db;

import model.UserModel;
import java.sql.*;
import java.util.*;
import java.sql.DriverManager;

public class DBHandler {
    private Connection connection;
    private static final String user = "root";
    private static final String password = "123";
    private static final String url = "jdbc:mysql://localhost/users";
    public DBHandler(){
        try {
            Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
            DriverManager.registerDriver(driver);
            this.connection = DriverManager.getConnection(url, user, password);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public List<UserModel> getAllUsers(){
        try(Statement statement = this.connection.createStatement()){
            ArrayList<UserModel> users = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("SELECT login, password, email FROM users");
            while (resultSet.next()){
                users.add(new UserModel(resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("email")));
            }
            if (users.isEmpty())
                return Collections.emptyList();
            return users;
        } catch (SQLException e){
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public void addUser(UserModel user) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO users(`login`, `password`, `email`) VALUES(?,?,?)")) {
            statement.setObject(1, user.getLogin());
            statement.setObject(2, user.getPassword());
            statement.setObject(3, user.getEmail());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
