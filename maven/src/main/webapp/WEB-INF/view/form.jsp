<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="send.do" method="post">
이름 : <input type="text" name="name" required value="${memberVo.name }"><br> <!-- 여기에도 required를 적용할 수 있다. 아까 입력한 값 나오게 하는 법. 커맨드 객체 사용-->
이메일 : <input type="text" name="email"><br>
취미 :  <input type ="checkbox" name ="hobby" value="1">독서
		<input type ="checkbox" name ="hobby" value="2">게임
		<input type ="checkbox" name ="hobby" value="3">영화
		<input type ="checkbox" name ="no" value="4">등산
<!-- <input type="hidden" name="no" value="10"/> --> <!-- defaultValue = "0"을 썼다면 hidden을 빼야한다. defaultValue를 쓰려면 no라는 항목이 없어야하는데 여기서 10으로 지정했기 때문에 -->
<input type="submit" value="전송"/>
</form>
</body>
</html>