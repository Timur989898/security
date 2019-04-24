package ru.kpfu.itis.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.springframework.web.util.UriComponentsBuilder;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.forms.LoginForm;
import ru.kpfu.itis.repository.UserAuthorityRepository;
import ru.kpfu.itis.service.UserService;

import java.util.Arrays;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserAuthorityRepository userAuthorityRepo;

    protected String showRegisterForm(ModelMap map){
        map.put("user", new User());
        return "signup";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @PreAuthorize("isAnonymous()")
    public String login(ModelMap map) {
        map.put("loginForm", new LoginForm());
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @PreAuthorize("isAnonymous()")
    public String login(@RequestParam(required = false) String error,
                        @ModelAttribute("loginForm") LoginForm loginForm,
                        BindingResult result, ModelMap map,
                        HttpServletResponse response,
                        HttpSession session) {
        if (error != null) {
            map.put("error", error);
            return "login";
        }
        session.setAttribute("user", loginForm);
        return "redirect:" + MvcUriComponentsBuilder.fromMappingName("AC#profile").build();
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    @PreAuthorize("isAnonymous()")
    public String register(ModelMap map, HttpServletRequest request) {
        map.put("user", new User());
        return showRegisterForm(map);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @PreAuthorize("isAnonymous()")
    public String registerHandler(
            RedirectAttributes redirectAttributes,
            @RequestParam("csrf_token") String token,
            @ModelAttribute("user") @Valid User user,
            BindingResult result,
            ModelMap map
    ) {
        if (!token.equals("qwe12123wqe231eqw123")) {
            return "redirect:" + MvcUriComponentsBuilder.fromMappingName("AC#register").build();
        }
        if (!result.hasErrors()) {
            try{
                userService.registerUser(user);
                redirectAttributes.addFlashAttribute("message", "You has been registered successfully");
                redirectAttributes.addFlashAttribute("messageType", "success");
                return "redirect:" + MvcUriComponentsBuilder.fromMappingName("AC#profile").build();
            }
            catch(DuplicateKeyException ex){
                result.rejectValue("username", "entry.duplicate", "There is account with such email already.");
            }
        }
        return showRegisterForm(map);
    }

    @RequestMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public String profile(HttpSession session, ModelMap map) {

        return "welcome";
    }

    @RequestMapping("/logout")
    @PreAuthorize("isAuthenticated()")
    public String logout(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie c: cookies) {
            if (c.getName().equals("user")) {
                c.setMaxAge(0);
            }
        }
        return "redirect:/test/login";
    }
}


