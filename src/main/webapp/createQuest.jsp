<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Создать гребаный квест</title>
    <link rel="stylesheet" href="css/styles.css">
    <script src="scripts/questions.js" defer></script>
</head>
<body>

<!-- Вставляем навигационную панель -->
<jsp:include page="/navbar" />

<h2>Создание нового квеста</h2>
<br />
<form action="createQuest" method="post">
    <span>Название квеста:</span>
    <input style="width: 100%; border-top: none; border-left: none; border-right: none; background-color: #f3f3f3;"
           type="text" name="questName" id="questName" required>
    <br />
    <br />
    <span>Количество вопросов:</span>
    <input style="width: 100%;"
           type="number" id="questionCount" name="questionCount" min="1" required onchange="addQuestion()">
    <br />
    <br />

    <div id="questions"></div>

    <br />
    <br />
    <button type="submit">Создать квест</button>
</form>

<!-- Вставляем футер -->
<jsp:include page="/footer" />
</body>
</html>
