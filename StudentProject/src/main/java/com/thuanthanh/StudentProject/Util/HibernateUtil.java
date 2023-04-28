package com.thuanthanh.StudentProject.Util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class HibernateUtil {
    //XML based configuration
    private static SessionFactory sessionFactory;
    //Annotation based configuration
    private static SessionFactory sessionAnnotationFactory;
    //Property based configuration
    private static SessionFactory sessionJavaConfigFactory;
    private static SessionFactory buidSessionFactory(){
        try{
            // Create the SessionFactory from hibernate.cfg.xml
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            System.out.println("Hibernate Configuration loaded");
            ServiceRegistry registry =  new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            System.out.println("Hibernate serviceRegistry created");
            SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
            return sessionFactory;
        } catch (Throwable e) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + e);
            throw new ExceptionInInitializerError(e);
        }
    }
    public static SessionFactory getSessionFactory(){
        if(sessionFactory == null)
            sessionFactory = buidSessionFactory();
        return sessionFactory;
    }
}
