<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<h1>
<%
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
	
	System.out.println("連線成功囉");
	stmt = c.createStatement();
	rs = stmt.executeQuery("Select * from dbo.customer");
	while(rs.next()){
	name = rs.getString("c_name");
	
	
	}
	System.out.println("123:"+name);
	//request.setAttribute("name",name);
	
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
	
%>
</h1>