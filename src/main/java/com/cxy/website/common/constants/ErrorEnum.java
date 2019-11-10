package com.cxy.website.common.constants;

/**
 * @author: hxy
 * @date: 2017/10/24 10:16
 */
public enum ErrorEnum {
	/*
	 * 错误信息
	 * 4XXX 系统级
	 * 5XXX 用户操作
	 * */
	E_4001(4001, "登陆已过期,请重新登陆",1),
	E_4002(4002, "缺少必填参数",1),
	E_4003(4003, "请求处理异常，请稍后再试",1),
	E_4004(4004, "请求方式有误,请检查 GET/POST",1),
	E_4005(4005, "请求路径不存在",1),


	E_5001(5001, "权限不足",1),
	E_5002(5002, "账户已存在",1),
	E_5003(5003, "角色删除失败,尚有用户属于此角色",1);


	private Integer errorCode;

	private String errorMsg;

	private Integer msgType;

	ErrorEnum(Integer errorCode, String errorMsg,Integer msgType) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
		this.msgType=msgType;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public Integer getMsgType() {
		return msgType;
	}


}