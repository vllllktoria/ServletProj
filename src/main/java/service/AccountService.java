package service;

import db.DBHandler;
import model.UserModel;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AccountService {
    //private static Map<String, UserModel> logins = new HashMap<>();
    private static Map<UserModel, HttpSession> sessions = new HashMap<>();
    DBHandler db = new DBHandler();

    public void addUser(UserModel user){
        db.addUser(user);
    }

    /*public UserModel getUserByLogin(String login) {
        return logins.get(login);
    }*/

//    public HttpSession getUserBySession(UserModel session) {
//        return sessions.get(session);
//    }
    public void addSession(UserModel user, HttpSession session){
        if (!sessions.containsKey(user))
            sessions.put(user, session);
    }

    public void removeSession(UserModel user){
        sessions.remove(user);
    }

    public boolean checkUser(String login, String password){
        for (UserModel user : db.getAllUsers()) {
            if (user.getLogin().equals(login)) {
                return user.getPassword().equals(password);
            }
        }
        return false;
    }

    public UserModel getBySession(String sessionId){
        for (UserModel user : sessions.keySet()) {
            if (Objects.equals(sessions.get(user).getId(), sessionId)){
                return user;
            }
        }
        return null;
    }

    public boolean hasActiveSession() {
        return sessions.isEmpty();
    }
}
