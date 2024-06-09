package nsu.trushkov.TouristClub.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import nsu.trushkov.TouristClub.entity.enums.DifficultyOfRoute;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "route")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Slf4j
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "route_name")
    private String routeName;

    @Column(name = "route_length")
    private String routeLength;

    @Column(name = "difficulty_of_route")
    @Enumerated(EnumType.STRING)
    private DifficultyOfRoute difficultyOfRoute;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Plan> plans = new ArrayList<>();

    @ToString.Exclude
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name="route_point",
            joinColumns=  @JoinColumn(name="id_route", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="id_point", referencedColumnName="id"))
    List<StoppingPoint> points = new ArrayList<>();

    public void addPoint(StoppingPoint stoppingPoint) {
        points.add(stoppingPoint);
        if (stoppingPoint.getRoutes() == null) {
            stoppingPoint.setRoutes(new ArrayList<>());
        }
    }

    public void removePoint(StoppingPoint stoppingPoint) {
        points.remove(stoppingPoint);
        stoppingPoint.getRoutes().remove(this);
    }

    public void removePoints() {
        log.info("In removePoints");
        points.clear();
        log.info("Exit removePoints");
    }

    public void updatePoints(List<StoppingPoint> newPoints) {
        List<StoppingPoint> firstInsect = new ArrayList<>();

        for (StoppingPoint pointFromClass : points) {
            boolean isInClass = false;
            for (StoppingPoint pointFromNew : newPoints) {
                if (pointFromClass.equals(pointFromNew)){
                    isInClass = true;
                    break;
                }
            }
            if (isInClass) {
                firstInsect.add(pointFromClass);
            }
        }

        List<StoppingPoint> secondInsect = new ArrayList<>();

        for (StoppingPoint pointsFromNew : newPoints) {
            boolean isInClass = false;
            for (StoppingPoint pointsFromInsect : firstInsect) {
                if (pointsFromNew.equals(pointsFromInsect)){
                    isInClass = true;
                    break;
                }
            }
            if (!isInClass) {
                secondInsect.add(pointsFromNew);
            }
        }

        List<StoppingPoint> remainder = new ArrayList<>();
        for (StoppingPoint point : points) {
            boolean isInClass = false;
            for (StoppingPoint pointFromInsect : firstInsect) {
                if (point.equals(pointFromInsect)){
                    isInClass = true;
                    break;
                }
            }
            if (!isInClass) {
                remainder.add(point);
            }
        }

        List<StoppingPoint> result = new ArrayList<>();
        result.addAll(secondInsect);
        result.addAll(firstInsect);
        points = result;
    }


    public void addPlan(Plan plan) {
        plans.add(plan);
        plan.setRoute(this);
    }


    public void removePlan(Plan plan) {
        plans.remove(plan);
        plan.setRoute(null);
    }

}
