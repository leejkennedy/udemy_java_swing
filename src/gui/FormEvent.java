package gui;
import java.util.EventObject;

import enums.AgeCategory;
import enums.EmploymentCategory;
import enums.Gender;

public class FormEvent extends EventObject {
	private static final long serialVersionUID = 4343368301562012677L;
	private String name;
	private String occupation;
	private AgeCategory ageCategory;
	private EmploymentCategory employmentCategory;
	private String taxID;
	private boolean usCitizen;
	private Gender gender;
	
	public FormEvent(Object source) {
		super(source);
	}

	public FormEvent(Object source, String name, String occupation,
			AgeCategory ageCategory, EmploymentCategory employmentCategory,
			boolean usCitizen, String taxID, Gender gender) {
		super(source);
		this.setName(name);
		this.setOccupation(occupation);
		this.setAgeCategory(ageCategory);
		this.setEmploymentCategory(employmentCategory);
		this.setTaxID(taxID);
		this.setUSCitizen(usCitizen);
		this.setGender(gender);
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

	public void setAgeCategory(AgeCategory agecategory) {
		this.ageCategory = agecategory;
	}
	
	public AgeCategory getAgeCategory() {
		return ageCategory;
	}

	public EmploymentCategory getEmploymentCategory() {
		return employmentCategory;
	}

	public void setEmploymentCategory(EmploymentCategory employmentcategory) {
		this.employmentCategory = employmentcategory;
	}
	
	public String getTaxID() {
		return taxID;
	}

	public void setTaxID(String taxID) {
		this.taxID = taxID;
	}

	public boolean isUsCitizen() {
		return usCitizen;
	}

	private void setUSCitizen(boolean usCitizen) {
		this.usCitizen = usCitizen;		
	}
	
	public Gender getGender() {
		return gender;
	}
	
	public void setGender(Gender gender) {
		this.gender = gender;
	}

}