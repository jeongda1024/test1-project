<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
<div align="center">
<table style="width:800px;">
<tr>
	<td style="height:100px;"><%@ include file="common/top.jsp" %></td>
</tr>
<tr>
	<td style="height:400px;"><c:import url="/${formpath }"/> </td>
</tr>
<tr>
	<td style="height:50px;"><%@ include file="common/footer.jsp" %></td>
</tr>
</table>
</div>
</body>
</html>