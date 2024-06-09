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
<form action="/route/request8_1" method="post">
    <#list sections as section>
        <div>
            Название: ${section.name}, Тип секции: ${section.typeOfSection.typeOfSport.name()}
            <input type="radio" name="idSection" value="${section.id}"><br><br>
        </div>
    </#list>

    <label for="dateStart">Даты проведения:</label><br><br>
    <input type="date" name="dateStart" required/>  -  <input type="date" name="dateEnd" required/><br><br>

    <input type="submit" value="Выполнить запрос"/>
</form>
</body>
</html>
