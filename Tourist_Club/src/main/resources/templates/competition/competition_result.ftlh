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
        b {
            font-weight: bold;
        }

        .back{
            background-color: rebeccapurple;
            color: white;
            bottom: 50px;
            right: 50px;
            position: absolute;
        }

    </style>
</head>
<body>

<b>Кол-во соревнований:</b> ${countOfCompetition}<br><br>

<#list competitions as competition>
    <div style="border: 2px solid #1e90ff; border-radius: 10px; padding: 10px; margin-bottom: 20px;">
        <b>Название соревнования: </b> ${competition.nameCompetition}<br>
        <b>Описание: </b> ${competition.description}<br>
        <div style="margin-top: 10px;">
            <#list competition.athletes as athlete>
                ${athlete.tourist.name} ${athlete.tourist.surname} ${athlete.yearOfWorkExperience}<br>
            </#list>
        </div>
    </div>
</#list>

</body>
</html>
