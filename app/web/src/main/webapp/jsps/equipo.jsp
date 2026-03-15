<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${mvc.locale}"/>
<fmt:setBundle basename="messages" var="messages"/>

<c:choose>
    <c:when test="${action == 'VIEW'}">
        <c:set var="readonly" value="readonly"/>
        <c:set var="labelSubmit" value=""/>
        <c:set var="urlForm" value=""/>
        <c:set var="method" value=""/>
    </c:when>
    <c:when test="${action == 'UPDATE'}">
        <c:set var="readonly" value=""/>
        <fmt:message bundle="${messages}" key="label.submit.update" var="labelSubmit"/>
        <c:set var="urlForm" value="${mvc.uri('updateEquipoById', {'id': equipo.id, 'locale': mvc.locale})}"/>
        <c:set var="method" value="PUT"/>
    </c:when>
    <c:when test="${action == 'INSERT'}">
        <c:set var="readonly" value=""/>
        <fmt:message bundle="${messages}" key="label.submit.insert" var="labelSubmit"/>
        <c:set var="urlForm" value="${mvc.uri('insertEquipo', {'locale': mvc.locale})}"/>
        <c:set var="method" value="POST"/>
    </c:when>
    <c:otherwise>
        <c:set var="readonly" value="readonly"/>
        <fmt:message bundle="${messages}" key="label.submit.delete" var="labelSubmit"/>
        <c:set var="urlForm" value="${mvc.uri('deleteEquipoById', {'id': equipo.id, 'locale': mvc.locale})}"/>
        <c:set var="method" value="DELETE"/>
    </c:otherwise>
</c:choose>

<html>
<head>
    <title><fmt:message bundle="${messages}" key="title.equipo"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .escudo-container {
            text-align: center;
            margin-bottom: 20px;
        }

        .foto-jugador {
            width: 60px;
            height: 60px;
        }

        .escudo-img {
            max-width: 150px;
            max-height: 150px;
        }
    </style>
</head>
<body>
<div class="container py-4">
    <div class="form-container">
        <h2 class="form-title"><fmt:message bundle="${messages}" key="title.informacionEquipo"/></h2>

        <form method="POST" action="${urlForm}" enctype="application/x-www-form-urlencoded">
            <fieldset>
                <input type="hidden" name="_method" value="${method}"/>

                <div class="escudo-container">
                    <c:if test="${action != 'INSERT'}">
                        <img src="${equipo.escudo}" class="escudo-img"
                             alt="<fmt:message bundle='${messages}' key='alt.escudo'/> ${equipo.nombre}">
                    </c:if>
                </div>


                <div class="row mb-3">
                    <div class="col-md-6">
                        <label for="nombre" class="form-label"><fmt:message bundle="${messages}" key="label.nombreEquipo"/></label>
                        <input type="text" class="form-control" id="nombre" name="nombre"
                               value="${equipo.nombre}" ${readonly}>
                        <c:if test="${not empty errores['nombre']}">
                            <ul>
                                <c:forEach var="message" items="${errores['nombre']}">
                                    <li>${message}</li>
                                </c:forEach>
                            </ul>
                        </c:if>
                    </div>
                    <div class="col-md-6">
                        <label for="estadio" class="form-label"><fmt:message bundle="${messages}"
                                                                             key="label.estadio"/></label>
                        <input type="text" class="form-control" id="estadio" name="estadio"
                               value="${equipo.estadio}" ${readonly}>
                        <c:if test="${not empty errores['estadio']}">
                            <ul>
                                <c:forEach var="message" items="${errores['estadio']}">
                                    <li>${message}</li>
                                </c:forEach>
                            </ul>
                        </c:if>
                    </div>
                </div>

                <div class="row mb-3">
                    <div class="col-md-6">
                        <label for="ciudad" class="form-label"><fmt:message bundle="${messages}"
                                                                            key="label.ciudad"/></label>
                        <input type="text" class="form-control" id="ciudad" name="ciudad"
                               value="${equipo.ciudad}" ${readonly}>
                        <c:if test="${not empty errores['ciudad']}">
                            <ul>
                                <c:forEach var="message" items="${errores['ciudad']}">
                                    <li>${message}</li>
                                </c:forEach>
                            </ul>
                        </c:if>
                    </div>
                    <div class="col-md-6">
                        <label for="fundacion" class="form-label"><fmt:message bundle="${messages}"
                                                                               key="label.fundacion"/></label>
                        <input type="number" class="form-control" id="fundacion" name="fundacion"
                               value="${equipo.fundacion}" ${readonly}>
                        <c:if test="${not empty errores['fundacion']}">
                            <ul>
                                <c:forEach var="message" items="${errores['fundacion']}">
                                    <li>${message}</li>
                                </c:forEach>
                            </ul>
                        </c:if>
                    </div>
                </div>

                <div class="row mb-3">
                    <div class="col-md-6">
                        <label for="presupuesto" class="form-label"><fmt:message bundle="${messages}"
                                                                                 key="label.presupuesto"/></label>

                        <input type="number" step="0.01" class="form-control"
                               id="presupuesto" name="presupuesto"
                               value="${equipo.presupuesto}" ${readonly}>
                        <c:if test="${not empty errores['presupuesto']}">
                            <ul>
                                <c:forEach var="message" items="${errores['presupuesto']}">
                                    <li>${message}</li>
                                </c:forEach>
                            </ul>
                        </c:if>
                    </div>
                    <div class="col-md-6">
                        <label for="escudo" class="form-label"><fmt:message bundle="${messages}"
                                                                            key="label.escudo"/></label>
                        <input type="url" class="form-control" id="escudo" name="escudo"
                               value="${equipo.escudo}" ${readonly}>
                        <c:if test="${not empty errores['escudo']}">
                            <ul>
                                <c:forEach var="message" items="${errores['escudo']}">
                                    <li>${message}</li>
                                </c:forEach>
                            </ul>
                        </c:if>
                    </div>
                </div>

                <c:if test="${action == 'VIEW'}">
                    <div>
                        <h4><fmt:message bundle="${messages}" key="title.plantilla"/></h4>
                        <table class="table table-striped">
                            <c:choose>
                            <c:when test="${not empty jugadores}">
                                <thead class="table-dark">
                                <tr>
                                    <th><fmt:message bundle="${messages}" key="label.foto"/></th>
                                    <th><fmt:message bundle="${messages}" key="label.nombre"/></th>
                                    <th><fmt:message bundle="${messages}" key="label.valor"/></th>
                                    <th><fmt:message bundle="${messages}" key="label.finContrato"/></th>
                                </tr>
                                </thead>
                                <tbody>

                                <c:forEach var="jugadorContrato" items="${jugadores}">
                                    <tr>
                                        <td><img src="${jugadorContrato.jugador.foto}" class="foto-jugador"
                                                 alt="${jugadorContrato.jugador.nombre}"></td>
                                        <td>${jugadorContrato.jugador.nombre}</td>
                                        <td>${jugadorContrato.jugador.valorMercado}</td>
                                        <td>${jugadorContrato.fin_contrato}</td>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <p class="text-muted"><fmt:message bundle="${messages}" key="msg.noJugadores"/></p>
                            </c:otherwise>
                            </c:choose>
                            </tbody>
                        </table>
                    </div>
                </c:if>

                <c:if test="${action != 'VIEW'}">
                    <div class="d-grid gap-2 d-md-flex justify-content-md-end mt-4">
                        <button type="submit" class="btn btn-primary">${labelSubmit}</button>
                    </div>
                </c:if>
            </fieldset>
        </form>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>