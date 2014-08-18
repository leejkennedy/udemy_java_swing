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
import java.sql.*;

public class Database {

	private List<Person> people;
	private Connection con;
	
	public Database() {
		people = new LinkedList<Person>();	
	}
	
	public void connect() throws Exception {
		
		if (con != null) return;			
	
		String user = "root";
		String password = "k085711233";
		String url = "jdbc://mysql://localhost:3306/javaswing";
	
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new Exception("Driver not found");
		}
		con = DriverManager.getConnection(url, user, password);
		
		System.out.println("Connection established.");
	}
	
	public void disconnect() {
		if (con != null){
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("Can't close connection");
			}
		}
	}
	
	public void save() throws SQLException {
		String checkSql = "SELECT Count(*) AS COUNT FROM T_PERSONS WHERE ID=?";
		
		PreparedStatement checkStatement = con.prepareStatement(checkSql);
		
		for (Person person: people) {
			int id = person.getId();
			
			checkStatement.setInt(1, id);
			ResultSet checkResult = checkStatement.executeQuery(checkSql);

			checkResult.next();
			
			int count = checkResult.getInt(1);
			
			System.out.println("Count for person with id " + id + " is " + count);
			
		}
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
