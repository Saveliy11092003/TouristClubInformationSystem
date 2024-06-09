<!doctype html>
<html lang="en">
<head>
    <title>Read</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f8ff; /* AliceBlue */
            color: #333;
            text-align: center;
            padding: 20px;
        }
        .group {
            border: 2px solid #4682b4; /* SteelBlue */
            background-color: #e6f7ff; /* LightBlue */
            border-radius: 10px;
            padding: 20px;
            margin: 20px auto;
            width: 60%;
        }

        .back{
            background-color: rebeccapurple;
            color: white;
            top: 1px;
            right: 50px;
            position: absolute;
        }

        .group-title {
            font-size: 1.5em;
            font-weight: bold;
            color: #4682b4; /* SteelBlue */
        }
        .group-detail {
            font-size: 1.2em;
            margin-bottom: 10px;
        }
        .tourist-list {
            margin-top: 10px;
        }
    </style>
</head>
<body>

<#list groups as group>
    <div class="group">
        <div class="group-title">Название группы: ${group.nameGroup}</div>
        <div class="group-detail">Имя тренера: ${group.coach.tourist.name}</div>
        <div class="group-detail">Фамилия тренера: ${group.coach.tourist.surname}</div>
        <div class="group-detail">Тип секции: ${group.section.typeOfSection.name}</div>
        <div class="group-detail">Список обучающихся:</div>
        <div class="tourist-list">
            <#list group.tourists as tourist>
                ${tourist.name}<br>
            </#list>
        </div>
    </div>
</#list>

<form action="/group" method="get" class="back">
    <input type="submit" value="Назад">
</form>

</body>
</html>
