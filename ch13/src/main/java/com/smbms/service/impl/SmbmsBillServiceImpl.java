package com.smbms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smbms.dao.SmbmsBillDao;
import com.smbms.entity.SmbmsBill;
import com.smbms.service.SmbmsBillService;
@Service
public class SmbmsBillServiceImpl implements SmbmsBillService{
	@Autowired
	private SmbmsBillDao smbmsBillDao;
	
	@Override
	public int saveBill(SmbmsBill bill) {
		// TODO Auto-generated method stub
		return smbmsBillDao.saveBill(bill);
	}

	@Override
	public int deleteBill(SmbmsBill bill) {
		// TODO Auto-generated method stub
		return smbmsBillDao.deleteBill(bill);
	}

	@Override
	public int updateBill(SmbmsBill bill) {
		// TODO Auto-generated method stub
		return smbmsBillDao.updateBill(bill);
	}

	@Override
	public List<SmbmsBill> findBillList(SmbmsBill bill, int firstResult,
			int maxResults) {
		// TODO Auto-generated method stub
		return smbmsBillDao.findBillList(bill, firstResult, maxResults);
	}

	@Override
	public SmbmsBill findBill(SmbmsBill bill) {
		// TODO Auto-generated method stub
		return smbmsBillDao.findBill(bill);
	}

	@Override
	public int billTotalCount(SmbmsBill bill) {
		// TODO Auto-generated method stub
		return smbmsBillDao.billTotalCount(bill);
	}

}
