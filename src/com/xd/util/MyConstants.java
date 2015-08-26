package com.xd.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;


import android.content.Context;
import android.os.Environment;
import android.view.Gravity;
import android.widget.Toast;


public class MyConstants {
	//设备类型
	public static final String DEVICE_TYPE = "Android";
	
	public static final String SERVER_IP = "192.168.1.110";// 服务器ip
	public static final int SERVER_PORT = 8080;// 服务器端口

	public static final int REGISTER_FAIL = 0;//注册失败
	public static final String ACTION = "com.way.message";//消息广播action
	public static final String MSGKEY = "message";//消息的key
	public static final String IP_PORT = "ipPort";//保存ip、port的xml文件名
	public static final String SAVE_USER = "saveUser";//保存用户信息的xml文件名
	public static final String BACKKEY_ACTION="com.way.backKey";//返回键发送广播的action
	public static final int NOTIFY_ID = 0x911;//通知ID
	public static final String DBNAME = "qq.db";//数据库名称
	
	public static final String INTENT_DIARYVO = "intent_diary_vo";
	public static final String INTENT_ALBUMVO = "intent_album_vo";
	public static final String INTENT_ALBUMVO_INDEX = "intent_album_vo_index";
	public static final String INTENT_ALBUMCONTENTVO = "intent_album_content_vo";
	
	//cookie
	public final static int COOKIE_VERIFY_FAIL=1111;
	public final static String COOKIE_VERIFY_FAIL_RECODE="登录验证失败";


	
	public static String account;


}
