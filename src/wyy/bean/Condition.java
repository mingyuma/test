package wyy.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.litepal.crud.DataSupport;

import com.xd.Vos.ImageVo;

public class Condition implements Serializable {
	public String getMedicalID() {
		return medicalID;
	}

	public void setMedicalID(String medicalID) {
		this.medicalID = medicalID;
	}

	public ArrayList<ImageVo> getImageList() {
		return imageList;
	}

	public void setImageList(ArrayList<ImageVo> imageList) {
		this.imageList = imageList;
	}

	private String Dignose;
	private String Date;
	private String Remarks;
	private int imageNum;
	private String medicalID;
	private ArrayList<ImageVo> imageList;
	public int getImageNum() {
		return imageNum;
	}

	public void setImageNum(int imageNum) {
		this.imageNum = imageNum;
	}

	public String getDignose() {
		return Dignose;
	}

	public void setDignose(String dignose) {
		Dignose = dignose;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public String getRemarks() {
		return Remarks;
	}

	public void setRemarks(String remarks) {
		Remarks = remarks;
	}

}
