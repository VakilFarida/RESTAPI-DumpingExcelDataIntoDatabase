package com.example.ExcelApi.web.controller;

import java.util.List;
import java.util.Map;

import com.example.ExcelApi.model.entity.DeveloperEntity;
import com.example.ExcelApi.service.impl.ServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
@CrossOrigin("*")
@RestController
public class DeveloperController {
    @Autowired
    private ServiceImplementation si;
    @PostMapping("/developer/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {
      ServiceImplementation sim=new ServiceImplementation();
        if (sim.checkExcelFormat(file)) 
        {
            si.save(file);
            return ResponseEntity.ok(Map.of("message", "File is uploaded and data is saved to db"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload excel file ");
    }
    @GetMapping("/Developer")
    public List<DeveloperEntity> getAllProduct() {
        return si.getAllDeveloper();
    }
}
