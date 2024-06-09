<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Panel</title>

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

        .back{
            background-color: rebeccapurple;
            color: white;
            bottom: 50px;
            right: 50px;
            position: absolute;
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

        h1 {
            text-align: center;
            color: #0056b3;
        }

    </style>

</head>
<body>

<#if error == false>

<h1>Результат запроса:</h1>
<table border="3">
    <thead>
    <tr>
        <#list columns as column>
            <th>${column}</th>
        </#list>
    </tr>
    </thead>
    <tbody>
    <#list rows as row>
        <tr>
            <#list row as element>
                <td>${element}</td>
            </#list>
        </tr>
    </#list>
    </tbody>
</table>

</#if>

<#if error == true>
    <h1>Разрешены только SELECT запросы</h1>
</#if>

<form action="/" method="get" class="back">
    <input type="submit" value="Назад">
</form>

</body>
</html>
