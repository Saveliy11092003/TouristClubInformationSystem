package nsu.trushkov.TouristClub.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import nsu.trushkov.TouristClub.entity.enums.TypeOfCamping;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "plans")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Slf4j
@Builder
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "planName")
    private String planName;

    @Column(name = "start_camping")
    private String startCamping;

    @Column(name = "end_camping")
    private String endCamping;

    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_route")
    private Route route;

    @Column(name = "type_of_camping")
    @Enumerated(EnumType.STRING)
    private TypeOfCamping typeOfCamping;

    @OneToMany()
    private List<Tourist> tourists = new ArrayList<>();

}
