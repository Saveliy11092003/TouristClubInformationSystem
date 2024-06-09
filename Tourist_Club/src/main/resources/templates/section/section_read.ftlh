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
        <th>Название секции</th>
        <th>Вид секции</th>
        <th>Имя руководителя</th>
        <th>Фамилия руководителя</th>
        <th>Возраст руководителя</th>
        <th>Зарплата руководителя</th>
        <th>Дата начала работы</th>
        <th>Дата рождения руководителя</th>
    </tr>
    </thead>
    <tbody>

    <#list sections as section>

        <tr>
            <td>${section.id}</td>
            <td>${section.name}</td>
            <td>${section.typeOfSection.name}</td>
            <td>${section.supervisor.name}</td>
            <td>${section.supervisor.surname}</td>
            <td>${section.supervisor.age}</td>
            <td>${section.supervisor.salary}</td>
            <td>${section.supervisor.dateOfEntry}</td>
            <td>${section.supervisor.dateOfBirth}</td>
        </tr>

    </#list>

    </tbody>
</table>

<form action="/section" method="get" class="back">
    <input type="submit" value="Назад">
</form>

</body>
</html>