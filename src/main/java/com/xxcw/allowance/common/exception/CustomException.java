package com.xxcw.allowance.common.exception;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 创建时间：2020年10月17日 下午2:54:57 作者：于海洋 说明：自定义异常类
 * 用法：首先在异常类型枚举类中寻找有没有相同类型的异常，如果没有添加异常类型 可以增加异常描述文字 throw new
 * CustormException(CustormExceptionType.PROCEDURE_ERROR).desc(message); 也可以不加描述
 * throw new CustormException(CustormExceptionType.PROCEDURE_ERROR)
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public class CustomException extends RuntimeException {
	//异常描述
	private String desc;
	//异常类型
	private CustomExceptionType custormExceptionType;

	public CustomException(CustomExceptionType custormExceptionType) {
		this.custormExceptionType = custormExceptionType;
	}

	public CustomException desc(String desc) {
		this.desc = desc;
		return this;
	}

	public String getDesc() {
		return desc;
	}

	public String getExceptionType() {
		return custormExceptionType.getExceptionType();
	}
}
