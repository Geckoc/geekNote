<%@ page language="java" import="java.sql.*" 
    pageEncoding="UTF-8"%>
<html>
<head>
<title>删除结果页面</title>
</head>
<body>
 <%String driverName="com.mysql.jdbc.Driver";
 String userName="root";
 String userPwd="123456";
 String dbName="students";
 String url1="jdbc:mysql://localhost:3306/"+dbName;
 String url2="?user="+userName+"&password="+userPwd;
 String url3="&useUnicode=true&characterEncoding=UTF-8";
 String url=url1+url2+url3;
 Class.forName(driverName);
 Connection conn=DriverManager.getConnection(url);
 
 request.setCharacterEncoding("UTF-8");
 String name=request.getParameter("name");
	String sex=request.getParameter("sex");
	float weight1=Float.parseFloat(request.getParameter("w1"));
	float weight2=Float.parseFloat(request.getParameter("w2"));;
	
/*	String s="1=1";
	if(!name.equals("")) s=s+"and name='"+name+"";
	if(sex!=null) s=s+"and sex='"+sex+"";
	float w1,w2;
	if(!ww1.equals("")){w1=Float.parseFloat(w1); s=s+"and weight>="+w1;}
	if(!ww2.equals("")){w1=Float.parseFloat(w2); s=s+"and weight>="+w2;}
	String sql="delete form stu_info where"+s;
	*/
	String sql="delete from stu_info where name=? and sex=? and weight>=? and weight<=?";
	PreparedStatement stmt=conn.prepareStatement(sql);
	stmt.setString(1,name);
	stmt.setString(2,sex);
	stmt.setFloat(3,weight1);
	stmt.setFloat(4,weight2);
	int n=stmt.executeUpdate();
	if(n==1){
	%> 删除成功<br> <% 
	}else{
	%> 删除失败<br> <%
	}

	if(stmt != null)  {stmt.close();}
	if(conn != null)  {conn.close();}
%>
 
</body>
</html>