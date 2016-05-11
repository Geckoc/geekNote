<%@ page language="java" import="java.sql.*"
    pageEncoding="UTF-8"%>

<html>
<head>

<title>插入结果页面</title>
</head>
<body>
  <%
  String driverName="com.mysql.jdbc.Driver";
      String userName="root";
      String userPwd="123456";
      String dbName="students";
      String url1="jdbc:mysql://localhost:3306/"+dbName;
      String url2="?user="+userName+"&password="+userPwd;
      String url3="&useUnicode=true&characterEncoding=UTF-8";
      String url=url1+url2+url3;
      Class.forName(driverName);
      Connection conn=DriverManager.getConnection(url);
      String sql="Insert into stu_info(id,name,sex,age,weight,height)values(?,?,?,?,?,?)";
      PreparedStatement pstmt=conn.prepareStatement(sql);
      request.setCharacterEncoding("UTF-8");
      int id=Integer.parseInt(request.getParameter("id"));
      String name=request.getParameter("name");
      String sex=request.getParameter("sex");
      int age=Integer.parseInt(request.getParameter("age"));
      float weight=Float.parseFloat(request.getParameter("weight"));
      float height=Float.parseFloat(request.getParameter("height"));
         pstmt.setInt(1, id);
         pstmt.setString(2, name);
         pstmt.setString(3, sex);
         pstmt.setInt(4, age);
         pstmt.setFloat(5, weight);
         pstmt.setFloat(6, height);
         
         int c=pstmt.executeUpdate();
            if(c==1)
            {%> 数据插入成功!<br><%}
            
            else{%>数据插入操作势失败！<br><%}
            if(pstmt!=null){pstmt.close();}
            if(conn!=null){conn.close();}%>
</body>
</html>