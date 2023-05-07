<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/jsp/common/header.jsp" />
<!-- Navigation Bar -->
<!-- 
<div class="container-fluid">
    <div class="row">
        <div id="navigation" class="col-md-12">
            <nav class="navbar navbar-expand-lg d-flex justify-content-center">
                <div class="navbar-brand">
                    <img alt="" src="img/accounts/tip-logo.png">
                </div> 
            </nav>
        </div>
    </div>
</div>
-->
<button class="btn btn-success" onclick="backToTop()" id="backToTopBtn">
    <i class="fa fa-arrow-up"></i>
</button>
<section class="section1 container-fluid">
    <div class="row" style="display: block">
        <div>
            <div style="display: flex; justify-content: flex-end">
                <div class="dropdown-main btn-group" style="display: flex; flex-direction: column;">
                    <button style="box-sizing: none; border: none;; outline: none; background-color: transparent; cursor: pointer;" class="dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <span id="user" style="color: #4B286D; font-weight: bold;"></span>
                    </button>
                    <div class="dropdown-menu dropdown-menu-signout" style="min-width: 3rem; border: none; margin: 0; padding: 0;">
                        <button class="signoutBtn btn btn-sm" style="background-color: #66CC00; min-width: 3rem; color: white; width: 100" onclick="logout()">
                            <i class="fas fa-sign-out-alt" style="margin-right: 6px"></i>Sign Out
                        </button>
                    </div>
                </div>
                <script type="text/javascript">
                /* Start - OneLogin Config */
                $.get(ctx + "/careercity-api/user", function(data) {
                    $("#user").html(data.userAuthentication.details.name);
                    $("#username").val(data.userAuthentication.details.preferred_username);
                    $(".authenticated").show()
                });
                var logout = function() {
                    $.post(ctx + "/logout", function() {
                        $("#user").html('');
                        $(".authenticated").hide();
                        window.location.href = ctx + "/signout";
                    })
                    return true;
                }
                $.ajaxSetup({
                    beforeSend : function(xhr, settings) {
                        if (settings.type == 'POST' || settings.type == 'PUT'|| settings.type == 'DELETE') {
                            if (!(/^http:.*/.test(settings.url) || /^https:.*/.test(settings.url))) {
                                xhr.setRequestHeader("X-XSRF-TOKEN", Cookies.get('XSRF-TOKEN'));
                            }
                        }
                    }
                });
                /* End - OneLogin Config */
                </script>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="section1-navigation col-md-4 align-self-center">
            <img class="d-flex justify-content-center" alt="" src="img/index/telus-logo.png" style="width: 25vw; margin: 3.5rem 0;">
            <div>
                <div class="container authenticated" style="display: none"></div>
            </div>
            <button class="btn btn-lg section1-btn1" type="button" onclick="focusOnQuiz()">
                <i class="fas fa-pencil-alt"></i> Let us guide you
            </button>
            <button class="btn btn-lg section1-btn2" type="button" onclick="focusOnAccounts()">
                <i class="far fa-lightbulb"></i> Explore opportunities
            </button>
            <!-- Start Add - Daryll Moya - 23-FEB-2021 -->
            <button class="btn btn-lg section1-btn3" type="button" onclick="focusOnAbout()">
                <i class="far fa-question-circle"></i> About iCan
            </button>
            <!-- End Add - Daryll Moya - 23-FEB-2021 -->
        </div>
        <div class="section1-image col-md-8 d-flex justify-content-center">
            <img style="top:5%; width:100vh; position: absolute; object-fit:cover;"src="img/index/Lion_Logo.png" />
        </div>
    </div>
</section>
<hr id="beforeAbout">
<section class="section3">
    <div class="section3opaque" id="section3opaque"></div>
    <div class="section3content">
        <div class="question-title">Are you ready to launch?</div>
        <hr style="width: 50%; background-color: #fff;">
        <br>
        <button type="button" class="section3button" id="button1">
            <a href="quiz" target="_blank">Yes, please guide me through. &#187;</a>
        </button>
    </div>
</section>
<hr id="afterAbout">
<section class="section2" id="section2">
    <section class="asection2">
        <div class="asection2content">
            <a href="${ctx}/accounts/cco"><div class="caption" id="pic1">CC Operations</div></a>
            <div class="asection2-images" id="images1">
                <img class="pics" id="picA" src="img/index/cco2.jpg" />
            </div>
            <a href="${ctx}/accounts/digitalsolutions"><div class="caption" id="pic2">Digital Solutions</div></a>
            <div class="asection2-images" id="images2">
                <img class="pics" id="picB" src="img/index/ds6.jpg" />
            </div>
            <a href="${ctx}/accounts/support"><div class="caption" id="pic3">Support functions</div></a>
            <div class="asection2-images" id="images3">
                <img class="pics" id="picC" src="img/index/support3.jpg" />
            </div>
        </div>
    </section>
    <section class="bsection2">
        <div class="bsection2opaque"></div>
        <div class="bsection2content">
            <div class="account-title" id="account-title1">Click the pillars and discover all the possibilities</div>
            <p id="description">Search for roles that fit your interests and skills - your next career move is inside!</p>
        </div>
    </section>
</section>
<hr id="beforeAbout">
<section class="section" id="parent">
    <!-- <img src="img/index/panda.png" style="height:350px; margin-bottom:10px;" /> -->
    <div class="panda" id="scroll-panda">
        <img src="img/quiz/TELUS Critters/Lion_Cub_Facing_Front_LyingDown_L_Sh_LR_RGB.png" style="width: 100%;" />
    </div>
    <!-- Start Add - Daryll Moya - 23-FEB-2021 -->
    <div id="aboutsectioncontainer" style="height: 75%;">
        <div class="about-title" id="aboutsection" data-toggle="modal" data-target="#digitalSolutionModal">About</div>
        <p style="text-align: center; width: 100%;">Welcome to iCan!</p>
        <p>This online resource is created for you to navigate job opportunities within TELUS International Philippines.</p>
        <br/>
        <p>iCan empowers you to take charge and plan your career in the organization. We understand how important your interests and preferences are to your career decisions. That's why we designed this tool for you to easily explore the many roles in Operations, Digital Solutions, and Support.</p>
        <br/>
        <p>Enjoy the search and bring it up to your supervisor in your next coaching session.</p>
        <!-- Start Delete - Daryll Moya - 16-MAR-2021 -->
        <!-- <a id="googleforms" href="https://docs.google.com/forms/d/1y5cVLvfEj-fbYDhno1qQeO7n11kcdqKKw_uiJaQ8B50/edit?ts=5b898cb4">Click here and tell us what you think.</a> -->
        <!-- End Delete - Daryll Moya - 16-MAR-2021 -->
    </div>
    <!-- End Add - Daryll Moya - 23-FEB-2021 -->
</section>
<hr id="afterAbout">
<script src="js/index.js" type="text/javascript"></script>
<jsp:include page="/WEB-INF/jsp/common/footer.jsp" />