package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Activity;
import com.repository.ActivityRepository;

@Service
public class ActivityService {
    @Autowired
    private ActivityRepository activityRepository;
	
	public void addActivity(Activity act){
		activityRepository.save(act);
    }
	
    public List<Activity> getAllActivity(){
        return activityRepository.findAll();
    }


}
