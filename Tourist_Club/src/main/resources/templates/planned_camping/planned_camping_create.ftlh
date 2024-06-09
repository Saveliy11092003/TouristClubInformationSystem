<!doctype html>
<html lang="en">
<head>
    <title>Document</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f8ff;
            color: #333;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 80vh;
        }


        .back{
            background-color: rebeccapurple;
            color: white;
            top: 1px;
            right: 50px;
            position: absolute;
        }

        .form-container {
            width: 90%;
            max-width: 800px;
            padding: 30px;
            border: 2px solid #1e90ff;
            border-radius: 15px;
            background-color: #e6f7ff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .form-container select,
        .form-container input[type="submit"] {
            width: 98%;
            padding: 15px;
            margin: 15px 0;
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
    </style>
</head>
<body>

<div class="form-container">
    <form action="/planned_camping/create/choose_plan" method="get">
        <label for="idPlan">Выберите план похода:</label>
        <select name="idPlan" id="idPlan" required>
            <#list plans as plan>
                <option value="${plan.id}">${plan.planName} ${plan.typeOfCamping.name()}</option>
            </#list>
        </select><br><br>

        <input type="submit" value="Добавить"/>
    </form>
</div>

<form action="/planned_camping" method="get" class="back">
    <input type="submit" value="Назад">
</form>

</body>
</html>
