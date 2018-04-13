<%-- 
    Document   : editSuperHumanForm
    Created on : Mar 25, 2018, 11:44:42 PM
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
        <title>Edit Super Human Page</title>
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
            <sf:form class="form-horizontal" role="form" modelAttribute="superHuman"
                     action="editSuperHuman" method="POST">
                <div class="form-group">
                    <label for="addName" class="col-md-4 control-label">Name:</label>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="addName"
                                  path="name" placeholder="Name"/>
                        <sf:errors path="name" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="addDescription" class="col-md-4 control-label">Description:</label>
                        <div class="col-md-8">
                        <sf:input type="text" name="description" class="form-control" id="addDescription"
                                  path="description" placeholder="Description"/>
                        <sf:errors path="description" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="addPowers" name="powers" class="col-md-4 control-label">Powers:</label>                          
                        <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="addPowers"
                                  path="powers" placeholder="Powers"/>
                        <sf:errors path="powers" cssclass="error"></sf:errors>

                        </div>
                    </div>
                    <div class="form-group">
                        <label for="addOrganization" class="col-md-4 control-label">Organizations:</label>
                        <select name="orgs" multiple>
                        <c:forEach var="org" items="${organizations}">
                            <c:choose>
                                <c:when test="${superHuman.isMember(org.organizationId)}">
                                    <option value="${org.organizationId}" selected="selected">${org.name}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${org.organizationId}">${org.name}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                    <sf:errors path="organizations" cssclass="error"></sf:errors>
                    <sf:hidden path="superHumanId"/>
                    <p>Hold down the Ctrl (windows) / Command (Mac) button to select multiple options.</p>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <input type="submit" class="btn btn-default" value="Update Super Human"/>
                    </div>
                </div>
            </sf:form>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>
