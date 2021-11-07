package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.Child;
import entity.Parent;
import tools.HibernateSessionFactoryUtil;

import java.util.List;

public class ParentDao {

    public Parent findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Parent.class, id);
    }
    
    public List<Parent> findByNameAndAddress(String name, String address) {
    	Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
    	String q = "select p from Parent p inner join p.addressParent where p.fullName = '"+name+"' AND p.addressParent.title = '"+address+"'";
    	List<Parent> parent = session.createQuery(q).getResultList();
        session.close();
        return parent;
    }

    public void save(Parent user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();
        session.close();
    }
    
    public void updateChild(Child child) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(child);
        tx1.commit();
        session.close();
    }

    public void update(Parent user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(user);
        tx1.commit();
        session.close();
    }

    public void delete(Parent user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(user);
        tx1.commit();
        session.close();
    }

    public Child findChildById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Child.class, id);
    }

    public List<Parent> findAll() {
    	Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
    	List<Parent> parents = session.createQuery("from Parent").getResultList();
    	session.close();
        return parents;
    }
}