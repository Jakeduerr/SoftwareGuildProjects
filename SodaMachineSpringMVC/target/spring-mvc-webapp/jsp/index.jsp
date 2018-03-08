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
        <style>
            body {
                font-family: merriweather;
            }
            h1 {
                text-align: center;
            }
            #input-divs {
                text-align: center;
                height: 798px;
                background-color: lightblue;
                border-style: solid;
                border-width: 4px;
            }
            label {
                text-align: center;
                margin: 18px;
                font-size: 23px;
            }
            input {
                text-align: center;
                margin: 18px;
            }
            #addDollar, #addQuarter, #addDime, #addNickel {
                margin: 6px;
            }
            #totalChange {
                font-size: 15px; 
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1>Vending Machine</h1>
            <hr/>
            <ul class="list-group" id="errorMessages"></ul>
            <div class="col-md-8" id="item-div">
                <c:forEach var="currentSoda" items="${sodaList}">
                    <form class="form-horizontal" role="form" method="POST" action="selectSoda">
                        <input type="hidden" name="sodaName" value="${currentSoda.sodaName}"/>
                        <button type="submit" name="selectSoda" class="col-md-4 btn btn-default" style="height: 266px; 
                                border-style: solid; border-width: 2px; border-color: black; text-align: center; 
                                font-size: 22px; background-color: rgba(253, 81, 81, 0.836);">
                            <c:out value="${currentSoda.sodaName}"/><br/>
                            <c:out value="$${currentSoda.sodaCost}"/><br/>
                            <c:out value="Inventory: ${currentSoda.numOfSoda}"/><br/>
                        </button>
                    </form>
                </c:forEach>
            </div>
            <div class="col-md-4" id="input-divs">
                <div class="form-group">
                    <label for="totalMoneyIn" class="col-md-11 control-label">Total $ In!</label>
                    <div class="col-md-offset-3 col-md-5">
                        <input type="text" class="form-control" name="totalMoney" value="${totalMoney}" placeholder="0" />
                    </div>
                </div>
                <div class="col-md-6">
                    <form class="form-horizontal" role="form" method="POST" action="addDollar">
                        <button type="submit" id="addDollar" class="btn btn-default">ADD DOLLAR!</button>
                    </form>
                </div>
                <div class="col-md-6">
                    <form class="form-horizontal" role="form" method="POST" action="addQuarter">
                        <button type="submit" id="addQuarter" class="btn btn-default">ADD QUARTER!</button>
                    </form>
                </div>
                <div class="col-md-6">
                    <form class="form-horizontal" role="form" method="POST" action="addDime">
                        <button type="submit" id="addDime" class="btn btn-default">ADD DIME!</button>
                    </form>
                </div>
                <div class="col-md-6">
                    <form class="form-horizontal" role="form" method="POST" action="addNickel">
                        <button type="submit" id="addNickel" class="btn btn-default">ADD NICKEL!</button>
                    </form>
                </div>
                <div class="col-md-11">
                    <label for="messages" class="col-md-12 control-label">Messages!</label>
                    <input type="text" class="form-control" name="messages" value="${message}" placeholder="Messages" />
                </div>
                <label for="items" class="col-md-11 control-label">Beverage:</label>
                <div class="col-md-offset-3 col-md-5">
                    <input type="text" class="form-control" name="selectedSoda" value="${selectedSoda.sodaName}" placeholder="Beverage"/>
                </div>
                <div class="col-md-offset-3 col-md-6">
                    <form class="form-horizontal" role="form" method="POST" action="makePurchase">
                        <button type="submit" name="makePurchase" class="btn btn-default">MAKE PURCHASE!</button>
                    </form>
                </div>
                <label for="change" class="col-md-11 control-label">Change!</label>
                <div class="col-md-11">
                    <input type="text" class="form-control" id="totalChange" value="${totalChange}" placeholder="Change" />
                </div>
                <div class="col-md-offset-3 col-md-6">
                    <form class="form-horizontal" role="form" method="POST" action="returnChange">
                        <button type="submit" name="returnChange" class="btn btn-default">RETURN CHANGE!</button>
                    </form>

                </div>
            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

