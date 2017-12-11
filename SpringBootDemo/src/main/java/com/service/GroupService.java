package com.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Groups;
import com.repository.GroupRepository;


@Service
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;
	
	public void addGroup(Groups group){
		groupRepository.save(group);
    }
	
    public List<Groups> getAllGroups(){
        return groupRepository.findAll();
    }

}
