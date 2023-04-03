package db;

import model.UserModel;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.Collections;
import java.util.List;

public class UsersDAOImpl implements UsersDAO {

    @Override
    public UserModel get(long id) {
        return (UserModel) HibernateSessionFactory.getSessionFactory().openSession().get(UserModel.class, id);
    }

    @Override
    public List<UserModel> getAll() {
        try
        {
            return HibernateSessionFactory.getSessionFactory().openSession().createCriteria(UserModel.class).list();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    @Override
    public void add(UserModel dataSet) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(dataSet);
        tx1.commit();
        session.close();
    }
}
