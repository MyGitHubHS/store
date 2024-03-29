﻿<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>订单详情</title>
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

  <%--      <script>
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
                            }

                        });
                    }
                });

            })
        </script>--%>
	</head>

	<body>

<jsp:include page="head.jsp"/>
		<div class="container">
			<div class="row">

				<div style="margin:0 auto;margin-top:10px;width:950px;">
					<strong>订单详情</strong>
					<table class="table table-bordered">
						<tbody>
							<tr class="warning">
								<th colspan="5">订单编号:9005 </th>
							</tr>
							<tr class="warning">
								<th>图片</th>
								<th>商品</th>
								<th>价格</th>
								<th>数量</th>
								<th>小计</th>
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
										<%--<input type="text" name="quantity" class="inputnum" pid="${cart.value.product.pid}" value="${cart.value.num}" maxlength="4" size="10">--%>
									    <span>${cart.value.num}</span>
                                    </td>
									<td width="15%">
										<span class="subtotal" id="price">￥${cart.value.product.shopPrice*cart.value.num}</span>
									</td>
								</tr>
								<c:set var="price" value="${price+cart.value.product.shopPrice*cart.value.num}"/>
							</c:forEach>
						</tbody>
					</table>
				</div>

				<div style="text-align:right;margin-right:120px;">
					 商品总金额: <strong style="color:#ff6600;" id="totalmoney">￥${price}元</strong>
				</div>

			</div>

			<div>
				<hr/>
				<form class="form-horizontal" style="margin-top:5px;margin-left:150px;" action="orderServlet?method=submit" method="post">
                    <input  type="text" hidden="hidden" name="method" value="submit"/>
					<div class="form-group">
						<label for="address" class="col-sm-1 control-label">地址</label>
						<div class="col-sm-5">
							<input type="text" class="form-control" id="address" name="address" placeholder="请输入收货地址">
						</div>
					</div>
					<div class="form-group">
						<label for="address" class="col-sm-1 control-label">收货人</label>
						<div class="col-sm-5">
							<input type="text" class="form-control" id="username" name="username" value="${sessionScope.user.username}" placeholder="请输收货人">
						</div>
					</div>
					<div class="form-group">
						<label for="telephone" class="col-sm-1 control-label">电话</label>
						<div class="col-sm-5">
							<input type="text" class="form-control" id="telephone" name="telephone" placeholder="请输入联系方式">
						</div>
					</div>


				<hr/>

					<div style="margin-top:5px">
						<strong>选择银行：</strong>
						<p>
							<br/>
							<input type="radio" name="pd_FrpId" value="ICBC-NET-B2C" checked="checked" />工商银行
							<img src="./bank_img/icbc.bmp" align="middle" />&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="pd_FrpId" value="BOC-NET-B2C" />中国银行
							<img src="./bank_img/bc.bmp" align="middle" />&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="pd_FrpId" value="ABC-NET-B2C" />农业银行
							<img src="./bank_img/abc.bmp" align="middle" />
							<br/>
							<br/>
							<input type="radio" name="pd_FrpId" value="BOCO-NET-B2C" />交通银行
							<img src="./bank_img/bcc.bmp" align="middle" />&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="pd_FrpId" value="PINGANBANK-NET" />平安银行
							<img src="./bank_img/pingan.bmp" align="middle" />&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="pd_FrpId" value="CCB-NET-B2C" />建设银行
							<img src="./bank_img/ccb.bmp" align="middle" />
							<br/>
							<br/>
							<input type="radio" name="pd_FrpId" value="CEB-NET-B2C" />光大银行
							<img src="./bank_img/guangda.bmp" align="middle" />&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="pd_FrpId" value="CMBCHINA-NET-B2C" />招商银行
							<img src="./bank_img/cmb.bmp" align="middle" />

						</p>
						<hr/>
                        <%--
						<p style="text-align:right;margin-right:100px;">
							&lt;%&ndash;<a href="orderServlet?method=submit">
							<img src="./images/finalbutton.gif" width="204" height="51" border="0"  />
							</a>&ndash;%&gt;

                          &lt;%&ndash;  <input type="image" src="./images/finalbutton.gif" onclick="javascript:document.forms['Form_2'].reset(); return false;" />&ndash;%&gt;
                        </p>--%>
						<hr/>
					</div>
                    <hr/>
                    <p style="text-align:right;margin-right:100px;">
                        <input   type="image" src="./images/finalbutton.gif" width="204" height="51" border="0"/>
                    </p>
				</form>
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