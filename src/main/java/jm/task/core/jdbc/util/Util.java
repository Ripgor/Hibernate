package jm.task.core.jdbc.util;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import jm.task.core.jdbc.model.User;

import java.sql.*;

public class Util {
    // реализуйте настройку соеденения с БД
//    private static final String URL = "jdbc:mysql://localhost:3306/pp_1";
//    private static final String LOGIN = "root";
//    private static final String PASSWORD = "root";
//    public static Connection getConnect() {
//
//        try {
//            return DriverManager.getConnection(URL, LOGIN, PASSWORD);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

    private static SessionFactory factory;
    public static SessionFactory getFactory() {
        if (factory == null) {
            try {
                Configuration configuration = new Configuration();
                // Настройки здесь дублировали бы XML-файл, если бы он существовал
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/pp_1?useSSL=false");
                settings.put(Environment.USER, "Ripgor");
                settings.put(Environment.PASS, "root");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");

                settings.put(Environment.SHOW_SQL, "true");

                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                configuration.setProperties(settings);

                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties())
                        .build();

                factory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return factory;
    }
}