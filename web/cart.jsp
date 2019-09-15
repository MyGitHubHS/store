<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html xmlns:jsp="http://www.w3.org/1999/html">

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>购物车</title>
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
			
			.container .row div {
				/* position:relative;
	 float:left; */
			}
			
			font {
				color: #3164af;
				font-size: 18px;
				font-weight: normal;
				padding: 0 10px;
			}
		</style>
	</head>
	<script>
		$(function () {
			var num1;
			//获取点击时的数量值
			$(".inputnum").click(function () {
				num1=$(this).val();
				console.log(num1)
			});
			$(".inputnum").blur(function () {
				//如果松开时，前后值不一致，就发送ajax请求去更新数据
				var pid = $(this).attr("pid");
				var num2 = $(this).val();
				console.log(num2);
				if(num1!=num2){
					$.ajax({
						url:"cartServlet?method=modifyByPid&pid="+pid+"&num="+num2,
						type:"get",
						dataType:"json",
						success:function (data) {
							$("#price").text(data[0]);
							$("#totalmoney").text(data[1]);
							$("#totalintegral").text(data[1]);
						}

					});
				}
			});

		})

	</script>

	<body>

	<jsp:include page="head.jsp"/>


		<div class="container">
			<div class="row">

				<div style="margin:0 auto; margin-top:10px;width:950px;">
					<strong style="font-size:16px;margin:5px 0;">订单详情</strong>
					<table class="table table-bordered">
						<tbody>
							<tr class="warning">
								<th>图片</th>
								<th>商品</th>
								<th>价格</th>
								<th>数量</th>
								<th>小计</th>
								<th>操作</th>
							</tr>
							<c:set var="price" value="0" />
							<c:forEach items="${sessionScope.map}" var="cart">
							<tr class="active">
								<td width="60" width="40%">
									<input type="hidden" name="id" value="22">
									<img src="${cart.value.product.pimage}" width="70" height="60">
										<%--${cart.product.pimage}--%>
								</td>
								<td width="30%">
									<a target="_blank">${cart.value.product.pname}</a>
								</td>
								<td width="20%">
									￥${cart.value.product.shopPrice}
								</td>
								<td width="10%">
									<input type="text" name="quantity"  class="inputnum" pid="${cart.value.product.pid}" value="${cart.value.num}" maxlength="4" size="10">
								</td>
								<td width="15%">
									<span class="subtotal" id="price">￥${cart.value.product.shopPrice*cart.value.num}</span>
								</td>
								<td>
									<a href="cartServlet?method=delectCartByPid&pid=${cart.value.product.pid}" class="delete">删除</a>
								</td>
							</tr>
								<c:set var="price" value="${price+cart.value.product.shopPrice*cart.value.num}"/>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>

			<div style="margin-right:130px;">
				<div style="text-align:right;">
					<em style="color:#ff6600;">
				登录后确认是否享有优惠&nbsp;&nbsp;
			</em> 赠送积分: <em style="color:#ff6600;" id="totalintegral">${price}</em>&nbsp; 商品金额: <strong style="color:#ff6600;" id="totalmoney">￥${price}元</strong>
				</div>
				<div style="text-align:right;margin-top:10px;margin-bottom:10px;">
					<a href="cartServlet?method=cleanAll" id="clear" class="clear">清空购物车</a>
					<a href="order_info.jsp">
						<input type="submit" width="100" value="提交订单" name="submit" border="0" style="background: url('./images/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
						height:35px;width:100px;color:white;">
					</a>
				</div>
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