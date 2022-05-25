<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${empty sessionScope.id }">
	<script>
		location.href="index?formpath=login";
	</script>
</c:if>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	function daumPost(){
    new daum.Postcode({
        oncomplete: function(data) {
            var addr = "";
            // R== 도로명주소, J==wlqjswnth
            if(data.userSelectedType=="R"){
            	addr = data.roadAddress;
            }else{
            	addr = data.jibunAddress;
            }
            document.getElementById('zonecode').value=data.zonecode; //우편번호
            document.getElementById('addr1').value=addr;
            document.getElementById('addr2').focus();
        }
    }).open();
    }
</script>

<center>
<h3>
	<font color="red" id="msg">${msg }</font>
</h3>	
<form action="memberModifyProc" method="post" id="f" >	
<table>
	<tr>
		<td align='right' height=40>아이디</td>
		<td>
			<input type=text name='id' id="id" value="${user.id }" disabled="disabled"/> 
		</td>
		<td colspan="2"></td>
	</tr>
	<tr>
		<td align='right' height=40>패스워드</td>
		<td>
			<input type=text name='pw' id="pw" placeholder='pw 입력'/> 
		</td>
		<td align='right'>패스워드 확인</td>
		<td>
			<input type=text name='confirmPw' id="confirmPw" placeholder='pw 입력'/> 
		</td>
	</tr>
	<tr>
		<td align='right' height=40>E-Mail</td>
		<td>
			<input type=text name='email' id="email" value="${user.email }"/> 
		</td>
		<td colspan="2"></td>
	</tr>
	<tr>
		<td align='right'>우편번호</td>
		<td>
			<input type=text name='zipcode' id="zonecode" value="${user.zipcode }" readonly="readonly"/> 
		</td>
		<td colspan="2"><input type="button" value="우편번호 검색" onclick="daumPost()" ></td>
	</tr>
	<tr>
		<td align='right'>주소</td>
		<td colspan="3">
			<input type=text name='addr1' id="addr1" readonly="readonly" value="${user.addr1 }" style="width: 475px; "/> 
		</td>
	</tr>
	<tr>
		<td align='right'>상세주소</td>
		<td colspan="3">
			<input type=text name='addr2' id="addr2" value="${user.addr2 }" style="width: 475px; "/> 
		</td>
	</tr>
	<tr>
		<td align='right' width=120>성 별</td>
		<td colspan="3">
		<c:choose>
			<c:when test="${user.gender=='w' }">
				<input type=radio name='gender' value='n' disabled="disabled"/>선택 안함
				<input type=radio name='gender' value='m' disabled="disabled"/>남자
				<input type=radio name='gender' value='w' checked="checked"/>여자 
			</c:when>
			<c:when test="${user.gender=='m' }">
				<input type=radio name='gender' value='n' disabled="disabled"/>선택 안함
				<input type=radio name='gender' value='m' checked="checked"/>남자
				<input type=radio name='gender' value='w' disabled="disabled"/>여자 
			</c:when>
			<c:otherwise>
				<input type=radio name='gender' value='n' checked="checked"/>선택 안함
				<input type=radio name='gender' value='m' disabled="disabled"/>남자
				<input type=radio name='gender' value='w' disabled="disabled"/>여자 
			</c:otherwise>
		</c:choose>
		</td>
		
	</tr>
	
	<tr>
		<td align='center' height=40 colspan=4>
			<input type=submit value='회원 수정' style="width: 120px; "/>
			<input type=reset value='취소' style="width: 120px; "/>	 
		</td>
	</tr>
</table>
</form>
</center>