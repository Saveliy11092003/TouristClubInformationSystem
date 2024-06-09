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
            bottom: 50px;
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
            text-align: center;
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

<b>Кол-во тренеров:</b> ${countOfCoaches}<br><br>

<table border="3">
    <thead>
    <tr>
        <th>Id</th>
        <th>Имя тренера</th>
        <th>Фамилия тренера:</th>
        <th>Зарплата тренера</th>
        <th>Специальность тренера</th>
    </tr>
    </thead>
    <tbody>

    <#list coaches as coach>

        <tr>
            <td>${coach.id}</td>
            <td>${coach.tourist.name}</td>
            <td>${coach.tourist.surname}</td>
            <td>${coach.salary}</td>
            <td>${coach.speciality}</td>
        </tr>

    </#list>

    </tbody>
</table>
</body>
</html>