<!doctype html>
<html lang="en">
<head>
    <title>Delete</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
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
            top: 50px;
            right: 50px;
            position: absolute;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        th, td {
            padding: 12px 15px;
            text-align: center; /* Выравнивание текста по центру */
        }
        thead {
            background-color: #007bff;
            color: white;
        }
        th {
            background-color: #007bff;
            color: white;
            text-transform: uppercase;
            letter-spacing: 0.1em;
            border-bottom: 2px solid #dee2e6;
        }
        tbody tr {
            background-color: #ffffff;
            border-bottom: 1px solid #dee2e6;
        }
        tbody tr:nth-of-type(even) {
            background-color: #f8f9fa;
        }
        tbody tr:hover {
            background-color: #e9ecef;
        }
    </style>

</head>
<body>

<h1> Прошедшие тренировки </h1>

<table border="3">
    <thead>
    <tr>
        <th>Id</th>
        <th>Имя тренера</th>
        <th>Название группы</th>
        <th>Посещаемость</th>
        <th>Место</th>
        <th>День недели</th>
        <th>Дата начала тренировки</th>
        <th>Дата конца тренировки</th>
        <th>Начало тренировки</th>
        <th>Конец тренировки</th>
    </tr>
    </thead>
    <tbody>

    <#list schedules as schedule>

        <#if schedule.isCompleted == true>

        <tr>
            <td>${schedule.id}</td>
            <td>${schedule.coach.tourist.name}</td>
            <td>${schedule.group.nameGroup}</td>
            <td>${schedule.attendance}</td>
            <td>${schedule.place}</td>
            <td>${schedule.day}</td>
            <td>${schedule.dateStart}</td>
            <td>${schedule.dateEnd}</td>
            <td>${schedule.startTraining}</td>
            <td>${schedule.endTraining}</td>
        </tr>

        </#if>
    </#list>

    </tbody>
</table>
    <br><br>

<h1>Тренировки, которые пройдут</h1>
<table border="3">
    <thead>
    <tr>
        <th>Id</th>
        <th>Название группы</th>
        <th>Место</th>
        <th>День недели</th>
        <th>Начало тренировки</th>
        <th>Конец тренировки</th>
    </tr>
    </thead>
    <tbody>

    <#list schedules as schedule>

        <#if schedule.isCompleted == false>

            <tr>
                <td>${schedule.id}</td>
                <td>${schedule.group.nameGroup}</td>
                <td>${schedule.place}</td>
                <td>${schedule.day}</td>
                <td>${schedule.startTraining}</td>
                <td>${schedule.endTraining}</td>
            </tr>

        </#if>
    </#list>

    </tbody>
</table>

<form action="/section_work_schedule" method="get" class="back">
    <input type="submit" value="Назад">
</form>

</body>
</html>