<%@ page language="java"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>按条件查询学生</title>
</head>
<body>
 请选择查询条件条件<hr width="100%" size="3">
   <form action="scan_info.jsp" method="post">
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