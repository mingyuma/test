package com.xd.util;


import com.xd.Vos.Patient;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * �洢�û�������Ϣ,�����˺š����롢�û�id���û�����
 * @author ??

 *
 */

public class SharePreferenceUtil {
	private SharedPreferences sp;
	private SharedPreferences.Editor editor;

	public SharePreferenceUtil(Context context, String file) {
		sp = context.getSharedPreferences(file, Context.MODE_PRIVATE);
		editor = sp.edit();
	}
	// �û����˺�
	public void setAccount(String account)
	{
		editor.putString("account", account);
		editor.commit();
	}
	public String getAccount()
	{
		return sp.getString("account", "");
	}
	
	// �û�������
	public void setPassword(String password) {
		editor.putString("password", password);
		editor.commit();
	}
	public String getPassword() {
		return sp.getString("password", "");
	}

		
	// �û�������
	public String getUserName() {
		return sp.getString("username", "");
	}
	public void setUserName(String name) {
		editor.putString("username", name);
		editor.commit();
	}
	//�Ա�
	public String getSex() {
		return sp.getString("Sex", "");
	}
	public void setSex(String name) {
		editor.putString("Sex", name);
		editor.commit();
	}
	//�Ա�
	public String getBirth() {
		return sp.getString("Birth", "");
	}
	public void setBirth(String name) {
		editor.putString("Birth", name);
		editor.commit();
	}
	//�绰
	public String getMoblie() {
		return sp.getString("Moblie", "");
	}
	public void setMoblie(String name) {
		editor.putString("Moblie", name);
		editor.commit();
	}
	//��ַ
	public String getAddress() {
		return sp.getString("Address", "");
	}
	public void setAddress(String name) {
		editor.putString("Address", name);
		editor.commit();
	}
	// ͷ��URL
	public String getPhotoUrl() {
		return sp.getString("photo", "");
	}

	public void setPhotoUrl(String url) {
		editor.putString("photo", url);
		editor.commit();
	}
	/**
	 * ����
	 ***********************************************
	 * */
	// ���˼���
	public String getPatientDisease() {
		return sp.getString("disease", "");
	}
	public void setPatientDisease(String name) {
		editor.putString("disease", name);
		editor.commit();
	}
	/**
	 ***********************************************
	 *ҽ��
	 **/
	//���
	public String getDoctorIntro() {
		return sp.getString("DoctorIntro", "");
	}
	public void setDoctorIntro(String name) {
		editor.putString("DoctorIntro", name);
		editor.commit();
	}
	//ҽԺ
	public String getHospital() {
		return sp.getString("Hospital", "");
	}
	public void setHospital(String name) {
		editor.putString("Hospital", name);
		editor.commit();
	}
	public String getHospitalID() {
		return sp.getString("HospitalID", "");
	}
	public void setHospitalID(String name) {
		editor.putString("HospitalID", name);
		editor.commit();
	}
	//
	public String getDepartment() {
		return sp.getString("Department", "");
	}
	public void setDepartment(String name) {
		editor.putString("Department", name);
		editor.commit();
	}
	public String getDepartmentID() {
		return sp.getString("DepartmentID", "");
	}
	public void setDepartmentID(String name) {
		editor.putString("DepartmentID", name);
		editor.commit();
	}
	public String getGoodAt() {
		return sp.getString("GoodAt", "");
	}
	public void setGoodAt(String name) {
		editor.putString("GoodAt", name);
		editor.commit();
	}
	public String getType() {
		return sp.getString("Type", "");
	}
	public void setType(String name) {
		editor.putString("Type", name);
		editor.commit();
	}
	
	/**
	 * 
	 ***********************************************
	 * */
	// �Ƿ��һ�����б�Ӧ��
	public void setIsFirst(boolean isFirst) {
		editor.putBoolean("isFirst", isFirst);
		editor.commit();
	}

	public boolean getisFirst() {
		return sp.getBoolean("isFirst", true);
	}
	
	
	
	//�Ƿ��¼�ɹ�
	public boolean getLoginSuccess()
	{
		   return sp.getBoolean("loginSuccess", false);
		   //editor.putString("loginSuccess", account);
		   
	}
	public void setLoginSuccess(boolean flag)
	{
		   editor.putBoolean("loginSuccess",flag);
		   editor.commit();
	}
}
