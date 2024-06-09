<!doctype html>
<html lang="en">
<head>
    <title>Delete</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f8ff;
            color: #333;
            text-align: center;
            margin: 0;
            padding: 0;
        }

        .back{
            background-color: rebeccapurple;
            color: white;
            top: 1px;
            right: 50px;
            position: absolute;
        }

        .group-container {
            display: inline-block;
            width: 80%;
            max-width: 800px;
            margin: 20px auto;
            padding: 20px;
            border: 2px solid #1e90ff;
            border-radius: 15px;
            background-color: #e6f7ff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .group-info {
            font-size: 1.2em;
            margin-bottom: 10px;
        }
        .athlete-info {
            font-size: 1em;
            margin-bottom: 5px;
        }
        .delete-form {
            margin-top: 20px; /* Отступ от предыдущего блока */
        }
        .delete-button {
            background-color: #ff0000; /* Цвет кнопки */
            color: white; /* Цвет текста */
            border: none; /* Убираем границу */
            border-radius: 5px; /* Закругляем углы */
            padding: 10px 20px; /* Отступы */
            cursor: pointer; /* Изменяем указатель при наведении */
            transition: background-color 0.3s; /* Плавное изменение цвета при наведении */
        }
        .delete-button:hover {
            background-color: #cc0000; /* Цвет кнопки при наведении */
        }
    </style>
</head>
<body>

<div id="infoMessage" style="display:none; padding: 10px; border: 1px solid #1e90ff; margin: 10px; background-color: #e6f7ff;">
    Соревнование было успешно удалено!
</div>

<#list competitions as competition>
    <div class="group-container">
        <div class="group-info">
            <b>Название соревнования: </b> ${competition.nameCompetition}<br>
            <b>Описание соревнования: </b> ${competition.description}<br>
            <b>Участники соревнования:</b><br>
            <#list competition.athletes as athlete>
                <div class="athlete-info">
                    ${athlete.tourist.name} ${athlete.tourist.surname} ${athlete.yearOfWorkExperience}
                </div>
            </#list>
        </div>
        <form class="delete-form" action="/competition/delete/${competition.id}" method="post">
            <input class="delete-button" type="submit" value="Удалить"/>
        </form>
    </div>
</#list>

<script>
    function displayInfoMessage() {
        var infoMessage = document.getElementById('infoMessage');
        if (${isDeleted?c}) {
            infoMessage.style.display = 'block';
        } else {
            infoMessage.style.display = 'none';
        }
    }

    displayInfoMessage();
</script>

<form action="/competition" method="get" class="back">
    <input type="submit" value="Назад">
</form>

</body>
</html>
