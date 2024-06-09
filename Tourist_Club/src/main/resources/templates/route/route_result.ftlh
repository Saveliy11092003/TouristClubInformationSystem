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

        .back{
            background-color: rebeccapurple;
            color: white;
            bottom: 50px;
            right: 50px;
            position: absolute;
        }

        .no-results {
            font-size: 1.2em;
            color: #4682b4; /* SteelBlue */
            margin-top: 20px;
        }
        .route {
            border: 2px solid #4682b4; /* SteelBlue */
            background-color: #e6f7ff; /* LightBlue */
            border-radius: 10px;
            padding: 20px;
            margin: 20px auto;
            width: 60%;
        }
        .route-title {
            font-size: 1.5em;
            font-weight: bold;
            color: #4682b4; /* SteelBlue */
        }
        .route-detail {
            font-size: 1.2em;
            margin-bottom: 10px;
        }
        .point-list {
            margin-top: 10px;
        }
    </style>
</head>
<body>

<#if routes?size == 0>
    <div class="no-results">Ничего не найдено</div>
<#else>
    <#list routes as route>
        <div class="route">
            <div class="route-title">Название маршрута: ${route.routeName}</div>
            <div class="route-detail">Длина маршрута: ${route.routeLength}</div>
            <div class="route-detail">Сложность маршрута: ${route.difficultyOfRoute}</div>
            <div class="route-detail">Точки остановки:</div>
            <div class="point-list">
                <#list route.points as point>
                    ${point.stoppingPointName}<br>
                </#list>
            </div>
        </div>
    </#list>
</#if>

</body>
</html>

