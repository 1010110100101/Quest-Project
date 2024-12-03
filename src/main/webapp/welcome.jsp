<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Добро пожаловать в проект Квестов</title>
  <link rel="stylesheet" href="styles.css"> <!-- Подключение стилей -->
  <link rel="stylesheet" href="css/styles.css">
</head>
<body>

<!-- Вставляем навигационную панель -->
<jsp:include page="/navbar" />

<br /><br/>
<header>
  <h1>Добро пожаловать в проект Квестов!</h1>
</header>

<main>
  <section>
    <h2>О проекте</h2>
    <p>Наш проект позволяет пользователям создавать и участвовать в квестах, чтобы испытать свои навыки, интеллект и интуицию.
      Каждый пользователь может создавать свои уникальные квесты, которые будут доступны для других участников платформы.</p>
  </section>

  <section>
    <h2>Возможности проекта</h2>
    <ul>
      <li>Создавайте свои собственные квесты, указывая заголовок, описание и количество вопросов.</li>
      <li>Присоединяйтесь к квестам, созданным другими пользователями, и соревнуйтесь за лучшие результаты.</li>
      <li>Просматривайте статистику и достижения, а также делитесь своими успехами с друзьями.</li>
      <li>Используйте систему аутентификации для защиты личных данных и сохранения вашего прогресса.</li>
    </ul>
  </section>

  <section>
    <h2>Начало работы</h2>
    <p>Зарегистрированные пользователи имеют возможность создавать авторские квесты, искать доступные задания и проверять свои силы в прохождении квестов.</p>

  </section>
</main>

<!-- Вставляем футер -->
<jsp:include page="/footer" />
</body>
</html>