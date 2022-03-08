package com.example.ExcelApi.service;
import java.io.InputStream;
import java.util.List;
import com.example.ExcelApi.model.entity.DeveloperEntity;
import org.springframework.web.multipart.MultipartFile;
public interface DeveloperService 
{
    //=================================
    public  boolean checkExcelFormat(MultipartFile file);
    public  List<DeveloperEntity> convertExcelToListOfProduct(InputStream is);
    public void save(MultipartFile file);
    public List<DeveloperEntity> getAllDeveloper();
}
