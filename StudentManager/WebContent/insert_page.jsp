<%@ page language="java" 
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
</head>
<body>
   <form action="insert_stu.jsp" method="post">
        <h3 align="center">学生信息添加页面</h3>
     <table  border="1" align="center" >
        <tr><td>学号:</td><td><input type="text" name="id"></td><td></td></tr>
        <tr><td>姓名:</td><td><input type="text" name="name"></td></tr>
        <tr><td>性别:</td><td><input type="text" name="sex"></td></tr>
        <tr><td>年龄:</td><td><input type="text" name="age"></td></tr>
        <tr><td>身高:</td><td><input type="text" name="height"></td></tr>
        <tr><td>体重:</td><td><input type="text" name="weight"></td></tr>
        <tr align="center"><td colspan="2">
          <input type="submit" value="提交">
          <input type="reset" value=" 重置">      
        </td></tr>  
        </table>
        </form>
</body>
</html>