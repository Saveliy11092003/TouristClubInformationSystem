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

        .back{
            background-color: rebeccapurple;
            color: white;
            top: 1px;
            right: 50px;
            position: absolute;
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
            <td>${stopping_point.id}</td>
            <td>${stopping_point.stoppingPointName}</td>
            <td><form action="/stopping_point/delete/${stopping_point.id}" method="post">
                    <input type="submit" value="Удалить точку остановки"/>
                </form></td>
        </tr>

    </#list>

    </tbody>
</table>

<form action="/stopping_point" method="get" class="back">
    <input type="submit" value="Назад">
</form>

</body>
</html>