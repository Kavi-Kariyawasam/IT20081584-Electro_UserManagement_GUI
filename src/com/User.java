package com;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/electricityproject?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertUser(String Name, String ACC_Num, String Email, String Password, String Phone) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into user1(`UID`,`Name`,`ACC_Num`,`Email`,`Password`,`Phone`)" + " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, Name);
			preparedStmt.setString(3, ACC_Num);
			preparedStmt.setString(4, Email);
			preparedStmt.setString(5, Password);
			preparedStmt.setString(6, Phone);
			// execute the statement
			preparedStmt.execute();
			con.close();
			/** ---------------------- **/
			//String newUser = readUser();
			 //output = "{\"status\":\"success\", \"data\": \"" + newUser + "\"}";
				/** ---------------------- **/
			output = "Inserted successfully";
		} catch (Exception e) {
			/** ---------------------- **/
			//output = "{\"status\":\"error\", \"data\": \"Error while inserting the user details.\"}";
			/** ---------------------- **/
			output = "Error while inserting the user.";
			System.err.println(e.getMessage());			
			/**-------------------------------------------**/
			//System.out.println(e.getMessage());
			//System.out.println(e);
			//e.printStackTrace();
			/**-------------------------------------------**/
		}
		return output;
	}

	public String readUser() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>User ID</th><th>User Name</th><th>Account Number</th><th>Email</th><th>Password</th><th>Phone No</th><th>Update</th><th>Remove</th></tr>";
			String query = "select * from user1";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String UID = Integer.toString(rs.getInt("UID"));
				String Name = rs.getString("Name");
				String ACC_Num = rs.getString("ACC_Num");
				String Email = rs.getString("Email");
				String Password = rs.getString("Password");
				String Phone = rs.getString("Phone");

				// Add into the html table
				output += "<tr><td>" + UID + "</td>";
				output += "<td>" + Name + "</td>";
				output += "<td>" + ACC_Num + "</td>";
				output += "<td>" + Email + "</td>";
				output += "<td>" + Password + "</td>";
				output += "<td>" + Phone + "</td>";
				
				// buttons
				// output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
				// + "<td><form method='post' action='users.jsp'>"
				// + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
				// + "<input name='UID' type='hidden' value='" + UID  
				// + "'>" + "</form></td></tr>";
				output += "<td><input name='btnUpdate' type='button' value='Update' "
						+ "class='btnUpdate btn btn-info' data-uid='" + UID + "'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' "
						+ "class='btnRemove btn btn-danger' data-uid='" + UID + "'></td></tr>";
				
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the user.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateUser(String UID, String Name, String ACC_Num, String Email, String Password, String Phone) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE user1 SET Name=?,ACC_Num=?,Email=?,Password=?,Phone=?" + "WHERE UID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, Name);
			preparedStmt.setString(2, ACC_Num);
			preparedStmt.setString(3, Email);
			preparedStmt.setString(4, Password);
			preparedStmt.setString(5, Phone);
			preparedStmt.setInt(6, Integer.parseInt(UID));

			// execute the statement
			preparedStmt.execute();
			con.close();
			
			 /**-------------------------------------**/
			//output = "Updated successfully"; 
			 String newUser = readUser(); 
			 output = "{\"status\":\"success\", \"data\": \"" + newUser + "\"}"; 

			//output = "Updated successfully";
		} catch (Exception e) {
			/**----------------------------------------------------------------------------------------------**/
			output = "{\"status\":\"error\", \"data\": \"Error while updating the user details.\"}";
			//output = "Error while updating the user.";
			System.err.println(e.getMessage());
			/**----------------------------**/
			//System.out.println(e);
		}

		return output;
	}

	public String deleteUser(String UID) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from user1 where UID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(UID));

			// execute the statement
			preparedStmt.execute();
			con.close();
			/**--------------------------------------------------------------------**/
//			String newBranch = readBranch(); 
//			 output = "{\"status\":\"success\", \"data\": \"" + newBranch + "\"}"; 
			

			output = "Deleted successfully";
		} catch (Exception e) {
			/** ---------------------------**/
//			 output = "{\"status\":\"error\", \"data\": \"Error while deleting the branch details.\"}";
			output = "Error while deleting the user.";
			System.err.println(e.getMessage());
			/**--------------------------------------------------**/
//			System.out.println(e);
		}

		return output;
	}

}
