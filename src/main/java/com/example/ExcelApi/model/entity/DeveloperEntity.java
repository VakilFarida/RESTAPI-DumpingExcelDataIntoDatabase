package com.example.ExcelApi.model.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeveloperEntity {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private  String FirstName;
    private String LastName;
    private String Designation;
    private Long Phone;
}
