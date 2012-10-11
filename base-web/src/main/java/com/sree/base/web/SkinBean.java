package com.sree.base.web;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

@ManagedBean(name = "skinBean")
@SessionScoped
public class SkinBean implements Serializable {
	private static final long serialVersionUID = 2744605279708632184L;
	private SelectItem[] skinSetItems = { new SelectItem("blueSky"),
			new SelectItem("classic"), new SelectItem("deepMarine"),
			new SelectItem("DEFAULT"), new SelectItem("emeraldTown"),
			new SelectItem("japanCherry"), new SelectItem("NULL"),
			new SelectItem("plain"), new SelectItem("ruby"),
			new SelectItem("wine") };

	private String skin = "blueSky";
	private boolean enableElementsSkinning = true;
	private boolean enableClassesSkinning = false;

	public String getSkin() {
		return this.skin;
	}

	public void setSkin(String skin) {
		this.skin = skin;
	}

	public boolean isEnableElementsSkinning() {
		return this.enableElementsSkinning;
	}

	public void setEnableElementsSkinning(boolean enableElementsSkinning) {
		this.enableElementsSkinning = enableElementsSkinning;
	}

	public boolean isEnableClassesSkinning() {
		return this.enableClassesSkinning;
	}

	public void setEnableClassesSkinning(boolean enableClassesSkinning) {
		this.enableClassesSkinning = enableClassesSkinning;
	}

	public SelectItem[] getSkinSetItems() {
		return this.skinSetItems;
	}
}