package nsu.trushkov.TouristClub.controllers;

import lombok.RequiredArgsConstructor;
import nsu.trushkov.TouristClub.entity.Tourist;
import nsu.trushkov.TouristClub.entity.enums.TouristType;
import nsu.trushkov.TouristClub.services.AthleteService;
import nsu.trushkov.TouristClub.services.TouristService;
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
public class AthleteController {
    private final TouristService touristService;
    private final AthleteService athleteService;

    @GetMapping("/athlete")
    public String athlete(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));

        model.addAttribute("isAdmin", isAdmin);
        return "athlete/athlete_main";
    }

    @GetMapping("/athlete/create")
    public String athleteCreate(Model model){
        boolean isCreated = false;
        model.addAttribute("isCreated", isCreated);
        model.addAttribute("tourists",touristService.getTouristsNotAthlete());
        return "athlete/athlete_create";
    }

    @PostMapping("/athlete/create")
    public String createAthlete(Long touristId, @RequestParam("yearOfWorkExperience") Integer yearOfWorkExperience,
                                Model model) {

        Tourist tourist = touristService.getTouristById(touristId);
        tourist.setTouristType(TouristType.ATHLETE);
        athleteService.createAthlete(tourist, yearOfWorkExperience);

        boolean isCreated = true;
        model.addAttribute("isCreated", isCreated);
        model.addAttribute("tourists",touristService.getTouristsWithoutType());
        return "athlete/athlete_create";
    }

    @GetMapping("/athlete/delete")
    public String athleteDel(Model model){
        boolean isDeleted = false;
        model.addAttribute("isDeleted", isDeleted);
        model.addAttribute("athletes", athleteService.getAthlete());
        return "athlete/athlete_delete";
    }

    @PostMapping("/athlete/delete/{id}")
    public String athleteDeletePost(@PathVariable long id, Model model){
        athleteService.deleteAthlete(id);
        Tourist tourist = touristService.getTouristById(id);
        tourist.setTouristType(TouristType.AMATEUR);
        touristService.save(tourist);
        boolean isDeleted = true;
        model.addAttribute("isDeleted", isDeleted);
        model.addAttribute("athletes", athleteService.getAthlete());
        return "athlete/athlete_delete";
    }


    @GetMapping("/athlete/read")
    public String athleteRead(Model model){
        model.addAttribute("athletes", athleteService.getAthlete());
        return "athlete/athlete_read";
    }



    @GetMapping("/athlete/update")
    public String athleteUpdateGet(Model model){
        model.addAttribute("athletes", athleteService.getAthlete());
        return "athlete/athlete_update";
    }

    @PostMapping("/athlete/update/{id}")
    public String athleteUpdatePost(@RequestParam("yearOfWorkExperience") Integer yearOfWorkExperience,
                                    @PathVariable long id, Model model){
        athleteService.updateAthlete(id, yearOfWorkExperience);
        model.addAttribute("athletes", athleteService.getAthlete());
        return "athlete/athlete_update";
    }


}
