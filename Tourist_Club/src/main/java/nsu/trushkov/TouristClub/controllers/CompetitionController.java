package nsu.trushkov.TouristClub.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nsu.trushkov.TouristClub.entity.Competition;
import nsu.trushkov.TouristClub.services.AthleteService;
import nsu.trushkov.TouristClub.services.CompetitionService;
import nsu.trushkov.TouristClub.services.SectionService;
import nsu.trushkov.TouristClub.services.TouristService;
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
public class CompetitionController {
    private final CompetitionService competitionService;
    private final AthleteService athleteService;
    private final SectionService sectionService;
    
    @GetMapping("/competition")
    public String competition(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));

        model.addAttribute("isAdmin", isAdmin);
        return "competition/competition_main";
    }

    @GetMapping("/competition/read")
    public String competitionRead(Model model){
        model.addAttribute("competitions", competitionService.getCompetitions());
        return "competition/competition_read";
    }

    @GetMapping("/competition/create")
    public String competitionCreate(Model model){
        boolean isCreated = false;
        model.addAttribute("isCreated", isCreated);
        model.addAttribute("athletes",athleteService.getAthlete());

        return "competition/competition_create";
    }

    @PostMapping("/competition/create")
    public String createCompetition(@RequestParam("nameCompetition") String nameCompetition,
                                    @RequestParam("description") String description,
                                    @RequestParam("athleteId") List<Long> athleteIds,
                                    Model model) {

        competitionService.createCompetition(nameCompetition, description, athleteIds);

        boolean isCreated = true;
        model.addAttribute("isCreated", isCreated);
        model.addAttribute("athletes",athleteService.getAthlete());
        return "competition/competition_create";
    }

    @GetMapping("/competition/delete")
    public String competitionDel(Model model){
        boolean isDeleted = false;
        model.addAttribute("isDeleted", isDeleted);
        model.addAttribute("competitions", competitionService.getCompetitions());
        return "competition/competition_delete";
    }

    @PostMapping("/competition/delete/{id}")
    public String competitionDeletePost(@PathVariable long id, Model model){
        competitionService.deleteCompetition(id);
        boolean isDeleted = true;
        model.addAttribute("isDeleted", isDeleted);
        model.addAttribute("competitions", competitionService.getCompetitions());
        return "competition/competition_delete";
    }

    @GetMapping("/competition/update")
    public String competitionUpdateGet(Model model){
        model.addAttribute("competitions", competitionService.getCompetitions());
        model.addAttribute("athletes", athleteService.getAthlete());
        log.info(athleteService.getAthlete().toString());
        return "competition/competition_update";
    }

    @PostMapping("/competition/update/{id}")
    public String competitionUpdatePost(@PathVariable long id,
                                        @RequestParam("selectedAthletes") List<Long> idsAthletes,
                                  @RequestParam("nameCompetition") String nameCompetition,
                                        @RequestParam("description") String description, Model model){
        competitionService.updateCompetition(id, idsAthletes, nameCompetition, description);
        model.addAttribute("competitions", competitionService.getCompetitions());
        model.addAttribute("athletes", athleteService.getAthlete());
        return "competition/competition_update";
    }

    @GetMapping("/competition/request")
    public String competitionRequestGet(Model model){
        model.addAttribute("sections", sectionService.getSections());
        return "competition/competition_request";
    }

    @GetMapping("/competition/result")
    public String competitionRequestPost(Model model, @RequestParam(name = "idSection", defaultValue = "0") Long idSection,
                                         @RequestParam(name = "allSection", defaultValue = "no") String allSection){

        List<Competition> competitions = competitionService.getCompetitionsBySection(idSection, allSection);

        model.addAttribute("competitions", competitions);
        model.addAttribute("countOfCompetition", competitions.size());
        return "competition/competition_result";
    }

    
}
