<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<center>
<table style="width: 650px; ">
	<thead>
	<tr>
		<th style="width: 40px; height:20px;" align="center">선택</th>
		<th style="width: 330px; height:20px;" align="center">제 목</th>
		<th style="width: 80px; height:20px;" align="center">작성자</th>
		<th style="width: 120px; height:20px;" align="center">작성일</th>
		<th style="width: 80px; height:20px;" align="center">조회수</th>
	</tr>
	</thead>
	<tr>
		<td style="width: 40px; height:20px;" align="center"><hr/></td>
		<td style="width: 330px; height:20px;" align="center"><hr/></td>
		<td style="width: 80px; height:20px;" align="center"><hr/></td>
		<td style="width: 120px; height:20px;" align="center"><hr/></td>
		<td style="width: 80px; height:20px;" align="center"><hr/></td>
	</tr>
	<tr>
		<td style="width: 40px; height:40px;" align="center"><input type="checkbox"/></td>
		<td style="width: 330px; height:40px;" align="center">자주 이용해 주세요</td>
		<td style="width: 80px; height:40px;" align="center">관리자</td>
		<td style="width: 120px; height:40px;" align="center">20xx.xx.xx</td>
		<td style="width: 80px; height:40px;" align="center">1</td>
	</tr>
	<tr>
		<td style="width: 40px; height:40px;" align="center"><input type="checkbox"/></td>
		<td style="width: 330px; height:40px;" align="center">게시판 확인</td>
		<td style="width: 80px; height:40px;" align="center">관리자</td>
		<td style="width: 120px; height:40px;" align="center">20xx.xx.xx</td>
		<td style="width: 80px; height:40px;" align="center">10</td>
	</tr>
	<tr><td colspan=5><hr/></td></tr>
	<tr>
		<td colspan=2><input type="checkbox"/>전체선택</td>
		<td colspan=3 align="right">
			<input type="button" value='삭제' style="width: 100px; "/>
			<input type="button" value='글쓰기' style="width: 100px; "/>
		</td>
	</tr>
	<tr><td colspan=5><hr/></td></tr>
</table>
이전 1 2 3 4 다음
<table>
<tr>
<td>
	<select>
		<option>전체</option>
		<option>제목</option>
		<option>작성자</option>
	</select>
	<input type=text name='search'/>
	<input type=button name='searchBtn' value='검색' style="width: 80px; "/>
</td>
</tr>
</table>
</center>