package nsu.trushkov.TouristClub.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nsu.trushkov.TouristClub.entity.Tourist;
import nsu.trushkov.TouristClub.services.*;
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
@Slf4j
@RequiredArgsConstructor
public class TouristController {
    private final TouristService touristService;
    private final GroupService groupService;
    private final SectionService sectionService;
    private final PlannedCampingService plannedCampingService;
    private final RouteService routeService;
    private final StoppingPointService stoppingPointService;

    @GetMapping("/tourist")
    public String tourist(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));

        model.addAttribute("isAdmin", isAdmin);
        return "tourist/tourist_main";
    }

    @GetMapping("/tourist/create")
    public String touristCreate(Model model){
        boolean isCreated = false;
        model.addAttribute("isCreated", isCreated);
        return "tourist/tourist_create";
    }

    @GetMapping("/tourist/read")
    public String touristRead(Model model){
        model.addAttribute("tourists", touristService.getTourists());
        return "tourist/tourist_read";
    }

    @GetMapping("/tourist/update")
    public String touristUpdateGet(Model model){
        model.addAttribute("tourists", touristService.getTourists());
        return "tourist/tourist_update";
    }

    @PostMapping("/tourist/update/{id}")
    public String touristUpdatePost(Tourist tourist, @PathVariable long id, Model model){
        touristService.updateTourist(id, tourist);
        model.addAttribute("tourists", touristService.getTourists());
        return "tourist/tourist_update";
    }

    @GetMapping("/tourist/delete")
    public String touristDeleteGet(Model model){
        boolean isDeleted = false;
        model.addAttribute("isDeleted", isDeleted);
        model.addAttribute("tourists", touristService.getTourists());
        return "tourist/tourist_delete";
    }

    @PostMapping("/tourist/delete/{id}")
    public String touristDeletePost(@PathVariable long id, Model model){
        touristService.deleteTourist(id);
        boolean isDeleted = true;
        model.addAttribute("isDeleted", isDeleted);
        model.addAttribute("tourists", touristService.getTourists());
        return "tourist/tourist_delete";
    }

    @PostMapping("/tourist/create")
    public String createTourist(Tourist tourist, Model model) {
        log.info("Create tourist");
        log.info(tourist.toString());
        touristService.createTourist(tourist);
        boolean isCreated = true;
        model.addAttribute("isCreated", isCreated);
        return "tourist/tourist_create";
    }

    @GetMapping("/tourist/request")
    public String touristRequestGet(Model model){
        return "tourist/tourist_request";
    }


    @PostMapping("/tourist/request/section")
    public String touristRequestSection(Model model, @RequestParam("idSection") Long idSection,
                                        @RequestParam("sex") String sex, @RequestParam(name = "age", defaultValue = "No") String age,
                                        @RequestParam(name = "allSex", defaultValue = "No") String allSex) {

        model.addAttribute("tourists", touristService.getTourists());
        model.addAttribute("groups", groupService.getGroups());
        model.addAttribute("sections", sectionService.getSections());

        log.info("BEGIN");
        log.info(allSex);
        log.info(age.toString());
        List<Tourist> tourists = touristService.requestSection(idSection, sex, age, allSex);
        model.addAttribute("tourists", tourists);
        model.addAttribute("countOfTourists", tourists.size());

        return "tourist/tourist_result";
    }

    @GetMapping("/tourist/request/section")
    public String touristRequestSectionGet(Model model){
        model.addAttribute("tourists", touristService.getTourists());
        model.addAttribute("groups", groupService.getGroups());
        model.addAttribute("sections", sectionService.getSections());
        return "tourist/tourist_request_section";
    }

    @PostMapping("/tourist/request/group")
    public String touristRequestGroup(Model model, @RequestParam("idGroup") Long idGroup,
                                        @RequestParam("sex") String sex, @RequestParam(name = "age", defaultValue = "No") String age,
                                        @RequestParam(name = "allSex", defaultValue = "No") String allSex) {

        List<Tourist> tourists = touristService.requestGroup(idGroup, sex, age, allSex);
        model.addAttribute("tourists", tourists);
        model.addAttribute("countOfTourists", tourists.size());

        return "tourist/tourist_result";
    }

    @GetMapping("/tourist/request/group")
    public String touristRequestGroupGet(Model model) {

        model.addAttribute("tourists", touristService.getTourists());
        model.addAttribute("groups", groupService.getGroups());
        model.addAttribute("sections", sectionService.getSections());

        return "tourist/tourist_request_group";
    }

    @GetMapping("/tourist/request5/count")
    public String touristRequest5Count(Model model) {

        model.addAttribute("sections", sectionService.getSections());
        model.addAttribute("groups", groupService.getGroups());

        return "tourist/tourist_request_count";
    }

    @PostMapping("/tourist/request5/count")
    public String touristRequest5CountPost(Model model,  @RequestParam(name = "idGroup", defaultValue = "0") Long idGroup,
                                           @RequestParam(name = "idSection", defaultValue = "0") Long idSection,
                                           @RequestParam("count") Integer count) {

        List<Tourist> tourists = touristService.request5Count(idSection, idGroup, count);
        model.addAttribute("tourists", tourists);
        model.addAttribute("countOfTourists", tourists.size());

        return "tourist/tourist_result";
    }

    @GetMapping("/tourist/request5/camping")
    public String touristRequest5Camping(Model model) {

        model.addAttribute("sections", sectionService.getSections());
        model.addAttribute("groups", groupService.getGroups());
        model.addAttribute("campings", plannedCampingService.getCampings());

        return "tourist/tourist_request_camping";
    }

    @PostMapping("/tourist/request5/camping")
    public String touristRequest5CampingPost(Model model,  @RequestParam(name = "idGroup", defaultValue = "0") Long idGroup,
                                           @RequestParam(name = "idSection", defaultValue = "0") Long idSection,
                                           @RequestParam("idCamping") Long idCamping) {

        List<Tourist> tourists = touristService.request5Camping(idSection, idGroup, idCamping);
        model.addAttribute("tourists", tourists);
        model.addAttribute("countOfTourists", tourists.size());

        return "tourist/tourist_result";
    }

    @GetMapping("/tourist/request5/time")
    public String touristRequest5Time(Model model) {

        model.addAttribute("sections", sectionService.getSections());
        model.addAttribute("groups", groupService.getGroups());

        return "tourist/tourist_request_time";
    }

    @PostMapping("/tourist/request5/time")
    public String touristRequest5TimePost(Model model,  @RequestParam(name = "idGroup", defaultValue = "0") Long idGroup,
                                             @RequestParam(name = "idSection", defaultValue = "0") Long idSection,
                                             @RequestParam("dateStart") String dateStart,
                                          @RequestParam("dateEnd") String dateEnd) {

        List<Tourist> tourists = touristService.request5Time(idSection, idGroup, dateStart, dateEnd);
        model.addAttribute("tourists", tourists);
        model.addAttribute("countOfTourists", tourists.size());

        return "tourist/tourist_result";
    }

    @GetMapping("/tourist/request5/route")
    public String touristRequest5Route(Model model) {

        model.addAttribute("sections", sectionService.getSections());
        model.addAttribute("groups", groupService.getGroups());
        model.addAttribute("routes", routeService.getRoute());

        return "tourist/tourist_request_route";
    }

    @PostMapping("/tourist/request5/route")
    public String touristRequest5TimeRoute(Model model,  @RequestParam(name = "idGroup", defaultValue = "0") Long idGroup,
                                          @RequestParam(name = "idSection", defaultValue = "0") Long idSection,
                                          @RequestParam("idRoute") Long idRoute) {

        List<Tourist> tourists = touristService.request5Route(idSection, idGroup, idRoute);
        model.addAttribute("tourists", tourists);
        model.addAttribute("countOfTourists", tourists.size());

        return "tourist/tourist_result";
    }


    @GetMapping("/tourist/request5/point")
    public String touristRequest5Point(Model model) {

        model.addAttribute("sections", sectionService.getSections());
        model.addAttribute("groups", groupService.getGroups());
        model.addAttribute("points", stoppingPointService.getStoppingPoints());

        return "tourist/tourist_request_point";
    }

    @PostMapping("/tourist/request5/point")
    public String touristRequest5TimePoint(Model model,  @RequestParam(name = "idGroup", defaultValue = "0") Long idGroup,
                                           @RequestParam(name = "idSection", defaultValue = "0") Long idSection,
                                           @RequestParam("idPoint") Long idPoint) {

        List<Tourist> tourists = touristService.request5Point(idSection, idGroup, idPoint);
        model.addAttribute("tourists", tourists);
        model.addAttribute("countOfTourists", tourists.size());

        return "tourist/tourist_result";
    }

    @GetMapping("/tourist/request5/difficulty")
    public String touristRequest5Difficulty(Model model) {

        model.addAttribute("sections", sectionService.getSections());
        model.addAttribute("groups", groupService.getGroups());

        return "tourist/tourist_request_difficulty";
    }

    @PostMapping("/tourist/request5/difficulty")
    public String touristRequest5TimeDifficulty(Model model,  @RequestParam(name = "idGroup", defaultValue = "0") Long idGroup,
                                           @RequestParam(name = "idSection", defaultValue = "0") Long idSection,
                                           @RequestParam("difficulty") String difficulty) {

        List<Tourist> tourists = touristService.request5Difficulty(idSection, idGroup, difficulty);
        model.addAttribute("tourists", tourists);
        model.addAttribute("countOfTourists", tourists.size());

        return "tourist/tourist_result";
    }

    @GetMapping("/tourist/request5/category")
    public String touristRequestCategory(Model model) {

        model.addAttribute("sections", sectionService.getSections());
        model.addAttribute("groups", groupService.getGroups());

        return "tourist/tourist_category";
    }

    @PostMapping("/tourist/request5/category")
    public String touristRequestCategory(Model model, @RequestParam(name = "idGroup", defaultValue = "0") Long idGroup,
                                                @RequestParam(name = "idSection", defaultValue = "0") Long idSection,
                                                @RequestParam("category") String category) {

        List<Tourist> tourists = touristService.requestCategory(idSection, idGroup, category);
        model.addAttribute("tourists", tourists);
        model.addAttribute("countOfTourists", tourists.size());

        return "tourist/tourist_result";
    }

    @GetMapping("/tourist/request10/type")
    public String touristRequest10Type(Model model) {

        model.addAttribute("sections", sectionService.getSections());
        model.addAttribute("groups", groupService.getGroups());

        return "tourist/tourist_request10";
    }

    @PostMapping("/tourist/request10/type")
    public String touristRequest10TypePost(Model model, @RequestParam(name = "idGroup", defaultValue = "0") Long idGroup,
                                         @RequestParam(name = "idSection", defaultValue = "0") Long idSection,
                                         @RequestParam("typeOfRoute") String typeOfRoute) {

        List<Tourist> tourists = touristService.request10TypeOfRoute(idSection, idGroup, typeOfRoute);
        model.addAttribute("tourists", tourists);
        model.addAttribute("countOfTourists", tourists.size());

        return "tourist/tourist_result";
    }

    @GetMapping("/tourist/request12")
    public String touristRequest12(Model model) {

        model.addAttribute("sections", sectionService.getSections());
        model.addAttribute("groups", groupService.getGroups());

        return "tourist/tourist_request12";
    }

    @PostMapping("/tourist/request12")
    public String touristRequest12Post(Model model, @RequestParam(name = "idGroup", defaultValue = "0") Long idGroup,
                                           @RequestParam(name = "idSection", defaultValue = "0") Long idSection) {

        List<Tourist> tourists = touristService.request12OfRoute(idSection, idGroup);
        model.addAttribute("tourists", tourists);
        model.addAttribute("countOfTourists", tourists.size());

        return "tourist/tourist_result";
    }

    @GetMapping("/tourist/request13")
    public String touristRequest13(Model model) {

        model.addAttribute("sections", sectionService.getSections());
        model.addAttribute("groups", groupService.getGroups());
        model.addAttribute("routes", routeService.getRoute());

        return "tourist/tourist_request13";
    }

    @PostMapping("/tourist/request13")
    public String touristRequest13Post(Model model, @RequestParam(name = "idGroup", defaultValue = "0") Long idGroup,
                                       @RequestParam(name = "idSection", defaultValue = "0") Long idSection,
                                       @RequestParam(name = "idsRoutes", required = false) List<Long> idsRoutes,
                                       @RequestParam(name = "allRoutes", defaultValue = "no") String allRoutes) {

        List<Tourist> tourists = touristService.request13(idSection, idGroup, idsRoutes, allRoutes);
        model.addAttribute("tourists", tourists);
        model.addAttribute("countOfTourists", tourists.size());

        return "tourist/tourist_result";
    }

    @GetMapping("/instructor")
    public String instructorRequests() {
        return "instructor/instructor_requests";
    }

    @GetMapping("/instructor/request1")
    public String instructorRequest11_1() {
        return "instructor/instructor_request1";
    }

    @PostMapping("/instructor/request1")
    public String instructorRequest11_1Post(@RequestParam(name = "typeInstructor", defaultValue = "no") String typeInstructor,
                                          @RequestParam(name = "difficulty", defaultValue = "no") String difficulty,
                                          Model model) {

        List<Tourist> instructors = touristService.getInstructorsRequest1(typeInstructor, difficulty);

        model.addAttribute("instructors", instructors);
        model.addAttribute("count", instructors.size());

        return "instructor/instructor_result";
    }

    @GetMapping("/instructor/request2")
    public String instructorRequest11_2() {
        return "instructor/instructor_request2";
    }

    @PostMapping("/instructor/request2")
    public String instructorRequest11_2Post(@RequestParam(name = "typeInstructor", defaultValue = "no") String typeInstructor,
                                            @RequestParam(name = "count") Integer count,
                                            Model model) {

        List<Tourist> instructors = touristService.getInstructorsRequest2(typeInstructor, count);

        model.addAttribute("instructors", instructors);
        model.addAttribute("count", instructors.size());

        return "instructor/instructor_result";
    }

    @GetMapping("/instructor/request3")
    public String instructorRequest11_3(Model model) {
        model.addAttribute("campings", plannedCampingService.getCampings());
        return "instructor/instructor_request3";
    }

    @PostMapping("/instructor/request3")
    public String instructorRequest11_3Post(@RequestParam(name = "typeInstructor", defaultValue = "no") String typeInstructor,
                                            @RequestParam(name = "idCamping") Long idCamping,
                                            Model model) {

        List<Tourist> instructors = touristService.getInstructorsRequest3(typeInstructor, idCamping);

        model.addAttribute("instructors", instructors);
        model.addAttribute("count", instructors.size());

        return "instructor/instructor_result";
    }

    @GetMapping("/instructor/request4")
    public String instructorRequest11_4(Model model) {
        model.addAttribute("routes", routeService.getRoute());
        return "instructor/instructor_request4";
    }

    @PostMapping("/instructor/request4")
    public String instructorRequest11_4Post(@RequestParam(name = "typeInstructor", defaultValue = "no") String typeInstructor,
                                            @RequestParam(name = "idRoute") Long idRoute,
                                            Model model) {

        List<Tourist> instructors = touristService.getInstructorsRequest4(typeInstructor, idRoute);

        model.addAttribute("instructors", instructors);
        model.addAttribute("count", instructors.size());

        return "instructor/instructor_result";
    }

    @GetMapping("/instructor/request5")
    public String instructorRequest11_5(Model model) {
        model.addAttribute("points", stoppingPointService.getStoppingPoints());
        return "instructor/instructor_request5";
    }

    @PostMapping("/instructor/request5")
    public String instructorRequest11_5Post(@RequestParam(name = "typeInstructor", defaultValue = "no") String typeInstructor,
                                            @RequestParam(name = "idPoint") Long idPoint,
                                            Model model) {

        List<Tourist> instructors = touristService.getInstructorsRequest5(typeInstructor, idPoint);

        model.addAttribute("instructors", instructors);
        model.addAttribute("count", instructors.size());

        return "instructor/instructor_result";
    }


}
