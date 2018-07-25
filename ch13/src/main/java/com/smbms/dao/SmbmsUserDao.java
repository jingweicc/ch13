package com.smbms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.smbms.entity.SmbmsUser;

public interface SmbmsUserDao {
	int saveUser(SmbmsUser user);
	int deleteUser(SmbmsUser user);
	int updateUser(SmbmsUser user);
	List<SmbmsUser> findUserList(@Param("user") SmbmsUser user,@Param("firstResult") Integer firstResult,@Param("maxResults") Integer maxResults);
	SmbmsUser findUser(SmbmsUser user);
	SmbmsUser login(SmbmsUser user);
	int totalCount(SmbmsUser user);
}
