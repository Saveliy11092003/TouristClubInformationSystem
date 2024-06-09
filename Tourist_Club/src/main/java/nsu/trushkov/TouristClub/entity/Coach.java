package nsu.trushkov.TouristClub.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nsu.trushkov.TouristClub.entity.enums.TypeOfSport;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "coaches")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Coach {

    @Id
    @Column(name = "id_coach")
    private Long id;

    @OneToOne(cascade = CascadeType.PERSIST)
    @MapsId
    @JoinColumn(name = "id_coach")
    private Tourist tourist;

    @Column(name = "speciality")
    @Enumerated(EnumType.STRING)
    private TypeOfSport speciality;

    @Column(name = "salary")
    private String salary;

    @OneToMany()
    private List<Group> groups = new ArrayList<>();

    public void addGroup(Group group){
        groups.add(group);
        group.setCoach(this);
    }

    public void removeGroup(Group group) {
        groups.remove(group);
        group.setCoach(null);
    }
}
