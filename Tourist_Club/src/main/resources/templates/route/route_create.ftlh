<!doctype html>
<html lang="en">
<head>
    <title>Document</title>
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
            height: 80vh;
        }

        .back{
            background-color: rebeccapurple;
            color: white;
            top: 1px;
            right: 50px;
            position: absolute;
        }

        .form-container {
            width: 90%;
            max-width: 800px;
            padding: 30px;
            border: 2px solid #1e90ff;
            border-radius: 15px;
            background-color: #e6f7ff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .form-container select,
        .form-container input[type="text"],
        .form-container input[type="submit"] {
            width: 98%;
            padding: 15px;
            margin: 15px 0;
            text-align: center;
            border: 1px solid #1e90ff;
            border-radius: 5px;
        }
        .form-container input[type="submit"] {
            background-color: #1e90ff;
            color: white;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        .form-container input[type="submit"]:hover {
            background-color: #104e8b;
        }
        .form-container label {
            margin-bottom: 10px;
        }
    </style>
</head>
<body>

<div id="infoMessage" style="display:none; position: absolute; top: 20px;right: 750px ;padding: 10px; border: 1px solid #1e90ff; margin: 10px; background-color: #e6f7ff;">
    Маршрут создан!
</div>

<div class="form-container">
    <form action="/route/create" method="post">
        <label for="selectedPoints">Выберите точки маршрута:</label><br>
        <#list points as point>
            <label>
                <input type="checkbox" name="selectedPoints" value="${point.id}">
                ${point.stoppingPointName}
            </label><br>
        </#list>

        <label for="routeName">Название маршрута:</label>
        <input type="text" name="routeName" required><br>

        <label for="length">Длина маршрута:</label>
        <input type="text" name="length" required><br>

        <label for="difficulty">Сложность маршрута:</label>
        <select name="difficulty">
            <option value="easy">Легкий</option>
            <option value="middle">Средний</option>
            <option value="hard">Сложный</option>
        </select><br>

        <input type="submit" value="Добавить маршрут"/>
    </form>
</div>

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

<form action="/route" method="get" class="back">
    <input type="submit" value="Назад">
</form>

</body>
</html>
