document.addEventListener('DOMContentLoaded', function () {
    const author = document.getElementById('author');
    const tooltip = document.getElementById('tooltip');

    author.addEventListener('mouseenter', function (event) {
        var authorId = event.target.getAttribute('data-author-id');

        var url = window.location.origin + "/Quests_Web_exploded/getQuestCount?authorId=" + authorId;

        // Отправляем запрос на сервер
        fetch(url)
            .then(response => response.text()) // Получаем строку
            .then(data => {
                tooltip.innerText = "Всего квестов у автора: " + data.trim(); // Устанавливаем текст из строки
                tooltip.style.display = 'block';

                // Позиционируем подсказку рядом с курсором
                const tooltipWidth = tooltip.offsetWidth;
                const tooltipHeight = tooltip.offsetHeight;
                const pageWidth = window.innerWidth;
                const pageHeight = window.innerHeight;

                // Начальные координаты
                let left = event.pageX + 15; // Отступ от курсора
                let top = event.pageY + 15;

                // Проверяем, выходит ли подсказка за правую границу экрана
                if (left + tooltipWidth > pageWidth) {
                    left = pageWidth - tooltipWidth - 15; // Сдвигаем влево
                }

                // Проверяем, выходит ли подсказка за нижнюю границу экрана
                if (top + tooltipHeight > pageHeight) {
                    top = pageHeight - tooltipHeight - 15; // Сдвигаем вверх
                }

                // Устанавливаем окончательные координаты
                tooltip.style.left = left-550 + "px";
                tooltip.style.top = top-350 + "px";
            })
            .catch(error => {
                tooltip.innerText = 'Ошибка при загрузке данных.';
                tooltip.style.display = 'block';
            });
    });

    // Скрываем подсказку при уходе мыши
    author.addEventListener('mouseleave', function () {
        tooltip.style.display = 'none';
    });
});
