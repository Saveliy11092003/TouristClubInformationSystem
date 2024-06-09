package nsu.trushkov.TouristClub.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import nsu.trushkov.TouristClub.entity.enums.TypeOfSport;


import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "types_of_section")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(exclude = "sections")
@Builder
public class TypeOfSection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type_of_section")
    private Long id;

    @Column(name = "name_type_of_section")
    private String name;

    @Column(name = "type_of_sport")
    @Enumerated(EnumType.STRING)
    private TypeOfSport typeOfSport;

    @OneToMany(mappedBy = "typeOfSection")
    private List<Section> sections = new ArrayList<>();

    public void addSection(Section section) {
        if (sections == null) {
            sections = new ArrayList<>();
        }
        section.setTypeOfSection(this);
        sections.add(section);
    }

    public void removeSection(Section section) {
        sections.remove(section);
        section = null;
    }

}
