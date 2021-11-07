package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import dao.AddressService;
import dao.ParentService;
import entity.Address;
import entity.Child;
import entity.District;
import entity.EducationalInstitution;
import entity.Parent;

import tools.MyScanner;

public class ChangeScreen {
	
	public Parent checkParent() {
		
		Parent parent = new Parent();
		ParentService parentService = new ParentService();
		List<Parent> parents = new ArrayList<Parent>();
		
		while(true) {
			System.out.print("Пожалуйста, введите полное ФИО родителя, "
					+ "которого необходимо изменить: ");
			String nameInput = MyScanner.scanLine();
			
			System.out.print("Пожалуйста, введите старое место жительства: ");
			String oldAddressInput = MyScanner.scanLine();
			
			parents = parentService.findByNameAndAddress(nameInput, oldAddressInput);
			
	        if(!parents.isEmpty()) {
	        	parent = parents.get(0);
	        	break;
	        } else {
	        	System.out.println("Некорректные данные, пожалуйста, попробуйте еще раз\n");
	        }
		}
		
		return parent;
		
	}
	
	public Address checkAddress() {
		Address address = new Address();
		AddressService addresstService = new AddressService();
		
		while(true) {
			System.out.print("Пожалуйста, введите новый адрес: ");
			String newAddressInput = MyScanner.scanLine();
			
			List<Address> addresses = addresstService.findAddressByTitle(newAddressInput);
	        if(!addresses.isEmpty()) {
	        	address = addresses.get(0);
	        	break;
	        } else {
	        	System.out.println("Некорректный адрес\n");
	        }
		}
		
		return address;
		
	}
	
	public Child getChild(List<Child> children) {
		
		Child child = new Child();
		
		while(true) {
			System.out.print("Пожалуйста, выберите ребенка у которого, хотите изменить учебное заведение: \n");
			int i = 1;
	        for (Child curChild: children) {
				System.out.println(i + " - " + curChild.getFullName());
				i++;
			}
	        
	        int taskNum = MyScanner.scanInt()-1;
	        if(taskNum >= 0 && taskNum <= children.size()-1) {
	        	child = children.get(taskNum);
	        	break;
	        }
		}
		
		return child;
		
	}
	
	public EducationalInstitution getEdInst(Address parentAddress) {
		
		EducationalInstitution edInst = new EducationalInstitution();
		List<EducationalInstitution> edInsts = new ArrayList<EducationalInstitution>();
		
		District district =  parentAddress.getDistrict();
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
		
		return edInst;
		
	}
}
