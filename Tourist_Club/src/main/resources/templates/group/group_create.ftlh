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
        .form-container input[type="text"],
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
        .form-container input[type="checkbox"] {
            width: auto;
            padding: 0;
            margin: 0 10px 0 0;
        }
        .form-container label {
            display: block;
            margin: 10px 0;
        }
    </style>
</head>
<body>

<div id="infoMessage" style="display:none; position: absolute; top: 20px;right: 750px ;padding: 10px; border: 1px solid #1e90ff; margin: 10px; background-color: #e6f7ff;">
    Группа создана!
</div>

<div class="form-container">
    <form action="/group/create" method="post">
        <#list tourists as tourist>
            <#if tourist.hasGroup == false>
                <label>
                    <input type="checkbox" name="selectedTourists" value="${tourist.id}">
                    ${tourist.name} ${tourist.surname}
                </label>
            </#if>
        </#list>

        Название группы: <input type="text" name="nameGroup" required><br><br>

        <select name="sectionId" required>
            <#list sections as section>
                <option value="${section.id}">${section.name} ${section.typeOfSection.name}</option>
            </#list>
        </select><br><br>

        <select name="coachId" required>
            <#list coaches as coach>
                <option value="${coach.id}">${coach.tourist.name} ${coach.speciality}</option>
            </#list>
        </select><br><br>

        <input type="submit" value="Добавить группу"/>
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

<form action="/group" method="get" class="back">
    <input type="submit" value="Назад">
</form>

</body>
</html>
