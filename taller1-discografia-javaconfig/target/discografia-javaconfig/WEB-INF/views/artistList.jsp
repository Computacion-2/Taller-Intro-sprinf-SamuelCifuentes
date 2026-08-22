<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8"/>
    <title>Lista de Artistas</title>
    <style>
        body { font-family: Arial, sans-serif; max-width: 900px; margin: 40px auto; padding: 0 20px; }
        table { border-collapse: collapse; width: 100%; margin-top: 16px; }
        th, td { border: 1px solid #ccc; padding: 8px 12px; text-align: left; }
        th { background-color: #f4f4f4; }
        .success { color: green; background: #e8f5e9; padding: 8px; border-radius: 4px; }
        a { color: #0066cc; text-decoration: none; }
        a:hover { text-decoration: underline; }
        .nav { margin-top: 20px; }
    </style>
</head>
<body>
<h1>Artistas</h1>

<c:if test="${not empty param.success}">
    <p class="success">Artista creado exitosamente.</p>
</c:if>

<c:choose>
    <c:when test="${empty artists}">
        <p>No hay artistas registrados.</p>
    </c:when>
    <c:otherwise>
        <table>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Nacionalidad</th>
                <th>Tracks</th>
                <th>Acciones</th>
            </tr>
            <c:forEach var="a" items="${artists}">
                <tr>
                    <td>${a.id}</td>
                    <td>${a.name}</td>
                    <td>${a.nationality}</td>
                    <td>${a.tracks.size()}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/artists/search">Buscar</a> |
                        <a href="${pageContext.request.contextPath}/artists/delete">Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:otherwise>
</c:choose>

<div class="nav">
    <a href="${pageContext.request.contextPath}/artists/create">+ Nuevo artista</a> &nbsp;
    <a href="${pageContext.request.contextPath}/index.jsp">Menú principal</a>
</div>
</body>
</html>
