$(function(){
	var id = 0;

	$(".list_dt").on("click", function() {
		$('.list_dd').stop();
		$(this).siblings("dt").removeAttr("id");
		if ($(this).attr("id") == "open") {
			$(this).removeAttr("id").siblings("dd").slideToggle();
		} else {
			$(this).attr("id", "open").next().slideToggle();
		}
	});

	function logout() {
		window.location.href = "login.html";
	}
	
	$(".deleteCategory").click(function(){
	
		id = $(this).attr("id");
		var text = $(this).parent().siblings(".cName").text();
		
		$("#myModalDelete").modal();
		$(".modal-body").text("是否要删除:   "+text);
	})

	$("#confirmDelete").click(function(){
		$.ajax({
			type: "GET",
			url: "/store/manager/ManagerController?op=deleteCategory",
			data: "id="+id,
			success: function(msg){
				window.location.href = "/store/manager/ManagerController?op=showCategoryAll";
			}
		}); 
	})

	$(".updateCategory").click(function(){
		
		id = $(this).attr("id");
		$.ajax({
			type: "GET",
			url: "/store/manager/ManagerController?op=showUpdateCategory",
			data: "id="+id,
			success: function(msg){
				var json = eval('(' + msg + ')');
				$("#id").val(json.id);
				$("#categoryName").val(json.categoryName);
				$("#des").text(json.des);
				$("#myModalUpdate").modal();
			}
		});
	})
	
	$(".updateCategoryButton").click(function(){
		var id = $("#id").val();
		var categoryName = $("#categoryName").val();
		var des = $("#des").val();
		$.ajax({
			type: "POST",
			url: "/store/manager/ManagerController?op=updateCategory",
			data: "categoryName="+categoryName+"&des="+des+"&id="+id,
			success: function(msg){
				window.location.href = "/store/manager/ManagerController?op=showCategoryAll";
			}
		});
		return false;
	})
	
})