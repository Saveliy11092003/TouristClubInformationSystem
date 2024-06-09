package nsu.trushkov.TouristClub.controllers;

import lombok.RequiredArgsConstructor;
import nsu.trushkov.TouristClub.entity.Section;
import nsu.trushkov.TouristClub.entity.Supervisor;
import nsu.trushkov.TouristClub.entity.enums.TypeOfSport;
import nsu.trushkov.TouristClub.repositories.TypeOfSectionRepository;
import nsu.trushkov.TouristClub.services.SectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class SectionController {
    private static final Logger log = LoggerFactory.getLogger(SectionController.class);
    private final SectionService sectionService;
    private final TypeOfSectionRepository typeOfSectionRepository;

    @GetMapping("/section")
    public String section(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));

        model.addAttribute("isAdmin", isAdmin);
        return "section/section_main";
    }

    @GetMapping("/section/create")
    public String sectionCreate(Model model){
        boolean isCreated = false;
        model.addAttribute("isCreated", isCreated);
        return "section/section_create";
    }

    @GetMapping("/section/read")
    public String sectionRead(Model model){
        model.addAttribute("sections", sectionService.getSections());
        return "section/section_read";
    }

    @GetMapping("/section/update")
    public String sectionUpdateGet(Model model){
        model.addAttribute("sections", sectionService.getSections());
        return "section/section_update";
    }

    @PostMapping("/section/update/{id}")
    public String sectionUpdatePost(Section section, @PathVariable long id, Model model){
        sectionService.updateSection(id, section);
        model.addAttribute("sections", sectionService.getSections());
        return "section/section_update";
    }

    @GetMapping("/section/delete")
    public String sectionDeleteGet(Model model){
        boolean isDeleted = false;
        model.addAttribute("isDeleted", isDeleted);
        model.addAttribute("sections", sectionService.getSections());
        return "section/section_delete";
    }

    @PostMapping("/section/delete/{id}")
    public String sectionDeletePost(@PathVariable long id, Model model){
        log.info("id " + id);
        for (Section section : sectionService.getSections()) {
            System.out.println(section);
        }
        sectionService.removeSection(id);
        log.info("remove begin");
        for (Section section : sectionService.getSections()) {
            System.out.println(section);
        }
        log.info("remove end");
        boolean isDeleted = true;
        model.addAttribute("isDeleted", isDeleted);
        model.addAttribute("sections", sectionService.getSections());
        return "section/section_delete";
    }

    @PostMapping("/section/create")
    public String createSection(Section section, @RequestParam("typeOfSport") String typeOfSport,
                                @RequestParam("name") String nameSection,
                                @RequestParam("supervisorName") String supervisorName,
                                @RequestParam("supervisorSurname") String supervisorSurname,
                                @RequestParam("supervisorAge") String supervisorAge,
                                @RequestParam("supervisorSalary") String supervisorSalary,
                                @RequestParam("dateOfEntry") String dateOfEntry,
                                @RequestParam("dateOfBirth") String dateOfBirth,
                                @RequestParam("typeOfSectionName") String typeOfSectionName,
                                Model model) {
        //section.getSupervisor().setDateOfEntry(LocalDateTime.parse(dateOfEntryString));
        //section.getSupervisor().setDateOfEntry(LocalDateTime.parse(dateOfBirthString));
        //TypeOfSection typeOfSection = typeOfSectionRepository.findByName(section.getTypeOfSection().getName());
        //section.setTypeOfSection(typeOfSection);
        log.info("Create section");
        log.info(section.toString());
        boolean isCreated = true;
        model.addAttribute("isCreated", isCreated);
        sectionService.createSection(typeOfSectionName,nameSection, supervisorAge, supervisorSurname,
                supervisorName, supervisorSalary, dateOfBirth, dateOfEntry, typeOfSport);
        return "section/section_create";
    }

    @GetMapping("/supervisor/request")
    public String sectionRequestSupervisor(){
        return "supervisor/supervisor_request";
    }


    @PostMapping("/supervisor/request")
    public String sectionRequestSupervisorPost(Model model, @RequestParam(name = "salary", defaultValue = "no") String salary,
                                               @RequestParam(name = "allSalary", defaultValue = "no") String allSalary,
                                               @RequestParam(name = "yearOfBirth", defaultValue = "0") Integer yearOfBirth,
                                               @RequestParam(name = "allYearsBirth", defaultValue = "no") String allYearsBirth,
                                               @RequestParam(name = "yearOfWork", defaultValue = "0") Integer yearOfWork,
                                               @RequestParam(name = "allYearsWork", defaultValue = "no") String allYearsWork){

        List<Supervisor> supervisors = sectionService.supervisorRequest(salary, allSalary, yearOfBirth, allYearsBirth,
                yearOfWork, allYearsWork);
        model.addAttribute("supervisors", supervisors);
        return "supervisor/supervisor_result";
    }

}
