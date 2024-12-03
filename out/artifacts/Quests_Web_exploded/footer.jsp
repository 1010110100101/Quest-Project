<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="footer">
  <p>&copy; 2024 My Quest App. Все права защищены.</p>

  <c:choose>
    <c:when test="${isUserLoggedIn}">
      <!-- Если пользователь авторизован, отображаем ссылку "Выход" -->
      <a href="login?logout=true" class="btn">Exit</a>
    </c:when>
    <c:otherwise>
      <!-- Если пользователь не авторизован, отображаем ссылки "Регистрация" и "Вход" -->
      <a href="register.jsp" class="btn">Create account</a>
      <a href="login.jsp" class="btn">Log in</a>
    </c:otherwise>
  </c:choose>
</div>

<div style="display:block; text-align:center; position:fixed; bottom:60px; right:4px; z-index:10000; cursor:pointer;">
  <div><a href="${telegramLink}"><img width="90px" src="https://kapitoly.com/img/telega.png"></a></div>
  <div><a href="${viberLink}"><img width="80px" src="https://kapitoly.com/img/viber.png"></a></div>
  <div><a href="${whatsappLink}"><img width="80px" src="https://kapitoly.com/img/whats.png"></a></div>
</div>

<!-- Кнопка для открытия панели -->
<button onclick="openSidebar()" class="open-btn">☰</button>

<!-- Выезжающая боковая панель -->
<div id="mySidebar" class="sidebar">
  <a href="javascript:void(0)" class="close-btn" onclick="closeSidebar()">×</a>
  <a href="index.jsp">Home</a>
  <a href="quests.jsp">Quests</a>
  <a href="createQuest">Create Quest</a>
  <a href="login.jsp">Log In</a>
</div>

<script src="scripts/sidebar.js" type="text/javascript"></script>
