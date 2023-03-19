package service;

import model.UserModel;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Objects;

public class AccountService {
    private static boolean isSomeoneLogin = false;
    private static HashMap<String, String> users = new HashMap<>();
    private static HashMap<UserModel, HttpSession> sessions = new HashMap<>();

    public static void addUsers(UserModel user, HttpSession session){
        users.put(user.getLogin(), user.getPassword());
        addSession(user, session);
    }

    public static boolean checkUser(UserModel user) {
        if(!users.containsKey(user.getLogin())){
            return false;
        }
        return Objects.equals(users.get(user.getLogin()), user.getPassword());
    }

    public static void addSession(UserModel user, HttpSession session){
        if(!sessions.containsKey(user))
            sessions.put(user, session);
    }

    public static void logOut(UserModel user) {

        sessions.remove(user);
    }

    public static UserModel getById(String id){
        for(UserModel user : sessions.keySet()){
            if(Objects.equals(sessions.get(user).getId(), id)){
                return user;
            }
        }
        return null;
    }

    public static boolean isSomeoneLogin() {
        return isSomeoneLogin;
    }

    public static void setSomeoneLogin(boolean someoneLogin) {
        isSomeoneLogin = someoneLogin;
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
