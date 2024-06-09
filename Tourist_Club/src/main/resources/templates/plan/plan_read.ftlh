<!doctype html>
<html lang="en">
<head>
    <title>Read</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        .back{
            background-color: rebeccapurple;
            color: white;
            top: 1px;
            right: 50px;
            position: absolute;
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
        <th>Название плана</th>
        <th>Дата начала плана</th>
        <th>Дата завершения плана</th>
        <th>Имя маршрута</th>
        <th>Тип маршрута</th>
    </tr>
    </thead>
    <tbody>

    <#list plans as plan>

        <tr>
            <td>${plan.id}</td>
            <td>${plan.planName}</td>
            <td>${plan.startCamping}</td>
            <td>${plan.endCamping}</td>
            <td>${plan.route.routeName}</td>
            <td>${plan.typeOfCamping.name()}</td>
        </tr>

    </#list>

    </tbody>
</table>

<form action="/plan" method="get" class="back">
    <input type="submit" value="Назад">
</form>

</body>
</html>