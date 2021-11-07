package dao;

import java.util.List;

import entity.Address;
import tools.HibernateSessionFactoryUtil;

public class AddressDao {
	public Address findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Address.class, id);
    }
	
	public List<Address> findAddressByTitle(String title) {
        List<Address> address = HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("from Address a where a.title = '"+title+"'").getResultList();
        return address;
    }
}
