<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8"/>
    <title>Gestión de Discografía</title>
    <style>
        body { font-family: Arial, sans-serif; max-width: 800px; margin: 40px auto; padding: 0 20px; }
        h1 { color: #333; }
        h2 { color: #555; margin-top: 30px; }
        ul { list-style: none; padding: 0; }
        li { margin: 8px 0; }
        a { color: #0066cc; text-decoration: none; padding: 6px 12px; border: 1px solid #0066cc; border-radius: 4px; display: inline-block; }
        a:hover { background-color: #0066cc; color: white; }
    </style>
</head>
<body>
<h1>Sistema de Gestión de Discografía</h1>

<h2>Artistas</h2>
<ul>
    <li><a href="${pageContext.request.contextPath}/artists">Listar todos los artistas</a></li>
    <li><a href="${pageContext.request.contextPath}/artists/create">Crear nuevo artista</a></li>
    <li><a href="${pageContext.request.contextPath}/artists/search">Buscar artista por nombre</a></li>
    <li><a href="${pageContext.request.contextPath}/artists/delete">Eliminar artista</a></li>
</ul>

<h2>Tracks</h2>
<ul>
    <li><a href="${pageContext.request.contextPath}/tracks">Listar todos los tracks</a></li>
    <li><a href="${pageContext.request.contextPath}/tracks/create">Crear nuevo track</a></li>
    <li><a href="${pageContext.request.contextPath}/tracks/delete">Eliminar track</a></li>
</ul>
</body>
</html>
