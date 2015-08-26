package wyy.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.litepal.crud.DataSupport;

import android.util.Log;

public class People extends DataSupport implements Serializable {
	private int id;
	private String name;
	private String sex;
	private String age;
	private String date;
	private String telnum;
	private List<Condition> conditions = new ArrayList<Condition>();
	private List<Folder> folders = new ArrayList<Folder>();
	private String sortLetters; // 显示数据拼音的首字母

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Folder> getFolders() {
		return folders;
	}

	public void setFolders(List<Folder> folders) {
		this.folders = folders;
	}

	public List<Condition> getConditions() {
		return conditions;
	}

	public void setConditions(List<Condition> conditions) {
		this.conditions = conditions;
	}

	public int getAllFolderNum() {
		int num = 0;

		return num;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getSortLetters() {
		return sortLetters;
	}

	public void setSortLetters(String sortLetters) {
		this.sortLetters = sortLetters;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getTelnum() {
		return telnum;
	}

	public void setTelnum(String telnum) {
		this.telnum = telnum;
	}

}
