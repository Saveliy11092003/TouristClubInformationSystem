package nsu.trushkov.TouristClub.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sections")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_section")
    private Long id;

    @Column(name = "name_section")
    private String name;

    @EqualsAndHashCode.Exclude
    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_supervisor")
    private Supervisor supervisor;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_type_of_section")
    private TypeOfSection typeOfSection;


    @OneToMany()
    private List<Group> groups = new ArrayList<>();


    public void addGroup(Group group) {
        group.setSection(this);
        groups.add(group);
    }

    public void removeGroup(Group group) {
        groups.remove(group);
        group = null;
    }

}
