<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script>
	function check(){
		document.getElementById('f')
	}
	
	var req;
	function isExistId(){
		req = new XMLHttpRequest();
		req.onreadystatechange = printMsg;
		req.open('post','isExistId');
		req.send(document.getElementById('id').value);
	}
	
	function printMsg(){
		var msg = document.getElementById('msg');
		msg.innerHTML = req.responseText;
	}
	
	function sendAuth(){
		req=new XMLHttpRequest();
		req.onreadystatechange = printMsg;
		req.open('post','sendAuth');
		req.send(document.getElementById('email').value);
	}
	
	function checkAuth(){
		req=new XMLHttpRequest();
		req.onreadystatechange = printMsg;
		req.open('post','checkAuth');
		req.setRequestHeader("Content-Type", "application/json; charset=UTF-8");
		var number = document.getElementById('authNumber').value;
		var data = {authNumber:number};
		data = JSON.stringify(data);
		req.send(data);
	}
</script>
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
<form action="memberProc" method="post" id="f" >	
<table>
	<tr>
		<td align='right' height=40>아이디</td>
		<td>
			<input type=text name='id' id="id" placeholder='id 입력'/> 
		</td>
		<td colspan="2"><input type="button" value="중복 확인" onclick="isExistId()"></td>
	</tr>
	<tr>
		<td align='right' height=40>패스워드</td>
		<td>
			<input type=text name='pw' id="pw" placeholder='pw 입력'/> 
		</td>
		<td align='right'>패스워드 확인</td>
		<td>
			<input type=text name='pwOk'id="pwOk" placeholder='pw 입력'/> 
		</td>
	</tr>
	<tr>
		<td align='right' height=40>E-Mail</td>
		<td>
			<input type=text name='email' id="email" placeholder='E-Mail 입력'/> 
		</td>
		<td colspan="2"><input type="button" value="인증번호 전송" onclick="sendAuth()"></td>
	</tr>
	<tr>
		<td align='right'>인증번호</td>
		<td>
			<input type=text name='authNum' id="authNumber" placeholder='인증번호 입력'/> 
		</td>
		<td colspan="2"><input type="button" value="인증번호 확인" onclick="checkAuth()"></td>
	</tr>
	<tr>
		<td align='right'>우편번호</td>
		<td>
			<input type=text name='zipcode' id="zonecode" readonly="readonly"/> 
		</td>
		<td colspan="2"><input type="button" value="우편번호 검색" onclick="daumPost()" ></td>
	</tr>
	<tr>
		<td align='right'>주소</td>
		<td colspan="3">
			<input type=text name='addr1' id="addr1" readonly="readonly" style="width: 475px; "/> 
		</td>
	</tr>
	<tr>
		<td align='right'>상세주소</td>
		<td colspan="3">
			<input type=text name='addr2' id="addr2" style="width: 475px; "/> 
		</td>
	</tr>
	<tr>
		<td align='right' width=120>성 별</td>
		<td colspan="3">
			<input type=radio name='gender' value='n' checked="checked"/>선택 안함
			<input type=radio name='gender' value='m' />남자
			<input type=radio name='gender' value='w' />여자 
		</td>
	</tr>
	
	<tr>
		<td align='center' height=40 colspan=4>
			<input type=submit value='회원 가입' style="width: 120px; "/>
			<input type=reset value='취소' style="width: 120px; "/>	 
		</td>
	</tr>
</table>
</form>
</center>