<!doctype html>
<html lang="en">
<head>
    <title>Create</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f8ff;
            color: #333;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .back{
            background-color: rebeccapurple;
            color: white;
            top: 1px;
            right: 50px;
            position: absolute;
        }

        .tourist-form {
            width: 95%;
            max-width: 800px;
            padding: 30px;
            border: 2px solid #1e90ff;
            border-radius: 15px;
            background-color: #e6f7ff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            display: flex;
            flex-direction: column; /* Устанавливаем направление столбцом */
            align-items: center; /* Выравниваем элементы по центру */
        }
        input[type="text"],
        input[type="number"],
        select,
        input[type="date"] {
            width: calc(100% - 22px);
            padding: 10px;
            margin: 10px 0;
            text-align: center;
            border: 1px solid #1e90ff;
            border-radius: 5px;
        }

    </style>
</head>
<body>

<div id="infoMessage" style="display:none; position: absolute; top: 20px;right: 750px ;padding: 10px; border: 1px solid #1e90ff; margin: 10px; background-color: #e6f7ff;">
    Турист создан!
</div>

<form action="/tourist/create" method="post" class="tourist-form">
    Имя: <input type="text" name="name" required/><br><br>
    Фамилия: <input type="text" name="surname" required/><br><br>
    Возраст: <input type="number" name="age" required/><br><br>

    Выберите пол:<br><br>
    <select name="sex">
        <option value="female">Женский</option>
        <option value="male">Мужской</option>
    </select><br><br>

    Дата рождения: <input type="date" name="dateOfBirth" required/><br><br>
    Сложность походов, которые преодолевал:
    <select name="difficultyOfRoute">
        <option value="EASY">Легкий</option>
        <option value="MIDDLE">Средний</option>
        <option value="HARD">Сложный</option>
    </select><br><br>

    <input type="submit" value="Добавить туриста"/>
</form>

<script>
    function displayInfoMessage() {
        var infoMessage = document.getElementById('infoMessage');
        if (${isCreated?c}) {
            infoMessage.style.display = 'block';
        } else {
            infoMessage.style.display = 'none';
        }
    }

    displayInfoMessage();
</script>

<form action="/tourist" method="get" class="back">
    <input type="submit" value="Назад">
</form>

</body>
</html>
