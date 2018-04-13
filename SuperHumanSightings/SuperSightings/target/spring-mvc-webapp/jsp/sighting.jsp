<%-- 
    Document   : Sighting
    Created on : Mar 23, 2018, 1:48:37 PM
    Author     : jakeduerr
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Sighting Page</title>
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
            <div class="row">
                <div class="col-md-6">
                    <h2>Sightings</h2>
                    <table id="sightingTable" class="table table-hover">
                        <tr>
                            <th width="30%">Date</th>
                            <th width="40%">Location</th>
                            <th width="15%"></th>
                            <th width="15%"></th>
                        </tr>
                        <c:forEach var="currentSighting" items="${sightings}">
                            <tr>
                                <td>
                                    <a href="displaySightingDetails?sightingId=${currentSighting.sightingId}">
                                        <c:out value="${currentSighting.date}"/> 
                                    </a>
                                </td>
                                <td>
                                    <c:out value="${currentSighting.location.name}"/>
                                </td>
                                <td>
                                    <a href="displayEditSightingForm?sightingId=${currentSighting.sightingId}">
                                        Edit
                                    </a>
                                </td>
                                <td>
                                    <a href="deleteSighting?sightingId=${currentSighting.sightingId}">
                                        Delete
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>                    
                </div> 

                <div class="col-md-6">
                    <h2>Add New Sighting</h2>
                    <c:if test="${not empty errors}">
                        <ul>
                            <c:forEach var="error" items="${errors}">
                                <li>${error.getDefaultMessage()}</li>
                            </c:forEach>
                        </ul>
                    </c:if>
                    <form class="form-horizontal" 
                          role="form" method="POST"
                          modelAttribute="sighting"
                          action="createSighting">
                        <div class="form-group">
                            <label for="date" class="col-md-4 control-label">Date:</label>
                            <div class="col-md-8">
                                <input type="date" class="form-control" name="date" placeholder="Date"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="locals" class="col-md-4 control-label">Location:</label>
                            <select class="mdb-select" name="locals">
                                <c:forEach var="currentLocation" items="${locals}">
                                    <option value="${currentLocation.locationId}">${currentLocation.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="addSuperHuman" class="col-md-4 control-label">Super Humans:</label>
                            <select class="mdb-select" name="superHumans" multiple>
                                <c:forEach var="currentSuperHuman" items="${superHumans}">
                                    <option value="${currentSuperHuman.superHumanId}">${currentSuperHuman.name}</option>
                                </c:forEach>
                            </select>
                            <p>*Hold down the Ctrl (windows) / Command (Mac) button to select multiple Super Humans.</p>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-default" value="Create Sighting"/>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
