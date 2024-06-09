<!doctype html>
<html lang="en">
<head>
    <title>Update</title>

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

    <#list tourists as tourist>
        <tr>
            <form action="/tourist/update/${tourist.id}" method="post">
                <input type="hidden" name="id" value="${tourist.id}">
                <td>${tourist.id}</td>
                <td><input type="text" name="name" value="${tourist.name}" required></td>
                <td><input type="text" name="surname" value="${tourist.surname}" required></td>
                <td><input type="text" name="age" value="${tourist.age}" required></td>
                <td><input type="text" name="sex" value="${tourist.sex}" required></td>
                <td><input type="text" name="dateOfBirth" value="${tourist.dateOfBirth}" required></td>
                <td>${tourist.difficultyOfRoute}<br>
                    <select name="difficultyOfRoute">
                        <option value="EASY">Легкий</option>
                        <option value="MIDDLE">Средний</option>
                        <option value="HARD">Сложный</option>
                    </select><br><br>
                </td>

                <td>
                    <input type="submit" value="Обновить"/>
                </td>
            </form>
        </tr>

    </#list>


    </tbody>
</table>

<form action="/tourist" method="get" class="back">
    <input type="submit" value="Назад">
</form>

</body>
</html>