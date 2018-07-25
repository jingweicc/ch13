package com.smbms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smbms.dao.SmbmsUserDao;
import com.smbms.entity.SmbmsUser;
import com.smbms.service.SmbmsUserService;
@Service
public class SmbmsUserServiceImpl implements SmbmsUserService {
	@Autowired
	private SmbmsUserDao smbmsUserDao;

	@Override
	public int saveUser(SmbmsUser user) {
		// TODO Auto-generated method stub
		return smbmsUserDao.saveUser(user);
	}

	@Override
	public int deleteUser(SmbmsUser user) {
		// TODO Auto-generated method stub
		return smbmsUserDao.deleteUser(user);
	}

	@Override
	public int updateUser(SmbmsUser user) {
		// TODO Auto-generated method stub
		return smbmsUserDao.updateUser(user);
	}

	@Override
	public List<SmbmsUser> findUserList(SmbmsUser user,Integer firstResult,Integer maxResults) {
		// TODO Auto-generated method stub
		return smbmsUserDao.findUserList(user, firstResult, maxResults);
	}

	@Override
	public SmbmsUser login(SmbmsUser user) {
		// TODO Auto-generated method stub
		return smbmsUserDao.login(user);
	}

	@Override
	public int getTotalCount(SmbmsUser user) {
		// TODO Auto-generated method stub
		return smbmsUserDao.totalCount(user);
	}

	@Override
	public SmbmsUser findUser(SmbmsUser user) {
		// TODO Auto-generated method stub
		return smbmsUserDao.findUser(user);
	}
}
