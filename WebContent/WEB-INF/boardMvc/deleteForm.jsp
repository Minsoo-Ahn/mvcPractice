<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<link href="${pageContext.request.contextPath }/css/style.css"
rel="stylesheet" type="text/css"/>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<script>
	function deleteSave(){
		if(document.delForm.pass.value==""){
			alert("비밀번호를 입력하세요.");
			document.delForm.pass.focus();
			return false;
		}
	}
</script>
<link href="${pageContext.request.contextPath }/css/style.css"
rel = "stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath }/css/writeFormstyle.css"
rel = "stylesheet" type="text/css"/>
</head>
<body>
<section>
<b>글삭제</b>
<form method="post" name="delForm" action="${pageContext.request.contextPath }/boards/deletePro.do?pageNum=${pageNum}"
onsubmit="return deleteSave()">
<table class="deletetable">
	<tr>
		<td><b>비밀번호를 입력해 주세요</b></td>
	</tr>
	<tr>
		<td>비밀번호 : 
			<input type="password" name="pass">
			<input type="hidden" name="num" value="${num }">
		</td>
	</tr>
	<tr>
		<td>
			<input type="submit" value="삭제">
			<input type="button" value="목록" onclick="document.location.href='${pageContext.request.contextPath}/boards/list.do?pageNum=${pageNum }'">
		</td>
	</tr>
</table>
</form>
</section>
</body>
</html>
