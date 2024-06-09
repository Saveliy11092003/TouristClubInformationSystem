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

        .back{
            background-color: rebeccapurple;
            color: white;
            bottom: 50px;
            right: 50px;
            position: absolute;
        }

        select, input[type="radio"], input[type="date"], input[type="submit"] {
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
<form action="/tourist/request10/type" method="post">

    <#list sections as section>
        <div>
            Cекция: ${section.name}, Спорт: ${section.typeOfSection.name}
            <input type="radio" name="idSection" value="${section.id}"><br><br>
        </div>
    </#list> <br><br>

    <#list groups as group>
        <div>
            Группа: ${group.nameGroup} Тренер: ${group.coach.tourist.name} Специализация: ${group.section.typeOfSection.typeOfSport.name()}
            <input type="radio" name="idGroup" value="${group.id}"><br><br>
        </div>
    </#list>

    <br><br>

    Выбрать тип похода:
    <select name="typeOfRoute" required>
        <option value="HIKING">Пеший</option>
        <option value="HORSE">Конный</option>
        <option value="SWIMMING">Сплав</option>
        <option value="MOUNTAIN">Горный</option>
    </select>

    <br><br>

    <input type="submit" value="Выполнить запрос"/>
</form>
</body>
</html>
