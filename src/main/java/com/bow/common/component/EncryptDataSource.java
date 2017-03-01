package com.bow.common.component;

import org.apache.commons.dbcp.BasicDataSource;

import com.bow.utils.EncryptUtils;

public class EncryptDataSource extends BasicDataSource {

	@Override
	public void setPassword(String password) {
		// 解密后重置
		String newPassword = EncryptUtils.decodeBase64String(password);
		super.setPassword(newPassword);
	}
}
