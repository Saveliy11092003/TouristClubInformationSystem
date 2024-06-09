package nsu.trushkov.TouristClub.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nsu.trushkov.TouristClub.entity.Coach;
import nsu.trushkov.TouristClub.entity.Tourist;
import nsu.trushkov.TouristClub.entity.enums.TouristType;
import nsu.trushkov.TouristClub.repositories.GroupRepository;
import nsu.trushkov.TouristClub.services.CoachService;
import nsu.trushkov.TouristClub.services.GroupService;
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

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CoachController {
    private final CoachService coachService;
    private final TouristService touristService;
    private final SectionService sectionService;
    private final GroupService groupService;

    @GetMapping("/coach")
    public String coach(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));

        model.addAttribute("isAdmin", isAdmin);
        return "coach/coach_main";
    }

    @GetMapping("/coach/create")
    public String coachCreate(Model model){
        boolean isCreated = false;
        model.addAttribute("isCreated", isCreated);
        model.addAttribute("tourists",touristService.getTouristsNotCoach());
        return "coach/coach_create";
    }

    @PostMapping("/coach/create")
    public String createCoach(Long touristId, String salary, String speciality, Model model) {
        log.info("id" + touristId);

        Tourist tourist = touristService.getTouristById(touristId);
        tourist.setTouristType(TouristType.COACH);
        coachService.createCoach(tourist, salary, speciality);

        boolean isCreated = true;
        model.addAttribute("isCreated", isCreated);
        model.addAttribute("tourists",touristService.getTouristsWithoutType());
        return "coach/coach_create";
    }

    @GetMapping("/coach/read")
    public String coachRead(Model model){
        model.addAttribute("coaches", coachService.getCoaches());
        return "coach/coach_read";
    }

    @GetMapping("/coach/request")
    public String coachRequestAll(){
        return "coach/coach_requests";
    }

    @GetMapping("/coach/request1")
    public String coachRequestGet(Model model){
        model.addAttribute("sections", sectionService.getSections());
        return "coach/coach_request1";
    }

    @PostMapping("/coach/request1")
    public String coachRequestPost(Model model, @RequestParam(name = "idSection", defaultValue = "0") Long idSection,
                                   @RequestParam(name = "allSection", defaultValue = "no") String allSection,
                                   @RequestParam("sex") String sex, @RequestParam("speciality") String speciality,
                                   @RequestParam(name = "salary", defaultValue = "no") String salary,
                                   @RequestParam(name = "allSalary", defaultValue = "no") String allSalary,
                                   @RequestParam(name = "age", defaultValue = "no") String age,
                                   @RequestParam(name = "allAge", defaultValue = "no") String allAge){
        List<Coach> coaches = coachService.request(idSection, allSection, speciality, salary, allSalary, age, allAge, sex);
        model.addAttribute("coaches", coaches);
        model.addAttribute("countOfCoaches", coaches.size());
        return "coach/coach_result";
    }


    @GetMapping("/coach/request2")
    public String coachRequest2Get(Model model){
        model.addAttribute("groups", groupService.getGroups());
        return "coach/coach_request2";
    }

    @PostMapping("/coach/request2")
    public String coachRequest2Post(Model model, @RequestParam("idGroup") Long idGroup,
                                    @RequestParam("dateStart") String dateStart,
                                    @RequestParam("dateEnd") String dateEnd) {
        List<Coach> coaches = coachService.request2(idGroup, dateStart, dateEnd);
        model.addAttribute("coaches", coaches);
        model.addAttribute("countOfCoaches", coaches.size());
        return "coach/coach_result";
    }

    @GetMapping("/coach/delete")
    public String coachDel(Model model){
        boolean isDeleted = false;
        model.addAttribute("isDeleted", isDeleted);
        model.addAttribute("coaches", coachService.getCoaches());
        return "coach/coach_delete";
    }

    @PostMapping("/coach/delete/{id}")
    public String coachDeletePost(@PathVariable long id, Model model){
        coachService.removeCoach(id);
        Tourist tourist = touristService.getTouristById(id);
        tourist.setTouristType(TouristType.AMATEUR);
        touristService.save(tourist);
        boolean isDeleted = true;
        model.addAttribute("isDeleted", isDeleted);
        model.addAttribute("coaches", coachService.getCoaches());
        return "coach/coach_delete";
    }

    @GetMapping("/coach/update")
    public String coachUpdateGet(Model model){
        model.addAttribute("coaches", coachService.getCoaches());
        return "coach/coach_update";
    }

    @PostMapping("/coach/update/{id}")
    public String coachUpdatePost(Coach coach, @PathVariable long id, Model model){
        coachService.updateCoach(id, coach);
        List<Coach> coaches = coachService.getCoaches();
        model.addAttribute("coaches", coaches);
        return "coach/coach_update";
    }

    @GetMapping("/coach/request3")
    public String coachRequest3Get(Model model){
        model.addAttribute("sections", sectionService.getSections());
        model.addAttribute("coaches", coachService.getCoaches());
        return "coach/coach_request3";
    }

    @PostMapping("/coach/request3")
    public String coachRequest3Post(Model model, @RequestParam("idCoach") Long idCoach,
                                    @RequestParam("dateStart") String dateStart,
                                    @RequestParam("dateEnd") String dateEnd) {
        String totalNumberOfHours = "";
        List<String> trainingHour = new ArrayList<>();
        totalNumberOfHours = coachService.request3(idCoach, dateStart, dateEnd, trainingHour);
        log.info("list " + trainingHour);
        log.info("total" + totalNumberOfHours);
        model.addAttribute("coach", coachService.getCoach(idCoach));
        model.addAttribute("total", totalNumberOfHours);
        model.addAttribute("trainingHour", trainingHour);
        return "coach/coach_result_7_1";
    }

    @GetMapping("/coach/request4")
    public String coachRequest4Get(Model model){
        model.addAttribute("sections", sectionService.getSections());
        model.addAttribute("coaches", coachService.getCoaches());
        return "coach/coach_request4";
    }

    @PostMapping("/coach/request4")
    public String coachRequest4Post(Model model, @RequestParam("idSection") Long idSection,
                                    @RequestParam("dateStart") String dateStart,
                                    @RequestParam("dateEnd") String dateEnd) {
        String totalNumberOfHours = "";
        List<String> trainingHour = new ArrayList<>();
        totalNumberOfHours = coachService.request4(idSection, dateStart, dateEnd, trainingHour);
        log.info("list " + trainingHour);
        log.info("total" + totalNumberOfHours);
        model.addAttribute("section", sectionService.getSection(idSection));
        model.addAttribute("total", totalNumberOfHours);
        model.addAttribute("trainingHour", trainingHour);
        return "coach/coach_result_7_2";
    }

}
