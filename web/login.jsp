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
<link rel="stylesheet" href="css/style.css" type="text/css"/>
<script>
    $(function () {
        $("#username").blur(function () {
            var name=$(this).val();
            if (name == ""||name==null){
                $("#tips").text("用户名不能为空！").css("color","red");
            }else{
                $.ajax({
                    type : "POST",
                    url : "userServlet?method=findUser",
                    data : "name=" + name,
                    success : function(msg) {
                        if (msg==0) {
                            $("#tips").text("用户名不正确哦！").css("color","red");
                        }
                    }
                });
            }
        })
<!--验证验证码-->
        $("#checkImg").click(function () {
            /*var url="checkImg.jpg?date="+new Date();
            $.ajax({
                type : "GET",
                url : url
            })
            console.log(url);*/
            $(this).attr("src","checkImg.jpg?date="+new Date());
        })
		$("#checkcode").blur(function () {
			if($(this).val()!=${sessionScope.code}){
				console.log(${sessionScope.code});
				alert("验证码错误！")
			}
		})
    })

</script>
<style>
  body{
   margin-top:20px;
   margin:0 auto;
 }
 .carousel-inner .item img{
	 width:100%;
	 height:300px;
 }
 .container .row div{ 
	 /* position:relative;
	 float:left; */
 }
 
font {
    color: #666;
    font-size: 22px;
    font-weight: normal;
    padding-right:17px;
}
 </style>
</head>
<body>

<jsp:include page="head.jsp"/>

<div class="container"  style="width:100%;height:460px;background:#FF2C4C url('images/loginbg.jpg') no-repeat;">
<div class="row"> 
	<div class="col-md-7">
		<!--<img src="./image/login.jpg" width="500" height="330" alt="会员登录" title="会员登录">-->
	</div>
	
	<div class="col-md-5">
				<div style="width:440px;border:1px solid #E7E7E7;padding:20px 0 20px 30px;border-radius:5px;margin-top:60px;background:#fff;">
				<font>会员登录</font>USER LOGIN

				<div>&nbsp;</div>
<form class="form-horizontal" method="post"  action="userServlet?method=login"><!--action="userServlet?method=login"-->
  
 <div class="form-group">
    <label for="username" class="col-sm-2 control-label">用户名</label>
	 <span ></span>
    <div class="col-sm-6">
      <input type="text" class="form-control" id="username" name="username" value="${cookie.get("remembername").value}" placeholder="请输入用户名">
        <span id="tips">${errorMsg}</span>
    </div>
  </div>
   <div class="form-group">
    <label for="inputPassword" class="col-sm-2 control-label">密码</label>
    <div class="col-sm-6">
      <input type="password" class="form-control" id="inputPassword" name="password" placeholder="请输入密码">
    </div>
  </div>
   <div class="form-group">
        <label for="checkcode" class="col-sm-2 control-label">验证码</label>
    <div class="col-sm-3">
      <input type="text" class="form-control" id="checkcode" name="checkcode" placeholder="请输入验证码">
    </div>
    <div class="col-sm-3">
      <img src="checkImg.jpg" id="checkImg"/>
    </div>
    
  </div>
   <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <div class="checkbox">
        <label>
          <input type="checkbox" name="aotulogin"> 自动登录
        </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <label>
          <input type="checkbox" name="remembername"> 记住用户名
        </label>
      </div>
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
    <input type="submit"  width="100" value="登录" name="submit" border="0"
    style="background: url('./images/login.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
    height:35px;width:100px;color:white;">
    </div>
  </div>
</form>
</div>			
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
</body></html>