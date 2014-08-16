package model;

import java.io.Serializable;

import enums.AgeCategory;
import enums.EmploymentCategory;
import enums.Gender;

public class Person implements Serializable {

	private static final long serialVersionUID = -4664113984338191034L;

	private static int count = 0;

	private int id;
	private String name;
	private String occupation;
	private AgeCategory ageCategory;
	private EmploymentCategory employmentCategory;
	private String taxID;
	private boolean usCitizen;
	private Gender gender;

	public Person(String name, String occupation, AgeCategory ageCategory,
			EmploymentCategory employmentCategory, String taxID,
			boolean usCitizen, Gender gender) {
		
		this.name = name;
		this.occupation = occupation;
		this.setAgeCategory(ageCategory);
		this.employmentCategory = employmentCategory;
		this.taxID = taxID;
		this.usCitizen = usCitizen;
		this.gender = gender;
		this.id = count;
		count++;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public EmploymentCategory getEmploymentCategory() {
		return employmentCategory;
	}

	public void setEmploymentCategory(EmploymentCategory employmentCategory) {
		this.employmentCategory = employmentCategory;
	}

	public String getTaxID() {
		return taxID;
	}

	public void setTaxID(String taxID) {
		this.taxID = taxID;
	}

	public void setUsCitizen(boolean usCitizen) {
		this.usCitizen = usCitizen;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public AgeCategory getAgeCategory() {
		return ageCategory;
	}

	public void setAgeCategory(AgeCategory ageCategory) {
		this.ageCategory = ageCategory;
	}

	public Object isUSCitizen() {
		return usCitizen;
	}

}
