package books;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class BookSQL {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con=DBConnection.getDBInstance();
		Scanner sc=new Scanner(System.in);
		/*
		System.out.println("Which field to change");
		String a=sc.next();
		int row=0;
		switch(a) {
		case "title":
			row=BookSQL.updateBook(con,"title",sc);
			break;
		case "authors":
			row=BookSQL.updateBook(con,"authors",sc);
			break;
		case "publisher":
			row=BookSQL.updateBook(con,"publisher",sc);
			break;
		case "publication_year":
			row=BookSQL.updateBook(con,"publication_year",sc);
			break;
		case "price":
			row=BookSQL.updateBook(con,"price",sc);
			break;
		default:
			System.out.println(a+" is not a valid table to update");
		}*/
		BookSQL.insertNewBook(con, sc);
		BookSQL.deleteBook(con, sc);
		//int row=BookSQL.updateBook(con,"title",sc);
		//System.out.println(row);
		String query="select * from `onlinebooks`.books;";
		ResultSet rs=DbUtility.executeQuery(con, query);
		/*if(rs!= null ) {
			try {
				while(rs.next()) {
					System.out.print("ID: "+ rs.getInt(1));
					System.out.print("title: "+rs.getString(2)+" authors"+rs.getString(3));
					System.out.println("publisher "+rs.getString(4)+"publication year"+rs.getString(5)+"price"+rs.getDouble(6));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		DbUtility.printEntireRS(rs);
		sc.close();
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static int updateBook(Connection con,String field,Scanner sc) {
		try {
			PreparedStatement stmt=con.prepareStatement("update `onlinebooks`.books set "+field+ " =? where title=?");
			
			System.out.print("What is title of book you would like to change?");
			String nv="";
			String wh="";
			if(sc.hasNext()) {
				sc.nextLine();
				wh=sc.nextLine();
			}
			System.out.println("What do you want to change the field to?");
			if(sc.hasNext()) {
				nv=sc.nextLine();
			}
			System.out.println(wh+" "+nv);
			
			stmt.setString(2, wh);
			stmt.setString(1, nv);
		
			int ret=stmt.executeUpdate();
			return ret;
			//DbUtility.executeUpdate(con, query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	public static int insertNewBook(Connection con,Scanner sc) {
		try {
			PreparedStatement stmt=con.prepareStatement("insert into `onlinebooks`.books(title,authors,publisher,publication_year,price) values('The Stranger','Camus','Who knows',1907,9.99)");
			int ret=stmt.executeUpdate();
			return ret;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	public static int deleteBook(Connection con,Scanner sc) {
		try {
			PreparedStatement stmt=con.prepareStatement("delete from `onlinebooks`.books where title=?");
			stmt.setString(1, "The Stranger");;
			int ret=stmt.executeUpdate();
			return ret;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

}
