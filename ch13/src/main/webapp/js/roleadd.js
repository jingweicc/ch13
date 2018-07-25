var roleCode = null;
var roleName = null;
var addBtn = null;
var backBtn = null;

function priceReg (value){
	value = value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符
		value = value.replace(/^\./g,"");  //验证第一个字符是数字而不是.
    value = value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的.
    value = value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");//去掉特殊符号￥
	if(value.indexOf(".")>0){
		value = value.substring(0,value.indexOf(".")+3);
	}
	return value;
}


$(function(){
	roleCode = $("#roleCode");
	roleName = $("#roleName");
	addBtn = $("#add");
	backBtn = $("#back");
	//初始化的时候，要把所有的提示信息变为：* 以提示必填项，更灵活，不要写在页面上
	roleCode.next().html("*");
	roleName.next().html("*");

	/*
	 * 验证
	 * 失焦\获焦
	 * jquery的方法传递
	 */
	roleCode.bind("blur",function(){
		//ajax后台验证--roleCode是否已存在
		//role.do?method=ucexist&roleCode=**
		$.ajax({
			type:"POST",//请求类型
			url:path+"/role/findRole",//请求的url
			data:{method:"roleexist",roleCode:roleCode.val()},//请求参数
			dataType:"json",//ajax接口（请求url）返回的数据类型
			success:function(data){//data：返回数据（json对象）
				if(data.result == "exist"){//账号已存在，错误提示
					validateTip(roleCode.next(),{"color":"red"},imgNo+ " 该角色已存在",false);
				}else{//账号可用，正确提示
					validateTip(roleCode.next(),{"color":"green"},imgYes+" 该角色可以使用",true);
				}
			},
			error:function(data){//当访问时候，404，500 等非200的错误状态码
				validateTip(roleCode.next(),{"color":"red"},imgNo+" 您访问的页面不存在",false);
			}
		});
		
		
	}).bind("focus",function(){
		//显示友情提示
		validateTip(roleCode.next(),{"color":"#666666"},"* 用户编码是您登录系统的账号",false);
	}).focus();
	
	roleName.on("focus",function(){
		validateTip(roleName.next(),{"color":"#666666"},"* 请输入角色名称",false);
	}).on("blur",function(){
		if(roleName.val() != null && roleName.val() != ""){
			validateTip(roleName.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(roleName.next(),{"color":"red"},imgNo+" 角色名称不能为空，请重新输入",false);
		}
		
	});
	
	addBtn.on("click",function(){
		if(roleCode.attr("validateStatus") != "true"){
			roleCode.blur();
		}else if(roleName.attr("validateStatus") != "true"){
			roleName.blur();
		}else{
			if(confirm("是否确认提交数据")){
				$("#roleForm").submit();
			}
		}
	});
	
	backBtn.on("click",function(){
		if(referer != undefined 
			&& null != referer 
			&& "" != referer
			&& "null" != referer
			&& referer.length > 4){
		 window.location.href = referer;
		}else{
			history.back(-1);
		}
	});
});