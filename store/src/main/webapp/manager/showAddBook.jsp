<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>首页</title>
    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath }/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/css/index.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/css/config.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/css/font-awesome.min.css" rel="stylesheet">

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
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">admin <span class="caret"></span></a>
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
                <dt class="list_dt" >
                    <span class="_after"></span>
                    <p>分类管理</p>
                    <i class="list_dt_icon"></i>
                </dt>
                <dd class="list_dd" >
                    <ul>
                        <li class="list_li" >
                            <a class="list_a" href="${pageContext.request.contextPath }/manager/showAddCategory.jsp">添加书籍分类</a>
                        </li>
                        <li class="list_li">
                            <a class="list_a" href="${pageContext.request.contextPath }/manager/ManagerController?op=showCategoryAll">查询书籍分类</a>
                        </li>
                      
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
                        	<a class="list_a" href="${pageContext.request.contextPath }/manager/showAddBook.jsp">添加书籍</a>
                        </li>
                        <li class="list_li">
                        	<a class="list_a" href="${pageContext.request.contextPath }/manager/showBook.jsp">查询书籍</a>
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
                    <li><a href="index.html">首页</a></li>
                </ol>
                <!-- 主体部分 -->
                <div class="main_box">
                  <div
						style="width: 680px; margin-top: 50px; margin-left: 200px; text-align: center;">
						<div class="panel panel-danger">
							<div class="panel-heading">情報増加</div>
							<div class="panel-body">
									<form action="${pageContext.request.contextPath }/manager/UploaderController" class="form-horizontal" method="post" enctype="multipart/form-data">
									<div class="form-group">
										<label for="inputEmail3" class="col-sm-2 control-label">名称</label>
										<div class="col-sm-10">
											<input type="text" name="bookName" class="form-control"
												id="bookName" placeholder="名称"> 
										</div>
									</div>
									<div class="form-group">
										<label for="inputPassword3" class="col-sm-2 control-label">ポイント</label>
										<div class="col-sm-10">
											<input type="text" name="sellPoint" class="form-control"
												id="sellPoint" placeholder="ポイント"> 
										</div>
									</div>
									<div class="form-group">
										<label for="inputPassword3" class="col-sm-2 control-label">画像</label>
										<input type="file" name="pic" id="exampleInputFile">
									</div>
									<div class="form-group">
										<label for="inputPassword3" class="col-sm-2 control-label">大筋</label>
										<div class="col-sm-10">
											<input type="text" name="des" class="form-control"
												id="des" placeholder="大筋"> 
										</div>
									</div>
									<div class="form-group">
										<label for="inputPassword3" class="col-sm-2 control-label">値段</label>
										<div class="col-sm-10">
											<input type="text" name="price" class="form-control"
												id="price" placeholder="値段"> 
										</div>
									</div>
									<div class="form-group">
										<label for="inputPassword3" class="col-sm-2 control-label">ジャンル</label>
										<div class="col-sm-10">
											<select id="categoryId" name="categoryId" class="form-control">
												<option value="0">ジャンルの選択</option>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label for="inputPassword3" class="col-sm-2 control-label">概述</label>
										<div class="col-sm-10">
											<textarea class="form-control" name="des" id="des" rows="3"></textarea>
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-offset-2 col-sm-10">
											<button type="submit" class="btn btn-default add">保存</button>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
					
                </div>

            </div>
        </div>
    </div>
    <!-- 引入公共js文件 -->
    <script src="${pageContext.request.contextPath }/js/jquery-3.3.1.min.js"></script>
    <script src="${pageContext.request.contextPath }/js/jquery.slimscroll.min.js"></script>
    <script src="${pageContext.request.contextPath }/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath }/js/showAddBook.js"></script>
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