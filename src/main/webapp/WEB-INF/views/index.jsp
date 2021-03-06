<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page session="false" isELIgnored="false" %>
<html>
<head>
<title>Activity Stream</title>
<style type="text/css">
.tg {
	border-collapse: collapse;
	border-spacing: 0;
	border-color: #ccc;
}

.tg td {
	font-family: Arial, sans-serif;
	font-size: 14px;
	padding: 10px 5px;
	border-style: solid;
	border-width: 1px;
	overflow: hidden;
	word-break: normal;
	border-color: #ccc;
	color: #333;
	background-color: #fff;
}

.tg th {
	font-family: Arial, sans-serif;
	font-size: 14px;
	font-weight: normal;
	padding: 10px 5px;
	border-style: solid;
	border-width: 1px;
	overflow: hidden;
	word-break: normal;
	border-color: #ccc;
	color: #333;
	background-color: #f0f0f0;
}

.tg .tg-4eph {
	background-color: #f9f9f9
}

.errorMessages {
    display: none;
    list-style-type: disc;
    margin: 0 10px 15px 10px;
    padding: 8px 35px 8px 30px;
    color: #B94A48;
    background-color: #F2DEDE;
    border: 2px solid #EED3D7;
    border-radius: 4px;
    text-shadow: 0 1px 0 rgba(255, 255, 255, 0.5);
}
.errorMessages span {
    font-weight: bold;
}
</style>
</head>
<body>
	<h1>Send a message</h1>

	

	<form action="sendMessage" method="post">
	
	
	</ul>
	
		<table>
				<c:if test="${!empty errorMsg}">
					${errorMsg}
				</c:if>
			<tr>
				
				<td>Sender Name:</td>
				<td><input type="text" name="sender" /></td>
			</tr>
			<tr>
			${messageEmpty}
				<td>Message:</td>
				<td><input type="text" name="message" /></td>
			</tr>



			<tr>
				<td colspan="2"><input type="submit" value="Send" /></td>
			</tr>
		</table>
	</form>
	<br>
	<h1>Circle Messages</h1>
	<c:if test="${!empty messages}">
		<table class="tg">
			<tr>
				<th width="80">Sender</th>
				<th width="120">Posted Date</th>
				<th width="120">Message</th>

			</tr>
			<c:forEach items="${messages}" var="message">
				<tr>
					<td>${message.senderName}</td>
					<td>${message.postedDate}</td>
					<td>${message.message}</td>
					
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>