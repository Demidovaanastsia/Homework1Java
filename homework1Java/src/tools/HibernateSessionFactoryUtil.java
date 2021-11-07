package tools;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import entity.Parent;
import entity.Child;
import entity.District;
import entity.Address;
import entity.EducationalInstitution;

public class HibernateSessionFactoryUtil {
	
	private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                sessionFactory = new Configuration()
                        .configure("hibernate.cfg.xml")
                        .addAnnotatedClass(Parent.class)
                        .addAnnotatedClass(Child.class)
                        .addAnnotatedClass(District.class)
                        .addAnnotatedClass(Address.class)
                        .addAnnotatedClass(EducationalInstitution.class)
                        .buildSessionFactory();

            } catch (Exception e) {
                System.out.println(e.getStackTrace());
            }
        }
        return sessionFactory;
    }
}
