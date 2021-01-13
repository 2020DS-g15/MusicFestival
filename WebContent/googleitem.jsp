<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GoogleSearch</title>
<style type="text/css">
#padding{
	padding: 0px 0px 0px 15px;
}
a {
	color: #0B173B;
	font-size: 30px;
	text-decoration: none;
}
a:hover{
text-decoration:underline;
}

.border-style {
	border-radius: 75px/90px;
}

</style>
</head>
<body>
<body style='background-color: #01A9DB'>
<form action='${requestUri}' method='get'>

	<div style='position: absolute;margin-top:190px;margin-left:50px'>
		<%
		String[][] orderList = (String[][]) request.getAttribute("query");
		for (int i = 0; i < orderList.length; i++) {
		%>
		
		<a href='<%=orderList[i][1]%>'><%=orderList[i][0]%> </a> <br> <br>
		<br>
		<%
}
%>
	</div>
	<div>
		<img src="images/band-2.png"
			style='position: absolute; width: 150px; height: 70px; left: 50%; top: 50%; margin-top: -338px; margin-left: -660px'>
	</div>
		<div>
		<input type='text' class="border-style" id="padding" name='keyword'
			style='font-size: 120%; position: absolute; left: 50%; top: 48%; margin-top: -310px; margin-left: -450px; width: 850px; height: 35px'
			placeholder = '請輸入關鍵字' value='<%=request.getParameter("keyword")%>'/>
	</div>

</form>
</body>
</html>