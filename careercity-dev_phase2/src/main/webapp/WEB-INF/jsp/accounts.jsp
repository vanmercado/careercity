<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.lang.Character"%>
<jsp:include page="/WEB-INF/jsp/common/header.jsp" />
<!-- Navigation Bar -->
<div class="container-fluid">
    <!-- Start Change - Daryll Moya - 02-MAR-2021 -->
    <!-- <div class="row" style="height: 3rem;"> -->
    <div class="row" style="height: 4rem;">
        <div class="col-4" style="float: left;">
            <a class="nav-link home-link far" href="/">
                <!-- <span style="font-family: 'TELUS-Web', 'Helvetica Neue', Helvetica, Arial, sans-serif !important;">Home</span> -->
                <span style="font-family: 'TELUS-Web', 'Helvetica Neue', Helvetica, Arial, sans-serif !important; padding-left: 15px;">Home</span>
            </a>
        </div>
        <div class="col-4" style="text-align: center;">
            <!-- <img class="navbar-brand" alt="" src="${ctx}/img/index/telus-logo.png" style="margin-top: -6%; height: 80%; margin: 0.5rem 0;"></img> -->
            <img class="navbar-brand" alt="" src="${ctx}/img/index/telus-logo.png" style="height: 80%; margin: 0.4rem 0;"></img>
        </div>
    </div>
    <!-- End Change - Daryll Moya - 02-MAR-2021 -->
</div>

<!-- ACCOUNT PAGE - CCO -->
<c:if test="${pillar == 'cco'}">
    <div class="container-fluid account-container" style="padding-right: 0; padding-left: 0;">
        <div class="col-md-12" style="margin-bottom: 1%; padding: 0;">
            <div class="page-title-main">
                <!-- Start Change - Daryll Moya - 02-MAR-2021 -->
                <!-- <h2>Accounts</h2> -->
                <h2 style="margin-bottom: 0px;">Accounts</h2>
                <!-- End Change - Daryll Moya - 02-MAR-2021 -->
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div style="padding: 0 0 1rem 0; width: 100%;">
                    <div class="col-md-12 row" style="margin: 0; padding: 0;">
                        <div class="input-group search-group col-md-3" style="height: 38px">
                            <div class="input-group-prepend">
                                <span class="input-group-text search-text">
                                    <i class="fas fa-search"></i>
                                </span>
                            </div>
                            <input type="text" name="filtr-search" class="form-control" placeholder="Search Keyword (e.g. Zynga, Non-Voice, Financial)" aria-label="Input group example" aria-describedby="btnGroupAddon" data-search>
                        </div>
                        <div class="col-md-9 category-content" style="float: left;">
                            <button type="button" class="category-btn" data-filter="all">All</button>
                            <!-- dynamic buttons for sites -->
                            <c:forEach items="${sitelist}" var="sites">
                                <button type="button" class="category-btn" <%-- value="${sites.SITE_NAME}" --%> data-filter="${sites.SITE_NAME}">${sites.SITE_NAME}</button>
                            </c:forEach>
                        </div>
                    </div>
                </div>
                <div class="card-deck filtr-container">
                    <c:forEach items="${accountList}" var="account">
                        <div class="card col-md-3 filtr-item" data-category="${account.LOCATION}" onclick="addClickCount(${account.ACCOUNT_ID},'CCO')">
                            <a href="#" data-target="#${account.ACCOUNT_ID}" data-toggle="modal" style="text-decoration: none; outline: none;">
                                <input type="hidden" id="workdayUsername" value="">
                                <c:if test="${empty account.ACCOUNT_IMAGE_PATH}">
                                    <div class="card-img-top" style="text-align: center;">
                                        <img src="${ctx}/img/index/telus-logo.png" alt="card image" style="padding: 25% 5%;">
                                    </div>
                                </c:if>
                                <c:if test="${not empty account.ACCOUNT_IMAGE_PATH}">
                                    <div class="card-img-top" style="text-align: center;">
                                        <img class="card-img-top" src="${account.ACCOUNT_IMAGE_PATH}" alt="card image">
                                    </div>
                                </c:if>
                                <div class="card-body text-dark" style="text-align: center; padding: 0px;">
                                    <div class="card-title">
                                        <h5>
                                            <strong>${account.ACCOUNT_NAME}</strong>
                                        </h5>
                                    </div>
                                    <div class="card-subtitle">
                                        <c:if test="${not empty account.LOCATION}">
                                            <p>${account.LOCATION}</p>
                                        </c:if>
                                        <c:if test="${empty account.LOCATION}">
                                            <p>N/A</p>
                                        </c:if>
                                        <c:if test="${not empty account.LOB}">
                                            <p>${account.LOB}</p>
                                        </c:if>
                                        <c:if test="${empty account.LOB}">
                                            <p>N/A</p>
                                        </c:if>
                                    </div>
                                    <div hidden="true">
                                        <p>${account.TM_TASK}</p>
                                        <p>${account.SKILLS}</p>
                                        <p>${account.SKILLS_PREMIUM}</p>
                                        <p>${account.BPO_EXP}</p>
                                        <p>${account.LOB}</p>
                                        <p>${account.EDUCATION}</p>
                                        <p>${account.LOB}</p>
                                        <p>${account.BUSINESS_VERTICAL}</p>
                                        <p>${account.OPERATING_HOURS}</p>
                                    </div>
                                </div>
                                <div class="card-footer"></div>
                            </a>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
        <c:forEach items="${accountList}" var="account">
            <div class="modal fade" id="${account.ACCOUNT_ID}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
                    <div class="modal-content">
                        <div>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close" style="float: right; padding: 1em;">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <div class="container-fluid">
                                <div class="row">
                                    <div class="account-modal-head col-md-12 row ">
                                        <div class="col-md-3 account-modal-image">
                                            <img class="card-img-bottom" src="${account.ACCOUNT_IMAGE_PATH}" alt="card image">
                                        </div>
                                        <section class="account-modal-title col-md-9 row ">
                                            <section class="col-md-12">
                                                <h2>
                                                    <strong>${account.ACCOUNT_NAME}</strong>
                                                </h2>
                                            </section>
                                            <!-- 1st row -->
                                            <c:if test="${not empty account.LOCATION}">
                                                <div class="col-md-6">
                                                    <table class="table">
                                                        <tr>
                                                            <td style="width: 3vw; padding-top: 3px;" title="Location">
                                                                <i class="fas fa-map-marker-alt"></i>
                                                            </td>
                                                            <td style="padding-top: 0; padding-bottom: 0;">${account.LOCATION}</td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </c:if>
                                            <c:if test="${empty account.LOCATION}">
                                                <div class="col-md-6">
                                                    <table class="table">
                                                        <tr>
                                                            <td style="width: 3vw; padding-top: 3px;" title="Location">
                                                                <i class="fas fa-map-marker-alt"></i>
                                                            </td>
                                                            <td style="padding-top: 0; padding-bottom: 0;">N/A</td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </c:if>
                                            <c:if test="${not empty account.BPO_EXP}">
                                                <div class="col-md-6">
                                                    <table class="table">
                                                        <tr>
                                                            <td style="width: 3vw; padding-top: 3px;" title="Experience">
                                                                <small>
                                                                    <strong>Exp</strong>
                                                                </small>
                                                            </td>
                                                            <td style="padding-top: 0; padding-bottom: 0;">${account.BPO_EXP}</td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </c:if>
                                            <c:if test="${empty account.BPO_EXP}">
                                                <div class="col-md-6">
                                                    <table class="table">
                                                        <tr>
                                                            <td style="width: 3vw; padding-top: 3px;" title="Experience">
                                                                <small>
                                                                    <strong>Exp</strong>
                                                                </small>
                                                            </td>
                                                            <td style="padding-top: 0; padding-bottom: 0;">N/A</td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </c:if>
                                            <!-- 2nd row -->
                                            <c:if test="${not empty account.EDUCATION}">
                                                <div class="col-md-6">
                                                    <table class="table">
                                                        <tr>
                                                            <td style="width: 3vw; padding-top: 3px;" title="Education">
                                                                <span>
                                                                    <i class="fas fa-book"></i>
                                                                </span>
                                                            </td>
                                                            <td style="padding-top: 0; padding-bottom: 0;">${account.EDUCATION}</td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </c:if>
                                            <c:if test="${empty account.EDUCATION}">
                                                <div class="col-md-6">
                                                    <table class="table">
                                                        <tr>
                                                            <td style="width: 3vw; padding-top: 3px;" title="Education">
                                                                <span>
                                                                    <i class="fas fa-book"></i>
                                                                </span>
                                                            </td>
                                                            <td style="padding-top: 0; padding-bottom: 0;">N/A</td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </c:if>
                                            <c:if test="${not empty account.OPERATING_HOURS}">
                                                <div class="col-md-6">
                                                    <table class="table">
                                                        <tr>
                                                            <td style="width: 3vw; padding-top: 3px;" title="Operating Hours">
                                                                <span>
                                                                    <i class="far fa-clock"></i>
                                                                </span>
                                                            </td>
                                                            <td style="padding-top: 0; padding-bottom: 0;">${account.OPERATING_HOURS}</td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </c:if>
                                            <c:if test="${empty account.OPERATING_HOURS}">
                                                <div class="col-md-6">
                                                    <table class="table">
                                                        <tr>
                                                            <td style="width: 3vw; padding-top: 3px;" title="Operating Hours">
                                                                <span>
                                                                    <i class="far fa-clock"></i>
                                                                </span>
                                                            </td>
                                                            <td style="padding-top: 0; padding-bottom: 0;">N/A</td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </c:if>
                                            <!-- 3nd row -->
                                            <c:if test="${not empty account.LOB}">
                                                <div class="col-md-6">
                                                    <table class="table">
                                                        <tr>
                                                            <td style="width: 3vw; padding-top: 3px;" title="LOB">
                                                                <i class="fas fa-user-tie"></i>
                                                            </td>
                                                            <td style="padding-top: 0; padding-bottom: 0;">${account.LOB}</td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </c:if>
                                            <c:if test="${empty account.LOB}">
                                                <div class="col-md-6">
                                                    <table class="table">
                                                        <tr>
                                                            <td style="width: 3vw; padding-top: 3px;" title="LOB">
                                                                <i class="fas fa-user-tie"></i>
                                                            </td>
                                                            <td style="padding-top: 0; padding-bottom: 0;">N/A</td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </c:if>
                                            <c:if test="${not empty account.BUSINESS_VERTICAL}">
                                                <div class="col-md-6">
                                                    <table class="table">
                                                        <tr>
                                                            <td style="width: 3vw; padding-top: 3px;" title="Business Vertical">
                                                                <i class="fas fa-briefcase"></i></td>
                                                            <td style="padding-top: 0; padding-bottom: 0;">${account.BUSINESS_VERTICAL}</td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </c:if>
                                            <c:if test="${empty account.BUSINESS_VERTICAL}">
                                                <div class="col-md-6">
                                                    <table class="table">
                                                        <tr>
                                                            <td style="width: 3vw; padding-top: 3px;" title="Business Vertical">
                                                                <i class="fas fa-briefcase"></i>
                                                            </td>
                                                            <td style="padding-top: 0; padding-bottom: 0;">N/A</td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </c:if>
                                            <!--
                                            <section class="col-md-6 account-subtitle">
                                                <p>
                                                    <i class="fas fa-map-marker-alt"></i>&nbsp;&nbsp;${account.LOCATION}
                                                <c:if test="${not empty account.EDUCATION}">
                                                    <p>
                                                        <span>
                                                        <i class="fas fa-book"></i></span>&nbsp;${account.EDUCATION}
                                                </c:if>
                                                <c:if test="${empty account.EDUCATION}">
                                                    <p>
                                                        <span>
                                                            <i class="fas fa-book"></i>
                                                        </span>&nbsp;N/A
                                                </c:if>
                                                <c:if test="${not empty account.LOB}">
                                                    <p>
                                                        <i class="fas fa-user-tie"></i>&nbsp;&nbsp;${account.LOB}
                                                </c:if>
                                                <c:if test="${empty account.LOB}">
                                                    <p>
                                                        <i class="fas fa-user-tie"></i>&nbsp;&nbsp;&nbsp;N/A
                                                </c:if>
                                            </section>
                                            <section class="col-md-6 account-subtitle">
                                                <c:if test="${not empty account.BPO_EXP}">
                                                    <p>
                                                        <small>
                                                            <strong>Exp</strong>
                                                        </small>&nbsp;${account.BPO_EXP}
                                                </c:if>
                                                <c:if test="${empty account.BPO_EXP}">
                                                    <p>
                                                        <small>
                                                            <strong>Exp</strong>
                                                        </small>&nbsp;N/A
                                                </c:if>
                                                <c:if test="${not empty account.OPERATING_HOURS}">
                                                    <p>
                                                        <span>
                                                            <i class="far fa-clock"></i>
                                                        </span>&nbsp;&nbsp;&nbsp;${account.OPERATING_HOURS}
                                                </c:if>
                                                <c:if test="${empty account.OPERATING_HOURS}">
                                                    <p>
                                                        <span>
                                                            <i class="far fa-clock"></i>
                                                        </span>&nbsp;&nbsp;&nbsp;N/A
                                                </c:if>
                                                <c:if test="${not empty account.BUSINESS_VERTICAL}">
                                                    <p>
                                                        <i class="fas fa-briefcase"></i>&nbsp;&nbsp;&nbsp;${account.BUSINESS_VERTICAL}
                                                </c:if>
                                                <c:if test="${empty account.BUSINESS_VERTICAL}">
                                                    <p>
                                                        <i class="fas fa-briefcase"></i>&nbsp;&nbsp;&nbsp;N/A
                                                </c:if>
                                            </section>
                                            -->
                                        </section>
                                    </div>
                                    <div class="card col-md-12">
                                        <div class="card-block">
                                            <span class="result-sub-desc">
                                                <strong>Description</strong>
                                            </span>
                                            <c:if test="${not empty account.DESCRIPTION}">
                                                <c:if test="${fn:contains(account.DESCRIPTION, '~') }">
                                                    <c:set var="descList" value="${fn:split(account.DESCRIPTION, '~')}" />
                                                    <ul class="card-text result-sub-desc-text">
                                                        <c:forEach items="${descList}" var="desc">
                                                            <li>${desc}</li>
                                                        </c:forEach>
                                                    </ul>
                                                </c:if>
                                                <c:if test="${not fn:contains(account.DESCRIPTION, '~') }">
                                                    <c:set var="firstChar" value="${fn:substring(account.DESCRIPTION, 0, 1)}" />
                                                    <c:if test="${Character.isLetter(firstChar)}">
                                                        <p class="card-text result-sub-desc-text">${account.DESCRIPTION}</p>
                                                    </c:if>
                                                    <c:if test="${not Character.isLetter(firstChar)}">
                                                        <c:set var="descList" value="${fn:split(account.DESCRIPTION, firstChar)}" />
                                                        <ul class="card-text result-sub-desc-text">
                                                            <c:forEach items="${descList}" var="desc">
                                                                <li>${desc}</li>
                                                            </c:forEach>
                                                        </ul>
                                                    </c:if>
                                                </c:if>
                                            </c:if>
                                            <c:if test="${empty account.DESCRIPTION}">
                                                <ul class="card-text result-sub-desc-text">
                                                    <p>N/A</p>
                                                </ul>
                                            </c:if>
                                            <span class="result-sub-desc"><strong>Team member tasks</strong></span>
                                            <c:if test="${not empty account.TM_TASK}">
                                                <c:if test="${fn:contains(account.TM_TASK, '~') }">
                                                    <c:set var="tmTaskList" value="${fn:split(account.TM_TASK, '~')}" />
                                                    <ul class="card-text result-sub-desc-text">
                                                        <c:forEach items="${tmTaskList}" var="tmTask">
                                                            <li>${tmTask}</li>
                                                        </c:forEach>
                                                    </ul>
                                                </c:if>
                                                <c:if test="${not fn:contains(account.TM_TASK, '~') }">
                                                    <c:set var="firstChar" value="${fn:substring(account.TM_TASK, 0, 1)}" />
                                                    <c:if test="${Character.isLetter(firstChar)}">
                                                        <p class="card-text result-sub-desc-text">${account.TM_TASK}</p>
                                                    </c:if>
                                                    <c:if test="${not Character.isLetter(firstChar)}">
                                                        <c:set var="tmTaskList" value="${fn:split(account.TM_TASK, firstChar)}" />
                                                        <ul class="card-text result-sub-desc-text">
                                                            <c:forEach items="${tmTaskList}" var="tmTask">
                                                                <li>${tmTask}</li>
                                                            </c:forEach>
                                                        </ul>
                                                    </c:if>
                                                </c:if>
                                            </c:if>
                                            <c:if test="${empty account.TM_TASK}">
                                                <ul class="card-text result-sub-desc-text">
                                                    <p>N/A</p>
                                                </ul>
                                            </c:if>
                                            <span class="result-sub-desc"><strong>Skills</strong></span>
                                            <c:if test="${not empty account.SKILLS}">
                                                <c:if test="${fn:contains(account.SKILLS, '~') }">
                                                    <c:set var="skillList" value="${fn:split(account.SKILLS, '~')}" />
                                                    <ul class="card-text result-sub-desc-text">
                                                        <c:forEach items="${skillList}" var="skill">
                                                            <li>${skill}</li>
                                                        </c:forEach>
                                                    </ul>
                                                </c:if>
                                                <c:if test="${not fn:contains(account.SKILLS, '~') }">
                                                    <c:set var="firstChar" value="${fn:substring(account.SKILLS, 0, 1)}" />
                                                    <c:if test="${Character.isLetter(firstChar)}">
                                                        <p class="card-text result-sub-desc-text">${account.SKILLS}</p>
                                                    </c:if>
                                                    <c:if test="${not Character.isLetter(firstChar)}">
                                                        <c:set var="skillList" value="${fn:split(account.SKILLS, firstChar)}" />
                                                        <ul class="card-text result-sub-desc-text">
                                                            <c:forEach items="${skillList}" var="skill">
                                                                <li>${skill}</li>
                                                            </c:forEach>
                                                        </ul>
                                                    </c:if>
                                                </c:if>
                                            </c:if>
                                            <c:if test="${empty account.SKILLS}">
                                                <ul class="card-text result-sub-desc-text">
                                                    <p>N/A</p>
                                                </ul>
                                            </c:if>
                                            <span class="result-sub-desc"><strong>Trainings</strong></span>
                                            <c:if test="${not empty account.TRAININGS}">
                                                <c:if test="${fn:contains(account.TRAININGS, '~') }">
                                                    <c:set var="trainingList" value="${fn:split(account.TRAININGS, '~')}" />
                                                    <p class="card-text result-sub-desc-text" style="margin-bottom: 0px">Visit MyGrowth to find the courses below:</p>
                                                    <ul class="card-text result-sub-desc-text" style="margin-top: 0px">
                                                        <c:forEach items="${trainingList}" var="training">
                                                            <!-- Start Change - Daryll Moya - 23-FEB-2021 -->
                                                            <!-- <li>${training}</li> -->
                                                            <li><a id="googleforms" href="javascript:window.open('https://mygrowthlms.bridgeapp.com/learner/library?search=${training}', '_blank');">${training}</a></li>
                                                            <!-- End Change - Daryll Moya - 23-FEB-2021 -->
                                                        </c:forEach>
                                                    </ul>
                                                </c:if>
                                                <c:if test="${not fn:contains(account.TRAININGS, '~') }">
                                                    <c:set var="firstChar" value="${fn:substring(account.TRAININGS, 0, 1)}" />
                                                    <c:if test="${Character.isLetter(firstChar)}">
                                                        <c:if test="${fn:contains(account.TRAININGS, ',') }">
                                                            <c:set var="trainingList" value="${fn:split(account.TRAININGS, ',')}" />
                                                            <p class="card-text result-sub-desc-text" style="margin-bottom: 0px">Visit MyGrowth to find the courses below:</p>
                                                            <ul class="card-text result-sub-desc-text" style="margin-top: 0px">
                                                                <c:forEach items="${trainingList}" var="training">
                                                                    <!-- Start Change - Daryll Moya - 23-FEB-2021 -->
                                                                    <!-- <li>${training}</li> -->
                                                                    <li><a id="googleforms" href="javascript:window.open('https://mygrowthlms.bridgeapp.com/learner/library?search=${training}', '_blank');">${training}</a></li>
                                                                    <!-- End Change - Daryll Moya - 23-FEB-2021 -->
                                                                </c:forEach>
                                                            </ul>
                                                        </c:if>
                                                        <c:if test="${not fn:contains(account.TRAININGS, ',') }">
                                                            <p class="card-text result-sub-desc-text">${account.TRAININGS}</p>
                                                        </c:if>
                                                    </c:if>
                                                    <c:if test="${not Character.isLetter(firstChar)}">
                                                        <c:set var="trainingList" value="${fn:split(account.SKILLS, TRAININGS)}" />
                                                        <p class="card-text result-sub-desc-text" style="margin-bottom: 0px">Visit MyGrowth to find the courses below:</p>
                                                        <ul class="card-text result-sub-desc-text" style="margin-top: 0px">
                                                            <c:forEach items="${trainingList}" var="training">
                                                                <!-- Start Change - Daryll Moya - 23-FEB-2021 -->
                                                                <!-- <li>${training}</li> -->
                                                                <li><a id="googleforms" href="javascript:window.open('https://mygrowthlms.bridgeapp.com/learner/library?search=${training}', '_blank');">${training}</a></li>
                                                                <!-- End Change - Daryll Moya - 23-FEB-2021 -->
                                                            </c:forEach>
                                                        </ul>
                                                    </c:if>
                                                </c:if>
                                            </c:if>
                                            <c:if test="${empty account.TRAININGS}">
                                                <ul class="card-text result-sub-desc-text">
                                                    <p>N/A</p>
                                                </ul>
                                            </c:if>
                                            <c:if test="${not empty account.JOB_REQ}">
                                                <span class="result-sub-desc">
                                                    <strong>Job Requisition</strong>
                                                    <p class="card-text result-sub-desc-text" style="margin-bottom: 0.1rem;">You may click on any of the links below to learn more about the vacancies.</p>
                                                    <!--  
                                                    <p class="card-text result-sub-desc-text" style="color: red !important; margin-bottom: 0.1rem;">Kindly copy the Job Requisition below and paste it on the search bar on Workday.</p>
                                                    <p class="card-text result-sub-desc-text" style="margin-top: 0.1rem;">Visit Workday by clicking
                                                        <a id="googleforms" href="javascript:window.open('https://wd3.myworkday.com/telusinternational/d/task/1422$3375.htmld', '_blank');">here</a> and search the job requisition below.
                                                    </p>
                                                    -->
                                                </span>
                                                <c:set var="jobReqList" value="${fn:split(account.JOB_REQ, ', ' )}" />
                                                <c:set var="jobReqUrl" value="${fn:split(account.INTERNAL_POSTING_URL, ', ' )}" />
                                                <ul>      
                                                    <c:forEach items="${jobReqList}" var="jobReq" varStatus="status">
                                                        <li><a id="googleforms" href="javascript:window.open('${jobReqUrl[status.index]}', '_blank');">${jobReq}</a></li>
                                                    </c:forEach>
                                                </ul>
                                            </c:if>
                                            <c:if test="${empty account.JOB_REQ}">
                                                <span class="result-sub-desc">
                                                    <strong>Job Requisition</strong>
                                                    <p class="card-text result-sub-desc-text">Sorry, no related open job requisition as of the moment.</p>
                                                </span>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-12 text-muted" style="font-size: 0.8rem; color: red !important;">
                                <h6>Disclaimer</h6>
                            </div>
                            <div class="col-md-12 text-muted" style="font-size: 0.8rem;">
                                <small>
                                    <p style="margin-bottom: 0.1rem;">Please be guided that the generated results do not guarantee immediate transfer or promotion.</p>
                                    <p style="margin-bottom: 0.1rem;">All team members will still go through our internal application process. Minimum requirements are:</p>
                                    <ul>
                                        <li>At least six (6) months in current role.</li>
                                        <li>No pending disciplinary sanction.</li>
                                        <li>Endorsement from immediate and next-in-line manager.</li>
                                        <li>Weighted average Baleen score of over 3.0 for the past six (6) months.</li>
                                        <!--
                                        <c:if test="${not empty account.JOB_REQ}">
                                            <a id="workdaylink" href="${account.JOB_REQ}">To see all available openings, click here.</a>
                                        </c:if>
                                        <c:if test="${empty account.JOB_REQ}">
                                            <a id="workdaylink" href="https://wd3.myworkday.com/telusinternational/d/inst/13102!CK5mGhIKBggDEMenAhIICgYI1A0QrwI~/cacheable-task/2997$2151.htmld">Job requesition is currently not available. Click this link to proceed finding other opportunities.</a>
                                        </c:if>
                                        -->
                                    </ul>
                                </small>
                                <a id="googleforms" href="https://docs.google.com/forms/d/1y5cVLvfEj-fbYDhno1qQeO7n11kcdqKKw_uiJaQ8B50/edit?ts=5b898cb4">Click here and tell us what you think.</a><br/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</c:if>

<!-- SUPPORT - support -->
<c:if test="${pillar == 'support'}">
    <div class="container-fluid account-container" style="padding-right: 0; padding-left: 0;">
        <div class="col-md-12" style="margin-bottom: 1%; padding: 0;">
            <div class="page-title-main">
                <!-- Start Change - Daryll Moya - 02-MAR-2021 -->
                <!-- <h2>Support</h2> -->
                <h2 style="margin-bottom: 0px;">Support</h2>
                <!-- End Change - Daryll Moya - 02-MAR-2021 -->
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div style="padding: 0 0 1rem 0; width: 100%;">
                    <div class="col-md-12 row" style="margin: 0; padding: 0;">
                        <%--
                        <div class="input-group search-group col-md-4" style="height: 38px">
                            <div class="input-group-prepend">
                                <span class="input-group-text search-text">
                                    <i class="fas fa-search"></i>
                                </span>
                            </div>
                            <input type="text" name="filtr-search" class="form-control" placeholder="Search Keyword (e.g. HR, iAspire, Communications)" aria-label="Input group example" aria-describedby="btnGroupAddon" data-search>
                        </div>
                        <div class="col-md-8 category-content" style="float:right;">
                            <button type="button" class="category-btn" data-filter="all">All</button>
                            <!-- dynamic buttons for support -->
                            <c:forEach items="${sitelist}" var="sites">
                                <button type="button" class="category-btn" value="${sites.SITE_NAME}" data-filter="${sites.SITE_NAME}">${sites.SITE_NAME}</button>
                            </c:forEach>
                        </div>
                        --%>
                        <div class="col-md-4" style="padding: 0;">
                        	<div class="dropdown">
  								<button class="dropdown-toggle" id="department-textbtn" style="font-size: 1em;" type="button" data-toggle="dropdown"><span>All Departments</span></button>
  								<div class="dropdown-menu" id="department-menu" aria-labelledby="dropdownMenuButton" x-placement="bottom-start" style="max-height: 280px; overflow-y: auto;" >
    						   		<button class="dropdown-item" type="button" data-filter="all">All Departments</button>
    						   		<c:forEach items="${accountList}" var="support" varStatus="loop">
    						   			<c:if test="${support.DEPARTMENT != accountList[loop.index - 1].DEPARTMENT}">
                                			<button class="dropdown-item" type="button" <%-- value="${unique}" --%> data-filter="${support.DEPARTMENT}">${support.DEPARTMENT}</button>
                                		</c:if>
                            		</c:forEach>
  								</div>
							</div>
                        </div>
                        <div class="col-md-8"></div>
                    </div>
                </div>
                <div class="card-deck filtr-container">
                    <c:forEach items="${accountList}" var="account">
                        <div class="card col-md-3 filtr-item" data-category="${account.DEPARTMENT}" onclick="addClickCount(${account.POSITION_ID},'ST')">
                            <a href="#" data-target="#${account.POSITION_ID}" data-toggle="modal" style="text-decoration: none; outline: none;">
                            	<input type="hidden" id="workdayUsername" value="">
                            	<c:if test="${empty account.IMAGE_PATH}">
                            		<div class="card-img-top" style="text-align: center;">
                                		<img src="${ctx}/img/index/telus_2018_logo3.png" alt="card image" style="padding: 25% 5%;">
                               		</div>
                           		</c:if>
                                <c:if test="${not empty account.IMAGE_PATH}">
                                    <div class="card-img-top" style="text-align: center;">
                                        <img src="${account.IMAGE_PATH}" alt="card image">
                                    </div>
                                </c:if>
                                <div class="card-body text-dark" style="text-align: center; padding: 0px;">
                                    <div class="card-title">
                                        <h5>
                                            <strong>${account.JOB_PROFILE}</strong>
                                        </h5>
                                    </div>
                                    <div class="card-subtitle">
                                        <c:if test="${not empty account.SUPPORT_TYPE}">
                                            <p>${account.SUPPORT_TYPE}</p>
                                        </c:if>
                                        <c:if test="${empty account.SUPPORT_TYPE}">
                                            <p>N/A</p>
                                        </c:if>
                                        <c:if test="${not empty account.DEPARTMENT}">
                                            <p>${account.DEPARTMENT}</p>
                                        </c:if>
                                        <c:if test="${empty account.DEPARTMENT}">
                                            <p>N/A</p>
                                        </c:if>
                                    </div>
                                    <div hidden="true">
                                        <p>${account.CERTIFICATION}</p>
                                        <p>${account.EDUCATIONAL_BACKGROUND}</p>
                                        <p>${account.EXPERIENCE_REQUIRED}</p>
                                        <p>${account.SKILLS_REQUIRED}</p>
                                        <p>${account.OTHER_SKILLS}</p>
                                        <p>${account.CATEGORY}</p>
                                        <p>${account.TRAININGS}</p>
                                    </div>
                                </div>
                                <div class="card-footer"></div>
                            </a>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
        <c:forEach items="${accountList}" var="account">
            <div class="modal fade" id="${account.POSITION_ID}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
                    <div class="modal-content">
                        <div>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close" style="float: right; padding: 1em;">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <div class="container-fluid">
                                <div class="row">
                                    <div class="account-modal-head col-md-12 row ">
                                        <div class="col-md-3 account-modal-image">
                                            <img class="card-img-bottom" src="${account.IMAGE_PATH}" alt="card image">
                                        </div>
                                        <section class="account-modal-title col-md-9 row ">
                                            <section class="col-md-12 ">
                                                <h2>
                                                    <strong>${account.JOB_PROFILE}</strong>
                                                </h2>
                                            </section>
                                            <!-- 1st row -->
                                            <c:if test="${not empty account.EDUCATIONAL_BACKGROUND}">
                                                <div class="col-md-6">
                                                    <table class="table">
                                                        <tr>
                                                            <td style="width: 3vw; padding-top: 3px;" title="Education">
                                                                <span>
                                                                    <i class="fas fa-book"></i>
                                                                </span>
                                                            </td>
                                                            <td style="padding-top: 0; padding-bottom: 0;">${account.EDUCATIONAL_BACKGROUND}</td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </c:if>
                                            <c:if test="${empty account.EDUCATIONAL_BACKGROUND}">
                                                <div class="col-md-6">
                                                    <table class="table">
                                                        <tr>
                                                            <td style="width: 3vw; padding-top: 3px;" title="Education">
                                                                <span>
                                                                    <i class="fas fa-book"></i>
                                                                </span>
                                                            </td>
                                                            <td style="padding-top: 0; padding-bottom: 0;">N/A</td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </c:if>
                                            <c:if test="${not empty account.EXPERIENCE_REQUIRED}">
                                                <div class="col-md-6">
                                                    <table class="table">
                                                        <tr>
                                                            <td style="width: 3vw; padding-top: 3px;" title="Experience">
                                                                <small>
                                                                    <strong>Exp</strong>
                                                                </small>
                                                            </td>
                                                            <td style="padding-top: 0; padding-bottom: 0;">${account.EXPERIENCE_REQUIRED}</td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </c:if>
                                            <c:if test="${empty account.EXPERIENCE_REQUIRED}">
                                                <div class="col-md-6">
                                                    <table class="table">
                                                        <tr>
                                                            <td style="width: 3vw; padding-top: 3px;" title="Experience">
                                                                <small>
                                                                    <strong>Exp</strong>
                                                                </small>
                                                            </td>
                                                            <td style="padding-top: 0; padding-bottom: 0;">N/A</td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </c:if>
                                            <!-- 2nd row -->
                                            <c:if test="${not empty account.SUPPORT_TYPE}">
                                                <div class="col-md-6">
                                                    <table class="table">
                                                        <tr>
                                                            <td style="width: 3vw; padding-top: 3px;" title="Type of Support">
                                                                <i class="fas fa-user-tie"></i>
                                                            </td>
                                                            <td style="padding-top: 0; padding-bottom: 0;">${account.SUPPORT_TYPE}</td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </c:if>
                                            <c:if test="${empty account.SUPPORT_TYPE}">
                                                <div class="col-md-6">
                                                    <table class="table">
                                                        <tr>
                                                            <td style="width: 3vw; padding-top: 3px;" title="Type of Support">
                                                                <i class="fas fa-user-tie"></i>
                                                            </td>
                                                            <td style="padding-top: 0; padding-bottom: 0;">N/A</td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </c:if>
                                            <c:if test="${not empty account.DEPARTMENT}">
                                                <div class="col-md-6">
                                                    <table class="table">
                                                        <tr>
                                                            <td style="width: 3vw; padding-top: 3px;" title="Department">
                                                                <i class="fas fa-briefcase"></i>
                                                            </td>
                                                            <td style="padding-top: 0; padding-bottom: 0;">${account.DEPARTMENT}</td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </c:if>
                                            <c:if test="${empty account.DEPARTMENT}">
                                                <div class="col-md-6">
                                                    <table class="table">
                                                        <tr>
                                                            <td style="width: 3vw; padding-top: 3px;" title="Department">
                                                                <i class="fas fa-briefcase"></i>
                                                            </td>
                                                            <td style="padding-top: 0; padding-bottom: 0;">N/A</td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </c:if>
                                        </section>
                                    </div>
                                    <div class="card col-md-12">
                                        <div class="card-block">
                                            <span class="result-sub-desc">
                                                <strong>Description</strong>
                                            </span>
                                            <!--
                                            <c:if test="${not empty account.DESCRIPTION}">
                                                <p class="card-text result-sub-desc-text">${account.DESCRIPTION}</p>
                                            </c:if>
                                            <c:if test="${empty account.DESCRIPTION}">
                                                <p class="card-text result-sub-desc-text">N/A</p>
                                            </c:if>
                                            -->
                                            <c:set var="firstChar" value="${fn:substring(account.DESCRIPTION, 0, 1)}" />
                                            <c:if test="${Character.isLetter(firstChar)}">
                                                <p class="card-text result-sub-desc-text" style="margin-bottom: 0;">${account.DESCRIPTION}</p>
                                            </c:if>
                                            <c:if test="${not Character.isLetter(firstChar)}">
                                                <div style="height: auto; margin: 1rem auto auto;">
                                                    <ul>
                                                        <c:set var="firstChar" value="${fn:substring(account.DESCRIPTION, 0, 1)}" />
                                                        <c:set var="descList" value="${fn:split(account.DESCRIPTION, firstChar)}" />
                                                        <c:forEach items="${descList}" var="desc">
                                                            <li>${desc}</li>
                                                        </c:forEach>
                                                    </ul>
                                                </div>
                                            </c:if>
                                            <c:set var="skillList" value="${fn:split(account.SKILLS_REQUIRED, '~' )}" />
                                            <c:if test="${not empty account.SKILLS_REQUIRED}">
                                                <span class="result-sub-desc">
                                                    <strong>SKILLS</strong>
                                                </span>
                                                <ul class="card-text result-sub-desc-text">
                                                    <c:forEach items="${skillList}" var="skill">
                                                        <li>${skill }</li>
                                                    </c:forEach>
                                                </ul>
                                            </c:if>
                                            <c:set var="otherSkillList" value="${fn:split(account.OTHER_SKILLS, '~' )}" />
                                            <c:if test="${not empty account.OTHER_SKILLS}">
                                                <span class="result-sub-desc">
                                                    <strong>Other skills</strong>
                                                </span>
                                                <ul class="card-text result-sub-desc-text">
                                                    <c:forEach items="${otherSkillList}" var="otherSkill">
                                                        <li>${otherSkill}</li>
                                                    </c:forEach>
                                                </ul>
                                            </c:if>
                                            <c:if test="${not empty account.CERTIFICATION}">
                                                <span class="result-sub-desc">
                                                    <strong>Certification</strong>
                                                </span>
                                                <p class="card-text result-sub-desc-text">${account.CERTIFICATION}</p>
                                            </c:if>
                                            <span class="result-sub-desc"><strong>TRAININGS</strong>
                                                <p class="card-text result-sub-desc-text">Visit MyGrowth to find the courses below</p> </span>
                                            <c:set var="trainingList" value="${fn:split(account.TRAININGS, '~' )}" />
                                            <ul>
                                                <c:if test="${not empty account.TRAININGS}">
                                                    <c:forEach items="${trainingList}" var="training">
                                                        <!-- Start Change - Daryll Moya - 23-FEB-2021 -->
                                                        <!-- <li>${training}</li> -->
                                                        <li><a id="googleforms" href="javascript:window.open('https://mygrowthlms.bridgeapp.com/learner/library?search=${training}', '_blank');">${training}</a></li>
                                                        <!-- End Change - Daryll Moya - 23-FEB-2021 -->
                                                    </c:forEach>
                                                </c:if>
                                                <c:if test="${empty account.TRAININGS}">
                                                    <li>N/A</li>
                                                </c:if>
                                            </ul>
                                            <c:if test="${not empty account.JOB_REQ}">
                                                <span class="result-sub-desc">
                                                    <strong>Job Requisition</strong>
                                                    <p class="card-text result-sub-desc-text" style="color: red !important; margin-bottom: 0.1rem;">Kindly copy the Job Requisition below and paste it on the search bar on Workday.</p>
                                                    <p class="card-text result-sub-desc-text" style="margin-top: 0.1rem;">Visit Workday by clicking
                                                        <a id="googleforms" href="javascript: window.location.href='https://wd3.myworkday.com/telusinternational/d/task/1422$3375.htmld'">here</a>
                                                        and search the job requisition below.
                                                    </p>
                                                </span>
                                                <c:set var="jobReqList" value="${fn:split(account.JOB_REQ, ', ' )}" />
                                                <ul>
                                                    <c:forEach items="${jobReqList}" var="jobReq">
                                                        <li>${jobReq}</li>
                                                    </c:forEach>
                                                </ul>
                                            </c:if>
                                            <c:if test="${empty account.JOB_REQ}">
                                                <span class="result-sub-desc">
                                                    <strong>Job Requisition</strong>
                                                    <p class="card-text result-sub-desc-text">Sorry, no related open job requisition as of the moment.</p>
                                                </span>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-12 text-muted" style="font-size: 0.8rem; color: red !important;">
                                <h6>Disclaimer</h6>
                            </div>
                            <div class="col-md-12 text-muted" style="font-size: 0.8rem;">
                                <small>
                                    <p style="margin-bottom: 0.1rem;">Please be guided that the generated results do not guarantee immediate transfer or promotion.</p>
                                    <p style="margin-bottom: 0.1rem;">All team members will still go through our internal application process. Minimum requirements are:</p>
                                    <ul>
                                        <li>At least six (6) months in current role.</li>
                                        <li>No pending disciplinary sanction.</li>
                                        <li>Endorsement from immediate and next-in-line manager.</li>
                                        <li>Weighted average Baleen score of over 3.0 for the past six (6) months.</li>
                                        <!--
                                        <li>${account.JOB_REQ}</li>
                                        <c:if test="${not empty account.JOB_REQ}">
                                            <a id="workdaylink" href="${account.JOB_REQ}">To see all available openings, click here.</a>
                                        </c:if>
                                        <c:if test="${empty account.JOB_REQ}">
                                            <a id="workdaylink" href="https://wd3.myworkday.com/telusinternational/d/inst/13102!CK5mGhIKBggDEMenAhIICgYI1A0QrwI~/cacheable-task/2997$2151.htmld">Job requesition is currently not available. Click this link to proceed finding other opportunities.</a>
                                        </c:if>
                                        -->
                                    </ul>
                                </small>
                                <a id="googleforms" href="https://docs.google.com/forms/d/1y5cVLvfEj-fbYDhno1qQeO7n11kcdqKKw_uiJaQ8B50/edit?ts=5b898cb4">Click here and tell us what you think.</a><br/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</c:if>

<!-- DIGITAL SERVICES - digitalsolutions -->
<c:if test="${pillar == 'digitalsolutions'}">
    <div class="container-fluid account-container" style="padding-right: 0; padding-left: 0;">
        <div class="col-md-12" style="margin-bottom: 1%; padding: 0;">
            <div class="page-title-main">
                <!-- Start Change - Daryll Moya - 02-MAR-2021 -->
                <!-- <h2>Digital Solutions</h2> -->
                <h2 style="margin-bottom: 0px;">Digital Solutions</h2>
                <!-- End Change - Daryll Moya - 02-MAR-2021 -->
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div style="padding: 0 0 1rem 0; width: 100%;">
                    <div class="col-md-12 row" style="margin: 0; padding: 0;">
                        <div class="input-group search-group col-md-4">
                            <div class="input-group-prepend">
                                <span class="input-group-text search-text">
                                    <i class="fas fa-search"></i>
                                </span>
                            </div>
                            <input type="text" name="filtr-search" class="form-control" placeholder="Search Keyword (e.g. Java, Infrastracture, 3 years)" aria-label="Input group example" aria-describedby="btnGroupAddon" data-search>
                        </div>
                    </div>
                </div>
                <div class="card-deck filtr-container">
                    <c:forEach items="${accountList}" var="account">
                        <div class="card col-md-3 filtr-item" data-category="" onclick="addClickCount(${account.POSITION_ID},'DS')">
                            <input type="hidden" id="workdayUsername" value="">
                                <a href="#" data-target="#${account.POSITION_ID}" data-toggle="modal" style="text-decoration: none; outline: none;">
                                <c:if test="${empty account.IMAGE_PATH}">
                                    <div class="card-img-top" style="text-align: center;">
                                        <img src="${ctx}/img/index/telus-logo.png" alt="card image" style="padding: 25% 5%;">
                                    </div>
                                </c:if>
                                <c:if test="${not empty account.IMAGE_PATH}">
                                    <div class="card-img-top" style="text-align: center;">
                                        <img class="card-img-top" src="${account.IMAGE_PATH}" alt="card image">
                                    </div>
                                </c:if>
                                <div class="card-body text-dark"
                                    style="text-align: center; padding: 0px;">
                                    <div class="card-title">
                                        <h5>
                                            <strong>${account.JOB_PROFILE}</strong>
                                        </h5>
                                    </div>
                                    <div class="card-subtitle">
                                        <c:if test="${not empty account.SUPPORT_TYPE}">
                                            <p>${account.SUPPORT_TYPE}</p>
                                        </c:if>
                                        <c:if test="${empty account.SUPPORT_TYPE}">
                                            <p>N/A</p>
                                        </c:if>
                                        <c:if test="${not empty account.DEPARTMENT}">
                                            <p>${account.DEPARTMENT}</p>
                                        </c:if>
                                        <c:if test="${empty account.DEPARTMENT}">
                                            <p>N/A</p>
                                        </c:if>
                                    </div>
                                    <div hidden="true">
                                        <p>${account.CERTIFICATION}</p>
                                        <p>${account.EDUCATIONAL_BACKGROUND}</p>
                                        <p>${account.EXPERIENCE_REQUIRED}</p>
                                        <p>${account.SKILLS_REQUIRED}</p>
                                        <p>${account.OTHER_SKILLS}</p>
                                        <p>${account.CATEGORY}</p>
                                        <p>${account.TRAININGS}</p>
                                    </div>
                                </div>
                                <div class="card-footer"></div>
                            </a>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
        <c:forEach items="${accountList}" var="account">
            <div class="modal fade" id="${account.POSITION_ID}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
                    <div class="modal-content">
                        <div>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close" style="float: right; padding: 1em;">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <div class="container-fluid">
                                <div class="row">
                                    <div class="account-modal-head col-md-12 row ">
                                        <div class="col-md-3 account-modal-image">
                                            <img class="card-img-bottom" src="${account.IMAGE_PATH}" alt="card image">
                                        </div>
                                        <section class="account-modal-title col-md-9 row ">
                                            <section class="col-md-12 ">
                                                <h2>
                                                    <strong>${account.JOB_PROFILE}</strong>
                                                </h2>
                                            </section>
                                            <!-- 1st row -->
                                            <c:if test="${not empty account.EDUCATIONAL_BACKGROUND}">
                                                <div class="col-md-6">
                                                    <table class="table">
                                                        <tr>
                                                            <td style="width: 3vw; padding-top: 3px;" title="Education">
                                                                <span>
                                                                    <i class="fas fa-book"></i>
                                                                </span>
                                                            </td>
                                                            <td style="padding-top: 0; padding-bottom: 0;">${account.EDUCATIONAL_BACKGROUND}</td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </c:if>
                                            <c:if test="${empty account.EDUCATIONAL_BACKGROUND}">
                                                <div class="col-md-6">
                                                    <table class="table">
                                                        <tr>
                                                            <td style="width: 3vw; padding-top: 3px;" title="Education">
                                                                <span>
                                                                    <i class="fas fa-book"></i>
                                                                </span>
                                                            </td>
                                                            <td style="padding-top: 0; padding-bottom: 0;">N/A</td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </c:if>
                                            <c:if test="${not empty account.EXPERIENCE_REQUIRED}">
                                                <div class="col-md-6">
                                                    <table class="table">
                                                        <tr>
                                                            <td style="width: 3vw; padding-top: 3px;" title="Experience">
                                                                <small>
                                                                    <strong>Exp</strong>
                                                                </small>
                                                            </td>
                                                            <td style="padding-top: 0; padding-bottom: 0;">${account.EXPERIENCE_REQUIRED}</td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </c:if>
                                            <c:if test="${empty account.EXPERIENCE_REQUIRED}">
                                                <div class="col-md-6">
                                                    <table class="table">
                                                        <tr>
                                                            <td style="width: 3vw; padding-top: 3px;" title="Experience">
                                                                <small>
                                                                    <strong>Exp</strong>
                                                                </small>
                                                            </td>
                                                            <td style="padding-top: 0; padding-bottom: 0;">N/A</td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </c:if>
                                            <!-- 2nd row -->
                                            <c:if test="${not empty account.SUPPORT_TYPE}">
                                                <div class="col-md-6">
                                                    <table class="table">
                                                        <tr>
                                                            <td style="width: 3vw; padding-top: 3px;" title="Type of Support">
                                                                <i class="fas fa-user-tie"></i>
                                                            </td>
                                                            <td style="padding-top: 0; padding-bottom: 0;">${account.SUPPORT_TYPE}</td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </c:if>
                                            <c:if test="${empty account.SUPPORT_TYPE}">
                                                <div class="col-md-6">
                                                    <table class="table">
                                                        <tr>
                                                            <td style="width: 3vw; padding-top: 3px;" title="Type of Support">
                                                                <i class="fas fa-user-tie"></i>
                                                            </td>
                                                            <td style="padding-top: 0; padding-bottom: 0;">N/A</td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </c:if>
                                            <c:if test="${not empty account.DEPARTMENT}">
                                                <div class="col-md-6">
                                                    <table class="table">
                                                        <tr>
                                                            <td style="width: 3vw; padding-top: 3px;" title="Department">
                                                                <i class="fas fa-map-marker-alt"></i>
                                                            </td>
                                                            <td style="padding-top: 0; padding-bottom: 0;">${account.DEPARTMENT}</td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </c:if>
                                            <c:if test="${empty account.DEPARTMENT}">
                                                <div class="col-md-6">
                                                    <table class="table">
                                                        <tr>
                                                            <td style="width: 3vw; padding-top: 3px;" title="Department">
                                                                <i class="fas fa-map-marker-alt"></i>
                                                            </td>
                                                            <td style="padding-top: 0; padding-bottom: 0;">N/A</td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </c:if>
                                        </section>
                                    </div>
                                    <div class="card col-md-12">
                                        <div class="card-block">
                                            <span class="result-sub-desc">
                                                <strong>Description</strong>
                                            </span>
                                            <!--
                                            <c:if test="${not empty account.DESCRIPTION}">
                                                <p class="card-text result-sub-desc-text">${account.DESCRIPTION}</p>
                                            </c:if>
                                            <c:if test="${empty account.DESCRIPTION}">
                                                <p class="card-text result-sub-desc-text">N/A</p>
                                            </c:if>
                                            -->
                                            <c:set var="firstChar" value="${fn:substring(account.DESCRIPTION, 0, 1)}" />
                                            <c:if test="${Character.isLetter(firstChar)}">
                                                <p class="card-text result-sub-desc-text" style="margin-bottom: 0;">${account.DESCRIPTION}</p>
                                            </c:if>
                                            <c:if test="${not Character.isLetter(firstChar)}">
                                                <div style="height: auto; margin: 1rem auto auto;">
                                                    <ul>
                                                        <c:set var="firstChar" value="${fn:substring(account.DESCRIPTION, 0, 1)}" />
                                                        <c:set var="descList" value="${fn:split(account.DESCRIPTION, firstChar)}" />
                                                        <c:forEach items="${descList}" var="desc">
                                                            <li>${desc}</li>
                                                        </c:forEach>
                                                    </ul>
                                                </div>
                                            </c:if>
                                            <c:set var="skillList" value="${fn:split(account.SKILLS_REQUIRED, '~' )}" />
                                            <c:if test="${not empty account.SKILLS_REQUIRED}">
                                                <span class="result-sub-desc">
                                                    <strong>Skills</strong>
                                                </span>
                                                <ul class="card-text result-sub-desc-text">
                                                    <c:forEach items="${skillList}" var="skill">
                                                        <li>${skill }</li>
                                                    </c:forEach>
                                                </ul>
                                            </c:if>
                                            <c:set var="otherSkillList" value="${fn:split(account.OTHER_SKILLS, '~' )}" />
                                            <c:if test="${not empty account.OTHER_SKILLS}">
                                                <span class="result-sub-desc">
                                                    <strong>Other Skills</strong>
                                                </span>
                                                <ul class="card-text result-sub-desc-text">
                                                    <c:forEach items="${otherSkillList}" var="otherSkill">
                                                        <li>${otherSkill}</li>
                                                    </c:forEach>
                                                </ul>
                                            </c:if>
                                            <c:if test="${not empty account.CERTIFICATION}">
                                                <span class="result-sub-desc">
                                                    <strong>Certification</strong>
                                                </span>
                                                <p class="card-text result-sub-desc-text">${account.CERTIFICATION}</p>
                                            </c:if>
                                            <span class="result-sub-desc">
                                                <strong>TRAININGS</strong>
                                                <p class="card-text result-sub-desc-text">Visit MyGrowth to find the courses below</p>
                                            </span>
                                            <c:set var="trainingList" value="${fn:split(account.TRAININGS, '~' )}" />
                                            <ul>
                                                <c:if test="${not empty account.TRAININGS}">
                                                    <c:forEach items="${trainingList}" var="training">
                                                        <!-- Start Change - Daryll Moya - 23-FEB-2021 -->
                                                        <!-- <li>${training}</li> -->
                                                        <li><a id="googleforms" href="javascript:window.open('https://mygrowthlms.bridgeapp.com/learner/library?search=${training}', '_blank');">${training}</a></li>
                                                        <!-- End Change - Daryll Moya - 23-FEB-2021 -->
                                                    </c:forEach>
                                                </c:if>
                                                <c:if test="${empty account.TRAININGS}">
                                                    <li>N/A</li>
                                                </c:if>
                                            </ul>
                                            <c:if test="${not empty account.JOB_REQ}">
                                                <span class="result-sub-desc">
                                                    <strong>Job Requisition</strong>
                                                    <p class="card-text result-sub-desc-text" style="color: red !important; margin-bottom: 0.1rem;">Kindly copy the Job Requisition below and paste it on the search bar on Workday.</p>
                                                    <p class="card-text result-sub-desc-text" style="margin-top: 0.1rem;">Visit Workday by clicking
                                                        <a id="googleforms" href="javascript: window.location.href='https://wd3.myworkday.com/telusinternational/d/task/1422$3375.htmld'">here</a> and search the job requisition below.
                                                    </p>
                                                </span>
                                                <c:set var="jobReqList" value="${fn:split(account.JOB_REQ, ', ' )}" />
                                                <ul>
                                                    <c:forEach items="${jobReqList}" var="jobReq">
                                                        <li>${jobReq}</li>
                                                    </c:forEach>
                                                </ul>
                                            </c:if>
                                            <c:if test="${empty account.JOB_REQ}">
                                                <span class="result-sub-desc">
                                                    <strong>Job Requisition</strong>
                                                    <p class="card-text result-sub-desc-text">Sorry, no related open job requisition as of the moment.</p>
                                                </span>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-12 text-muted" style="font-size: 0.8rem; color: red !important;">
                                <h6>Disclaimer</h6>
                            </div>
                            <div class="col-md-12 text-muted" style="font-size: 0.8rem;">
                                <small>
                                    <p style="margin-bottom: 0.1rem;">Please be guided that the generated results do not guarantee immediate transfer or promotion.</p>
                                    <p style="margin-bottom: 0.1rem;">All team members will still go through our internal application process. Minimum requirements are:</p>
                                    <ul>
                                        <li>At least six (6) months in current role.</li>
                                        <li>No pending disciplinary sanction.</li>
                                        <li>Endorsement from immediate and next-in-line manager.</li>
                                        <!--
                                        <c:if test="${not empty account.JOB_REQ}">
                                            <a id="workdaylink" href="${account.JOB_REQ}">To see all available openings, click here.</a>
                                        </c:if>
                                        <c:if test="${empty account.JOB_REQ}">
                                            <a id="workdaylink" href="https://wd3.myworkday.com/telusinternational/d/inst/13102!CK5mGhIKBggDEMenAhIICgYI1A0QrwI~/cacheable-task/2997$2151.htmld">Job requesition is currently not available. Click this link to proceed finding other opportunities.</a>
                                        </c:if>
                                        -->
                                    </ul>
                                </small>
                                <a id="googleforms" href="https://docs.google.com/forms/d/1y5cVLvfEj-fbYDhno1qQeO7n11kcdqKKw_uiJaQ8B50/edit?ts=5b898cb4">Click here and tell us what you think.</a><br/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</c:if>
<script src="${ctx}/js/account.js" type="text/javascript"></script>
<hr id="afterAbout">
<jsp:include page="/WEB-INF/jsp/common/footer.jsp" />