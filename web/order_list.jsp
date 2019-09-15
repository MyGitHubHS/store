<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>会员登录</title>
		<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
		<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
		<!-- 引入自定义css文件 style.css -->
		<link rel="stylesheet" href="css/style.css" type="text/css" />

		<style>
			body {
				margin-top: 20px;
				margin: 0 auto;
			}
			
			.carousel-inner .item img {
				width: 100%;
				height: 300px;
			}
		</style>
	</head>

	<body>

	<jsp:include page="head.jsp"/>



		<div class="container">
			<div class="row">

				<div style="margin:0 auto; margin-top:10px;width:950px;">
					<strong>我的订单</strong>
					<c:forEach items="${orderListPage.list}" var="order" >
						<table class="table table-bordered">
							<tbody>
								<tr class="success">
									<th colspan="5">订单编号：${order.oid} </th>
								</tr>
								<tr class="warning">
									<th>图片</th>
									<th>商品</th>
									<th>价格</th>
									<th>数量</th>
									<th>小计</th>
								</tr>
								<tr class="active">
									<td width="60" width="40%">
										<input type="hidden" name="id" value="22">
										<img src="${order.pimage}" width="70" height="60">
									</td>
									<td width="30%">
										<a target="_blank"> ${order.pname}</a>
									</td>
									<td width="20%">
										￥ ${order.shopPrice}
									</td>
									<td width="10%">
											${order.count}
									</td>
									<td width="15%">
										<span class="subtotal">￥${order.subtotal}</span>
									</td>
								</tr>
							</tbody>
						</c:forEach>
					</table>
				</div>
			</div>
			<div style="text-align: center;">
				<ul class="pagination">
					<%--<li><a href="#">共 <em style="color:#ff6600;" >${orderListPage.totalCount}</em>条记录</a></li>
					<li><a href="#">当前第 <em style="color:#ff6600;" >${orderListPage.pageNo}</em>页</a></li>
					<li><a href="#">共<em style="color:#ff6600;" >${orderListPage.totalPage}</em>页</a></li>--%>
					<c:if test="${orderListPage.pageNo>1}"><li>
						<a href="orderListServlet?method=fingMyOrder&pageNo=${orderListPage.pageNo-1}"
						   aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li></c:if>
					<li <c:if test="${orderListPage.pageNo==1}" > class="active" </c:if> >

						<a href="orderListServlet?method=fingMyOrder&pageNo=1">1</a></li>
					<li <c:if test="${orderListPage.pageNo==2}" > class="active" </c:if>>
						<a href="orderListServlet?method=fingMyOrder&pageNo=2">2</a></li>
					<li <c:if test="${orderListPage.pageNo==3}" > class="active" </c:if>>
						<a href="orderListServlet?method=fingMyOrder&pageNo=3">3</a></li>
					<li <c:if test="${orderListPage.pageNo==4}" > class="active" </c:if>>
						<a href="orderListServlet?method=fingMyOrder&pageNo=4">4</a></li>
						<c:if test="${orderListPage.pageNo<orderListPage.totalPage}">
					<li>
						<a href="#" aria-label="Next">
							<span aria-hidden="true">&raquo;</span>
						</a>
					</li>
						</c:if>
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