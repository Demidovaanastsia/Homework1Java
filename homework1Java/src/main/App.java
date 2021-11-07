package main;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dao.AddressService;
import dao.ParentService;
import entity.Address;
import entity.Child;
import entity.EducationalInstitution;
import entity.Parent;
import tools.MyScanner;

public class App {
	
	ParentService parentService;
	AddressService addresstService;
	AddScreen addScreen;
	ChangeScreen changeScreen;
	
	public void start() {
		while(true) {
			System.out.println("Пожалуйста, выберите нужный пункт:"
					+ "\n1 - Ввод новой информации"
					+ "\n2 - Изменение данных"
					+ "\n\n9 - Выход\n");
			
			int taskNum = MyScanner.scanInt();
			
			switch (taskNum) {
			case 1:
				addData();
				break;
			case 2:
				changeData();
				break;
			case 9:
				exitApp();
			}
		}
	}
	
	private void addData() {
		
		Set<Parent> parents = new HashSet<Parent>();
		List<Child> children = new ArrayList<Child>();
		
		parentService = new ParentService();
		addScreen = new AddScreen();
		
		System.out.println("\nДобавление нового родителя");
		
		Parent parent = addScreen.createParent();
		parents.add(parent);
		parentService.saveParent(parent);
		
		System.out.println("Добавление ребенка");
		
		children.add(addScreen.createChild(parent.getAddress()));
		
		while(true) {
			System.out.println("Хотите добавить еще одного ребенка? (Y/N)");
			MyScanner.scanLine();
			String taskBoolNum = MyScanner.scanLine();
			
			if(taskBoolNum.equals("Y")) {
				children.add(addScreen.createChild(parent.getAddress()));
			} else if(taskBoolNum.equals("N")) {
				break;
			}
		}
		parent.setChildren(children);		
		parentService.updateParent(parent);
		
	}
	
	private void changeData() {
		
		Parent parent = new Parent();
		Child child = new Child();
		Address address = new Address();
		EducationalInstitution edInst = new EducationalInstitution();
		
		List<Child> children = new ArrayList<Child>();
		addresstService = new AddressService();
		parentService = new ParentService();
		changeScreen = new ChangeScreen();
		
		System.out.println("Режим изменения");
		
		parent = changeScreen.checkParent();
		address = changeScreen.checkAddress();	
		
		children = parent.getChild();	
		
		while(true) {
			
			child = changeScreen.getChild(children);
			edInst = changeScreen.getEdInst(address);
			
			child.setEducationalInstitution(edInst);
			parentService.updateChild(child);
			
			String taskBoolNum = "";
			
			if(children.size() > 1) {
				
				while(true) {
					
					System.out.println("Изменить учебное заведение у другого ребенка? (Y/N)");
					taskBoolNum = MyScanner.scanLine();
					
					if(taskBoolNum.equals("Y") || taskBoolNum.equals("N")) {
						break;
					}
				}
				if(taskBoolNum.equals("N")) {
					break;
				}
			} else {
				break;
			}
		}
		
		parent.setAddress(address);
		parentService.updateParent(parent);
	}
	
	private void exitApp() {
		System.out.println("\nВыход из приложения.....");
		System.exit(0);
	}
}
