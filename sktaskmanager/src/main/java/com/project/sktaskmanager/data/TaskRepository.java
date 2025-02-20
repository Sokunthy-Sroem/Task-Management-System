package com.project.sktaskmanager.data;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.sktaskmanager.model.Task;
@Repository
public interface TaskRepository extends JpaRepository<Task,Integer>{
	
		List<Task> findById(int id);
		@Query("select s from Task s where lower(s.taskName) like lower(CONCAT('%', :search, '%')) or (s.category) like lower(CONCAT('%', :search, '%'))")
		List<Task> findByNameOrCategory(@Param("search") String search);
		//if you want to use some part of name
		List<Task> findByTaskNameContainingIgnoreCaseOrCategoryContainingIgnoreCase(String taskName, String category);
		

		
}

