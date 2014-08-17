package model;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Database {

	private List<Person> people;
	
	public Database() {
		people = new LinkedList<Person>();	
	}
	
	public void addPerson(Person person){
		people.add(person);
	}
	
	public void removePerson(int index) {
		people.remove(index);
	}
	
	public List<Person> getPeople(){
		return Collections.unmodifiableList(people);
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
