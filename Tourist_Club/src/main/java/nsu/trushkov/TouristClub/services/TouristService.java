package nsu.trushkov.TouristClub.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nsu.trushkov.TouristClub.entity.*;
import nsu.trushkov.TouristClub.entity.enums.DifficultyOfRoute;
import nsu.trushkov.TouristClub.entity.enums.TouristType;
import nsu.trushkov.TouristClub.entity.enums.TypeOfCamping;
import nsu.trushkov.TouristClub.entity.enums.TypeOfSport;
import nsu.trushkov.TouristClub.repositories.*;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static nsu.trushkov.TouristClub.entity.enums.DifficultyOfRoute.*;
import static nsu.trushkov.TouristClub.entity.enums.TouristType.*;
import static nsu.trushkov.TouristClub.entity.enums.TypeOfCamping.*;
import static nsu.trushkov.TouristClub.entity.enums.TypeOfSport.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class TouristService {
    private final TouristRepository touristRepository;
    private final SectionRepository sectionRepository;
    private final GroupRepository groupRepository;
    private final PlannedCampingRepository plannedCampingRepository;
    private final RouteService routeService;
    private final RouteRepository routeRepository;
    private final StoppingPointRepository stoppingPointRepository;
    private final CoachRepository coachRepository;
    private final AthleteRepository athleteRepository;


    public void createTourist(Tourist tourist) {
        tourist.setTouristType(AMATEUR);
        touristRepository.save(tourist);
    }


    public List<Tourist> getTourists() {
        return touristRepository.findAll();
    }

    public void deleteTourist(Long id) {
        touristRepository.deleteById(id);
    }

    public Tourist getTouristById(Long id) {
        return touristRepository.findById(id).get();
    }

    public void updateTourist(Long id, Tourist tourist) {
        Tourist touristNew = touristRepository.findById(id).get();
        touristNew.setName(tourist.getName());
        touristNew.setSurname(tourist.getSurname());
        touristNew.setAge(tourist.getAge());
        touristNew.setSex(tourist.getSex());
        touristNew.setDateOfBirth(tourist.getDateOfBirth());
        touristNew.setDifficultyOfRoute(tourist.getDifficultyOfRoute());
        touristRepository.save(touristNew);
    }

    public List<Tourist> getCoaches() {
        List<Tourist> result = new ArrayList<>();
        for (Tourist tourist : touristRepository.findAll()) {
            if (tourist.getTouristType() == COACH) {
                result.add(tourist);
            }
        }
        return result;
    }

    public List<Tourist> getAmateur() {
        List<Tourist> result = new ArrayList<>();
        for (Tourist tourist : touristRepository.findAll()) {
            if (tourist.getTouristType() == TouristType.AMATEUR) {
                result.add(tourist);
            }
        }
        return result;
    }

    public List<Tourist> getAthlete() {
        List<Tourist> result = new ArrayList<>();
        for (Tourist tourist : touristRepository.findAll()) {
            if (tourist.getTouristType() == TouristType.ATHLETE) {
                result.add(tourist);
            }
        }
        return result;
    }

    public List<Tourist> getTouristsWithoutType() {
        List<Tourist> result = new ArrayList<>();
        for (Tourist tourist : touristRepository.findAll()) {
            if (tourist.getTouristType() != AMATEUR) {
                result.add(tourist);
            }
        }
        return result;
    }

    public List<Tourist> getTouristsNotCoach() {
        List<Tourist> result = new ArrayList<>();
        for (Tourist tourist : touristRepository.findAll()) {
            if (tourist.getTouristType() != COACH) {
                result.add(tourist);
            }
        }
        return result;
    }


    public List<Tourist> getTouristsByTypeOfCamping(TypeOfCamping typeOfCamping) {
        List<Tourist> result = new ArrayList<>();

        if (typeOfCamping == HIKING) {
            result.addAll(touristRepository.findByGroupNotNull());
        }

        if (typeOfCamping == WATER_TRIP) {
            result.addAll(getTouristWatter());
        }

        if (typeOfCamping == MOUNTAIN_HIKE) {
            result.addAll(getTouristMountain());
        }

        if (typeOfCamping == HORSE_RIDING) {
            result.addAll(getTouristHorse());
        }

        return result;
    }

    public List<Tourist> getTouristMiddle() {
        List<Tourist> result = new ArrayList<>();

        for (Tourist tourist : touristRepository.findAll()) {
            if (tourist.getDifficultyOfRoute() == DifficultyOfRoute.MIDDLE) {
                result.add(tourist);
            }
        }
        return result;
    }

    public List<Tourist> getTouristHard() {
        List<Tourist> result = new ArrayList<>();

        for (Tourist tourist : touristRepository.findAll()) {
            if (tourist.getDifficultyOfRoute() == DifficultyOfRoute.HARD) {
                result.add(tourist);
            }
        }
        return result;
    }

    public List<Tourist> getTouristWatter() {
        Set<Tourist> result = new HashSet<>();

        for (Tourist tourist : touristRepository.findAll()) {
            if (tourist.getGroup() != null && tourist.getGroup().getSection() != null &&
                    tourist.getGroup().getSection().getTypeOfSection() != null &&
                    tourist.getGroup().getSection().getTypeOfSection().getTypeOfSport().equals(SWIMMING_SPORT)) {
                result.add(tourist);
            }
        }

        for (Coach coach : coachRepository.findAll()) {
            if (coach.getSpeciality() == SWIMMING_SPORT) {
                result.add(touristRepository.findById(coach.getId()).get());
            }
        }

        return new ArrayList<>(result);
    }

    public List<Tourist> getTouristMountain() {
        Set<Tourist> result = new HashSet<>();

        for (Tourist tourist : touristRepository.findAll()) {
            if (tourist.getGroup() != null && tourist.getGroup().getSection() != null &&
                    tourist.getGroup().getSection().getTypeOfSection() != null &&
                    tourist.getGroup().getSection().getTypeOfSection().getTypeOfSport().equals(MOUNTAIN_SPORT)) {
                result.add(tourist);
            }
        }

        for (Coach coach : coachRepository.findAll()) {
            if (coach.getSpeciality() == MOUNTAIN_SPORT) {
                result.add(touristRepository.findById(coach.getId()).get());
            }
        }

        return new ArrayList<>(result);
    }


    public List<Tourist> getTouristHorse() {
        Set<Tourist> result = new HashSet<>();

        for (Tourist tourist : touristRepository.findAll()) {
            if (tourist.getGroup() != null && tourist.getGroup().getSection() != null &&
                    tourist.getGroup().getSection().getTypeOfSection() != null &&
                    tourist.getGroup().getSection().getTypeOfSection().getTypeOfSport().equals(HORSEBACK_RIDING)) {
                result.add(tourist);
            }
        }

        for (Coach coach : coachRepository.findAll()) {
            if (coach.getSpeciality() == HORSEBACK_RIDING) {
                result.add(touristRepository.findById(coach.getId()).get());
            }
        }


        return new ArrayList<>(result);
    }


    public List<Tourist> getInstructors(DifficultyOfRoute difficultyOfRoute, TypeOfCamping typeOfCamping) {
        List<Tourist> tourists = getTouristsByTypeOfCamping(typeOfCamping);
        List<Tourist> result = new ArrayList<>();

        for (Tourist tourist : tourists) {
            if (difficultyOfRoute == EASY) {
                if (tourist.getTouristType() != AMATEUR) {
                    result.add(tourist);
                }
            }
            if (difficultyOfRoute == MIDDLE) {
                if (tourist.getTouristType() != AMATEUR && tourist.getDifficultyOfRoute() != EASY) {
                    result.add(tourist);
                }
            }

            if (difficultyOfRoute == HARD) {
                if (tourist.getTouristType() != AMATEUR && tourist.getDifficultyOfRoute() == HARD) {
                    result.add(tourist);
                }
            }
        }
        return result;
    }

    public List<Tourist> requestSection(Long idSection, String sex, String age, String allAge) {


        List<Tourist> tourists = new ArrayList<>();

        Optional<Section> optionalSection = sectionRepository.findById(idSection);

        log.info(optionalSection.toString());

        if (optionalSection.isPresent()) {
            for (Group group : optionalSection.get().getGroups()) {
                tourists.addAll(group.getTourists());
            }
        }

        log.info("tourists " + tourists.toString());

        List<Tourist> minus = new ArrayList<>();

        log.info("sex " + sex);
        if (sex.equals("All")) {

        } else {
            for (Tourist tourist : tourists) {
                if (!tourist.getSex().equals(sex)) {
                    minus.add(tourist);
                }
            }
        }

        log.info("minus " + minus.toString());

        tourists.removeAll(minus);
        minus.clear();

        if (allAge.equals("All")) {

        } else {
            for (Tourist tourist : tourists) {
                if (!Objects.equals(tourist.getAge(), age)) {
                    minus.add(tourist);
                }
            }
        }

        log.info("minus " + minus.toString());
        tourists.removeAll(minus);

        return tourists;
    }

    public List<Tourist> requestGroup(Long idGroup, String sex, String age, String allAge) {
        List<Tourist> tourists = new ArrayList<>();

        Optional<Group> optionalGroup = groupRepository.findById(idGroup);

        log.info(optionalGroup.toString());

        if (optionalGroup.isPresent()) {
            tourists.addAll(optionalGroup.get().getTourists());

        }

        log.info("tourists " + tourists.toString());

        List<Tourist> minus = new ArrayList<>();

        log.info("sex " + sex);
        if (sex.equals("All")) {

        } else {
            for (Tourist tourist : tourists) {
                if (!tourist.getSex().equals(sex)) {
                    minus.add(tourist);
                }
            }
        }

        log.info("minus " + minus.toString());

        tourists.removeAll(minus);
        minus.clear();

        if (allAge.equals("All")) {

        } else {
            for (Tourist tourist : tourists) {
                if (!Objects.equals(tourist.getAge(), age)) {
                    minus.add(tourist);
                }
            }
        }

        log.info("minus " + minus.toString());
        tourists.removeAll(minus);

        return tourists;
    }

    public List<Tourist> request5Count(Long idSection, Long idGroup, Integer count) {
        List<PlannedCamping> plannedCampings = plannedCampingRepository.findAll();
        // Set<Tourist> tourists = new HashSet<>();
        Map<Tourist, Integer> map = new HashMap<>();

        for (PlannedCamping plannedCamping : plannedCampings) {
            for (Tourist tourist : plannedCamping.getTourists()) {
                if (idSection != 0) {
                    if (tourist.getGroup().getSection().equals(sectionRepository.findById(idSection).get())) {
                        if (!map.containsKey(tourist)) {
                            map.put(tourist, 1);
                        } else {
                            Integer c = map.get(tourist) + 1;
                            map.put(tourist, c);
                        }
                    }
                } else {
                    if (tourist.getGroup().equals(groupRepository.findById(idGroup).get())) {
                        if (!map.containsKey(tourist)) {
                            map.put(tourist, 1);
                        } else {
                            Integer c = map.get(tourist) + 1;
                            map.put(tourist, c);
                        }
                    }
                }
            }
        }

        List<Tourist> result = new ArrayList<>();

        for (Map.Entry<Tourist, Integer> entry : map.entrySet()) {
            if (entry.getValue().equals(count)) {
                result.add(entry.getKey());
            }
        }

        return result;

    }

    public List<Tourist> request5Camping(Long idSection, Long idGroup, Long idCamping) {
        PlannedCamping plannedCamping = plannedCampingRepository.findById(idCamping).get();
        Set<Tourist> tourists = new HashSet<>();


        for (Tourist tourist : plannedCamping.getTourists()) {
            if (idSection != 0) {
                if (tourist.getGroup().getSection().equals(sectionRepository.findById(idSection).get())) {
                    tourists.add(tourist);
                }
            } else {
                if (tourist.getGroup().equals(groupRepository.findById(idGroup).get())) {
                    tourists.add(tourist);
                }
            }
        }

        List<Tourist> result = new ArrayList<>(tourists);
        return result;
    }

    public List<Tourist> request5Time(Long idSection, Long idGroup, String dateStart, String dateEnd) {
        List<PlannedCamping> plannedCampings = plannedCampingRepository.findAll();
        Set<Tourist> tourists = new HashSet<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(dateStart, formatter);
        LocalDate endDate = LocalDate.parse(dateEnd, formatter);


        for (PlannedCamping plannedCamping : plannedCampings) {
            Plan plan = plannedCamping.getPlan();

            LocalDate scheduleStart = LocalDate.parse(plan.getStartCamping());
            LocalDate scheduleEnd = LocalDate.parse(plan.getEndCamping());

            if ((scheduleStart.isEqual(startDate) || scheduleStart.isAfter(startDate)) &&
                    (scheduleEnd.isEqual(endDate) || scheduleEnd.isBefore(endDate))) {

                for (Tourist tourist : plannedCamping.getTourists()) {
                    if (idSection != 0) {
                        if (tourist.getGroup().getSection().equals(sectionRepository.findById(idSection).get())) {
                            tourists.add(tourist);
                        }
                    } else {
                        if (tourist.getGroup().equals(groupRepository.findById(idGroup).get())) {
                            tourists.add(tourist);
                        }
                    }
                }
            }
        }


        List<Tourist> result = new ArrayList<>(tourists);
        return result;
    }

    public List<Tourist> request5Route(Long idSection, Long idGroup, Long idRoute) {
        List<PlannedCamping> plannedCampings = plannedCampingRepository.findAll();
        Set<Tourist> tourists = new HashSet<>();

        Route routeSpecified = routeRepository.findById(idRoute).get();


        for (PlannedCamping plannedCamping : plannedCampings) {
            Route route = plannedCamping.getPlan().getRoute();

            if (route.equals(routeSpecified)) {

                for (Tourist tourist : plannedCamping.getTourists()) {
                    if (idSection != 0) {
                        if (tourist.getGroup().getSection().equals(sectionRepository.findById(idSection).get())) {
                            tourists.add(tourist);
                        }
                    } else {
                        if (tourist.getGroup().equals(groupRepository.findById(idGroup).get())) {
                            tourists.add(tourist);
                        }
                    }
                }
            }
        }


        List<Tourist> result = new ArrayList<>(tourists);
        return result;
    }

    public List<Tourist> request5Point(Long idSection, Long idGroup, Long idPoint) {
        List<PlannedCamping> plannedCampings = plannedCampingRepository.findAll();
        Set<Tourist> tourists = new HashSet<>();

        StoppingPoint stoppingPoint = stoppingPointRepository.findById(idPoint).get();


        for (PlannedCamping plannedCamping : plannedCampings) {
            Route route = plannedCamping.getPlan().getRoute();

            for (StoppingPoint point : route.getPoints()) {

                if (point.equals(stoppingPoint)) {

                    for (Tourist tourist : plannedCamping.getTourists()) {
                        if (idSection != 0) {
                            if (tourist.getGroup().getSection().equals(sectionRepository.findById(idSection).get())) {
                                tourists.add(tourist);
                            }
                        } else {
                            if (tourist.getGroup().equals(groupRepository.findById(idGroup).get())) {
                                tourists.add(tourist);
                            }
                        }
                    }
                }
            }
        }


        List<Tourist> result = new ArrayList<>(tourists);
        return result;
    }

    public List<Tourist> request5Difficulty(Long idSection, Long idGroup, String difficultyRoute) {
        List<PlannedCamping> plannedCampings = plannedCampingRepository.findAll();
        Set<Tourist> tourists = new HashSet<>();

        DifficultyOfRoute difficultySpecified;

        if (difficultyRoute.equals("easy")) {
            difficultySpecified = EASY;
        } else if (difficultyRoute.equals("middle")) {
            difficultySpecified = MIDDLE;
        } else {
            difficultySpecified = HARD;
        }


        for (PlannedCamping plannedCamping : plannedCampings) {
            Route route = plannedCamping.getPlan().getRoute();

            if (route.getDifficultyOfRoute().equals(difficultySpecified)) {

                for (Tourist tourist : plannedCamping.getTourists()) {
                    if (idSection != 0) {
                        if (tourist.getGroup().getSection().equals(sectionRepository.findById(idSection).get()) &&
                                tourist.getDifficultyOfRoute() == difficultySpecified) {
                            tourists.add(tourist);
                        }
                    } else {
                        if (tourist.getGroup().equals(groupRepository.findById(idGroup).get()) &&
                                tourist.getDifficultyOfRoute() == difficultySpecified) {
                            tourists.add(tourist);
                        }
                    }
                }
            }

        }

        List<Tourist> result = new ArrayList<>(tourists);
        return result;
    }

    public List<Tourist> requestCategory(Long idSection, Long idGroup, String category) {
        List<Tourist> tourists = touristRepository.findAll();
        Set<Tourist> result = new HashSet<>();

        TouristType touristType = ATHLETE;

        if (category.equals("ATHLETE")) {
            touristType = ATHLETE;
        } else if (category.equals("COACH")) {
            touristType = COACH;
        } else {
            touristType = AMATEUR;
        }


        for (Tourist tourist : tourists) {
            if (tourist.getTouristType().equals(touristType)) {
                if (idSection != 0) {
                    if (tourist.getGroup() != null && tourist.getGroup().getSection().equals(sectionRepository.findById(idSection).get())) {
                        result.add(tourist);
                    }
                } else {
                    if (tourist.getGroup() != null && tourist.getGroup().equals(groupRepository.findById(idGroup).get())) {
                        result.add(tourist);
                    }
                }
            }
        }

        if (touristType == COACH) {
            if (idSection != 0) {
                for (Group group : sectionRepository.findById(idSection).get().getGroups()) {
                    result.add(touristRepository.findById(group.getCoach().getId()).get());
                }

            } else {
                result.add(touristRepository.findById(groupRepository.findById(idGroup).get().getCoach().getId()).get());
            }
        }

        return new ArrayList<>(result);
    }


    public List<Tourist> request10TypeOfRoute(Long idSection, Long idGroup, String typeOfRoute) {
        log.info("idSection " + idSection);
        log.info("idGroup " + idGroup);
        log.info("typeOfRoute " + typeOfRoute);

        Set<Tourist> tourists = new HashSet<>();

        Section sectionS = new Section();
        Group groupS = new Group();
        if (idSection != 0) {
            sectionS = sectionRepository.findById(idSection).get();
        } else {
            groupS = groupRepository.findById(idGroup).get();
        }


        for (Tourist tourist : touristRepository.findAll()) {
            if (tourist.getGroup() != null) {
                TypeOfSport typeOfSport = tourist.getGroup().getSection().getTypeOfSection().getTypeOfSport();
                log.info("typeOfSport " + typeOfSport);
                if (typeOfRoute.equals("HIKING")) {
                    log.info("HIKING");
                    if (idSection != 0 &&
                            tourist.getGroup().getSection().equals(sectionS)) {
                        for (Group group : sectionS.getGroups()) {
                            log.info("Group " + group);
                            tourists.addAll(group.getTourists());
                        }

                    } else {
                        if (tourist.getGroup().equals(groupS)) {
                            tourists.addAll(groupS.getTourists());
                        }
                    }
                } else if (typeOfRoute.equals("HORSE")) {
                    if (typeOfSport.equals(HORSEBACK_RIDING)) {
                        if (idSection != 0 &&
                                tourist.getGroup().getSection().equals(sectionS)) {
                            for (Group group : sectionS.getGroups()) {
                                tourists.addAll(group.getTourists());
                            }

                        } else {
                            if (tourist.getGroup().equals(groupS)) {
                                tourists.addAll(groupS.getTourists());
                            }
                        }
                    }
                } else if (typeOfRoute.equals("SWIMMING")) {
                    if (typeOfSport.equals(SWIMMING_SPORT)) {
                        log.info("Swimming");
                        if (idSection != 0 &&
                                tourist.getGroup().getSection().equals(sectionS)) {
                            for (Group group : sectionS.getGroups()) {
                                tourists.addAll(group.getTourists());
                            }
                            log.info("section");

                        } else {
                            if (tourist.getGroup().equals(groupS)) {
                                log.info("group");
                                log.info("tourist " + tourist);
                                tourists.addAll(groupS.getTourists());
                            }
                        }
                    }
                } else {
                    if (typeOfSport.equals(MOUNTAIN_SPORT)) {
                        if (idSection != 0 &&
                                tourist.getGroup().getSection().equals(sectionS)) {
                            for (Group group : sectionS.getGroups()) {
                                tourists.addAll(group.getTourists());
                            }

                        } else {
                            if (tourist.getGroup().equals(groupS)) {
                                tourists.addAll(groupS.getTourists());
                            }
                        }
                    }
                }
            }
        }

        return new ArrayList<>(tourists);

    }

    public List<Tourist> request12OfRoute(Long idSection, Long idGroup) {
        Set<Tourist> tourists = new HashSet<>();

        Section sectionS = new Section();
        Group groupS = new Group();
        if (idSection != 0) {
            sectionS = sectionRepository.findById(idSection).get();
        } else {
            groupS = groupRepository.findById(idGroup).get();
        }

        for (Tourist tourist : touristRepository.findAll()) {
            if (tourist.getGroup() != null) {
                Tourist coach = touristRepository.findById(tourist.getGroup().getCoach().getId()).get();

                for (PlannedCamping camping : plannedCampingRepository.findAll()) {
                    if (camping.getInstructor().equals(coach)) {

                        if (idSection != 0 &&
                                tourist.getGroup().getSection().equals(sectionS)) {
                            tourists.add(tourist);
                        } else {
                            if (tourist.getGroup().equals(groupS)) {
                                tourists.add(tourist);
                            }
                        }

                    }
                }
            }

        }
        return new ArrayList<>(tourists);
    }

    public List<Tourist> request13(Long idSection, Long idGroup, List<Long> idsRoutes, String allRoutes) {
        Set<Tourist> tourists = new HashSet<>();

        Section sectionS = new Section();
        Group groupS = new Group();
        if (idSection != 0) {
            sectionS = sectionRepository.findById(idSection).get();
        } else {
            groupS = groupRepository.findById(idGroup).get();
        }

        Set<Route> routeForTourist = new HashSet<>();

        if (allRoutes.equals("All")) {
            for (Tourist tourist : touristRepository.findAll()) {
                if (tourist.getGroup() != null) {
                    for (PlannedCamping plannedCamping : tourist.getPlannedCampings()) {
                        routeForTourist.add(plannedCamping.getPlan().getRoute());
                    }

                    if (routeForTourist.size() == routeRepository.findAll().size()) {
                        if (idSection != 0 &&
                                tourist.getGroup().getSection().equals(sectionS)) {
                            tourists.add(tourist);
                        } else {
                            if (tourist.getGroup().equals(groupS)) {
                                tourists.add(tourist);
                            }
                        }
                    }

                    routeForTourist.clear();
                }
            }
        } else {
            List<Route> routesSpecified = new ArrayList<>();
            for (Long id : idsRoutes) {
                routesSpecified.add(routeRepository.findById(id).get());
            }

            for (Tourist tourist : touristRepository.findAll()) {
                if (tourist.getGroup() != null) {
                    for (PlannedCamping plannedCamping : tourist.getPlannedCampings()) {
                        routeForTourist.add(plannedCamping.getPlan().getRoute());
                    }

                    if (routeForTourist.size() == routesSpecified.size()) {
                        if (idSection != 0 &&
                                tourist.getGroup().getSection().equals(sectionS)) {
                            tourists.add(tourist);
                        } else {
                            if (tourist.getGroup().equals(groupS)) {
                                tourists.add(tourist);
                            }
                        }
                    }

                    routeForTourist.clear();
                }
            }

        }

        return new ArrayList<>(tourists);

    }

    public List<Tourist> getInstructorsRequest1(String typeInstructor, String difficulty) {
        Set<Tourist> instructors = new HashSet<>();


        for (PlannedCamping camping : plannedCampingRepository.findAll()) {
            instructors.add(camping.getInstructor());
        }

        Set<Tourist> minus = new HashSet<>();

        for (Tourist instructor : instructors) {
            if (difficulty.equals("EASY")) {

            } else if (difficulty.equals("MIDDLE") && instructor.getDifficultyOfRoute().equals(EASY)) {
                minus.add(instructor);
            } else if (difficulty.equals("HARD") && !instructor.getDifficultyOfRoute().equals(HARD)) {
                minus.add(instructor);
            }
        }

        instructors.removeAll(minus);
        minus.clear();

        for (Tourist instructor : instructors) {

            if (typeInstructor.equals("instructor")) {

            } else if (typeInstructor.equals("instructor-athlete")) {
                if (!instructor.getTouristType().equals(ATHLETE)) {
                    minus.add(instructor);
                }
            } else if (typeInstructor.equals("instructor-coach")) {
                if (!instructor.getTouristType().equals(COACH)) {
                    minus.add(instructor);
                }
            }

        }

        instructors.removeAll(minus);
        minus.clear();

        return new ArrayList<>(instructors);

    }

    public List<Tourist> getInstructorsRequest2(String typeInstructor, Integer count) {

        log.info("count " + count);

        Set<Tourist> instructors = new HashSet<>();

        Map<Tourist, Integer> map = new HashMap<>();


        for (PlannedCamping camping : plannedCampingRepository.findAll()) {
            instructors.add(camping.getInstructor());
        }

        Set<Tourist> minus = new HashSet<>();

        for (Tourist instructor : instructors) {

            if (typeInstructor.equals("instructor")) {

            } else if (typeInstructor.equals("instructor-athlete")) {
                if (!instructor.getTouristType().equals(ATHLETE)) {
                    minus.add(instructor);
                }
            } else if (typeInstructor.equals("instructor-coach")) {
                if (!instructor.getTouristType().equals(COACH)) {
                    minus.add(instructor);
                }
            }

        }

        instructors.removeAll(minus);
        minus.clear();

        for (Tourist instructor : instructors) {
            for (PlannedCamping camping : plannedCampingRepository.findAll()) {
                Tourist instructorFromCamping = camping.getInstructor();

                if (instructorFromCamping.equals(instructor)) {
                    if (!map.containsKey(instructor)) {
                        map.put(instructor, 1);
                    } else {
                        map.put(instructor, map.get(instructor) + 1);
                    }
                } else {
                    for (Tourist tourist : camping.getTourists()) {
                        if (tourist.equals(instructor)) {
                            if (!map.containsKey(instructor)) {
                                map.put(instructor, 1);
                            } else {
                                map.put(instructor, map.get(instructor) + 1);
                            }
                        }
                    }
                }

            }
        }

        List<Tourist> result = new ArrayList<>();


        for (Map.Entry<Tourist, Integer> entry : map.entrySet()) {
            if (entry.getValue().equals(count)) {
                result.add(entry.getKey());
            }
        }
        return result;
    }

    public List<Tourist> getInstructorsRequest3(String typeInstructor, Long idCamping) {

        Set<Tourist> instructors = new HashSet<>();

        for (PlannedCamping camping : plannedCampingRepository.findAll()) {
            instructors.add(camping.getInstructor());
        }

        Set<Tourist> minus = new HashSet<>();

        for (Tourist instructor : instructors) {

            if (typeInstructor.equals("instructor")) {

            } else if (typeInstructor.equals("instructor-athlete")) {
                if (!instructor.getTouristType().equals(ATHLETE)) {
                    minus.add(instructor);
                }
            } else if (typeInstructor.equals("instructor-coach")) {
                if (!instructor.getTouristType().equals(COACH)) {
                    minus.add(instructor);
                }
            }
        }

        instructors.removeAll(minus);
        minus.clear();


        PlannedCamping camping = plannedCampingRepository.findById(idCamping).get();

        for (Tourist instructor : instructors) {
            if (!camping.getInstructor().equals(instructor)) {
                minus.add(instructor);
            }
        }
        instructors.removeAll(minus);
        minus.clear();

        Set<Tourist> result = new HashSet<>();
        result.addAll(instructors);

        for (PlannedCamping camping1 : plannedCampingRepository.findAll()) {
            instructors.add(camping1.getInstructor());
        }

        for (Tourist instructor : instructors) {
            for (Tourist tourist : camping.getTourists()) {
                if (tourist.equals(instructor)) {
                    result.add(instructor);
                }
            }
        }

        minus.clear();

        for (Tourist instructor : result) {

            if (typeInstructor.equals("instructor")) {

            } else if (typeInstructor.equals("instructor-athlete")) {
                if (!instructor.getTouristType().equals(ATHLETE)) {
                    minus.add(instructor);
                }
            } else if (typeInstructor.equals("instructor-coach")) {
                if (!instructor.getTouristType().equals(COACH)) {
                    minus.add(instructor);
                }
            }
        }

        result.removeAll(minus);


        return new ArrayList<>(result);
    }

    public List<Tourist> getInstructorsRequest4(String typeInstructor, Long idRoute) {
        Set<Tourist> instructors = new HashSet<>();


        for (PlannedCamping camping : plannedCampingRepository.findAll()) {
            instructors.add(camping.getInstructor());
        }

        Set<Tourist> minus = new HashSet<>();

        for (Tourist instructor : instructors) {

            if (typeInstructor.equals("instructor")) {

            } else if (typeInstructor.equals("instructor-athlete")) {
                if (!instructor.getTouristType().equals(ATHLETE)) {
                    minus.add(instructor);
                }
            } else if (typeInstructor.equals("instructor-coach")) {
                if (!instructor.getTouristType().equals(COACH)) {
                    minus.add(instructor);
                }
            }
        }

        instructors.removeAll(minus);
        minus.clear();


        Route route = routeRepository.findById(idRoute).get();

        List<PlannedCamping> campings = new ArrayList<>();

        for (PlannedCamping camping : plannedCampingRepository.findAll()) {
            if (camping.getPlan().getRoute().equals(route)) {
                campings.add(camping);
            }
        }

        Set<Tourist> result = new HashSet<>();

        for (Tourist instructor : instructors) {
            for (PlannedCamping camping : campings) {
                if (camping.getInstructor().equals(instructor) || camping.getTourists().contains(instructor)) {
                    result.add(instructor);
                }
            }
        }

        return new ArrayList<>(result);
    }

    public List<Tourist> getInstructorsRequest5(String typeInstructor, Long idPoint) {
        Set<Tourist> instructors = new HashSet<>();


        for (PlannedCamping camping : plannedCampingRepository.findAll()) {
            instructors.add(camping.getInstructor());
        }

        Set<Tourist> minus = new HashSet<>();

        for (Tourist instructor : instructors) {

            if (typeInstructor.equals("instructor")) {

            } else if (typeInstructor.equals("instructor-athlete")) {
                if (!instructor.getTouristType().equals(ATHLETE)) {
                    minus.add(instructor);
                }
            } else if (typeInstructor.equals("instructor-coach")) {
                if (!instructor.getTouristType().equals(COACH)) {
                    minus.add(instructor);
                }
            }
        }

        instructors.removeAll(minus);
        minus.clear();


        StoppingPoint point = stoppingPointRepository.findById(idPoint).get();

        List<PlannedCamping> campings = new ArrayList<>();

        for (PlannedCamping camping : plannedCampingRepository.findAll()) {
            for (StoppingPoint stoppingPoint : camping.getPlan().getRoute().getPoints()) {
                if (stoppingPoint.equals(point)) {
                    campings.add(camping);
                }
            }
        }

        Set<Tourist> result = new HashSet<>();

        for (Tourist instructor : instructors) {
            for (PlannedCamping camping : campings) {
                if (camping.getInstructor().equals(instructor) || camping.getTourists().contains(instructor)) {
                    result.add(instructor);
                }
            }
        }

        return new ArrayList<>(result);
    }

    public void save(Tourist tourist) {
        touristRepository.save(tourist);
    }

    public Object getTouristsNotAthlete() {
        List<Tourist> result = new ArrayList<>();
        for (Tourist tourist : touristRepository.findAll()) {
            if (tourist.getTouristType() != ATHLETE) {
                result.add(tourist);
            }
        }
        return result;
    }
}

