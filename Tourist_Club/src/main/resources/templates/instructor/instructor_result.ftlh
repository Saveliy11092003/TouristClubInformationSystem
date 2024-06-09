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
        thead {
            background-color: #007bff;
            color: white;
        }

        .back{
            background-color: rebeccapurple;
            color: white;
            bottom: 50px;
            right: 50px;
            position: absolute;
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

    <b>Кол-во инструкторов:</b> ${count}<br><br>

    <thead>
    <tr>
        <th>Id</th>
        <th>Имя</th>
        <th>Фамилия</th>
        <th>Возраст</th>
        <th>Пол</th>
        <th>Дата рождения</th>
        <th>Сложность походов</th>
        <th></th>
    </tr>
    </thead>
    <tbody>

    <#list instructors as tourist>
        <tr>
            <td>${tourist.id}</td>
            <td>${tourist.name}</td>
            <td>${tourist.surname}</td>
            <td>${tourist.age}</td>
            <td>${tourist.sex}</td>
            <td>${tourist.dateOfBirth}</td>
            <td>${tourist.difficultyOfRoute}</td>
        </tr>

    </#list>

    </tbody>
</table>
</body>
</html>