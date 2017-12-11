package com.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Activity {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer activityID;
    private Integer userID;
    private String status;
    
    public Activity() {
    	userID=null;
    	status=null;
    }
    
    public Activity(Integer uid, String st) {
    	userID=uid;
    	status=st;
    }
    
    public Integer getActivityID() {
		return activityID;
	}
	public void setActivityID(Integer activityID) {
		this.activityID = activityID;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
