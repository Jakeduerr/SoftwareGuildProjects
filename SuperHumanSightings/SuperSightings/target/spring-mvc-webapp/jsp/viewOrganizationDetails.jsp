<%-- 
    Document   : viewOrganizationDetails
    Created on : Mar 25, 2018, 8:26:17 PM
    Author     : jakeduerr
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Organization Details</title>
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
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displaySuperHumansPage">
                            Super Human
                        </a>
                    </li>
                    <li role="presentation"
                        class="active">
                        <a href="${pageContext.request.contextPath}/displayOrganizationsPage">
                            Organization
                        </a>
                    </li>
                </ul>    
            </div>
            <p>
                Name: <c:out value="${organization.name}"/>
            </p>
            <p>
                Description: <c:out value="${organization.description}"/>
            </p>
            <p>
                Phone: <c:out value="${organization.phone}"/>
            </p>
            <p>
                Address: <c:out value="${organization.address}"/>
            </p>
            <p>
                Super Human(s): 
                <c:forEach var="currentSuperHuman" items="${supers}">
                    <c:out value="${currentSuperHuman.name}"/> ||
                </c:forEach>
            </p>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>
