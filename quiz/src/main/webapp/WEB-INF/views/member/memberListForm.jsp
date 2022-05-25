<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${empty sessionScope.id}">
	<script>
		location.href='index?formpath=login';
	</script>
</c:if>

<center>
<table style="width: 450px; ">
	<thead>
	<tr>
		<th style="width: 120px; height:20px;" align="center">아이디</th>
		<th style="width: 280px; height:20px;" align="center">이메일</th>
		<th style="width: 50px; height:20px;" align="center">성별</th>
	</tr>
	</thead>
	<tr>
		<td style="width: 120px; height:20px;" align="center"><hr/></td>
		<td style="width: 280px; height:20px;" align="center"><hr/></td>
		<td style="width: 50px; height:20px;" align="center"><hr/></td>
	</tr>
	<c:forEach var="db" items="${sessionScope.list }">
		<tr>
			<td style="width: 120px; height:40px;" align="center"><a href="userInfoProc?id=${db.id}">${db.id }</a></td>
			<td style="width: 280px; height:40px;" align="center">${db.email }</td>
			<td style="width: 50px; height:40px;" align="center">${db.gender }</td>
			
		</tr>
	</c:forEach>	
	<tr><td colspan=3><hr/></td></tr>
</table>
<!-- 이전 1 2 3 4 다음 -->
${sessionScope.page }

<form>
<table>
<tr>
<td>
	<select name="select">
		<option value="">전체</option>
		<option value="id">아이디</option>
		<option value="email">이메일</option>
	</select>
	<input type=text name='search'/>
	<input type=submit name='searchBtn' value='검색' style="width: 80px; "/>
</td>
</tr>
</table>
</form>
</center>