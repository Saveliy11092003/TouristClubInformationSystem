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

<div id="infoMessage" style="display:none; padding: 10px; border: 1px solid #1e90ff; margin: 10px; background-color: #e6f7ff;">
    Маршрут был успешно удален!
</div>

<#list routes as route>
    <form action="/route/delete/${route.id}" method="post" class="route-form">
        <div class="route-info">
            <b>Название маршрута:</b> ${route.routeName}<br>
            <b>Длина маршрута:</b> ${route.routeLength}<br>
            <b>Сложность маршрута:</b> ${route.difficultyOfRoute}<br>
        </div>
        <div class="point-info">
            <b>Точки остановки:</b><br>
            <#list route.points as point>
                ${point.stoppingPointName}<br>
            </#list>
        </div>
        <input type="submit" value="Удалить маршрут">
    </form>
</#list>

<script>
    function displayInfoMessage() {
        var infoMessage = document.getElementById('infoMessage');
        if (${isRouteDeleted?c}) {
            infoMessage.style.display = 'block';
        } else {
            infoMessage.style.display = 'none';
        }
    }

    displayInfoMessage();
</script>

<form action="/route" method="get" class="back">
    <input type="submit" value="Назад">
</form>

</body>
</html>
