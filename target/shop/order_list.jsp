<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
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


	<!-- 引入header.jsp -->
	<jsp:include page="/header.jsp"></jsp:include>

	<div class="container">
		<div class="row">
			<div style="margin: 0 auto; margin-top: 10px; width: 950px;">
				<strong>我的订单</strong>
				<table class="table table-bordered">
					<tbody>
						<c:forEach items="${vo.list}" var="orders">
							<tr class="success">
								<th colspan="5">
									订单编号:${orders.oid}&nbsp;&nbsp;&nbsp;&nbsp;
									订单状态:${orders.state==0?"未支付":"已支付"}
								</th>
							</tr>

							<tr class="warning">
								<th>图片</th>
								<th>商品</th>
								<th>价格</th>
								<th>数量</th>
								<th>小计</th>
							</tr>

							<c:forEach items="${orders.itemList}" var="orderItem">
								<tr class="active">
									<td width="60" width="40%">
										<img src="${path}/${orderItem.product.pimage}" width="70" height="60"></td>
									<td width="30%"><a target="_blank">${orderItem.product.pname}</a></td>
									<td width="20%">￥${orderItem.product.shop_price}</td>
									<td width="10%">${orderItem.count}</td>
									<td width="15%"><span class="subtotal">￥${orderItem.subtotal}元</span></td>
								</tr>
							</c:forEach>

						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>

		<!--分页 -->
		<%-- 当前列表中没有数据，则显示图片 --%>
		<c:if test="${vo.list.size()==0}">
			<div style="width: 380px;margin: 50px auto;">
				<img src="images/cart-empty.png">
			</div>
		</c:if>

		<c:if test="${vo.list.size()!=0}">
			<div style="width: 380px; margin: 0 auto; margin-top: 50px;">
				<ul class="pagination" style="text-align: center; margin-top: 10px;">

						<%-- 若在第一页，则不可以点击上一页 --%>
					<c:if test="${vo.pageNow == 1}">
						<li class="disabled">
							<a href="JavaScript:void(0)" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a>
						</li>
					</c:if>

						<%-- 若不在第一页，则可以点击上一页 --%>
					<c:if test="${vo.pageNow != 1}">
						<li>
							<a href="${path}/order?method=viewMyOrders&pageNow=${vo.pageNow-1}" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a>
						</li>
					</c:if>

					<c:forEach begin="1" end="${vo.myPages}" var="page">

						<%--  若当前页码pageNow正好是page，则显示被点击的状态 --%>
						<c:if test="${vo.pageNow == page}">
							<li class="active">
								<a href="JavaScript:void(0)">${page}</a>
							</li>
						</c:if>

						<%--  若当前页码pageNow不是page，则显示可以点击的状态 --%>
						<c:if test="${vo.pageNow != page}">
							<li>
								<a href="${path}/order?method=viewMyOrders&pageNow=${page}">${page}</a>
							</li>
						</c:if>

					</c:forEach>



						<%-- 若在最后一页，则不可以点击下一页 --%>
					<c:if test="${vo.pageNow == vo.myPages}">
						<li class="disabled">
							<a href="JavaScript:void(0)" aria-label="Next"> <span aria-hidden="true">&raquo;</span></a>
						</li>
					</c:if>

						<%-- 若不在最后一页，则可以点击下一页 --%>
					<c:if test="${vo.pageNow != vo.myPages}">
						<li>
							<a href="${path}/order?method=viewMyOrders&pageNow=${vo.pageNow+1}" aria-label="Next"> <span aria-hidden="true">&raquo;</span></a>
						</li>
					</c:if>
				</ul>
			</div>
		</c:if>
		<!-- 分页结束 -->
	</div>

	<!-- 引入footer.jsp -->
	<jsp:include page="/footer.jsp"></jsp:include>
	
</body>

</html>