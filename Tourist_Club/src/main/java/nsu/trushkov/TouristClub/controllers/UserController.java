package nsu.trushkov.TouristClub.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nsu.trushkov.TouristClub.entity.User;
import nsu.trushkov.TouristClub.services.UserService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JdbcTemplate jdbcTemplate;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @GetMapping("/login")
    public String login() {
        log.info("login get");

        //CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        //model.addAttribute("_csrf", csrfToken);

        return "login";
    }

    @PostMapping("/registration")
    public String createUser(User user, Model model) {
        if (userService.createUser(user)) {
            model.addAttribute("errorMessage", "Пользователь с email " + user.getEmail() +
                    " уже существует");
            return "registration";
        }
        return "login";
    }

    @GetMapping("/admin")
    public String adminPanel(Model model) {
        return "admin_panel";
    }

    @PostMapping("/admin_panel")
    public String adminPanelPost(Model model, @RequestParam(name = "request") String request) {

        log.info("-------------");
        if (!isSelectQuery(request)) {
            model.addAttribute("error", true);
            return "request_admin"; // Замените "error_view" на имя вашего представления для ошибок
        }
        List<Map<String, Object>> result = jdbcTemplate.queryForList(request);
        for (Map<String, Object> map : result) {
            log.info("map.key " + map.keySet());
            log.info("map.value " + map.values());
        }
        log.info("------------");
        log.info("request " + request);
        log.info("result " + result);
        model.addAttribute("result", result);

        List<String> columns = new ArrayList<>(result.get(0).keySet());

        List<List<Object>> rows = new ArrayList<>();

        for (Map<String, Object> map : result) {
            List<Object> row = new ArrayList<>();
            for (Object element : map.values()) {
                row.add(element);
            }
            rows.add(row);
        }
        model.addAttribute("rows", rows);
        model.addAttribute("columns", columns);
        model.addAttribute("error", false);
        return "request_admin";
    }

    private boolean isSelectQuery(String query) {
        String normalizedQuery = query.replaceAll("\\s", "").toLowerCase();
        return normalizedQuery.startsWith("select");
    }

}
