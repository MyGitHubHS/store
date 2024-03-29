<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 何珊
  Date: 2019/9/11
  Time: 14:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container-fluid">
    <div class="col-md-4">
        <img src="img/logo2.png" />
    </div>
    <div class="col-md-5">
        <img src="img/header.png" />
    </div>
    <div class="col-md-3" style="padding-top:20px">
        <ol class="list-inline">
            <c:if test="${empty sessionScope.user}">
                <li><a href="login.jsp">登录</a></li>
                <li><a href="register.jsp">注册</a></li>
            </c:if>
            <c:if test="${not empty sessionScope.user}">
                欢迎你：${user.username}
            </c:if>

            <li><a href="cart.jsp">购物车</a></li>
            <li><a href="orderListServlet?method=fingMyOrder">我的订单</a></li>
        </ol>
    </div>
</div>
<!--
时间：2015-12-30
描述：导航条
-->
<script>
    $(function () {
        $.ajax({
            url:"productServlet?method=findHead",
            type:"get",
            dataType:"json",
            success:function (data) {
                $(data).each(function (i) {
                    var product=data[i];
                    $("#producthead").append("<li class=\"active\"><a href=\"productServlet?method=findProductByCid&cid="+product.cid+"&pageNo=1\">"+product.cname+"<span class=\"sr-only\">(current)</span></a></li>")

                })
            }

        });
    })


</script>
<div class="container-fluid">
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.jsp">首页</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav" id="producthead">


                </ul>
                <form class="navbar-form navbar-right" role="search">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Search">
                    </div>
                    <button type="submit" class="btn btn-default">Submit</button>
                </form>

            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>
</div>
