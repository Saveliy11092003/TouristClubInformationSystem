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

        .back{
            background-color: rebeccapurple;
            color: white;
            top: 1px;
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
<table border="3">
    <thead>
    <tr>
        <th>Id</th>
        <th>Имя тренера</th>
        <th>Фамилия тренера:</th>
        <th>Зарплата тренера</th>
        <th>Специальность тренера</th>
        <th></th>
    </tr>
    </thead>
    <tbody>

    <#list coaches as coach>
        <tr>
            <form action="/coach/update/${coach.id}" method="post">
                <input type="hidden" name="id" value="${coach.id}">
                <td>${coach.id}</td>
                <td><input type="text" name="tourist.name" value="${coach.tourist.name}" required></td>
                <td><input type="text" name="tourist.surname" value="${coach.tourist.surname}" required></td>
                <td><input type="text" name="salary" value="${coach.salary}" required/></td>
                <td> ${coach.speciality}<br> <select name="speciality">
                        <option value="FOOTBALL">Футбол</option>
                        <option value="SWIMMING_SPORT">Плавание</option>
                        <option value="HORSEBACK_RIDING">Конный спорт</option>
                        <option value="MOUNTAIN_SPORT">Горный спорт</option>
                    </select><br><br></td>
                <td>
                    <input type="submit" value="Обновить"/>

                </td>
            </form>
        </tr>

    </#list>

    </tbody>
</table>

<form action="/coach" method="get" class="back">
    <input type="submit" value="Назад">
</form>

</body>
</html>