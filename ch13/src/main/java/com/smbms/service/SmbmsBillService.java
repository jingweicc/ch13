package com.smbms.service;

import java.util.List;

import com.smbms.entity.SmbmsBill;

public interface SmbmsBillService {
	int saveBill(SmbmsBill bill);
	int deleteBill(SmbmsBill bill);
	int updateBill(SmbmsBill bill);
	List<SmbmsBill> findBillList(SmbmsBill bill,int firstResult,int maxResults);
	SmbmsBill findBill(SmbmsBill bill);
	int billTotalCount(SmbmsBill bill);
}
