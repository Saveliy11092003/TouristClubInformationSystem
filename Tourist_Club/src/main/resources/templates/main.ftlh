<!DOCTYPE html>
<html>
<head>
    <title>Tourist Club</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            color: #333;
            margin: 0;
            padding: 20px;
        }
        h1 {
            text-align: center;
            color: #0056b3;
        }
        hr {
            border: 0;
            height: 1px;
            background: #ccc;
            margin: 20px 0;
        }
        .container {
            max-width: 1600px;
            height: 1000px;
            margin: 0 auto;
            padding: 20px;
            background: white;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }
        .grid {
            display: grid;
            grid-template-columns: repeat(3, 1fr);
            gap: 10px;
        }
        input[type="submit"] {
            background-color: #0056b3;
            color: white;
            border: none;
            padding: 20px;
            width: 100%;
            box-sizing: border-box;
            text-transform: uppercase;
            cursor: pointer;
            transition: background-color 0.3s ease;
            font-size: 16px;
        }
        input[type="submit"]:hover {
            background-color: #003d80;
        }

        .l{
            background-color: rebeccapurple;
            color: white;
            bottom: 50px;
            right: 50px;
            position: absolute;
        }

        .logout-button {
            position: fixed;
            right: 20px;
            bottom: 20px;
            background-color: #99c223;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
        }

    </style>
</head>
<body>
<div class="container">
    <h1>Информационная система туристического клуба</h1><hr>
    <div class="grid">
        <form action="/amateur" method="get">
            <input type="submit" value="Любители">
        </form>

        <form action="/athlete" method="get">
            <input type="submit" value="Спортсмены">
        </form>

        <form action="/coach" method="get">
            <input type="submit" value="Тренеры">
        </form>

        <form action="/competition" method="get">
            <input type="submit" value="Соревнования">
        </form>

        <form action="/group" method="get">
            <input type="submit" value="Группы">
        </form>

        <form action="/instructor" method="get">
            <input type="submit" value="Инструкторы">
        </form>

        <form action="/plan" method="get">
            <input type="submit" value="Планы">
        </form>

        <form action="/planned_camping" method="get">
            <input type="submit" value="Запланированные походы">
        </form>

        <form action="/route" method="get">
            <input type="submit" value="Маршруты">
        </form>

        <form action="/section" method="get">
            <input type="submit" value="Секции">
        </form>

        <form action="/section_work_schedule" method="get">
            <input type="submit" value="Расписание работы секций">
        </form>

        <form action="/stopping_point" method="get">
            <input type="submit" value="Точки остановки маршрутов">
        </form>

        <form action="/tourist" method="get">
            <input type="submit" value="Туристы">
        </form>

        <#if isAdmin == true>
            <form action="/admin" method="get">
                <input type="submit" value="Панель администратора">
            </form>
        </#if>

    </div>

    <form action="/logout" method="post" class="l">
        <input type="submit" value="Выйти">
    </form>

</div>
</body>
</html>
