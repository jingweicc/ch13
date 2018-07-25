package com.smbms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smbms.dao.SmbmsRoleDao;
import com.smbms.dao.SmbmsUserDao;
import com.smbms.entity.SmbmsRole;
import com.smbms.entity.SmbmsUser;
import com.smbms.service.SmbmsRoleService;

@Service
public class SmbmsRoleServiceImpl implements SmbmsRoleService {
	@Autowired
	private SmbmsRoleDao smbmsRoleDao;
	@Autowired
	private SmbmsUserDao smbmsUserDao;

	/*
	 * 删除角色(non-Javadoc)
	 * @see com.smbms.service.SmbmsRoleService#deleteRole(com.smbms.entity.SmbmsRole)
	 */
	@Override
	public int deleteRole(SmbmsRole role) {
		// TODO Auto-generated method stub
		SmbmsUser user = new SmbmsUser();
		user.setUserRole(role.getId());
		return smbmsUserDao.deleteUser(user)+smbmsRoleDao.deleteRole(role);
	}

	@Override
	public int updateRole(SmbmsRole role) {
		// TODO Auto-generated method stub
		return smbmsRoleDao.updateRole(role);
	}

	@Override
	public List<SmbmsRole> findRoleList() {
		// TODO Auto-generated method stub
		return smbmsRoleDao.findRoleList();
	}

	@Override
	public int saveRole(SmbmsRole role) {
		// TODO Auto-generated method stub
		return smbmsRoleDao.saveRole(role);
	}

	@Override
	public SmbmsRole findRole(SmbmsRole role) {
		// TODO Auto-generated method stub
		return smbmsRoleDao.findRole(role);
	}
}
