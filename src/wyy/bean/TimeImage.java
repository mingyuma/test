package wyy.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.litepal.crud.DataSupport;

public class TimeImage extends DataSupport implements Serializable {
	private String yearMonthDay;
	private String hourMinSec;
	private String bodyLocation;
	private int type;
	private int fenli;
	private int taxian;
	private String binglihao;
	private String doctor;
	private String hospital;
	private String zhiliao;

	public String getZhiliao() {
		return zhiliao;
	}

	public void setZhiliao(String zhiliao) {
		this.zhiliao = zhiliao;
	}

	private List<Folder> folders = new ArrayList<Folder>();

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getFenli() {
		return fenli;
	}

	public void setFenli(int fenli) {
		this.fenli = fenli;
	}

	public int getTaxian() {
		return taxian;
	}

	public void setTaxian(int taxian) {
		this.taxian = taxian;
	}

	public String getYearMonthDay() {
		return yearMonthDay;
	}

	public void setYearMonthDay(String yearMonthDay) {
		this.yearMonthDay = yearMonthDay;
	}

	public String getHourMinSec() {
		return hourMinSec;
	}

	public void setHourMinSec(String hourMinSec) {
		this.hourMinSec = hourMinSec;
	}

	public String getBodyLocation() {
		return bodyLocation;
	}

	public void setBodyLocation(String bodyLocation) {
		this.bodyLocation = bodyLocation;
	}

	public String getBinglihao() {
		return binglihao;
	}

	public void setBinglihao(String binglihao) {
		this.binglihao = binglihao;
	}

	public String getDoctor() {
		return doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public List<Folder> getFolders() {
		return folders;
	}

	public void setFolders(List<Folder> folders) {
		this.folders = folders;
	}

}
