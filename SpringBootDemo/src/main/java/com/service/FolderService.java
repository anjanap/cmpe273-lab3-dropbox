package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Folder;
import com.entity.User;
import com.repository.FolderRepository;

@Service
public class FolderService {
    @Autowired
    private FolderRepository folderRepository;
	
	public void addFolder(Folder folder){
        folderRepository.save(folder);
    }
	
    public List<Folder> getAllFolders(){
        return folderRepository.findAll();
    }

}
