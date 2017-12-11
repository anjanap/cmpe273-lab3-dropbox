package com.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.entity.Activity;

public interface ActivityRepository extends CrudRepository<Activity, Long> {
	List<Activity> findAll();
}
