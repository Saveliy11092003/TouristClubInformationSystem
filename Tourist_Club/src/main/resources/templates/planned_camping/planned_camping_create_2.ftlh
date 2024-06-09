<!doctype html>
<html lang="en">
<head>
    <title>Document</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f8ff;
            height: 80vh;
            color: #333;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .back{
            background-color: rebeccapurple;
            color: white;
            top: 1px;
            right: 50px;
            position: absolute;
        }

        .form-container {
            width: 95%;
            max-width: 800px;
            padding: 5px; /* Further reduced padding */
            border: 2px solid #1e90ff;
            border-radius: 15px;
            background-color: #e6f7ff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .form-container input[type="text"],
        .form-container select,
        .form-container input[type="submit"] {
            width: calc(100% - 22px); /* Adjusted width */
            padding: 5px; /* Further reduced padding */
            margin: 3px 0; /* Further reduced margin */
            text-align: center;
            border: 1px solid #1e90ff;
            border-radius: 5px;
        }
        .form-container input[type="submit"] {
            background-color: #1e90ff;
            color: white;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        .form-container input[type="submit"]:hover {
            background-color: #104e8b;
        }
        .form-container label {
            display: block;
            margin-bottom: 15px;
        }
        .form-container .checkbox-input {
            display: inline-flex;
            margin-top: 10px;
        }
    </style>
</head>
<body>

<div id="infoMessage" style="display:none; position: absolute; top: 20px;right: 750px ;padding: 10px; border: 1px solid #1e90ff; margin: 10px; background-color: #e6f7ff;">
    Поход создан!
</div>

<div class="form-container">
    <form action="/planned_camping/create/choose_plan" method="post">
        Название похода: <input type="text" name="name" required><br>
        Дневник: <input type="text" name="diary" required><br>

        <input type="hidden" name="idPlan" value="${idPlan}">

        Выберите инструктора похода:
        <select name="idInstructor" required>
            <#list instructors as instructor>
                <option value="${instructor.id}">${instructor.name} ${instructor.surname} ${instructor.sex}</option>
            </#list>
        </select><br>

        Выбрать туристов в поход:<br>
        <#list tourists as tourist>

                ${tourist.name} ${tourist.surname}
                <input type="checkbox" name="idTourists" value="${tourist.id}"><br>

        </#list>

        <input type="submit" value="Добавить"/>
    </form>
</div>

<script>
    function displayInfoMessage() {
        var infoMessage = document.getElementById('infoMessage');
        if (${isCreated?c}) {
            infoMessage.style.display = 'block';
        } else {
            infoMessage.style.display = 'none';
        }
    }

    displayInfoMessage();
</script>

<form action="/planned_camping" method="get" class="back">
    <input type="submit" value="Назад">
</form>

</body>
</html>
