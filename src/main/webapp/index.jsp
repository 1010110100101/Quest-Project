<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>

    <title>Главная страница</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>

<!-- Вставляем навигационную панель -->
<jsp:include page="/navbar" />
<div>
    <br /><br />
    <h1>Добро пожаловать на главную страницу!</h1>
    <p>Здесь вы можете начать прохождение квестов или авторизоваться.</p>
    <br />
    <br />
</div>

<div id="gentitle">
    <h1>СТУДИЯ КВЕСТОВ <span style="font-size: 36px; color: #777;">КВЕ<span style="color:#ffa201;">СТУ</span><span style="color:#1990cf;">ДИ</span>О</span></h1>
    <h2 style="font-size:18px; font-weight: 500; color:#555555; text-align: center;padding: 0; margin: 0;">Ушатаем вашу психику на все 300%</h2>
</div>

<br />
<br />
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

<section>
    <h2>Частые тупые вопросы пользователей</h2>
</section>

<details>
    <summary><h3>Я не прошел очередной квест. Это означает я тупой?</h3></summary>
    <p>Да.</p>
</details>
<details>
    <summary><h3>Какое количество квестов можно проходить за один день?</h3></summary>
    <p>Нет никаких ограничений, вы можете проходить любые доступные квесты пока не отключится ваш интернет.</p>
</details>
<details>
    <summary><h3>Как добавить собственный квест?</h3></summary>
    <p>Для создания авторского квеста вы должны обладать интеллектом и также быть зарегистрированным пользователем. В этом случае вы можете воспользоваться меню <a href="createQuest.jsp">"Создать квест"</a>.</p>
</details>
<details>
    <summary><h3>Зачем проходить квесты?</h3></summary>
    <p>Вам полезно проходить всевозможные квесты хотя бы по причине, что мы на таких как вы генерируем трафик, а это рекламодатели, инвестиции и монетизированный хайп.</p>
</details>

<!-- Вставляем футер -->
<jsp:include page="/footer" />
</body>
</html>
