package books;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class BookSQL {

	public static void main(String[] args) {
		Connection con=DBConnection.getDBInstance();
		Scanner sc=new Scanner(System.in);
		BookSQL.updateBook(con,"new", "title", "a");
		String query="select * from `onlinebooks`.books;";
		ResultSet rs=DbUtility.executeQuery(con, query);
		DbUtility.printEntireRS(rs);
		sc.close();
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static int updateBook(Connection con,String title,String field,String newV) {
		try {
			System.out.println(title+" "+field+" "+newV);
			PreparedStatement stmt=con.prepareStatement("update `onlinebooks`.books set "+field+ " =? where title=?");
			
			//System.out.print("What is title of book you would like to change?");
			stmt.setString(2, title);
			stmt.setString(1, newV);
			System.out.println(stmt);
			int ret=stmt.executeUpdate();
			return ret;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	public static int insertNewBook(Connection con,String title,String auth,String pub,int year,double pr) {
		try {
			PreparedStatement stmt=con.prepareStatement("insert "
					+ "into `onlinebooks`.books(title,authors,publisher,publication_year,price) "
					+ "values('"+title+"','"+auth+"','"+pub+"','"+year+"','"+pr+"');");
			int ret=stmt.executeUpdate();
			return ret;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	public static int deleteBook(Connection con,String title) {
		try {
			PreparedStatement stmt=con.prepareStatement("delete from `onlinebooks`.books where title=?");
			stmt.setString(1, title);
			int ret=stmt.executeUpdate();
			return ret;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
