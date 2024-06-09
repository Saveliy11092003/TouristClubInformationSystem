<!doctype html>
<html lang="en">
<head>
    <title>Read</title>
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

        .camping-info {
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
        .info-label {
            font-weight: bold;
            font-size: 1em;
            margin-bottom: 10px;
            display: block;
        }
        .tourist-info {
            font-size: 1em;
            margin-bottom: 10px;
        }
        .delete-button {
            background-color: #1e90ff; /* Цвет кнопки */
            color: white; /* Цвет текста */
            border: none; /* Убираем границу */
            border-radius: 5px; /* Закругляем углы */
            padding: 10px 20px; /* Отступы */
            cursor: pointer; /* Изменяем указатель при наведении */
            transition: background-color 0.3s; /* Плавное изменение цвета при наведении */
        }
        .delete-button:hover {
            background-color: #104e8b; /* Цвет кнопки при наведении */
        }
    </style>
</head>
<body>
<div id="infoMessage" style="display:none; padding: 10px; border: 1px solid #1e90ff; margin: 10px; background-color: #e6f7ff;">
    Поход был успешно удалён!
</div>

<#list campings as camping>
    <div class="camping-info">
        <span class="info-label">Название похода:</span> ${camping.name}<br>
        <span class="info-label">Дневник:</span> ${camping.diary}<br>
        <span class="info-label">Имя инструктора:</span> ${camping.instructor.name}<br>
        <span class="info-label">Название плана:</span> ${camping.plan.planName}<br>
        <span class="info-label">Тип похода:</span> ${camping.plan.typeOfCamping}<br>
        <span class="info-label">Список туристов в походе:</span><br>
        <#list camping.tourists as tourist>
            <span class="tourist-info">${tourist.name}</span><br>
        </#list>
        <form action="/planned_camping/delete/${camping.id}" method="post">
            <input class="delete-button" type="submit" value="Удалить поход"/>
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

<form action="/planned_camping" method="get" class="back">
    <input type="submit" value="Назад">
</form>

</body>
</html>
