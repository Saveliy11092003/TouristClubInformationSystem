package nsu.trushkov.TouristClub.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nsu.trushkov.TouristClub.entity.Tourist;
import nsu.trushkov.TouristClub.services.PlanService;
import nsu.trushkov.TouristClub.services.PlannedCampingService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PlannedCampingController {
    private final PlannedCampingService plannedCampingService;
    private final PlanService planService;

    @GetMapping("/planned_camping")
    public String planned(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));

        model.addAttribute("isAdmin", isAdmin);
        return "planned_camping/planned_camping_main";
    }

    @GetMapping("/planned_camping/create")
    public String plannedCreate(Model model){
        boolean isCreated = false;
        model.addAttribute("isCreated", isCreated);
        model.addAttribute("plans",planService.getPlans());
        return "planned_camping/planned_camping_create";
    }

    @GetMapping("/planned_camping/create/choose_plan")
    public String createPlannedChoose(@RequestParam("idPlan") Long idPlan,
                                Model model) {

        List<Tourist> tourist = planService.getTouristsByTypeOfCamping(idPlan);
        List<Tourist> instructors = planService.getInstructors(idPlan);

        boolean isCreated = false;
        model.addAttribute("isCreated", isCreated);
        model.addAttribute("idPlan", idPlan);
        model.addAttribute("tourists",tourist);
        model.addAttribute("instructors",instructors);
        return "planned_camping/planned_camping_create_2";
    }

    @PostMapping("/planned_camping/create/choose_plan")
    public String createPlanned(@RequestParam("idPlan") Long idPlan,
                                @RequestParam("idTourists") List<Long> idTourists,
                                @RequestParam("idInstructor") Long idInstructor,
                                @RequestParam("name") String name,
                                @RequestParam("diary") String diary,
                                Model model) {

        boolean isCreated = true;
        model.addAttribute("isCreated", isCreated);
        log.info(idPlan.toString());
        log.info(idTourists.toString());
        log.info(idInstructor.toString());
        log.info(name.toString());
        log.info(diary.toString());


        plannedCampingService.createPlannedCamping(idPlan, idInstructor, idTourists, name, diary);

        model.addAttribute("plans",planService.getPlans());
        return "planned_camping/planned_camping_create";
    }

    @GetMapping("/planned_camping/read")
    public String groupRead(Model model){
        model.addAttribute("campings", plannedCampingService.getCampings());
        return "planned_camping/planned_camping_read";
    }

    @GetMapping("/planned_camping/delete")
    public String group(Model model){
        boolean isDeleted = false;
        model.addAttribute("isDeleted", isDeleted);
        model.addAttribute("campings", plannedCampingService.getCampings());
        return "planned_camping/planned_camping_delete";
    }

    @PostMapping("/planned_camping/delete/{id}")
    public String groupDeletePost(@PathVariable long id, Model model){
        plannedCampingService.deleteCamping(id);
        boolean isDeleted = true;
        model.addAttribute("isDeleted", isDeleted);
        model.addAttribute("campings", plannedCampingService.getCampings());
        return "planned_camping/planned_camping_delete";
    }

    @GetMapping("/planned_camping/update")
    public String groupUpdateGet(Model model){
        model.addAttribute("campings", plannedCampingService.getCampings());
        model.addAttribute("plans",planService.getPlans());
        return "planned_camping/planned_camping_update";
    }

    @PostMapping("/planned_camping/update/{id}")
    public String groupUpdatePost(@PathVariable long id, @RequestParam("idPlan") Long idPlan,
                                  @RequestParam("name") String name,
                                  @RequestParam("diary") String diary, Model model){
        plannedCampingService.updateCampingService(id, idPlan, name, diary);
        model.addAttribute("campings", plannedCampingService.getCampings());
        model.addAttribute("plans",planService.getPlans());
        return "planned_camping/planned_camping_update";
    }

}
