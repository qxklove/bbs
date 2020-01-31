<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="qxk" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN'>
<html>
    <head>
        <meta content='text/html;charset=UTF-8' http-equiv='content-type'>
        <title>BBS</title>
        <link rel='stylesheet' href='../css/member.css' type='text/css'>
    </head>
    <body>
<c:choose>    
    <c:when test="${requestScope.blahs != null }">
        <div class='leftPanel'>
            <img src='../images/index.jpg' alt='bbs' />
            <br><br>${requestScope.username}的bbs
        </div>
        <qxk:Blahs/>
        <hr style='width: 100%'>
    </c:when>        
    <c:otherwise>
        <h1 style='color: rgb(255, 0, 0);'>${requestScope.username} 用户不存在</h1>
    </c:otherwise>
</c:choose>
    </body>
</html>
