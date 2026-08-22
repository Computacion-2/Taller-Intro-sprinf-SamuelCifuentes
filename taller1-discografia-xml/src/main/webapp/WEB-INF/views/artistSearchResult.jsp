<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8"/>
    <title>Resultado de Búsqueda</title>
    <style>
        body { font-family: Arial, sans-serif; max-width: 900px; margin: 40px auto; padding: 0 20px; }
        table { border-collapse: collapse; width: 100%; margin-top: 12px; }
        th, td { border: 1px solid #ccc; padding: 8px 12px; text-align: left; }
        th { background-color: #f4f4f4; }
        h2 { margin-top: 30px; }
        a { color: #0066cc; text-decoration: none; }
    </style>
</head>
<body>
<h1>Artista Encontrado</h1>

<table>
    <tr><th>ID</th><th>Nombre</th><th>Nacionalidad</th><th>Total Tracks</th></tr>
    <tr>
        <td>${artist.id}</td>
        <td>${artist.name}</td>
        <td>${artist.nationality}</td>
        <td>${artist.tracks.size()}</td>
    </tr>
</table>

<h2>Tracks de ${artist.name}</h2>
<c:choose>
    <c:when test="${empty artist.tracks}">
        <p>Este artista no tiene tracks registrados.</p>
    </c:when>
    <c:otherwise>
        <table>
            <tr>
                <th>ID</th>
                <th>Título</th>
                <th>Género</th>
                <th>Duración (s)</th>
                <th>Álbum</th>
            </tr>
            <c:forEach var="t" items="${artist.tracks}">
                <tr>
                    <td>${t.id}</td>
                    <td>${t.title}</td>
                    <td>${t.genre}</td>
                    <td>${t.duration}</td>
                    <td>${t.albumTitle}</td>
                </tr>
            </c:forEach>
        </table>
    </c:otherwise>
</c:choose>

<p>
    <a href="${pageContext.request.contextPath}/artists/search">Nueva búsqueda</a> &nbsp;|&nbsp;
    <a href="${pageContext.request.contextPath}/artists">Listado de artistas</a> &nbsp;|&nbsp;
    <a href="${pageContext.request.contextPath}/index.jsp">Menú principal</a>
</p>
</body>
</html>
