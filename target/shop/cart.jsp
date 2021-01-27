﻿<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>极客商城购物车</title>
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
			
			font {
				color: #3164af;
				font-size: 18px;
				font-weight: normal;
				padding: 0 10px;
			}
		</style>
	</head>

	<body>
		<!-- 引入header.jsp -->
		<jsp:include page="/header.jsp"></jsp:include>

		<c:if test="${empty cart.map}">
			<div style="width: 380px;margin: auto;">
				<img src="images/cart-empty.png">
			</div>
		</c:if>

		<c:if test="${!empty cart.map}">
			<div class="container">
				<div class="row">

					<div style="margin:0 auto; margin-top:10px;width:950px;">
						<strong style="font-size:16px;margin:5px 0;">购物车详情</strong>
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

								<%--  展示购物车明细 --%>
							<c:forEach items="${cart.map}" var="entry">
								<tr class="active">
									<td width="60" width="40%">
										<input type="hidden" name="id" value="22">
										<img src="${entry.value.product.pimage}" width="70" height="60">
									</td>
									<td width="30%">
										<a target="_blank">${entry.value.product.pname}</a>
									</td>
									<td width="20%">
										￥${entry.value.product.shop_price}
									</td>
									<td width="10%">
										<span>${entry.value.buyNum}</span>
									</td>
									<td width="15%">
										<span class="subtotal">￥${entry.value.subTotal}</span>
									</td>
									<td>
										<a href="${path}/cart?method=delCart&pid=${entry.value.product.pid}" class="delete">删除</a>
									</td>
								</tr>
							</c:forEach>

							</tbody>
						</table>
					</div>
				</div>

				<div style="margin-right:130px;">
					<div style="text-align:right;">
						<em style="color:#ff6600;">
							登录后确认是否享有优惠&nbsp;&nbsp;
						</em>
						赠送积分:
						<em style="color:#ff6600;">
							<fmt:formatNumber type="number" value="${cart.total}" maxFractionDigits="0" />
						</em>&nbsp;
						商品金额: <strong style="color:#ff6600;">￥${cart.total}元</strong>
					</div>
					<div style="text-align:right;margin-top:10px;margin-bottom:10px;">
						<a href="${path}/cart?method=clearCart" id="clear" class="clear">清空购物车</a>
						<a href="${path}/order?method=submitOrder">
							<input type="submit" width="100" value="提交订单" name="submit" border="0" style="background: url('./images/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
						height:35px;width:100px;color:white;">
						</a>
						<span style="color: red">${msg}</span>
					</div>
				</div>

			</div>
		</c:if>

		<!-- 引入footer.jsp -->
		<jsp:include page="/footer.jsp"></jsp:include>

	</body>
</html>