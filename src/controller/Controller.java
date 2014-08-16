package controller;

import enums.AgeCategory;
import enums.EmploymentCategory;
import enums.Gender;
import gui.FormEvent;
import java.util.List;
import model.Database;
import model.Person;

public class Controller {

	Database db = new Database();
	
	public List<Person> getPeople() {
		return db.getPeople();
	}
	
	public void addPerson(FormEvent e) {
		String name = e.getName();
		String occupation = e.getOccupation();
		AgeCategory ageCategory = e.getAgeCategory();
		EmploymentCategory employmentCategory = e.getEmploymentCategory();
		boolean isUSCitizen = e.isUsCitizen();
		String taxID = e.getTaxID();
		Gender gender = e.getGender();
		
		Person person = new Person(name, occupation, ageCategory,employmentCategory, taxID, isUSCitizen, gender);
		db.addPerson(person);
	}
}