<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>首页</title>
<!-- Bootstrap core CSS -->
<link href="${pageContext.request.contextPath }/css/bootstrap.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath }/css/index.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath }/css/config.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath }/css/font-awesome.min.css"
	rel="stylesheet">
</head>
<body>
	<!-- nav导航 -->
	<nav class="navbar navbar-inverse bdr-n z-ind">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<a class="navbar-brand" href="#">钉钉书城后台</a>
			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">admin <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="#">设置</a></li>
							<li><a href="#">个人中心</a></li>
							<li onclick="logout()"><a href="#">退出</a></li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
	</nav>
	<!-- 页面主体 -->
	<div class="dh mainbox">
		<!-- 左边菜单 -->
		<div class="navleft leftnav-bgc">
			<dl class="list_dl">
				<dt class="list_dt">
					<span class="_after"></span>
					<p>分类管理</p>
					<i class="list_dt_icon"></i>
				</dt>
				<dd class="list_dd">
					<ul>
						<li class="list_li"><a class="list_a"
							href="${pageContext.request.contextPath }/manager/showAddCategory.jsp">添加书籍分类</a>
						</li>
						<li class="list_li"><a class="list_a"
							href="${pageContext.request.contextPath }/manager/ManagerController?op=showCategoryAll">查询书籍分类</a></li>
					</ul>
				</dd>
				<dt class="list_dt">
					<span class="_after"></span>
					<p>书籍管理</p>
					<i class="list_dt_icon"></i>
				</dt>
				<dd class="list_dd">
					<ul>
						<li class="list_li">
							<a class="list_a" href="${pageContext.request.contextPath }/manager/showAddBook.jsp">添加书籍信息</a>
						</li>
						<li class="list_li">
						<a class="list_a"
							href="${pageContext.request.contextPath }/manager/showBook.jsp">添加书籍信息</a>
						</li>
					</ul>
				</dd>
				<dt class="list_dt">
					<span class="_after"></span>
					<p>订单管理</p>
					<i class="list_dt_icon"></i>
				</dt>
				<dd class="list_dd">
					<ul>
						<li class="list_li">查看订单</li>
					</ul>
				</dd>
			</dl>
		</div>
		<!-- 右边主显示部分 -->
		<div class="main-rightbox">
			<div class="mainrightbox">
				<!-- 路径导航 -->
				<ol class="breadcrumb">
					<li><a href="${pageContext.request.contextPath }/manager">首页</a></li>
					<li class="active">书籍管理</li>
					<li class="active">查询书籍</li>
				</ol>
				<!-- 主体部分 -->
				<div class="main_box">
					<div
						style="width: 900px; margin-top: 50px; margin-left: 50px; text-align: center;">
						<div class="panel panel-danger">
							<div class="panel-heading">查询书籍分类信息</div>
							<div class="panel-body">
								<table class="table table-hover bookTable" style="width: 870px">
									<tr>
										<td>书籍编号</td>
										<td>书籍名称</td>
										<td>书籍卖点</td>
										<td>书籍价格</td>
										<td>书籍图片</td>
										<td>书籍描述</td>
										<td>书籍类别</td>
										<td>操作</td>
									</tr>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="myModalBookDelete" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">删除书籍分类信息</h4>
				</div>
				<div class="modal-body ">
					是否删除书籍分类信息
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-danger" id="confirmDelete" data-dismiss="modal">确定</button>
				</div>
			</div>
		</div>
	</div>
	
<div class="modal fade" id="myModalBookUpdate" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">修改书籍分类信息</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal updateCategory">
						<input type="hidden" id="id" name="id" />
						<input type="hidden" name="op" value="updateCategory" />
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">分类名称</label>
							<div class="col-sm-10">
								<input type="text" name="categoryName" class="form-control"
									id="categoryName" placeholder="分类名称"> <span id="tips"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-2 control-label">分类描述</label>
							<div class="col-sm-10">
								<textarea class="form-control" name="des" id="des" rows="3"></textarea>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-primary"
								data-dismiss="modal">关闭</button>
							<button type="button" class="btn btn-danger updateCategoryButton" data-dismiss="modal">修改</button>
						</div>
					</form>
				</div>

			</div>
		</div>
	</div>



	<!-- 引入公共js文件 -->
	<script
		src="${pageContext.request.contextPath }/js/jquery-3.3.1.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/js/jquery.slimscroll.min.js"></script>
	<script src="${pageContext.request.contextPath }/js/bootstrap.js"></script>
	<script src="${pageContext.request.contextPath }/js/showBook.js"></script>
	<script>
        // 此方法为切换左边菜单的js文件
        $(".list_dt").on("click", function() {
            $('.list_dd').stop();
            $(this).siblings("dt").removeAttr("id");
            if ($(this).attr("id") == "open") {
                $(this).removeAttr("id").siblings("dd").slideToggle();
            } else {
                $(this).attr("id", "open").next().slideToggle();
            }
        });
        // 退出
        function logout() {
            window.location.href = "login.html";
        }
    </script>
</body>

</html>