<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title>FreeMarker</title>
</head>
<body>
<h1>${user.name?if_exists}</h1>
<h1>${user.age}</h1>
<h1>${user.birthday?string('yyyy -MM-dd')}</h1>
<h1>
    <#list user.list! as item>
        <h5><p>${item}</p></h5>
    </#list>
</h1>
</body>
</html>