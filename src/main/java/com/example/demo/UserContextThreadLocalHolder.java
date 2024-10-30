package com.example.demo;


import com.example.demo.vo.UserInfo;

/**
 * 
 * @author Leo.LIANG
 *
 */
public class UserContextThreadLocalHolder {

	private static final ThreadLocal<UserInfo> userThreadLocal = new ThreadLocal<UserInfo>();
	
	public static UserInfo getCurrentUser(){
		return userThreadLocal.get();
	}
	
	public static void setCurrentUser(UserInfo user){
		userThreadLocal.set(user);
	}
	
	public static void removeCurrentUser(){
		userThreadLocal.remove();
	}
	
	public static String getCurrentUserName(){
		UserInfo u = getCurrentUser();
		return u!=null? u.getId() : null;
	}

	public static String getCurrentUserId(){
		UserInfo u = getCurrentUser();
		return u!=null? u.getId() : null;
	}

}
