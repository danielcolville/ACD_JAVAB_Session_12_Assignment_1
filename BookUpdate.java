package books;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BookUpdate
 */
@WebServlet("/BookUpdate")
public class BookUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       Connection con;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		con=DBConnection.getDBInstance();
		
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("<html>\r\n" + 
				"<head>abc</head>\r\n" + 
				"<body>\r\n" + 
				"<form action=\"BookUpdate\" method=\"get\" >\r\n" + 
				"Type of update <select name=\"type\">\r\n" + 
				"<option value=\"insert\">Insert book</option>\r\n" + 
				"<option value=\"update\">Update book</option>\r\n" + 
				"<option value=\"delete\">Delete book</option>\r\n" + 
				"</select>\r\n" + 
				"Book Title to alter: <input type=\"text\" name=\"title\">\r\n" + 
				"Book new title:<input type=\"text\" name=\"newtitle\">\r\n" + 
				"Book new authors: <input type=\"text\" name=\"author\">\r\n" + 
				"Book new publisher: <input type=\"text\" name=\"pub\">\r\n" + 
				"Book new publication year: <input type=\"number\" name=\"year\">\r\n" + 
				"Book new price: <input type=\"number\" name=\"price\">\r\n" + 
				"<input type=\"submit\" value=\"update book fields\">\r\n" + 
				"</form>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"</body>\r\n" + 
				"</html>");
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String type=(String) request.getParameter("type");
		String title=(String) request.getParameter("title");
		String newT=(String ) request.getParameter("newtitle");
		String auth=(String) request.getParameter("author");
		String pub=(String) request.getParameter("pub");
		String year=request.getParameter("year");
		String price=request.getParameter("price");
		if(type.equals("insert")) {
			System.out.println("insert" +newT+auth+pub);
			if( newT.equals("") || auth.equals("") || pub.equals("") || year.equals("") || price.equals(""))
				response.getWriter().append("<h1> Insertion failed due to missing field </h1>");	
			else {
				BookSQL.insertNewBook(con, newT,auth,pub,Integer.parseInt(year),Double.parseDouble(price));
			}
		}
		else if (type.equals("update")) {
			if(!title.equals("")&& !newT.equals("")) {//&& !auth.equals("") && !pub.equals("") && !year.equals("") && !price.equals("") ) {
				//inefficient but much easier logic
				BookSQL.updateBook(con, title,"title",newT);
				BookSQL.updateBook(con, newT, "authors", auth);
				BookSQL.updateBook(con, newT, "publisher", pub);
				BookSQL.updateBook(con, newT, "publication_year", year);
				BookSQL.updateBook(con, newT, "price", price);
			}
			else {
				response.getWriter().append("<h1> Can't update, no title to find </h1>");
			}
		}
		else if (type.equals("delete")) {
			if(!title.equals("")) {
				BookSQL.deleteBook(con,title);
			}
			else {
				response.getWriter().append("<h1> Can't delete, no title to find </h1>");
			}
		}
		response.getWriter().append("\nServed at: ").append(request.getContextPath());
	}

}
