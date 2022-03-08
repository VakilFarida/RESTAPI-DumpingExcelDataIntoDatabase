package com.example.ExcelApi.model.repository;
import com.example.ExcelApi.model.entity.DeveloperEntity;
import org.springframework.data.jpa.repository.JpaRepository;
public interface DeveloperRepository  extends JpaRepository<DeveloperEntity , Integer>{
    
}
