<!doctype html>
<html lang="en">
<head>
    <title>Document</title>
</head>
<body>
<form action="/planned_camping/create/choose_plan" method="post">

    <#list pl></#list>
    Название похода: <input type="text" name="name" value="" required><br><br>
    Дневник: <input type="text" name="diary"required><br><br>

    <input type="hidden" name="idPlan" value="${idPlan}">

    Выберите инструктора похода:
    <select name="idInstructor">
        <#list instructors as instructor>
            <option value="${instructor.id}">${instructor.name} ${instructor.surname} ${instructor.sex}</option>
        </#list>
        <input type="hidden" name="idInstructor"><br><br>
    </select><br><br>

    Выбрать туристов в поход:<br>
    <#list tourists as tourist>
        <input type="checkbox" name="idTourists" value="${tourist.id}">
        ${tourist.name} ${tourist.surname}<br>
    </#list>


    <input type="submit" value="Добавить"/>
</form>
</body>
</html>