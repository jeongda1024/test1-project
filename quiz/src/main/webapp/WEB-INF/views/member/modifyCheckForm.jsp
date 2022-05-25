<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${empty sessionScope.id }">
	<script>
		location.href="index?formpath=login";
	</script>
</c:if>
<style>
 div {padding: auto;}
</style>
<center>
<h3>
	<font color="red">${msg }</font>
</h3>
<form action="modifyCheckProc" method="post">
	<table>
		<tr>
			<td>패스워드</td>
			<td><input type=text name='pw' id="pw" placeholder='PW 입력'/></td>
		</tr>
		<tr>
			<td>패스워드 확인</td>
			<td><input type=password name='confirmPw' id='confirmPw' placeholder='PW 입력'/></td>
		</tr>
		<tr>
			<td colspan=2 align='center'>
				<input type=submit value='확인' style="width: 86px; "/>
				<input type=reset value='취소' style="width: 86px; "/> 
			</td>
		</tr>
	</table>
</form>
</center>
