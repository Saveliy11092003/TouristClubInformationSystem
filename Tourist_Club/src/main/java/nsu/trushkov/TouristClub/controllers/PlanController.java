package nsu.trushkov.TouristClub.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nsu.trushkov.TouristClub.services.PlanService;
import nsu.trushkov.TouristClub.services.RouteService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PlanController {
    private final PlanService planService;
    private final RouteService routeService;

    @GetMapping("/plan")
    public String plan(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));

        model.addAttribute("isAdmin", isAdmin);
        return "plan/plan_main";
    }

    @GetMapping("/plan/create")
    public String planCreate(Model model){
        boolean isCreated = false;
        model.addAttribute("isCreated", isCreated);
        model.addAttribute("routes",routeService.getRoute());
        return "plan/plan_create";
    }

    @PostMapping("/plan/create")
    public String createPlan(@RequestParam("planName") String planName,
                             @RequestParam("startCamping") String startCamping,
                             @RequestParam("endCamping") String endCamping,
                             @RequestParam("routeId") Long idRoute,
                             @RequestParam("type") String type,
                             Model model) {
        boolean isCreated = true;
        model.addAttribute("isCreated", isCreated);
        planService.createPlan(planName, startCamping, endCamping, idRoute, type);

        model.addAttribute("routes",routeService.getRoute());
        return "plan/plan_create";
    }

    @GetMapping("/plan/read")
    public String planRead(Model model){
        model.addAttribute("plans", planService.getPlans());
        return "plan/plan_read";
    }

    @GetMapping("/plan/delete")
    public String planDel(Model model){
        boolean isDeleted = false;
        model.addAttribute("isDeleted", isDeleted);
        model.addAttribute("plans", planService.getPlans());
        return "plan/plan_delete";
    }


    @PostMapping("/plan/delete/{id}")
    public String planDeletePost(@PathVariable long id, Model model){
        planService.deletePlan(id);
        boolean isDeleted = true;
        model.addAttribute("isDeleted", isDeleted);
        model.addAttribute("plans", planService.getPlans());
        return "plan/plan_delete";
    }

    @GetMapping("/plan/update")
    public String planUpdateGet(Model model){
        model.addAttribute("plans", planService.getPlans());
        model.addAttribute("routes", routeService.getRoute());
        return "plan/plan_update";
    }



    @PostMapping("/plan/update/{id}")
    public String planUpdatePost(@RequestParam("planName") String planName,
                                 @RequestParam("startCamping") String startCamping,
                                 @RequestParam("endCamping") String endCamping,
                                 @RequestParam("routeId") Long routeId,
                                           @PathVariable long id, Model model){
        planService.updatePlan(id, planName, startCamping, endCamping, routeId);
        model.addAttribute("plans", planService.getPlans());
        model.addAttribute("routes", routeService.getRoute());
        return "plan/plan_update";
    }
    
}
