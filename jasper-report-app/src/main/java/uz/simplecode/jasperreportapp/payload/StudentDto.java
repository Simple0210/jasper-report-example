package uz.simplecode.jasperreportapp.payload;

import lombok.Data;

@Data
public class StudentDto {
    private String fullName;
    private String groupNumber;
    private String phoneNumber;
    private Double totalScore;
}
