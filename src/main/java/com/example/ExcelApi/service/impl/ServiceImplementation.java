package com.example.ExcelApi.service.impl;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.example.ExcelApi.model.entity.DeveloperEntity;
import com.example.ExcelApi.model.pojo.Developer;
import com.example.ExcelApi.model.repository.DeveloperRepository;
import com.example.ExcelApi.service.DeveloperService;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
public class ServiceImplementation implements DeveloperService {
    @Autowired
    private DeveloperRepository  dr;
   // check file format  it is excel or not
    @Override
    public boolean checkExcelFormat(MultipartFile file) {
            String contentType = file.getContentType();
    
            if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
                return true;
            } else {
                return false;
          }
    }
   // convert excel to list
    @Override
    public  List<DeveloperEntity> convertExcelToListOfDeveloper(InputStream is) 
    {
        List<DeveloperEntity> list = new ArrayList<>();
        try{
             
            XSSFWorkbook workbook = new XSSFWorkbook(is);
            XSSFSheet sheet = workbook.getSheet("Sheet1");


            int rowNumber = 0;
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) 
            {
                Row row = iterator.next();
            //skip first row
                if (rowNumber == 0) 
                {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cells = row.iterator();
                int cid = 0;
                DeveloperEntity p = new DeveloperEntity();
                while (cells.hasNext())
                 {
                    Cell cell = cells.next();

                    switch (cid) 
                    {
                        case 0:
                             p.setId((int) cell.getNumericCellValue());
                        break;
                        case 1:
                             p.setFirstName(cell.getStringCellValue());
                        break;
                       case 2:
                             p.setLastName(cell.getStringCellValue());
                        break;
                        case 3:
                             p.setDesignation(cell.getStringCellValue());
                        break;
                        case 4:
                            p.setPhone((long)cell.getNumericCellValue());
                        break;
                      
                        default:
                        break;
                     }
                cid++;
              }
             list.add(p);
             workbook.close();
            }

        } catch (Exception e)
         {
            e.printStackTrace();
         }
        return list;
    }
    @Override
    public void save(MultipartFile file)
     {
     try {
        List<DeveloperEntity> dev=convertExcelToListOfDeveloper(file.getInputStream());
        dr.saveAll(dev);
    } catch (IOException e) {
        e.printStackTrace();
    }
        
    }
    @Override
    public List<DeveloperEntity> getAllDeveloper() {
       return dr.findAll();
    }
  //
    


    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = { "Id", "FirstName", "LastName", "Designation","Phone" };
    static String SHEET = "developers";
    @Override
    public ByteArrayInputStream developersToExcel(List<DeveloperEntity> developers) {
  
      try (Workbook workbook = new XSSFWorkbook(); 
      ByteArrayOutputStream out = new ByteArrayOutputStream();) {
        org.apache.poi.ss.usermodel.Sheet sheet = workbook.createSheet(SHEET);
  
        // Header
        Row headerRow = sheet.createRow(0);
  
        for (int col = 0; col < HEADERs.length; col++) {
          Cell cell = headerRow.createCell(col);
          cell.setCellValue(HEADERs[col]);
        }
  
        int rowIdx = 1;
        for (DeveloperEntity developer : developers) {
          Row row = sheet.createRow(rowIdx++);
  
          row.createCell(0).setCellValue(developer.getId());
          row.createCell(1).setCellValue(developer.getFirstName());
          row.createCell(2).setCellValue(developer.getLastName());
          row.createCell(3).setCellValue(developer.getDesignation());
          row.createCell(4).setCellValue(developer.getPhone());

        }
  
        workbook.write(out);
        return new ByteArrayInputStream(out.toByteArray());
      } catch (IOException e) {
        throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
      }
    }

    public ByteArrayInputStream load() {
        List<DeveloperEntity> developers = dr.findAll();
        ByteArrayInputStream in = developersToExcel(developers);
        return in;
      }
} 


