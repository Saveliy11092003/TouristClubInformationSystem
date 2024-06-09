<!doctype html>
<html lang="en">
<head>
    <title>Document</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa; /* Light Gray */
            color: #333;
            text-align: center;
            padding: 20px;
        }
        form {
            margin: 0 auto;
            width: 60%;
            padding: 20px;
            border: 2px solid #1e90ff;
            border-radius: 15px;
            background-color: #e6f7ff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .back{
            background-color: rebeccapurple;
            color: white;
            bottom: 50px;
            right: 50px;
            position: absolute;
        }

        label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }
        input[type="radio"],
        input[type="checkbox"] {
            margin-right: 10px;
        }
        select {
            margin-bottom: 10px;
            width: calc(100% - 22px);
            padding: 10px;
            border: 1px solid #1e90ff;
            border-radius: 5px;
        }
        input[type="text"] {
            width: calc(100% - 22px);
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #1e90ff;
            border-radius: 5px;
        }
        input[type="checkbox"] {
            margin-right: 5px;
        }
        input[type="submit"] {
            background-color: #1e90ff;
            color: white;
            cursor: pointer;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            transition: background-color 0.3s;
        }
        input[type="submit"]:hover {
            background-color: #104e8b;
        }
    </style>
</head>
<body>
<form action="/tourist/request/group" method="post">
    <#list sections as section>
        <label>
            Секция: ${section.name}<br>
            <#list section.groups as group>
                Группа: ${group.nameGroup}, Тренер: ${group.coach.tourist.name}<input type="radio" name="idGroup" value="${group.id}">
                <br>
            </#list>
        </label><br><br><br><br>
    </#list>

    <label for="sex">Выбрать пол:</label>
    <select name="sex">
        <option value="male">Мужской</option>
        <option value="female">Женский</option>
        <option value="All">Все</option>
    </select><br>

    <label for="age">Возраст:</label> <input type="text" name="age"/><br>
    <label for="allSex">Выбрать все возраста:</label> <input type="checkbox" name="allSex" value="All"><br><br>

    <input type="submit" value="Выполнить запрос"/>
</form>
</body>
</html>
