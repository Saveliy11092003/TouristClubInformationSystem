package nsu.trushkov.TouristClub.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nsu.trushkov.TouristClub.entity.Route;
import nsu.trushkov.TouristClub.services.PlannedCampingService;
import nsu.trushkov.TouristClub.services.RouteService;
import nsu.trushkov.TouristClub.services.SectionService;
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
public class RouteController {
    private final RouteService routeService;
    private final StoppingPointService stoppingPointService;
    private final SectionService sectionService;
    private final PlannedCampingService plannedCampingService;

    @GetMapping("/route")
    public String route(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));

        model.addAttribute("isAdmin", isAdmin);
        return "route/route_main";
    }

    @GetMapping("/route/create")
    public String routeCreate(Model model){
        boolean isCreated = false;
        model.addAttribute("isCreated", isCreated);
        model.addAttribute("points",stoppingPointService.getStoppingPoints());
        return "route/route_create";
    }

    @PostMapping("/route/create")
    public String createRoute(@RequestParam("selectedPoints") List<Long> ids,
                              @RequestParam("routeName") String routeName,
                              @RequestParam("length") String length,
                                @RequestParam("difficulty") String difficultyRoute,
                                Model model) {

        routeService.createRoute(ids, routeName, length, difficultyRoute);

        boolean isCreated = true;
        model.addAttribute("isCreated", isCreated);
        model.addAttribute("points",stoppingPointService.getStoppingPoints());
        return "route/route_create";
    }

    @GetMapping("/route/read")
    public String routeRead(Model model){
        model.addAttribute("routes", routeService.getRoute());
        return "route/route_read";
    }

    @GetMapping("/route/delete")
    public String routeDel(Model model){
        boolean isRouteDeleted = false;
        model.addAttribute("isRouteDeleted", isRouteDeleted);
        model.addAttribute("routes", routeService.getRoute());
        return "route/route_delete";
    }

    @PostMapping("/route/delete/{id}")
    public String routeDeletePost(@PathVariable long id, Model model){
        log.info("idRoute for delete - " + id);
        routeService.deleteRoute(id);
        boolean isRouteDeleted = true;
        model.addAttribute("isRouteDeleted", isRouteDeleted);
        model.addAttribute("routes", routeService.getRoute());
        return "route/route_delete";
    }

    @GetMapping("/route/update")
    public String routeUpdateGet(Model model){
        model.addAttribute("routes", routeService.getRoute());
        model.addAttribute("points", stoppingPointService.getStoppingPoints());
        return "route/route_update";
    }

    @PostMapping("/route/update/{id}")
    public String routeUpdatePost(@PathVariable long id,
                                  @RequestParam("selectedPoints") List<Long> idsPoints,
                                  @RequestParam("length") String length,
                                  @RequestParam("routeName") String nameRoute,
                                  @RequestParam("difficulty") String difficultyRoute,
                                  Model model){
        log.info("difficulty " + difficultyRoute);
        routeService.updateRoute(id, idsPoints, nameRoute, length, difficultyRoute);
        model.addAttribute("routes", routeService.getRoute());
        model.addAttribute("points", stoppingPointService.getStoppingPoints());
        return "route/route_update";
    }

    @GetMapping("/route/requests")
    public String routeRequests(Model model){
        return "route/route_requests";
    }

    @GetMapping("/route/request9")
    public String routeRequestGet(Model model){
        model.addAttribute("points", stoppingPointService.getStoppingPoints());
        return "route/route_request9";
    }

    @PostMapping("/route/request9")
    public String routeRequestPost(Model model, @RequestParam("difficulty") String difficultyRoute,
                                   @RequestParam("length") Integer length, @RequestParam("idPoint") Long idPoint){

        List<Route> routes = routeService.requestRoute(difficultyRoute, length, idPoint);

        model.addAttribute("routes", routes);
        model.addAttribute("count", routes.size());
        return "route/route_result";
    }

    @GetMapping("/route/request8_1")
    public String routeRequest8_1(Model model){
        model.addAttribute("sections", sectionService.getSections());
        return "route/route_request8_1";
    }

    @PostMapping("/route/request8_1")
    public String routeRequestPost8_1(Model model, @RequestParam("idSection") Long idSection,
                                      @RequestParam("dateStart") String dateStart,
                                      @RequestParam("dateEnd") String dateEnd) {

        List<Route> routes = routeService.requestRoute8_1(idSection, dateStart, dateEnd);

        model.addAttribute("routes", routes);
        model.addAttribute("count", routes.size());
        return "route/route_result";
    }

    @GetMapping("/route/request8_2")
    public String routeRequest8_2(Model model){
        model.addAttribute("instructors", plannedCampingService.getAllInstructors());
        return "route/route_request8_2";
    }

    @PostMapping("/route/request8_2")
    public String routeRequestPost8_2(Model model, @RequestParam("idInstructor") Long idInstructor) {

        List<Route> routes = routeService.requestRoute8_2(idInstructor);

        model.addAttribute("routes", routes);
        model.addAttribute("count", routes.size());
        return "route/route_result";
    }

    @GetMapping("/route/request8_3")
    public String routeRequest8_3(Model model){
        return "route/route_request8_3";
    }

    @PostMapping("/route/request8_3")
    public String routeRequestPost8_3(Model model, @RequestParam("count") Integer count) {

        List<Route> routes = routeService.requestRoute8_3(count);

        model.addAttribute("routes", routes);
        model.addAttribute("count", routes.size());
        return "route/route_result";
    }
    
}
