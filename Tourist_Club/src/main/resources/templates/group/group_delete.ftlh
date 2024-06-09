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
        .tourist-info {
            font-size: 1em;
            margin-bottom: 5px;
        }
        .delete-button {
            background-color: #ff6347; /* Цвет кнопки */
            color: white; /* Цвет текста */
            border: none; /* Убираем границу */
            border-radius: 5px; /* Закругляем углы */
            padding: 10px 20px; /* Отступы */
            cursor: pointer; /* Изменяем указатель при наведении */
            transition: background-color 0.3s; /* Плавное изменение цвета при наведении */
        }
        .delete-button:hover {
            background-color: #cc4731; /* Цвет кнопки при наведении */
        }
    </style>
</head>
<body>

<div id="infoMessage" style="display:none; padding: 10px; border: 1px solid #1e90ff; margin: 10px; background-color: #e6f7ff;">
    Группа была успешно удалена!
</div>

<#list groups as group>
    <div class="group-container">
        <div class="group-info">
            <b>Название группы: </b> ${group.nameGroup}<br>
            <b>Имя тренера: </b> ${group.coach.tourist.name}<br>
            <b>Фамилия тренера: </b> ${group.coach.tourist.surname}<br>
            <b>Тип секции: </b> ${group.section.typeOfSection.name}<br>
            <b>Список обучающихся: </b><br>
            <#list group.tourists as tourist>
                <div class="tourist-info">
                    ${tourist.name}<br>
                </div>
            </#list>
        </div>
        <form action="/group/delete/${group.id}" method="post">
            <input class="delete-button" type="submit" value="Удалить группу"/>
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

<form action="/group" method="get" class="back">
    <input type="submit" value="Назад">
</form>

</body>
</html>
