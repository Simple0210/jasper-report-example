package uz.simplecode.jasperreportapp.service;

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import uz.simplecode.jasperreportapp.entity.User;
import uz.simplecode.jasperreportapp.repository.UserRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    public static final String  BASE_URL="D:/DEMO-PROJECTS/";

    private final UserRepository userRepository;

    @Override
    public ResponseEntity<List<User>> getAllUser() {
        List<User> users = userRepository.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> exportFile(String reportFormat) throws FileNotFoundException, JRException {
        List<User> users = userRepository.getUsers();
        File file = ResourceUtils.getFile("classpath:Blank_A4.jrxml");
        JasperReport report = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(users);
        JasperPrint jasperPrint = JasperFillManager.fillReport(report, null, dataSource);

        if (reportFormat.equalsIgnoreCase(".pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, BASE_URL + "Blank_A4.pdf");
        } else if (reportFormat.equalsIgnoreCase(".html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, BASE_URL + "Blank_A4.html");
        }else if (reportFormat.equalsIgnoreCase(".xlsx")){
            JRXlsxExporter exporter = new JRXlsxExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(BASE_URL + "Blank_A4.xlsx"));
            SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
            configuration.setOnePagePerSheet(true);
            configuration.setRemoveEmptySpaceBetweenColumns(true);
            configuration.setDetectCellType(true);
            exporter.setConfiguration(configuration);
            configuration.setSheetNames(new String[] {"Users report page"});
            exporter.exportReport();
        }
        return new ResponseEntity<>("Report saved in -> D:/DEMO-PROJECTS", HttpStatus.OK);
    }
}
