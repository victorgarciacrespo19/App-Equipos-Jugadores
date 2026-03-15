<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${mvc.locale}"/>
<fmt:setBundle basename="messages" var="messages"/>

<html>
<head>
    <title><fmt:message bundle="${messages}" key="title.equipos"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-SgOJa3DmI69IUzQ2PVdRZhwQ+dy64/BUtbMJw1MZ8t5HZApcHrRKUc4W0kG879m7" crossorigin="anonymous">
    <style>
        html, body {
            height: 100%;
            margin: 0;
            padding: 0;
        }
        .escudo-img {
            max-width: 60px;
            max-height: 60px;
        }
    </style>
</head>
<body>
<div class="full-height-container">
    <a href="${mvc.uri('formInsertEquipo', {'locale': mvc.locale})}"
       class="btn btn-primary btn-add">
        <i class="bi bi-plus-circle"></i> <fmt:message bundle="${messages}" key="action.add"/>
    </a>
    <table class="table table-striped table-hover caption-top table-container">
        <caption><fmt:message bundle="${messages}" key="title.listadoEquipos"/></caption>
        <thead class="table-dark">
        <tr>
            <th scope="col"><fmt:message bundle="${messages}" key="label.escudo"/></th>
            <th scope="col"><fmt:message bundle="${messages}" key="label.equipo"/></th>
            <th scope="col"><fmt:message bundle="${messages}" key="label.acciones"/></th>
        </tr>
        </thead>
        <tbody class="table-group-divider">
        <c:forEach var="equipo" items="${equipos}">
            <c:set var="uriEquipo" value="${mvc.uri('findEquipoById', {'id': equipo.id, 'locale': mvc.locale })}"/>
            <c:set var="uriUpdateEquipoById" value="${mvc.uri('formUpdateEquipoById', {'id': equipo.id, 'locale': mvc.locale })}"/>
            <c:set var="uriDeleteEquipoById" value="${mvc.uri('formDeleteEquipoById', {'id': equipo.id, 'locale': mvc.locale })}"/>
            <tr>

                <td><img src="${equipo.escudo}" alt="<fmt:message bundle="${messages}" key="alt.escudo"/> ${equipo.nombre}" class="escudo-img"></td>
                <td><a href="${uriEquipo}">${equipo.nombre}</a></td>
                <td class="actions-column">
                    <a href="${uriUpdateEquipoById}" class="btn btn-sm btn-outline-primary">
                        <fmt:message bundle="${messages}" key="action.edit"/>
                    </a>
                    <a href="${uriDeleteEquipoById}" class="btn btn-sm btn-outline-danger">
                        <fmt:message bundle="${messages}" key="action.delete"/>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js" integrity="sha384-k6d4wzSIapyDyv1kpU366/PK5hCdSbCRGRCMv+eplOQJWyd1fbcAu9OCUj5zNLiq" crossorigin="anonymous"></script>
</body>
</html>