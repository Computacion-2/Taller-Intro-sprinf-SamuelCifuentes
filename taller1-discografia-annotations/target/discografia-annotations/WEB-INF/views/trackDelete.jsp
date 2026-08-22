<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8"/>
    <title>Eliminar Track</title>
    <style>
        body { font-family: Arial, sans-serif; max-width: 500px; margin: 40px auto; padding: 0 20px; }
        input[type=number] { width: 120px; padding: 6px; }
        input[type=submit] { padding: 6px 16px; background: #cc3300; color: white; border: none; border-radius: 4px; cursor: pointer; }
        .success { color: green; background: #e8f5e9; padding: 8px; border-radius: 4px; }
        .error { color: red; background: #fdecea; padding: 8px; border-radius: 4px; }
        a { color: #0066cc; text-decoration: none; }
    </style>
</head>
<body>
<h1>Eliminar Track</h1>

<c:if test="${not empty success}">
    <p class="success">${success}</p>
</c:if>
<c:if test="${not empty error}">
    <p class="error">${error}</p>
</c:if>

<form method="post" action="${pageContext.request.contextPath}/tracks/delete">
    <label for="id">ID del track:</label>
    <input type="number" id="id" name="id" min="1" required/>
    <input type="submit" value="Eliminar"/>
</form>

<p>
    <a href="${pageContext.request.contextPath}/tracks">Listado de tracks</a> &nbsp;|&nbsp;
    <a href="${pageContext.request.contextPath}/index.jsp">Menú principal</a>
</p>
</body>
</html>
