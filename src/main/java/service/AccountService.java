package service;

import db.UsersDAO;
import db.UsersDAOImpl;
import model.UserModel;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AccountService {
    private static Map<UserModel, HttpSession> sessions = new HashMap<>();
    private static UsersDAO dao = new UsersDAOImpl();

    public void addUser(UserModel user){
        dao.add(user);
    }

    public void addSession(UserModel user, HttpSession session){
        if (!sessions.containsKey(user))
            sessions.put(user, session);
    }

    public void removeSession(UserModel user){
        sessions.remove(user);
    }

    public boolean checkUser(String login, String password){
        if(dao.getAll() == null)
            return false;
        for (UserModel user : dao.getAll()) {
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
