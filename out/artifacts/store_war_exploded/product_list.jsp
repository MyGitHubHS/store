<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>商品展示</title>
		<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
		<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
		<!-- 引入自定义css文件 style.css -->
		<link rel="stylesheet" href="css/style.css" type="text/css" />

		<style>
			body {
				margin-top: 20px;
				margin: 0 auto;
				width: 100%;
			}
			.carousel-inner .item img {
				width: 100%;
				height: 300px;
			}
		</style>
	</head>

	<body>
		
			<!--
            	时间：2015-12-30
            	描述：菜单栏
            -->

			<!--
            	时间：2015-12-30
            	描述：导航条
            -->

<jsp:include page="head.jsp"/>

		<div class="row" style="width:1210px;margin:0 auto;">
			<div class="col-md-12">
				<ol class="breadcrumb">
					<li><a href="#">首页</a></li>
				</ol>
			</div>

            <c:forEach items="${pagelist.list}" var="product">
			<div class="col-md-2">
				<a href="productServlet?method=findProductByPid&pid=${product.pid}">
					<img src="${product.pimage}" width="170" height="170" style="display: inline-block;">
				</a>
				<p><a href="product_info.html" style='color:green'>${product.pname}</a></p>
				<p><font color="#FF0000">商城价：${product.shopPrice }</font></p>
			</div>
            </c:forEach>

		</div>

		<!--分页 -->
		<div style="width:380px;margin:0 auto;margin-top:50px;">
			<ul class="pagination" style="text-align:center; margin-top:10px;">
				<c:if test="${pagelist.pageNo>1}">
				<li ><a href="productServlet?method=findProductByCid&cid=1&pageNo=${pagelist.pageNo-1}" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
				</c:if>
               <%-- <c:if test="${requestScope.page.pageNo}<3">--%>
				<%--<c:forEach var="i" begin="1" items="${pagelist.list}" step="${pagelist.totalPage}">--%>
				<li <c:if test="${1==pagelist.pageNo}"> class="active" </c:if>><a href="productServlet?method=findProductByCid&cid=1&pageNo=1">1</a></li>
				<li <c:if test="${2==pagelist.pageNo}"> class="active" </c:if>><a href="productServlet?method=findProductByCid&cid=1&pageNo=2">2</a></li>
				<li <c:if test="${3==pagelist.pageNo}"> class="active" </c:if>><a href="productServlet?method=findProductByCid&cid=1&pageNo=3">3</a></li>
				<li <c:if test="${4==pagelist.pageNo}"> class="active" </c:if>><a href="productServlet?method=findProductByCid&cid=1&pageNo=4">4</a></li>
				<%--</c:forEach>--%>
				<c:if test="${pagelist.pageNo<pagelist.totalPage}">
				<li>
					<a href="productServlet?method=findProductByCid&cid=1&pageNo=${pagelist.pageNo+1}" aria-label="Next">
						<span aria-hidden="true">&raquo;</span>
					</a>
				</li>
				</c:if>
			</ul>
			<!---->
		</div>
		<!-- 分页结束=======================        -->

		<!--
       		商品浏览记录:
        -->
		<div style="width:1210px;margin:0 auto; padding: 0 9px;border: 1px solid #ddd;border-top: 2px solid #999;height: 246px;">

			<h4 style="width: 50%;float: left;font: 14px/30px " 微软雅黑 ";">浏览记录</h4>
			<div style="width: 50%;float: right;text-align: right;"><a href="">more</a></div>
			<div style="clear: both;"></div>

			<div style="overflow: hidden;">

				<ul style="list-style: none;">
					<li style="width: 150px;height: 216;float: left;margin: 0 8px 0 0;padding: 0 18px 15px;text-align: center;"><img src="products/1/cs10001.jpg" width="130px" height="130px" /></li>
				</ul>

			</div>
		</div>
		<div style="margin-top:50px;">
			<img src="./image/footer.jpg" width="100%" height="78" alt="我们的优势" title="我们的优势" />
		</div>

		<div style="text-align: center;margin-top: 5px;">
			<ul class="list-inline">
				<li><a>关于我们</a></li>
				<li><a>联系我们</a></li>
				<li><a>招贤纳士</a></li>
				<li><a>法律声明</a></li>
				<li><a>友情链接</a></li>
				<li><a target="_blank">支付方式</a></li>
				<li><a target="_blank">配送方式</a></li>
				<li><a>服务声明</a></li>
				<li><a>广告声明</a></li>
			</ul>
		</div>
		<div style="text-align: center;margin-top: 5px;margin-bottom:20px;">
			Copyright &copy; 2005-2016 天虎商城 版权所有
		</div>

	</body>

</html>