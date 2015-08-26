package wyy.bean;

import java.io.Serializable;

import org.litepal.crud.DataSupport;

public class Folder extends DataSupport implements Serializable {
	private String folderDescripe;
	private String imagePath;
	private String bodyLoction;
	private String type;

	public String getBodyLoction() {
		return bodyLoction;
	}

	public void setBodyLoction(String bodyLoction) {
		this.bodyLoction = bodyLoction;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFolderDescripe() {
		return folderDescripe;
	}

	public void setFolderDescripe(String folderDescripe) {
		this.folderDescripe = folderDescripe;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

}
