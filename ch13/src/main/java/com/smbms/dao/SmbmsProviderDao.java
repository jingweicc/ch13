package com.smbms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.smbms.entity.SmbmsProvider;

public interface SmbmsProviderDao {
	int saveProvider(SmbmsProvider provider);
	int deleteProvider(SmbmsProvider provider);
	int updateProvider(SmbmsProvider provider);
	List<SmbmsProvider> findProviderList(@Param("provider") SmbmsProvider provider,@Param("firstResult") Integer firstResult,@Param("maxResults") Integer maxResults);
	SmbmsProvider findProvider(@Param("provider")SmbmsProvider provider);
	int proTotalCount(@Param("provider")SmbmsProvider provider);
}
