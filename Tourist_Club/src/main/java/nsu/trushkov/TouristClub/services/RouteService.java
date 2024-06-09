package nsu.trushkov.TouristClub.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nsu.trushkov.TouristClub.entity.*;
import nsu.trushkov.TouristClub.entity.enums.DifficultyOfRoute;
import nsu.trushkov.TouristClub.repositories.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static nsu.trushkov.TouristClub.entity.enums.DifficultyOfRoute.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class RouteService {
    private final RouteRepository routeRepository;
    private final StoppingPointRepository stoppingPointRepository;
    private final SectionRepository sectionRepository;
    private final SectionWorkScheduleRepository sectionWorkScheduleRepository;
    private final PlannedCampingRepository plannedCampingRepository;
    private final TouristRepository touristRepository;

    public void createRoute(List<Long> ids, String routeName, String length, String difficultyRoute) {
        Route route = new Route();
        for (Long id : ids) {
            StoppingPoint stoppingPoint = stoppingPointRepository.findById(id).get();
            route.addPoint(stoppingPoint);
        }
        route.setRouteLength(length);
        route.setRouteName(routeName);

        if (difficultyRoute.equals("easy")) {
            route.setDifficultyOfRoute(EASY);
        } else if(difficultyRoute.equals("middle")) {
            route.setDifficultyOfRoute(DifficultyOfRoute.MIDDLE);
        } else {
            route.setDifficultyOfRoute(DifficultyOfRoute.HARD);
        }

        routeRepository.save(route);
    }

    public List<Route> getRoute() {
        return routeRepository.findAll();
    }

    public void deleteRoute(long id) {
        log.info("In deleteRoute");
        Route route = routeRepository.findById(id).get();
        log.info("Route for delete - " + route);
        route.removePoints();
        routeRepository.deleteById(id);
    }

    public void updateRoute(long id, List<Long> idsPoints, String nameRoute, String length, String difficultyRoute) {
        Route route = routeRepository.findById(id).get();
        route.setRouteName(nameRoute);
        route.setRouteLength(length);

        log.info("CHECK " + difficultyRoute);
        if (difficultyRoute.equals("easy")) {
            log.info("TRUE EASY");
            route.setDifficultyOfRoute(EASY);
        } else if(difficultyRoute.equals("middle")) {
            log.info("TRUE MIDDLE");
            route.setDifficultyOfRoute(DifficultyOfRoute.MIDDLE);
        } else {
            log.info("TRUE HARD");
            route.setDifficultyOfRoute(DifficultyOfRoute.HARD);
        }

        List<StoppingPoint> stoppingPoints = new ArrayList<>();
        for (Long idPoint : idsPoints) {
            StoppingPoint stoppingPoint = stoppingPointRepository.findById(idPoint).get();
            stoppingPoints.add(stoppingPoint);
        }
        route.updatePoints(stoppingPoints);

        routeRepository.save(route);
    }

    public List<Route> requestRoute(String difficultyRoute, Integer length, Long idPoint) {
        List<Route> routes = routeRepository.findAll();
        List<Route> minus = new ArrayList<>();

        DifficultyOfRoute difficulty = EASY;

        if (difficultyRoute.equals("EASY")) {
            difficulty = EASY;
        } else if(difficultyRoute.equals("MIDDLE")) {
            difficulty = MIDDLE;
        } else {
            difficulty = HARD;
        }

        for (Route route : routes) {
            if (!route.getDifficultyOfRoute().equals(difficulty)) {
                minus.add(route);
            }
        }

        routes.removeAll(minus);
        minus.clear();

        for (Route route : routes) {
            if (Integer.parseInt(route.getRouteLength()) <= length) {
                minus.add(route);
            }
        }
        routes.removeAll(minus);
        minus.clear();

        StoppingPoint pointSpecified = stoppingPointRepository.findById(idPoint).get();

        for (Route route : routes) {
            boolean pointPresent = false;
            for (StoppingPoint point : route.getPoints()) {
                if(point.equals(pointSpecified)) {
                    pointPresent = true;
                }
            }
            if (pointPresent == false) {
                minus.add(route);
            }
        }

        routes.removeAll(minus);
        minus.clear();
        return routes;
    }

    public List<Route> requestRoute8_1(Long idSection, String dateStart, String dateEnd) {
        Section section = sectionRepository.findById(idSection).get();

        Set<Route> routes = new HashSet<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(dateStart, formatter);
        LocalDate endDate = LocalDate.parse(dateEnd, formatter);

        for (Group group : section.getGroups()) {
            for (Tourist tourist : group.getTourists()) {
                for (PlannedCamping plannedCamping : plannedCampingRepository.findAll()) {
                    for (Tourist touristFromCamping : plannedCamping.getTourists()) {
                        if (tourist.equals(touristFromCamping)) {
                            LocalDate scheduleStart = LocalDate.parse(plannedCamping.getPlan().getStartCamping());
                            LocalDate scheduleEnd = LocalDate.parse(plannedCamping.getPlan().getEndCamping());

                            if ((scheduleStart.isEqual(startDate) || scheduleStart.isAfter(startDate)) &&
                                    (scheduleEnd.isEqual(endDate) || scheduleEnd.isBefore(endDate))) {
                                routes.add(plannedCamping.getPlan().getRoute());
                            }

                        }
                    }
                }
            }
        }

        return new ArrayList<>(routes);
    }

    public List<Route> requestRoute8_2(Long idInstructor) {
        Set<Route> routes = new HashSet<>();
        Tourist instructor = touristRepository.findById(idInstructor).get();

        for (PlannedCamping camping : plannedCampingRepository.findAll()) {
            if (instructor.equals(camping.getInstructor())) {
                routes.add(camping.getPlan().getRoute());
            }
        }
        return new ArrayList<>(routes);
    }

    public List<Route> requestRoute8_3(Integer count) {
        List<Route> routes = new ArrayList<>();

        Map<Route, Integer> map = new HashMap<>();

        for (PlannedCamping camping : plannedCampingRepository.findAll()) {
            Route route = camping.getPlan().getRoute();

            if (!map.containsKey(route)) {
                map.put(route, 1);
            } else {
                map.put(route, map.get(route) + 1);
            }

        }

        for(Map.Entry<Route, Integer> entry : map.entrySet()) {
            if (entry.getValue().equals(count)) {
                routes.add(entry.getKey());
            }
        }
        return routes;
    }
}
