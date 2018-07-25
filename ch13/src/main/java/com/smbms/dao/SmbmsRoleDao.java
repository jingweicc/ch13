package com.smbms.dao;


import java.util.List;

import com.smbms.entity.SmbmsRole;

public interface SmbmsRoleDao {
	int saveRole(SmbmsRole role);
	int deleteRole(SmbmsRole role);
	int updateRole(SmbmsRole role);
	List<SmbmsRole> findRoleList();
	SmbmsRole findRole(SmbmsRole role);
	
}
