package com.xd.Vos;

import java.io.Serializable;

import android.util.Log;

/**
 * 存储用户登陆后的基本信息,包括用户id、用户名、小孩id、小孩名、小孩所在班级ID、小孩所在班级名称�?�小孩所在幼儿园ID、小孩所在幼儿园名称、个性签名�?�头像URL
 * @author ??
 * @modefied by Bryan 2013-8-22
 *
 */

public class UserVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//服务器返回码�?
	private String resultCode;
	//服务器错误信�?
	private String errDescribe;
	
	//用户验证�?
	private String cookie;
	//家长ID
	private String userId;
	//家长名字
	private String userName;
	//登录账号
	private String account;
	
	//小孩的个性签�?
	private String status;
	//小孩的头像url
	private String photoUrl;


	
	
	
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getErrDescribe() {
		return errDescribe;
	}
	public void setErrDescribe(String errDescribe) {
		this.errDescribe = errDescribe;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getCookie() {
		return cookie;
	}
	public void setCookie(String cookie) {
		this.cookie = cookie;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	
	
}
