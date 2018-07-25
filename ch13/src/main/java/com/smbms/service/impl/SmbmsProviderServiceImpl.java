package com.smbms.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smbms.dao.SmbmsProviderDao;
import com.smbms.entity.SmbmsProvider;
import com.smbms.service.SmbmsProviderService;

@Service
public class SmbmsProviderServiceImpl implements SmbmsProviderService{
	@Autowired
	private SmbmsProviderDao smbmsProviderDao;

	@Override
	public int saveProvider(SmbmsProvider provider) {
		// TODO Auto-generated method stub
		return smbmsProviderDao.saveProvider(provider);
	}

	@Override
	public int deleteProvider(SmbmsProvider provider) {
		// TODO Auto-generated method stub
		return smbmsProviderDao.deleteProvider(provider);
	}

	@Override
	public int updateProvider(SmbmsProvider provider) {
		// TODO Auto-generated method stub
		return smbmsProviderDao.updateProvider(provider);
	}

	@Override
	public List<SmbmsProvider> findProviderList(
			SmbmsProvider provider,
			Integer firstResult,
			Integer maxResults) {
		// TODO Auto-generated method stub
		return smbmsProviderDao.findProviderList(provider, firstResult, maxResults);
	}

	@Override
	public SmbmsProvider findProvider(SmbmsProvider provider) {
		// TODO Auto-generated method stub
		return smbmsProviderDao.findProvider(provider);
	}

	@Override
	public int proTotalCount(SmbmsProvider provider) {
		// TODO Auto-generated method stub
		return smbmsProviderDao.proTotalCount(provider);
	}

}
