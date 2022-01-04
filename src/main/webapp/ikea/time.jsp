<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<times>
<%
      Date dNow = new Date( );
      SimpleDateFormat fd = new SimpleDateFormat ("yyyy-MM-dd");
      SimpleDateFormat ft = new SimpleDateFormat ("HH:mm:ss");
      String date = fd.format(dNow);
      String time = ft.format(dNow);
      out.println(date+"   "+time);
%> 
</times>