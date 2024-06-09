package nsu.trushkov.TouristClub.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nsu.trushkov.TouristClub.entity.Plan;
import nsu.trushkov.TouristClub.entity.Route;
import nsu.trushkov.TouristClub.entity.Tourist;
import nsu.trushkov.TouristClub.entity.TypeOfSection;
import nsu.trushkov.TouristClub.entity.enums.DifficultyOfRoute;
import nsu.trushkov.TouristClub.entity.enums.TypeOfCamping;
import nsu.trushkov.TouristClub.entity.enums.TypeOfSport;
import nsu.trushkov.TouristClub.repositories.PlanRepository;
import nsu.trushkov.TouristClub.repositories.RouteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlanService {
    private final PlanRepository planRepository;
    private final RouteRepository routeRepository;
    private final TouristService touristService;

    public void createPlan(String planName, String startCamping, String endCamping, Long idRoute, String type) {
        Route route = routeRepository.findById(idRoute).get();

        TypeOfCamping typeOfCamping;

        if (type.equals("hiking")) {
            typeOfCamping = TypeOfCamping.HIKING;
        } else if (type.equals("water")) {
            typeOfCamping = TypeOfCamping.WATER_TRIP;
        } else if(type.equals("mountain")) {
            typeOfCamping = TypeOfCamping.MOUNTAIN_HIKE;
        } else {
            typeOfCamping = TypeOfCamping.HORSE_RIDING;
        }

        Plan plan = Plan.builder().planName(planName).startCamping(startCamping)
                .endCamping(endCamping).route(route).typeOfCamping(typeOfCamping).build();
        planRepository.save(plan);
    }

    public List<Plan> getPlans() {
        return planRepository.findAll();
    }

    public void deletePlan(long id) {
        planRepository.deleteById(id);
    }

    public void updatePlan(long id, String planName, String startCamping, String endCamping, Long routeId) {
        Plan plan = planRepository.findById(id).get();
        plan.setPlanName(planName);
        plan.setStartCamping(startCamping);
        plan.setEndCamping(endCamping);

        Route routeOld = plan.getRoute();
        routeOld.removePlan(plan);
        log.info("route old " + routeOld);
        log.info("plan " + plan);

        Route routeNew = routeRepository.findById(routeId).get();
        log.info("route new " + routeNew);
        routeNew.addPlan(plan);
        routeRepository.save(routeOld);
        routeRepository.save(routeNew);
        planRepository.save(plan);
    }

    public List<Tourist> getTouristsByTypeOfCamping(Long idPlan) {
        Plan plan = planRepository.findById(idPlan).get();
        TypeOfCamping typeOfCamping = plan.getTypeOfCamping();

        List<Tourist> tourists = touristService.getTouristsByTypeOfCamping(typeOfCamping);
        return tourists;
    }

    public List<Tourist>  getInstructors(Long idPlan) {
        Plan plan = planRepository.findById(idPlan).get();
        DifficultyOfRoute difficultyOfRoute = plan.getRoute().getDifficultyOfRoute();
        TypeOfCamping typeOfCamping = plan.getTypeOfCamping();

        List<Tourist> instructors = touristService.getInstructors(difficultyOfRoute, typeOfCamping);
        return instructors;
    }

}
