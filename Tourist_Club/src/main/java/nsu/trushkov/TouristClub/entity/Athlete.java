package nsu.trushkov.TouristClub.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "athletes")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Athlete {
    @Id
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.PERSIST)
    @MapsId
    @JoinColumn(name = "id")
    private Tourist tourist;

    @Column(name = "year_of_work_experience")
    private Integer yearOfWorkExperience;

    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="competition_athlete",
            joinColumns=  @JoinColumn(name="id_athlete", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="id_competition", referencedColumnName="id"))
    List<Competition> competitions = new ArrayList<>();

    public void addCompetition(Competition competition) {
        competitions.add(competition);
        if (competition.getAthletes() == null) {
            competition.setAthletes(new ArrayList<>());
        }
    }

    public void removeCompetition(Competition competition) {
        competitions.remove(competition);
        competition.getAthletes().remove(this);
    }


}