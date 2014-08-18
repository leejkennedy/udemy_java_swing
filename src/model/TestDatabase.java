package model;

import java.sql.*;

public class TestDatabase {
	public static void main(String[] args) {
		System.out.println("Running db test");
		
		Database db = new Database();
		try {
			db.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			db.save();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.disconnect();
	};
}
