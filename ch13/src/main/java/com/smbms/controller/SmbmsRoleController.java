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
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.smbms.entity.SmbmsRole;
import com.smbms.entity.SmbmsUser;
import com.smbms.service.SmbmsRoleService;
import com.smbms.service.SmbmsUserService;

@Controller
@RequestMapping(value="role")
public class SmbmsRoleController {
	@Autowired
	private SmbmsRoleService smbmsRoleService;
	@Autowired
	private SmbmsUserService smbmsUserService;
	
	@RequestMapping(value="findRoleList")
	public String findRoleList(Model model){
		List<SmbmsRole> list = smbmsRoleService.findRoleList();
		model.addAttribute("roleList",list);
		return "jsp/rolelist";
	}
	
	 @ResponseBody
	@RequestMapping(value="getRoleList")
	public List<SmbmsRole> getRoleList(){
		List<SmbmsRole> roleList = smbmsRoleService.findRoleList();
		return roleList;
	}
	 
	 @RequestMapping(value="toModifyRole")
	public String toModifyRole(SmbmsRole role,String method,Model model){
		role = smbmsRoleService.findRole(role);
		model.addAttribute("role",role);
		if("modify".equals(method)){
			return "jsp/rolemodify";
		}
		return "jsp/roleview";
	}
	 
	 @ResponseBody
	 @RequestMapping(value="delRole")
		public String delRole(SmbmsRole role,String method,Model model){
			Map<String,String> map = new HashMap<String,String>();
			SmbmsUser user = new SmbmsUser();
			user.setUserRole(role.getId());
			if(smbmsUserService.findUserList(user, null, null).size()>0){
				map.put("result", "more");
			}else if(smbmsRoleService.findRole(role)==null){
				map.put("result", "notexist");
			}else if(smbmsRoleService.deleteRole(role)>0){
				map.put("result", "true");
			}else{
				map.put("result", "false");
			}
			return JSONArray.toJSONString(map);
		}
	 
	 @RequestMapping(value="addRole")
		public String addRole(SmbmsRole role,String method,Model model,HttpSession session){
		 SmbmsUser user = (SmbmsUser) session.getAttribute("userSession");
		 	if(user!=null){
		 		role.setCreatedBy(user.getId().longValue());
		 		role.setCreationDate(new Timestamp(System.currentTimeMillis()));
		 	}
			if(smbmsRoleService.saveRole(role)>0){
				return "redirect:findRoleList";
			}
			return "jsp/roleadd";
		}
	 
	 @RequestMapping(value="modifyRole")
		public String modifyRole(SmbmsRole role,String method,Model model,HttpSession session){
		 	SmbmsUser user = (SmbmsUser) session.getAttribute("userSession");
		 	if(user!=null){
		 		role.setModifyBy(user.getId().longValue());
		 		role.setModifyDate(new Timestamp(System.currentTimeMillis()));
		 	}
			if(smbmsRoleService.updateRole(role)>0){
				return "redirect:findRoleList";
			}
			return "jsp/rolemodify";
		}
	 
	 @ResponseBody
	 @RequestMapping(value="findRole")
		public String findRole(SmbmsRole role,String method,Model model){
			role = smbmsRoleService.findRole(role);
			Map<String,String> map = new HashMap<String,String>();
			if(role!=null){
				map.put("result", "exist");
			}else{
				map.put("result", "true");
			}
			return JSONArray.toJSONString(map);	
		}
}
