package nsu.trushkov.TouristClub.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nsu.trushkov.TouristClub.entity.Coach;
import nsu.trushkov.TouristClub.entity.Group;
import nsu.trushkov.TouristClub.entity.Section;
import nsu.trushkov.TouristClub.entity.Tourist;
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

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class GroupController {
    private final GroupService groupService;
    private final TouristService touristService;
    private final SectionService sectionService;
    private final CoachService coachService;

    @GetMapping("/group")
    public String group(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));

        model.addAttribute("isAdmin", isAdmin);
        return "group/group_main";
    }

    @GetMapping("/group/read")
    public String groupRead(Model model){
        model.addAttribute("groups", groupService.getGroups());
        return "group/group_read";
    }

    @GetMapping("/group/create")
    public String groupCreate(Model model){
        boolean isCreated = false;
        model.addAttribute("isCreated", isCreated);
        model.addAttribute("tourists",touristService.getTourists());
        model.addAttribute("sections",sectionService.getSections());
        model.addAttribute("coaches",coachService.getCoaches());
        return "group/group_create";
    }


    @PostMapping("/group/create")
    public String createGroup(@RequestParam("selectedTourists") List<Long> idsTourist,
                              @RequestParam("sectionId") Long sectionId,
                              @RequestParam("coachId") Long coachId,
                              @RequestParam("nameGroup") String nameGroup,
                              Model model) {

        groupService.createGroup(idsTourist, sectionId, coachId, nameGroup);

        boolean isCreated = true;
        model.addAttribute("isCreated", isCreated);
        model.addAttribute("tourists",touristService.getTourists());
        model.addAttribute("sections",sectionService.getSections());
        model.addAttribute("coaches",coachService.getCoaches());
        return "group/group_create";
    }

    @GetMapping("/group/delete")
    public String groupDel(Model model){
        boolean isDeleted = false;
        model.addAttribute("isDeleted", isDeleted);
        model.addAttribute("groups", groupService.getGroups());
        return "group/group_delete";
    }

    @PostMapping("/group/delete/{id}")
    public String groupDeletePost(@PathVariable long id, Model model){
        groupService.deleteGroup(id);
        boolean isDeleted = true;
        model.addAttribute("isDeleted", isDeleted);
        model.addAttribute("groups", groupService.getGroups());
        return "group/group_delete";
    }

    @GetMapping("/group/update")
    public String groupUpdateGet(Model model){
        model.addAttribute("groups", groupService.getGroups());
        model.addAttribute("tourists",touristService.getTourists());
        return "group/group_update";
    }

    @PostMapping("/group/update/{id}")
    public String groupUpdatePost(@PathVariable long id,
                                  @RequestParam("selectedTourists") List<Long> idsTourist,
                                  @RequestParam("nameGroup") String nameGroup, Model model){
        groupService.updateGroup(id, idsTourist, nameGroup);
        model.addAttribute("groups", groupService.getGroups());
        model.addAttribute("tourists",touristService.getTourists());
        return "group/group_update";
    }

}
