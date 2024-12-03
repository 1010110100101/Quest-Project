<%@ page import="model.Quest" %>
<%@ page import="model.services.QuestManage" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
  // Получаем questId из параметра и сохраняем его в сессии
  int questId = Integer.parseInt(request.getParameter("questId"));
  //session.setAttribute("questId", questId);

  QuestManage qm = new QuestManage();
  Quest quest = qm.getQuestById(questId);
  session.setAttribute("quest", quest);

  session.setAttribute("currentQuestionIndex", 0);

  // Перенаправление на startQuest.jsp
  response.sendRedirect("startQuest");
%>
