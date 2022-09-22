package uz.simplecode.jasperreportapp.service;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import uz.simplecode.jasperreportapp.entity.Student;
import uz.simplecode.jasperreportapp.payload.StudentDto;
import uz.simplecode.jasperreportapp.repository.StudentRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {

    public static final String BASE_URL = "D:/DEMO-PROJECTS/";

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public ResponseEntity<String> saveStudent(StudentDto dto) {
        try {
            Student student = new Student();
            student.setFullName(dto.getFullName());
            student.setGroupNumber(dto.getGroupNumber());
            student.setPhoneNumber(dto.getPhoneNumber());
            student.setTotalScore(dto.getTotalScore());
            studentRepository.save(student);
            return new ResponseEntity<>("Student ma`lumotlari muvaffaqiyatli saqlandi!", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Xatolik tufayli student ma`lumotlari saqlanmadi!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> exportFile(String format) throws FileNotFoundException, JRException {
        try {
            List<Student> students = studentRepository.findAll();
//            File file = ResourceUtils.getFile("classpath:Students.jrxml");
            File file = ResourceUtils.getFile("src//main//resources//Student.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(students);
            Map<String, Object> params = new HashMap<>();
            params.put("currentDate", new Date());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);

            if (format.equals(".html")) {
                JasperExportManager.exportReportToHtmlFile(jasperPrint, BASE_URL + "Student.html");
                return new ResponseEntity<>("Report saved to " + BASE_URL + "Students.html", HttpStatus.OK);
            } else if (format.equals(".pdf")) {
                JasperExportManager.exportReportToPdfFile(jasperPrint, BASE_URL + "Student.pdf");
                return new ResponseEntity<>("Report saved to " + BASE_URL + "Student.pdf", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Format xato kiritildi!", HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("File export qilinmadi!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
