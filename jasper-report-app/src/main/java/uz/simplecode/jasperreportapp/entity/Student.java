package uz.simplecode.jasperreportapp.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity(name = "students")
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "group_number")
    private String groupNumber;
    @Column(name = "phone_number",unique = true)
    private String phoneNumber;
    @Column(name = "total_score")
    private Double totalScore;
}
