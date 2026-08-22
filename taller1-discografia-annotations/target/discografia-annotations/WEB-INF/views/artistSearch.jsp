<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8"/>
    <title>Buscar Artista</title>
    <style>
        body { font-family: Arial, sans-serif; max-width: 500px; margin: 40px auto; padding: 0 20px; }
        input[type=text] { width: 70%; padding: 6px; }
        input[type=submit] { padding: 6px 16px; background: #0066cc; color: white; border: none; border-radius: 4px; cursor: pointer; }
        .error, .notfound { color: red; background: #fdecea; padding: 8px; border-radius: 4px; margin-top: 12px; }
        a { color: #0066cc; text-decoration: none; }
    </style>
</head>
<body>
<h1>Buscar Artista por Nombre</h1>

<form method="post" action="${pageContext.request.contextPath}/artists/search">
    <input type="text" name="name" value="${searchName}" placeholder="Nombre del artista" required/>
    <input type="submit" value="Buscar"/>
</form>

<c:if test="${notFound}">
    <p class="notfound">No se encontró ningún artista con el nombre "<strong>${searchName}</strong>".</p>
</c:if>
<c:if test="${not empty error}">
    <p class="error">${error}</p>
</c:if>

<p><a href="${pageContext.request.contextPath}/index.jsp">Menú principal</a></p>
</body>
</html>
