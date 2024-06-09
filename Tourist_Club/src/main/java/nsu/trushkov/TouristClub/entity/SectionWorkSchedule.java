package nsu.trushkov.TouristClub.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "section_work_schedule")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SectionWorkSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "place")
    private String place;

    @Column(name = "startTraining")
    private String startTraining;

    @Column(name = "endTraining")
    private String endTraining;

    @Column(name = "dateStart")
    private String dateStart;

    @Column(name = "dateEnd")
    private String dateEnd;

    @Column(name = "name_training")
    private String trainingName;

    @Column(name = "is_completed")
    private Boolean isCompleted = false;

    @Column(name = "attendance")
    private Integer attendance;

    @Column(name = "day")
    private String day;

    @ManyToOne()
    @JoinColumn(name = "id_group")
    private Group group;

    @ManyToOne()
    @JoinColumn(name = "id_coach")
    private Coach coach;



}
