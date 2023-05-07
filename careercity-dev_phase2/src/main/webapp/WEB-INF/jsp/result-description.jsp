<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/jsp/common/header.jsp" />
<!-- Navigation Bar -->
<div class="container-fluid">
    <div class="row">
        <div id="navigation" class="col-md-12 ">
            <nav class="navbar navbar-expand-lg d-flex justify-content-between">
                <div class="pd-2">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link" href="${ctx}">
                                <i class="fas fa-arrow-circle-left fa-2x"></i>
                            </a>
                        </li>
                    </ul>
                </div>
                <div class="ti-logo navbar-header">
                    <img class="navbar-brand" alt="" src="${ctx}/img/accounts/tip-logo.png">
                </div>
                <div class="pd-2"></div>
            </nav>
        </div>
    </div>
</div>
<div class="result-description-container container" style="padding: 5% 0;">
    <div class="row">
        <h1>${account.ACCOUNT_NAME}</h1>
        <br>
        <h1>${account.LOCATION}</h1>
    </div>
</div>
<jsp:include page="/WEB-INF/jsp/common/footer.jsp" />