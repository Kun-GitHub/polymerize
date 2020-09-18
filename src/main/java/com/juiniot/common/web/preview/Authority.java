package com.juiniot.common.web.preview;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Authority {
	
	/**
	 * 是否需要session限制，默认不需要
	 */
	NeedSession needSession() default NeedSession.NO;
	
	/**
	 * 权限代码，默认不需要权限控制
	 */
	FunAuth authCode() default FunAuth.NO_AUTH;
}
