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
            width: 95%;
            max-width: 800px;
            padding: 30px;
            border: 2px solid #1e90ff;
            border-radius: 15px;
            background-color: #e6f7ff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        input[type="text"],
        input[type="date"],
        input[type="time"],
        select {
            width: 50%;
            padding: 10px;
            margin: 10px 0;
            text-align: center;
            align-self: center;
            border: 1px solid #1e90ff;
            border-radius: 5px;
        }

        input[type="date"] {
            width: calc(40% - 22px);
            padding: 10px;
            margin: 10px 0;
            text-align: center;
            border: 1px solid #1e90ff;
            border-radius: 5px;
        }

        select {
            width: calc(100% - 22px);
        }

    </style>
</head>
<body>

<div id="infoMessage" style="display:none; position: absolute; top: 20px;right: 750px ;padding: 10px; border: 1px solid #1e90ff; margin: 10px; background-color: #e6f7ff;">
    Расписание создано!
</div>

<form action="/section_work_schedule/create" method="post" class="section-form">
    Название тренировки: <input type="text" name="trainingName" required><br>
    Место: <input type="text" name="place" required><br>

    Даты проведения:<br>
    <input type="date" name="dateStart" required/>  -  <input type="date" name="dateEnd" required/><br>

    <label for="day">День недели:</label>
    <select id="day" name="day">
        <option value="monday">Понедельник</option>
        <option value="tuesday">Вторник</option>
        <option value="wednesday">Среда</option>
        <option value="thursday">Четверг</option>
        <option value="friday">Пятница</option>
        <option value="saturday">Суббота</option>
        <option value="sunday">Воскресенье</option>
    </select><br>

    <label for="time">Время начала:</label>
    <input type="time" id="timeBegin" name="timeBegin" required><br>
    <label for="time">Время конца:</label>
    <input type="time" id="timeEnd" name="timeEnd" required><br>

    <select name="idGroup">
        <#list groups as group>
            <option value="${group.id}">${group.nameGroup} ${group.section.typeOfSection.name}</option>
        </#list>
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

<form action="/section_work_schedule" method="get" class="back">
    <input type="submit" value="Назад">
</form>

</body>
</html>
