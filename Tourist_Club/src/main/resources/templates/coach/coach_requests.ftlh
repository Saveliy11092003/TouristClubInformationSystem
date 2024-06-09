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

        .back{
            background-color: rebeccapurple;
            color: white;
            bottom: 50px;
            right: 50px;
            position: absolute;
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

        input[type="submit"]:hover {
            background-color: #003d80;
        }
    </style>

</head>
<body>
<h1>Tourist Club</h1><hr>
<div class="button-container">
<form action="/coach/request1" method="get">
    <input type="submit" value="Запрос 2">
</form> <br>

<form action="/coach/request2" method="get">
    <input type="submit" value="Запрос 4">
</form> <br>

<form action="/coach/request3" method="get">
    <input type="submit" value="Запрос 7 по тренеру">
</form> <br>

<form action="/coach/request4" method="get">
    <input type="submit" value="Запрос 7 по секции">
</form> <br>

</body>
</html>