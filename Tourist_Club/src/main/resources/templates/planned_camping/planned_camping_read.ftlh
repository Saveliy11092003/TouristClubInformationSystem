<!doctype html>
<html lang="en">
<head>
    <title>Read</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f8ff; /* AliceBlue */
            color: #333; /* Navy */
            text-align: center;
            padding: 20px;
        }

        .back{
            background-color: rebeccapurple;
            color: white;
            top: 1px;
            right: 50px;
            position: absolute;
        }


        .camping {
            border: 2px solid #4682b4; /* SteelBlue */
            background-color: #e6f7ff; /* LightBlue */
            border-radius: 10px;
            padding: 20px;
            margin: 20px auto;
            width: 60%;
        }
        .camping-title {
            font-size: 1.5em;
            font-weight: bold;
            color: #4682b4; /* SteelBlue */
        }
        .camping-detail {
            font-size: 1.2em;
            margin-bottom: 10px;
        }
        .tourist-list {
            margin-top: 10px;
        }
    </style>
</head>
<body>

<#list campings as camping>
    <div class="camping">
        <div class="camping-title">Название похода: ${camping.name}</div>
        <div class="camping-detail">Дневник: ${camping.diary}</div>
        <div class="camping-detail">Имя инструктора: ${camping.instructor.name}</div>
        <div class="camping-detail">Фамилия инструктора: ${camping.instructor.surname}</div>
        <div class="camping-detail">Название плана: ${camping.plan.planName}</div>
        <div class="camping-detail">Тип похода: ${camping.plan.typeOfCamping}</div>
        <div class="camping-detail">Список туристов в походе:</div>
        <div class="tourist-list">
            <#list camping.tourists as tourist>
                ${tourist.name} ${tourist.surname}<br>
            </#list>
        </div>
    </div>
</#list>

<form action="/planned_camping" method="get" class="back">
    <input type="submit" value="Назад">
</form>

</body>
</html>
