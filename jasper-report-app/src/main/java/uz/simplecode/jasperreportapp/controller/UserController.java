package uz.simplecode.jasperreportapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.simplecode.jasperreportapp.entity.User;
import uz.simplecode.jasperreportapp.service.UserService;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity<List<User>> getAllUsers() {
        return userService.getAllUser();
    }

    @GetMapping("/get-report/{reportFormat}")
    @Operation(summary = "Export file", description = "Fileni reportlarni export qilish")
    public ResponseEntity<String> getReport(@PathVariable String reportFormat) throws FileNotFoundException, JRException {
        return userService.exportFile(reportFormat);
    }

}
