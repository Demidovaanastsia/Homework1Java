package dao;

import java.util.List;

import entity.Address;

public class AddressService {
	private AddressDao addressesDao = new AddressDao();
	
	public AddressService() {
	    
    }
	
	public List<Address> findAddressByTitle(String title) {
        return addressesDao.findAddressByTitle(title);
    }
}
