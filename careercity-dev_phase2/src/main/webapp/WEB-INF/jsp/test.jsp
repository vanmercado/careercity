<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
    <h4>HELLO WORLD!!</h4>
    <c:forEach items="${ answers }" var="answer">${ answer.ANSWER }
    </c:forEach>
    --%>
    <table>
        <c:forEach items="${listofaccounts }" var="test">
            <tr>
                <td>${test.ACCOUNT_ID }</td>
                <br>
                <td>${test.ACCOUNT_NAME }</td>
                <br>
            </tr>
        </c:forEach>
    </table>
</body>
</html>