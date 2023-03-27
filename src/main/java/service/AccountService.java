package service;

import model.UserModel;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AccountService {
    private static Map<String, UserModel> logins = new HashMap<>();
    private static Map<UserModel, HttpSession> sessions = new HashMap<>();

    public void addUser(UserModel user){
        logins.put(user.getLogin(), user);
    }

    public void addSession(UserModel user, HttpSession session){
        if (!sessions.containsKey(user))
            sessions.put(user, session);
    }

    public void removeSession(UserModel user){
        sessions.remove(user);
    }

    public boolean checkUser(String login, String password){
        UserModel user = logins.get(login);
        return user != null && user.getPassword().equals(password);
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
