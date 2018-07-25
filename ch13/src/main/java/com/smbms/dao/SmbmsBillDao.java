package com.smbms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.smbms.entity.SmbmsBill;

public interface SmbmsBillDao {
	int saveBill(SmbmsBill bill);
	int deleteBill(SmbmsBill bill);
	int updateBill(SmbmsBill bill);
	List<SmbmsBill> findBillList(@Param("bill") SmbmsBill bill,@Param("firstResult") int firstResult,@Param("maxResults") int maxResults);
	SmbmsBill findBill(@Param("bill")SmbmsBill bill);
	int billTotalCount(@Param("bill")SmbmsBill bill);
}
