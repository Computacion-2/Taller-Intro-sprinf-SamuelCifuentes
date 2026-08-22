<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8"/>
    <title>Crear Artista</title>
    <style>
        body { font-family: Arial, sans-serif; max-width: 500px; margin: 40px auto; padding: 0 20px; }
        label { display: block; margin-top: 12px; font-weight: bold; }
        input[type=text] { width: 100%; padding: 6px; margin-top: 4px; box-sizing: border-box; }
        input[type=submit] { margin-top: 16px; padding: 8px 20px; background: #0066cc; color: white; border: none; border-radius: 4px; cursor: pointer; }
        .error { color: red; background: #fdecea; padding: 8px; border-radius: 4px; }
        a { color: #0066cc; text-decoration: none; }
    </style>
</head>
<body>
<h1>Crear Nuevo Artista</h1>

<c:if test="${not empty error}">
    <p class="error">${error}</p>
</c:if>

<form method="post" action="${pageContext.request.contextPath}/artists/create">
    <label for="name">Nombre</label>
    <input type="text" id="name" name="name" required placeholder="Ej: Shakira"/>

    <label for="nationality">Nacionalidad</label>
    <input type="text" id="nationality" name="nationality" required placeholder="Ej: Colombiana"/>

    <input type="submit" value="Crear Artista"/>
</form>

<p><a href="${pageContext.request.contextPath}/artists">Volver al listado</a></p>
</body>
</html>
