package nsu.trushkov.TouristClub.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "stopping_points")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StoppingPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name_point", nullable = false)
    private String stoppingPointName;

    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="route_point",
            joinColumns=  @JoinColumn(name="id_point", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="id_route", referencedColumnName="id"))
    List<Route> routes = new ArrayList<>();

}
