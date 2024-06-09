package nsu.trushkov.TouristClub.services;

import lombok.RequiredArgsConstructor;
import nsu.trushkov.TouristClub.entity.Coach;
import nsu.trushkov.TouristClub.entity.Group;
import nsu.trushkov.TouristClub.entity.SectionWorkSchedule;
import nsu.trushkov.TouristClub.repositories.CoachRepository;
import nsu.trushkov.TouristClub.repositories.GroupRepository;
import nsu.trushkov.TouristClub.repositories.SectionWorkScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SectionWorkScheduleService {
    private final SectionWorkScheduleRepository sectionWorkScheduleRepository;
    private final GroupRepository groupRepository;
    private final CoachRepository coachRepository;


    public void createSectionWorkSchedule(String trainingName,Long idGroup,String timeBegin,
                                          String timeEnd, String place, String day,
                                          String dateStart, String dateEnd) {

        Group group = groupRepository.findById(idGroup).get();
        //Coach coach = coachRepository.findById(idCoach).get();

        SectionWorkSchedule sectionWorkSchedule = SectionWorkSchedule.builder()
                .trainingName(trainingName).startTraining(timeBegin).endTraining(timeEnd)
                .group(group).place(place).isCompleted(false).day(day).dateStart(dateStart).dateEnd(dateEnd).build();
        sectionWorkScheduleRepository.save(sectionWorkSchedule);
    }

    public List<SectionWorkSchedule> getSchedules() {
        return sectionWorkScheduleRepository.findAll();
    }

    public void deleteSchedule(long id) {
        sectionWorkScheduleRepository.deleteById(id);
    }

    public void updateSchedule(long id, String trainingName, Long idGroup, String timeBegin,
                               String timeEnd, String place, String day, Integer attendance,
                               Long idCoach, String dateStart, String dateEnd) {

        SectionWorkSchedule sectionWorkSchedule = sectionWorkScheduleRepository.findById(id).get();
        sectionWorkSchedule.setTrainingName(trainingName);
        sectionWorkSchedule.setDay(day);
        sectionWorkSchedule.setAttendance(attendance);
        sectionWorkSchedule.setStartTraining(timeBegin);
        sectionWorkSchedule.setEndTraining(timeEnd);
        sectionWorkSchedule.setPlace(place);
        sectionWorkSchedule.setDateStart(dateStart);
        sectionWorkSchedule.setDateEnd(dateEnd);

        Group group = groupRepository.findById(idGroup).get();
        Coach coach = coachRepository.findById(idCoach).get();

        sectionWorkSchedule.setCoach(coach);
        sectionWorkSchedule.setGroup(group);

        if (!sectionWorkSchedule.getIsCompleted()) {
            sectionWorkSchedule.setIsCompleted(true);
        }

        sectionWorkScheduleRepository.save(sectionWorkSchedule);
    }
}
