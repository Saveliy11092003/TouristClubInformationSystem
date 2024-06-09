<!doctype html>
<html lang="en">
<head>
    <title>Document</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f8ff;
            color: #333;
            text-align: center;
            margin: 0;
            padding: 0;
        }
        form {
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
            bottom: 50px;
            right: 50px;
            position: absolute;
        }

        select, input[type="number"], input[type="submit"] {
            width: calc(100% - 20px);
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #1e90ff;
            border-radius: 5px;
        }
        input[type="submit"] {
            background-color: #1e90ff;
            color: white;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        input[type="submit"]:hover {
            background-color: #104e8b;
        }
    </style>
</head>
<body>
<form action="/instructor/request3" method="post">
    <label for="typeInstructor">Выберите тип инструктора:</label><br><br>
    <select name="typeInstructor">
        <option value="instructor">Инструктор</option>
        <option value="instructor-athlete">Инструктор-спортсмен</option>
        <option value="instructor-coach">Инструктор-тренер</option>
        <input type="hidden" name="touristId">
    </select><br><br>

    <label for="idCamping">Выберите поход:</label><br><br>
    <#list campings as camping>
        <div>
            <label>
                Название похода: ${camping.name}<br>
                Имя инструктора: ${camping.instructor.name}<br>
                Фамилия инструктора: ${camping.instructor.surname}<br>
                Тип похода: ${camping.plan.typeOfCamping.name()}<br>
                <input type="radio" name="idCamping" value="${camping.id}"><br><br>
            </label>
        </div>
    </#list> <br><br>

    <input type="submit" value="Выполнить запрос"/>
</form>
</body>
</html>
