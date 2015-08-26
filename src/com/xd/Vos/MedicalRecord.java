package com.xd.Vos;

import java.util.ArrayList;

public class MedicalRecord {
	private String medicalID;
	private String date;
	private String disease;
	private String remarks;
	private ArrayList<ImageVo> imageList;
	public String getMedicalID() {
		return medicalID;
	}
	public void setMedicalID(String medicalID) {
		this.medicalID = medicalID;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDisease() {
		return disease;
	}
	public void setDisease(String disease) {
		this.disease = disease;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public ArrayList<ImageVo> getImageList() {
		return imageList;
	}
	public void setImageList(ArrayList<ImageVo> imageList) {
		this.imageList = imageList;
	}
}
