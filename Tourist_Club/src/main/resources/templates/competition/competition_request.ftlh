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
            max-width: 800px;
            margin: 20px auto;
            padding: 20px;
            border: 2px solid #1e90ff;
            border-radius: 15px;
            background-color: #e6f7ff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        form input[type="checkbox"],
        form input[type="radio"],
        form input[type="submit"] {
            margin-bottom: 10px;
        }

        .back{
            background-color: rebeccapurple;
            color: white;
            bottom: 50px;
            right: 50px;
            position: absolute;
        }


    </style>
</head>
<body>
<form action="/competition/result" method="get">

    <#list sections as section>
        Все группы из секции ${section.name} <input type="radio" name="idSection" value="${section.id}"><br><br>
    </#list>

    Выбрать все секции: <input type="checkbox" name="allSection" value="All"><br><br>
    <input type="submit" value="Выполнить запрос"/>
</form>
</body>
</html>
