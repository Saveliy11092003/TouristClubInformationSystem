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
        input[type="text"],
        input[type="submit"],
        select {
            width: calc(50% - 22px);
            padding: 10px;
            margin: 10px 0;
            text-align: center;
            border: 1px solid #1e90ff;
            border-radius: 5px;
        }
        input[type="checkbox"] {
            margin: 10px 0;
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
<form action="/coach/request1" method="post">

    <#list sections as section>
        <div>
            Все группы из секции ${section.name}
            <input type="radio" name="idSection" value="${section.id}"><br><br>
        </div>
    </#list>

    <div>
        Выбрать все секции:
        <input type="checkbox" name="allSection" value="All"><br><br>
    </div>

    <div>
        Выбрать специализацию:
        <select name="speciality">
            <option value="FOOTBALL">Футбол</option>
            <option value="HORSE">Конный спорт</option>
            <option value="SWIMMING">Плавание</option>
            <option value="MOUNTAIN">Горный спорт</option>
        </select>
    </div>
    <br>

    <div>
        Выбрать пол:
        <select name="sex">
            <option value="male">Мужской</option>
            <option value="female">Женский</option>
            <option value="All">Все</option>
        </select>
    </div>
    <br>

    <div>
        Зарплата меньше: <input type="text" name="salary"/> <br>
        Выбрать все зарплаты: <input type="checkbox" name="allSalary" value="All"><br><br>
    </div>

    <div>
        Возраст: <input type="text" name="age"/> <br>
        Выбрать все возраста: <input type="checkbox" name="allAge" value="All"><br><br>
    </div>

    <input type="submit" value="Выполнить запрос"/>
</form>
</body>
</html>
