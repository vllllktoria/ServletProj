package db;

import model.UserModel;
import java.util.List;

public interface UsersDAO {
    UserModel get(long id);
    List<UserModel> getAll() ;
    void add(UserModel dataSet);
}
