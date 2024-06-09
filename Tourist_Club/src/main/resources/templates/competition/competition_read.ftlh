<!doctype html>
<html lang="en">
<head>
    <title>Read</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f8ff;
            color: #333;
            text-align: center;
            margin: 0;
            padding: 0;
        }
        .competition-container {
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

        .back{
            background-color: rebeccapurple;
            color: white;
            top: 1px;
            right: 50px;
            position: absolute;
        }

        .competition-title {
            font-size: 1.5em;
            color: #1e90ff;
            margin-bottom: 10px;
        }
        .competition-description {
            font-size: 1.2em;
            margin-bottom: 20px;
        }
        .athlete-info {
            font-size: 1em;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>

<#list competitions as competition>
    <div class="competition-container">
        <div class="competition-title"><b>Название соревнования: </b> ${competition.nameCompetition}</div>
        <div class="competition-description"><b>Описание: </b> ${competition.description}</div>
        <#list competition.athletes as athlete>
            <div class="athlete-info">
                ${athlete.tourist.name} ${athlete.tourist.surname} - ${athlete.yearOfWorkExperience} лет опыта
            </div>
        </#list>
    </div>
</#list>

<form action="/competition" method="get" class="back">
    <input type="submit" value="Назад">
</form>

</body>
</html>
