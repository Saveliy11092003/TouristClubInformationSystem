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
        <th>Имя любителя:</th>
        <th>Фамилия любителя:</th>
        <th>Опыт деятельности любителя:</th>
    </tr>
    </thead>
    <tbody>

    <#list athletes as athlete>

        <tr>
            <td>${athlete.id}</td>
            <td>${athlete.tourist.name}</td>
            <td>${athlete.tourist.surname}</td>
            <td>${athlete.yearOfWorkExperience}</td>
        </tr>

    </#list>

    </tbody>
</table>

<form action="/athlete" method="get" class="back">
    <input type="submit" value="Назад">
</form>

</body>
</html>