<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8"/>
    <title>Lista de Tracks</title>
    <style>
        body { font-family: Arial, sans-serif; max-width: 1100px; margin: 40px auto; padding: 0 20px; }
        table { border-collapse: collapse; width: 100%; margin-top: 16px; }
        th, td { border: 1px solid #ccc; padding: 8px 10px; text-align: left; }
        th { background-color: #f4f4f4; }
        .success { color: green; background: #e8f5e9; padding: 8px; border-radius: 4px; }
        a { color: #0066cc; text-decoration: none; }
    </style>
</head>
<body>
<h1>Tracks</h1>

<c:if test="${not empty param.success}">
    <p class="success">Track creado exitosamente.</p>
</c:if>

<c:choose>
    <c:when test="${empty tracks}">
        <p>No hay tracks registrados.</p>
    </c:when>
    <c:otherwise>
        <table>
            <tr>
                <th>ID</th>
                <th>Título</th>
                <th>Género</th>
                <th>Duración (s)</th>
                <th>Álbum</th>
                <th>Artistas</th>
            </tr>
            <c:forEach var="t" items="${tracks}">
                <tr>
                    <td>${t.id}</td>
                    <td>${t.title}</td>
                    <td>${t.genre}</td>
                    <td>${t.duration}</td>
                    <td>${t.albumTitle}</td>
                    <td>
                        <c:forEach var="a" items="${t.artists}" varStatus="s">
                            ${a.name}<c:if test="${!s.last}">, </c:if>
                        </c:forEach>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:otherwise>
</c:choose>

<div style="margin-top:20px">
    <a href="${pageContext.request.contextPath}/tracks/create">+ Nuevo track</a> &nbsp;
    <a href="${pageContext.request.contextPath}/index.jsp">Menú principal</a>
</div>
</body>
</html>
