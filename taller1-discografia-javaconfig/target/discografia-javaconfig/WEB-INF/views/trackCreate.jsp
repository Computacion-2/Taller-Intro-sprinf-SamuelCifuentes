<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8"/>
    <title>Crear Track</title>
    <style>
        body { font-family: Arial, sans-serif; max-width: 560px; margin: 40px auto; padding: 0 20px; }
        label { display: block; margin-top: 12px; font-weight: bold; }
        input[type=text], input[type=number] { width: 100%; padding: 6px; margin-top: 4px; box-sizing: border-box; }
        select[multiple] { width: 100%; height: 160px; margin-top: 4px; }
        input[type=submit] { margin-top: 16px; padding: 8px 20px; background: #0066cc; color: white; border: none; border-radius: 4px; cursor: pointer; }
        .error { color: red; background: #fdecea; padding: 8px; border-radius: 4px; }
        .hint { font-size: 0.85em; color: #666; }
        a { color: #0066cc; text-decoration: none; }
    </style>
</head>
<body>
<h1>Crear Nuevo Track</h1>

<c:if test="${not empty error}">
    <p class="error">${error}</p>
</c:if>

<form method="post" action="${pageContext.request.contextPath}/tracks/create">
    <label for="title">Título</label>
    <input type="text" id="title" name="title" required placeholder="Ej: Bohemian Rhapsody"/>

    <label for="genre">Género</label>
    <input type="text" id="genre" name="genre" required placeholder="Ej: Rock"/>

    <label for="duration">Duración (segundos)</label>
    <input type="number" id="duration" name="duration" required min="1" placeholder="Ej: 354"/>

    <label for="albumTitle">Álbum</label>
    <input type="text" id="albumTitle" name="albumTitle" required placeholder="Ej: A Night at the Opera"/>

    <label for="artistIds">Artistas <span class="hint">(Ctrl/Cmd + clic para seleccionar varios)</span></label>
    <select id="artistIds" name="artistIds" multiple required>
        <c:forEach var="a" items="${artists}">
            <option value="${a.id}">${a.name} (${a.nationality})</option>
        </c:forEach>
    </select>

    <input type="submit" value="Crear Track"/>
</form>

<p><a href="${pageContext.request.contextPath}/tracks">Volver al listado</a></p>
</body>
</html>
