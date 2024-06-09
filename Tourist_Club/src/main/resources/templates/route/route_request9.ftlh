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
            bottom: 50px;
            right: 50px;
            position: absolute;
        }

        form {
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
        input[type="number"], input[type="submit"], select {
            width: calc(100% - 20px);
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #1e90ff;
            border-radius: 5px;
        }
        input[type="submit"] {
            background-color: #1e90ff;
            color: white;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        input[type="submit"]:hover {
            background-color: #104e8b;
        }
    </style>
</head>
<body>
<form action="/route/request9" method="post">

    Выбрать точку, через которую проходит маршрут:<br>

    <#list points as point>
    Название точки ${point.stoppingPointName} <input type="radio" name="idPoint" value="${point.id}"><br><br>
<br><br>
    </#list><br><br><br>

    Сложность маршрута:<br>
    <select name="difficulty">
        <option value="EASY">Лёгкий</option>
        <option value="MIDDLE">Средний</option>
        <option value="HARD">Сложный</option>
    </select>
    <br>
    Длина маршрута больше: <input type="number" name="length"/> <br>

    <input type="submit" value="Выполнить запрос"/>
</form>
</body>
</html>
