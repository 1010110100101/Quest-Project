<!-- register.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Регистрация</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>

<!-- Вставляем навигационную панель -->
<jsp:include page="/navbar" />

<h2>Регистрация</h2>

<%= (request.getAttribute("error")) == null ? "" : (String) request.getAttribute("error")  %>


<form action="register" method="post">
    <div class="main_div">
        <div style="text-align: left; width: 100%;">
            <p for="username">Имя пользователя:</p>
            <input type="text" id="username" name="username" required>

            <br /><br />
            <p for="password">Пароль:</p>
            <input type="password" id="password" name="password" required>

            <br /><br />
            <br /><br />
            <button type="submit">Зарегистрироваться</button>
        </div>
    </div>
</form>

<p>Уже есть аккаунт? <a href="login.jsp">Войдите здесь</a>.</p>

<!-- Вставляем футер -->
<jsp:include page="/footer" />
</body>
</html>
