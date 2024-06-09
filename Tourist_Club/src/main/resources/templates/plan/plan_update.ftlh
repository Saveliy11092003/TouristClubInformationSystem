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
        <th>Название маршрута</th>
        <th>Тип маршрута</th>
        <th></th>
    </tr>
    </thead>
    <tbody>

    <#list plans as plan>

        <tr>
            <form action="/plan/update/${plan.id}" method="post">
                <input type="hidden" name="id" value="${plan.id}">
                <td>${plan.id}</td>
                <td><input type="text" name="planName" value="${plan.planName}" required></td>
                <td><input type="text" name="startCamping" value="${plan.startCamping}" required></td>
                <td><input type="text" name="endCamping" value="${plan.endCamping}" required></td>
                <td> ${plan.route.routeName} <br> <select name="routeId">
                    <#list routes as route>
                        <option value="${route.id}">${route.routeName}</option>
                    </#list>
                    <input type="hidden" name="touristId"><br><br>
                </select>
                </td>
                <td>${plan.typeOfCamping.name()}</td>
                <td>
                    <input type="submit" value="Обновить"/>
                </td>
            </form>
        </tr>

    </#list>

    </tbody>
</table>

<form action="/plan" method="get" class="back">
    <input type="submit" value="Назад">
</form>
</body>
</html>