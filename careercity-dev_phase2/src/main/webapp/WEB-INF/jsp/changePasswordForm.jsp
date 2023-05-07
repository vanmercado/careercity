<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<jsp:include page="/WEB-INF/jsp/common/header.jsp" />
<!-- Custom Style  -->
<link rel="stylesheet" href="${ctx}/css/login.css">
<section class="container-fluid">
    <div class="row">
        <div class="col-md-7 text-center " style="min-height: 100vh;">
            <div class="row">
                <div class="col-md-5">
                    <div class="imgbox">
                        <img class="center-fit" src="${ctx}/img/login/Bamboo5.jpg" style="height: 100vh;">
                    </div>
                </div>
                <div class="col-md-7 ">
                    <h1 style="margin: 10vh 0; font-size: 3rem; text-transform: normal;">Change your initial password.</h1>
                    <img class="center-fit" src="${ctx}/img/login/panda-bamboo.png" style="position: absolute; bottom: 0; left: 0;">
                </div>
            </div>
        </div>
        <div class="col-md-5" style="background: #4B286D; width: 100%; height: 100%; color: white; min-height: 100vh; padding: 4rem">
            <div class="text-center">
                <img alt="cc-logo" src="${ctx}/img/index/cc-logo.png" width="40%;" style="border-radius: 100%; margin-bottom: 1rem;">
                <h3 style="text-transform: capitalize;">You can change your password on your initial login.</h3>
            </div>
            <hr class="text-white">
            <div class="mt-5">
                <form:form action="${ctx}/initChangePassword" method="POST">
                    <div class="form-group">
                        <label>New Password:</label>
                        <input class="form-control" type="password" name="newPassword" required>
                    </div>
                    <div class="form-group">
                        <label>Confirm Password:</label>
                        <input class="form-control" type="password" name="confirmPassword" required>
                    </div>
                    <div class="form-group text-center">
                        <button class="btn btn-success form-control" type="submit">Submit</button>
                        <%--
                        <hr style="color:white;">
                        <a style="color:white;" href="${ctx}/">Proceed, keep my default password.</a>
                        --%>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</section>
<!------- LIBRARIES --------->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<!-- Bootstrap 4.1.1 JS -->
<script src="${ctx}/lib/bootstrap-4.1.1-dist/js/bootstrap.min.js"></script>
<!-- Fliterizr -->
<script src="${ctx}/lib/Filterizr-master/dist/jquery.filterizr.min.js" type="text/javascript"></script>
<!-- Slick -->
<script src="${ctx}/lib/slick/slick.min.js" type="text/javascript"></script>
<!-- Custom JS -->
<script src="${ctx}/js/custom.js" type="text/javascript"></script>
</body>
</html>