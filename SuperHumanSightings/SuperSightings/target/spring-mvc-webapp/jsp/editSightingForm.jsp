<%-- 
    Document   : editSightingForm
    Created on : Mar 25, 2018, 11:44:15 PM
    Author     : jakeduerr
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- Directive for Spring Form tag libraries -->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Edit Sighting Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link rel='stylesheet' type='text/css' href='css/style.css'/>
    </head>
    <body>
        <div class="container">
            <h1>Super Human Sightings</h1>
            <hr/>
            <p>
                Keep track of all sightings of Heroes and Villains and all their<br/>
                Organizations. Always know the location of your favorite Super Humans!
            </p>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/">
                            Home
                        </a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displayLocationsPage">
                            Location
                        </a>
                    </li>
                    <li role="presentation"
                        class="active">
                        <a href="${pageContext.request.contextPath}/displaySightingsPage">
                            Sighting
                        </a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displaySuperHumansPage">
                            Super Human
                        </a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displayOrganizationsPage">
                            Organization
                        </a>
                    </li>
                </ul>    
            </div>
            <sf:form class="form-horizontal" role="form" modelAttribute="sighting"
                     action="editSighting" method="POST">
                <div class="form-group">
                    <label for="date" class="col-md-4 control-label">Date:</label>
                    <div class="col-md-8">
                        <sf:input type="text" name="date" class="form-control" id="addDate"
                                  path="date" placeholder="Date"/>
                        <sf:errors path="date" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="location" class="col-md-4 control-label">Locations:</label>
                    <sf:select path="location" name="location">
                        <sf:options itemValue="locationId" items="${locations}" itemLabel="name"/>
                    </sf:select>
                    <sf:errors path="location" cssclass="error"></sf:errors>    
                    </div>
                    <div class="form-group">  
                        <label for="superHumans" class="col-md-4 control-label">Super Humans:</label>
                        <select name="superHumans" multiple>
                        <c:forEach var="superHuman" items="${superHumans}">
                            <c:choose>
                                <c:when test="${sighting.isMember(superHuman.superHumanId)}">
                                    <option value="${superHuman.superHumanId}" selected="selected">${superHuman.name}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${superHuman.superHumanId}">${superHuman.name}</option>
                                </c:otherwise>

                            </c:choose>
                        </c:forEach>
                    </select>
                    <%--<sf:select path="superHumans" name="superHumans" mutiple="true">--%>
                    <%--<sf:options itemValue="superHumanId" items="${superHumans}" itemLabel="name"/>--%>
                    <%--</sf:select>--%>
                    <sf:errors path="superHumans" cssclass="error"></sf:errors>
                    <sf:hidden path="sightingId"/>
                    <p>Hold down the <kdb>Ctrl</kdb> (windows) / Command (Mac) button to select multiple options.</p>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <input type="submit" class="btn btn-default" value="Update Sighting"/>
                    </div>
                </div>
            </sf:form>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>
