package com.juiniot.common.web.propEditor;

import com.juiniot.common.utils.URICoder;
import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;

/**
 * 自定义String属性编辑器,用于字符串转码
 * @author ZHIFEN
 */
public class CustomStringEditor extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if(StringUtils.hasText(text)){
			text =  URICoder.decode(text).trim();
		}
		setValue(text);
	}

	
}
