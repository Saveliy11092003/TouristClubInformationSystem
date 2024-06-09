package nsu.trushkov.TouristClub.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import nsu.trushkov.TouristClub.entity.enums.DifficultyOfRoute;
import nsu.trushkov.TouristClub.entity.enums.TouristType;
import nsu.trushkov.TouristClub.services.TouristService;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tourists")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Tourist {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tourist")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "age")
    private String age;

    @Column(name = "sex")
    private String sex;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @Column(name = "difficulty_of_route")
    @Enumerated(EnumType.STRING)
    private DifficultyOfRoute difficultyOfRoute;

    @Column(name = "tourist_type")
    @Enumerated(EnumType.STRING)
    private TouristType touristType;

    @Column(name = "has_group")
    private Boolean hasGroup = false;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_group")
    private Group group;

    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name="planned_camping_tourist",
            joinColumns=  @JoinColumn(name="id_tourist", referencedColumnName="id_tourist"),
            inverseJoinColumns= @JoinColumn(name="id_planned_camping", referencedColumnName="id"))
    List<PlannedCamping> plannedCampings = new ArrayList<>();

}
