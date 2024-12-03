<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.Quest" %>
<%@ page import="model.Question" %>

<%
  Quest quest = (Quest) request.getAttribute("quest");
  Integer currentQuestionIndex = (Integer) request.getAttribute("currentQuestionIndex");
  Question question = (Question) request.getAttribute("question");
%>

<html>
<head>
  <title><%= quest.getName() %> - Вопрос <%= currentQuestionIndex + 1 %></title>
  <link rel="stylesheet" href="css/styles.css">
</head>
<body>

<!-- Вставляем навигационную панель -->
<jsp:include page="/navbar" />

<h1 style="padding-bottom: 4px;"><%= quest.getName() %></h1>

<div class="progress-container">
  <progress id="progressBar" value="<%= (currentQuestionIndex * 100) / quest.getQuestions().size() %>" max="100"></progress>
  <p>Пройдено <%= currentQuestionIndex %> из <%= quest.getQuestions().size() %> вопросов</p>
</div>

<h2>Вопрос <%= currentQuestionIndex + 1 %>:</h2>
<h3 style="padding-left:60px; color:#f78539; font-weight: 500; font-size: 24px;">
  <%= question.getText() %>
</h3>

<div style="margin: 80px 0px 0px 160px;">
  <form action="startQuest" method="post">
    <%
      int index = 0;
      for (String option : question.getAnswers()) {
    %>
    <input type="radio" name="answer" id="answer<%= index %>" value="<%= index %>" required>
    <label for="answer<%= index %>"><%= option %></label><br>
    <%
        index++;
      }
    %>
    <br />
    <button type="submit">Ответить</button>
  </form>
</div>

<jsp:include page="/footer" />
</body>
</html>
