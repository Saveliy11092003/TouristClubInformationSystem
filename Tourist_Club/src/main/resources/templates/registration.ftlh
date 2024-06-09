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

        form {
            background-color: #ffffff;
            border: 2px solid #0288d1;
            border-radius: 10px;
            padding: 20px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 300px;
            text-align: center;
        }

        form input[type="text"],
        form input[type="email"],
        form input[type="password"] {
            padding: 10px;
            margin: 10px 0;
            width: 250px;
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
<h4>Регистрация</h4>
<form action="/registration" method="post">
    Имя пользователя: <input type="text" name="name" required><br><br>
    Эл. почта: <input type="email" name="email" required><br><br>
    Номер телефона: <input type="text" name="phoneNumber" required><br><br>
    Пароль: <input type="password" name="password" required><br><br>
    <input type="submit" value="Зарегистрироваться">
</form>
</body>
</html>
