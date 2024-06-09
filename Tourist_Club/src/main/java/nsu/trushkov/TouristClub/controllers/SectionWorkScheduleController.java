package nsu.trushkov.TouristClub.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nsu.trushkov.TouristClub.services.CoachService;
import nsu.trushkov.TouristClub.services.GroupService;
import nsu.trushkov.TouristClub.services.SectionWorkScheduleService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequiredArgsConstructor
public class SectionWorkScheduleController {
    private final SectionWorkScheduleService sectionWorkScheduleService;
    private final GroupService groupService;
    private final CoachService coachService;


    @GetMapping("/section_work_schedule")
    public String SectionWorkSchedule(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));

        model.addAttribute("isAdmin", isAdmin);
        return "section_work_schedule/section_work_schedule_main";
    }

    @GetMapping("/section_work_schedule/create")
    public String sectionWorkScheduleCreate(Model model){
        boolean isCreated = false;
        model.addAttribute("isCreated", isCreated);
        model.addAttribute("groups", groupService.getGroups());
        model.addAttribute("coaches", coachService.getCoaches());
        return "section_work_schedule/section_work_schedule_create";
    }

    @PostMapping("/section_work_schedule/create")
    public String createSectionWorkSchedule(@RequestParam("trainingName") String trainingName,
                                             @RequestParam("idGroup") Long idGroup,
                                             @RequestParam("timeBegin") String timeBegin,
                                             @RequestParam("timeEnd") String timeEnd,
                                             @RequestParam("place") String place,
                                             @RequestParam("day") String day,
                                             @RequestParam("dateStart") String dateStart,
                                            @RequestParam("dateEnd") String dateEnd,
                                            Model model) {

        sectionWorkScheduleService.createSectionWorkSchedule(trainingName, idGroup, timeBegin, timeEnd,
                place, day, dateStart, dateEnd);
        boolean isCreated = true;
        model.addAttribute("isCreated", isCreated);
        model.addAttribute("groups", groupService.getGroups());
        model.addAttribute("coaches", coachService.getCoaches());
        return "section_work_schedule/section_work_schedule_create";
    }



    @GetMapping("/section_work_schedule/read")
    public String scheduleRead(Model model){
        model.addAttribute("schedules", sectionWorkScheduleService.getSchedules());
        return "section_work_schedule/section_work_schedule_read";
    }

    @GetMapping("/section_work_schedule/delete")
    public String scheduleDeleteGet(Model model){
        boolean isDeleted = false;
        model.addAttribute("isDeleted", isDeleted);
        model.addAttribute("schedules", sectionWorkScheduleService.getSchedules());
        return "section_work_schedule/section_work_schedule_delete";
    }

    @PostMapping("/section_work_schedule/delete/{id}")
    public String scheduleDeletePost(@PathVariable long id, Model model){
        sectionWorkScheduleService.deleteSchedule(id);
        boolean isDeleted = true;
        model.addAttribute("isDeleted", isDeleted);
        model.addAttribute("schedules", sectionWorkScheduleService.getSchedules());
        return "section_work_schedule/section_work_schedule_delete";
    }



    @GetMapping("/section_work_schedule/update")
    public String scheduleUpdateGet(Model model){
        model.addAttribute("schedules", sectionWorkScheduleService.getSchedules());
        model.addAttribute("groups", groupService.getGroups());
        model.addAttribute("coaches", coachService.getCoaches());
        return "section_work_schedule/section_work_schedule_update";
    }

    @PostMapping("/section_work_schedule/update/{id}")
    public String scheduleUpdatePost(@PathVariable long id,
                                     @RequestParam("trainingName") String trainingName,
                                     @RequestParam("idGroup") Long idGroup,
                                     @RequestParam("idCoach") Long idCoach,
                                     @RequestParam("timeBegin") String timeBegin,
                                     @RequestParam("timeEnd") String timeEnd,
                                     @RequestParam("place") String place,
                                     @RequestParam("day") String day,
                                     @RequestParam("attendance") Integer attendance,
                                     @RequestParam("dateStart") String dateStart,
                                     @RequestParam("dateEnd") String dateEnd,
                                     Model model){

        log.info(trainingName);
        log.info(idGroup.toString());
        log.info(idCoach.toString());
        log.info(timeEnd);
        log.info(place);
        log.info(day);
        log.info(attendance.toString());

        sectionWorkScheduleService.updateSchedule(id, trainingName, idGroup, timeBegin, timeEnd,
                place, day, attendance, idCoach, dateStart, dateEnd);
        model.addAttribute("schedules", sectionWorkScheduleService.getSchedules());
        model.addAttribute("groups", groupService.getGroups());
        model.addAttribute("coaches", coachService.getCoaches());

        return "section_work_schedule/section_work_schedule_update";
    }




}
