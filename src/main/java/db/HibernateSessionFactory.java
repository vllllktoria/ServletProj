package db;

import model.UserModel;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import java.sql.Driver;
import java.sql.DriverManager;

public class HibernateSessionFactory {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactory() {}

    public static SessionFactory getSessionFactory()
    {
        if(sessionFactory == null){
            try {
                Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
                DriverManager.registerDriver(driver);
                Configuration configuration = new Configuration();
                configuration
                        .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                        .setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver")
                        .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/users")
                        .setProperty("hibernate.connection.username", "root")
                        .setProperty("hibernate.connection.password", "123")
                        .setProperty("hibernate.show_sql", "false")
                        .setProperty("hibernate.hbm2dll.auto", "update");
                configuration.addAnnotatedClass(UserModel.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
