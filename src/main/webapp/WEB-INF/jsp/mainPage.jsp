<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="current" value="${param.ddlLanguage}" scope="session"/>
<c:if test="${not empty current}">
    <fmt:setLocale value="${current}" scope="session"/>
</c:if>
<fmt:setBundle basename="locale" scope="session"/>
<html>
<head>
    <title><fmt:message key="main"/></title>
    <link rel="stylesheet" href="css/mainPage.css">
    <link rel="stylesheet" href="css/pageHead.css">
    <link href="https://unpkg.com/bootstrap@4.5.0/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs4/dt-1.10.23/datatables.min.css"/>

</head>
<body>
<c:import url="header.jsp" />
<div class="main_background">
    <div class="mainPageContainer">
        <div class="blog-post">
            <h1><fmt:message key="mainTitle"/></h1>
            <tbody>
            <c:forEach items="${mainPageCourses}" var="courses">
                <div class="course" onclick="location.href='home?command=courseInfo&courseId=${courses.id}'">
                    <p class="date"><fmt:message key="startDate"/>: ${courses.startDate}</p>
                    <h2>${courses.title}</h2>
                    <p class="main-text">${courses.description}</p>
                </div>
            </c:forEach>
            <div class=" prev">prev</div>
            <div id="divPages" class="pagination"></div>
            <div class="next ">next</div>
            </tbody>
        </div>
        <div class="buttonPanel">
            <button class="btn btn-primary" onclick="window.location.href='user?command=teacherPanel'"><fmt:message key="workPanel"/></button>
        </div>
    </div>
</div>

<script src="https://unpkg.com/jquery@3.3.1/dist/jquery.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/v/bs4/dt-1.10.23/datatables.min.js"></script>
<script>
    $(document).ready(function(){
        var totalrows = $(".course").length;
        var pageSize=2;
        var noOfPage = totalrows/pageSize;
        noOfPage = Math.ceil(noOfPage);
        for(var i=1;i<=noOfPage;i++)
        {
            $("#divPages").append('<div class="page">'+i+'</div>');
        }
        var totalPagenum = $("div.page").length;
        if(totalPagenum >2)
        {
            $("div.page").hide();
            for(var n=1;n<=2;n++)
            {
                $("div.page:nth-child("+n+")").show();
            }
        }
        else{
            $("div.next").hide();
            $("div.prev").hide();
        }
        $("div.course").hide();
        for(var j=1;j<=pageSize+1;j++)
        {
            $("div.course:nth-child("+j+")").show();
        }
        displayevent();
        $("div.next").click(function(){
            if($("div.selected:last").nextAll('div.page').length > 2)
            {
                $("div.selected").last().nextAll(':lt(2)').show();
                $("div.selected").hide();
                displayevent();
                //lastposevent();
                $("div.prev").show();
                $("div.next").show();
            }
            else{
                $("div.selected").last().nextAll().show();
                $("div.selected").hide();
                displayevent();
                $("div.next").hide();
                $("div.prev").show();
            }
        });
        $("div.prev").click(function(){
            if($("div.selected:first").prevAll('div.page').length > 2)
            {
                $("div.selected").first().prevAll(':lt(2)').show();
                $("div.selected").hide();
                $("div.prev").show();
                $("div.next").show();
                displayevent();
            }
            else{
                $("div.selected").first().prevAll().show();
                $("div.selected").hide();
                $("div.prev").hide();
                $("div.next").show();
                displayevent();
            }
        });
        $("div.page").click(function(){
            var currentPage = $(this).text();
            $("div.course").hide();
            for (var k = (currentPage * pageSize+1) - (pageSize-1); k <= (currentPage * pageSize+1); k++)
            {
                $("div.course:nth-child("+k+")").show();
            }
        });
    });
    function displayevent()
    {
        $("div.page").each(function(){
            if( $(this).css('display') === 'block') {
                $(this).addClass('selected');
            }
            else{
                $(this).removeClass('selected');
            }
        });
    }
</script>
</body>
</html>
