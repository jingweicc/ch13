package com.smbms.service;


import java.util.List;

import com.smbms.entity.SmbmsRole;

public interface SmbmsRoleService {
	int saveRole(SmbmsRole role);
	int deleteRole(SmbmsRole role);
	int updateRole(SmbmsRole role);
	List<SmbmsRole> findRoleList();
	SmbmsRole findRole(SmbmsRole role);
}
