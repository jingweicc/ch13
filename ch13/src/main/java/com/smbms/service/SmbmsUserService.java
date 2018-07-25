package com.smbms.service;

import java.util.List;

import com.smbms.entity.SmbmsUser;

public interface SmbmsUserService {
	int saveUser(SmbmsUser user);
	int deleteUser(SmbmsUser user);
	int updateUser(SmbmsUser user);
	List<SmbmsUser> findUserList(SmbmsUser user,Integer firstResult,Integer maxResults);
	SmbmsUser findUser(SmbmsUser user);
	SmbmsUser login(SmbmsUser user);
	int getTotalCount(SmbmsUser user);
}
