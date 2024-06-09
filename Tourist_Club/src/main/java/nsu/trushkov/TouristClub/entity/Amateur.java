package nsu.trushkov.TouristClub.entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "amateurs")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Amateur {
    @Id
    @Column(name = "id_amateur")
    private Long id;

    @OneToOne(cascade = CascadeType.PERSIST)
    @MapsId
    @JoinColumn(name = "id_amateur")
    private Tourist tourist;

    @Column(name = "year_of_work_experience")
    private Integer yearOfWorkExperience;

}
