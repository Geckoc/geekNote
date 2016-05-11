<%@ page language="java" 
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>按条件修改学生信息页面</title>
</head>
<body>
  请选择修改的条件<hr width="100%" size="3">
   <form action="update_stu_info1.jsp" method="post">
      姓名：<input type="text" name="name"><br><br>
   	性别：男<input type="radio" value="男" name="sex" checked="checked"/>
   		女<input type="radio" value="女" name="sex"><br><br>
   	体重范围：<p>&nbsp;&nbsp;&nbsp;&nbsp;
   		最小<input type="text" name="w1" value="0"><br><br>
   		&nbsp;&nbsp;&nbsp;&nbsp;
   		最大<input type="text" name="w2" value="150"><br>
   	<input type="submit" value="提交">&nbsp;&nbsp;
   	<input type="reset" value="取消">
   </form>
</body>
</html>