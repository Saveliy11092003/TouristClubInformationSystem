<!DOCTYPE html>
<html>
<head>
    <title>Tourist Club</title>
    <link rel="stylesheet" href="/static/css/style.css">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
          integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn"
          crossorigin="anonymous">

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

        .container {
            max-width: 1600px;
            height: 1000px;
            margin: 0 auto;
            padding: 20px;
            background: white;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }

        .grid {
            display: grid;
            grid-template-columns: repeat(3, 1fr);
            gap: 10px;
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
<h1>Tourist Club</h1>
<hr>

<div class="button-container">

    <#if isAdmin == true>
    <form action="/amateur/create" method="get">
        <input type="submit" value="Создать любителя">
    </form>
    </#if>
    <br>

    <form action="/amateur/read" method="get">
        <input type="submit" value="Посмотреть">
    </form>
    <br>

    <form action="/amateur/update" method="get">
        <input type="submit" value="Обновить информацию об любителе">
    </form>
    <br>

    <form action="/amateur/delete" method="get">
        <input type="submit" value="Удалить любителя">
    </form>
    <br>

    <form action="/" method="get" class="back">
        <input type="submit" value="Назад">
    </form>
</div>
</body>
</html>