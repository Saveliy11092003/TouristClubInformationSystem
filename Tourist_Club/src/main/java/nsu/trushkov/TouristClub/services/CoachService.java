package nsu.trushkov.TouristClub.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nsu.trushkov.TouristClub.entity.*;
import nsu.trushkov.TouristClub.entity.enums.TypeOfSport;
import nsu.trushkov.TouristClub.repositories.*;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class CoachService {
    private final CoachRepository coachRepository;
    private final TouristRepository touristRepository;
    private final SectionRepository sectionRepository;
    private final SectionWorkScheduleRepository sectionWorkScheduleRepository;
    private final GroupRepository groupRepository;

    public void createCoach(Tourist tourist, String salary, String speciality) {

        TypeOfSport spec = null;
        if (speciality.equals("FOOTBALL")) {
            spec = TypeOfSport.FOOTBALL;
        } else if (speciality.equals("HORSE")) {
            spec = TypeOfSport.HORSEBACK_RIDING;
        } else if (speciality.equals("SWIMMING")) {
            spec = TypeOfSport.SWIMMING_SPORT;
        } else if (speciality.equals("MOUNTAIN")) {
            spec = TypeOfSport.MOUNTAIN_SPORT;
        }

        Coach coach = Coach.builder().tourist(tourist).salary(salary).speciality(spec).build();
        coachRepository.save(coach);
    }

    public List<Coach> getCoaches() {
        return coachRepository.findAll();
    }

    public void updateCoach(Long id, Coach coach) {

        log.info(coach.getSpeciality().toString());
        Coach newCoach = coachRepository.findById(id).get();
        newCoach.setSalary(coach.getSalary());
        newCoach.setSpeciality(coach.getSpeciality());
        newCoach.getTourist().setName(coach.getTourist().getName());
        newCoach.getTourist().setSurname(coach.getTourist().getSurname());
        coachRepository.save(newCoach);
    }

    public void removeCoach(Long id) {
        Tourist tourist = touristRepository.findById(id).get();
        tourist.setTouristType(null);
        touristRepository.save(tourist);
        coachRepository.deleteById(id);
    }

    public List<Coach> request(Long idSection, String allSection, String speciality, String salary, String allSalary,
                               String age, String allAge, String sex) {

        List<Coach> result = new ArrayList<>();

        if (allSection.equals("All")) {
            for (Section section : sectionRepository.findAll()) {
                for (Group group : section.getGroups()) {
                    result.add(group.getCoach());
                }
            }
        } else {
            Section section = sectionRepository.findById(idSection).get();
            for (Group group : section.getGroups()) {
                result.add(group.getCoach());
            }
        }

        List<Coach> minus = new ArrayList<>();


        log.info("sex " + sex);
        if (sex.equals("All")) {

        } else {
            for (Coach coach : result) {
                if (!coach.getTourist().getSex().equals(sex)) {
                    minus.add(coach);
                }
            }
        }

        log.info("minus " + minus.toString());

        result.removeAll(minus);
        minus.clear();

        if (allAge.equals("All")) {

        } else {
            for (Coach coach : result) {
                if (Integer.parseInt(coach.getTourist().getAge()) == Integer.parseInt(age)) {
                    minus.add(coach);
                }
            }
        }

        log.info("minus " + minus.toString());
        result.removeAll(minus);
        minus.clear();

        if (allSalary.equals("All")) {

        } else {
            for (Coach coach : result) {
                if (Integer.parseInt(coach.getSalary()) > Integer.parseInt(salary)) {
                    minus.add(coach);
                }
            }
        }

        log.info("minus " + minus.toString());
        result.removeAll(minus);
        minus.clear();


        TypeOfSport spec = null;
        if (speciality.equals("FOOTBALL")) {
            spec = TypeOfSport.FOOTBALL;
        } else if (speciality.equals("HORSE")) {
            spec = TypeOfSport.HORSEBACK_RIDING;
        } else if (speciality.equals("SWIMMING")) {
            spec = TypeOfSport.SWIMMING_SPORT;
        } else if (speciality.equals("MOUNTAIN")) {
            spec = TypeOfSport.MOUNTAIN_SPORT;
        }


        for (Coach coach : result) {
            if (coach.getSpeciality() != spec) {
                minus.add(coach);
            }
        }

        result.removeAll(minus);

        Set<Coach> set = new HashSet<>();
        for (Coach coach : result) {
            set.add(coach);
        }

        result.clear();
        for (Coach coach : set) {
            result.add(coach);
        }

        return result;

    }

    public List<Coach> request2(Long idGroup, String dateStart, String dateEnd) {
        Group groupSpecified = groupRepository.findById(idGroup).get();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(dateStart, formatter);
        LocalDate endDate = LocalDate.parse(dateEnd, formatter);

        Set<Coach> coaches = new HashSet<>();

        for (SectionWorkSchedule schedule : sectionWorkScheduleRepository.findAll()) {
            if (groupSpecified.equals(schedule.getGroup())) {
                LocalDate scheduleStart = LocalDate.parse(schedule.getDateStart());
                LocalDate scheduleEnd = LocalDate.parse(schedule.getDateEnd());

                if ((scheduleStart.isEqual(startDate) || scheduleStart.isAfter(startDate)) &&
                        (scheduleEnd.isEqual(endDate) || scheduleEnd.isBefore(endDate))) {
                    if (schedule.getCoach() != null) {
                        coaches.add(schedule.getCoach());
                    }
                }
            }
        }

        List<Coach> result = new ArrayList<>(coaches);

        return result;
    }

    public String request3(Long idCoach, String dateStart, String dateEnd,
                           List<String> trainingHour) {

        Coach coach = coachRepository.findById(idCoach).get();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(dateStart, formatter);
        LocalDate endDate = LocalDate.parse(dateEnd, formatter);
        long hours = 0;
        long minutes = 0;

        Map<String, Long> map = new HashMap<>();

        for (SectionWorkSchedule schedule : sectionWorkScheduleRepository.findAll()) {
            if (coach.equals(schedule.getCoach()) && schedule.getIsCompleted()) {
                LocalDate scheduleStart = LocalDate.parse(schedule.getDateStart());
                LocalDate scheduleEnd = LocalDate.parse(schedule.getDateEnd());

                LocalDate start, end;

                if (startDate.isBefore(scheduleStart)) {
                    start = scheduleStart;
                } else {
                    start = startDate;
                }

                if (endDate.isBefore(scheduleEnd)) {
                    end = endDate;
                } else {
                    end = scheduleEnd;
                }


                DateTimeFormatter formatterHourMinute = DateTimeFormatter.ofPattern("HH:mm");
                LocalTime localTime1 = LocalTime.parse(schedule.getStartTraining(), formatterHourMinute);
                LocalTime localTime2 = LocalTime.parse(schedule.getEndTraining(), formatterHourMinute);


                Duration duration = Duration.between(localTime1, localTime2);

                long daysBetween = ChronoUnit.DAYS.between(start, end);

                long fullWeeksBetween = daysBetween / 7;

                hours += Math.abs(duration.toHours() * fullWeeksBetween);
                minutes += Math.abs(duration.toMinutes()  % 60 * fullWeeksBetween);

                if (!map.containsKey(schedule.getTrainingName())) {
                    map.put(schedule.getTrainingName(), Math.abs(duration.toMinutes()*fullWeeksBetween));
                } else {
                    map.put(schedule.getTrainingName(), Math.abs(duration.toMinutes()*fullWeeksBetween) + map.get(schedule.getTrainingName()));
                }

            }

        }

        long hourInMinutes = minutes / 60;
        minutes %= 60;
        hours += hourInMinutes;
        String totalTime = new String(hours + "ч. " + minutes + "м.");

        for (Map.Entry<String, Long> entry : map.entrySet()) {
            trainingHour.add(entry.getKey() + " " + entry.getValue() / 60 + "ч. " + entry.getValue() % 60 + "м. ");
        }
        return totalTime;

    }

    public Coach getCoach(Long idCoach) {
        return coachRepository.findById(idCoach).get();
    }

    public String request4(Long idSection, String dateStart, String dateEnd, List<String> trainingHour) {
        Section sectionSpecified = sectionRepository.findById(idSection).get();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(dateStart, formatter);
        LocalDate endDate = LocalDate.parse(dateEnd, formatter);
        long hours = 0;
        long minutes = 0;

        Map<String, Long> map = new HashMap<>();

        for (SectionWorkSchedule schedule : sectionWorkScheduleRepository.findAll()) {
            Section section = schedule.getGroup().getSection();
            if (sectionSpecified.equals(section) && schedule.getIsCompleted()) {
                LocalDate scheduleStart = LocalDate.parse(schedule.getDateStart());
                LocalDate scheduleEnd = LocalDate.parse(schedule.getDateEnd());

                LocalDate start, end;

                if (startDate.isBefore(scheduleStart)) {
                    start = scheduleStart;
                } else {
                    start = startDate;
                }

                if (endDate.isBefore(scheduleEnd)) {
                    end = endDate;
                } else {
                    end = scheduleEnd;
                }


                DateTimeFormatter formatterHourMinute = DateTimeFormatter.ofPattern("HH:mm");
                LocalTime localTime1 = LocalTime.parse(schedule.getStartTraining(), formatterHourMinute);
                LocalTime localTime2 = LocalTime.parse(schedule.getEndTraining(), formatterHourMinute);


                Duration duration = Duration.between(localTime1, localTime2);

                long daysBetween = ChronoUnit.DAYS.between(start, end);

                long fullWeeksBetween = Math.abs(daysBetween / 7);

                hours += Math.abs(duration.toHours() * fullWeeksBetween);
                minutes += Math.abs(duration.toMinutes()  % 60 * fullWeeksBetween);

                if (!map.containsKey(schedule.getTrainingName())) {
                    map.put(schedule.getTrainingName(), Math.abs(duration.toMinutes())*fullWeeksBetween);
                } else {
                    map.put(schedule.getTrainingName(), Math.abs(duration.toMinutes()*fullWeeksBetween) + map.get(schedule.getTrainingName()));
                }
            }
        }

        long hourInMinutes = minutes / 60;
        minutes %= 60;
        hours += hourInMinutes;
        String totalTime = new String(hours + "ч. " + minutes + "м.");

        for (Map.Entry<String, Long> entry : map.entrySet()) {
            trainingHour.add(entry.getKey() + " " + entry.getValue() / 60 + "ч. " + entry.getValue() % 60 + "м. ");
        }
        return totalTime;

    }
}
