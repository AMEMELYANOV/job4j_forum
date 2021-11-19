package ru.job4j.forum.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.UserService;

@Controller
public class RegControl {
    private final UserService userService;

    public RegControl(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/reg")
    public String saveUser(@ModelAttribute User user, Model model) {
        user.setEnabled(true);
        boolean rsl = userService.saveNewUser(user.getUsername(), user.getPassword());
        if (!rsl) {
            model.addAttribute("message", "This username exists!");
            return "reg";
        }
        return "login";
    }

    @GetMapping("/reg")
    public String regPage() {
        return "reg";
    }
}