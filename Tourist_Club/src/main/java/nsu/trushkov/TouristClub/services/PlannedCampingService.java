package nsu.trushkov.TouristClub.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nsu.trushkov.TouristClub.entity.Plan;
import nsu.trushkov.TouristClub.entity.PlannedCamping;
import nsu.trushkov.TouristClub.entity.Tourist;
import nsu.trushkov.TouristClub.repositories.PlanRepository;
import nsu.trushkov.TouristClub.repositories.PlannedCampingRepository;
import nsu.trushkov.TouristClub.repositories.TouristRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlannedCampingService {
    private final PlannedCampingRepository plannedCampingRepository;
    private final PlanRepository planRepository;
    private final TouristRepository touristRepository;

    public void createPlannedCamping(Long idPlan, Long idInstructor, List<Long> idTourists, String name,
                                     String diary) {
        PlannedCamping plannedCamping = PlannedCamping.builder().diary(diary).name(name).build();
        Plan plan = planRepository.findById(idPlan).get();

        for (Long id : idTourists) {
            plannedCamping.addTourist(touristRepository.findById(id).get());
        }

        Tourist instructor = touristRepository.findById(idInstructor).get();

        plannedCamping.setPlan(plan);
        plannedCamping.setInstructor(instructor);

        plannedCampingRepository.save(plannedCamping);

    }

    public List<PlannedCamping> getCampings() {
        return plannedCampingRepository.findAll();
    }


    public void deleteCamping(long id) {
        plannedCampingRepository.deleteById(id);
    }

    public void updateCampingService(long id, Long idPlan, String name, String diary) {
        PlannedCamping plannedCamping = plannedCampingRepository.findById(id).get();
        Plan plan = planRepository.findById(idPlan).get();
        plannedCamping.setPlan(plan);
        plannedCamping.setName(name);
        plannedCamping.setDiary(diary);
        plannedCampingRepository.save(plannedCamping);
    }

    public List<Tourist> getAllInstructors() {
        Set<Tourist> tourists = new HashSet<>();
        for (PlannedCamping camping : plannedCampingRepository.findAll()) {
            tourists.add(camping.getInstructor());
        }
        return new ArrayList<>(tourists);
    }
}
