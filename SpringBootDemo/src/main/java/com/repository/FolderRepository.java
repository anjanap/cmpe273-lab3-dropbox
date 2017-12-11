package com.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.entity.Folder;
import com.entity.User;

public interface FolderRepository extends CrudRepository<Folder, Long> {
    List<Folder> findAll();
}