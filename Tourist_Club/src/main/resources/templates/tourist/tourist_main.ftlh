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

        .back{
            background-color: rebeccapurple;
            color: white;
            bottom: 50px;
            right: 50px;
            position: absolute;
        }

        hr {
            border: 0;
            height: 1px;
            background: #ccc;
            margin: 20px 0;
        }
        .button-container {
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        input[type="submit"] {
            background-color: #0056b3;
            color: white;
            border: none;
            padding: 20px;
            width: 500px;
            box-sizing: border-box;
            text-transform: uppercase;
            cursor: pointer;
            transition: background-color 0.3s ease;
            font-size: 16px;
        }

        input[type="submit"]:hover {
            background-color: #003d80;
        }
    </style>

</head>
<body>
<h1>Tourist Club</h1><hr>
<div class="button-container">
    <#if isAdmin == true>
<form action="/tourist/create" method="get">
    <input type="submit" value="Создать туриста">
</form> <br>
    </#if>

<form action="/tourist/read" method="get">
    <input type="submit" value="Список туристов">
</form> <br>

<form action="/tourist/update" method="get">
    <input type="submit" value="Обновить информацию об туристе">
</form> <br>

<form action="/tourist/delete" method="get">
    <input type="submit" value="Удалить туриста">
</form> <br>

<form action="/tourist/request" method="get">
    <input type="submit" value="Запросы">
</form> <br>
</div>

<form action="/" method="get" class="back">
    <input type="submit" value="Назад">
</form>

</body>
</html>