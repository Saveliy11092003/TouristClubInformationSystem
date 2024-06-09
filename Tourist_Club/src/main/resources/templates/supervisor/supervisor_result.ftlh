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
            text-align: center;
        }

        .back{
            background-color: rebeccapurple;
            color: white;
            bottom: 50px;
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
        <th>Имя руководителя</th>
        <th>Фамилия руководителя</th>
        <th>Зарплата руководителя</th>
        <th>Дата рождения руководителя</th>
        <th>Дата поступления на работу</th>
    </tr>
    </thead>
    <tbody>

    <#if supervisors?? && supervisors?size gt 0>
        <#list supervisors as supervisor>
            <tr>
                <td>${supervisor.id}</td>
                <td>${supervisor.name}</td>
                <td>${supervisor.surname}</td>
                <td>${supervisor.salary}</td>
                <td>${supervisor.dateOfBirth}</td>
                <td>${supervisor.dateOfEntry}</td>
            </tr>
        </#list>
    <#else>
        <tr>
            <td colspan="6">Ничего не найдено</td>
        </tr>
    </#if>

    </tbody>
</table>
</body>
</html>
