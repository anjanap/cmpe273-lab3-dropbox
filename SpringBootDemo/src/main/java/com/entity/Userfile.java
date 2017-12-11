package com.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Userfile {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer fileID;
    
    private Integer userID;
    private String filename;
    private Integer mainfolder;
    private Integer folderID;
    private Integer starred;
    
    public Userfile() {
    	this.userID=0;
    	this.filename=null;
    	this.mainfolder=null;
    	this.folderID=null;
    this.starred=null;
    }
    
    public Userfile(Integer uid, String name, Integer mfolder, Integer fid, Integer star) {
    	this.userID=uid;
    	this.filename=name;
    	this.mainfolder=mfolder;
    	this.folderID=fid;
    this.starred=star;
    }
    
	public Integer getFileID() {
		return fileID;
	}
	public void setFileID(Integer fileID) {
		this.fileID = fileID;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public Integer getMainfolder() {
		return mainfolder;
	}
	public void setMainfolder(Integer mainfolder) {
		this.mainfolder = mainfolder;
	}
	public Integer getFolderID() {
		return folderID;
	}
	public void setFolderID(Integer folderID) {
		this.folderID = folderID;
	}
	public Integer getStarred() {
		return starred;
	}
	public void setStarred(Integer starred) {
		this.starred = starred;
	}

}
