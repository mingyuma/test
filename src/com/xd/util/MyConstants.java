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
	//�豸����
	public static final String DEVICE_TYPE = "Android";
	
	public static final String SERVER_IP = "192.168.1.110";// ������ip
	public static final int SERVER_PORT = 8080;// �������˿�

	public static final int REGISTER_FAIL = 0;//ע��ʧ��
	public static final String ACTION = "com.way.message";//��Ϣ�㲥action
	public static final String MSGKEY = "message";//��Ϣ��key
	public static final String IP_PORT = "ipPort";//����ip��port��xml�ļ���
	public static final String SAVE_USER = "saveUser";//�����û���Ϣ��xml�ļ���
	public static final String BACKKEY_ACTION="com.way.backKey";//���ؼ����͹㲥��action
	public static final int NOTIFY_ID = 0x911;//֪ͨID
	public static final String DBNAME = "qq.db";//���ݿ�����
	
	public static final String INTENT_DIARYVO = "intent_diary_vo";
	public static final String INTENT_ALBUMVO = "intent_album_vo";
	public static final String INTENT_ALBUMVO_INDEX = "intent_album_vo_index";
	public static final String INTENT_ALBUMCONTENTVO = "intent_album_content_vo";
	
	//cookie
	public final static int COOKIE_VERIFY_FAIL=1111;
	public final static String COOKIE_VERIFY_FAIL_RECODE="��¼��֤ʧ��";


	
	public static String account;


}
