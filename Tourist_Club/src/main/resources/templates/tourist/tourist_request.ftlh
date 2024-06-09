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
<form action="/tourist/request/section" method="get">
    <input type="submit" value="Запрос 1 по секции ">
</form> <br>

<form action="/tourist/request/group" method="get">
    <input type="submit" value="Запрос 1 по группе">
</form> <br>

<form action="/tourist/request5/count" method="get">
    <input type="submit" value="Запрос 5 по заданному кол-ву походов">
</form> <br>

<form action="/tourist/request5/camping" method="get">
    <input type="submit" value="Запрос 5 по указанному походу">
</form> <br>

<form action="/tourist/request5/time" method="get">
    <input type="submit" value="Запрос 5 по определенному времеми">
</form> <br>

<form action="/tourist/request5/route" method="get">
    <input type="submit" value="Запрос 5 по указанному маршруту">
</form> <br>

<form action="/tourist/request5/point" method="get">
    <input type="submit" value="Запрос 5 по указанной точке">
</form> <br>

<form action="/tourist/request5/category" method="get">
    <input type="submit" value="Запрос 5 по соответствующей категории">
</form> <br>


<form action="/tourist/request5/difficulty" method="get">
    <input type="submit" value="Запрос 5 по сложности">
</form> <br>

<form action="/tourist/request10/type" method="get">
    <input type="submit" value="Запрос 10 по типу походов">
</form> <br>

<form action="/tourist/request12" method="get">
    <input type="submit" value="Запрос 12">
</form> <br>

<form action="/tourist/request13" method="get">
    <input type="submit" value="Запрос 13">
</form> <br>
</div>
</body>
</html>