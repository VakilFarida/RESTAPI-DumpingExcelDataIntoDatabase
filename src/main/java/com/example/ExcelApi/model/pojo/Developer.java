package com.example.ExcelApi.model.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Developer {
    private int id;
    private  String FirstName;
    private String LastName;
    private String Designation;
    private Long Phone;
}
