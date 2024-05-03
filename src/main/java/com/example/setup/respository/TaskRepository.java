package com.example.setup.respository;

import com.example.setup.object.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findByTitleContaining(String title);
    List<Task> findByStatus(Boolean status);
}
