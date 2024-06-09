<!doctype html>
<html lang="en">
<head>
    <title>Document</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f8ff; /* AliceBlue */
            color: #333;
            text-align: center;
            padding: 20px;
        }
        form {
            margin: 0 auto;
            width: 60%;
        }

        .back{
            background-color: rebeccapurple;
            color: white;
            bottom: 50px;
            right: 50px;
            position: absolute;
        }

        input[type="text"],
        input[type="number"] {
            margin-bottom: 10px;
            padding: 8px;
            border-radius: 5px;
            border: 1px solid #4682b4; /* SteelBlue */
            width: 100%;
            box-sizing: border-box;
        }
        input[type="checkbox"] {
            margin-bottom: 10px;
        }
        input[type="submit"] {
            background-color: #4682b4; /* SteelBlue */
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #3a6880; /* Darker SteelBlue */
        }
    </style>
</head>
<body>
<form action="/supervisor/request" method="post">


    Зарплата меньше: <input type="text" name="salary"/> <br>
    Выбрать все зарплаты: <input type="checkbox" name="allSalary" value="All"><br><br>

    Год рождения: <input type="number" name="yearOfBirth"/> <br>
    Выбрать все года: <input type="checkbox" name="allYearsBirth" value="All"><br><br>

    Год поступления на работу: <input type="number" name="yearOfWork"/> <br>
    Выбрать все года: <input type="checkbox" name="allYearsWork" value="All"><br><br>

    <input type="submit" value="Выполнить запрос"/>
</form>
</body>
</html>
