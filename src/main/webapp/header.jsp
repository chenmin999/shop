<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- 登录 注册 购物车... -->
<div class="container-fluid">
	<div class="col-md-4">
		<img src="img/logo.png" />
	</div>
	<div class="col-md-5">
		<img src="img/header.png" />
	</div>
	<div class="col-md-3" style="padding-top:20px">
		<ol class="list-inline">

			<%-- 未登录 --%>
			<c:if test="${sessionScope.user==null}">
				<li><a href="login.jsp">登录</a></li>
				<li><a href="register.jsp">注册</a></li>
			</c:if>

			<%-- 已登录 --%>
			<c:if test="${sessionScope.user!=null}">
				<li>欢迎${sessionScope.user.username}<a href="${path}/user?method=logout">登出</a></li>
			</c:if>

			<li><a href="cart.jsp">购物车</a></li>
			<li><a href="${path}/order?method=viewMyOrders">我的订单</a></li>
		</ol>
	</div>
</div>

<c:set value="${pageContext.request.contextPath}" scope="application" var="path"></c:set>

<!-- 导航条 -->
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
				<a class="navbar-brand" href="${path}/product?method=index">首页</a>
			</div>

			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav" id="categorys">

					<%--  动态在数据库中查询的商品分类的展示 --%>

				</ul>

				<%-- 当前若是商品列表展示，则头部页面有表单显示 --%>
				<c:if test="${vo!=null}">
					<form class="navbar-form navbar-right" role="search">
						<div class="form-group">
							<input type="text" class="form-control" placeholder="Search" id="searchName" name="pname" value="${vo.query2}">
						</div>
						<input type="button" class="btn btn-success" value="Submit" onclick="query()" />
						
						<script>
							function query() {
								window.location.href="${path}/product?method=viewProductListByCidPname&cid=${vo.query1}&pname="+$("#searchName").val();
							}
						</script>
					</form>
				</c:if>

			</div>
		</div>
	</nav>
</div>
<script>

	var data = "";
	$.ajax({
		type:"get",
		url:"${path}/category?method=viewAllCategory",
		dataType:"json",
		success:function(categoryList){
			for (var i in categoryList) {  //i是集合的下标
				//console.log("i = "+i)
				data += "<li><a href='${path}/product?method=viewProductListByCidPname&cid="+categoryList[i].cid+"'>"+categoryList[i].cname+"</a></li>";
			}
			$("#categorys").html(data);
		}
	});

</script>