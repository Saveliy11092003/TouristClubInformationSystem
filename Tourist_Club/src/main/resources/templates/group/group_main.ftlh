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

        .back{
            background-color: rebeccapurple;
            color: white;
            bottom: 50px;
            right: 50px;
            position: absolute;
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
<form action="/group/create" method="get">
    <input type="submit" value="Создать группу">
</form> <br>
    </#if>

<form action="/group/read" method="get">
    <input type="submit" value="Запросы">
</form> <br>

<form action="/group/update" method="get">
    <input type="submit" value="Обновить информацию об группах">
</form> <br>

<form action="/group/delete" method="get">
    <input type="submit" value="Удалить группу">
</form> <br>
</div>
</body>

<form action="/" method="get" class="back">
    <input type="submit" value="Назад">
</form>

</html>