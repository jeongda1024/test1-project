<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${empty sessionScope.id }">
	<script>
		location.href = 'index?formpath=login'
	</script>
</c:if>
<c:url var="root" value="/" />

<center>
	<h3>
		<font color="red" id="msg">${msg }</font>
	</h3>
	
	<table style="width: 450px;" border=1>
		<tr>
			<td colspan=2 style="height: 40px;" align="center">
				<h2>회원 정보</h2>
				<h3>
					<font color="red">로그인 계정 : ${sessionScope.id }</font>
				</h3>
			</td>
		</tr>
		<tr>
			<td style="width: 100px; height: 40px;" align="center" valign="bottom"><b>아이디</b></td>
			<td style="width: 250px; height: 40px;" align="center" valign="bottom">${user.id }</td>
		</tr>
		<tr>
			<td style="width: 100px; height: 40px;" align="center" valign="bottom"><b>이메일</b></td>
			<td style="width: 250px; height: 40px;" align="center" valign="bottom">${user.email }</td>
		</tr>
		<tr>
			<td style="width: 100px; height: 40px;" align="center" valign="bottom"><b>성별</b></td>
			<c:choose>
				<c:when test="${user.gender == 'm'}">
					<td style="width: 250px; height: 40px;" align="center" valign="bottom">남</td>
				</c:when>
				<c:when test="${user.gender == 'w'}">
					<td style="width: 250px; height: 40px;" align="center" valign="bottom">여</td>
				</c:when>
				<c:otherwise>
					<td style="width: 250px; height: 40px;" align="center" valign="bottom">선택안함</td>
				</c:otherwise>
			</c:choose>
		</tr>
		<tr>
			<td style="width: 100px; height: 40px;" align="center" valign="bottom"><b>주소</b></td>
			<td style="width: 250px; height: 40px;" align="center" valign="bottom">
				${user.addr1}&nbsp;&nbsp;${user.addr2 }
			</td>
		</tr>
		<tr>
			<td colspan=2 style="height: 10px;"></td>
		</tr>
		<tr>
			<td colspan=2 align="right">
			<input type=button style="width: 60px;" value='수정'
				onclick="location.href='${root}index?formpath=modifyCheck&modifyId=${user.id }'" />
			<input type=button style="width: 60px;" value='삭제'
				onclick="location.href='${root}index?formpath=memberDelete&modifyId=${user.id }'" />
			<input type=button style="width: 60px;" value='목록'
				onclick="location.href='memberListProc'" /></td>
		</tr>
	</table>
</center>