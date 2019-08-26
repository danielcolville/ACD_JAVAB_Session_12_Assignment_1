package books;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Singleton Design Pattern Implementation

public class DBConnection {

	static DBConnection con;
	private static Connection connection;

	private DBConnection(String url,String user,String pass) {
		// TODO Auto-generated constructor stub

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager
					.getConnection(url,user,pass);

		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public static Connection getDBInstance(){

		if(con==null){
			con = new DBConnection(DbConstants.url,DbConstants.userName,DbConstants.password);
		}
		return connection;
	}
	
	public static void closeConnection(){
		
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}