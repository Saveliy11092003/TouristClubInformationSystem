package nsu.trushkov.TouristClub.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nsu.trushkov.TouristClub.services.StoppingPointService;
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
public class StoppingPointController {
    private final StoppingPointService stoppingPointService;

    @GetMapping("/stopping_point")
    public String stopping_point(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));

        model.addAttribute("isAdmin", isAdmin);
        return "stopping_point/stopping_point_main";
    }

    @GetMapping("/stopping_point/read")
    public String stoppingPointRead(Model model){
        model.addAttribute("stopping_points", stoppingPointService.getStoppingPoints());
        return "stopping_point/stopping_point_read";
    }

    @GetMapping("/stopping_point/create")
    public String stopping_pointCreate(Model model){
        boolean isCreated = false;
        model.addAttribute("isCreated", isCreated);
        return "stopping_point/stopping_point_create";
    }

    @PostMapping("/stopping_point/create")
    public String createStoppingPoint(@RequestParam("stoppingPointName") String stoppingPointName,
                                      Model model) {

        log.info("stoppingPointName" + stoppingPointName + "..........");
        stoppingPointService.createStoppingPoint(stoppingPointName);

        boolean isCreated = true;
        model.addAttribute("isCreated", isCreated);
        return "stopping_point/stopping_point_create";
    }

    @GetMapping("/stopping_point/delete")
    public String stopping_pointDel(Model model){
        boolean isDeleted = false;
        model.addAttribute("isDeleted", isDeleted);
        model.addAttribute("stopping_points", stoppingPointService.getStoppingPoints());
        return "stopping_point/stopping_point_delete";
    }

    @PostMapping("/stopping_point/delete/{id}")
    public String stopping_pointDeletePost(@PathVariable String id, Model model){
        log.info("Строка " + id);
        id = id.replaceAll("\\D", "");
        stoppingPointService.deleteStoppingPoint(id);
        boolean isDeleted = true;
        model.addAttribute("isDeleted", isDeleted);
        model.addAttribute("stopping_points", stoppingPointService.getStoppingPoints());
        return "stopping_point/stopping_point_delete";
    }

    @GetMapping("/stopping_point/update")
    public String stopping_pointUpdateGet(Model model){
        model.addAttribute("stopping_points", stoppingPointService.getStoppingPoints());
        return "stopping_point/stopping_point_update";
    }

    @PostMapping("/stopping_point/update/{id}")
    public String stopping_pointUpdatePost(@RequestParam("stoppingPointName") String stoppingPointName,
                                    @PathVariable long id, Model model){
        stoppingPointService.updateStoppingPoint(id, stoppingPointName);
        model.addAttribute("stopping_points", stoppingPointService.getStoppingPoints());
        return "stopping_point/stopping_point_update";
    }
    
    
}
