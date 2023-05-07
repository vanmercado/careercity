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
            <!-- <img class="navbar-brand" alt="" src="${ctx}/img/accounts/tip-logo.png" style="margin-top: -6%;"></img> -->
            <img class="navbar-brand" alt="" src="${ctx}/img/index/telus-logo.png" style="height: 80%; margin: 0.4rem 0;"></img>
        </div>
        <div class="col-4" style="text-align: right; float: right;">
            <c:if test="${not empty matchedCcoResults || not empty matchedDsStResults}">
                <a class="nav-link retake-quiz far" href="${ctx}/quiz">
                    <i class="fas fa-sync-alt"></i>
                    <span style="font-family: 'TELUS-Web', 'Helvetica Neue', Helvetica, Arial, sans-serif !important;">RETAKE QUIZ</span>
                </a>
            </c:if>
        </div>
    </div>
    <!-- End Change - Daryll Moya - 02-MAR-2021 -->
</div>
<div class="container-fluid column" style="padding: 0;">
    <div class="col-md-12" style="font-size: 1rem; margin-bottom: 1%; margin: 0; padding: 0; text-align: center;">
        <div class="col-md-12 page-title-main">
            <!-- Start Change - Daryll Moya - 02-MAR-2021 -->
            <!-- <h2>Result</h2> -->
            <h2 style="margin-bottom: 0px;">Result</h2>
            <!-- End Change - Daryll Moya - 02-MAR-2021 -->
        </div>
    </div>
</div>
<div class="container-fluid result-background">
    <c:if test="${empty matchedCcoResults && empty matchedDsStResults}">
        <div class="container">
            <div class="row no-matched-result">
                <div class="no-matched-result-text col-md-5">
                    <i class="far fa-frown fa-5x"></i>
                    <!-- Start Change - Daryll Moya - 16-MAR-2021 -->
                    <!-- <h3>Sorry, no account matched. Please <a href="${ctx}/quiz"style="color: #248700;">re-take the questionnaire</a> or <a href="${ctx}/accounts/cco" style="color: #248700;">see accounts</a>.</h3> -->
                    <!-- <h3>Sorry, no account with openings matched at the moment. <br/><br/>To see the all the accounts and first-level support posts, <br/>please click <a href="${ctx}/accounts/cco" style="color: #248700;">here</a>. <br/><br/>Feel free to also <a href="${ctx}/quiz"style="color: #248700;"> re-take the questionnaire </a> at your own convenience.</h3> -->
                    <h3><br/>
                    	Sorry, we have not found job opportunities that match your preferences at the moment.
                    	<br/><br/>
                    	Feel free to <a href="${ctx}/quiz"style="color: #248700;"> retake the questionnaire </a> soon.
                    	<br/><br/>
                    	To explore other options, click <a href="${ctx}/accounts/cco" style="color: #248700;">here</a>.
                    </h3>
                    <!-- End Change - Daryll Moya - 16-MAR-2021 -->
                </div>
                <div class="no-matched-result-image col-md-7">
                    <!-- Start Change - Daryll Moya - 16-MAR-2021 -->
                    <!-- <img alt="" src="${ctx}/img/index/think-panda.png"></img> -->
                    <img alt="" src="${ctx}/img/index/Lion_Cub_Lying_Down_R_Sleeping_Sh_LR_RGB.png" class="img-center"></img>
                    <!-- End Change - Daryll Moya - 16-MAR-2021 -->
                </div>
            </div>
        </div>
    </c:if>
    <c:if test="${not empty matchedCcoResults || not empty matchedDsStResults}">
        <input id="pageLength" name="pageLength" type="hidden" value="${totalPage}" />
        <div class="container result-container col-md-12">
            <div class="result-carousel row">
                <c:if test="${not empty matchedCcoResults}">
                    <c:forEach items="${matchedCcoResults}" var="job" varStatus="loop">
                        <!-- Start Add - Daryll Moya - 23-FEB-2021 -->
                        <c:if test="${not empty job.JOB_REQ}">
                            <div class="card result-card" id="${job.ACCOUNT_ID}">
                                <div class="card-body text-dark result-body" id="descriptionDiv">
                                    <section class="row result-top-content">
                                        <section class="result-carousel-image col-md-3">
                                            <img class="card-img-top" src="${job.ACCOUNT_IMAGE_PATH}" alt="card image"></img>
                                        </section>
                                        <section class="result-carousel-title col-md-9 row">
                                            <section class="col-md-9">
                                                <h2>
                                                    <strong>${job.ACCOUNT_NAME}</strong>
                                                </h2>
                                            </section>
                                            <section class="col-md-3" style="text-align: right; padding: 0;">
                                                <small>Page ${loop.count} out of ${totalPage}</small>
                                                <c:set var="lastLoop" value="${loop.count}" />
                                            </section>
                                            <!-- 1st row -->
                                            <c:if test="${not empty job.LOCATION}">
                                                <div class="col-md-6">
                                                    <table class="table">
                                                        <tr>
                                                            <td style="width: 3vw; padding-top: 3px;" title="Location">
                                                                <i class="fas fa-map-marker-alt"></i>
                                                            </td>
                                                            <td style="padding-top: 0; padding-bottom: 0;">${job.LOCATION}</td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </c:if>
                                            <c:if test="${empty job.LOCATION}">
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
                                            <c:if test="${not empty job.BPO_EXP}">
                                                <div class="col-md-6">
                                                    <table class="table">
                                                        <tr>
                                                            <td style="width: 3vw; padding-top: 3px;" title="Experience">
                                                                <small>
                                                                    <strong>Exp</strong>
                                                                </small>
                                                            </td>
                                                            <td style="padding-top: 0; padding-bottom: 0;">${job.BPO_EXP}</td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </c:if>
                                            <c:if test="${empty job.BPO_EXP}">
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
                                            <c:if test="${not empty job.EDUCATION}">
                                                <div class="col-md-6">
                                                    <table class="table">
                                                        <tr>
                                                            <td style="width: 3vw; padding-top: 3px;" title="Education">
                                                                <span>
                                                                    <i class="fas fa-book"></i>
                                                                </span>
                                                            </td>
                                                            <td style="padding-top: 0; padding-bottom: 0;">${job.EDUCATION}</td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </c:if>
                                            <c:if test="${empty job.EDUCATION}">
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
                                            <c:if test="${not empty job.OPERATING_HOURS}">
                                                <div class="col-md-6">
                                                    <table class="table">
                                                        <tr>
                                                            <td style="width: 3vw; padding-top: 3px;" title="Operating Hours">
                                                                <span>
                                                                    <i class="far fa-clock"></i>
                                                                </span>
                                                            </td>
                                                            <td style="padding-top: 0; padding-bottom: 0;">${job.OPERATING_HOURS}</td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </c:if>
                                            <c:if test="${empty job.OPERATING_HOURS}">
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
                                            <c:if test="${not empty job.LOB}">
                                                <div class="col-md-6">
                                                    <table class="table">
                                                        <tr>
                                                            <td style="width: 3vw; padding-top: 3px;" title="LOB">
                                                                <i class="fas fa-user-tie"></i>
                                                            </td>
                                                            <td style="padding-top: 0; padding-bottom: 0;">${job.LOB}</td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </c:if>
                                            <c:if test="${empty job.LOB}">
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
                                            <c:if test="${not empty job.BUSINESS_VERTICAL}">
                                                <div class="col-md-6">
                                                    <table class="table">
                                                        <tr>
                                                            <td style="width: 3vw; padding-top: 3px;" title="Business Vertical">
                                                                <i class="fas fa-briefcase"></i>
                                                            </td>
                                                            <td style="padding-top: 0; padding-bottom: 0;">${job.BUSINESS_VERTICAL}</td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </c:if>
                                            <c:if test="${empty job.BUSINESS_VERTICAL}">
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
                                        </section>
                                    </section>
                                    <hr/>
                                    <span class="result-sub-desc">
                                        <strong>Description</strong>
                                    </span>
                                    <c:if test="${not empty job.DESCRIPTION}">
                                        <c:if test="${fn:contains(job.DESCRIPTION, '~') }">
                                            <c:set var="descList" value="${fn:split(job.DESCRIPTION, '~')}" />
                                            <ul class="card-text result-sub-desc-text">
                                                <c:forEach items="${descList}" var="desc">
                                                    <li>${desc}</li>
                                                </c:forEach>
                                            </ul>
                                        </c:if>
                                        <c:if test="${not fn:contains(job.DESCRIPTION, '~') }">
                                            <c:set var="firstChar" value="${fn:substring(job.DESCRIPTION, 0, 1)}" />
                                            <c:if test="${Character.isLetter(firstChar)}">
                                                <p class="card-text result-sub-desc-text">${job.DESCRIPTION}</p>
                                            </c:if>
                                            <c:if test="${not Character.isLetter(firstChar)}">
                                                <c:set var="descList" value="${fn:split(job.DESCRIPTION, firstChar)}" />
                                                <ul class="card-text result-sub-desc-text">
                                                    <c:forEach items="${descList}" var="desc">
                                                        <li>${desc}</li>
                                                    </c:forEach>
                                                </ul>
                                            </c:if>
                                        </c:if>
                                    </c:if>
                                    <c:if test="${empty job.DESCRIPTION}">
                                        <ul class="card-text result-sub-desc-text">
                                            <p>N/A</p>
                                        </ul>
                                    </c:if>
                                    <div id="moreDesc-${loop.count}" style="display: none; height: auto; margin: 1rem auto auto auto;">
                                        <span class="result-sub-desc">
                                            <strong>Team member tasks</strong>
                                        </span>
                                        <c:if test="${not empty job.TM_TASK}">
                                            <c:if test="${fn:contains(job.TM_TASK, '~') }">
                                                <c:set var="tmTaskList" value="${fn:split(job.TM_TASK, '~')}" />
                                                <ul class="card-text result-sub-desc-text">
                                                    <c:forEach items="${tmTaskList}" var="tmTask">
                                                        <li>${tmTask}</li>
                                                    </c:forEach>
                                                </ul>
                                            </c:if>
                                            <c:if test="${not fn:contains(job.TM_TASK, '~') }">
                                                <c:set var="firstChar" value="${fn:substring(job.TM_TASK, 0, 1)}" />
                                                <c:if test="${Character.isLetter(firstChar)}">
                                                    <p class="card-text result-sub-desc-text">${job.TM_TASK}</p>
                                                </c:if>
                                                <c:if test="${not Character.isLetter(firstChar)}">
                                                    <c:set var="tmTaskList" value="${fn:split(job.TM_TASK, firstChar)}" />
                                                    <ul class="card-text result-sub-desc-text">
                                                        <c:forEach items="${tmTaskList}" var="tmTask">
                                                            <li>${tmTask}</li>
                                                        </c:forEach>
                                                    </ul>
                                                </c:if>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${empty job.TM_TASK}">
                                            <ul class="card-text result-sub-desc-text">
                                                <p>N/A</p>
                                            </ul>
                                        </c:if>
                                        <span class="result-sub-desc">
                                            <strong>Skills needed</strong>
                                        </span>
                                        <c:if test="${not empty job.SKILLS}">
                                            <c:if test="${fn:contains(job.SKILLS, '~') }">
                                                <c:set var="skillList" value="${fn:split(job.SKILLS, '~')}" />
                                                <ul class="card-text result-sub-desc-text">
                                                    <c:forEach items="${skillList}" var="skill">
                                                        <li>${skill}</li>
                                                    </c:forEach>
                                                </ul>
                                            </c:if>
                                            <c:if test="${not fn:contains(job.SKILLS, '~') }">
                                                <c:set var="firstChar" value="${fn:substring(job.SKILLS, 0, 1)}" />
                                                <c:if test="${Character.isLetter(firstChar)}">
                                                    <p class="card-text result-sub-desc-text">${job.SKILLS}</p>
                                                </c:if>
                                                <c:if test="${not Character.isLetter(firstChar)}">
                                                    <c:set var="skillList" value="${fn:split(job.SKILLS, firstChar)}" />
                                                    <ul class="card-text result-sub-desc-text">
                                                        <c:forEach items="${skillList}" var="skill">
                                                            <li>${skill}</li>
                                                        </c:forEach>
                                                    </ul>
                                                </c:if>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${empty job.SKILLS}">
                                            <ul class="card-text result-sub-desc-text">
                                                <p>N/A</p>
                                            </ul>
                                        </c:if>
                                        <span class="result-sub-desc">
                                            <strong>Trainings</strong>
                                        </span>
                                        <c:if test="${not empty job.TRAININGS}">
                                            <c:if test="${fn:contains(job.TRAININGS, '~') }">
                                                <c:set var="trainingList" value="${fn:split(job.TRAININGS, '~')}" />
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
                                            <c:if test="${not fn:contains(job.TRAININGS, '~') }">
                                                <c:set var="firstChar" value="${fn:substring(job.TRAININGS, 0, 1)}" />
                                                <c:if test="${Character.isLetter(firstChar)}">
                                                    <c:if test="${fn:contains(job.TRAININGS, ',') }">
                                                        <c:set var="trainingList" value="${fn:split(job.TRAININGS, ',')}" />
                                                        <p class="card-text result-sub-desc-text" style="margin-bottom: 0px">Visit MyGrowth to find the courses below:</p>
                                                        <ul class="card-text result-sub-desc-text" style="margin-top: 0px">
                                                            <c:forEach items="${trainingList}" var="training">
                                                                <li>${training}</li>
                                                            </c:forEach>
                                                        </ul>
                                                    </c:if>
                                                    <c:if test="${not fn:contains(job.TRAININGS, ',') }">
                                                        <p class="card-text result-sub-desc-text">${job.TRAININGS}</p>
                                                    </c:if>
                                                </c:if>
                                                <c:if test="${not Character.isLetter(firstChar)}">
                                                    <c:set var="trainingList" value="${fn:split(job.SKILLS, TRAININGS)}" />
                                                    <p class="card-text result-sub-desc-text" style="margin-bottom: 0px">Visit MyGrowth to find the courses below:</p>
                                                    <ul class="card-text result-sub-desc-text" style="margin-top: 0px">
                                                        <c:forEach items="${trainingList}" var="training">
                                                            <li>${training}</li>
                                                        </c:forEach>
                                                    </ul>
                                                </c:if>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${empty job.TRAININGS}">
                                            <ul class="card-text result-sub-desc-text">
                                                <p>N/A</p>
                                            </ul>
                                        </c:if>
                                        <c:if test="${not empty job.JOB_REQ}">
                                            <span class="result-sub-desc">
                                                <strong>Job Requisition</strong>
                                                <p class="card-text result-sub-desc-text" style="margin-bottom: 0.1rem;">You may click on any of the links below to learn more about the vacancies.</p>
                                                <!-- <p class="card-text result-sub-desc-text" style="color: red !important; margin-bottom: 0.1rem;">Kindly copy the Job Requisition below and paste it on the search bar on Workday.</p>
                                                <p class="card-text result-sub-desc-text" style="margin-top: 0.1rem;">Visit Workday by clicking
                                                    <a id="googleforms" href="javascript:window.open('https://wd3.myworkday.com/telusinternational/d/task/1422$3375.htmld', '_blank');">here</a> and search the job requisition below.
                                                </p> -->
                                            </span>
                                            <c:set var="jobReqList" value="${fn:split(job.JOB_REQ, ', ' )}" />
                                            <c:set var="jobReqUrl" value="${fn:split(job.INTERNAL_POSTING_URL, ', ' )}" />
                                            <ul>
                                                <c:forEach items="${jobReqList}" var="jobReq" varStatus="status">
                                                    <li><a id="googleforms" href="javascript:window.open('${jobReqUrl[status.index]}', '_blank');">${jobReq}</a></li>
                                                </c:forEach>
                                            </ul>
                                            <!-- <ul>
                                                <c:forEach items="${jobReqList}" var="jobReq">
                                                    <li>${jobReq}</li>
                                                </c:forEach>
                                            </ul> -->
                                        </c:if>
                                        <c:if test="${empty job.JOB_REQ}">
                                            <span class="result-sub-desc">
                                                <strong>Job Requisition</strong>
                                                <p class="card-text result-sub-desc-text">Sorry, no related open job requisition as of the moment.</p>
                                            </span>
                                        </c:if>
                                    </div>
                                    <div class="col-md-12" style="display: inline-block; padding: 0.7rem 1rem 0.7rem 0;">
                                        <a onclick="learnMore('${loop.count}')" id="learnMoreBtn-${loop.count}" style="float: left;">See More</a>
                                        <a onclick="undoLearnMore('${loop.count}')" id="undoBtn-${loop.count}" style="display: none;">Show Less</a>
                                        <!-- <a onclick="print();" style="font-size: 1.2rem; float: right;"><i class="fas fa-print"></i></a> -->
                                    </div>
                                </div>
                                <div class="card-footer text-muted" style="display: flex;">
                                    <div class="col-md-9" style="font-size: 0.8rem;">
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
                                                    <c:if test="${not empty job.JOB_REQ}">
                                                        <a id="workdaylink" href="${job.JOB_REQ}">To see all available openings, click here.</a>
                                                    </c:if>
                                                    <c:if test="${empty job.JOB_REQ}">
                                                        <a id="workdaylink" href="https://wd3.myworkday.com/telusinternational/d/inst/13102!CK5mGhIKBggDEMenAhIICgYI1A0QrwI~/cacheable-task/2997$2151.htmld">Job requisition is currently not available. Click this link to proceed finding other opportunities.</a>
                                                    </c:if>
                                                    -->
                                                </ul>
                                            </small>
                                            <a id="googleforms" href="https://docs.google.com/forms/d/1y5cVLvfEj-fbYDhno1qQeO7n11kcdqKKw_uiJaQ8B50/edit?ts=5b898cb4">Click here and tell us what you think.</a>
                                            <br/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                    </c:forEach>
                </c:if>
                <c:if test="${not empty matchedDsStResults}">
                    <c:forEach items="${matchedDsStResults}" var="job" varStatus="loop">
                        <!-- Start Add - Daryll Moya - 23-FEB-2021 -->
                        <c:if test="${not empty job.JOB_REQ}">
                            <div class="card result-card" id="${job.POSITION_ID}">
                                <div class="card-body text-dark result-body" id="descriptionDiv">
                                    <section class="row result-top-content">
                                        <section class="result-carousel-image col-md-3">
                                            <img class="card-img-top" src="${job.IMAGE_PATH}" alt="card image"></img>
                                        </section>
                                        <section class="result-carousel-title col-md-9 row">
                                            <section class="col-md-9">
                                                <h2>
                                                    <strong>${job.JOB_PROFILE}</strong>
                                                </h2>
                                            </section>
                                            <section class="col-md-3" style="text-align: right; padding: 0;">
                                                <small>Page ${lastLoop + loop.count} out of ${totalPage}</small>
                                            </section>
                                            <!-- 1st row -->
                                            <c:if test="${not empty job.EDUCATIONAL_BACKGROUND}">
                                                <div class="col-md-6">
                                                    <table class="table">
                                                        <tr>
                                                            <td style="width: 3vw; padding-top: 3px;" title="Education">
                                                                <span>
                                                                    <i class="fas fa-book"></i>
                                                                </span>
                                                            </td>
                                                            <td style="padding-top: 0; padding-bottom: 0;">${job.EDUCATIONAL_BACKGROUND}</td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </c:if>
                                            <c:if test="${empty job.EDUCATIONAL_BACKGROUND}">
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
                                            <c:if test="${not empty job.EXPERIENCE_REQUIRED}">
                                                <div class="col-md-6">
                                                    <table class="table">
                                                        <tr>
                                                            <td style="width: 3vw; padding-top: 3px;" title="Experience">
                                                                <small>
                                                                    <strong>Exp</strong>
                                                                </small>
                                                            </td>
                                                            <td style="padding-top: 0; padding-bottom: 0;">${job.EXPERIENCE_REQUIRED}</td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </c:if>
                                            <c:if test="${empty job.EXPERIENCE_REQUIRED}">
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
                                            <c:if test="${not empty job.SUPPORT_TYPE}">
                                                <div class="col-md-6">
                                                    <table class="table">
                                                        <tr>
                                                            <td style="width: 3vw; padding-top: 3px;" title="Type of Support">
                                                                <i class="fas fa-user-tie"></i>
                                                            </td>
                                                            <td style="padding-top: 0; padding-bottom: 0;">${job.SUPPORT_TYPE}</td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </c:if>
                                            <c:if test="${empty job.SUPPORT_TYPE}">
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
                                            <c:if test="${not empty job.DEPARTMENT}">
                                                <div class="col-md-6">
                                                    <table class="table">
                                                        <tr>
                                                            <td style="width: 3vw; padding-top: 3px;" title="Department">
                                                                <i class="fas fa-map-marker-alt"></i>
                                                            </td>
                                                            <td style="padding-top: 0; padding-bottom: 0;">${job.DEPARTMENT}</td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </c:if>
                                            <c:if test="${empty job.DEPARTMENT}">
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
                                    </section>
                                    <hr/>
                                    <span class="result-sub-desc">
                                        <strong>Description</strong>
                                    </span>
                                    <c:set var="firstChar" value="${fn:substring(job.DESCRIPTION, 0, 1)}" />
                                    <c:if test="${Character.isLetter(firstChar)}">
                                        <p class="card-text result-sub-desc-text" style="margin-bottom: 0;">${job.DESCRIPTION}</p>
                                    </c:if>
                                    <c:if test="${not Character.isLetter(firstChar)}">
                                        <div style="height: auto; margin: 1rem auto auto;">
                                            <ul>
                                                <c:set var="firstChar" value="${fn:substring(job.DESCRIPTION, 0, 1)}" />
                                                <c:set var="descList" value="${fn:split(job.DESCRIPTION, firstChar)}" />
                                                <c:forEach items="${descList}" var="desc">
                                                    <li>${desc}</li>
                                                </c:forEach>
                                            </ul>
                                        </div>
                                    </c:if>
                                    <div id="moreDesc-${lastLoop + loop.count}" style="display: none; height: auto; margin: 1rem auto auto auto;">
                                        <c:if test="${not empty job.SKILLS_REQUIRED }">
                                            <span class="result-sub-desc">
                                                <strong>Skills needed</strong>
                                            </span>
                                            <c:set var="skillList" value="${fn:split(job.SKILLS_REQUIRED, '~' )}" />
                                            <ul class="card-text result-sub-desc-text">
                                                <c:forEach items="${skillList}" var="skill">
                                                    <li>${skill}</li>
                                                </c:forEach>
                                            </ul>
                                        </c:if>
                                        <c:if test="${not empty job.OTHER_SKILLS }">
                                            <span class="result-sub-desc">
                                                <strong>Other skills</strong>
                                            </span>
                                            <c:set var="otherSkillList" value="${fn:split(job.OTHER_SKILLS, '~' )}" />
                                            <ul class="card-text result-sub-desc-text">
                                                <c:forEach items="${otherSkillList}" var="otherSkill">
                                                    <li>${otherSkill}</li>
                                                </c:forEach>
                                            </ul>
                                        </c:if>
                                        <c:if test="${not empty job.CERTIFICATION }">
                                            <span class="result-sub-desc">
                                                <strong>Certification</strong>
                                            </span>
                                            <c:set var="certiList" value="${fn:split(job.CERTIFICATION, '~' )}" />
                                            <ul class="card-text result-sub-desc-text">
                                                <c:forEach items="${certiList}" var="certificate">
                                                    <li>${certificate}</li>
                                                </c:forEach>
                                            </ul>
                                        </c:if>
                                        <c:if test="${not empty job.TRAININGS }">
                                            <span class="result-sub-desc">
                                                <strong>Trainings</strong>
                                                <p class="card-text result-sub-desc-text">Visit MyGrowth to find the courses below</p>
                                            </span>
                                            <c:set var="trainingList" value="${fn:split(job.TRAININGS, '~' )}" />
                                            <ul>
                                                <c:forEach items="${trainingList}" var="training">
                                                    <!-- Start Change - Daryll Moya - 23-FEB-2021 -->
                                                    <!-- <li>${training}</li> -->
                                                    <li><a id="googleforms" href="javascript:window.open('https://mygrowthlms.bridgeapp.com/learner/library?search=${training}', '_blank');">${training}</a></li>
                                                    <!-- End Change - Daryll Moya - 23-FEB-2021 -->
                                                </c:forEach>
                                            </ul>
                                        </c:if>
                                        <c:if test="${not empty job.JOB_REQ}">
                                            <span class="result-sub-desc">
                                                <strong>Job Requisition</strong>
                                                <p class="card-text result-sub-desc-text" style="margin-bottom: 0.1rem;">You may click on any of the links below to learn more about the vacancies.</p>
                                                <!-- <p class="card-text result-sub-desc-text" style="color: red !important; margin-bottom: 0.1rem;">Kindly copy the Job Requisition below and paste it on the search bar on Workday.</p>
                                                <p class="card-text result-sub-desc-text" style="margin-top: 0.1rem;">Visit Workday by clicking
                                                    <a id="googleforms" href="javascript:window.open('https://wd3.myworkday.com/telusinternational/d/task/1422$3375.htmld', '_blank');">here</a> and search the job requisition below.
                                                </p> -->
                                            </span>
                                            <c:set var="jobReqList" value="${fn:split(job.JOB_REQ, ', ' )}" />
                                            <c:set var="jobReqUrl" value="${fn:split(account.INTERNAL_POSTING_URL, ', ' )}" />
                                            <ul>
                                                <c:forEach items="${jobReqList}" var="jobReq" varStatus="status">
                                                    <li><a id="googleforms" href="javascript:window.open('${jobReqUrl[status.index]}', '_blank');">${jobReq}</a></li>
                                                </c:forEach>
                                            </ul>
                                        </c:if>
                                        <c:if test="${empty job.JOB_REQ}">
                                            <span class="result-sub-desc">
                                                <strong>Job Requisition</strong>
                                                <p class="card-text result-sub-desc-text">Sorry, no related open job requisition as of the moment.</p>
                                            </span>
                                        </c:if>
                                    </div>
                                    <div class="col-md-12" style="display: inline-block; padding: 0.7rem 1rem 0.7rem 0; width: 100%;">
                                        <a onclick="learnMore('${lastLoop + loop.count}')" id="learnMoreBtn-${lastLoop + loop.count}" style="float: left;">See More</a>
                                        <a onclick="undoLearnMore('${lastLoop + loop.count}')" id="undoBtn-${lastLoop + loop.count}" style="display: none;">Show Less</a>
                                        <!-- <a onclick="print();" style="font-size: 1.2rem; float:right;"><i class="fas fa-print"></i></a> -->
                                    </div>
                                </div>
                                <div class="card-footer text-muted" style="display: flex;">
                                    <div class="col-md-9" style="font-size: 0.8rem;">
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
                                                    <c:if test="${not empty job.JOB_REQ}">
                                                        <a id="workdaylink" href="${job.JOB_REQ}">To see all available openings, click here.</a>
                                                    </c:if>
                                                    <c:if test="${empty job.JOB_REQ}">
                                                        <a id="workdaylink" href="https://wd3.myworkday.com/telusinternational/d/inst/13102!CK5mGhIKBggDEMenAhIICgYI1A0QrwI~/cacheable-task/2997$2151.htmld">Job requisition is currently not available. Click this link to proceed finding other opportunities.</a>
                                                    </c:if>
                                                    -->
                                                </ul>
                                            </small>
                                            <a id="googleforms" href="https://docs.google.com/forms/d/1y5cVLvfEj-fbYDhno1qQeO7n11kcdqKKw_uiJaQ8B50/edit?ts=5b898cb4">Click here and tell us what you think.</a>
                                            <br/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                    </c:forEach>
                </c:if>
            </div>
        </div>
    </c:if>
</div>
<script src="js/result.js" type="text/javascript"></script>
<hr id="afterAbout"/>
<jsp:include page="/WEB-INF/jsp/common/footer.jsp" />