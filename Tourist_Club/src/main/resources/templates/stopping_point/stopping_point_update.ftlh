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
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        th, td {
            padding: 12px 15px;
            text-align: center; /* Выравнивание текста по центру */
        }

        .back{
            background-color: rebeccapurple;
            color: white;
            top: 1px;
            right: 50px;
            position: absolute;
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
        <th>Название точки остановки:</th>
        <th></th>
    </tr>
    </thead>
    <tbody>

    <#list stopping_points as stopping_point>
        <tr>
            <form action="/stopping_point/update/${stopping_point.id}" method="post">
                <input type="hidden" name="id" value="${stopping_point.id}">
                <td>${stopping_point.id}</td>
                <td><input type="text" name="stoppingPointName" value="${stopping_point.stoppingPointName}"></td>
                <td>
                    <input type="submit" value="Обновить"/>
                </td>
            </form>
        </tr>

    </#list>

    </tbody>
</table>

<form action="/stopping_point" method="get" class="back">
    <input type="submit" value="Назад">
</form>

</body>
</html>