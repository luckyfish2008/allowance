package com.xxcw.allowance.common.exception;

/**
 * 创建时间：2020年10月17日 下午3:29:44
 * 作者：于海洋
 * 说明：自定义异常枚举类
 */
public enum CustomExceptionType {
	PROCEDURE_ERROR("存储过程出错"),NULLPOINT_ERROR("空指针异常"),NOLOGIN("未登录");
	private String exceptionType;
	
	CustomExceptionType(String exceptionType){
		 this.exceptionType = exceptionType;
	}
	
	public String getExceptionType() {
		return exceptionType;
	}

	public void setExceptionType(String exceptionType) {
		this.exceptionType = exceptionType;
	}
	
}
