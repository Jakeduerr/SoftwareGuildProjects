<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Vending Machine</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        <div class="container">
            <h1>Vending Machine</h1>
            <hr/>
            <ul class="list-group" id="errorMessages"></ul>
            <div class="col-md-8" id="item-div">
                <c:forEach var="currentSoda" items="${sodaList}">
                    <button class="col-md-4 btn btn-default" id="button" onclick="" style="height: 266px; border-style: solid; border-width: 2px; border-color: black; text-align: center; font-size: 22px; background-color: rgba(253, 81, 81, 0.836);">
                        <c:out value="${currentSoda.sodaName}"/><br/>
                        <c:out value="${currentSoda.sodaCost}"/><br/>
                        <c:out value="${currentSoda.numOfSoda}"/><br/>
                    </button>
                </c:forEach>

                <!-- div that holds all the items in the vending machine -->

            </div>
            <!--do i make every button an input and give a type submit?-->
            <div class="col-md-4" id="input-divs">
                <form class="form-horizontal" role="form" method="GET" action="">
                    <div class="form-group">
                        <label for="total-money-in" class="col-md-11 control-label">Total $ In!</label>
                        <div class="col-md-offset-3 col-md-5">
                            <input type="text" class="form-control" name="total-money-in" placeholder="0" />
                        </div>
                    </div>
                    <div class="col-md-6">
                        <button type="button" id="add-dollar" class="btn btn-default">ADD DOLLAR!</button>
                    </div>
                    <div class="col-md-6">
                        <button type="button" id="add-quarter" class="btn btn-default">ADD QUARTER!</button>
                    </div>
                    <div class="col-md-6">
                        <button type="button" id="add-dime" class="btn btn-default">ADD DIME!</button>
                    </div>
                    <div class="col-md-6">
                        <button type="button" id="add-nickel" class="btn btn-default">ADD NICKEL!</button>
                    </div>
                </form>
                <hr/>
                <form class="form-horizontal" role="form" method="GET" action="">
                    <div class="col-md-11">
                        <label for="messages" class="col-md-11 control-label">Messages!</label>
                        <input type="text" class="form-control" name="messages" placeholder="Messages" />
                    </div>

                    <label for="items" class="col-md-11 control-label">Item:</label>

                    <div class="col-md-offset-3 col-md-5">
                        <input type="text" class="form-control" name="items" placeholder="Item"/>
                    </div>

                    <div class="col-md-offset-3 col-md-6">
                        <button type="button" id="makePurchase" class="btn btn-default">MAKE PURCHASE!</button>
                    </div>
                </form>
                <hr/>
                <form class="form-horizontal" role="form" method="GET" action="">
                    <label for="change" class="col-md-11 control-label">Change!</label>

                    <div class="col-md-11">
                        <input type="text" class="form-control" name="change" placeholder="Change" />
                    </div>

                    <div class="col-md-offset-3 col-md-6">
                        <button type="button" id="returnChange" class="btn btn-default">RETURN CHANGE!</button>
                    </div>
                </form>

            </div>

        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

