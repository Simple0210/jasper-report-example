package uz.simplecode.jasperreportapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.simplecode.jasperreportapp.entity.Student;
import uz.simplecode.jasperreportapp.payload.StudentDto;
import uz.simplecode.jasperreportapp.service.StudentService;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/students")
@Tag(name = "STUDENTS")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/save")
    @Operation(summary = "Yangi student saqlash")
    public ResponseEntity<String> saveStudent(@RequestBody StudentDto dto) {
        return studentService.saveStudent(dto);
    }

    @GetMapping
    @Operation(summary = "Studentlar listini olish")
    public ResponseEntity<List<Student>> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/export-report/{format}")
    @Operation(summary = "Student report generatsiya qilish")
    public ResponseEntity<String> exportFile(@PathVariable String format) throws FileNotFoundException, JRException {
        return studentService.exportFile(format);

    }

}
