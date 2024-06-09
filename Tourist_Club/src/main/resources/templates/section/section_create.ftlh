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

        .section-form {
            width: 90%;
            max-width: 800px;
            padding: 30px;
            border: 2px solid #1e90ff;
            border-radius: 15px;
            background-color: #e6f7ff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        input[type="text"],
        input[type="number"],
        input[type="date"],
        select {
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
    Секция создана!
</div>

<form action="/section/create" method="post" class="section-form">
    Название секции: <input type="text" name="name" required/><br>
    Имя руководителя секции: <input type="text" name="supervisorName" required/><br>
    Фамилия руководителя секции: <input type="text" name="supervisorSurname" required/><br>
    Возраст: <input type="number" name="supervisorAge" required/><br>
    Зарплата: <input type="number" name="supervisorSalary" required/><br>
    Дата начала работы: <input type="date" name="dateOfEntry" required/><br>
    Дата день рождения: <input type="date" name="dateOfBirth" required/><br>
    Тип секции: <input type="text" name="typeOfSectionName" required/><br>

    Вид спорта:
    <select name="typeOfSport">
        <option value="FOOTBALL">Футбол</option>
        <option value="HORSEBACK">Конный спорт</option>
        <option value="SWIMMING">Плавание</option>
        <option value="MOUNTAIN">Горный спорт</option>
    </select><br>

    <input type="submit" value="Добавить"/>
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

<form action="/section" method="get" class="back">
    <input type="submit" value="Назад">
</form>


</body>
</html>
