package gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Person;

public class PersonTableModel extends AbstractTableModel {
	private static final long serialVersionUID = -6508897732445332520L;

	private List<Person> db;
	private String[] columnNames = {"ID","Name","Occupation","Age","Employment","US Citizen","Tax ID","Gender"};

	public PersonTableModel(){
	}
	
	public void setData(List<Person> db){
		this.db = db;
	}

	public String getColumnName(int column) {
		return columnNames[column];
	}
		
	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return db.size();
	}

	public Object getValueAt(int row, int column) {
		Person person = db.get(row);
		switch (column){
		case 0:
			return person.getId();
		case 1:
			return person.getName();
		case 2: 
			return person.getOccupation();
		case 3:
			return person.getAgeCategory();
		case 4:
			return person.getEmploymentCategory();
		case 5: 
			return person.getTaxID();
		case 6:
			return person.isUSCitizen();
		case 7:
			return person.getGender();
		}
		return null;
	}
}
