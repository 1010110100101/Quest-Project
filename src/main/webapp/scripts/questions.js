function addQuestion() {
    const questionCount = document.getElementById("questionCount").value;
    let questionsDiv = document.getElementById("questions");
    questionsDiv.innerHTML = "";

    for (let i = 0; i < questionCount; i++) {
        questionsDiv.innerHTML += '<div class="new_question">' +
            '<h3 style="padding-left: 60px; color: #f78539; font-weight: 500; font-size: 24px;">Вопрос ' + i + '</h3>' +
            '<span>Текст вопроса:</span>' +
            '<input style="width: 98%; border-top: none; border-left: none; border-right: none;" type="text" name="question' + i + '" id="question' + i + '" required><br /><br /><br /><br />' +
            '<span>Варианты ответов:</span><br />' +
            '<input style="width: 90%; margin: 20px 0px 0px 60px;" type="text" name="option' + i + '_1" id="option' + i + '_1" placeholder="Вариант 1" required>' +
            '<input style="width: 90%; margin: 20px 0px 0px 60px;" type="text" name="option' + i + '_2" id="option' + i + '_2" placeholder="Вариант 2" required>' +
            '<input style="width: 90%; margin: 20px 0px 0px 60px;" type="text" name="option' + i + '_3" id="option' + i + '_3" placeholder="Вариант 3" required>' +
            '<input style="width: 90%; margin: 20px 0px 0px 60px;" type="text" name="option' + i + '_4" id="option' + i + '_4" placeholder="Вариант 4" required><br /><br />' +
            '<span>Правильный ответ (номер):</span>' +
            '<input type="number" name="correctAnswer' + i + '" id="correctAnswer' + i + '" min="1" max="4" required><br />' +
            '</div>';
    }
}
