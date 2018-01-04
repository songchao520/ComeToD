<%@ page language="java"  import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="js/jquery-1.11.3.js"></script>
<title>Insert title here</title>
</head>
<body>
	<button id="button">按钮</button>
</body>
<script type="text/javascript">
	$(function(){
		$("#button").click(function(){
			$.post("/springMVC/loginDBs",{src:"22"},function(data){
				alert(data);
			});
		})
		
	});
</script>
</html>