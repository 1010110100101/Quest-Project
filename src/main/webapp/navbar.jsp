<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!-- Навигационная панель -->
<div class="navbar">
    <a href="index.jsp">Home</a>
    <a href="quests">Quests</a>

    <%
        String username = (String) request.getAttribute("username");
        if (username != null) {
    %>
    <a href="createQuest">Create quest</a>
    <a href="login?logout=true" class="right">LogOut ( <%= username %> )</a>
    <% } else { %>
    <a href="login.jsp" class="right">LogIn</a>
    <% } %>
</div>

<!-- Хлебные крошки -->
<div class="breadcrumbs">
    <%= request.getAttribute("breadcrumbsHtml") %>
</div>
