<!-- login.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Авторизация</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>

<!-- Вставляем навигационную панель -->
<jsp:include page="/navbar" />

<h2>Авторизация</h2>
<% if (request.getParameter("error") != null) { %>
<p style="color:red;">Неправильное имя пользователя или пароль. Попробуйте еще раз.</p>
<% } %>
<form action="login" method="post">
    <div class="main_div">
        <div style="text-align: left; width: 100%;">
        <p for="username">Имя пользователя:</p>
        <input type="text" id="username" name="username" required>

            <br /><br />
        <p for="password">Пароль:</p>
        <input type="password" id="password" name="password" required>

            <br /><br />
            <br /><br />
        <button type="submit">Авторизоваться</button>
        </div>
    </div>
</form>

<p>Еще нет аккаунта? <a href="register.jsp" style="font-size: 14px;">Зарегистрируйтесь</a>.</p>

<!-- Вставляем футер -->
<jsp:include page="/footer" />
</body>
</html>
