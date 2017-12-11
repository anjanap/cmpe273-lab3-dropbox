package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Userfile;
import com.repository.UserfileRepository;

@Service
public class UserfileService {
    @Autowired
    private UserfileRepository userfileRepository;

    public List<Userfile> getAllFiles(){
        return userfileRepository.findAll();
    }

    public void addFile(Userfile userfile){
        userfileRepository.save(userfile);
    }
    
    public void deleteFile(Integer fileid){
        userfileRepository.deleteFile(fileid);
    }
    
    public void updateStar(Integer s,Integer f) {
    	userfileRepository.updateStar(s,f);
    }

}
