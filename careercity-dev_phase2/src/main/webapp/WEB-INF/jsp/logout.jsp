<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/jsp/common/header.jsp" />
<!-- Error Custom Style  -->
<link rel="stylesheet" href="${ctx}/css/errorStyle.css">
<!-- Navigation Bar -->
<div class="container-fluid">
    <div class="row">
        <div id="navigation" class="col-md-12">
            <nav class="navbar navbar-expand-lg d-flex justify-content-center">
                <div class="navbar-brand">
                    <img alt="" src="${ctx}/img/accounts/tip-logo.png" />
                </div>
            </nav>
        </div>
    </div>
</div>
<div class="cont col-md-12 col-sm-12">
    <div class="error-msg">
        <h1>Signed out</h1>
        <div id="msg">You have been signed out.</div>
        <button type="button" class="home-btn">
            <a href="${ctx}/">Sign in</a>
        </button>
    </div>
</div>
<script src="js/error.js" type="text/javascript"></script>
<jsp:include page="/WEB-INF/jsp/common/footer.jsp" />