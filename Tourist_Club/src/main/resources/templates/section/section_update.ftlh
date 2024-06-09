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
        <th></th>
    </tr>
    </thead>
    <tbody>

    <#list sections as section>
        <tr>
            <form action="/section/update/${section.id}" method="post">
                <input type="hidden" name="id" value="${section.id}">
                <input type="hidden" name="supervisor.id" value="${section.supervisor.id}">
                <input type="hidden" name="typeOfSection.id" value="${section.typeOfSection.id}">
                <td>${section.id}</td>
                <td><input type="text" name="name" value="${section.name}" required></td>
                <td><input type="text" name="typeOfSection.name" value="${section.typeOfSection.name}" required></td>
                <td><input type="text" name="supervisor.name" value="${section.supervisor.name}" required/></td>
                <td><input type="text" name="supervisor.surname" value="${section.supervisor.surname}" required/></td>
                <td><input type="number" name="supervisor.age" value="${section.supervisor.age}" required/></td>
                <td><input type="text" name="supervisor.salary" value="${section.supervisor.salary}" required></td>
                <td><input type="date" name="supervisor.dateOfEntry" value="${section.supervisor.dateOfEntry}" required/></td>
                <td><input type="date" name="supervisor.dateOfBirth" value="${section.supervisor.dateOfBirth}" required/></td>
                <td>
                    <input type="submit" value="Обновить"/>
                </td>
            </form>
        </tr>

    </#list>


    </tbody>
</table>

<form action="/section" method="get" class="back">
    <input type="submit" value="Назад">
</form>

</body>
</html>
