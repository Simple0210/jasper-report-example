package uz.simplecode.jasperreportapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.simplecode.jasperreportapp.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value="SELECT * FROM public.users ORDER BY id ASC", nativeQuery=true)
    List<User> getUsers();
}
