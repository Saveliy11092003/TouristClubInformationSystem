package nsu.trushkov.TouristClub.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "planned_camping")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Slf4j
@Builder
public class PlannedCamping {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "diary")
    private String diary;

    @ManyToOne
    @JoinColumn(name = "id_plan")
    private Plan plan;

    @ManyToOne
    @JoinColumn(name = "id_instructor")
    private Tourist instructor;

    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name="planned_camping_tourist",
            joinColumns=  @JoinColumn(name="id_planned_camping", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="id_tourist", referencedColumnName="id_tourist"))
    List<Tourist> tourists = new ArrayList<>();


    public void addTourist(Tourist tourist) {
        if (tourists == null) {
            tourists = new ArrayList<>();
        }
        tourists.add(tourist);
        if (tourist.getPlannedCampings() == null) {
            tourist.setPlannedCampings(new ArrayList<>());
        }
    }

    public void removeTourist(Tourist tourist) {
        tourists.remove(tourist);
        tourist.getPlannedCampings().remove(this);
    }

    public void removeTourists() {
        for (Tourist tourist : tourists) {
            removeTourist(tourist);
        }
    }


}
