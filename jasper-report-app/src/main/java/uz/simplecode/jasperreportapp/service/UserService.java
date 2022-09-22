package uz.simplecode.jasperreportapp.service;

import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ResponseEntity;
import uz.simplecode.jasperreportapp.entity.User;

import java.io.FileNotFoundException;
import java.util.List;

public interface UserService {

    ResponseEntity<List<User>> getAllUser();

    ResponseEntity<String> exportFile(String reportFormat) throws FileNotFoundException, JRException;
}
