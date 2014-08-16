package model;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Database {

	private ArrayList<Person> people;
	
	public Database() {
		people = new ArrayList<Person>();	
	}
	
	public void addPerson(Person person){
		people.add(person);
	}
	
	public List<Person> getPeople(){
		return people;
	}
	
	public void savetoFile(File file) throws IOException {
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		Person[] personArray = people.toArray(new Person[people.size()]);
		
		oos.writeObject(personArray);
		
		oos.close();
	}
	
	public void loadfromFile(File file) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		try {
			Person[] personArray = (Person[])ois.readObject();
			
			people.clear();
			
			people.addAll(Arrays.asList(personArray));
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		ois.close();
	}
	
}
