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
        form {
            width: 80%;
            max-width: 400px;
            margin: 20px auto;
            padding: 20px;
            border: 2px solid #1e90ff;
            border-radius: 10px;
            background-color: #e6f7ff;
        }

        .back{
            background-color: rebeccapurple;
            color: white;
            bottom: 50px;
            right: 50px;
            position: absolute;
        }

        select {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #1e90ff;
            border-radius: 5px;
            box-sizing: border-box;
        }
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #1e90ff;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        input[type="submit"]:hover {
            background-color: #104e8b;
        }
    </style>
</head>
<body>
<form action="/instructor/request1" method="post">
    Выберите тип инструктора: <br><br>
    <select name="typeInstructor">
        <option value="instructor">Инструктор</option>
        <option value="instructor-athlete">Инструктор-спортсмен</option>
        <option value="instructor-coach">Инструктор-тренер</option>
    </select><br><br>

    Выберите сложность маршрутов, в которые может ходить инструктор:<br><br>
    <select name="difficulty">
        <option value="EASY">Легкий</option>
        <option value="MIDDLE">Средний</option>
        <option value="HARD">Сложный</option>
    </select><br><br>

    <input type="submit" value="Выполнить запрос"/>
</form>
</body>
</html>
