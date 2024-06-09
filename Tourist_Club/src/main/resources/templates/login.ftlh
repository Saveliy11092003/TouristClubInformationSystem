<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Tourist Club</title>
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

        hr {
            width: 80%;
            border: 1px solid #0288d1;
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

        form input[type="email"],
        form input[type="password"] {
            width: 250px;
            padding: 10px;
            margin: 10px 0;
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

        a {
            color: #01579b;
            text-decoration: none;
            margin-top: 15px;
            display: inline-block;
        }

        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<h1>Tourist Club</h1>
<hr>
<h4>Авторизация</h4>
<form action="/login" method="post">
    <label for="username">Эл. почта:</label><br>
    <input type="email" id="username" name="username" required><br><br>
    <label for="password">Пароль:</label><br>
    <input type="password" id="password" name="password" required><br><br>
    <input type="submit" value="Вход">

</form>
<a href="/registration">Зарегистрироваться</a>
</body>
</html>
