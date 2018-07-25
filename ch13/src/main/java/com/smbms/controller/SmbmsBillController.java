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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.smbms.entity.SmbmsBill;
import com.smbms.entity.SmbmsProvider;
import com.smbms.entity.SmbmsUser;
import com.smbms.service.SmbmsBillService;
import com.smbms.service.SmbmsProviderService;

@Controller
@RequestMapping(value="bill")
public class SmbmsBillController {
	@Autowired
	private SmbmsBillService smbmsBillService;
	@Autowired
	private SmbmsProviderService smbmsProviderService;
	
	@RequestMapping(value="findBillList")
	public String findBillList(String queryProductName,Integer queryProviderId,Integer queryIsPayment,@RequestParam(defaultValue="1")Integer pageIndex,Model model){
		queryProductName = queryProductName!=null&&queryProductName.trim().length()>0?queryProductName:null;
		queryProviderId = queryProviderId!=null&&queryProviderId>0?queryProviderId:null;
		queryIsPayment = queryIsPayment!=null&&queryIsPayment>0?queryIsPayment:null;
		SmbmsBill bill = new SmbmsBill();
		bill.setProductName(queryProductName);
		bill.setProviderId(queryProviderId);
		bill.setIsPayment(queryIsPayment);
		int pageSize = 5;
		int totalCount = smbmsBillService.billTotalCount(bill);
		int totalPage = totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
		List<SmbmsBill> list = smbmsBillService.findBillList(bill, (pageIndex-1)*pageSize, pageSize);
		model.addAttribute("billList",list);
		model.addAttribute("totalPageCount",totalPage);
		model.addAttribute("totalCount",totalCount);
		model.addAttribute("currentPageNo",pageIndex);
		model.addAttribute("queryProductName",queryProductName);
		model.addAttribute("queryProviderId",queryProviderId);
		model.addAttribute("queryIsPayment",queryIsPayment);
		List<SmbmsProvider> proList = smbmsProviderService.findProviderList(new SmbmsProvider(), null, null);
		model.addAttribute("providerList",proList);
		return "jsp/billlist";
	}
	
	@ResponseBody
	@RequestMapping(value="delBill")
	public Object delBill(SmbmsBill bill){
		Map<String,String> map = new HashMap<String,String>();
		if(smbmsBillService.findBill(bill) == null){
			map.put("delResult", "notexist");
		} else if(smbmsBillService.deleteBill(bill)>0){
			map.put("delResult", "true");
		}else{
			map.put("delResult", "false");
		}
		return JSONArray.toJSONString(map);
	}
	
	@RequestMapping(value="toModifyBill")
	public String toModifyBill(SmbmsBill bill,String method,Model model){
		bill = smbmsBillService.findBill(bill);
		model.addAttribute("bill",bill);
		if("modify".equals(method)){
			return "jsp/billmodify";
		}
		return "jsp/billview";
	}
	
	@RequestMapping(value="modifyBill")
	public String modifyBill(SmbmsBill bill,Model model,HttpSession session){
		SmbmsUser user = (SmbmsUser) session.getAttribute("userSession");
		if(user!=null){
			bill.setModifyBy(user.getId().longValue());
			bill.setModifyDate(new Timestamp(System.currentTimeMillis()));
		}
		if(smbmsBillService.updateBill(bill)>0){
			return "redirect:findBillList";
		}
		model.addAttribute("bill",bill);
		return "jsp/billmodify";
	}
	
	@RequestMapping(value="addBill")
	public String addBill(SmbmsBill bill,HttpSession session){
		SmbmsUser user = (SmbmsUser) session.getAttribute("userSession");
		if(user!=null){
			bill.setCreatedBy(user.getId().longValue());
			bill.setCreationDate(new Timestamp(System.currentTimeMillis()));
		}
		if(smbmsBillService.saveBill(bill)>0){
			return "redirect:findBillList";
		}
		return "jsp/billadd";
	}
}
