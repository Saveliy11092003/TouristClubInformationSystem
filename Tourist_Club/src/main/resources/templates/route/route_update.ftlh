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

        .back{
            top: 1px;
            right: 50px;
            position: absolute;
            box-shadow: none;
        }

        .route-form {
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
        .route-info {
            font-size: 1em;
            margin-bottom: 10px;
        }
        .point-info {
            font-size: 1em;
            margin-bottom: 10px;
        }
        input[type="text"],
        input[type="radio"],
        input[type="checkbox"],
        input[type="submit"] {
            padding: 5px;
            margin: 5px 0;
        }
        input[type="submit"] {
            background-color: #1e90ff;
            color: white;
            border: none;
            border-radius: 5px;
            padding: 10px 20px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        input[type="submit"]:hover {
            background-color: #104e8b;
        }
    </style>
</head>
<body>

<#list routes as route>
    <form action="/route/update/${route.id}" method="post" class="route-form">
        <div class="route-info">
            <b>Название маршрута:</b> <input type="text" name="routeName" value="${route.routeName}" required><br>
            <b>Длина маршрута:</b> <input type="text" name="length" value="${route.routeLength}" required><br>
            <b>Сложность маршрута:</b><br>
            <input type="radio" name="difficulty" value="easy" <#if route.difficultyOfRoute.toString() == "EASY">checked</#if>> Легкий<br>
            <input type="radio" name="difficulty" value="middle" <#if route.difficultyOfRoute.toString() == "MIDDLE">checked</#if>> Средний<br>
            <input type="radio" name="difficulty" value="hard" <#if route.difficultyOfRoute.toString() == "HARD">checked</#if>> Сложный<br>
        </div>
        <div class="point-info">
            <b>Список точек остановки:</b><br>
            <#list points as point>
                <input type="checkbox" name="selectedPoints" value="${point.id}" <#if route.points?seq_contains(point)>checked</#if>> ${point.stoppingPointName}<br>
            </#list>
        </div>
        <input type="submit" value="Обновить">
    </form>
</#list>

<form action="/route" method="get" class="back">
    <input type="submit" value="Назад">
</form>

</body>
</html>
