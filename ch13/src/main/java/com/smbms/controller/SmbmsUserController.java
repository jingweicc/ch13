package com.smbms.controller;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.smbms.entity.SmbmsRole;
import com.smbms.entity.SmbmsUser;
import com.smbms.service.SmbmsRoleService;
import com.smbms.service.SmbmsUserService;

@Controller
@RequestMapping(value="user")
public class SmbmsUserController {
	@Autowired
	private SmbmsUserService smbmsUserService;
	@Autowired
	private SmbmsRoleService smbmsRoleService;
	
	@RequestMapping(value="login")
	public String login(SmbmsUser user,Model model,HttpSession session){
		SmbmsUser logined = smbmsUserService.login(user);
		if(logined!=null){
			session.setAttribute("userSession", logined);
			return "redirect:/user/userList";
		}
		return "login";
	}
	
	@RequestMapping(value="logout")
	public String logout(HttpSession session){
		session.removeAttribute("userSession");
		return "login";
	}
	
	@RequestMapping(value="register")
	public String register(SmbmsUser user,HttpSession session){
		SmbmsUser logined = (SmbmsUser) session.getAttribute("userSession");
		if(logined!=null){
			user.setCreatedBy(logined.getId().longValue());
			user.setCreationDate(new Timestamp(System.currentTimeMillis()));
		}
		int result = smbmsUserService.saveUser(user);
		if(result>0){
			return "redirect:/user/userList";
		}
		return "jsp/useradd";
	}
	
	@ResponseBody
	@RequestMapping(value="topwdmodify")
	public String topwdmodify(String oldpassword,HttpSession session){
		SmbmsUser user = (SmbmsUser) session.getAttribute("userSession");
		Map<String,String> map = new HashMap<String,String>();
		if(user == null){
			map.put("result", "sessionerror");
		}else if(oldpassword == null||oldpassword.trim()==""){
			map.put("result", "error");
		}else if(!oldpassword.equals(user.getUserPassword())){
			map.put("result", "false");
		}else{
			map.put("result", "true");
		}
		return JSONArray.toJSONString(map);
	}
	
	@RequestMapping(value="userList")
	public String findUserList(String queryname,Integer queryUserRole,@RequestParam(defaultValue="1")Integer pageIndex,Model model){
		queryname = queryname!=null&&queryname.trim().length()>0?queryname:null;
		queryUserRole = queryUserRole!=null&&queryUserRole>0?queryUserRole:null;
		SmbmsUser user = new SmbmsUser();
		user.setUserName(queryname);
		user.setUserRole(queryUserRole);
		int pageSize = 5;
		int totalCount = smbmsUserService.getTotalCount(user);
		int totalPage = totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
		List<SmbmsUser> list = smbmsUserService.findUserList(user, (pageIndex-1)*pageSize, pageSize);
		model.addAttribute("userList",list);
		model.addAttribute("totalPageCount",totalPage);
		model.addAttribute("totalCount",totalCount);
		model.addAttribute("currentPageNo",pageIndex);
		model.addAttribute("queryUserName",queryname);
		model.addAttribute("queryUserRole",queryUserRole);
		List<SmbmsRole> roleList = smbmsRoleService.findRoleList();
		model.addAttribute("roleList",roleList);
		return "jsp/userlist";
	}
	
	@ResponseBody
	@RequestMapping(value="checkUser",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public SmbmsUser userIsExist(String userCode){
		SmbmsUser user = new SmbmsUser();
		user.setUserCode(userCode);
		SmbmsUser exist = smbmsUserService.findUser(user);
		return exist;
	}
	
	@RequestMapping(value="getUserInfo")
	public String getUserInfo(String method,Integer uid,Model model){
		SmbmsUser user = new SmbmsUser();
		user.setId(uid);
		user = smbmsUserService.findUser(user);
		model.addAttribute("user",user);
		if("modify".equals(method)){
			return "jsp/usermodify";
		}
		return "jsp/userview";
	}
	
	@RequestMapping(value="changeUserInfo")
	public String changeUserInfo(String method,SmbmsUser user,Model model,HttpSession session){
		SmbmsUser logined = (SmbmsUser) session.getAttribute("userSession");
		if(logined!=null){
			user.setModifyBy(logined.getId().longValue());
			user.setModifyDate(new Timestamp(System.currentTimeMillis()));
		}
		int result = smbmsUserService.updateUser(user);
		if(result>0){
			return "redirect:/user/userList";
		}
		model.addAttribute("user",user);
		return "jsp/usermodify";
	}
	
	@ResponseBody
	@RequestMapping(value="deleteUser",method=RequestMethod.GET)
	public String deleteUser(String method,SmbmsUser user,Model model){
		int result = smbmsUserService.deleteUser(user);
		String delResult = JSON.toJSONString("false");
		if(result>0){
			delResult = JSON.toJSONString("true");
		}
		return delResult;
	}
	
	@RequestMapping(value="pwdModify")
	public String pwdModify(String method,String newpassword,Model model,HttpSession session){
		SmbmsUser user = (SmbmsUser) session.getAttribute("userSession");
		user.setUserPassword(newpassword);
		if(smbmsUserService.updateUser(user)>0){
			session.removeAttribute("userSession");
			return "login";
		}
		return "jsp/pwdmodify";
	}
}
