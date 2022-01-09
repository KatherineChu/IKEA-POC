

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class GetTag
 */

@WebServlet(name = "GetTag",urlPatterns = {"/GetTag"})
public class GetTag extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetTag() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		work(request, response);
	}

	 private void work(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
			
			
	
		 Connection c = null;
		 String tag = request.getParameter("tag"); 
			System.out.println(tag);
		 List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
		 try{
		 	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		 	System.out.println("載入成功");
		 	String user="sa";
		 	String pwd ="!QAZ2wsx#EDC4rfv";
		 	c=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;database=ikea",user,pwd);
		 	
		 	System.out.println("連線成功");
		 	Statement stmt = c.createStatement();
		 	ResultSet rs = stmt.executeQuery("SELECT c_name ,tag_name FROM gameData g\r\n"
		 			+ "LEFT JOIN Customer c ON  c.c_id = g.c_id\r\n"
		 			+ "LEFT JOIN Tag t ON t.t_id = g.t_id\r\n"
		 			+ "WHERE tag_name ='"+tag+"'");
		 	
		 	while(rs.next()){
		 		Map map = new HashMap();
		 		map.put ("c_name",rs.getString("c_name"));
		 	
		 	
		 	
		 	lists.add(map);
		 	}
		 System.out.println(lists);
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
		Gson gson = new Gson();
		String json_list = gson.toJson(lists);
		out.write(json_list);
		 request.setAttribute(json_list, json_list);
		
	 }

	

}
