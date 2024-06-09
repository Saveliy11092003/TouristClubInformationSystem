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

<h1>Прошедшие тренировки</h1>

<table border="3">
    <thead>
    <tr>
        <th>Id</th>
        <th>Имя тренера</th>
        <th>Название группы</th>
        <th>Название тренировки</th>
        <th>Посещаемость</th>
        <th>Место</th>
        <th>Дата начала тренировки</th>
        <th>Дата конца тренировки</th>
        <th>День недели</th>
        <th>Начало тренировки</th>
        <th>Конец тренировки</th>
        <th></th>
    </tr>
    </thead>
    <tbody>

    <#list schedules as schedule>

        <#if schedule.isCompleted == true>

            <tr>
                <td>${schedule.id}</td>
                <form action="/section_work_schedule/update/${schedule.id}" method="post">
                    <input type="hidden" name="id" value="${schedule.id}">

                    <td> ${schedule.coach.tourist.name} <br>
                        <select name="idCoach">
                            <#list coaches as coach>
                                <option value="${coach.id}">${coach.tourist.name} ${coach.speciality}</option>
                            </#list>
                            <input type="hidden" name="idCoach"><br><br>
                        </select>
                    </td>

                    <td> ${schedule.group.nameGroup} <br>
                        <select name="idGroup">
                            <#list groups as group>
                                <option value="${group.id}">${group.nameGroup} ${group.section.typeOfSection}</option>
                            </#list>
                            <input type="hidden" name="idGroup"><br><br>
                        </select>
                    </td>

                    <td><input type="text" name="trainingName" value="${schedule.trainingName}"></td>
                    <td><input type="number" name="attendance" value="${schedule.attendance}"></td>
                    <td><input type="text" name="place" value="${schedule.place}"></td>
                    <td><input type="date" name="dateStart" value="${schedule.dateStart}"></td>
                    <td><input type="date" name="dateEnd" value="${schedule.dateEnd}"></td>
                    <td><input type="text" name="day" value="${schedule.day}"></td>
                    <td><input type="time" name="timeBegin" value="${schedule.startTraining}"></td>
                    <td><input type="time" name="timeEnd" value="${schedule.endTraining}"></td>
                    <td><input type="submit" value="Обновить"/></td>
                </form>
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
        <th>Имя тренера</th>
        <th>Название группы</th>
        <th>Название тренировки</th>
        <th>Посещаемость</th>
        <th>Место</th>
        <th>Дата начала тренировки</th>
        <th>Дата конца тренировки</th>
        <th>День недели</th>
        <th>Начало тренировки</th>
        <th>Конец тренировки</th>
        <th></th>
    </tr>
    </thead>
    <tbody>

    <#list schedules as schedule>

        <#if schedule.isCompleted == false>

            <tr>
                <td>${schedule.id}</td>
                <form action="/section_work_schedule/update/${schedule.id}" method="post">
                    <input type="hidden" name="id" value="${schedule.id}">

                    <td>
                        <select name="idCoach">
                            <#list coaches as coach>
                                <option value="${coach.id}">${coach.tourist.name} ${coach.speciality}</option>
                            </#list>
                            <input type="hidden" name="idCoach"><br><br>
                        </select>
                    </td>

                    <td> ${schedule.group.nameGroup} <br>
                        <select name="idGroup">
                            <#list groups as group>
                                <option value="${group.id}">${group.nameGroup} ${group.section.typeOfSection}</option>
                            </#list>
                            <input type="hidden" name="idGroup"><br><br>
                        </select>
                    </td>
                    <td><input type="text" name="trainingName" value="${schedule.trainingName}"></td>
                    <td><input type="text" name="attendance" value="" required></td>
                    <td><input type="text" name="place" value="${schedule.place}"></td>
                    <td><input type="date" name="dateStart" value="${schedule.dateStart}"></td>
                    <td><input type="date" name="dateEnd" value="${schedule.dateEnd}"></td>
                    <td><input type="text" name="day" value="${schedule.day}"></td>
                    <td><input type="time" name="timeBegin" value="${schedule.startTraining}"></td>
                    <td><input type="time" name="timeEnd" value="${schedule.endTraining}"></td>
                    <td><input type="submit" value="Обновить"/></td>
                </form>
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


