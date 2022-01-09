
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet(name = "TestServlet",urlPatterns = {"/TestServlet"})
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * Default constructor. 
     */
    public TestServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 System.out.println("matthew3");
		 PrintWriter out=response.getWriter();
		 out.write("No");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 System.out.println("matthew1");
		work(request, response);
	}
	 private void work(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		
		 Connection c =null;
		 Statement stmt =null;
		 ResultSet rs = null;
		 String name = null;
		 try{
		 	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		 	System.out.println("載入成功");
		 	String user="sa";
		 	String pwd ="!QAZ2wsx#EDC4rfv";
		 	c=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;database=ikea",user,pwd);
		 	
		 	System.out.println("連線成功");
		 	stmt = c.createStatement();
		 	rs = stmt.executeQuery("Select * from dbo.customer");
		 	while(rs.next()){
		 	name = rs.getString("c_name");
		 	
		 	
		 	}
		 	System.out.println("123:"+name);
		 	
		 	
		 }catch (Exception e){
		 	System.out.println(e.getClass().getName()+":"+e.getMessage());
		 	System.out.println("error");
		 }
		 finally{
		 	try{
		 		if(c!=null)
		 			c.close();
		 	}catch(Exception e){
		 		
		 	}
		 }
		 
		 PrintWriter out=response.getWriter();
		 out.write(name);
		
	 }
}
