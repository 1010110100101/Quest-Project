<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Наши квесты</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>

<!-- Вставляем навигационную панель -->
<jsp:include page="/navbar" />

<h2>Наши квесты (${totalQuests} шт.)</h2>

<!-- Вставляем сгенерированный HTML -->
${questsHtml}

<!-- Вставляем футер -->
<jsp:include page="/footer" />
</body>
</html>
