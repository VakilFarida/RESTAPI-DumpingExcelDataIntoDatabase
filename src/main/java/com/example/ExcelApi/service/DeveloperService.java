package com.example.ExcelApi.service;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import com.example.ExcelApi.model.entity.DeveloperEntity;
import com.example.ExcelApi.model.pojo.Developer;

import org.springframework.web.multipart.MultipartFile;
public interface DeveloperService 
{
    //=================================
    public  boolean checkExcelFormat(MultipartFile file);
    public  List<DeveloperEntity> convertExcelToListOfDeveloper(InputStream is);
    public void save(MultipartFile file);
    public List<DeveloperEntity> getAllDeveloper();
    public ByteArrayInputStream developersToExcel(List<DeveloperEntity> developers);
    
}
