package nsu.trushkov.TouristClub.entity;

import jakarta.persistence.*;
import lombok.*;


import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "competitions")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Competition {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name_competition")
    private String nameCompetition;

    @Column(name = "description")
    private String description;

    @EqualsAndHashCode.Exclude
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="competition_athlete",
            joinColumns=  @JoinColumn(name="id_competition", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="id_athlete", referencedColumnName="id"))
    List<Athlete> athletes = new ArrayList<>();


    public void addAthlete(Athlete athlete) {
        athletes.add(athlete);
        athlete.getCompetitions().add(this);
    }

    public void removeAthlete(Athlete athlete) {
        athletes.remove(athlete);
        athlete.getCompetitions().remove(this);
    }

    public void updateAthletes(List<Athlete> newAthlete) {

        List<Athlete> firstInsect = new ArrayList<>();

        for (Athlete athleteFromClass : athletes) {
            boolean isInClass = false;
            for (Athlete athleteFromNew : newAthlete) {
                if (athleteFromClass.equals(athleteFromNew)){
                    isInClass = true;
                    break;
                }
            }
            if (isInClass) {
                firstInsect.add(athleteFromClass);
            }
        }

        List<Athlete> secondInsect = new ArrayList<>();

        for (Athlete athleteFromNew : newAthlete) {
            boolean isInClass = false;
            for (Athlete athleteFromInsect : firstInsect) {
                if (athleteFromNew.equals(athleteFromInsect)){
                    isInClass = true;
                    break;
                }
            }
            if (!isInClass) {
                secondInsect.add(athleteFromNew);
            }
        }

        List<Athlete> remainder = new ArrayList<>();
        for (Athlete athlete : athletes) {
            boolean isInClass = false;
            for (Athlete athleteFromInsect : firstInsect) {
                if (athlete.equals(athleteFromInsect)){
                    isInClass = true;
                    break;
                }
            }
            if (!isInClass) {
                remainder.add(athlete);
            }
        }

        List<Athlete> result = new ArrayList<>();
        result.addAll(secondInsect);
        result.addAll(firstInsect);
        athletes = result;

    }
}
