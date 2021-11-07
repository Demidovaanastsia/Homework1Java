package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import dao.AddressService;
import entity.Address;
import entity.Child;
import entity.District;
import entity.EducationalInstitution;
import entity.Parent;

import tools.MyScanner;

public class AddScreen {
	
	Parent parent;
	Address address;
	
	public Parent createParent() {
		AddressService addresstService = new AddressService();
		parent = new Parent();
		
		System.out.print("Пожалуйста, введите полное ФИО родителя: ");
		MyScanner.scanLine();
		String nameInput = MyScanner.scanLine();
		
		while(true) {
			System.out.print("Пожалуйста, введите адрес проживания: ");
			String addressInput = MyScanner.scanLine();
			
			List<Address> addresses = addresstService.findAddressByTitle(addressInput);
	        if(!addresses.isEmpty()) {
	        	address = addresses.get(0);
	        	break;
	        } else {
	        	System.out.println("Некорректный адрес\n");
	        }
		}
		
		parent.setFullName(nameInput);
		parent.setAddress(address);
		
		return parent;
	}

	public Child createChild(Address address) {
		List<EducationalInstitution> edInsts = new ArrayList<EducationalInstitution>();
		EducationalInstitution edInst = new EducationalInstitution();
		Child child = new Child();
		
		System.out.print("Пожалуйста, введите полное ФИО ребенка: ");
		String nameInput = MyScanner.scanLine();
		
		System.out.print("Пожалуйста, введите возраст ребенка: ");
		int ageInput = MyScanner.scanInt();
		
		District district =  address.getDistrict();
		Set<Address> districtAddresses = district.getAddresses();
		
		for (Address a: districtAddresses) {
        	edInsts.addAll(a.getEducationalInstitutions());
		}
		
		while(true) {
			System.out.print("Пожалуйста, выберите учебное заведение из списка: ");
			int i = 1;
	        for (EducationalInstitution edIn: edInsts) {
				System.out.println(i + " - " + edIn.getNumber());
				i++;
			}
	        int taskNum = MyScanner.scanInt()-1;
	        if(taskNum >= 0 && taskNum <= edInsts.size()-1) {
	        	edInst = edInsts.get(taskNum);
	        	break;
	        }
		}
		
		child.setFullName(nameInput);
		child.setAge(ageInput);
		child.setEducationalInstitution(edInst);
		
		return child;
	}
}
