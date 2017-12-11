package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entity.Userfile;

@Repository
public interface UserfileRepository extends CrudRepository<Userfile, Long> {
    List<Userfile> findAll();
    
    @Transactional
    @Modifying
    @Query("delete from Userfile u where u.fileID = ?1")
    void deleteFile(Integer fileid);
    
    @Transactional
    @Modifying
    @Query("update Userfile u set u.starred = ?1 where u.fileID = ?2")
    void updateStar(Integer star,Integer fileid);
}
