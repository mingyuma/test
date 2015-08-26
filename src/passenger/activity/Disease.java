package passenger.activity;

import java.io.Serializable;

public class Disease implements Serializable {
	String name;
	String nickName;
	String chiefContent;
	String performs;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getChiefContent() {
		return chiefContent;
	}

	public void setChiefContent(String chiefContent) {
		this.chiefContent = chiefContent;
	}

	public String getPerforms() {
		return performs;
	}

	public void setPerforms(String performs) {
		this.performs = performs;
	}

}
