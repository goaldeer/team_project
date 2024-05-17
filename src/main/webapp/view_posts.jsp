<!DOCTYPE html>
<html>
<head>
    <title>Posts</title>
</head>
<body>
    <h2>Posts</h2>
    <c:forEach var="post" items="${posts}">
        <h3>${post.title}</h3>
        <p>${post.content}</p>
        <hr>
    </c:forEach>
</body>
</html>
