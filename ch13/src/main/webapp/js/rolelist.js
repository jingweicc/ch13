var roleObj;

//订单管理页面上点击删除按钮弹出删除框(rolelist.jsp)
function deleteRole(obj){
	$.ajax({
		type:"GET",
		url:path+"/role/delRole",
		data:{method:"delrole",id:obj.attr("roleid")},
		dataType:"json",
		success:function(data){
			if(data.result == "true"){//删除成功：移除删除行
				cancleBtn();
				obj.parents("tr").remove();
			}else if(data.result == "false"){//删除失败
				//alert("对不起，删除订单【"+obj.attr("rolecc")+"】失败");
				changeDLGContent("对不起，删除角色【"+obj.attr("rolecc")+"】失败");
			}else if(data.result == "notexist"){
				//alert("对不起，订单【"+obj.attr("rolecc")+"】不存在");
				changeDLGContent("对不起，角色【"+obj.attr("rolecc")+"】不存在");
			}else if(data.result == "more"){
				changeDLGContent("对不起，角色【"+obj.attr("rolecc")+"】被系统用户所引用,暂时不能删除");
			}
		},
		error:function(data){
			alert("对不起，删除失败");
		}
	});
}

function openYesOrNoDLG(){
	$('.zhezhao').css('display', 'block');
	$('#removeBi').fadeIn();
}

function cancleBtn(){
	$('.zhezhao').css('display', 'none');
	$('#removeBi').fadeOut();
}
function changeDLGContent(contentStr){
	var p = $(".removeMain").find("p");
	p.html(contentStr);
}

$(function(){	
	$(".viewRole").on("click",function(){
		//将被绑定的元素（a）转换成jquery对象，可以使用jquery方法
		var obj = $(this);
		window.location.href=path+"/role/toModifyRole?method=view&id="+ obj.attr("roleid");
	});
	
	$(".modifyRole").on("click",function(){
		var obj = $(this);
		window.location.href=path+"/role/toModifyRole?method=modify&id="+ obj.attr("roleid");
	});
	$('#no').click(function () {
		cancleBtn();
	});
	
	$('#yes').click(function () {
		deleteRole(roleObj);
	});

	$(".deleteRole").on("click",function(){
		roleObj = $(this);
		changeDLGContent("你确定要删除角色【"+roleObj.attr("rolecc")+"】吗？");
		openYesOrNoDLG();
	});
});

