package com.smbms.entity;

import java.sql.Timestamp;

/**
 * SmbmsProvider entity. @author MyEclipse Persistence Tools
 */

public class SmbmsProvider implements java.io.Serializable {

	// Fields

	private Integer id;
	private String proCode;
	private String proName;
	private String proDesc;
	private String proContact;
	private String proPhone;
	private String proAddress;
	private String proFax;
	private Long createdBy;
	private Timestamp creationDate;
	private Timestamp modifyDate;
	private Long modifyBy;
	private String license;
	private String certificate;

	// Constructors

	/** default constructor */
	public SmbmsProvider() {
	}

	/** full constructor */
	
	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public SmbmsProvider(Integer id, String proCode, String proName, String proDesc, String proContact, String proPhone,
			String proAddress, String proFax, Long createdBy, Timestamp creationDate, Timestamp modifyDate,
			Long modifyBy, String license, String certificate) {
		super();
		this.id = id;
		this.proCode = proCode;
		this.proName = proName;
		this.proDesc = proDesc;
		this.proContact = proContact;
		this.proPhone = proPhone;
		this.proAddress = proAddress;
		this.proFax = proFax;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.modifyDate = modifyDate;
		this.modifyBy = modifyBy;
		this.license = license;
		this.certificate = certificate;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProCode() {
		return this.proCode;
	}

	public void setProCode(String proCode) {
		this.proCode = proCode;
	}

	public String getProName() {
		return this.proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getProDesc() {
		return this.proDesc;
	}

	public void setProDesc(String proDesc) {
		this.proDesc = proDesc;
	}

	public String getProContact() {
		return this.proContact;
	}

	public void setProContact(String proContact) {
		this.proContact = proContact;
	}

	public String getProPhone() {
		return this.proPhone;
	}

	public void setProPhone(String proPhone) {
		this.proPhone = proPhone;
	}

	public String getProAddress() {
		return this.proAddress;
	}

	public void setProAddress(String proAddress) {
		this.proAddress = proAddress;
	}

	public String getProFax() {
		return this.proFax;
	}

	public void setProFax(String proFax) {
		this.proFax = proFax;
	}

	public Long getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	public Timestamp getModifyDate() {
		return this.modifyDate;
	}

	public void setModifyDate(Timestamp modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Long getModifyBy() {
		return this.modifyBy;
	}

	public void setModifyBy(Long modifyBy) {
		this.modifyBy = modifyBy;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	@Override
	public String toString() {
		return "SmbmsProvider [id=" + id + ", proCode=" + proCode + ", proName=" + proName + ", proDesc=" + proDesc
				+ ", proContact=" + proContact + ", proPhone=" + proPhone + ", proAddress=" + proAddress + ", proFax="
				+ proFax + ", createdBy=" + createdBy + ", creationDate=" + creationDate + ", modifyDate=" + modifyDate
				+ ", modifyBy=" + modifyBy + ", license=" + license + ", certificate=" + certificate + "]";
	}

}