<!doctype html>
<html lang="en">
<head>
    <title>Delete</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f8ff;
            color: #333;
            text-align: center;
            margin: 0;
            padding: 0;
        }


        .back{
            background-color: rebeccapurple;
            color: white;
            bottom: 50px;
            right: 50px;
            position: absolute;
        }

        .section-info {
            width: 80%;
            max-width: 800px;
            margin: 20px auto;
            padding: 20px;
            border: 2px solid #1e90ff;
            border-radius: 15px;
            background-color: #e6f7ff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .section-info b {
            font-weight: bold;
        }
        .training-hours {
            font-size: 1em;
            margin-top: 20px;
        }
    </style>
</head>
<body>

<div class="section-info">
    <b>Название:</b> ${section.name}, <b>Тип секции:</b> ${section.typeOfSection.typeOfSport.name()}<br><br>

    <b>Общая нагрузка:</b> ${total} <br><br>

    <div class="training-hours">
        <b>Часы тренировок:</b><br>
        <#list trainingHour as training>
            ${training}<br>
        </#list>
    </div>
</div>

</body>
</html>
