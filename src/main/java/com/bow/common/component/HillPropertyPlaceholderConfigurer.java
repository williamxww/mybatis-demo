package com.bow.common.component;

import com.bow.utils.EncryptUtils;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * 用于密码解析 这样干就把所有的property为password的值更改了
 * 
 * @author ViVi
 * @date 2015年9月12日 上午10:43:24
 */

@Deprecated
public class HillPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

	@Override
	public String convertProperty(String propertyName, String propertyValue) {
		String password = "";
		if ("password" == propertyName) {
			// 进行密码解码
			password = EncryptUtils.decodeBase64String(propertyValue);
		}
		return super.convertProperty(propertyName, password);
	}

}
