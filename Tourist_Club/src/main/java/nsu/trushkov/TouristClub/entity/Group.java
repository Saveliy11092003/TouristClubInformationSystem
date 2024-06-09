package nsu.trushkov.TouristClub.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "groups")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Slf4j
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_group")
    private Long id;

    @Column(name = "name_group")
    private String nameGroup;


    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne()
    @JoinColumn(name = "id_coach")
    private Coach coach;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL)
    private List<Tourist> tourists = new ArrayList<>();

    @ToString.Exclude
    @ManyToOne()
    @JoinColumn(name = "id_section")
    private Section section;

    public void addTourist(Tourist tourist) {
        tourists.add(tourist);
        tourist.setHasGroup(true);
        tourist.setGroup(this);
    }

    public void removeTourist(Tourist tourist) {
        tourists.remove(tourist);
        tourist.setHasGroup(false);
        tourist.setGroup(null);
    }

    public void addTouristWithCheck(Tourist t) {
        if (tourists.isEmpty()) {
            return;
        }
        for (Tourist tourist: tourists) {
            if(t.equals(tourist)) {
                return;
            }
        }
        tourists.add(t);
        t.setHasGroup(true);
        t.setGroup(this);
    }

    public void removeTouristWithCheck(Tourist tourist) {
        tourists.remove(tourist);
        tourist.setHasGroup(false);
        tourist.setGroup(null);
    }

    public void removeTourists() {
        log.info(tourists.toString());
        for (Tourist tourist : tourists) {
            tourist.setHasGroup(false);
            tourist.setGroup(null);
        }
        tourists.clear();
    }

    public void updateTourists(List<Tourist> newTourists) {

        List<Tourist> firstInsect = new ArrayList<>();

        for (Tourist touristFromClass : tourists) {
            boolean isInClass = false;
            for (Tourist touristFromNew : newTourists) {
                if (touristFromClass.equals(touristFromNew)){
                    isInClass = true;
                    break;
                }
            }
            if (isInClass) {
                firstInsect.add(touristFromClass);
            }
        }

        List<Tourist> secondInsect = new ArrayList<>();

        for (Tourist touristFromNew : newTourists) {
            boolean isInClass = false;
            for (Tourist touristFromInsect : firstInsect) {
                if (touristFromNew.equals(touristFromInsect)){
                    isInClass = true;
                    break;
                }
            }
            if (!isInClass) {
                secondInsect.add(touristFromNew);
                touristFromNew.setGroup(this);
                touristFromNew.setHasGroup(true);
            }
        }

        List<Tourist> remainder = new ArrayList<>();
        for (Tourist tourist : tourists) {
            boolean isInClass = false;
            for (Tourist touristFromInsect : firstInsect) {
                if (tourist.equals(touristFromInsect)){
                    isInClass = true;
                    break;
                }
            }
            if (!isInClass) {
                remainder.add(tourist);
                tourist.setGroup(null);
                tourist.setHasGroup(false);
            }
        }

        log.info("tourist begin " + tourists.toString());
        List<Tourist> result = new ArrayList<>();
        result.addAll(secondInsect);
        result.addAll(firstInsect);
        log.info("result " + result.toString());
        tourists = result;

        //log.info(tourists.toString());

    }

}
