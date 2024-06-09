<!doctype html>
<html lang="en">
<head>
    <title>Update</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f8ff;
            color: #333;
            text-align: center;
            margin: 0;
            padding: 0;
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

        .back{
            background-color: rebeccapurple;
            color: white;
            top: 1px;
            right: 50px;
            position: absolute;
        }

        .group-info {
            font-size: 1.2em;
            margin-bottom: 10px;
        }
        .tourist-info {
            font-size: 1em;
            margin-bottom: 5px;
        }
        .update-form {
            margin-top: 20px; /* Отступ от предыдущего блока */
        }
        .update-button {
            background-color: #1e90ff; /* Цвет кнопки */
            color: white; /* Цвет текста */
            border: none; /* Убираем границу */
            border-radius: 5px; /* Закругляем углы */
            padding: 10px 20px; /* Отступы */
            cursor: pointer; /* Изменяем указатель при наведении */
            transition: background-color 0.3s; /* Плавное изменение цвета при наведении */
        }
        .update-button:hover {
            background-color: #104e8b; /* Цвет кнопки при наведении */
        }
    </style>
</head>
<body>

<#list groups as group>
    <div class="group-container">
        <form class="update-form" action="/group/update/${group.id}" method="post">
            <div class="group-info">
                <b>Название группы: </b> <input type="text" name="nameGroup" value="${group.nameGroup}" required> <br>
                <b>Имя тренера: </b> ${group.coach.tourist.name}<br>
                <b>Фамилия тренера: </b> ${group.coach.tourist.surname}<br>
                <b>Тип секции: </b> ${group.section.typeOfSection.name}<br>
                <b>Список обучающихся: </b><br>
                <#list tourists as tourist>
                    <#if tourist.group??>
                        <#if tourist.group.id == group.id>
                            <input type="checkbox" name="selectedTourists" value="${tourist.id}" checked>
                            ${tourist.surname}<br>
                        </#if>
                    <#else>
                        <#if tourist.hasGroup == false>
                            <input type="checkbox" name="selectedTourists" value="${tourist.id}">
                            ${tourist.surname}<br>
                        </#if>
                    </#if>
                </#list>
            </div>
            <input class="update-button" type="submit" value="Обновить"/>
        </form>
    </div>
</#list>

<form action="/group" method="get" class="back">
    <input type="submit" value="Назад">
</form>
</body>
</html>
