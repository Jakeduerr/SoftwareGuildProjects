<%-- 
    Document   : SuperHuman
    Created on : Mar 23, 2018, 1:48:53 PM
    Author     : jakeduerr
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Super Human Page</title>
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
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displaySightingsPage">
                            Sighting
                        </a>
                    </li>
                    <li role="presentation"
                        class="active">
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
                    <h2>Super Humans</h2>
                    <table id="superHumanTable" class="table table-hover">
                        <tr>
                            <th width="30%">Name</th>
                            <th width="40%">Description</th>
                            <th width="15%"></th>
                            <th width="15%"></th>
                        </tr>
                        <c:forEach var="currentSuperHuman" items="${supers}">
                            <tr>
                                <td>
                                    <a href="displaySuperHumanDetails?superHumanId=${currentSuperHuman.superHumanId}">
                                        <c:out value="${currentSuperHuman.name}"/>
                                    </a>
                                </td>
                                <td>
                                    <c:out value="${currentSuperHuman.description}"/>
                                </td>
                                <td>
                                    <a href="displayEditSuperHumanForm?superHumanId=${currentSuperHuman.superHumanId}">
                                        Edit
                                    </a>
                                </td>
                                <td>
                                    <a href="deleteSuperHuman?superHumanId=${currentSuperHuman.superHumanId}">
                                        Delete
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>                    
                </div> 

                <div class="col-md-6">
                    <h2>Add New Super Human</h2>
                    <c:if test="${not empty errors}">
                        <ul>
                            <c:forEach var="error" items="${errors}">
                                <li>${error.getDefaultMessage()}</li>
                            </c:forEach>
                        </ul>
                    </c:if>
                    <form class="form-horizontal" 
                          role="form" method="POST"
                          modelAttribute="superHuman"
                          action="createSuperHuman">
                        <div class="form-group">
                            <label for="addName" class="col-md-4 control-label">Name:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="name" placeholder="Name"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="addDescription" class="col-md-4 control-label">Description:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="description" placeholder="Description"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="addPowers" class="col-md-4 control-label">Powers:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="powers" placeholder="Powers"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="addOrganization" class="col-md-4 control-label">Organizations:</label>
                            <select class="mdb-select" name="orgs" multiple>
                                <c:forEach var="currentOrganization" items="${organizations}">
                                    <option value="${currentOrganization.organizationId}">${currentOrganization.name}</option>
                                </c:forEach>
                            </select>
                            <p>Hold down the Ctrl (windows) / Command (Mac) button to select multiple options.</p>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-default" value="Create Super Human"/>
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
