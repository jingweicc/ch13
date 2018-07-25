package com.smbms.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.smbms.entity.SmbmsProvider;
import com.smbms.entity.SmbmsUser;
import com.smbms.service.SmbmsProviderService;

@Controller
@RequestMapping(value="provider")
public class SmbmsProviderController {
	@Autowired
	private SmbmsProviderService smbmsProviderService;
	@RequestMapping(value="proList")
	public String findProviderList(String queryProName,String queryProCode,@RequestParam(defaultValue="1")Integer pageIndex,Model model){
		queryProName = queryProName!=null&&queryProName.trim().length()>0?queryProName:null;
		queryProCode = queryProCode!=null&&queryProCode.trim().length()>0?queryProCode:null;
		SmbmsProvider provider = new SmbmsProvider();
		provider.setProName(queryProName);
		provider.setProCode(queryProCode);
		int pageSize = 5;
		int totalCount = smbmsProviderService.proTotalCount(provider);
		int totalPage = totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
		List<SmbmsProvider> list = smbmsProviderService.findProviderList(provider, (pageIndex-1)*pageSize, pageSize);
		model.addAttribute("providerList",list);
		model.addAttribute("totalPageCount",totalPage);
		model.addAttribute("totalCount",totalCount);
		model.addAttribute("currentPageNo",pageIndex);
		model.addAttribute("queryProName",queryProName);
		model.addAttribute("queryProCode",queryProCode);
		return "jsp/providerlist";
	}
	
	@RequestMapping(value="getProviderInfo")
	public String getProviderInfo(String method,Integer proid,Model model){
		SmbmsProvider provider = new SmbmsProvider();
		provider.setId(proid);
		provider = smbmsProviderService.findProvider(provider);
		model.addAttribute("provider",provider);
		if("modify".equals(method)){
			return "jsp/providermodify";
		}
		return "jsp/providerview";
	}
	
	@RequestMapping(value="addProvider")
	public String addProvider(String method,SmbmsProvider provider,@RequestParam(value="photos") MultipartFile[] photos,Model model,HttpSession session){
		String path = session.getServletContext().getRealPath("photos");
		if(photos!=null){
			provider.setLicense(upload(photos[0],path));
			provider.setCertificate(upload(photos[1],path));
		}
		SmbmsUser user = (SmbmsUser) session.getAttribute("userSession");
		if(user!=null){
			provider.setCreatedBy(user.getId().longValue());
			provider.setCreationDate(new Timestamp(System.currentTimeMillis()));
		}
		int result = smbmsProviderService.saveProvider(provider);
		if(result>0){
			return "redirect:/provider/proList";
		}
		model.addAttribute("provider",provider);
		return "jsp/provideradd";
	}
	
	public String upload(MultipartFile photo,String path){
		if (!photo.isEmpty()) {
			Long time = System.currentTimeMillis();
			int rand = (int) (Math.random() * 9000) + 1000;
			String fileName = time + "" + rand + photo.getOriginalFilename();
			File file = new File(path, fileName);
			try {
				photo.transferTo(file);
				return File.separator + "photos" + File.separator + fileName;
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		return null;
	}
	
	
	@RequestMapping(value="changeProviderInfo")
	public String changeProviderInfo(String method,SmbmsProvider provider,@RequestParam(value="photos") MultipartFile[] photos,Model model,HttpSession session){
		String path = session.getServletContext().getRealPath("photos");
		if(photos!=null){
			provider.setLicense(upload(photos[0],path));
			provider.setCertificate(upload(photos[1],path));
		}
		SmbmsUser user = (SmbmsUser) session.getAttribute("userSession");
		if(user!=null){
			provider.setModifyBy(user.getId().longValue());
			provider.setModifyDate(new Timestamp(System.currentTimeMillis()));
		}
		int result = smbmsProviderService.updateProvider(provider);
		if(result>0){
			return "redirect:/provider/proList";
		}
		model.addAttribute("provider",provider);
		return "jsp/providermodify";
	}
	
	@ResponseBody
	@RequestMapping(value="delProvider")
	public String deleteProviderInfo(String method,SmbmsProvider provider,Model model){
		String delResult = JSON.toJSONString("false");
		int result = smbmsProviderService.deleteProvider(provider);
		HashMap<String,String> resultMap = new HashMap<String,String>();
		if(result>0){
			delResult = JSON.toJSONString("true");
		}
		return delResult;
	}
	
	@ResponseBody
	@RequestMapping(value="getProviderList")
	public String getProviderList(String method){
		List<SmbmsProvider> list = smbmsProviderService.findProviderList(new SmbmsProvider(),null,null);
		return JSONArray.toJSONString(list);
	}
}
