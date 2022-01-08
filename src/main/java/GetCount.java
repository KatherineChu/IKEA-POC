

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
 * Servlet implementation class GetCount
 */
@WebServlet(name = "GetCount",urlPatterns = {"/GetCount"})
public class GetCount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCount() {
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
	
		 String total = null;
		 String data1 = null;
		 String p_id = null;
		 String num = null;
		 List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
		 try{
		 	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		 	System.out.println("載入成功");
		 	String user="sa";
		 	String pwd ="!QAZ2wsx#EDC4rfv";
		 	c=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;database=ikea",user,pwd);
		 	
		 	System.out.println("連線成功");
		 	Statement stmt = c.createStatement();
		 	ResultSet rs = stmt.executeQuery("select count(*) as total from dbo.gameData where like_num=1");
		 	while(rs.next()){
		 		total = rs.getString("total");	
		 	}
		 	System.out.println(total);
		 	Statement stmt1 = c.createStatement();
		 	ResultSet rs1 = stmt1.executeQuery("select  p_id, count(*) as num from dbo.gameData where like_num=1"
		 			+ "group by p_id");
		 	ResultSetMetaData metaData = rs.getMetaData();
		 	int columnCount = metaData.getColumnCount();
		 	
		 	while(rs1.next()){
		 		Map map = new HashMap();
		 		for (int i=1 ; i<=columnCount; i++) {
		 	 map.put ("p_id",rs1.getString("p_id"));
		 	 map.put ("num",rs1.getString("num"));	
		 	
		 	}
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
		
	 }

}
