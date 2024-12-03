<%@ page import="model.Quest" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    Quest quest = (Quest) request.getAttribute("quest");
    if (quest != null) {
%>
<html>
<head>
    <title><%= quest.getName() %></title>
    <link rel="stylesheet" href="css/styles.css">
    <script src="scripts/getAuthorQuestsCount.js" defer></script>
</head>
<body>

<!-- Вставляем навигационную панель -->
<jsp:include page="navbar.jsp"/>

<div class="main_div">
    <h1><%= quest.getName() %></h1>
    <p><span id="author" data-author-id="<%= quest.getAuthorId() %>">Автор: <%= quest.getAuthorName() %></span></p>
    <div id="tooltip" class="tooltip"></div>
    <p>Количество вопросов: <%= quest.getQuestions().size() %></p>
    <br/>
    <form action="setQuestId.jsp" method="post">
        <input type="hidden" name="questId" id="questId" value="<%= quest.getId() %>">
        <button type="submit">Начать квест</button>
    </form>
</div>
<!-- Вставляем футер -->
<jsp:include page="/footer"/>
</body>
</html>
<%
} else {
%>
<p>Квест не найден.</p>
<%
    }
%>
