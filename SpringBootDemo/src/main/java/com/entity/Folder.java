package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Folder {
	
		@Id
	    @GeneratedValue(strategy=GenerationType.AUTO)
	    private Integer folderID;
		
		@Column(name="userID")
	    private Integer userID;
		
		@Column(name="folder_name")
	    private String folder_name;
	    
		public Integer getFolderID() {
			return folderID;
		}
		public void setFolderID(Integer folderID) {
			this.folderID = folderID;
		}
		public Integer getUserID() {
			return userID;
		}
		public void setUserID(Integer userID) {
			this.userID = userID;
		}
		public String getFolderName() {
			return folder_name;
		}
		public void setFolderName(String folderName) {
			this.folder_name = folderName;
		}
   
}
