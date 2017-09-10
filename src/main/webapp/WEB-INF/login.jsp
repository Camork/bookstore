<%--
  Created by IntelliJ IDEA.
  User: Camork
  Date: 2017-05-17
  Time: 10:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>登录书城</title>
    <%@include file="common/headSources.jsp" %>
</head>
<body style="background-color: #2f3e9e">
<div id="loginModal" style="height: 100%;top: 10%">

    <div class="modal-content">

        <div class="panel">
            <h3>登录</h3>
            <div class="formset">

                <div class="input-field col s12">
                    <i class="material-icons prefix">account_circle</i>
                    <input id="username" type="text">
                    <label for="username">用户名</label>
                </div>
                <div class="input-field col s12">
                    <i class="material-icons prefix">lock_outline</i>
                    <input id="password" type="password">
                    <label for="password">密码</label>
                </div>

                <div id="remember">
                    <input type="checkbox" class="filled-in" id="filled-box" name="rememberMe"/>
                    <label for="filled-box">记住我</label>
                </div>
                <button id="login" class="btn waves-effect waves-light pink" onclick="login()">确定</button>
            </div>
            <div class="register-form">
                <i id="close" class="material-icons close">add</i>
                <h3>注册</h3>
                <div class="formset">
                    <div class="input-field col s12 ">
                        <input id="reg_username" type="text"/>
                        <label for="reg_username">用户名</label>
                    </div>
                    <div class="input-field col s12 ">
                        <input id="reg_pass" type="password"/>
                        <label for="reg_pass">密码</label>
                    </div>
                    <div class="input-field col s12">
                        <input id="pass_again" type="password"/>
                        <label for="pass_again">再次输入密码</label>
                    </div>
                    <button id="reg" class="btn waves-effect waves-light" onclick="reg()">OK</button>
                </div>
            </div>

        </div>
    </div>
</div>
</body>
</html>

<script>
    $(function () {
        Materialize.toast('管理员账号:admin,密码:123', 4000);
    })
</script>