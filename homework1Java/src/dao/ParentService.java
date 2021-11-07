package dao;

import java.util.List;

import entity.Child;
import entity.Parent;

public class ParentService {
	private ParentDao parentsDao = new ParentDao();

    public ParentService() {
    }

    public Parent findParent(int id) {
        return parentsDao.findById(id);
    }
    
    public List<Parent> findByNameAndAddress(String name, String address) {
        return parentsDao.findByNameAndAddress(name, address);
    }

    public void saveParent(Parent parent) {
    	parentsDao.save(parent);
    }
    
    public void updateChild(Child child) {
    	parentsDao.updateChild(child);
    }

    public void deleteParent(Parent parent) {
    	parentsDao.delete(parent);
    }

    public void updateParent(Parent parent) {
    	parentsDao.update(parent);
    }

    public List<Parent> findAllParents() {
        return parentsDao.findAll();
    }

    public Child findChildById(int id) {
        return parentsDao.findChildById(id);
    }
}
