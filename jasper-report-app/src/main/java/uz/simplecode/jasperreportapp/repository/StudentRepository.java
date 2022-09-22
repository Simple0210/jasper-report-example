package uz.simplecode.jasperreportapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.simplecode.jasperreportapp.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

}
