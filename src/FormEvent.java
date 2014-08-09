import java.util.EventObject;

public class FormEvent extends EventObject {
	private static final long serialVersionUID = 3522737520641947059L;
	private String name;
	private String occupation;
	private int ageCategoryID;
	private String employmentCategory;
	private String taxID;
	private boolean usCitizen;
	private String gender;

	public FormEvent(Object source, String name, String occupation,
			int ageCategoryID, String employmentCategory,String taxID, Boolean usCitizen, String gender) {
		super(source);
		this.name = name;
		this.occupation = occupation;
		this.setAgeCategoryID(ageCategoryID);
		this.employmentCategory = employmentCategory;
		this.taxID = taxID;
		this.usCitizen = usCitizen;
		this.gender = gender;
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

	public int getAgeCategoryID() {
		return ageCategoryID;
	}

	public void setAgeCategoryID(int ageCategoryID) {
		this.ageCategoryID = ageCategoryID;
	}

	public String getEmploymentCategory() {
		return employmentCategory;
	}

	public void setEmploymentCategory(String employmentCategory) {
		this.employmentCategory = employmentCategory;
	}

	public boolean isUsCitizen() {
		return usCitizen;
	}

	public String getTaxID() {
		return taxID;
	}

	public String getGender() {
		return gender;
	}

}
