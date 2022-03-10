package com.example.ExcelApi.web.controller;

import java.net.http.HttpHeaders;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.example.ExcelApi.model.entity.DeveloperEntity;
import com.example.ExcelApi.service.impl.ServiceImplementation;

import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
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
     // ServiceImplementation sim=new ServiceImplementation();
        if (si.checkExcelFormat(file)) 
        {
            si.save(file);
            return ResponseEntity.ok(Map.of("message", "File is uploaded and data is saved to db"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload excel file ");
    }
    @GetMapping("/Developer")
    public List<DeveloperEntity> getAllDeveloper() {
        return si.getAllDeveloper();
    }

    @GetMapping("/download")
    public ResponseEntity<InputStreamResource> getFile() {
      String filename = "developers.xlsx";
      InputStreamResource file = new InputStreamResource(si.load());
      return ResponseEntity.ok()
          .header(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
          .contentType(org.springframework.http.MediaType.parseMediaType("application/vnd.ms-excel"))
          .body(file);
    }
}
