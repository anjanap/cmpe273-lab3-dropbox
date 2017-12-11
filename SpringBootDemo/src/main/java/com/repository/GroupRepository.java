package com.repository;

import java.util.List;


import org.springframework.data.repository.CrudRepository;
import com.entity.Groups;

public interface GroupRepository extends CrudRepository<Groups, Long> {
    List<Groups> findAll();
}
