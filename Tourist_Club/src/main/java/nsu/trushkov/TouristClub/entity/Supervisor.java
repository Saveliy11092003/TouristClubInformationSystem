package nsu.trushkov.TouristClub.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.Year;

@Entity
@Table(name = "supervisors")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Supervisor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_supervisor")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "age")
    private String age;

    @Column(name = "salary")
    private String salary;

    @Column(name = "date_of_entry")
    private String dateOfEntry;

    @Column(name = "date_of_birth")
    private String  dateOfBirth;

    @ToString.Exclude
    @OneToOne(mappedBy = "supervisor")
    private Section section;

}
