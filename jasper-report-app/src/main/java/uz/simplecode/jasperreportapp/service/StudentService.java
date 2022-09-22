package uz.simplecode.jasperreportapp.service;

import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ResponseEntity;
import uz.simplecode.jasperreportapp.entity.Student;
import uz.simplecode.jasperreportapp.payload.StudentDto;

import java.io.FileNotFoundException;
import java.util.List;

public interface StudentService {

    ResponseEntity<String> saveStudent(StudentDto dto);

    ResponseEntity<List<Student>> getAllStudents();

    ResponseEntity<String> exportFile(String format) throws FileNotFoundException, JRException;

}
