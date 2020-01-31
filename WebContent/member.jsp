<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="qxk" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN'>
<html>
    <head>
        <meta content='text/html;charset=UTF-8' http-equiv='content-type'>
        <title>BBS</title>
        <link rel='stylesheet' href='css/member.css' type='text/css'>
    </head>
    <body>
        <div class='leftPanel'>
            <img src='images/index.jpg' alt='bbs' />
            <br><br>
            <a href='logout.do?username="${ sessionScope.login }'>
                登出 ${ sessionScope.login }</a>
        </div>
        <form method='post' action='message.do'>
        分享新鲜事...<br>
       <c:if test="${requestScope.blabla != null}">
                       留言要在 140 字以内<br>                    
       </c:if>
            <textarea cols='60' rows='4' name='blabla'>${requestScope.blabla}</textarea><br>
            <br><button type='submit'>发出</button>
        </form>
        <qxk:Blahs/>
    </body>
</html>
