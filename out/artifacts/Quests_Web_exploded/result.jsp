<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    boolean success = Boolean.parseBoolean(request.getParameter("success"));
%>
<html>
<head>
    <title>Результат Квеста</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>

<!-- Вставляем навигационную панель -->
<jsp:include page="/navbar" />
<div class="main_div">
<%
    if (success) {
%>
<h1 style="color:#2aa243;">Поздравляем!<br />Вы успешно прошли квест.</h1>
<%
} else {
%>
<h1 style="color:#da2020;">Соболезнуем,<br />Вы не прошли квест.<br />Попробуйте еще раз!</h1>
<%
    }
%>
    <br />
<a href="quests">Вернуться к списку квестов</a>
</div>

<!-- Вставляем футер -->
<jsp:include page="/footer" />
</body>
</html>
