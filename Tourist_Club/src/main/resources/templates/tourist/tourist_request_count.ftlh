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
        label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }
        input[type="radio"] {
            margin-right: 10px;
        }

        .back{
            background-color: rebeccapurple;
            color: white;
            bottom: 50px;
            right: 50px;
            position: absolute;
        }


        input[type="number"] {
            width: calc(100% - 22px);
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #1e90ff;
            border-radius: 5px;
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
<form action="/tourist/request5/count" method="post">
    <#list sections as section>
        <label>
            Cекция: ${section.name}, Спорт: ${section.typeOfSection.name}
            <input type="radio" name="idSection" value="${section.id}">
        </label><br>
    </#list> <br><br>

    <#list groups as group>
        <label>
            Группа: ${group.nameGroup} Тренер: ${group.coach.tourist.name} Специализация: ${group.coach.speciality}
            <input type="radio" name="idGroup" value="${group.id}">
        </label><br>
    </#list>

    <br><br>
    <label for="count">Кол-во походов:</label>
    <input type="number" id="count" name="count"/><br>

    <input type="submit" value="Выполнить запрос"/>
</form>
</body>
</html>
