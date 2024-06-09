package nsu.trushkov.TouristClub.controllers;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import nsu.trushkov.TouristClub.entity.Coach;
import nsu.trushkov.TouristClub.entity.Tourist;
import nsu.trushkov.TouristClub.entity.enums.TouristType;
import nsu.trushkov.TouristClub.services.AmateurService;
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
public class AmateurController {
    private final TouristService touristService;
    private final AmateurService amateurService;

    @GetMapping("/amateur")
    public String amateur(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));

        model.addAttribute("isAdmin", isAdmin);
        return "amateur/amateur_main";
    }


    @GetMapping("/amateur/create")
    public String amateurCreate(Model model){
        boolean isCreated = false;
        model.addAttribute("isCreated", isCreated);
        model.addAttribute("tourists",touristService.getTouristsWithoutType());
        return "amateur/amateur_create";
    }

    @PostMapping("/amateur/create")
    public String createAmateur(Long touristId, @RequestParam("yearOfWorkExperience") Integer yearOfWorkExperience,
                                Model model) {

        Tourist tourist = touristService.getTouristById(touristId);
        tourist.setTouristType(TouristType.AMATEUR);
        amateurService.createAmateur(tourist, yearOfWorkExperience);

        boolean isCreated = true;
        model.addAttribute("isCreated", isCreated);
        model.addAttribute("tourists",touristService.getTouristsWithoutType());
        return "amateur/amateur_create";
    }

    @GetMapping("/amateur/delete")
    public String amateurDel(Model model){
        boolean isDeleted = false;
        model.addAttribute("isDeleted", isDeleted);
        model.addAttribute("amateurs", amateurService.getAmateurs());
        return "amateur/amateur_delete";
    }

    @PostMapping("/amateur/delete/{id}")
    public String amateurDeletePost(@PathVariable long id, Model model){
        amateurService.deleteAmateur(id);
        Tourist tourist = touristService.getTouristById(id);
        tourist.setTouristType(TouristType.AMATEUR);
        touristService.save(tourist);
        boolean isDeleted = true;
        model.addAttribute("isDeleted", isDeleted);
        model.addAttribute("amateurs", amateurService.getAmateurs());
        return "amateur/amateur_delete";
    }


    @GetMapping("/amateur/read")
    public String amateurRead(Model model){
        model.addAttribute("amateurs", amateurService.getAmateurs());
        return "amateur/amateur_read";
    }



    @GetMapping("/amateur/update")
    public String amateurUpdateGet(Model model){
        model.addAttribute("amateurs", amateurService.getAmateurs());
        return "amateur/amateur_update";
    }

    @PostMapping("/amateur/update/{id}")
    public String amateurUpdatePost(@RequestParam("yearOfWorkExperience") Integer yearOfWorkExperience,
                                    @PathVariable long id, Model model){
        amateurService.updateAmateur(id, yearOfWorkExperience);
        model.addAttribute("amateurs", amateurService.getAmateurs());
        return "amateur/amateur_update";
    }


}
