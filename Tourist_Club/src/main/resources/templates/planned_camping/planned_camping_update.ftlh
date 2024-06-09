<!doctype html>
<html lang="en">
<head>
    <title>Document</title>
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

        .camping-form {
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
        .form-input {
            font-size: 1em;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #1e90ff;
            border-radius: 5px;
        }
        .radio-label {
            font-size: 1em;
            margin-bottom: 10px;
            display: block;
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

<#list campings as camping>
    <form class="camping-form" action="/planned_camping/update/${camping.id}" method="post">
        <label for="name">Название похода:</label><br>
        <input class="form-input" type="text" id="name" name="name" value="${camping.name}"><br>

        <label for="diary">Дневник:</label><br>
        <input class="form-input" type="text" id="diary" name="diary" value="${camping.diary}"><br>

        <label>Текущий план похода:</label><br>
        ${camping.plan.planName} ${camping.plan.typeOfCamping.name()}<br>

        <label>Выберите план похода:</label><br>
        <#list plans as plan>
            <label class="radio-label">
                <input type="radio" name="idPlan" value="${plan.id}"> ${plan.planName} ${plan.typeOfCamping.name()}<br>
            </label>
        </#list>
        <input type="hidden" name="idPlan"><br>

        <input class="update-button" type="submit" value="Обновить"/>
    </form>
</#list>

<form action="/planned_camping" method="get" class="back">
    <input type="submit" value="Назад">
</form>

</body>
</html>
