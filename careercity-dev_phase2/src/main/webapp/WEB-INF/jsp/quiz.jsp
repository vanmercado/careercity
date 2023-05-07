<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<jsp:include page="/WEB-INF/jsp/common/header.jsp" />
<link rel="stylesheet" href="css/quizStyle.css">
<!-- Navigation Bar -->
<div class="container-fluid">
    <!-- Start Change - Daryll Moya - 03-MAR-2021 -->
    <!-- <div class="row" style="height: 3rem;"> -->
    <div class="row" style="height: 4rem;">
        <div class="col-4" style="float: left;">
            <a class="nav-link home-link far" href="/">
                <!-- <span style="font-family: 'TELUS-Web', 'Helvetica Neue', Helvetica, Arial, sans-serif !important;">Home</span> -->
                <span style="font-family: 'TELUS-Web', 'Helvetica Neue', Helvetica, Arial, sans-serif !important; padding-left: 15px;">Home</span>
            </a>
        </div>
        <div class="col-4" style="text-align: center;">
            <!-- <img class="navbar-brand" alt="" src="${ctx}/img/index/telus-logo.png" style="margin-top: -6%; height: 80%; margin: 0.5rem 0;"> -->
            <img class="navbar-brand" alt="" src="${ctx}/img/index/telus-logo.png" style="height: 80%; margin: 0.4rem 0;"></img>
        </div>
    </div>
    <!-- End Change - Daryll Moya - 03-MAR-2021 -->
</div>
<!-- <div class="quiz-container" style="min-width: 970px; min-height: 500px;">  -->
<div class="quiz-container">
    <div class="page-title-main">
        <!-- Start Change - Daryll Moya - 01-MAR-2021 -->
        <!-- <h2 style="color: #fff;">Questionnaire</h2> -->
        <h2 style="color: #fff; margin-bottom: 0px">Questionnaire</h2>
        <!-- End Change - Daryll Moya - 01-MAR-2021 -->
    </div>
    <!-- QUIZ SECTION -->
    <div id="quizSection">
        <form:form id="quizForm" method="POST" modelAttribute="answerSheet" action="${ctx}/result">
            <div class="item" id="quizmain" hidden="true"></div>
            <div class="info">
                <h2>Let's get started!</h2>
                <br><br>
                <div class="form-group workday-id-row">
                    <div class="col-md-12">
                        <div class="critter" style="text-align: center;">
                            <img src="img/quiz/TELUS Critters/Lion_Cub_Facing_Front_Sitting_Sh_LR_RGB.png" style="height: 50%; margin-bottom: 30px;" />
                            <input type="hidden" id="quizWorkdayEmail" name="quizWorkdayEmail" value="">
                            <input type="text" id="quizWorkdayId" name="quizWorkdayId" class="form-control" placeholder="Enter your Workday ID" value="" disabled="disabled" />
                            <!-- <span id="quizWorkdayId_helper" class="text-danger" hidden="true">Please enter 8 digit Workday ID</span> -->
                            <br>
                            <input type="text" id="quizName" name="quizName" class="form-control" placeholder="Enter your name" value="" disabled="disabled" />
                            <!-- <span id="quizName_helper" class="text-danger" hidden="true">Please enter name</span> -->
                        </div>
                    </div>
                    <div class="divBtns" id="startBtn">
                        <button type="button" id="quizmainbtn" class="nextBtn btn">Start</button>
                    </div>
                </div>
            </div>
            <!-- QUESTION 1 -->
            <div class="item d-flex justify-content-center">
                <h4 class="itemtitle">Location</h4>
            </div>
            <div class="info" id="info1">
                <h2>Where would you like to work?</h2>
                <div class="dropdown-divider"></div>
                <div class="content" id="question1" name="required1" required>
                    <p>Preferred site location (maximum of 2) <span class="required">*</span></p>
                    <c:forEach items="${ question1 }" var="item">
                        <div class="form-check q1">
                            <label class="form-check-label">
                            <form:checkbox class="form-check-input" name="q1" path="q1" value="${ item.ANSWER_ID }" />${ item.ANSWER }
                            <span class="checkmark"></span>
                            </label>
                        </div>
                    </c:forEach>
                </div>
                <div class="divBtnsCont">
                    <div class="divBtns">
                        <button type="button" class="backBtn btn" style="position: relative; float: left">Back</button>
                        <!--<button type="button" class="nextBtn btn" style="position: relative; float: right" id="question1-next" disabled>Next</button>-->
                        <p style="position: absolute; left: 75%;"><span class="required">*</span> Required Field</p>
                        <button type="button" class="nextBtn btn" style="position: relative; float: right" id="question1-next">Next</button>
                    </div>
                </div>
            </div>
            <!-- QUESTION 2, 3 & 4 -->
            <div class="item d-flex justify-content-center">
                <h4 class="itemtitle">Work</h4>
            </div>
            <div class="info" id="info2">
                <h2>What are your career interests?</h2>
                <div class="dropdown-divider"></div>
                <div class="content" id="question2">
                    <p>Preferred type of work (you can choose a maximum of 3) <span class="required">*</span></p>
                    <div class="row">
                        <div class="col-md-4" name="required2" required>
                            <p>Call Center Operations</p>
                            <c:forEach items="${ question2 }" var="item">
                                <div class="form-check">
                                    <label class="form-check-label">
                                    <form:checkbox class="form-check-input" name="q2" path="q2" value="${ item.ANSWER_ID }" />${ item.ANSWER }
                                    <span class="checkmark"></span>
                                    </label>
                                </div>
                            </c:forEach>
                        </div>
                        <div class="col-md-4" name="required3" required>
                            <p>Digital Solutions</p>
                            <c:forEach items="${ question3 }" var="item">
                                <div class="form-check">
                                    <label class="form-check-label">
                                    <form:checkbox class="form-check-input" name="q3" path="q3" value="${ item.ANSWER_ID }" />${ item.ANSWER }
                                    <span class="checkmark"></span>
                                    </label>
                                </div>
                            </c:forEach>
                        </div>
                        <div class="col-md-4" name="required4" required>
                            <p>Support functions</p>
                            <c:forEach items="${ question4 }" var="item">
                                <div class="form-check">
                                    <label class="form-check-label">
                                    <form:checkbox class="form-check-input" name="q4" path="q4" value="${ item.ANSWER_ID }" />${ item.ANSWER }
                                    <span class="checkmark"></span>
                                    </label>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
                <div class="divBtnsCont">
                    <div class="divBtns">
                        <button type="button" class="backBtn btn" style="position: relative; float: left">Back</button>
                        <!--<button type="button" class="nextBtn btn" style="position: relative; float: right" id="question2-next" disabled>Next</button>-->
                        <p style="position: absolute; left: 75%;"><span class="required">*</span> Required Field</p>
                        <button type="button" class="nextBtn btn" style="position: relative; float: right" id="question2-next">Next</button>
                    </div>
                </div>
            </div>
            <!--
            <%-- QUESTION 5 --%>
            <div class="item d-flex justify-content-center">
                <h4 class="itemtitle">Schedule</h4>
            </div>
            <div class="info" id="info3">
                <h2>When are you most productive?</h2>
                <div class="dropdown-divider"></div>
                <div class="content" id="question5" name="required5" required>
                    <p>Preferred Work Schedule</p>
                    <c:forEach items="${ question5 }" var="item">
                        <div class="form-check">
                            <%--
                            <label class="form-check-label">
                                <form:checkbox class="form-check-input" name="q5" path="q5" value="${ item.ANSWER_ID }" />&nbsp;${ item.ANSWER }
                                <span class="checkmark"></span>
                            </label>
                            --%>
                            <label class="form-check-label">
                            <form:radiobutton class="form-check-input" name="q5" path="q5" value="${ item.ANSWER_ID }" />${ item.ANSWER }
                            <span class="checkmark-radio"></span>
                            </label>
                        </div>
                    </c:forEach>
                </div>
                <div class="divBtnsCont">
                    <div class="divBtns">
                        <button type="button" class="backBtn btn" style="position: relative; float: left">Back</button>
                        <%--<button type="button" class="nextBtn btn" style="position: relative; float: right" id="question5-next" disabled>Next</button>--%>
                        <button type="button" class="nextBtn btn" style="position: relative; float: right" id="question5-next">Next</button>
                    </div>
                </div>
            </div>
            <%-- QUESTION 6 & 7 --%>
            <div class="item d-flex justify-content-center">
                <h4 class="itemtitle">Education</h4>
            </div>
            <div class="info" id="info4">
                <h2>Educational Background</h2>
                <div class="dropdown-divider"></div>
                <div class="content" id="question6">
                    <div class="row">
                        <div class="col-md-6" id="q6div">
                            <p>Educational Attainment</p>
                            <c:forEach items="${ question6 }" var="item">
                                <div class="form-check">
                                    <label class="form-check-label">
                                    <form:radiobutton class="form-check-input" name="q6" path="q6" value="${ item.ANSWER_ID }" />${ item.ANSWER }
                                    <span class="checkmark-radio"></span>
                                    </label>
                                </div>
                            </c:forEach>
                        </div>
                        <%--<div class="col-md-6" id="q7div" style="display: none;">--%>
                        <div class="col-md-6" id="q7divSub">
                            <p>Field of Study</p>
                            <c:forEach items="${ question7 }" var="item">
                                <div class="form-check">
                                    <label class="form-check-label">
                                    <form:radiobutton class="form-check-input" name="q7" path="q7" value="${ item.ANSWER_ID }" />${ item.ANSWER }
                                    <span class="checkmark-radio"></span>
                                    </label>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
                <div class="divBtnsCont">
                    <div class="divBtns">
                        <button type="button" class="backBtn btn" style="position: relative; float: left">Back</button>
                        <%--<button type="button" class="nextBtn btn" style="position: relative; float: right" id="question6-next" disabled>Next</button>--%>
                        <button type="button" class="nextBtn btn" style="position: relative; float: right" id="question6-next">Next</button>
                    </div>
                </div>
            </div>
            -->
            <!-- QUESTION 8 & 9 -->
            <div class="item d-flex justify-content-center">
                <h4 class="itemtitle">Experience</h4>
            </div>
            <div class="info" id="info5">
                <h2>Tell us about your BPO experience</h2>
                <div class="dropdown-divider"></div>
                <div class="content" id="question8">
                    <div class="row">
                        <div class="col-md-6" id="q8div">
                            <p>BPO Experience (including tenure in TIP) <span class="required">*</span></p>
                            <c:forEach items="${ question8 }" var="item">
                                <div class="form-check">
                                    <label class="form-check-label">
                                    <form:radiobutton class="form-check-input" name="q8" path="q8" value="${ item.ANSWER_ID }" />${ item.ANSWER }
                                    <span class="checkmark-radio"></span>
                                    </label>
                                </div>
                            </c:forEach>
                        </div>
                        <div class="col-md-6" id="q9div">
                            <p>If with BPO experience, choose which type</p>
                            <c:forEach items="${ question9 }" var="item">
                                <div class="form-check">
                                    <label class="form-check-label">
                                    <form:checkbox class="form-check-input" name="q9" path="q9" value="${ item.ANSWER_ID }" />${ item.ANSWER }
                                    <span class="checkmark"></span>
                                    </label>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="divBtnsCont">
                        <div class="divBtns">
                            <button type="button" class="backBtn btn" style="position: relative; float: left">Back</button>
                            <!--<button type="button" class="nextBtn btn" style="position: relative; float: right" id="question8-next" disabled>Next</button>-->
                            <p style="position: absolute; left: 75%;"><span class="required">*</span> Required Field</p>
                            <button type="button" class="nextBtn btn" style="position: relative; float: right" id="question8-next">Next</button>
                        </div>
                    </div>
                </div>
            </div>
            <!-- QUESTION 10, 11 & 12 -->
            <div class="item d-flex justify-content-center">
                <h4 class="itemtitle">Skills</h4>
            </div>
            <div class="info" id="info6">
                <h2>Are you interested in any of these?</h2>
                <div class="dropdown-divider"></div>
                <div class="content" id="question10">
                    <div class="row">
                        <div class="col-md-4">
                            <p>General</p>
                            <c:forEach items="${ question10 }" var="item">
                                <div class="form-check">
                                    <label class="form-check-label">
                                    <form:checkbox class="form-check-input" name="q10" path="q10" value="${ item.ANSWER_ID }" />${ item.ANSWER }
                                    <span class="checkmark"></span>
                                    </label>
                                </div>
                            </c:forEach>
                        </div>
                        <div class="col-md-4">
                            <p>Basic troubleshooting</p>
                            <c:forEach items="${ question11 }" var="item">
                                <div class="form-check">
                                    <label class="form-check-label">
                                    <form:checkbox class="form-check-input" name="q11" path="q11" value="${ item.ANSWER_ID }" />${ item.ANSWER }
                                    <span class="checkmark"></span>
                                    </label>
                                </div>
                            </c:forEach>
                        </div>
                        <div class="col-md-4">
                            <p>Information Technology</p>
                            <c:forEach items="${ question12 }" var="item">
                                <div class="form-check">
                                    <label class="form-check-label">
                                    <form:checkbox class="form-check-input" name="q12" path="q12" value="${ item.ANSWER_ID }" />${ item.ANSWER }
                                    <span class="checkmark"></span>
                                    </label>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <div
                        class="col-md-12 text-muted disclaimer d-flex align-items-end flex-column">
                        <div class="mt-auto p-2">
                            <!-- Start Change - Daryll Moya - 16-MAR-2021 -->
                            <!--
                            <small style="margin-bottom: 0.1rem;">Please be guided that the generated results do not guarantee immediate transfer or promotion.</small><br>
                            <small style="margin-bottom: 0.1rem;">All team members will still go through our internal application process.</small>
                            <p>Upon submission of this questionnaire, you've agreed<br>the terms and condition mentioned above.</p>
                            -->
                            <p style="margin-bottom: 0.1rem; font-weight: bold;">Please be guided that the generated results do not guarantee <br/>immediate transfer or promotion. All team members will still go <br/>through our internal application process.</p>
                            <p style="margin-bottom: 0.1rem; font-size: 15px;">Upon submission of this questionnaire, you've agreed on the terms and condition <br/>mentioned above.</p>
                            <!-- End Change - Daryll Moya - 16-MAR-2021 -->
                        </div>
                        <!--
                        <div class="agreeBox">
                            <div class="form-check" style="padding-left: 0;">
                                <label class="form-check-label"> 
                                    <input type="checkbox" id=agreeCheckbox value="agree">&nbsp;I Agree<BR>
                                    <span class="checkmark"></span>
                                </label>
                            </div>
                        </div>
                        -->
                    </div>
                    <div class="divBtnsCont">
                        <div class="divBtns">
                            <button type="button" class="backBtn btn" style="position: relative; float: left">Back</button>
                            <button type="submit" id="submitBtn" class="nextBtn btn" style="position: relative; float: right">Submit</button>
                        </div>
                    </div>
                </div>
            </div>
        </form:form>
        <h4 class="answers"></h4>
    </div>
</div>
<script src="js/quiz.js" type="text/javascript"></script>
<hr id="afterAbout">
<jsp:include page="/WEB-INF/jsp/common/quiz-modal.jsp" />