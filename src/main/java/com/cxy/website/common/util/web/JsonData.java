package com.cxy.website.common.util.web;

import com.cxy.website.common.constants.ErrorEnum;

import java.io.Serializable;

/**
 * 功能描述：工具类
 *
 * <p> 创建时间：May 14, 2018 7:58:06 PM </p>
 */
public class JsonData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private Integer code; // 状态码 0 表示成功，其他失败
	private Object data; // 数据
    private Integer msgType;//提示信息类型，0 表示不显示，1表示显示
	private String msg;// 描述

	public JsonData() {
	}

	public JsonData(Integer code, Object data, String msg,Integer msgType) {
		this.code = code;
		this.data = data;
		this.msg = msg;
		if (this.msg==null) {
			this.msg="";
		}
		this.msgType=msgType;
	}

	// 成功，传入数据
	public static JsonData buildSuccess() {
		return new JsonData(0, null, null,0);
	}

	// 成功，传入数据
	public static JsonData buildSuccess(Object data) {
		return new JsonData(0, data, null,0);
	}
	// 成功，传入数据,及描述信息
	public static JsonData buildSuccess(Object data, String msg,Integer msgType) {
		return new JsonData(0, data, msg,msgType);
	}


	// 失败，传入描述信息String msg
	public static JsonData buildError(String msg,Integer msgType) {
		return new JsonData(-1, null, msg,msgType);
	}

	// 失败，传入描述信息String msg
	public static JsonData buildError() {
		return new JsonData(ErrorEnum.E_4003.getErrorCode(), null, ErrorEnum.E_4003.getErrorMsg(),ErrorEnum.E_4003.getMsgType());
	}

	// 失败，传入描述信息,状态码
	public static JsonData buildError(String msg, Integer code,Integer msgType) {
		return new JsonData(code, null, msg,msgType);
	}

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    public Integer getCode() {
        return code;
    }

    public void setResultCode(Integer code) {
        this.code = code;
    }

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "JsonData [code=" + code + ", data=" + data + ", msg=" + msg
				+ "]";
	}

}
