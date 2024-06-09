<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BuySell</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #e0f7fa;
            color: #01579b;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 80vh;
        }

        h1 {
            color: #0288d1;
        }

        .back{
            background-color: rebeccapurple;
            color: white;
            bottom: 50px;
            right: 50px;
            position: absolute;
        }

        form {
            background-color: #ffffff;
            border: 2px solid #0288d1;
            border-radius: 10px;
            padding: 20px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 500px;
            height: 500px;
            text-align: center;
        }

        form input[type="text"]{
            padding: 10px;
            margin: 10px 0;
            width: 450px;
            border: 1px solid #0288d1;
            border-radius: 5px;
        }

        form input[type="submit"] {
            background-color: #0288d1;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        form input[type="submit"]:hover {
            background-color: #0277bd;
        }

        h4 {
            color: #01579b;
        }
    </style>
</head>
<body>
<h1>Tourist Club</h1>


<form action="/admin_panel" method="post">
    Введите запрос: <input type="text" name="request" required><br><br>
    <input type="submit" value="Выполнить">
</form>

</body>
</html>