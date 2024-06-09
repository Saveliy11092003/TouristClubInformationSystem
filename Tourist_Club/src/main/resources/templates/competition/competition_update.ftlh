<!doctype html>
<html lang="en">
<head>
    <title>Update</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f8ff;
            color: #333;
            text-align: center;
            margin: 0;
            padding: 0;
        }
        .form-container {
            display: inline-block;
            width: 80%;
            max-width: 800px;
            margin: 20px auto;
            padding: 20px;
            border: 2px solid #1e90ff;
            border-radius: 15px;
            background-color: #e6f7ff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .form-container input[type="text"] {
            width: calc(100% - 22px);
            padding: 10px;
            margin: 10px 0;
            text-align: center;
            border: 1px solid #1e90ff;
            border-radius: 5px;
        }
        .form-container input[type="checkbox"] {
            margin: 10px 0;
        }

        .back{
            background-color: rebeccapurple;
            color: white;
            top: 1px;
            right: 50px;
            position: absolute;
        }

        .form-container input[type="submit"] {
            background-color: #1e90ff;
            color: white;
            cursor: pointer;
            transition: background-color 0.3s;
            border: none;
            border-radius: 5px;
            padding: 10px 20px; /* Отступы */
            margin-top: 20px; /* Отступ от других элементов формы */
        }
        .form-container input[type="submit"]:hover {
            background-color: #104e8b;
        }
    </style>
</head>
<body>

<#list competitions as competition>
    <div class="form-container">
        <form action="/competition/update/${competition.id}" method="post">
            <b>Название соревнования: </b> <input type="text" name="nameCompetition" value="${competition.nameCompetition}" required><br>
            <b>Описание соревнования: </b> <input type="text" name="description" value="${competition.description}" required> <br>
            <b>Список участников: </b><br>
            <#list athletes as athlete>
                <#if competition.athletes?seq_contains(athlete)>
                    <input type="checkbox" name="selectedAthletes" value="${athlete.id}" checked>
                    ${athlete.tourist.name}${athlete.tourist.surname}<br>
                <#else>
                    <input type="checkbox" name="selectedAthletes" value="${athlete.id}">
                    ${athlete.tourist.name}${athlete.tourist.surname}<br>
                </#if>
            </#list>
            <input type="submit" value="Обновить"/>
        </form>
    </div>
</#list>

<form action="/competition" method="get" class="back">
    <input type="submit" value="Назад">
</form>

</body>
</html>
