package com.shiber.gezu;

import java.io.Serializable;

public class StoreResult implements Serializable{
	private int code;
	private String msg;
	private Object data;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public StoreResult(int code, String msg, Object data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	public StoreResult(Object data) {
		super();
		this.code = 200;
		this.msg = "yoshi";
		this.data = data;
	}
	public StoreResult() {
		this.code = 200;
		this.msg = "yoshi";
	}
	public StoreResult(int code,String msg) {
		this.code = code;
		this.msg = msg;
	}
	@Override
	public String toString() {
		return "StoreResult [code=" + code + ", msg=" + msg + ", data=" + data + "]";
	}
	public static StoreResult build(int code,String msg,Object data) {
		return new StoreResult(code, msg, data);
	}
	public static StoreResult yoshi() {
		return new StoreResult();
	}
	public static StoreResult yoshi(Object data) {
		return new StoreResult(data);
	}
	public static StoreResult yoshi(int code,String msg){
		return new StoreResult(code,msg);
    }
}
